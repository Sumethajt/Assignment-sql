package services.impl;

import dao.IPayrollDAO;
import entities.Payroll;
import exceptions.DatabaseException;
import exceptions.PayrollGenerationException;
import services.IPayrollService;
import validation.ValidationService;
import exceptions.ValidationException;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayrollService implements IPayrollService {
    private static final Logger LOGGER = Logger.getLogger(PayrollService.class.getName());
    private final IPayrollDAO payrollDAO;

    public PayrollService(IPayrollDAO payrollDAO) {
        this.payrollDAO = payrollDAO;
    }

    @Override
    public void addPayroll(Payroll payroll) throws PayrollGenerationException {
        try {
            // Validate Payroll Data Before Processing
            ValidationService.validatePayrollData(payroll);

            payrollDAO.addPayroll(payroll);
            LOGGER.info("Payroll added successfully for Employee ID: " + payroll.getEmployeeID());
        } catch (ValidationException e) {
            LOGGER.log(Level.WARNING, "Validation error while adding payroll", e);
            throw new PayrollGenerationException("Validation error: " + e.getMessage());
        } catch (DatabaseException e) {
            LOGGER.log(Level.SEVERE, "Database error while adding payroll", e);
            throw new PayrollGenerationException("Database error while adding payroll: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error while adding payroll", e);
            throw new PayrollGenerationException("Unexpected error: " + e.getMessage());
        }
    }

    @Override
    public Payroll getPayrollById(int payrollId) throws PayrollGenerationException {
        try {
            ValidationService.validatePayrollID(payrollId);
            Payroll payroll = payrollDAO.getPayrollById(payrollId);
            if (payroll == null) {
                throw new PayrollGenerationException("Payroll not found for ID: " + payrollId);
            }
            return payroll;
        } catch (ValidationException e) {
            throw new PayrollGenerationException("Validation error: " + e.getMessage());
        } catch (DatabaseException e) {
            throw new PayrollGenerationException("Database error while fetching payroll: " + e.getMessage());
        }
    }

    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        ValidationService.validateEmployeeID(employeeId);
        return payrollDAO.getPayrollsForEmployee(employeeId);
    }

    @Override
    public List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate) throws PayrollGenerationException {
        if (startDate.isAfter(endDate)) {
            throw new PayrollGenerationException("Start date cannot be after end date.");
        }
        return payrollDAO.getPayrollsForPeriod(startDate, endDate);
    }

    @Override
    public void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate) throws PayrollGenerationException {
        try {
            // Validate Inputs
            ValidationService.validateEmployeeID(employeeId);
            ValidationService.validateDates(startDate, endDate);

            // Fetch Employee Salary Details (Assume basic salary, overtime pay, deductions from DB or calculation)
            double basicSalary = 50000;  // Example default value (fetch from Employee DB in real case)
            double overtimePay = 5000;   // Example default value
            double deductions = 3000;    // Example default value

            double netSalary = basicSalary + overtimePay - deductions;

            // Validate Net Salary Calculation
            ValidationService.validateNetSalary(basicSalary, overtimePay, deductions, netSalary);

            // Generate Payroll
            payrollDAO.generatePayroll(employeeId, startDate, endDate, basicSalary, overtimePay, deductions);
            LOGGER.info("Payroll successfully generated for Employee ID: " + employeeId);

        } catch (ValidationException e) {
            LOGGER.log(Level.WARNING, "Validation error during payroll generation", e);
            throw new PayrollGenerationException("Validation error: " + e.getMessage());
        } catch (DatabaseException e) {
            LOGGER.log(Level.SEVERE, "Database error during payroll generation", e);
            throw new PayrollGenerationException("Database error: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error during payroll generation", e);
            throw new PayrollGenerationException("Unexpected error: " + e.getMessage());
        }
    }
}
