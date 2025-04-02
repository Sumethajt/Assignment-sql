package Services;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import Entities.Employee;
import Entities.Courier;
import exceptions.InvalidEmployeeIdException;
public interface ICourierAdminService {

    /**
     * Add a new courier staff member to the system.
     * @param obj Employee object containing details of the courier staff.
     * @return The ID of the newly added courier staff member.
     */
    boolean addCourierStaff(Employee obj);
    List<Courier> getAssignedOrder(int courierStaffId) throws InvalidEmployeeIdException;
    boolean removeCourierStaff(int employeeId);
    void updateCourierStaff(int employeeId, Employee updatedEmployee);
}

