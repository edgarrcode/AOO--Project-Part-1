import java.util.Scanner;

/**
* This class ensures user enters a valid menu option.
*/
public class MenuInputReader {

    /**
     * Reads the user's menu choice as an integer.
     * If the user's input is not a valid integer, it prompts the user to enter a valid menu item number.
     *
     * @param scanner the Scanner object used to read the user's input
     * @return the user's menu choice as an integer
     */
    public static int readMenuIntegerInput (Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry, please enter the number of a valid menu item");
            }
        }
    }
}
