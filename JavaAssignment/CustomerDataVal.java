import java.util.Scanner;
import java.util.regex.*;
public class CustomerDataVal {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        System.out.println("Enter Details to validate");
        System.out.println("Enter the Data :");
        String Data = sn.nextLine();
        System.out.println("Enter the Details :");
        String Details = sn.nextLine();
        validateDetails(Data,Details);
        boolean isValid = validateDetails(Data, Details);
        if (isValid) {
            System.out.println("✅ Valid " + Details + ": " + Data);
        } else {
            System.out.println("❌ Invalid " + Details + "! Please enter a valid one.");
        }

    }
    public static Boolean validateDetails(String Data, String Details)
    {
        switch (Details.toLowerCase()) {
            case "name":
                return validateName(Data);
            case "address":
                return validateAddress(Data);
            case "phone":
                return validatePhoneNumber(Data);
            default:
                System.out.println("❌ Invalid detail type! Use 'name', 'address', or 'phone'.");
                return false;
        }
    }
    public static boolean validateName(String name)
    {
        String nameRegex = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$"; // Capitalized words
        return name.matches(nameRegex);
    }
    public static boolean validateAddress(String address)
    {
        String addressRegex = "^[A-Za-z0-9\\s,.-]+$"; // Letters, numbers, spaces, comma, dot, and hyphen allowed
        return address.matches(addressRegex);
    }
    public static boolean validatePhoneNumber(String phoneNumber)
    {
        String phoneRegex = "^\\d{3}-\\d{3}-\\d{4}$"; // Format 123-456-7890
        return phoneNumber.matches(phoneRegex);
    }
}
