package services.impl;

import entities.Payroll;
import exceptions.PayrollGenerationException;
import services.IPayrollService;
import validation.ValidationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayrollService implements IPayrollService {
    private Map<Integer, Payroll> payrolls = new HashMap<>();

    @Override
    public Payroll getPayrollById(int payrollId) throws PayrollGenerationException {
        ValidationService.validatePayrollID(payrollId);
        if (!payrolls.containsKey(payrollId)) {
            throw new PayrollGenerationException("Payroll record not found.");
        }
        return payrolls.get(payrollId);
    }

    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        ValidationService.validateEmployeeID(employeeId);
        List<Payroll> result = new ArrayList<>();
        for (Payroll payroll : payrolls.values()) {
            if (payroll.getEmployeeID() == employeeId) {
                result.add(payroll);
            }
        }
        return result;
    }

    @Override
    public List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate) {
        List<Payroll> result = new ArrayList<>();
        for (Payroll payroll : payrolls.values()) {
            if (payroll.getPayPeriodStartDate().isAfter(startDate) && payroll.getPayPeriodEndDate().isBefore(endDate)) {
                result.add(payroll);
            }
        }
        return result;
    }

    @Override
    public void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate) throws PayrollGenerationException {
        if (startDate.isAfter(endDate)) {
            throw new PayrollGenerationException("Start date cannot be after end date.");
        }
        // Dummy payroll calculation logic
        Payroll payroll = new Payroll(payrolls.size() + 1, employeeId, startDate, endDate, 50000, 5000, 2000, 53000);
        payrolls.put(payroll.getPayrollID(), payroll);
    }
}