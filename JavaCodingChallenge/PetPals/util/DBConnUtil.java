package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {
    public static Connection getConnection() {
        Properties props = DBPropertyUtil.loadProperties();
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println(" Error connecting to database: " + e.getMessage());
            return null;
        }
    }
}
