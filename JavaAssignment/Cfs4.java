import java.sql.*;

public class Cfs4 {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3307/courier_management_sys";
        String user = "root";
        String password = "12345678";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {  // Establish connection
            System.out.println("✅ Database connected successfully!");

            // Step 1: Retrieve all pending shipments
            String shipmentQuery = "SELECT CourierID, ReceiverAddress FROM Couriers WHERE Status = 'Pending'";
            PreparedStatement psShipment = conn.prepareStatement(shipmentQuery);
            ResultSet rsShipment = psShipment.executeQuery();

            // Process each pending shipment
            while (rsShipment.next()) {
                int courierID = rsShipment.getInt("CourierID");
                String receiverAddress = rsShipment.getString("ReceiverAddress");

                // Step 2: Find the nearest available courier with the least active deliveries
                String courierQuery = "SELECT e.EmployeeID, e.Name, COUNT(c.CourierID) AS ActiveDeliveries " +
                        "FROM Employees e " +
                        "LEFT JOIN Couriers c ON e.EmployeeID = c.EmployeeID AND c.Status != 'Delivered' " +
                        "WHERE e.Role = 'Courier' " +
                        "GROUP BY e.EmployeeID, e.Name " + // FIXED: Group by EmployeeID and Name
                        "ORDER BY ABS((SELECT LocationID FROM Couriers WHERE ReceiverAddress = ?) - e.EmployeeID) ASC, ActiveDeliveries ASC " +
                        "LIMIT 1";  // Select the best courier (closest + least loaded)

                PreparedStatement psCourier = conn.prepareStatement(courierQuery);
                psCourier.setString(1, receiverAddress);
                ResultSet rsCourier = psCourier.executeQuery();

                if (rsCourier.next()) {
                    int employeeID = rsCourier.getInt("EmployeeID");
                    String employeeName = rsCourier.getString("Name");

                    // Step 3: Assign the courier to the shipment
                    String assignQuery = "UPDATE Couriers SET EmployeeID = ?, Status = 'In Transit' WHERE CourierID = ?";
                    PreparedStatement psAssign = conn.prepareStatement(assignQuery);
                    psAssign.setInt(1, employeeID);
                    psAssign.setInt(2, courierID);
                    psAssign.executeUpdate();

                    System.out.println("✅ Shipment " + courierID + " assigned to Courier " + employeeName);
                } else {
                    System.out.println("⚠ No available courier for shipment " + courierID);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }
}
