package dao;

import entities.Employee;
import java.util.List;

public interface IEmployeeDAO {
    Employee getEmployeeById(int employeeId);  // Fetch a single employee by ID
    List<Employee> getAllEmployees();  // Fetch all employees
    void addEmployee(Employee employee);  // Add a new employee
    void updateEmployee(Employee employee);  // Update existing employee details
    void removeEmployee(int employeeId);
    void deleteEmployee(int employeeId);// Delete an employee by ID
}

