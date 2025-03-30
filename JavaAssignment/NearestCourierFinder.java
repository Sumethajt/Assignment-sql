import java.util.Random;

public class NearestCourierFinder {
    public static void main(String[] args) {
        Random random = new Random();

        // Create an array of available couriers
        ArraysDs2[] couriers = {
                new ArraysDs2("John Doe", random.nextInt(100)),
                new ArraysDs2("Sarah Lee", random.nextInt(100)),
                new ArraysDs2("Michael Scott", random.nextInt(100)),
                new ArraysDs2("Pam Beesly", random.nextInt(100)),
                new ArraysDs2("Jim Halpert", random.nextInt(100))
        };
        int orderLocation = random.nextInt(100); // Simulated order location
        System.out.println("📍 New order placed at location: " + orderLocation);

        // Find the nearest courier
        ArraysDs2 nearestCourier = couriers[0];
        int minDistance = Math.abs(couriers[0].location - orderLocation);

        for (int i = 1; i < couriers.length; i++) {
            int distance = Math.abs(couriers[i].location - orderLocation);
            if (distance < minDistance) {
                minDistance = distance;
                nearestCourier = couriers[i];
            }
        }

        System.out.println("🚛 Nearest available courier: " + nearestCourier.Name +
                " (Location: " + nearestCourier.location + ")");
    }
}