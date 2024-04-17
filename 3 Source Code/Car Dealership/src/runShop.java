/**
* This class runs the dealership code.
* Run this class, then enter username and password.
* Use the menu options (1-5)
* Date: 4/9/24
* Course: CS 3331 – Advanced Object-Oriented Programming
* Instructor: Dr. Bhanukiran Gurijala
* Programming assignment 1
* Honesty Statment: We completed this work entirely on our own without any outside sources including peers, experts, online sources.
* @author Carlos Cabral and Edgar Rodriguez
* @version 1.1
*/
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

/** runShop */
public class runShop{
   public static void main(String[] args) throws Exception {
    //logger
    LoggingConfiguration.setupLogging();
    Logger logger = Logger.getLogger("");

    try {
        Printer printer = new Printer();
        Scanner scanner = new Scanner(System.in);
        InputLogger inputLogger = new InputLogger();
        InputInterpreter interpreter = new InputInterpreter();
        FileReader2 reader2 = new FileReader2();
        FileReader2 reader3 = new FileReader2();
        logger.info("Application Started");
        reader2.setInputFile("user_data.csv");
        String[][] data = reader2.readCSV();
        printer.printLogin();
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        logger.info("User attempted login");
        String[] loginInfo = inputLogger.loginLogger(username, password);
        String info = interpreter.loginInfo(loginInfo, 0);
        String[][] userData1 = reader2.filterDataByCondition(data, info, 6);
        reader3.setInputFile("car_data.csv");
        String[] infoToSendtoExcel;
        if (interpreter.loginChecker(loginInfo, userData1)){
            logger.info("Login successful"); // Log successful login
            Person customer = new Customer(userData1[0][0], userData1[0][1] +" " +userData1[0][2], Double.parseDouble(userData1[0][3]),
                    userData1[0][4], Boolean.parseBoolean(userData1[0][5]), userData1[0][6], userData1[0][7]);
            System.out.println("Hello "+ customer.getFullName());
            printer.printMenu();
            int menuInput= Integer.parseInt(scanner.nextLine());
            while (menuInput!=5) {
                inputLogger.menuLogger(menuInput);
                String[][] carData = reader3.readCSV();
                Car car=null;
                infoToSendtoExcel=interpreter.menuChoice(inputLogger.menuLogger(menuInput), reader3, printer, carData, scanner,customer);
                System.out.println("\n");
                if (car!=null) {
                    String[][] newUserData=reader2.updatedUserDataArrayMaker(data,infoToSendtoExcel,customer);
                    reader2.writeNewCSV(newUserData,"user_data2.csv");
                    String[][] newCarData= reader3.updatedCarDataArrayMaker(carData,infoToSendtoExcel,InputInterpreter.getCar());
                    reader3.writeNewCSV(newCarData,"car_data2.csv");   
                }
                printer.printMenu();
                menuInput=Integer.parseInt(scanner.nextLine());
            }
            System.out.println("Good bye!");
        }
        else {
            System.out.println("incorrect login information");
            new runShop();
        }    
    } catch (Exception e) {
        logger.severe("An unexpected error occurred: " + e.getMessage());
    }
    logger.info("Application Ended"); 
    }
}
