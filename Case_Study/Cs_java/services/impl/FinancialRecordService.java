package services.impl;

import dao.IFinancialRecordsDAO;
import dao.impl.FinancialRecordsDAO;
import entities.FinancialRecord;
import exceptions.FinancialRecordException;
import exceptions.InvalidInputException;
import exceptions.ValidationException;
import services.IFinancialRecordService;

import java.time.LocalDate;
import java.util.List;

public class FinancialRecordService implements IFinancialRecordService {
    private final IFinancialRecordsDAO financialRecordsDAO;

    // Constructor - Dependency Injection
    public FinancialRecordService() {
        this.financialRecordsDAO = new FinancialRecordsDAO(); // Using DAO implementation
    }

    @Override
    public void addFinancialRecord(int employeeId, String description, double amount, String recordType)
            throws InvalidInputException {
        if (employeeId <= 0 || amount < 0 || description == null || description.isEmpty() || recordType == null || recordType.isEmpty()) {
            throw new InvalidInputException("Invalid financial record data provided.");
        }

        FinancialRecord record = new FinancialRecord();
        record.setEmployeeID(employeeId);
        record.setDescription(description);
        record.setAmount(amount);
        record.setRecordType(recordType);
        record.setRecordDate(LocalDate.now());

        try {
            financialRecordsDAO.addFinancialRecord(record);
        } catch (ValidationException e) {
            throw new InvalidInputException("Failed to add financial record: " + e.getMessage());
        }
    }

    @Override
    public FinancialRecord getFinancialRecordById(int recordId) throws FinancialRecordException {
        try {
            return financialRecordsDAO.getFinancialRecordById(recordId);
        } catch (ValidationException e) {
            throw new FinancialRecordException("Error retrieving record: " + e.getMessage());
        }
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) throws FinancialRecordException {
        try {
            return financialRecordsDAO.getFinancialRecordsForEmployee(employeeId);
        } catch (ValidationException e) {
            throw new FinancialRecordException("Error retrieving records: " + e.getMessage());
        }
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForDate(LocalDate recordDate) {
        try {
            return financialRecordsDAO.getFinancialRecordsForDateRange(recordDate, recordDate);
        } catch (ValidationException e) {
            System.err.println("Error retrieving records for date: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void updateFinancialRecord(FinancialRecord record) throws InvalidInputException {
        if (record == null || record.getRecordID() <= 0 || record.getAmount() < 0) {
            throw new InvalidInputException("Invalid financial record data for update.");
        }

        try {
            financialRecordsDAO.updateFinancialRecord(record);
        } catch (ValidationException e) {
            throw new InvalidInputException("Failed to update financial record: " + e.getMessage());
        }
    }

    @Override
    public void deleteFinancialRecord(int recordId) throws FinancialRecordException {
        try {
            financialRecordsDAO.deleteFinancialRecord(recordId);
        } catch (ValidationException e) {
            throw new FinancialRecordException("Failed to delete record: " + e.getMessage());
        }
    }

    @Override
    public double calculateTotalRevenue() {
        return financialRecordsDAO.calculateTotalRevenue();
    }

    @Override
    public double calculateTotalExpenses() {
        return financialRecordsDAO.calculateTotalExpenses();
    }

    @Override
    public double calculateNetBalance() {
        return financialRecordsDAO.calculateNetBalance();
    }
}
