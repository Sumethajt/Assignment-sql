package Dao;

import Entities.Employee;
import Services.ICourierAdminService;

public class CourierAdminServiceCollectionImpl extends CourierUserServiceCollectionImpl implements ICourierAdminService {

    // Constructor
    public CourierAdminServiceCollectionImpl() {
        super();  // Calls parent class constructor
    }

    // ✅ Implement addCourierStaff method
    @Override
    public boolean addCourierStaff(Employee employee) {
        companyObj.getEmployeeDetails().add(employee);
        System.out.println("✅ Courier staff added successfully: " + employee.getEmployeeID());
        return companyObj.getEmployeeDetails().add(employee);
    }

    // ✅ Implement removeCourierStaff method
    @Override
    public boolean removeCourierStaff(int employeeId) {
        boolean removed = companyObj.getEmployeeDetails().removeIf(e -> e.getEmployeeID() == employeeId);
        if (removed) {
            System.out.println("✅ Employee with ID " + employeeId + " removed successfully.");
        } else {
            System.out.println("⚠️ Employee ID not found: " + employeeId);
        }
        return removed;
    }

    // ✅ Implement updateCourierStaff method
    @Override
    public void updateCourierStaff(int employeeId, Employee updatedEmployee) {
        for (int i = 0; i < companyObj.getEmployeeDetails().size(); i++) {
            if (companyObj.getEmployeeDetails().get(i).getEmployeeID() == employeeId) {
                companyObj.getEmployeeDetails().set(i, updatedEmployee);
                System.out.println("✅ Employee details updated for ID: " + employeeId);
                return;
            }
        }
        System.out.println("⚠️ Employee ID not found: " + employeeId);
    }
}
