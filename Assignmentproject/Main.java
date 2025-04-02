import Dao.CourierServiceDb;
import Entities.Courier;
import Dao.CourierUserServiceCollectionImpl;
import exceptions.TrackingNumberNotFoundException;
import exceptions.InvalidEmployeeIdException;
import connectionutil.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static Dao.CourierServiceDb.generateStatus;
import static Dao.CourierServiceDb.insertNewUser;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/courier_management_sys";  // ✅ Ensure correct port
        String user = "root";
        String password = "12345678";  // ✅ Ensure correct password

        Scanner scanner = new Scanner(System.in);
        CourierServiceDb courierServiceDb = new CourierServiceDb(); // Create an instance of CourierServiceDb

        while (true) {
            System.out.println("\n=== Courier Management System ===");
            System.out.println("1. Place Order");
            System.out.println("2. Get Order Status");
            System.out.println("3. Deleting the order");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // 📦 Place an Order
                    System.out.println("\nEnter details to place a new order:");

                    System.out.println("Are you an existing user? (yes/no): ");
                    String existingUser = scanner.nextLine().toLowerCase();

                    int userId = 0;

                    if (existingUser.equals("yes")) {
                        // Existing user, ask for their User ID
                        System.out.println("Enter your User ID: ");
                        userId = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                    } else if (existingUser.equals("no")) {
                        // New user, ask for their details
                        System.out.println("Enter your Name: ");
                        String name = scanner.nextLine();

                        System.out.println("Enter your Email: ");
                        String email = scanner.nextLine();

                        System.out.println("Enter your Password: ");
                        String pass = scanner.nextLine();

                        System.out.println("Enter your Contact Number: ");
                        String contactNumber = scanner.nextLine();

                        System.out.println("Enter your Address: ");
                        String address = scanner.nextLine();

                        System.out.println("Enter your Username: ");
                        String username = scanner.nextLine();

                        // Insert the new user into the database
                        userId = insertNewUser(name, email, password, contactNumber, address, username);

                        System.out.println("✅ New user created successfully! Your User ID: " + userId);
                    }


                    System.out.println("Enter Sender Name: ");
                    String senderName = scanner.nextLine();

                    System.out.println("Enter Sender Address: ");
                    String senderAddress = scanner.nextLine();

                    System.out.println("Enter Receiver Name: ");
                    String receiverName = scanner.nextLine();

                    System.out.println("Enter Receiver Address: ");
                    String receiverAddress = scanner.nextLine();

                    System.out.println("Enter Service ID" + "\n" +
                            "10: Standard delivery" + "\n" +
                            "20: Express deliery" + "\n" +
                            "30: Overnight delivery" + "\n" +
                            "40: International delivery" + "\n" +
                            "50: Same day delivery");
                    int serviceId = scanner.nextInt();

                    // Generate the tracking number for the order
                    String trackingNumber = generateTrackingNumber();
                    System.out.println("The Tracking Number: " + trackingNumber);

                    // Get today's date for orderPlacedDate
                    LocalDate orderPlacedDate = LocalDate.now();
                    System.out.println("OrderPlcedDate: " + orderPlacedDate);

                    // Shipment date is the next day
                    LocalDate shipmentDate = orderPlacedDate.plusDays(1);
                    System.out.println("ShipmentDate: " + shipmentDate);

                    // Set delivery date based on service ID
                    LocalDate deliveryDate;
                    switch (serviceId) {
                        case 10:
                            deliveryDate = shipmentDate.plusDays(5); // Delivered in 5 days
                            break;
                        case 20:
                            deliveryDate = shipmentDate.plusDays(2); // Delivered in 2 days
                            break;
                        case 30:
                            deliveryDate = shipmentDate.plusDays(1); // Delivered in 1 day
                            break;
                        case 40:
                            deliveryDate = shipmentDate.plusDays(10); // Delivered in 10 days
                            break;
                        case 50:
                            deliveryDate = shipmentDate; // Delivered same day
                            break;
                        default:
                            System.out.println("⚠️ Invalid Service ID. Defaulting to 5 days delivery.");
                            deliveryDate = shipmentDate.plusDays(5);
                            break;
                    }
                    System.out.println("Delivery date: " + deliveryDate);

                    String status = generateStatus(shipmentDate, deliveryDate);
                    System.out.println("Status:" + status);

                    System.out.println("Enter Package Name: ");
                    scanner.nextLine();
                    String packageName = scanner.nextLine();

                    System.out.println("Enter Quantity: ");
                    int quantity = scanner.nextInt();

                    System.out.println("Enter Weight (kg): ");
                    double weight = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline

                    // Insert package into database if it doesn't exist and retrieve packageId
                    int packageId = courierServiceDb.getOrCreatePackage(packageName, quantity, weight);

                    System.out.println("✅ Package registered with ID: " + packageId);

                    // List of existing Employee IDs
                    List<Integer> employeeIds = Arrays.asList(1051, 2098, 3175, 4240, 5312, 6789, 7213, 8564, 9032, 10025, 11247, 12089, 13542, 14923, 15789);

                // Select a random Employee ID
                    Random random = new Random();
                    int employeeId = employeeIds.get(random.nextInt(employeeIds.size()));

                    System.out.println("✅ Assigned Employee ID: " + employeeId);


                    System.out.println("Enter Delivery Location Name: ");
                    String locationName = scanner.nextLine();

                    String address = scanner.nextLine();

                    // Fetch or create the location ID
                    int locationId = courierServiceDb.getOrUpdateLocation(locationName, address);

                    System.out.println("✅ Location assigned with ID: " + locationId);

                    // Create the Courier object with the generated tracking number and other details
                    int paymentID=0;
                    // 📌 Payment Processing in Main Function
                    System.out.println("Enter Payment Date (YYYY-MM-DD): ");
                    LocalDate paymentDate = LocalDate.parse(scanner.nextLine());


                    // Calculate total amount based on Service ID
                    double costPerKg = switch (serviceId) {
                        case 10 -> 10.99;
                        case 20 -> 25.99;
                        case 30 -> 50.00;
                        case 40 -> 100.00;
                        case 50 -> 75.00;
                        default -> 0.00;
                    };

                    double totalAmount = weight * costPerKg;
                    System.out.println("💰 Total Payment Amount: $" + totalAmount);

                    int CourierID = CourierServiceDb.getGeneratedCourierID() ;
                    // Insert Payment Record
                    try (Connection connection = DBConnection.getConnection()) {
                        String query = "INSERT INTO payments (CourierID, LocationID, Amount, PaymentDate, PackageID, ServiceID, EmployeeID) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                                "ON DUPLICATE KEY UPDATE " +
                                "LocationID = VALUES(LocationID), " +
                                "Amount = VALUES(Amount), " +
                                "PaymentDate = VALUES(PaymentDate), " +
                                "PackageID = VALUES(PackageID), " +
                                "ServiceID = VALUES(ServiceID), " +
                                "EmployeeID = VALUES(EmployeeID)";

                        PreparedStatement stmt = connection.prepareStatement(query);

                        stmt.setInt(1, CourierID);  // Use correct variable for CourierID
                        stmt.setInt(2, locationId);
                        stmt.setDouble(3, totalAmount);
                        stmt.setDate(4, Date.valueOf(paymentDate));
                        stmt.setInt(5, packageId);
                        stmt.setInt(6, serviceId);
                        stmt.setInt(7, employeeId);

                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("✅ Payment recorded/updated successfully!");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }



                    Courier newCourier = new Courier(
                            senderName,
                            senderAddress,
                            receiverName,
                            receiverAddress,
                            status,
                            trackingNumber,
                            deliveryDate,
                            userId,
                            serviceId,
                            packageId,
                            shipmentDate,
                            employeeId,
                            locationId,
                            orderPlacedDate,
                            paymentID
                    );



                    // Insert the order into the database
                    courierServiceDb.insertCourierOrder(newCourier);

                    System.out.println("✅ Order Placed Successfully with Tracking Number: " + trackingNumber);
                    break;

                case 2:
                    // 🚚 Get Order Status
                    System.out.println("Enter Tracking Number: ");
                    String trackingNumberForStatus = scanner.nextLine();

                    try {
                        // Get order status from the database
                        String orderStatus = courierServiceDb.getOrderStatus(trackingNumberForStatus);
                        System.out.println("📦 Order Status: " + orderStatus);
                    } catch (SQLException e) {
                        System.out.println("⚠️ Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Enter Tracking Number to delete the order: ");
                    String trackingNumberForDelete = scanner.nextLine();

                    try {
                        // Delete the order from the database
                        boolean isDeleted = courierServiceDb.deleteCourierOrder(trackingNumberForDelete);
                        if (isDeleted) {
                            System.out.println("✅ Order with Tracking Number " + trackingNumberForDelete + " has been deleted successfully.");
                        } else {
                            System.out.println("⚠️ No order found with the provided Tracking Number.");
                        }
                    } catch (SQLException e) {
                        System.out.println("⚠️ Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("🚪 Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("⚠️ Invalid Choice. Try Again.");
            }
        }
    }

    // Method to generate the tracking number
    public static String generateTrackingNumber() {
        // Generate a random 4-digit number and prefix it with 'TRK'
        int randomNum = (int) (Math.random() * 10000); // Generate a 4-digit number
        return "TRK" + String.format("%04d", randomNum); // Example: TRK0123
    }
}
