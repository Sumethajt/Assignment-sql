package Entities;

public class CourierStaff {
    private int staffId;   // The unique staff ID
    private boolean assigned;   // If the staff is assigned to an order or not

    // Constructor
    public CourierStaff(int staffId) {
        this.staffId = staffId;
        this.assigned = false;  // Initially, they are not assigned
    }

    // Getter and Setter for Staff ID
    public int getStaffId() {
        return staffId;
    }

    // Getter and Setter for assigned status
    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}

