package services.impl;

import dao.ITaxDAO;
import entities.Tax;
import exceptions.TaxCalculationException;
import exceptions.TaxNotFoundException;
import services.ITaxService;

import java.util.List;

public class TaxService implements ITaxService {
    private final ITaxDAO taxDAO;

    // Constructor Dependency Injection
    public TaxService(ITaxDAO taxDAO) {
        this.taxDAO = taxDAO;
    }

    @Override
    public void calculateTax(int employeeId, int taxYear) throws TaxCalculationException {
        try {
            // Assume taxable income is retrieved from another service
            double taxableIncome = getTaxableIncomeForEmployee(employeeId, taxYear);

            // Calculate tax using DAO logic
            double taxAmount = taxDAO.calculateTax(taxableIncome);

            // Create and store tax record
            Tax tax = new Tax(employeeId, taxYear, taxableIncome, taxAmount);
            taxDAO.addTaxRecord(tax);
        } catch (Exception e) {
            throw new TaxCalculationException("Error calculating tax: " + e.getMessage());
        }
    }

    @Override
    public Tax getTaxById(int taxId) throws TaxNotFoundException {
        Tax tax = taxDAO.getTaxById(taxId);
        if (tax == null) {
            throw new TaxNotFoundException("Tax record with ID " + taxId + " not found.");
        }
        return tax;
    }

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        return taxDAO.getTaxesForEmployee(employeeId);
    }

    @Override
    public List<Tax> getTaxesForYear(int taxYear) {
        return taxDAO.getTaxesForYear(taxYear);
    }

    private double getTaxableIncomeForEmployee(int employeeId, int taxYear) {
        // This method should integrate with payroll or financial services to get taxable income.
        // Placeholder logic:
        return 60000; // Default taxable income
    }
}
