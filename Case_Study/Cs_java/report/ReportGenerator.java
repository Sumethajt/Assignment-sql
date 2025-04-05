package report;

import database.DatabaseContext;
import exceptions.ReportGenerationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportGenerator {
    private static final Logger logger = Logger.getLogger(ReportGenerator.class.getName());

    // Generate Payroll Report
    public static void generatePayrollReport() throws ReportGenerationException {
        String query = "SELECT EmployeeID, PayPeriodStartDate, PayPeriodEndDate, NetSalary FROM Payroll";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("PAYROLL REPORT:");
            System.out.println("====================================");
            while (rs.next()) {
                System.out.println("EmployeeID: " + rs.getInt("EmployeeID"));
                System.out.println("Pay Period: " + rs.getDate("PayPeriodStartDate") + " to " + rs.getDate("PayPeriodEndDate"));
                System.out.println("Net Salary: $" + rs.getDouble("NetSalary"));
                System.out.println("------------------------------------");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error generating payroll report", e);
            throw new ReportGenerationException("Failed to generate payroll report.");
        }
    }

    // Generate Tax Report
    public static void generateTaxReport() throws ReportGenerationException {
        String query = "SELECT EmployeeID, TaxYear, TaxableIncome, TaxAmount FROM Tax";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("TAX REPORT:");
            System.out.println("====================================");
            while (rs.next()) {
                System.out.println("EmployeeID: " + rs.getInt("EmployeeID"));
                System.out.println("Tax Year: " + rs.getInt("TaxYear"));
                System.out.println("Taxable Income: $" + rs.getDouble("TaxableIncome"));
                System.out.println("Tax Amount: $" + rs.getDouble("TaxAmount"));
                System.out.println("------------------------------------");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error generating tax report", e);
            throw new ReportGenerationException("Failed to generate tax report.");
        }
    }

    // Generate Financial Record Report
    public static void generateFinancialRecordReport() throws ReportGenerationException {
        String query = "SELECT EmployeeID, RecordDate, Description, Amount, RecordType FROM FinancialRecord";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("FINANCIAL RECORD REPORT:");
            System.out.println("====================================");
            while (rs.next()) {
                System.out.println("EmployeeID: " + rs.getInt("EmployeeID"));
                System.out.println("Record Date: " + rs.getDate("RecordDate"));
                System.out.println("Description: " + rs.getString("Description"));
                System.out.println("Amount: $" + rs.getDouble("Amount"));
                System.out.println("Record Type: " + rs.getString("RecordType"));
                System.out.println("------------------------------------");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error generating financial record report", e);
            throw new ReportGenerationException("Failed to generate financial record report.");
        }
    }
}
