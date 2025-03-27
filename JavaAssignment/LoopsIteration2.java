import java.util.Scanner;
import java.util.Random;
public class LoopsIteration2 {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        Random ran = new Random();
        int currentLocation = 0;
        int destination = 100;  // Destination location

        System.out.println("📦 Courier tracking started...");

        // While loop to track the courier in real-time
        while (currentLocation < destination) {
            // Simulate random distance traveled (between 5 to 15 units)
            int step = ran.nextInt(10) + 5;
            currentLocation += step;

            // Ensure courier does not exceed destination
            if (currentLocation > destination) {
                currentLocation = destination;
            }

            // Display the current location
            System.out.println("🚚 Courier is now at location: " + currentLocation);

            // Simulate real-time delay (1 second)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("⚠ Tracking interrupted!");
            }
        }

        System.out.println("✅ Courier has reached the destination!");
        sn.close();
    }
}
