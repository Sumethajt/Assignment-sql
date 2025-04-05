package dao;

import entities.Payroll;
import java.time.LocalDate;
import java.util.List;

public interface IPayrollDAO {

    /**
     * Adds a new payroll record to the database.
     *
     * @param payroll The Payroll object containing salary details.
     */
    void addPayroll(Payroll payroll);

    /**
     * Retrieves a payroll record by its unique ID.
     *
     * @param payrollId The ID of the payroll record.
     * @return Payroll object if found, otherwise null.
     */
    Payroll getPayrollById(int payrollId);

    /**
     * Retrieves all payroll records for a specific employee.
     *
     * @param employeeId The ID of the employee.
     * @return List of payroll records for the given employee.
     */
    List<Payroll> getPayrollsForEmployee(int employeeId);

    /**
     * Generates a payroll record based on salary details.
     *
     * @param employeeId The ID of the employee.
     * @param startDate The start date of the pay period.
     * @param endDate The end date of the pay period.
     * @param basicSalary The basic salary of the employee.
     * @param overtimePay Additional pay for overtime work.
     * @param deductions Any deductions applicable to the employee.
     */
    void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate,
                         double basicSalary, double overtimePay, double deductions);

    /**
     * Retrieves payroll records within a specific pay period.
     *
     * @param startDate The start date of the period.
     * @param endDate The end date of the period.
     * @return List of payroll records within the given date range.
     */
    List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate);
    public void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate);
}

