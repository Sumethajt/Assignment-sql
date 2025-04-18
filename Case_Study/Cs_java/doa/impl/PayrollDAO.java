package dao.impl;

import dao.IPayrollDAO;
import database.DatabaseContext;
import entities.Payroll;
import exceptions.DatabaseException;
import validation.ValidationService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAO implements IPayrollDAO {

    @Override
    public void addPayroll(Payroll payroll) {
        String query = "INSERT INTO Payroll (EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) VALUES (?, ?, ?, ?, ?, ?, ?)";

        ValidationService.validatePayrollData(payroll);
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, payroll.getEmployeeID());
            stmt.setDate(2, Date.valueOf(payroll.getPayPeriodStartDate()));
            stmt.setDate(3, Date.valueOf(payroll.getPayPeriodEndDate()));
            stmt.setDouble(4, payroll.getBasicSalary());
            stmt.setDouble(5, payroll.getOvertimePay());
            stmt.setDouble(6, payroll.getDeductions());
            stmt.setDouble(7, payroll.getNetSalary());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        payroll.setPayrollID(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding payroll", e.getMessage());
            throw new DatabaseException("Unable to add payroll: " + e.getMessage());
        }
    }

    @Override
    public Payroll getPayrollById(int payrollId) {
        String query = "SELECT * FROM Payroll WHERE PayrollID = ?";
        ValidationService.validatePayrollID(payrollId);

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, payrollId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractPayrollFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching payroll by ID" + e.getMessage());
            throw new DatabaseException("Unable to retrieve payroll: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        String query = "SELECT * FROM Payroll WHERE EmployeeID = ?";
        ValidationService.validateEmployeeID(employeeId);
        List<Payroll> payrolls = new ArrayList<>();

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    payrolls.add(extractPayrollFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println( "Error fetching payrolls for employee" + e.getMessage());
            throw new DatabaseException("Unable to retrieve payroll records: " + e.getMessage());
        }
        return payrolls;
    }

    @Override
    public List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate) {
        String query = "SELECT * FROM Payroll WHERE PayPeriodStartDate >= ? AND PayPeriodEndDate <= ?";
        List<Payroll> payrolls = new ArrayList<>();

        if (startDate.isAfter(endDate)) {
            throw new DatabaseException("Start date cannot be after end date.");
        }

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    payrolls.add(extractPayrollFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching payrolls for period" + e.getMessage());
            throw new DatabaseException("Unable to retrieve payroll records: " + e.getMessage());
        }
        return payrolls;
    }

    @Override
    public void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate, double basicSalary, double overtimePay, double deductions) {
        String query = "INSERT INTO Payroll (EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) VALUES (?, ?, ?, ?, ?, ?, ?)";

        double netSalary = basicSalary + overtimePay - deductions;

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, employeeId);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            stmt.setDouble(4, basicSalary);
            stmt.setDouble(5, overtimePay);
            stmt.setDouble(6, deductions);
            stmt.setDouble(7, netSalary);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("Payroll generated with ID: " + generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error generating payroll: " + e.getMessage(), e);
        }
    }

    private Payroll extractPayrollFromResultSet(ResultSet rs) throws SQLException {
        Payroll payroll = new Payroll(
                rs.getInt("EmployeeID"),
                rs.getDate("PayPeriodStartDate").toLocalDate(),
                rs.getDate("PayPeriodEndDate").toLocalDate(),
                rs.getDouble("BasicSalary"),
                rs.getDouble("OvertimePay"),
                rs.getDouble("Deductions"),
                rs.getDouble("NetSalary")
        );
        payroll.setPayrollID(rs.getInt("PayrollID"));
        return payroll;
    }

    public double calculateGrossSalaryByEmployeeId(int employeeId) throws SQLException {
        String query = "SELECT BasicSalary, OvertimePay FROM Payroll WHERE EmployeeID = ?";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double basicSalary = rs.getDouble("BasicSalary");
                    double overtimePay = rs.getDouble("OvertimePay");
                    return basicSalary + overtimePay;
                } else {
                    throw new SQLException("No payroll record found for Employee ID " + employeeId);
                }
            }
        }
    }

    public double calculateNetSalaryByEmployeeId(int employeeId) throws SQLException {
        double grossSalary = calculateGrossSalaryByEmployeeId(employeeId); // existing method
        double deduction = 0.0;

        String query = "SELECT Deduction FROM Payroll WHERE EmployeeID = ? ORDER BY PayPeriodEndDate DESC LIMIT 1";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employeeId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    deduction = rs.getDouble("Deduction");
                }
            }
        }

        return grossSalary - deduction;
    }
}
