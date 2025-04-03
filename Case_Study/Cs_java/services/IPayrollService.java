package services;

import entities.Payroll;
import exceptions.PayrollGenerationException;
import exceptions.EmployeeNotFoundException;

import java.util.List;
import java.time.LocalDate;

public interface IPayrollService {
    void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate) throws PayrollGenerationException, EmployeeNotFoundException;
    Payroll getPayrollById(int payrollId) throws PayrollGenerationException;
    List<Payroll> getPayrollsForEmployee(int employeeId) throws EmployeeNotFoundException;
    List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate);
}
