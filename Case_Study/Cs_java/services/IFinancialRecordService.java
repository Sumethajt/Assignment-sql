package services;

import entities.FinancialRecord;
import exceptions.FinancialRecordException;
import exceptions.InvalidInputException;

import java.util.List;
import java.time.LocalDate;

public interface IFinancialRecordService {
    void addFinancialRecord(int employeeId, String description, double amount, String recordType)
            throws InvalidInputException;

    FinancialRecord getFinancialRecordById(int recordId)
            throws FinancialRecordException;

    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId)
            throws FinancialRecordException;

    List<FinancialRecord> getFinancialRecordsForDate(LocalDate recordDate);
}
