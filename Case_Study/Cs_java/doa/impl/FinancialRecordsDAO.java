package dao.impl;

import dao.IFinancialRecordsDAO;
import database.DatabaseContext;
import entities.FinancialRecord;
import exceptions.ValidationException;
import validation.ValidationService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinancialRecordsDAO implements IFinancialRecordsDAO {

    @Override
    public void addFinancialRecord(FinancialRecord record) throws ValidationException {
        String query = "INSERT INTO FinancialRecords (EmployeeID, RecordDate, Description, Amount, RecordType) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ValidationService.validateFinancialRecord(record);

            stmt.setInt(1, record.getEmployeeID());
            stmt.setDate(2, Date.valueOf(record.getRecordDate()));
            stmt.setString(3, record.getDescription());
            stmt.setDouble(4, record.getAmount());
            stmt.setString(5, record.getRecordType());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    record.setRecordID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new ValidationException("Error adding financial record: " + e.getMessage());
        }
    }

    @Override
    public FinancialRecord getFinancialRecordById(int recordId) throws ValidationException {
        String query = "SELECT * FROM FinancialRecords WHERE RecordID = ?";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ValidationService.validateRecordID(recordId);

            stmt.setInt(1, recordId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapToFinancialRecord(rs);
            }
        } catch (SQLException e) {
            throw new ValidationException("Error retrieving financial record: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) throws ValidationException {
        String query = "SELECT * FROM FinancialRecords WHERE EmployeeID = ?";
        List<FinancialRecord> records = new ArrayList<>();

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ValidationService.validateEmployeeID(employeeId);

            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                records.add(mapToFinancialRecord(rs));
            }
        } catch (SQLException e) {
            throw new ValidationException("Error retrieving records for employee: " + e.getMessage());
        }
        return records;
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForDateRange(LocalDate startDate, LocalDate endDate) throws ValidationException {
        String query = "SELECT * FROM FinancialRecords WHERE RecordDate BETWEEN ? AND ?";
        List<FinancialRecord> records = new ArrayList<>();

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                records.add(mapToFinancialRecord(rs));
            }
        } catch (SQLException e) {
            throw new ValidationException("Error retrieving financial records for date range: " + e.getMessage());
        }
        return records;
    }

    @Override
    public void deleteFinancialRecord(int recordId) throws ValidationException {
        String query = "DELETE FROM FinancialRecords WHERE RecordID = ?";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ValidationService.validateRecordID(recordId);

            stmt.setInt(1, recordId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ValidationException("Error deleting financial record: " + e.getMessage());
        }
    }

    private FinancialRecord mapToFinancialRecord(ResultSet rs) throws SQLException {
        return new FinancialRecord(
                rs.getInt("RecordID"),
                rs.getInt("EmployeeID"),
                rs.getDate("RecordDate").toLocalDate(),
                rs.getString("Description"),
                rs.getDouble("Amount"),
                rs.getString("RecordType")
        );
    }

    @Override
    public void updateFinancialRecord(FinancialRecord record) throws ValidationException {
        String query = "UPDATE FinancialRecords SET RecordDate = ?, Description = ?, Amount = ?, RecordType = ? WHERE RecordID = ?";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Validate the record before updating
            ValidationService.validateFinancialRecord(record);
            ValidationService.validateRecordID(record.getRecordID());

            stmt.setDate(1, Date.valueOf(record.getRecordDate()));
            stmt.setString(2, record.getDescription());
            stmt.setDouble(3, record.getAmount());
            stmt.setString(4, record.getRecordType());
            stmt.setInt(5, record.getRecordID());

            int updatedRows = stmt.executeUpdate();
            if (updatedRows == 0) {
                throw new ValidationException("No financial record found with ID: " + record.getRecordID());
            }
        } catch (SQLException e) {
            throw new ValidationException("Error updating financial record: " + e.getMessage());
        }
    }

    @Override
    public double calculateTotalRevenue() {
        return calculateTotal("REVENUE");
    }

    @Override
    public double calculateTotalExpenses() {
        return calculateTotal("EXPENSE");
    }

    @Override
    public double calculateNetBalance() {
        return calculateTotalRevenue() - calculateTotalExpenses();
    }

    private double calculateTotal(String recordType) {
        String query = "SELECT SUM(Amount) FROM FinancialRecords WHERE RecordType = ?";
        double total = 0.0;

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, recordType);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total " + recordType + ": " + e.getMessage());
        }
        return total;
    }
}
