/**
* This class Reads and writes the user and car files
*/
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class FileReader2 {
    private String inputFile;
    String[][] data = null;
    /**
     * setter for our input file ie 'car_data.csv' or 'use_data.csv'
     * @param inputFile
     */
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Method to read from a csv file
     * @return data
     * @throws IOException
     */
    public String[][] readCSV() throws IOException {
        String line = "";
        String csvSplitBy = ",";
        String[][] data = null;
        /**
         * This line creates a BufferedReader named br that will read from a file with the name inputFile
         */
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            int lines = 0;
            while (br.readLine() != null) lines++;
            br.close();

            data = new String[lines][];

            BufferedReader br2 = new BufferedReader(new FileReader(inputFile));
            int i = 0;
            while ((line = br2.readLine()) != null) {
                // use comma as separator
                String[] values = line.split(csvSplitBy);
                data[i] = values;
                i++;
            }
            br2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * This method filters the required data by using a condition
     * This method is used in conjunction with several other methods for a variety of tasks
     * @param data
     * @param condition
     * @param x
     * @return result
     */
    public String[][] filterDataByCondition(String[][] data, String condition, int x){
        // Create a list to store the matching rows
        List<String[]> matchingRows = new ArrayList<>();

        // Iterate over each row in the data
        for (String[] row : data) {
            // Check if the data matches the user's input
            if (row[x].equalsIgnoreCase(condition)) {
                // Add the matching row to the list
                matchingRows.add(row);
            }
        }

        // Convert the list of matching rows to a 2D array
        String[][] result = new String[matchingRows.size()][];
        for (int i = 0; i < matchingRows.size(); i++) {
            result[i] = matchingRows.get(i);
        }
        // this result will be used by another method eventually
        return result;
    }

    /**
     * This method writes a new file using updated data arrays
     * @param data
     * @param outputFile
     * @throws Exception
     */
    public void writeNewCSV(String[][] data, String outputFile) throws Exception {
        // Creates the new File object
        java.io.File newFile = new java.io.File(outputFile);

        // Creates the PrintWriter object on new File object
        try (java.io.PrintWriter outfile = new java.io.PrintWriter(newFile)) {
            // Iterate over each row in the data
            for (String[] row : data) {
                // Join the row elements with commas and write the line to the file
                outfile.write(String.join(",", row) + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }/**
     This method updates the original user data array by replacing the users money with their new balance
     This method is hard coded and can be improved on.
     */
    public String[][] updatedUserDataArrayMaker(String[][] originalData, String[] newData,Person user, Finder finder) {
        for(int i=0; i<8;i++){
            if (user.getId().equals(originalData[i][finder.findColumnIndex(originalData,"ID")])){
                originalData[i][finder.findColumnIndex(originalData,"Money Available")]=newData[2];//money
                originalData[i][finder.findColumnIndex(originalData,"Cars Purchased")]=newData[1];//cars bought
            }
        }
        return originalData;
    }/**
     This method updates the original car data array by decreasing the ammount of cars available
     This method is hard coded and can be improved on and maybe incorporated onto the previous method
     */
    public String[][] updatedCarDataArrayMaker(String[][] originalData, String[] newData,Car car,Finder f) {
        /**Iterates through original array to find the correct car based on the ID of the car object*/
        for(int i=0; i<12;i++){
            if (car.getId().equals(originalData[i][f.findColumnIndex(originalData,"ID")])){
                originalData[i][f.findColumnIndex(originalData,"Cars Available")]=newData[0];
            }
        }
        /**returns data to be written later*/
        return originalData;
    }

}
