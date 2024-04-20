/**
* This class has the methods to purchase a car.
*/
import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public class InputInterpreter {
    //logger
    private static final Logger logger = Logger.getLogger(InputInterpreter.class.getName());
    private static Car car;

    /**
     * this method checks if there are cars available to purchase
     * @param car
     * @return
     */
    public boolean isCarAvailable(Car car){
        logger.info("Checking car availability");
        return car.getCarsAvailable() > 0;
    }

    /**
     * This method checks if user has enough money to purchase car
     * @param carCost
     * @param userMoney
     * @return
     */
    public boolean enoughFunds(double carCost, double userMoney){
        logger.info("Checking if user has enough funds");
        return userMoney >= carCost;
    }

    /**
     * This method uses a switch/case to generate the car object based on the "Car Type"
     * At the moment it seems a bit redundant, but I was not able to think of better way to input all
     * necessary data automatically
     * @param carToBuyData
     * @return
     */
    public Car createCar(String[][] carToBuyData){
        String carType = carToBuyData[0][1].trim();
        logger.info("Creating car type");
        //depending on the car type a new car object is created
         switch (carType) {
            case "Hatchback" -> {
               Car car = new Hatchback(carToBuyData[0][0], carToBuyData[0][1], carToBuyData[0][2], carToBuyData[0][3], carToBuyData[0][4],
                        carToBuyData[0][5], carToBuyData[0][6], carToBuyData[0][7], carToBuyData[0][8], carToBuyData[0][9],
                        Double.parseDouble(carToBuyData[0][10]), Integer.parseInt(carToBuyData[0][11]));
                return car;
            }
            case "Sedan" -> {
                Car car = new Sedan(carToBuyData[0][0], carToBuyData[0][1], carToBuyData[0][2], carToBuyData[0][3], carToBuyData[0][4],
                        carToBuyData[0][5], carToBuyData[0][6], carToBuyData[0][7], carToBuyData[0][8], carToBuyData[0][9],
                        Double.parseDouble(carToBuyData[0][10]), Integer.parseInt(carToBuyData[0][11]));
                return car;
            }
            case "SUV" -> {
                Car car = new SUV(carToBuyData[0][0], carToBuyData[0][1], carToBuyData[0][2], carToBuyData[0][3], carToBuyData[0][4],
                        carToBuyData[0][5], carToBuyData[0][6], carToBuyData[0][7], carToBuyData[0][8], carToBuyData[0][9],
                        Double.parseDouble(carToBuyData[0][10]), Integer.parseInt(carToBuyData[0][11]));
                return car;
            }
            case "Pickup" -> {
                Car car = new Pickup(carToBuyData[0][0], carToBuyData[0][1], carToBuyData[0][2], carToBuyData[0][3], carToBuyData[0][4],
                        carToBuyData[0][5], carToBuyData[0][6], carToBuyData[0][7], carToBuyData[0][8], carToBuyData[0][9],
                        Double.parseDouble(carToBuyData[0][10]), Integer.parseInt(carToBuyData[0][11]));
                return car;
            }
            default -> throw new IllegalArgumentException("Invalid car type: " + carToBuyData[0][1]);

        }

    }

    /**
     * This method "purchases" the car by subtracting User money and car availability
     * as well as adding cars purchased to the user
     * @param car
     * @param user
     * @return
     */
    public String[] purchaseCar(Car car, Person user){
        String[] newData = new String[12];
        if(isCarAvailable(car) && enoughFunds(car.getPrice(),user.getMoney())){
            logger.info("Car purchased successfuly");
            newData[0]=Integer.toString(car.getCarsAvailable()-1);
            newData[1]=Integer.toString(Integer.parseInt(user.getCarsBought())+1);
            user.setMoney(user.getMoney()-car.getPrice());
            newData[2]=Double.toString(user.getMoney());
            newData[3]=car.getColor();
            newData[4]=car.getCarType();
            newData[5]=car.getVin();
        }
        else {
            logger.info("Purchase failed, not enough money.");
            System.out.println("Not enough M O N E Y");
            System.out.println("Car cost: "+car.getPrice());
            System.out.println("Current funds: "+user.getMoney());
            System.out.println("Returning to menu");
            return newData;
        }
        return newData;
    }

    public String loginInfo(String[] loginInfo,int x){
        return loginInfo[x];
    }

    /**
     * Checks if provided Username and password exist on a specific array
     * if both match
     * user is allowed to continue
     * @param info
     * @param userData
     * @return
     */
    public Boolean loginChecker(String[] info, String[][] userData){
        if (Objects.equals(info[0], userData[0][6]) && Objects.equals(info[1], userData[0][7])){
            return true;}
        else{
            System.out.println("Invalid login");
            return false;}
    }
    //New login checker: compares the user input to what is actually on the new userdata
    //if both are true, user is allowed in
    public Boolean newLoginChecker(String[] info,String[][] newScrambledUserData,Finder finder){
        int userNameIndex=finder.findColumnIndex(newScrambledUserData,"Username");
        int passWordIndex=finder.findColumnIndex(newScrambledUserData,"Password");

        String[] userNames=finder.getColumnValues(newScrambledUserData,userNameIndex);
        String[] passWord=finder.getColumnValues(newScrambledUserData,passWordIndex);

        if (finder.findUserInputInTheNewColumn(info,userNames,0) &&
                finder.findUserInputInTheNewColumn(info,passWord,1) ){
            return true;
        }

       System.out.println("Invalid login");
        return false;
    }

    /**
     * Main logic handler
     * Depending on user input several different processes take place
     * * Side-note: Most errors and difficulties came from this method
     * it can definitely be improved
     * @param menuInput
     * @param fileReader2
     * @param printer
     * @param data
     * @param scanner
     * @param user
     * @return
     */
    public String[] menuChoice(int menuInput, FileReader2 fileReader2,
                           Printer printer, String[][] data, Scanner scanner, Person user){
        String[] newDataToSendToExcelFile = new String[6];

        switch (menuInput){
            case 1:
            logger.info("Menu Option: 1. Display all cars.");
            printer.printALLData(data);

            break;
            case 2:
            logger.info("Menu Option: 2. Filter Cars (used / new).");
                printer.printFilterCars();
                //int x=Integer.parseInt(scanner.nextLine());
                int x = MenuInputReader.readMenuIntegerInput(scanner);
                if (x==1) {
                    logger.info("Menu Option: 2. Filter Cars (used / new)> New.");
                    String condition = "new";
                   printer.printCarData(fileReader2.filterDataByCondition(data, condition, 3));
                break;
                }
                if(x==2){
                    logger.info("Menu Option: 2. Filter Cars (used / new)> Used.");
                    String condition = "used";
                    printer.printCarData(fileReader2.filterDataByCondition(data, condition, 3));
                    break;
                }
                if(x==3){
                    logger.info("Menu Option: 2. Filter Cars (used / new)> Back.");
                    System.out.println("Going back to Main menu");
                    break;
                    }
                else {
                    logger.info("Menu Option: 2. Filter Cars (used / new)> invalid choice, going back to menu.");
                    System.out.println("invalid choice, going back to menu");
                }
                return null;

            case 3:
                logger.info("Menu Option: 3. Purchase a car");
                System.out.println("Please provide ID for the car you want to purchase");
                String condition= scanner.nextLine();
                String[][] carToBuyData=fileReader2.filterDataByCondition(data,condition,0);

                Car myNewCar=createCar(carToBuyData);
                setCar(myNewCar);
                newDataToSendToExcelFile=purchaseCar(myNewCar,user);
                if (newDataToSendToExcelFile[0]==null){
                    return null;
                }
                //Here we have to ask to filter what car they want to buy, we can use car id
                //here we set car data=filtered car
                System.out.println("Press 4 for ticket!");
                condition= scanner.nextLine();
                if(condition.equals("4")){
                    System.out.println("You have purchased a "+myNewCar.isCondition()+" " +myNewCar.getColor() +" "+myNewCar.getCarType()+ " VIN: "+myNewCar.getVin());
                    System.out.println("Your new Balance is: "+user.getMoney());
                }
                break;
            case 4:
                logger.info("Menu Option: 4. View Tickets");
                System.out.println("You must buy a car first!");
                break;
        }
        return newDataToSendToExcelFile;
    }
    public void setCar(Car car){
        this.car=car;
    }

    public static Car getCar() {
        return car;
    }
}
