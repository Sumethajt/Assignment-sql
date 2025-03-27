import java.sql.*;
import java.util.Scanner;
public class Cfs2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/courier_management_sys";
        String user = "root";
        String password = "12345678";

        Scanner sn = new Scanner(System.in);
        System.out.println("Enter the packageID");
        int packageID = sn.nextInt();

        String query = "SELECT Weight FROM Package WHERE PackageID = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, packageID);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                double weight = rs.getDouble("Weight");
                int weightCategory = (weight <= 2) ? 1 : (weight <= 5) ? 2 : 3;

                // Categorize based on weight
                switch (weightCategory) {
                    case 1:
                        System.out.println("📦 The parcel is categorized as: LIGHT");
                        break;
                    case 2:
                        System.out.println("📦 The parcel is categorized as: MEDIUM");
                        break;
                    case 3:
                        System.out.println("📦 The parcel is categorized as: HEAVY");
                        break;
                    default:
                        System.out.println("⚠ Invalid weight!");
                }
            }
            else {
                System.out.println("❌ No package found with Package ID: " + packageID);
            }

    } catch (SQLException e) {
        System.err.println("❌ Database connection failed!");
        e.printStackTrace();
    } finally {
        sn.close();
    }
    }
}
