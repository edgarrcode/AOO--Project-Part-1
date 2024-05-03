import java.util.logging.Logger;

/**
 * This class represents a case or scenario in the application.
 * Specifically, it handles the case where all cars need to be displayed.
 */
public class Case1 {
    /**
     * Logs the menu option and prints all car data.
     *
     * @param logger Logger object to log information
     * @param printer Printer object to print car data
     * @param data 2D String array containing car data
     */
    public void case1(Logger logger,Printer printer,String[][] data){
        logger.info("Menu Option: 1. Display all cars.");
        printer.printALLData(data);
    }
}