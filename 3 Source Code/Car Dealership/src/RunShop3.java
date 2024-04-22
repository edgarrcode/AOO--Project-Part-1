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
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RunShop3 {
    public static Logger logger = Logger.getLogger("");
    public static Scanner scanner = new Scanner(System.in);
    public static Printer printer = new Printer();
    public static InputLogger inputLogger = new InputLogger();
    public static InputInterpreter interpreter = new InputInterpreter();
    public static FileReader2 reader2 = new FileReader2();
    public static FileReader2 reader3 = new FileReader2();
    public static Finder f = new Finder();

    public static void showAdminMenu() throws IOException {
        AdminActions adminActions = new AdminActions(scanner);
        System.out.println("hello admin");
        while (true) {
            System.out.println("\n1. Add Car");
            System.out.println("2. Remove Car");
            System.out.println("3. Display Cars");
            System.out.println("4. Add User");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

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
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public static void showPersonMenu(Person customer) throws IOException {
        System.out.println("hello " + customer.getFullName());
        reader3.setInputFile("car_data.csv");
        String[] infoToSendtoExcel;

        printer.printMenu();
        int menuInput = Integer.parseInt(scanner.nextLine());
        while (menuInput != 5) {
            inputLogger.menuLogger(menuInput);
            String[][] carData = reader3.readCSV();
            infoToSendtoExcel = interpreter.menuChoice(inputLogger.menuLogger(menuInput), reader3, printer, carData, scanner, customer, f);
            if (null != InputInterpreter.getCar()) {
                updateData(carData, infoToSendtoExcel, customer);
            }
            printer.printMenu();
            menuInput = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("Good bye!");
    }
    public static void main(String[] args) throws Exception {
        LoggingConfiguration.setupLogging();
        logger.info("Application Started");

        authenticateUser();

        logger.info("Application Ended");
    }

    public static void authenticateUser() throws IOException {
        reader2.setInputFile("user_data.csv");
        String[][] data = reader2.readCSV();
        logger.info("User attempting to login");
        printer.printLoginUsername();
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
                showPersonMenu(customer);
            } else {
                System.out.println("Incorrect login information");
            }
        }
    }

    public static Person buildPerson(String[][] data, String username) {
        // Implementation to build person object
        return interpreter.personBuilder(f.rowFinder(data, f.findDataInColumn(username, f.getColumnValues(data, f.findColumnIndex(data, "Username")))), f, data);
    }

    public static void updateData(String[][] carData, String[] infoToSendtoExcel, Person customer) throws IOException {
        String[][] newUserData = reader2.updatedUserDataArrayMaker(carData, infoToSendtoExcel, customer);
        reader2.writeNewCSV(newUserData, "user_data_out.csv");
        String[][] newCarData = reader3.updatedCarDataArrayMaker(carData, infoToSendtoExcel, InputInterpreter.getCar());
        reader3.writeNewCSV(newCarData, "car_data_out.csv");
    }
}
