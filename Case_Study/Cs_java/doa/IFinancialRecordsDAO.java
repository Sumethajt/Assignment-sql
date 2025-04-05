package dao;

import entities.FinancialRecord;
import exceptions.ValidationException;

import java.time.LocalDate;
import java.util.List;

public interface IFinancialRecordsDAO {
    void addFinancialRecord(FinancialRecord record);
    FinancialRecord getFinancialRecordById(int recordId);
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
    List<FinancialRecord> getFinancialRecordsForDateRange(LocalDate startDate, LocalDate endDate);
    void updateFinancialRecord(FinancialRecord record) throws ValidationException;
    void deleteFinancialRecord(int recordId);
    double calculateTotalRevenue();
    double calculateTotalExpenses();
    double calculateNetBalance();
}

