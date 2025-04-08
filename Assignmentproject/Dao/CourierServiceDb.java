package Dao;

import connectionutil.DBConnection;
import Entities.Courier;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.Connection;


import static connectionutil.DBConnection.getConnection;

public class CourierServiceDb {
    private static Connection connection;

    static {
        connection = getConnection();  // Get the connection from DBConnection class
    }


    // Method to insert a new courier order into the database
    public boolean insertCourierOrder(Courier courier) {
        String query = "INSERT INTO couriers (SenderName, SenderAddress, ReceiverName, ReceiverAddress, Status, TrackingNumber, DeliveryDate, UserID, ServiceID, PackageID, ShipmentDate, EmployeeID, LocationID, OrderPlacementDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, courier.getSenderName());
            stmt.setString(2, courier.getSenderAddress());
            stmt.setString(3, courier.getReceiverName());
            stmt.setString(4, courier.getReceiverAddress());
            stmt.setString(5, courier.getStatus());
            stmt.setString(6, courier.getTrackingNumber());
            stmt.setDate(7, Date.valueOf(courier.getDeliveryDate()));
            stmt.setInt(8, courier.getUserId());
            stmt.setInt(9, courier.getServiceID());
            stmt.setInt(10, courier.getPackageID());
            stmt.setDate(11, Date.valueOf(courier.getShipmentDate()));
            stmt.setInt(12, courier.getEmployeeId());
            stmt.setInt(13, courier.getLocationId());
            stmt.setDate(14, Date.valueOf(courier.getOrderPlacementDate())); // Assuming this field is added

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting courier order: " + e.getMessage());
            return false;
        }
    }

    // Method to update the status of a courier order by CourierID
    public boolean updateCourierStatus(int courierID, String status, Date deliveryDate) {
        String query = "UPDATE couriers SET Status = ?, DeliveryDate = ? WHERE CourierID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setDate(2, deliveryDate);
            stmt.setInt(3, courierID);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating courier status: " + e.getMessage());
            return false;
        }
    }

    // Method to retrieve a courier order by CourierID
    public Courier getCourierById(int courierID) {
        String query = "SELECT * FROM couriers WHERE CourierID = ?";
        Courier courier = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courierID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Fetching the data from ResultSet
                courier = new Courier(
                        rs.getString("SenderName"), // SenderName column
                        rs.getString("SenderAddress"), // SenderAddress column
                        rs.getString("ReceiverName"), // ReceiverName column
                        rs.getString("ReceiverAddress"), // ReceiverAddress column
                        rs.getString("Status"), // Status column
                        rs.getString("TrackingNumber"), // TrackingNumber column
                        rs.getDate("DeliveryDate") != null ? rs.getDate("DeliveryDate").toLocalDate() : null, // DeliveryDate
                        rs.getInt("UserID"), // UserID column
                        rs.getInt("ServiceID"), // ServiceID column
                        rs.getInt("PackageID"), // PackageID column
                        rs.getDate("ShipmentDate") != null ? rs.getDate("ShipmentDate").toLocalDate() : null, // ShipmentDate
                        rs.getInt("EmployeeID"), // EmployeeID column
                        rs.getInt("LocationID"), // LocationID column
                        rs.getDate("OrderPlacementDate") != null ? rs.getDate("OrderPlacementDate").toLocalDate() : null, // OrderPlacementDate
                        rs.getInt("PaymentID") // PaymentID column
                );
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving courier by ID: " + e.getMessage());
        }
        return courier;
    }

    // Method to retrieve all courier orders
    public List<Courier> getAllCouriers() {
        String query = "SELECT * FROM couriers";
        List<Courier> couriers = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Courier courier = new Courier(
                        rs.getString("SenderName"), // SenderName column
                        rs.getString("SenderAddress"), // SenderAddress column
                        rs.getString("ReceiverName"), // ReceiverName column
                        rs.getString("ReceiverAddress"), // ReceiverAddress column
                        rs.getString("Status"), // Status column
                        rs.getString("TrackingNumber"), // TrackingNumber column
                        rs.getDate("DeliveryDate") != null ? rs.getDate("DeliveryDate").toLocalDate() : null, // DeliveryDate
                        rs.getInt("UserID"), // UserID column
                        rs.getInt("ServiceID"), // ServiceID column
                        rs.getInt("PackageID"), // PackageID column
                        rs.getDate("ShipmentDate") != null ? rs.getDate("ShipmentDate").toLocalDate() : null, // ShipmentDate
                        rs.getInt("EmployeeID"), // EmployeeID column
                        rs.getInt("LocationID"), // LocationID column
                        rs.getDate("OrderPlacementDate") != null ? rs.getDate("OrderPlacementDate").toLocalDate() : null, // OrderPlacementDate
                        rs.getInt("PaymentID") // PaymentID column
                );
                couriers.add(courier);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all couriers: " + e.getMessage());
        }
        return couriers;
    }

    // Method to retrieve the delivery history for a specific tracking number
    public List<Courier> getDeliveryHistoryByTrackingNumber(String trackingNumber) {
        String query = "SELECT * FROM couriers WHERE TrackingNumber = ?";
        List<Courier> history = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, trackingNumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Courier courier = new Courier(
                        rs.getString("SenderName"), // SenderName column
                        rs.getString("SenderAddress"), // SenderAddress column
                        rs.getString("ReceiverName"), // ReceiverName column
                        rs.getString("ReceiverAddress"), // ReceiverAddress column
                        rs.getString("Status"), // Status column
                        rs.getString("TrackingNumber"), // TrackingNumber column
                        rs.getDate("DeliveryDate") != null ? rs.getDate("DeliveryDate").toLocalDate() : null, // DeliveryDate
                        rs.getInt("UserID"), // UserID column
                        rs.getInt("ServiceID"), // ServiceID column
                        rs.getInt("PackageID"), // PackageID column
                        rs.getDate("ShipmentDate") != null ? rs.getDate("ShipmentDate").toLocalDate() : null, // ShipmentDate
                        rs.getInt("EmployeeID"), // EmployeeID column
                        rs.getInt("LocationID"), // LocationID column
                        rs.getDate("OrderPlacementDate") != null ? rs.getDate("OrderPlacementDate").toLocalDate() : null, // OrderPlacementDate
                        rs.getInt("PaymentID") // PaymentID column
                );
                history.add(courier);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving delivery history: " + e.getMessage());
        }
        return history;
    }
    public String generateTrackingNumber() {
        // Generate a random 4-digit number between 1000 and 9999
        Random rand = new Random();
        int randomNumber = rand.nextInt(9000) + 1000;  // Ensures the number is always 4 digits

        // Concatenate the timestamp and random number with "TRK" prefix
        return "TRK"  + randomNumber;  // Example: TRK17118152923711234
    }

    public static String generateStatus(LocalDate shipmentDate, LocalDate deliveryDate) {
        LocalDate currentDate = LocalDate.now();

        if (currentDate.isBefore(shipmentDate)) {
            // Order is still pending, shipment hasn't occurred
            return "Pending";
        } else if (currentDate.isAfter(shipmentDate) && currentDate.isBefore(deliveryDate)) {
            // Order is in transit, between shipment and delivery dates
            return "In Transit";
        } else if (currentDate.isAfter(deliveryDate)) {
            // Order is delivered, the delivery date has passed
            return "Delivered";
        } else if (currentDate.isAfter(shipmentDate) && currentDate.isBefore(deliveryDate)) {
            // If it's after shipment but before delivery, mark it as shipped
            return "Shipped";
        }
        return "Pending";
    }

    public static int insertNewUser(String name, String email, String password, String contactNumber, String address, String username) {
        String insertUserQuery = "INSERT INTO users (Name, Email, Password, ContactNumber, Address, UserName) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, contactNumber);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, username);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Get the auto-generated UserID
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // Return the generated UserID
                }
            }
        } catch (SQLException e) {
            System.out.println("⚠️ Error inserting new user: " + e.getMessage());
        }
        return -1; // Return -1 if user insertion failed
    }

    public int getOrCreatePackage(String packageName, int quantity, double weight) {
        int packageId = -1;

        String selectQuery = "SELECT PackageID FROM package WHERE PackageName = ? AND Quantity = ? AND Weight = ?";
        String insertQuery = "INSERT INTO package (PackageName, Quantity, Weight) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
             PreparedStatement insertStmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            // Check if the package exists
            selectStmt.setString(1, packageName);
            selectStmt.setInt(2, quantity);
            selectStmt.setDouble(3, weight);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                packageId = rs.getInt("PackageID"); // Use existing package ID
            } else {
                // Insert new package
                insertStmt.setString(1, packageName);
                insertStmt.setInt(2, quantity);
                insertStmt.setDouble(3, weight);
                int rowsAffected = insertStmt.executeUpdate();

                // Get the generated package ID
                if (rowsAffected > 0) {
                    ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        packageId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageId; // Return the package ID
    }

    public int getOrUpdateLocation(String locationName, String address) {
        int locationID = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection(); // Ensure active DB connection

            // Step 1: Check if the location already exists
            String checkQuery = "SELECT LocationID FROM locations WHERE locationName = ?";
            pstmt = conn.prepareStatement(checkQuery);
            pstmt.setString(1, locationName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Location exists, get the existing LocationID
                locationID = rs.getInt("LocationID");
                System.out.println("✅ Existing Location ID found: " + locationID);
            } else {
                // Step 2: Insert new location if not found
                String insertQuery = "INSERT INTO locations (locationName, address) VALUES (?, ?)";
                pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, locationName);
                pstmt.setString(2, address);
                pstmt.executeUpdate();

                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    locationID = rs.getInt(1); // Get the newly generated LocationID
                    System.out.println("✅ New Location added with ID: " + locationID);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ SQL Error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("⚠️ Error closing resources: " + e.getMessage());
            }
        }
        return locationID;
    }




    public String getOrderStatus(String trackingNumber) throws SQLException {
        String orderStatus = null;

        // SQL query to fetch the status based on the tracking number
        String query = "SELECT Status FROM couriers WHERE TrackingNumber = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set the tracking number in the query
            stmt.setString(1, trackingNumber);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Check if result is available
            if (rs.next()) {
                orderStatus = rs.getString("Status");
            } else {
                orderStatus = "Order not found.";
            }
        }
        return orderStatus;
    }

    public boolean deleteCourierOrder(String trackingNumber) throws SQLException {
        String query = "DELETE FROM couriers WHERE TrackingNumber = ?";
        boolean isDeleted = false;

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set the tracking number in the query
            stmt.setString(1, trackingNumber);

            // Execute the update and check if any rows were affected
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                isDeleted = true;
            }
        }

        return isDeleted;
    }

    public static int getGeneratedCourierID() {
        int courierId = -1;
        String query = "SELECT CourierID FROM couriers ORDER BY CourierID DESc LIMIT 1";  // Gets the most recent ID

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()) {

            if (resultSet.next()) {
                courierId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courierId ;
    }

   public boolean updateUserContactInfo(int userId, String ContactNumber, String address, String email) {
        String sql = "UPDATE Users SET ContactNumber = ?, address = ?, email = ? WHERE userID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ContactNumber);
            stmt.setString(2, address);
            stmt.setString(3, email);
            stmt.setInt(4, userId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("❌ Database Error: " + e.getMessage());
            return false;
        }
    }

    public void displayDeliveryHistory(String trackingNumber) {
        String sql = "SELECT * FROM Couriers WHERE trackingNumber = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, trackingNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n📦 Delivery History:");
                System.out.println("Courier ID: " + rs.getInt("courierID"));
                System.out.println("Sender: " + rs.getString("senderName"));
                System.out.println("Receiver: " + rs.getString("receiverName"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("Order Placement Date: " + rs.getDate("OrderPlacementDate"));
                System.out.println("Delivery Date: " + rs.getDate("deliveryDate"));
                System.out.println("Shipment Date: " + rs.getDate("shipmentDate"));
            } else {
                System.out.println("⚠ No delivery record found for tracking number: " + trackingNumber);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching delivery history: " + e.getMessage());
        }
    }

    public void generateShipmentStatusReport() {
        String sql = "SELECT status, COUNT(*) AS count FROM Couriers GROUP BY status";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n🚚 Shipment Status Report:");
            while (rs.next()) {
                String status = rs.getString("status");
                int count = rs.getInt("count");
                System.out.printf("%-20s : %d%n", status, count);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error generating report: " + e.getMessage());
        }
    }

    public void generateRevenueReport() {
        String sql = "SELECT l.locationName, SUM(p.amount) AS totalRevenue " +
                "FROM Payments p JOIN Locations l ON p.locationID = l.locationID " +
                "GROUP BY l.locationName";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n💰 Revenue Report by Location:");
            while (rs.next()) {
                String location = rs.getString("locationName");
                double revenue = rs.getDouble("totalRevenue");
                System.out.printf("%-20s : ₹%.2f%n", location, revenue);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error generating revenue report: " + e.getMessage());
        }
    }

}
