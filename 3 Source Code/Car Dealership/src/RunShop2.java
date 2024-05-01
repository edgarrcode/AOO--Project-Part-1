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
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

/** runShop */
public class RunShop2{
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
            Finder f = new Finder();
            logger.info("Application Started");
            reader2.setInputFile("user_data_out.csv");
            String[][] data = reader2.readCSV();
            //testing
            //System.out.println(Arrays.deepToString(data));

            printer.printLoginUsername();
            String username = scanner.nextLine();
            printer.printLoginPassword();
            String password = scanner.nextLine();
            logger.info("User attempted login");
            String[] loginInfo = inputLogger.loginLogger(username, password);
            String info = interpreter.loginInfo(loginInfo, 0);

            //old code
            //String[][] userData1 = reader2.filterDataByCondition(data, info, 0);
            //System.out.println(Arrays.deepToString(userData1));


            reader3.setInputFile("car_data_out.csv");
            String[] infoToSendtoExcel;
            if (interpreter.newLoginChecker(loginInfo, data,f)){
                logger.info("Login successful"); // Log successful login

                //testing
                System.out.println("This works");
                Person customer = interpreter.personBuilder(
                        f.rowFinder(
                                data,f.findDataInColumn(
                                        username,f.getColumnValues(
                                                data,f.findColumnIndex(
                                                        data,"Username")
                                        )
                                )
                        ),f,data);
                System.out.println("Testing:");
                System.out.println("Fullname: "+customer.getFullName());;
                System.out.println("Money: "+customer.getMoney());
                System.out.println("Username: "+customer.getUsername());
                System.out.println("ID: "+customer.getId());
                printer.printMenu();



                int menuInput= Integer.parseInt(scanner.nextLine());
                while (menuInput!=5) {
                    inputLogger.menuLogger(menuInput);
                    String[][] carData = reader3.readCSV();

                    infoToSendtoExcel=interpreter.menuChoice(inputLogger.menuLogger(menuInput), reader3, printer, carData, scanner,customer,f);
                    System.out.println("\n");
                    Car car=InputInterpreter.getCar();
                    //Fixed if statement. Overwritten data was printing 'null' on new file
                    if (null!=car) {
                        String[][] newUserData=reader2.updatedUserDataArrayMaker(data,infoToSendtoExcel,customer);
                        //debugging
                        //System.out.println(Arrays.toString(new String[]{newUserData[7][3]}));
                        //printer.printALLData(newUserData);
                        reader2.writeNewCSV(newUserData,"user_data_out.csv");
                        String[][] newCarData= reader3.updatedCarDataArrayMaker(carData,infoToSendtoExcel,InputInterpreter.getCar());
                        reader3.writeNewCSV(newCarData,"car_data_out.csv");
                    }
                    printer.printMenu();
                    menuInput=Integer.parseInt(scanner.nextLine());
                }
                System.out.println("Good bye!");
            }
            else {
                System.out.println("incorrect login information");
                new RunShop2();
            }
        } catch (Exception e) {
            logger.severe("An unexpected error occurred: " + e.getMessage());
        }
        logger.info("Application Ended");
    }
}
