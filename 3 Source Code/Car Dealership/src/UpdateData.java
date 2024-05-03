/**
 * This class is used to update the data of cars and users.
 */
public class UpdateData implements updateDataInterface{

    /**
     * Updates the data of cars and users.
     * The method reads data from CSV files, updates the data, and writes the updated data to new CSV files.
     *
     * @param carData the data of all cars
     * @param infoToSendtoExcel the information to be sent to the Excel file
     * @param customer the person who is buying a car
     * @param userData the data of all users
     * @param f the Finder object used to search for cars
     * @param userReader the FileReader2 object used to read user data from a CSV file
     * @param carReader the FileReader2 object used to read car data from a CSV file
     * @throws Exception if an error occurs while reading data from a CSV file or updating data
     */
    static void updateData(String[][] carData, String[] infoToSendtoExcel, Person customer, String[][] userData, Finder f, FileReader2 userReader, FileReader2 carReader) throws Exception {
        //testing
        //System.out.println("in update data method "+ Arrays.toString(infoToSendtoExcel));
        String[][] newUserData = userReader.updatedUserDataArrayMaker(userData, infoToSendtoExcel, customer,f );
        //testing
        //System.out.println("in updateData() newUserData= "+Arrays.toString(newUserData));

        userReader.writeNewCSV(newUserData, "user_data_out.csv");
        //testing
        //System.out.println(Arrays.toString(infoToSendtoExcel));
        String[][] newCarData = carReader.updatedCarDataArrayMaker(carData, infoToSendtoExcel, InputInterpreter.getCar(),f);
        //testing
        //System.out.println("in updateData() newCarData= "+Arrays.toString(newCarData));

        carReader.writeNewCSV(newCarData, "car_data_out.csv");
    }
}
