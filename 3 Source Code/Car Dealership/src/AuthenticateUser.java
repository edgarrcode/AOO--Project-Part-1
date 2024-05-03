import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class is responsible for authenticating a user.
 * It implements the authenticateUserInterface.
 */
public class AuthenticateUser implements authenticateUserInterface {
    /**
     * Authenticates a user based on their input.
     *
     * @param userReader FileReader2 object to read user data
     * @param logger Logger object to log information
     * @param scanner Scanner object to read user input
     * @param printer Printer object to print messages
     * @param inputLogger InputLogger object to log user input
     * @param carReader FileReader2 object to read car data
     * @param purchaseReader FileReader2 object to read purchase data
     * @param interpreter InputInterpreter object to interpret user input
     * @throws Exception if an error occurs during file reading or user authentication
     */   
    public static void authenticateUser(FileReader2 userReader, Logger logger, Scanner scanner, Printer printer, InputLogger inputLogger,
                                        FileReader2 carReader, FileReader2 purchaseReader, InputInterpreter interpreter) throws Exception {
        userReader.setInputFile("user_data_out.csv");
        String[][] data = userReader.readCSV();
        if (null != data) {
            //testing
            // System.out.println("Data is being read " + data[1][2]);
        }
        logger.info("User attempting to login");

        // Ask if the user is an admin or a customer
        System.out.println("Are you an admin or a customer? (Enter 'admin' or 'customer')");
        String userType = scanner.nextLine().trim().toLowerCase();

        if ("admin".equals(userType)) {
            // Admin login flow
            ShowAdminMenu.showAdminMenu(scanner);
        } else if ("customer".equals(userType)) {
            // Customer login flow
            printer.printLoginUsername();
            String username = scanner.nextLine();
            printer.printLoginPassword();
            String password = scanner.nextLine();
            String[] loginInfo = inputLogger.loginLogger(username, password);
            if (RunShop3.interpreter.newLoginChecker(loginInfo, data, new Finder())) {
                logger.info("Login successful");
                Person customer = RunShop3.buildPerson(data, username);
                showPersonMenuConcrete.showPersonMenu(customer, data, new Finder(), carReader, scanner, printer, purchaseReader, interpreter, inputLogger, userReader);
            } else {
                System.out.println("Incorrect login information");
            }
        } else {
            System.out.println("Invalid user type. Please start over and enter 'admin' or 'customer'.");
        }
    }
}
