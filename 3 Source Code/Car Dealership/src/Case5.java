import java.util.Scanner;

/**
 * This class represents a case or scenario in the application.
 * Specifically, it handles the case where a user wants to return a car.
 */
public class Case5 {
    InputInterpreter i=new InputInterpreter();

    /**
     * Logs the menu option and prompts the user to provide the ID of the car they want to return.
     * It then processes the return and prints a ticket.
     *
     * @param f Finder object to find the index of a column
     * @param purchasedCarData 2D String array containing purchased car data
     * @param user Person object representing the user
     * @param scanner Scanner object to get user input
     * @param fileReader2 FileReader2 object to read data from file
     * @param data 2D String array containing car data
     * @param newDataToSendToExcelFile String array containing the purchase information
     * @return String array containing the purchase information
     */
    public String[] case5(Finder f, String[][] purchasedCarData, Person user, Scanner scanner,FileReader2 fileReader2,String[][] data,String[] newDataToSendToExcelFile){

        if(i.doesUserHaveCar(f,purchasedCarData,user))
        {
            String condition2;
            String[][] carToBuyData2;
            System.out.println("To return, enter ID:");
            condition2= scanner.nextLine();
            String[][] carToReturn=fileReader2.filterDataByCondition(purchasedCarData,condition2,f.findColumnIndex(purchasedCarData,"ID"));

            carToBuyData2=fileReader2.filterDataByCondition(data,condition2,f.findColumnIndex(data,"ID"));
            System.out.println(carToBuyData2[0][0]);
            if (null != carToReturn[0][0]){
                //testing
                //System.out.println(carToReturn[0][0]);
                Car newCarToReturn=i.createCar(carToBuyData2,data,f);
                System.out.println("You have returned a "+newCarToReturn.isCondition()+" " +newCarToReturn.getColor() +" "+newCarToReturn.getCarType()+ " VIN: "+newCarToReturn.getVin());
                double newBalance=user.getMoney()+newCarToReturn.getPrice();
                System.out.println("Your new Balance is: "+newBalance);
                newDataToSendToExcelFile=i.purchaseCar(newCarToReturn,user);

                return newDataToSendToExcelFile;
            }


        }
        return null;
    }
}
