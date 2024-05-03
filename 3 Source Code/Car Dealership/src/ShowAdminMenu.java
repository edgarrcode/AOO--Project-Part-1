import java.io.IOException;
import java.util.Scanner;

/**
 * This class is responsible for displaying the admin menu and handling the admin's choices.
 */
public class ShowAdminMenu implements ShowAdminMenuInterface {

    /**
     * Displays the admin menu and handles the admin's choices.
     * The admin can choose to add a car, remove a car, display cars, add a user, get revenue, or exit.
     * If the admin chooses to get revenue, they can choose to get all revenue, revenue by ID, revenue by car type, or go back.
     *
     * @param scanner the Scanner object used to get the admin's choices
     * @throws IOException if an I/O error occurs
     */
    public static void showAdminMenu(Scanner scanner) throws IOException {
        AdminActions adminActions = new AdminActions(scanner);
        System.out.println("hello admin");
        while (true) {
            System.out.println("\n1. Add Car");
            System.out.println("2. Remove Car");
            System.out.println("3. Display Cars");
            System.out.println("4. Add User");
            System.out.println("5. Get Revenue");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());  // Attempt to parse the input as an integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;  // Skip the rest of the loop iteration and prompt the user again
            }

            switch (choice) {
                case 1:
                    adminActions.addCar();
                    break;
                case 2:
                    adminActions.removeCar();
                    break;
                case 3:
                    adminActions.displayCars();
                    break;
                case 4:
                    adminActions.addUser();
                    break;
                case 5:
                    System.out.println("1. All Revenue");
                    System.out.println("2. Revenue by ID");
                    System.out.println("3. Revenue Car Type");
                    System.out.println("4. Back");
                    System.out.print("Choose an option for Revenue: ");

                    int revenueChoice = 0;
                    try {
                        revenueChoice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        continue;  // Skip to the next iteration of the loop
                    }

                    switch (revenueChoice) {
                        case 1:
                            adminActions.getAllRevenue();
                            break;
                        case 2:
                            adminActions.sumPricesMatchedCarIDs();
                            break;
                        case 3:
                            adminActions.sumPricesMatchedCarTypes();
                            break;
                        case 4:
                            System.out.println("Returning to main menu...");
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }
}
