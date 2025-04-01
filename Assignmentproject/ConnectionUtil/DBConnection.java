package connectionutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3307/courier_management_sys";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    private static Connection connection;
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if (conn.isClosed()) {
                System.out.println("❌ Error: Connection is closed immediately after opening!");
            } else {
                System.out.println("✅ Database connection is active.");
            }
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Error connecting to database: " + e.getMessage());
            return null;
        }
    }
    // ✅ Static block to initialize connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ Load MySQL driver
            connection = DriverManager.getConnection(URL, USER, PASSWORD); // ✅ Connect to DB
            System.out.println("✅ Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Error connecting to database: " + e.getMessage());
        }
    }


}

