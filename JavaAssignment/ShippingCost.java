import java.util.Random;
import java.util.Scanner;

public class ShippingCost {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input source and destination addresses
        System.out.print("Enter source address: ");
        String source = scanner.nextLine();
        System.out.print("Enter destination address: ");
        String destination = scanner.nextLine();

        // Input weight of the parcel
        System.out.print("Enter weight of the parcel (kg): ");
        double weight = scanner.nextDouble();

        // Simulate distance calculation (random value for simplicity)
        int distance = getDistance(source, destination);

        // Calculate shipping cost
        double cost = calculateShippingCost(distance, weight);

        // Display shipping details
        System.out.println("\n📦 Shipping Details:");
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Weight: " + weight + " kg");
        System.out.println("💰 Shipping Cost: ₹" + cost);

        scanner.close();
    }

    // Simulate distance calculation (randomized for simplicity)
    public static int getDistance(String source, String destination) {
        Random random = new Random();
        return random.nextInt(1000) + 50; // Generates a distance between 50 and 1000 km
    }

    // Calculate shipping cost based on distance and weight
    public static double calculateShippingCost(int distance, double weight) {
        double baseRate = 5.0; // Base rate per km per kg
        return distance * weight * baseRate;
    }
}

