package Entities;
import java.time.LocalDate;

public class Courier {
    private int courierId;
    private String senderName;
    private String senderAddress;
    private String receiverName;
    private String receiverAddress;
    private String status;
    private String trackingNumber;
    private LocalDate orderPlacementDate;
    private LocalDate shipmentDate;
    private LocalDate deliveryDate;
    private int userID;
    private int serviceID;
    private int packageID;
    private int employeeID;
    private int locationID;
    private int paymentID;
    private int courierStaffID;

    // Default Constructor
    public Courier(String senderName, String senderAddress, String receiverName, String receiverAddress, String status, String trackingNumber, LocalDate orderPlacedDate, LocalDate shipmentDate, LocalDate deliveryDate, int userId, int serviceId, int packageId, int employeeId, int locationId) {}

    // Parameterized Constructor
    public Courier( String senderName, String senderAddress,
                   String receiverName, String receiverAddress, String status,
                   String trackingNumber, LocalDate deliveryDate, int userID,
                   int serviceID, int packageID, LocalDate shipmentDate,
                   int employeeID, int locationID, LocalDate orderPlacementDate, int paymentID) {
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.status = status;
        this.trackingNumber = trackingNumber;
        this.deliveryDate = deliveryDate;
        this.userID = userID;
        this.serviceID = serviceID;
        this.packageID = packageID;
        this.shipmentDate = shipmentDate;
        this.employeeID = employeeID;
        this.locationID = locationID;
        this.orderPlacementDate = orderPlacementDate;
        this.paymentID = paymentID;
    }

    public Courier(String trackingNumber, String receiverName, String status, int courierStaffID) {
        this.trackingNumber = trackingNumber;
        this.receiverName = receiverName;
        this.status = status;
        this.courierStaffID = courierStaffID;
    }

    // Getters and Setters
//    public int getCourierID() { return courierID; }
//    public void setCourierID(int courierID) { this.courierID = courierID; }

    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }

    public String getSenderAddress() { return senderAddress; }
    public void setSenderAddress(String senderAddress) { this.senderAddress = senderAddress; }

    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }

    public String getReceiverAddress() { return receiverAddress; }
    public void setReceiverAddress(String receiverAddress) { this.receiverAddress = receiverAddress; }

    //public double getWeight() { return weight; }
    //public void setWeight(double weight) { this.weight = weight; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public LocalDate getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate() { this.deliveryDate = deliveryDate; }

    public int getUserId() { return userID; }
    public void setUserId() { this.userID = userID; }

    public int getCourierStaffID() {return courierStaffID;}
    public void setCourierStaffID() { this.courierStaffID = courierStaffID;}

    public int getServiceID() { return serviceID;}
    public void setServiceID() { this.serviceID = serviceID;}

    public int getPackageID() {return packageID;}
    public void setPackageID() {packageID = packageID;}

    public LocalDate getShipmentDate() {return shipmentDate;}
    public void setShipmentDate() {shipmentDate = shipmentDate;}

    public int getLocationId() {return locationID;}
    public void setLocationId() {this.locationID = locationID;}

    public int getEmployeeId() {return employeeID;}
    public void setEmployeeId() {employeeID = employeeID;}

    public LocalDate getOrderPlacementDate() {return orderPlacementDate;}
    public void setOrderPlacementDate() {this.orderPlacementDate = orderPlacementDate;}

    public int getPaymentID() {return paymentID;}
    public void setPaymentID() {this.paymentID = paymentID;}

    // toString() method
    @Override
    public String toString() {
        return "Courier{" +
//                "courierID=" + courierID +
                ", senderName='" + senderName + '\'' +
                ", senderAddress='" + senderAddress + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                //", weight=" + weight +
                ", status='" + status + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", userID=" + userID +
                '}';
    }
}

