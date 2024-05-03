import java.util.Scanner;

/**
 * This class represents a case or scenario in the application.
 * Specifically, it handles the case where a user wants to purchase a car.
 */
public class Case3 extends InputInterpreter {
    private InputInterpreter inputInterpreter = new InputInterpreter();
    FileReader2 fileReader = new FileReader2();
    Scanner scanner=new Scanner(System.in);
    InputInterpreter interpreter= new InputInterpreter();

    /**
     * Logs the menu option and prompts the user to provide the ID of the car they want to purchase.
     * It then processes the purchase and prints a ticket.
     *
     * @param user Person object representing the user
     * @param data 2D String array containing car data
     * @param finder Finder object to find the index of a column
     * @return String array containing the purchase information
     */
    public Case3() {
        }

    public String[] purchaseCar(Person user, String[][] data, Finder finder) {
        System.out.println("Please provide ID for the car you want to purchase");
        String condition = scanner.nextLine();
        String[][] carToBuyData = fileReader.filterDataByCondition(data, condition, finder.findColumnIndex(data, "ID"));
        Car myNewCar = inputInterpreter.createCar(carToBuyData, data, finder);
        inputInterpreter.setCar(myNewCar);
        ticketShower(myNewCar,user);
        return inputInterpreter.purchaseCar(myNewCar, user);
    }

    /**
     * Displays the ticket after a car purchase.
     *
     * @param myNewCar the car that has been purchased
     * @param user the user who purchased the car
     */
    public void ticketShower(Car myNewCar,Person user){
        System.out.println(myNewCar.getId());
        String condition;
        System.out.println("Press 4 for ticket!");
        condition= scanner.nextLine();
        if(condition.equals("4")){
            System.out.println("You have purchased a "+myNewCar.isCondition()+" " +myNewCar.getColor() +" "+myNewCar.getCarType()+ " VIN: "+myNewCar.getVin());
            System.out.println("Your new Balance is: "+user.getMoney());
        }

    }
}
