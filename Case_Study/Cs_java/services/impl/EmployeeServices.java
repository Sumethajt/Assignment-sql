package services.impl;

import dao.IEmployeeDAO;
import dao.ITaxDAO;
import entities.Employee;
import exceptions.EmployeeNotFoundException;
import exceptions.InvalidInputException;
import services.IEmployeeService;
import validation.ValidationService;

import java.util.List;

public class EmployeeService implements IEmployeeService {
    private IEmployeeDAO employeeDAO;

    public EmployeeService(IEmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException {
        ValidationService.validateEmployeeID(employeeId);
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public void addEmployee(Employee employee) throws InvalidInputException {
        ValidationService.validateEmployeeData(employee);
        employeeDAO.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        ValidationService.validateEmployeeData(employee);
        if (employeeDAO.getEmployeeById(employee.getEmployeeID()) == null) {
            throw new EmployeeNotFoundException("Employee with ID " + employee.getEmployeeID() + " not found.");
        }
        employeeDAO.updateEmployee(employee);
    }

    @Override
    public void removeEmployee(int employeeId) throws EmployeeNotFoundException {
        ValidationService.validateEmployeeID(employeeId);
        if (employeeDAO.getEmployeeById(employeeId) == null) {
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
        }
        employeeDAO.removeEmployee(employeeId);
    }

}
