package validation;

import exceptions.ValidationException;
import entities.Employee;
import entities.FinancialRecord;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidationService {

    // Validate Email Format with Exception Handling
    public static void validateEmail(String email) throws ValidationException {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {
            throw new ValidationException("Invalid email format: " + email);
        }
    }

    // Validate Phone Number (Allows Country Code) with Exception Handling
    public static void validatePhoneNumber(String phoneNumber) throws ValidationException {
        String phoneRegex = "^(\\+\\d{1,3})?\\d{10}$";
        if (!Pattern.matches(phoneRegex, phoneNumber)) {
            throw new ValidationException("Invalid phone number: " + phoneNumber);
        }
    }

    // Validate Non-Empty String with Exception Handling
    public static void validateString(String input) throws ValidationException {
        if (input == null || input.trim().isEmpty()) {
            throw new ValidationException("Invalid input: String cannot be empty or null.");
        }
    }

    // Validate Salary Amount (Should be Positive) with Exception Handling
    public static void validateAmount(double amount) throws ValidationException {
        if (amount < 0) {
            throw new ValidationException("Invalid amount: Salary cannot be negative.");
        }
    }

    // Validate Employee ID (Should be Positive) with Exception Handling
    public static void validateEmployeeID(int employeeId) throws ValidationException {
        if (employeeId <= 0) {
            throw new ValidationException("Invalid Employee ID: ID must be greater than zero.");
        }
    }

    // Validate Gender (Only "Male", "Female", "Other" allowed)
    public static void validateGender(String gender) throws ValidationException {
        if (!(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other"))) {
            throw new ValidationException("Invalid Gender: Accepted values are Male, Female, or Other.");
        }
    }

    // Validate Joining Date (Should not be in the future)
    public static void validateJoiningDate(LocalDate joiningDate) throws ValidationException {
        if (joiningDate == null || joiningDate.isAfter(LocalDate.now())) {
            throw new ValidationException("Invalid Joining Date: Date cannot be in the future.");
        }
    }

    public static void validateDateOfBirth(LocalDate dob) throws ValidationException {
        if (dob == null || dob.isAfter(LocalDate.now())) {
            throw new ValidationException("Invalid Date of Birth: Date must be in the past.");
        }
    }

    public static void validateEmployeeData(Employee employee) throws ValidationException {
        validateString(employee.getFirstName());
        validateString(employee.getLastName());
        validateEmail(employee.getEmail());
        validatePhoneNumber(employee.getPhoneNumber());
        validateDateOfBirth(employee.getDateOfBirth());
        validateGender(employee.getGender());
        validateString(employee.getAddress());
        validateString(employee.getPosition());
        validateJoiningDate(employee.getJoiningDate());

        // Validate Termination Date (If provided, it should not be before Joining Date)
        if (employee.getTerminationDate() != null && employee.getTerminationDate().isBefore(employee.getJoiningDate())) {
            throw new ValidationException("Termination date cannot be before the joining date.");
        }
    }

    // Validate Payroll ID (Must be Positive)
    public static void validatePayrollID(int payrollId) throws ValidationException {
        if (payrollId <= 0) {
            throw new ValidationException("Invalid Payroll ID: ID must be greater than zero.");
        }
    }

    // Validate Tax ID
    public static void validateTaxID(int taxId) throws ValidationException {
        if (taxId <= 0) {
            throw new ValidationException("Invalid Tax ID: ID must be greater than zero.");
        }
    }

    // Validate Tax Year
    public static void validateTaxYear(int taxYear) throws ValidationException {
        int currentYear = LocalDate.now().getYear();
        if (taxYear < 2000 || taxYear > currentYear) {
            throw new ValidationException("Invalid Tax Year: Must be between 2000 and " + currentYear);
        }
    }

    // Validate Financial Record
    public static void validateFinancialRecord(FinancialRecord record) throws ValidationException {
        validateString(record.getDescription());
        validateAmount(record.getAmount());
    }

    // Validate Record ID
    public static void validateRecordID(int recordId) throws ValidationException {
        if (recordId <= 0) {
            throw new ValidationException("Invalid Record ID: Must be greater than zero.");
        }
    }

    public static void validateDates(LocalDate startDate, LocalDate endDate) throws ValidationException {
        if (startDate.isAfter(endDate)) {
            throw new ValidationException("Invalid payroll period: Start date cannot be after end date.");
        }
    }

    public static void validateNetSalary(double basicSalary, double overtimePay, double deductions, double netSalary) throws ValidationException {
        double expectedNetSalary = basicSalary + overtimePay - deductions;
        if (Math.abs(expectedNetSalary - netSalary) > 0.01) {
            throw new ValidationException("Net salary calculation is incorrect. Expected: " + expectedNetSalary + ", Provided: " + netSalary);
        }
    }

    public static void validatePayrollData(Payroll payroll) throws ValidationException {
        if (payroll == null) {
            throw new ValidationException("Payroll data cannot be null.");
        }

        validateEmployeeID(payroll.getEmployeeID());
        validateDates(payroll.getPayPeriodStartDate(), payroll.getPayPeriodEndDate());
        validateAmount(payroll.getBasicSalary());
        validateAmount(payroll.getOvertimePay());
        validateAmount(payroll.getDeductions());
        validateAmount(payroll.getNetSalary());
        validateNetSalary(payroll.getBasicSalary(), payroll.getOvertimePay(), payroll.getDeductions(), payroll.getNetSalary());
    }

    public static void validateTaxableIncome(double taxableIncome) throws ValidationException {
        if (taxableIncome < 0) {
            throw new ValidationException("Taxable income cannot be negative.");
        }
    }

    public static void validateTax(Tax tax) throws ValidationException {
        if (tax == null) {
            throw new ValidationException("Tax object cannot be null.");
        }

        validateEmployeeID(tax.getEmployeeID());
        validateTaxYear(tax.getTaxYear());
        validateTaxableIncome(tax.getTaxableIncome());
    }





}
