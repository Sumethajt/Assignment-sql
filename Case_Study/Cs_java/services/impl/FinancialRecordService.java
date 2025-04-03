package services.impl;

import entities.FinancialRecord;
import exceptions.FinancialRecordException;
import exceptions.InvalidInputException;
import services.IFinancialRecordService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinancialRecordService implements IFinancialRecordService {
    private List<FinancialRecord> records = new ArrayList<>();

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
        records.add(record);
    }

    @Override
    public FinancialRecord getFinancialRecordById(int recordId) throws FinancialRecordException {
        return records.stream()
                .filter(r -> r.getRecordID() == recordId)
                .findFirst()
                .orElseThrow(() -> new FinancialRecordException("Financial record with ID " + recordId + " not found."));
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) throws FinancialRecordException {
        List<FinancialRecord> result = new ArrayList<>();
        for (FinancialRecord record : records) {
            if (record.getEmployeeID() == employeeId) {
                result.add(record);
            }
        }
        if (result.isEmpty()) {
            throw new FinancialRecordException("No financial records found for Employee ID: " + employeeId);
        }
        return result;
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForDate(LocalDate recordDate) {
        List<FinancialRecord> result = new ArrayList<>();
        for (FinancialRecord record : records) {
            if (record.getRecordDate().isEqual(recordDate)) {
                result.add(record);
            }
        }
        return result;
    }
}
