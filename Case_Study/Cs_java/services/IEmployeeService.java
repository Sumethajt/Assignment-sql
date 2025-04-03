package services;

import entities.Employee;
import exceptions.EmployeeNotFoundException;
import exceptions.InvalidInputException; // Import this exception

import java.util.List;

public interface IEmployeeService {
    Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException;
    List<Employee> getAllEmployees();
    void addEmployee(Employee employeeData) throws InvalidInputException; // Changed exception type
    void updateEmployee(Employee employeeData) throws EmployeeNotFoundException;
    void removeEmployee(int employeeId) throws EmployeeNotFoundException;
}

