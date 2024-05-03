import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class is responsible for displaying the person menu and handling the person's choices.
 */
public class showPersonMenuConcrete implements showPersonMenuInterface{

    /**
     * Displays the person menu and handles the person's choices.
     * The person can choose to view cars, search for a car, buy a car, view purchased cars, add money, or exit.
     * The method reads data from CSV files, interprets the person's choices, logs the person's choices, and updates data.
     *
     * @param customer the person who is using the menu
     * @param userData the data of all users
     * @param f the Finder object used to search for cars
     * @param carReader the FileReader2 object used to read car data from a CSV file
     * @param scanner the Scanner object used to get the person's choices
     * @param printer the Printer object used to print the menu and car data
     * @param purchaseReader the FileReader2 object used to read purchased car data from a CSV file
     * @param interpreter the InputInterpreter object used to interpret the person's choices
     * @param inputLogger the InputLogger object used to log the person's choices
     * @param userReader the FileReader2 object used to read user data from a CSV file
     * @throws Exception if an error occurs while reading data from a CSV file or updating data
     */    
    public static void showPersonMenu(Person customer, String[][] userData, Finder f, FileReader2 carReader,
                                      Scanner scanner, Printer printer,FileReader2 purchaseReader,InputInterpreter interpreter, InputLogger inputLogger,FileReader2 userReader) throws Exception {

        System.out.println("Hello " + customer.getFullName());
        carReader.setInputFile("car_data_out.csv");
        String[] infoToSendtoExcel;
        System.err.println(" user menu");

        printer.printMenu();
        int menuInput = Integer.parseInt(scanner.nextLine());
        while (menuInput != 6) {
            inputLogger.menuLogger(menuInput);
            String[][] carData = carReader.readCSV();
            purchaseReader.setInputFile("purchased.csv");
            String[][] purchasedCarsData = purchaseReader.readCSV();
            infoToSendtoExcel = interpreter.menuChoice(inputLogger.menuLogger(menuInput), carReader, printer, carData, scanner, customer, f, purchasedCarsData);
            //testing
            //System.out.println("In show person method "+Arrays.toString(infoToSendtoExcel));
            if (null != InputInterpreter.getCar()) {
                //testing
                //System.out.println(customer.getMoney());
                //System.out.println(Arrays.deepToString(carData));
                //System.out.println(Arrays.toString(infoToSendtoExcel));

                UpdateData.updateData(carData, infoToSendtoExcel, customer, userData, f,userReader,carReader);
            }
            printer.printMenu();
            menuInput = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("Good bye!");
    }
}
