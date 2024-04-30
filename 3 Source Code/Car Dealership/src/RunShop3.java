/**
 * This class runs the dealership code.
 * Run this class, then enter username and password.
 * Use the menu options (1-5)
 * Date: 4/9/24
 * Course: CS 3331 – Advanced Object-Oriented Programming
 * Instructor: Dr. Bhanukiran Gurijala
 * Programming assignment 1
 * Honesty Statement: We completed this work entirely on our own without any outside sources including peers, experts, online sources.
 * @author Carlos Cabral and Edgar Rodriguez
 * @version 1.1
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RunShop3 {
    private static Logger logger = Logger.getLogger("");
    private static Scanner scanner = new Scanner(System.in);
    private static Printer printer = new Printer();
    private static InputLogger inputLogger = new InputLogger();
    private static InputInterpreter interpreter = new InputInterpreter();
    private static FileReader2 reader2 = new FileReader2();
    private static FileReader2 reader3 = new FileReader2();
    private static FileReader2 reader4 = new FileReader2();
    private static Finder f = new Finder();

    public static void showAdminMenu() throws IOException {
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

    public static void showPersonMenu(Person customer, String[][] userData, Finder f) throws Exception {
        System.out.println("Hello " + customer.getFullName());
        reader3.setInputFile("car_data_out.csv");
        String[] infoToSendtoExcel;
        System.err.println(" user menu");

        printer.printMenu();
        int menuInput = Integer.parseInt(scanner.nextLine());
        while (menuInput != 6) {
            inputLogger.menuLogger(menuInput);
            String[][] carData = reader3.readCSV();
            reader4.setInputFile("purchased.csv");
            String[][] purchasedCarsData = reader4.readCSV();
            infoToSendtoExcel = interpreter.menuChoice(inputLogger.menuLogger(menuInput), reader3, printer, carData, scanner, customer, f, purchasedCarsData);
            //testing
            //System.out.println(Arrays.toString(infoToSendtoExcel));
            if (null != InputInterpreter.getCar()) {
                //testing
                //System.out.println(customer.getMoney());
                updateData(carData, infoToSendtoExcel, customer, userData, f);
            }
            printer.printMenu();
            menuInput = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("Good bye!");
    }

    public static void main(String[] args) throws Exception {
        LoggingConfiguration.setupLogging();
        logger.info("Application Started");
        System.err.println("before auth");
        authenticateUser();

        logger.info("Application Ended");
    }

    private static void authenticateUser() throws Exception {
        reader2.setInputFile("user_data_out.csv");
        String[][] data = reader2.readCSV();
        if (null != data) {
            //testing
            //System.out.println("Data is being read " + data[1][2]);
        }
        logger.info("User attempting to login");
        printer.printLoginUsername();
        Finder f = new Finder();
        String username = scanner.nextLine();

        if ("admin".equals(username)) {
            showAdminMenu();
        } else {
            printer.printLoginPassword();
            String password = scanner.nextLine();
            String[] loginInfo = inputLogger.loginLogger(username, password);
            if (interpreter.newLoginChecker(loginInfo, data, f)) {
                logger.info("Login successful");
                Person customer = buildPerson(data, username);
                showPersonMenu(customer, data, f);
            } else {
                System.out.println("Incorrect login information");
            }
        }
    }

    private static Person buildPerson(String[][] data, String username) {
        // Implementation to build person object
        return interpreter.personBuilder(f.rowFinder(data, f.findDataInColumn(username, f.getColumnValues(data, f.findColumnIndex(data, "Username")))), f, data);
    }

    private static void updateData(String[][] carData, String[] infoToSendtoExcel, Person customer, String[][] userData,Finder f) throws Exception {

        String[][] newUserData = reader2.updatedUserDataArrayMaker(userData, infoToSendtoExcel, customer,f );
        //testing
        System.out.println("in updateData()"+Arrays.toString(newUserData[8]));

        reader2.writeNewCSV(newUserData, "user_data_out.csv");
        //testing
        //System.out.println(Arrays.toString(infoToSendtoExcel));
        String[][] newCarData = reader3.updatedCarDataArrayMaker(carData, infoToSendtoExcel, InputInterpreter.getCar(),f);
        //testing
        //System.out.println(Arrays.toString(newCarData[2]));

        reader3.writeNewCSV(newCarData, "car_data_out.csv");
    }
}
