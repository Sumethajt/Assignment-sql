package services.impl;

import entities.Employee;
import exceptions.EmployeeNotFoundException;
import exceptions.InvalidInputException;
import services.IEmployeeService;
import validation.ValidationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService implements IEmployeeService {
    private Map<Integer, Employee> employees = new HashMap<>();

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException { // Fixed method name
        ValidationService.validateEmployeeID(employeeId);
        if (!employees.containsKey(employeeId)) {
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
        }
        return employees.get(employeeId);
    }

    @Override
    public List<Employee> getAllEmployees() { // Fixed method name
        return new ArrayList<>(employees.values());
    }

    @Override
    public void addEmployee(Employee employee) throws InvalidInputException { // Fixed method name
        ValidationService.validateEmployeeData(employee);
        if (employee == null || employee.getFirstName().isEmpty()) {
            throw new InvalidInputException("Invalid employee data provided.");
        }
        employees.put(employee.getEmployeeID(), employee);
    }

    @Override
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException { // Fixed method name
        ValidationService.validateEmployeeData(employee);
        if (!employees.containsKey(employee.getEmployeeID())) {
            throw new EmployeeNotFoundException("Employee not found for update.");
        }
        employees.put(employee.getEmployeeID(), employee);
    }

    @Override
    public void removeEmployee(int employeeId) throws EmployeeNotFoundException { // Fixed method name
        ValidationService.validateEmployeeID(employeeId);
        if (!employees.containsKey(employeeId)) {
            throw new EmployeeNotFoundException("Employee not found for removal.");
        }
        employees.remove(employeeId);
    }
}
