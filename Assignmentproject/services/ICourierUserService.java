package Services;

import exceptions.TrackingNumberNotFoundException;
import exceptions.InvalidEmployeeIdException;
import Entities.Courier;
import java.util.List;

public interface ICourierUserService {
    String placeOrder(Courier courierObj);
    String getOrderStatus(String trackingNumber) throws TrackingNumberNotFoundException;
    boolean cancelOrder(String trackingNumber) throws TrackingNumberNotFoundException; // ✅ Add 'throws' here
    List<Courier> getAssignedOrder(int courierStaffId) throws InvalidEmployeeIdException;
}
