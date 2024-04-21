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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
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
    private static Finder f = new Finder();

    public static void showAdminMenu() throws IOException {
        System.out.println("hello admin");
        while (true) {
            System.out.println("\n1. Add Car");
            System.out.println("2. Remove Car");
            System.out.println("3. Remove Car");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    removeCar();
                    break;
                case 3:
                    displayCars();
                case 4:
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
    // Admin Method: Add new car
    private static void addCar() throws IOException {
        System.out.println("Enter the following details to add a car:");
        System.out.print("Car Type: ");
        String carType = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Condition: ");
        String condition = scanner.nextLine();
        System.out.print("Capacity: ");
        String capacity = scanner.nextLine();
        System.out.print("Year: ");
        String year = scanner.nextLine();
        System.out.print("Fuel Type: ");
        String fuelType = scanner.nextLine();
        System.out.print("Transmission: ");
        String transmission = scanner.nextLine();
        System.out.print("VIN: ");
        String vin = scanner.nextLine();
        System.out.print("Price: ");
        String price = scanner.nextLine();
        System.out.print("Cars Available: ");
        String carsAvailable = scanner.nextLine();
        System.out.print("Has Turbo (true/false): ");
        String hasTurbo = scanner.nextLine();

        String newCar = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                carType, model, condition, capacity, year, fuelType, transmission, vin, price, carsAvailable, hasTurbo);

        try (FileWriter fw = new FileWriter("admincar_out.csv", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(newCar);
        }
        System.out.println("Car added successfully.");
    }
    //Admin Method: remove existing car
    private static void removeCar() throws IOException {
        System.out.print("Enter the VIN of the car to remove: ");
        String vinToRemove = scanner.nextLine();

        List<String> lines = Files.readAllLines(new File("admincar_out.csv").toPath());
        List<String> updatedLines = lines.stream()
                                         .filter(line -> !line.split(",")[7].equals(vinToRemove))
                                         .collect(Collectors.toList());

        try (FileWriter fw = new FileWriter("admincar_out.csv", false);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String line : updatedLines) {
                bw.write(line + "\n");
            }
        }
        System.out.println("Car removed successfully.");
    }
    //Admin Method: display cars
    private static void displayCars() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("admincar_out.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        }
    }
    public static void main(String[] args) throws Exception {
        LoggingConfiguration.setupLogging();
        logger.info("Application Started");

        authenticateUser();

        logger.info("Application Ended");
    }

    private static void authenticateUser() throws IOException {
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

    private static Person buildPerson(String[][] data, String username) {
        // Implementation to build person object
        return interpreter.personBuilder(f.rowFinder(data, f.findDataInColumn(username, f.getColumnValues(data, f.findColumnIndex(data, "Username")))), f, data);
    }

    private static void updateData(String[][] carData, String[] infoToSendtoExcel, Person customer) throws IOException {
        String[][] newUserData = reader2.updatedUserDataArrayMaker(carData, infoToSendtoExcel, customer);
        reader2.writeNewCSV(newUserData, "user_data_out.csv");
        String[][] newCarData = reader3.updatedCarDataArrayMaker(carData, infoToSendtoExcel, InputInterpreter.getCar());
        reader3.writeNewCSV(newCarData, "car_data_out.csv");
    }
}
