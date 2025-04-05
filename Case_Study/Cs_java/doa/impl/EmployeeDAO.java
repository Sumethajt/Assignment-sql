package dao.impl;

import dao.IEmployeeDAO;
import database.DatabaseContext;
import entities.Employee;
import exceptions.EmployeeNotFoundException;
import validation.ValidationService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO {

    @Override
    public Employee getEmployeeById(int employeeId) {
        String query = "SELECT * FROM Employee WHERE EmployeeID = ?";
        ValidationService.validateEmployeeID(employeeId);
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractEmployeeFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching employee: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        String query = "SELECT * FROM Employee";
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                employees.add(extractEmployeeFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all employees: " + e.getMessage());
        }
        return employees;
    }

    @Override
    public void addEmployee(Employee employee) {
        String query = "INSERT INTO Employee (FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ValidationService.validateEmployeeData(employee);
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            setEmployeeStatement(stmt, employee);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    employee.setEmployeeID(generatedKeys.getInt(1)); // Set the generated EmployeeID
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding employee: " + e.getMessage());
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        String query = "UPDATE Employee SET FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ?, Email = ?, PhoneNumber = ?, Address = ?, Position = ?, JoiningDate = ?, TerminationDate = ? WHERE EmployeeID = ?";
        ValidationService.validateEmployeeData(employee);
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            setEmployeeStatement(stmt, employee);
            stmt.setInt(11, employee.getEmployeeID());

            int updatedRows = stmt.executeUpdate();
            if (updatedRows == 0) {
                throw new exceptions.EmployeeNotFoundException("No employee found with ID " + employee.getEmployeeID());
            }
        } catch (SQLException | EmployeeNotFoundException e) {
            throw new RuntimeException("Error updating employee: " + e.getMessage());
        }
    }

    @Override
    public void removeEmployee(int employeeId) {
        String query = "DELETE FROM Employee WHERE EmployeeID = ?";
        ValidationService.validateEmployeeID(employeeId);
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employeeId);
            int deletedRows = stmt.executeUpdate();
            if (deletedRows == 0) {
                throw new EmployeeNotFoundException("No employee found with ID " + employeeId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error removing employee: " + e.getMessage());
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("EmployeeID"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getDate("DateOfBirth").toLocalDate(),
                rs.getString("Gender"),
                rs.getString("Email"),
                rs.getString("PhoneNum"),
                rs.getString("Address"),
                rs.getString("Position"),
                rs.getDate("JoiningDate").toLocalDate(),
                rs.getDate("TerminationDate") != null ? rs.getDate("TerminationDate").toLocalDate() : null
        );
    }

    @Override
    public void deleteEmployee(int employeeId) {
        String query = "DELETE FROM Employee WHERE EmployeeID = ?";

        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employeeId);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Employee with ID " + employeeId + " has been removed.");
            } else {
                System.out.println("No employee found with ID " + employeeId);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
    }

    private void setEmployeeStatement(PreparedStatement stmt, Employee employee) throws SQLException {
        stmt.setString(1, employee.getFirstName());
        stmt.setString(2, employee.getLastName());
        stmt.setDate(3, Date.valueOf(employee.getDateOfBirth()));
        stmt.setString(4, employee.getGender());
        stmt.setString(5, employee.getEmail());
        stmt.setString(6, employee.getPhoneNumber());
        stmt.setString(7, employee.getAddress());
        stmt.setString(8, employee.getPosition());
        stmt.setDate(9, Date.valueOf(employee.getJoiningDate()));
        stmt.setDate(10, employee.getTerminationDate() != null ? Date.valueOf(employee.getTerminationDate()) : null);
    }
}
