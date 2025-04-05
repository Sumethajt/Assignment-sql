package dao.impl;

import dao.ITaxDAO;
import database.DatabaseContext;
import entities.Tax;
import exceptions.ValidationException;
import validation.ValidationService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaxDAO implements ITaxDAO {

    @Override
    public void addTaxRecord(Tax tax) {
        String query = "INSERT INTO Tax (EmployeeID, TaxYear, TaxableIncome, TaxAmount) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ValidationService.validateTax(tax);

            stmt.setInt(1, tax.getEmployeeID());
            stmt.setInt(2, tax.getTaxYear());
            stmt.setDouble(3, tax.getTaxableIncome());

            // Calculate tax before inserting
            double taxAmount = calculateTax(tax.getTaxableIncome());
            stmt.setDouble(4, taxAmount);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    tax.setTaxID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException | ValidationException e) {
            System.err.println("Error adding tax record: " + e.getMessage());
        }
    }

    @Override
    public Tax getTaxById(int taxId) {
        String query = "SELECT * FROM Tax WHERE TaxID = ?";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ValidationService.validateTaxID(taxId);

            stmt.setInt(1, taxId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapTax(rs);
            }
        } catch (SQLException | ValidationException e) {
            System.err.println("Error retrieving tax record: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        return getTaxesByCondition("EmployeeID", employeeId);
    }

    @Override
    public List<Tax> getTaxesForYear(int taxYear) {
        return getTaxesByCondition("TaxYear", taxYear);
    }

    private List<Tax> getTaxesByCondition(String columnName, int value) {
        String query = "SELECT * FROM Tax WHERE " + columnName + " = ?";
        List<Tax> taxRecords = new ArrayList<>();

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, value);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                taxRecords.add(mapTax(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving tax records: " + e.getMessage());
        }
        return taxRecords;
    }

    @Override
    public void updateTaxRecord(Tax tax) {
        String query = "UPDATE Tax SET TaxableIncome = ?, TaxAmount = ? WHERE TaxID = ?";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ValidationService.validateTax(tax);

            stmt.setDouble(1, tax.getTaxableIncome());
            stmt.setDouble(2, calculateTax(tax.getTaxableIncome()));
            stmt.setInt(3, tax.getTaxID());

            stmt.executeUpdate();
        } catch (SQLException | ValidationException e) {
            System.err.println("Error updating tax record: " + e.getMessage());
        }
    }

    @Override
    public void deleteTaxRecord(int taxId) {
        String query = "DELETE FROM Tax WHERE TaxID = ?";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ValidationService.validateTaxID(taxId);

            stmt.setInt(1, taxId);
            stmt.executeUpdate();
        } catch (SQLException | ValidationException e) {
            System.err.println("Error deleting tax record: " + e.getMessage());
        }
    }

    @Override
    public double calculateTax(double taxableIncome) {
        if (taxableIncome <= 25000) {
            return 0;
        } else if (taxableIncome <= 50000) {
            return (taxableIncome - 25000) * 0.10;
        } else if (taxableIncome <= 100000) {
            return (25000 * 0.10) + ((taxableIncome - 50000) * 0.20);
        } else {
            return (25000 * 0.10) + (50000 * 0.20) + ((taxableIncome - 100000) * 0.30);
        }
    }

    private Tax mapTax(ResultSet rs) throws SQLException {
        return new Tax(
                rs.getInt("TaxID"),
                rs.getInt("EmployeeID"),
                rs.getInt("TaxYear"),
                rs.getDouble("TaxableIncome"),
                rs.getDouble("TaxAmount")
        );
    }
}
