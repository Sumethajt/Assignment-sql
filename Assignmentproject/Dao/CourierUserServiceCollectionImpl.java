package Dao;

import Entities.*;
import Services.ICourierUserService;
import exceptions.TrackingNumberNotFoundException;
import exceptions.InvalidEmployeeIdException;

import java.util.ArrayList;
import java.util.List;

public class CourierUserServiceCollectionImpl implements ICourierUserService {
    protected CourierCompany companyObj;

    // Constructor initializing the CourierCompany object
    public CourierUserServiceCollectionImpl() {
        this.companyObj = new CourierCompany("FastCourier");
        this.companyObj.getCourierDetails().addAll(new ArrayList<>());
        this.companyObj.getEmployeeDetails().addAll(new ArrayList<>());
        this.companyObj.getLocationDetails().addAll(new ArrayList<>());
    }

    // Place a new courier order
    @Override
    public String placeOrder(Courier courierObj) {
        companyObj.getCourierDetails().add(courierObj);
        return courierObj.getTrackingNumber(); // Assuming Courier class has a trackingNumber field
    }

    // Get the status of a courier order
    @Override
    public String getOrderStatus(String trackingNumber) throws TrackingNumberNotFoundException {
        for (Courier courier : companyObj.getCourierDetails()) {
            if (courier.getTrackingNumber().equals(trackingNumber)) {
                return courier.getStatus();
            }
        }
        throw new TrackingNumberNotFoundException("Tracking number not found: " + trackingNumber);
    }

    // Cancel an order
    @Override
    public boolean cancelOrder(String trackingNumber) throws TrackingNumberNotFoundException {
        List<Courier> courierList = companyObj.getCourierDetails();
        for (Courier courier : courierList) {
            if (courier.getTrackingNumber().equals(trackingNumber)) {
                courierList.remove(courier);
                return true;
            }
        }
        throw new TrackingNumberNotFoundException("Tracking number not found: " + trackingNumber);
    }

    // Get assigned orders for a courier staff member
    @Override
    public List<Courier> getAssignedOrder(int courierStaffId) throws InvalidEmployeeIdException {
        List<Courier> assignedOrders = new ArrayList<>();

        for (Courier courier : companyObj.getCourierDetails()) {
            if (courier.getCourierStaffID() == courierStaffId) {
                assignedOrders.add(courier);
            }
        }

        if (assignedOrders.isEmpty()) {
            throw new InvalidEmployeeIdException("Invalid Employee ID: No assigned orders found.");
        }

        return assignedOrders;
    }

//    public String generateTrackingNumber() {
//        return "TRK" + System.currentTimeMillis(); // Example: TRK1711815292371
//    }

    public int getAvailableCourierStaffId() {
        // Assuming companyObj.getCourierStaffList() returns a List of CourierStaff objects
        for (CourierStaff staff : companyObj.getCourierStaffList()) {
            // Check if the staff member is available (unassigned)
            if (!staff.isAssigned()) {
                staff.setAssigned(true);  // Mark as assigned
                return staff.getStaffId();  // Return the staff ID
            }
        }
        // If no staff is available, return -1
        return -1;
    }



}
