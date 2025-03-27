import java.util.Scanner;
import java.util.Arrays;
public class ArraysDs1 {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        String[] locationTracking = new String[10];
        int locationcount = 0;
        System.out.println("Tracting started.........");
        while(locationcount<10)
        {
            System.out.println("Enter parcel's current location (or type 'done' to stop): ");
            String location = sn.nextLine();
            if(location.equalsIgnoreCase("done"))
            {
                break;
            }
            locationTracking[locationcount] = location;
            System.out.println("✅ Location updated: " + location);
            locationcount++;
        }
        if(locationcount==10)
        {
            System.out.println("⚠ Tracking history is full!");
        }
        System.out.println("\n📍 Parcel Tracking History:");
        for(int i=0;i<locationTracking.length;i++)
        {
            System.out.println((i + 1) + "." + locationTracking[i]);
        }
    }

}

