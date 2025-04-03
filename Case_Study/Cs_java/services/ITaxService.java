package services;

import entities.Tax;
import exceptions.TaxCalculationException;
import exceptions.TaxNotFoundException;

import java.util.List;

public interface ITaxService {
    void calculateTax(int employeeId, int taxYear) throws TaxCalculationException;
    Tax getTaxById(int taxId) throws TaxNotFoundException;
    List<Tax> getTaxesForEmployee(int employeeId);
    List<Tax> getTaxesForYear(int taxYear);
}
