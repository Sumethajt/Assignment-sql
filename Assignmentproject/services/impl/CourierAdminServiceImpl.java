package Services.impl;

import Entities.Courier;
import Entities.CourierCompany;
import Entities.Employee;
import exceptions.InvalidEmployeeIdException;
import Services.ICourierAdminService;
import Dao.CourierUserServiceCollectionImpl;
import Dao.CourierAdminServiceCollectionImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CourierAdminServiceImpl implements ICourierAdminService {
    private Map<Integer, Employee> employeeDatabase = new HashMap<>();
    private Map<Integer, List<Courier>> assignedOrders = new HashMap<>();
    private static int employeeCounter = 1;
    protected CourierCompany companyObj;
    public CourierAdminServiceImpl() {
        companyObj = new CourierCompany(); // Initialize it as per your class design
    }

    @Override
    public boolean addCourierStaff(Employee employee) {  // ✅ Return type matches
        boolean added = companyObj.getEmployeeDetails().add(employee);
        if (added) {
            System.out.println("✅ Courier staff added: " + employee.getEmployeeID());
        }
        return added;
    }


    @Override
    public List<Courier> getAssignedOrder(int courierStaffId) throws InvalidEmployeeIdException {
        if (!employeeDatabase.containsKey(courierStaffId)) {
            throw new InvalidEmployeeIdException("Employee ID " + courierStaffId + " is invalid!");
        }
        return assignedOrders.getOrDefault(courierStaffId, new ArrayList<>());
    }
}
