import java.sql.*;
import java.util.Scanner;
public class Cfs3 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/courier_management_sys";
        String user = "root";
        String password = "12345678";

        Scanner sn = new Scanner(System.in);
        System.out.println("Enter the userName:");
        String username = sn.nextLine();
        System.out.println(("Enter the password"));
        String userPassword = sn.nextLine();

        String userQuery = "SELECT Name, UserID, Email, Address, ContactNumber,'Customer' As role from Users where username = ? AND password = ?";
        String employeeQuery = "SELECT EmployeeID, Name, Email,ContactNumber,Role, Salary from Employees where username = ? AND password = ?";

        try(Connection conn = DriverManager.getConnection(url,user, password);
            PreparedStatement psUser = conn.prepareStatement(userQuery);
            PreparedStatement psEmployee = conn.prepareStatement(employeeQuery)){

            psUser.setString(1,username);
            psUser.setString(2,userPassword);
            ResultSet rsUser = psUser.executeQuery();

            if (rsUser.next()) {
                System.out.println("✅ Login successful! Welcome, Customer " + rsUser.getString("Name"));
                System.out.println("📧 Email: " + rsUser.getString("Email"));
                System.out.println("📧 UserID: " + rsUser.getString("UserID"));
                System.out.println("🏠 Address: " + rsUser.getString("Address"));
                System.out.println("📞 Contact: " + rsUser.getString("ContactNumber"));
                return;
            }

            psEmployee.setString(1,username);
            psEmployee.setString(2,userPassword);
            ResultSet rsEmployee = psEmployee.executeQuery();

            if (rsEmployee.next()){
                System.out.println("✅ Login successful! Welcome, Employee " + rsEmployee.getString("Name"));
                System.out.println("📧 Email: " + rsEmployee.getString("Email"));
                System.out.println("📞 Contact: " + rsEmployee.getString("ContactNumber"));
                System.out.println("🛠 Role: " + rsEmployee.getString("Role"));
                System.out.println("💰 Salary: " + rsEmployee.getDouble("Salary"));
                return;
            }
            System.out.println("❌ Invalid username or password. Please try again.");
        }
        catch (SQLException e){
        System.err.println("❌ Database connection failed!");
        e.printStackTrace();
        }
        finally {
        sn.close(); // Close scanner to prevent memory leaks
        }

    }
}
