package dao;

import exceptions.AdoptionException;
import util.DBConnUtil;

import java.sql.*;
import java.util.Scanner;

public class AdoptionEventDAO {

    public void displayAdoptionEvents()  {
        String query = "SELECT * FROM AdoptionEvents";

        try (Connection conn = DBConnUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("📅 Upcoming Adoption Events:");
            while (rs.next()) {
                System.out.println("Event ID: " + rs.getInt("EventID") +
                        ", Name: " + rs.getString("EventName") +
                        ", Date: " + rs.getTimestamp("EventDate") +
                        ", Location: " + rs.getString("Location"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching adoption events: " + e.getMessage());
        }
    }

    public void registerForEvent() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter participant type (Adopter/Shelter): ");
            String type = scanner.nextLine();

            if (name.isBlank() || (!type.equalsIgnoreCase("Adopter") && !type.equalsIgnoreCase("Shelter"))) {
                throw new AdoptionException("Invalid participant details provided.");
            }

            System.out.print("Enter Event ID: ");
            int eventId = scanner.nextInt();

            if (!eventExists(eventId)) {
                throw new AdoptionException("Event ID does not exist.");
            }

            String query = "INSERT INTO Participants (ParticipantName, ParticipantType, EventID) VALUES (?, ?, ?)";

            try (Connection conn = DBConnUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, name);
                ps.setString(2, type);
                ps.setInt(3, eventId);

                ps.executeUpdate();
                System.out.println("✅ Successfully registered for the event!");
            }

        } catch (AdoptionException e) {
            System.err.println("❌ Adoption error: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ DB error: " + e.getMessage());
        }
    }

    private boolean eventExists(int eventID) {
        String checkQuery = "SELECT 1 FROM AdoptionEvents WHERE EventID = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(checkQuery)) {

            ps.setInt(1, eventID);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // returns true if a row exists

        } catch (SQLException e) {
            System.err.println("❌ Error checking Event ID: " + e.getMessage());
            return false;
        }
    }

}
