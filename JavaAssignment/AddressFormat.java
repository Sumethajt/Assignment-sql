import java.util.Scanner;

public class AddressFormat {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);

        // Taking address details as input
        System.out.println("📌 Enter Address Details");
        System.out.print("🏠 Street: ");
        String street = sn.nextLine();

        System.out.print("🏙 City: ");
        String city = sn.nextLine();

        System.out.print("🌎 State: ");
        String state = sn.nextLine();

        System.out.print("📮 ZIP Code: ");
        String zipCode = sn.nextLine();

        String formattedAddress = formatAddress(street, city, state, zipCode);
        if (formattedAddress != null) {
            System.out.println("\n✅ Formatted Address:\n" + formattedAddress);
        } else {
            System.out.println("❌ Invalid ZIP Code. Please enter a valid 5 or 9-digit ZIP Code.");
        }

        sn.close();
    }

    public static String formatAddress(String street, String city, String state, String zipCode) {
        // Validate ZIP Code (5-digit or 9-digit format)
        if (!zipCode.matches("^\\d{6}$")) {
            return null;
        }

        // Capitalizing the first letter of each word
        street = capitalizeWords(street);
        city = capitalizeWords(city);
        state = capitalizeWords(state);

        // Formatting the address properly
        return street + "\n" + city + ", " + state + " " + zipCode;
    }

    public static String capitalizeWords(String input) {
        String[] words = input.split("\\s+");
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return capitalized.toString().trim();
    }
}

