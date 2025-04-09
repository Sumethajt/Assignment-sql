package dao;

import util.DBConnUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PetDAO {
    public void displayAvailablePets() {
        String query = "SELECT * FROM Pets WHERE AvailableForAdoption = 1";

        try (Connection conn = DBConnUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("🐾 Available Pets for Adoption:");
            while (rs.next()) {
                System.out.println("Pet ID: " + rs.getInt("PetID") +
                        ", Name: " + rs.getString("Name") +
                        ", Age: " + rs.getInt("Age") +
                        ", Breed: " + rs.getString("Breed") +
                        ",Type: " + rs.getString("Type")+
                        ", AvailableForAdoption: " + rs.getBoolean("AvailableForAdoption"));
            }
        } catch (Exception e) {
            System.err.println("❌ Error retrieving pets: " + e.getMessage());
        }
    }
}
