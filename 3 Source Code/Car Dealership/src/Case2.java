import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class represents a case or scenario in the application.
 * Specifically, it handles the case where cars need to be filtered by condition (new or used).
 */
public class Case2 {

    /**
     * Logs the menu option and filters cars based on condition (new or used).
     *
     * @param logger Logger object to log information
     * @param printer Printer object to print car data
     * @param scanner Scanner object to get user input
     * @param fileReader2 FileReader2 object to read data from file
     * @param data 2D String array containing car data
     */
    public void case2(Logger logger, Printer printer, Scanner scanner, FileReader2 fileReader2,String[][] data){
        logger.info("Menu Option: 2. Filter Cars (used / new).");
        printer.printFilterCars();
        int x=Integer.parseInt(scanner.nextLine());
        if (x==1) {
            logger.info("Menu Option: 2. Filter Cars (used / new)> New.");
            String condition = "new";
            printer.printCarData(fileReader2.filterDataByCondition(data, condition, 3));

        }
        if(x==2){
            logger.info("Menu Option: 2. Filter Cars (used / new)> Used.");
            String condition = "used";
            printer.printCarData(fileReader2.filterDataByCondition(data, condition, 3));

        }
        if(x==3){
            logger.info("Menu Option: 2. Filter Cars (used / new)> Back.");
            System.out.println("Going back to Main menu");

        }
        else {
            logger.info("Menu Option: 2. Filter Cars (used / new)> invalid choice, going back to menu.");
            System.out.println("invalid choice, going back to menu");

        }


    }
}
