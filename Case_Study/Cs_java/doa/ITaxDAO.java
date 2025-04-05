package dao;

import entities.Tax;
import java.util.List;

public interface ITaxDAO {
    void addTaxRecord(Tax tax);
    Tax getTaxById(int taxId);
    List<Tax> getTaxesForEmployee(int employeeId);
    List<Tax> getTaxesForYear(int taxYear);
    void updateTaxRecord(Tax tax);
    void deleteTaxRecord(int taxId);
    double calculateTax(double taxableIncome); // New Method
}
