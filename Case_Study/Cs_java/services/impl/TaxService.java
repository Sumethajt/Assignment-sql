package services.impl;

import entities.Tax;
import exceptions.TaxCalculationException;
import exceptions.TaxNotFoundException;
import services.ITaxService;

import java.util.ArrayList;
import java.util.List;

public class TaxService implements ITaxService {
    private List<Tax> taxes = new ArrayList<>();

    @Override
    public void calculateTax(int employeeId, int taxYear) throws TaxCalculationException {
        Tax tax = new Tax();
        tax.setEmployeeID(employeeId);
        tax.setTaxYear(taxYear);
        // Compute taxable income and tax amount
        taxes.add(tax);
    }

    @Override
    public Tax getTaxById(int taxId) throws TaxNotFoundException {
        return taxes.stream()
                .filter(t -> t.getTaxID() == taxId)
                .findFirst()
                .orElseThrow(() -> new TaxNotFoundException("Tax record with ID " + taxId + " not found."));
    }

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId)  {
        List<Tax> result = new ArrayList<>();
        for (Tax tax : taxes) {
            if (tax.getEmployeeID() == employeeId) {
                result.add(tax);
            }
        }
        return result;
    }

    @Override
    public List<Tax> getTaxesForYear(int taxYear) {
        List<Tax> result = new ArrayList<>();
        for (Tax tax : taxes) {
            if (tax.getTaxYear() == taxYear) {
                result.add(tax);
            }
        }
        return result;
    }
}
