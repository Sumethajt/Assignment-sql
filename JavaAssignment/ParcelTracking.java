import java.util.Scanner;
public class ParcelTracking {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        String[][] parcel = {
                {"5873", "Parcel In transit"},
                {"5342", "Parcel Out for delivery"},
                {"5382", "Parcel pending for delivery"},
                {"1256", "Parcel delivered"}
        };
        while (true) {
            System.out.print("\nEnter Tracking Number (or type 'exit' to stop): ");
            String trackingNumber = sn.nextLine();

            if (trackingNumber.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Parcel Tracking System...");
                break;
            }

            // Call function to get parcel status
            String status = getParcelStatus(parcel, trackingNumber);

            if (status != null) {
                System.out.println("📦 Status: " + status);
            } else {
                System.out.println("❌ Invalid Tracking Number! Please try again.");
            }
        }
        sn.close();
    }


    public static String getParcelStatus(String[][] parcels, String trackingNumber) {
        for (int i = 0; i < parcels.length; i++) {
            if (parcels[i][0].equals(trackingNumber)) {
                return parcels[i][1]; // Return status if tracking number matches
            }
        }
        return null; // Return null if tracking number is not found
    }
}

