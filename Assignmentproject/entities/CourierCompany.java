package Entities;

import java.util.ArrayList;
import java.util.List;

public class CourierCompany {
    private String companyName;
    private List<Courier> courierDetails;
    private List<Employee> employeeDetails;
    private List<Location> locationDetails;
    private List<CourierStaff> courierStaffList;

    // Default Constructor: Initializes Lists to prevent NullPointerException
    public CourierCompany() {
        this.courierDetails = new ArrayList<>();
        this.employeeDetails = new ArrayList<>();
        this.locationDetails = new ArrayList<>();
        this.courierStaffList = new ArrayList<>();
        this.courierStaffList.add(new CourierStaff(1001));
        this.courierStaffList.add(new CourierStaff(1002));
        this.courierStaffList.add(new CourierStaff(1003));
    }

    public List<CourierStaff> getCourierStaffList() {
        return this.courierStaffList;
    }

    // Parameterized Constructor
    public CourierCompany(String companyName) {
        this();
        this.companyName = companyName;
    }

    // Getters and Setters
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public List<Courier> getCourierDetails() { return courierDetails; }
    public List<Employee> getEmployeeDetails() { return employeeDetails; }
    public List<Location> getLocationDetails() { return locationDetails; }

    // Methods to Add Entities
    public void addCourier(Courier courier) { courierDetails.add(courier); }
    public void addEmployee(Employee employee) { employeeDetails.add(employee); }
    public void addLocation(Location location) { locationDetails.add(location); }

    // Methods to Remove Entities
    public boolean removeCourier(String trackingNumber) {
        return courierDetails.removeIf(c -> c.getTrackingNumber().equals(trackingNumber));
    }

    public boolean removeEmployee(int employeeId) {
        return employeeDetails.removeIf(e -> e.getEmployeeID() == employeeId);
    }

    public boolean removeLocation(String locationName) {
        return locationDetails.removeIf(l -> l.getLocationName().equalsIgnoreCase(locationName));
    }

    // toString() method for Display
    @Override
    public String toString() {
        return "CourierCompanyCollection{" +
                "companyName='" + companyName + '\'' +
                ", courierDetails=" + courierDetails +
                ", employeeDetails=" + employeeDetails +
                ", locationDetails=" + locationDetails +
                '}';
    }
}
