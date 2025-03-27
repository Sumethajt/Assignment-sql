import java.sql.*;
import java.util.Scanner;

public class Cfs1 {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3307/courier_management_sys"; // Change DB name if needed
        String user = "root";  // Replace with your MySQL username
        String password = "12345678"; // Replace with your MySQL password

        // Scanner for user input
        Scanner sc = new Scanner(System.in);

        // Ask for Courier ID
        System.out.print("Enter Courier ID: ");
        int courierID = sc.nextInt();

        // Query to fetch order status
        String query = "SELECT Status FROM Couriers WHERE CourierID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Set parameter in query
            ps.setInt(1, courierID);
            ResultSet rs = ps.executeQuery();

            // Check if result exists
            if (rs.next()) {
                String status = rs.getString("Status");
                System.out.println("📦 Order Status: " + status);
            } else {
                System.out.println("❌ No order found with Courier ID: " + courierID);
            }

        } catch (SQLException e) {
            System.err.println("❌ Database connection failed!");
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}

