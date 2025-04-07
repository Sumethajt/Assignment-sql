import dao.IEmployeeDAO;
import dao.ITaxDAO;
import dao.impl.EmployeeDAO;
import dao.impl.TaxDAO;
import entities.Employee;
import entities.Tax;
import exceptions.EmployeeNotFoundException;
import report.ReportGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IEmployeeDAO employeeDAO = new EmployeeDAO();
        ITaxDAO taxDAO = new TaxDAO();
        ReportGenerator reportGenerator = new ReportGenerator();

        while (true) {
            System.out.println("\n=== Tax & Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Add Tax Record");
            System.out.println("4. Get Tax by ID");
            System.out.println("5. Get Taxes for Employee");
            System.out.println("6. Get Taxes for Year");
            System.out.println("7. Update Tax Record");
            System.out.println("8. Delete Tax Record");
            System.out.println("9. Remove Employee");
            System.out.println("10. Generate Reports");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(scanner, employeeDAO);
                    break;
                case 2:
                    viewAllEmployees(employeeDAO);
                    break;
                case 3:
                    addTaxRecord(scanner, taxDAO);
                    break;
                case 4:
                    getTaxById(scanner, taxDAO);
                    break;
                case 5:
                    getTaxesForEmployee(scanner, taxDAO);
                    break;
                case 6:
                    getTaxesForYear(scanner, taxDAO);
                    break;
                case 7:
                    updateTaxRecord(scanner, taxDAO);
                    break;
                case 8:
                    deleteTaxRecord(scanner, taxDAO);
                    break;
                 case 9:
                    removeEmployee(scanner,employeeDAO);
                    break;
                case 10:
                    reportGenerate(scanner, reportGenerator);
                    break;
                case 11:
                    System.out.println("Exiting the system...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addEmployee(Scanner scanner, IEmployeeDAO employeeDAO) {
        scanner.nextLine(); // Consume leftover newline

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter Gender (Male/Female/Other): ");
        String gender = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Position: ");
        String position = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume leftover newline

        System.out.print("Enter Joining Date (YYYY-MM-DD): ");
        LocalDate joiningDate = LocalDate.parse(scanner.nextLine());

        LocalDate terminationDate = null;
        // Create an Employee object
        Employee employee = new Employee(firstName, lastName, dateOfBirth, gender, email, phoneNumber, address, position, joiningDate, terminationDate);

        // Add employee to the database
        employeeDAO.addEmployee(employee);
        System.out.println("Employee added successfully with ID: " + employee.getEmployeeID());
    }


    private static void viewAllEmployees(IEmployeeDAO employeeDAO) {
        List<Employee> employees = employeeDAO.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            employees.forEach(System.out::println);
        }
    }

    private static void addTaxRecord(Scanner scanner, ITaxDAO taxDAO) {
        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        System.out.print("Enter Tax Year: ");
        int taxYear = scanner.nextInt();
        System.out.print("Enter Taxable Income: ");
        double taxableIncome = scanner.nextDouble();

        Tax tax = new Tax(employeeId, taxYear, taxableIncome, 0);
        taxDAO.addTaxRecord(tax);
        System.out.println("Tax record added successfully with ID: " + tax.getTaxID());
    }

    private static void getTaxById(Scanner scanner, ITaxDAO taxDAO) {
        System.out.print("Enter Tax ID: ");
        int taxId = scanner.nextInt();
        Tax tax = taxDAO.getTaxById(taxId);
        System.out.println(tax != null ? tax : "Tax record not found.");
    }

    private static void getTaxesForEmployee(Scanner scanner, ITaxDAO taxDAO) {
        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        taxDAO.getTaxesForEmployee(employeeId).forEach(System.out::println);
    }

    private static void getTaxesForYear(Scanner scanner, ITaxDAO taxDAO) {
        System.out.print("Enter Tax Year: ");
        int taxYear = scanner.nextInt();
        taxDAO.getTaxesForYear(taxYear).forEach(System.out::println);
    }

    private static void updateTaxRecord(Scanner scanner, ITaxDAO taxDAO) {
        System.out.print("Enter Tax ID: ");
        int taxId = scanner.nextInt();
        System.out.print("Enter new Taxable Income: ");
        double newTaxableIncome = scanner.nextDouble();
        Tax tax = taxDAO.getTaxById(taxId);
        if (tax != null) {
            tax.setTaxableIncome(newTaxableIncome);
            taxDAO.updateTaxRecord(tax);
            System.out.println("Tax updated.");
        }
    }

    private static void deleteTaxRecord(Scanner scanner, ITaxDAO taxDAO) {
        System.out.print("Enter Tax ID to delete: ");
        int taxId = scanner.nextInt();
        taxDAO.deleteTaxRecord(taxId);
        System.out.println("Tax record deleted.");
    }
    
    private static void removeEmployee(Scanner scanner, IEmployeeDAO employeeDAO){
        System.out.println("Enter EmployeeID to remove Employee: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();
        try {
            employeeDAO.removeEmployee(employeeId);
            System.out.println("Employee removed successfully.");
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void reportGenerate(Scanner scanner,ReportGenerator reportGenerator) {
        try {
            System.out.println("Enter the EmployeeID to generate report:");
            int EmployeeID = scanner.nextInt();
            ReportGenerator.generatePayrollReport(EmployeeID);
            ReportGenerator.generateTaxReport(EmployeeID);
            ReportGenerator.generateFinancialRecordReport(EmployeeID);
        } catch (ReportGenerationException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
}

