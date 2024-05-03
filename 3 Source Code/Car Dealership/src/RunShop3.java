import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class runs the dealership code.
 * Date: 4/9/24
 * Course: CS 3331 – Advanced Object-Oriented Programming
 * Instructor: Dr. Bhanukiran Gurijala
 * Programming assignment 1
 * Honesty Statement: We completed this work entirely on our own without any outside sources including peers, experts, online sources.
 * @author Carlos Cabral and Edgar Rodriguez
 * @version 1.3
 */
public class RunShop3 implements updateDataInterface, showPersonMenuInterface,authenticateUserInterface, ShowAdminMenuInterface{
    private static Logger logger = Logger.getLogger("");
    private static Scanner scanner = new Scanner(System.in);
    private static Printer printer = new Printer();
    private static InputLogger inputLogger = new InputLogger();
    static InputInterpreter interpreter = new InputInterpreter();
    private static FileReader2 userReader = new FileReader2();
    private static FileReader2 carReader = new FileReader2();
    private static FileReader2 purchaseReader = new FileReader2();
    private static Finder f = new Finder();

    public static void main(String[] args) throws Exception {
        LoggingConfiguration.setupLogging();
        logger.info("Application Started");
        System.err.println("before auth");
        AuthenticateUser.authenticateUser(userReader, logger, scanner, printer, inputLogger, carReader, purchaseReader, interpreter);

        logger.info("Application Ended");
    }
    static Person buildPerson(String[][] data, String username) {
        // Implementation to build person object
        return interpreter.personBuilder(f.rowFinder(data, f.findDataInColumn(username, f.getColumnValues(data, f.findColumnIndex(data, "Username")))), f, data);
    }

}
