import dao.impl.PayrollDAO;
import entities.Payroll;
import exceptions.ValidationException;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PayrollTest {

    private PayrollDAO payrollDAO;

    @BeforeAll
    void setup() {
        payrollDAO = new PayrollDAO();
        System.out.println("PayrollDAO initialized.");
    }

    @AfterAll
    void tearDown() {
        payrollDAO = null;
        System.out.println("PayrollDAO resources cleared.");
    }

    @Test
    @DisplayName("Calculate Gross Salary for an Employee")
    public void testCalculateGrossSalaryForEmployee() throws Exception {
        int employeeId = 1001;
        double basicSalary = 5000.0;
        double overtimePay = 200.0;

        double expectedGrossSalary = basicSalary + overtimePay;
        double actualGrossSalary = payrollDAO.calculateGrossSalaryByEmployeeId(employeeId);

        assertEquals(expectedGrossSalary, actualGrossSalary, 0.01, "Mismatch in gross salary calculation.");
        System.out.println("ActualNet Salary: $" + actualGrossSalary + " | Expected Net Salary: $" + expectedGrossSalary);
    }

    @Test
    @DisplayName("Calculate Net Salary After Deductions")
    public void testCalculateNetSalaryAfterDeductions() throws Exception {
        int employeeId = 1001;
        double basicSalary = 5000.0;
        double overtimePay = 300.0;
        double deductions = 400.0;


        double expectedNetSalary = (basicSalary + overtimePay) - deductions;
        double actualNetSalary = payrollDAO.calculateNetSalaryByEmployeeId(employeeId);

        assertEquals(expectedNetSalary, actualNetSalary, 0.01, "Mismatch in net salary calculation.");
        System.out.println("ActualNet Salary: $" + actualNetSalary + " | Expected Net Salary: $" + expectedNetSalary);
    }

    @Test
    @DisplayName("Process Payroll for Multiple Employees")
    public void testProcessPayrollForMultipleEmployees() {
        int[] employeeIds = {1001, 1002, 1003};
        LocalDate startDate = LocalDate.of(2024, 4, 1);
        LocalDate endDate = LocalDate.of(2024, 4, 30);
        double basicSalary = 5000;
        double overtimePay = 200;
        double deduction = 300;

        for (int empId : employeeIds) {
            // Process payroll
            double netSalary = payrollDAO.generatePayroll(empId, startDate, endDate, basicSalary, overtimePay, deduction);
            assertEquals(basicSalary + overtimePay - deduction, netSalary, 0.01, "Incorrect net salary calculated.");

            // Verify the payroll was stored
            List<Payroll> payrolls = payrollDAO.getPayrollsForEmployee(empId);
            assertFalse(payrolls.isEmpty(), "No payroll record found for Employee ID: " + empId);

            Payroll latest = payrolls.get(payrolls.size() - 1);
            assertEquals(startDate, latest.getPayPeriodStartDate()," Start Date doesn't match");
            assertEquals(endDate, latest.getPayPeriodEndDate()," End Date doesn't match");
            assertEquals(netSalary, latest.getNetSalary(), 0.01," Net salary doesn't match");
        }

        System.out.println("Payroll processing verified for multiple employees.");
    }


    @Test
    @DisplayName("Verify Error Handling for Invalid Employee Data")
    public void testErrorHandlingForInvalidEmployeeData() {
        int invalidEmployeeId = -1;
        double invalidSalary = -1000.0;

        Exception exception = assertThrows(ValidationException.class, () -> {
            payrollDAO.generatePayroll(
                    invalidEmployeeId,
                    LocalDate.now(),
                    LocalDate.now().plusDays(14),
                    invalidSalary,
                    0,
                    0
            );
        });

        String expectedMessage = "Invalid employee ID or salary";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains("Invalid") || actualMessage.contains("employee"), "Error message does not match expected.");
    }
}
