import java.sql.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class LoopsIteration1 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/courier_management_sys";  // ✅ Ensure correct port
        String user = "root";
        String password = "12345678";  // ✅ Ensure correct password

        Scanner sn = new Scanner(System.in);
        System.out.print("Enter the UserID: ");
        int userID = sn.nextInt();

        String query = "SELECT CourierID, SenderName, ReceiverName, TrackingNumber, Status, DeliveryDate " +
                "FROM Couriers WHERE UserID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(query)) {

            System.out.println("✅ Database connected successfully!");

            ps.setInt(1, userID);
            ResultSet rsOrder = ps.executeQuery();
            List<String> orders = new ArrayList<>();

            while (rsOrder.next()) {
                String orderDetails = "\n🔹 Order #" + (orders.size() + 1) + "\n" +
                        "📦 Courier ID: " + rsOrder.getInt("CourierID") + "\n" +
                        "📍 Sender: " + rsOrder.getString("SenderName") + "\n" +
                        "📍 Receiver: " + rsOrder.getString("ReceiverName") + "\n" +
                        "🚛 Tracking Number: " + rsOrder.getString("TrackingNumber") + "\n" +
                        "📦 Status: " + rsOrder.getString("Status") + "\n" +
                        "📅 Delivery Date: " + rsOrder.getDate("DeliveryDate") + "\n" +
                        "-------------------------------";
                orders.add(orderDetails);
            }

            if (orders.isEmpty()) {
                System.out.println("⚠ No orders found for User ID: " + userID);
            } else {
                for (int i = 0; i < orders.size(); i++) {
                    System.out.println(orders.get(i));
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Database connection failed! Check username, password, or port.");
            e.printStackTrace();
        } finally {
            sn.close();
        }
    }
}
