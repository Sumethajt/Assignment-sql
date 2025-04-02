package Services.impl;

import Entities.Courier;
import Entities.Employee;
import exceptions.TrackingNumberNotFoundException;
import exceptions.InvalidEmployeeIdException;
import Services.ICourierUserService;

import java.util.*;

public class CourierUserServiceImpl implements ICourierUserService {
    private Map<String, Courier> courierDatabase = new HashMap<>();
    private Map<Integer, List<Courier>> assignedOrders = new HashMap<>();

    @Override
    public String placeOrder(Courier courierObj) {
        String trackingNumber = courierObj.getTrackingNumber();
        courierDatabase.put(trackingNumber, courierObj);
        return trackingNumber;
    }

    @Override
    public String getOrderStatus(String trackingNumber) throws TrackingNumberNotFoundException {
        if (!courierDatabase.containsKey(trackingNumber)) {
            throw new TrackingNumberNotFoundException("Tracking number " + trackingNumber + " not found!");
        }
        return courierDatabase.get(trackingNumber).getStatus();
    }

    @Override
    public boolean cancelOrder(String trackingNumber) throws TrackingNumberNotFoundException { // ✅ Must match interface
        if (!courierDatabase.containsKey(trackingNumber)) {
            throw new TrackingNumberNotFoundException("Cannot cancel order: Tracking number " + trackingNumber + " not found!");
        }
        courierDatabase.remove(trackingNumber);
        return true;
    }

    @Override
    public List<Courier> getAssignedOrder(int courierStaffId) throws InvalidEmployeeIdException {
        if (!assignedOrders.containsKey(courierStaffId)) {
            throw new InvalidEmployeeIdException("Invalid Employee ID: " + courierStaffId + " is invalid!");
        }
        return assignedOrders.get(courierStaffId);
    }


}
