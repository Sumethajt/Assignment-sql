package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static Properties loadProperties() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\util\\db.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading DB properties: " + e.getMessage());
        }
        return props;
    }
}
