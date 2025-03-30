import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OrderEmail {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting customer details
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter Order Number: ");
        String orderNumber = scanner.nextLine();

        System.out.print("Enter Delivery Address: ");
        String deliveryAddress = scanner.nextLine();

        // Simulate expected delivery date (5 days from today)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Date expectedDeliveryDate = new Date(currentDate.getTime() + (5L * 24 * 60 * 60 * 1000));

        // Generate email content
        String emailContent = generateEmail(customerName, orderNumber, deliveryAddress, dateFormat.format(expectedDeliveryDate));

        // Display the email
        System.out.println("\n=== ORDER CONFIRMATION EMAIL ===\n");
        System.out.println(emailContent);
    }

    public static String generateEmail(String name, String orderNumber, String address, String deliveryDate) {
        return "Dear " + name + ",\n\n" +
                "Thank you for your order! Your order details are as follows:\n\n" +
                "📦 Order Number: " + orderNumber + "\n" +
                "📍 Delivery Address: " + address + "\n" +
                "📅 Expected Delivery Date: " + deliveryDate + "\n\n" +
                "We appreciate your business and look forward to serving you again!\n" +
                "Best regards,\n" +
                "Courier Service Team";
    }
}