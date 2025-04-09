package dao;

import exceptions.InsufficientFundsException;
import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DonationDAO {
    public void recordDonation() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter donor name: ");
            String donorName = scanner.nextLine();

            System.out.print("Enter donation amount: ");
            double amount = scanner.nextDouble();

            if (amount < 10) {
                throw new InsufficientFundsException("Donation must be at least $10.");
            }

            String query = "INSERT INTO Donations (DonorName, DonationType, DonationAmount, DonationDate) VALUES (?, 'Cash', ?, NOW())";

            try (Connection conn = DBConnUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, donorName);
                ps.setDouble(2, amount);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("✅ Donation recorded successfully.");
                }
            }

        } catch (InsufficientFundsException e) {
            System.err.println("❌ " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Error recording donation: " + e.getMessage());
        }
    }
}
