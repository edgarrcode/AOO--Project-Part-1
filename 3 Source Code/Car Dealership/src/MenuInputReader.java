/**
* This class ensures user enters a valid menu option.
*/

import java.util.Scanner;

public class MenuInputReader {
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
