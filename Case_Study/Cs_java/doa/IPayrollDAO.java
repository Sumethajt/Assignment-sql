package dao;

import entities.Payroll;
import java.time.LocalDate;
import java.util.List;

public interface IPayrollDAO {


    void addPayroll(Payroll payroll);
    Payroll getPayrollById(int payrollId);
    List<Payroll> getPayrollsForEmployee(int employeeId);
    void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate,
                         double basicSalary, double overtimePay, double deductions);
    List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate);
}

