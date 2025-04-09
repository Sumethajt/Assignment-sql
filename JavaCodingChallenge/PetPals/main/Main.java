
import dao.PetDAO;
import dao.DonationDAO;
import dao.AdoptionEventDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PetDAO petDAO = new PetDAO();
        DonationDAO donationDAO = new DonationDAO();
        AdoptionEventDAO eventDAO = new AdoptionEventDAO();

        int choice;

        do {
            System.out.println("\n=== 🐾 PetPals Management Menu ===");
            System.out.println("1. Display Available Pets");
            System.out.println("2. Record Cash Donation");
            System.out.println("3. View Adoption Events");
            System.out.println("4. Register for Adoption Event");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next(); // discard invalid input
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    petDAO.displayAvailablePets();
                    break;
                case 2:
                    donationDAO.recordDonation();
                    break;
                case 3:
                    eventDAO.displayAdoptionEvents();
                    break;
                case 4:
                    eventDAO.registerForEvent();
                    break;
                case 0:
                    System.out.println("👋 Exiting PetPals. Goodbye!");
                    break;
                default:
                    System.out.println("❗ Invalid choice. Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
