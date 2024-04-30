/**
* This class manages the print methods
*/
import java.util.Arrays;

public class Printer {

    public void printLoginUsername(){
        System.out.println("Username:");
    }
    public void printLoginPassword(){
        System.out.println("Password:");
    }
    public void failedLogin(){
        System.out.println("Wrong login information, please log in again.");
    }
    public void successfulLogin(String name){
        System.out.println("Welcome: "+ name);
    }
    public void printMenu(){
        System.out.println(
                "1. Display all cars.\n" +
                "2. Filter Cars (used / new)\n" +
                "3. Purchase a car\n" +
                "4. View Tickets\n" +
                        "5. Return a car\n"+
                "6. Sign out");
    }
    public void printFilterCars(){
        System.out.println(
                "1)New\n" +
                "   (Print info)\n" +
                "2)Used\n" +
                "   (Print info)\n" +
                "3)Go back\n" );
    }

    /**
     * Prints all data in given array
     * @param data
     */
    public void printALLData(String[][] data){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void printCarData(String[][] data) {
        // Check if the data is not null
        if (data != null) {
            // Iterate over each row in the data
            for (String[] row : data) {
                // Print the details of the car
                System.out.println(Arrays.toString(row));
            }
        } else {
            System.out.println("No cars found with the given condition.");
        }
    }
    /**
     * testing main
    public static void main(String[] args){
        printLogin();
    }*/
}
