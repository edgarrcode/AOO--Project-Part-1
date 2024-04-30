/**
* This class has the methods to purchase a car.
*/
import java.io.File;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
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
     * @param totalCost
     * @param userMoney
     * @return
     */
    public boolean enoughFunds(double totalCost, double userMoney){
        logger.info("Checking if user has enough funds");
        return userMoney >= totalCost;
    }

    /**
     * This method uses a switch/case to generate the car object based on the "Car Type"
     * At the moment it seems a bit redundant, but I was not able to think of better way to input all
     * necessary data automatically
     * @param carToBuyData
     * @return
     */
    public Car createCar(String[][] carToBuyData,String[][] data,Finder f){
        if (carToBuyData.length == 0 || carToBuyData[0].length == 0) {
            throw new IllegalArgumentException("carToBuyData cannot be empty");
        }

        String carType = carToBuyData[0][1].trim();
        String[] rowContents = new String[15];

        System.arraycopy(carToBuyData[0], 0, rowContents, 0, carToBuyData[0].length);


        logger.info("Creating car type");
        //depending on the car type a new car object is created
         switch (carType) {
            case "Hatchback" -> {
                Car car=hatchbackBuilder(rowContents,f,data);
                return car;
            }
            case "Sedan" -> {
                Car car = sedanBuilder(rowContents,f,data);
                return car;
            }
            case "SUV" -> {
                Car car = SUVBuilder(rowContents,f,data);
                return car;
            }
            case "Pickup" -> {
                Car car = pickupBuilder(rowContents,f,data);
                return car;
            }
            default -> throw new IllegalArgumentException("Invalid car type: " + carToBuyData[0][1]);

        }

    }

    public Car createCarToReturn(String[][] carToReturnData,String[][] data,Finder f){
        if (carToReturnData.length == 0 || carToReturnData[0].length == 0) {
            throw new IllegalArgumentException("carToBuyData cannot be empty");
        }

        String carType = carToReturnData[0][1].trim();
        String[] rowContents = new String[15];

        System.arraycopy(carToReturnData[0], 0, rowContents, 0, carToReturnData[0].length);


        logger.info("Creating car type");
        //depending on the car type a new car object is created
        switch (carType) {
            case "Hatchback" -> {
                Car car=hatchbackBuilder(rowContents,f,data);
                return car;
            }
            case "Sedan" -> {
                Car car = sedanBuilder(rowContents,f,data);
                return car;
            }
            case "SUV" -> {
                Car car = SUVBuilder(rowContents,f,data);
                return car;
            }
            case "Pickup" -> {
                Car car = pickupBuilder(rowContents,f,data);
                return car;
            }
            default -> throw new IllegalArgumentException("Invalid car type: " + carToReturnData[0][1]);

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
        String username = user.getUsername();
        double taxRate = 0.0625;
        String isMember = user.isMembership();
        double basePrice = car.getPrice();
        System.out.println(isMember);
        // Apply a 10% discount if the user is a member
        if (isMember != null && isMember.equals("TRUE")) {
            logger.info("User is a member, giving user a 10% discount!");
            basePrice *= 0.9; // Reduce price by 10%
        }

        // Calculate total cost including tax
        double totalCost = basePrice * (1 + taxRate);

        String[] newData = new String[12];
        if(isCarAvailable(car) && enoughFunds(totalCost, user.getMoney())){
            logger.info("Car purchased successfuly");
            newData[0]=Integer.toString(car.getCarsAvailable()-1);
            newData[1]=Integer.toString(Integer.parseInt(user.getCarsBought())+1);
            user.setMoney(user.getMoney() - totalCost);
            newData[2]=Double.toString(user.getMoney());
            newData[3]=car.getColor();
            newData[4]=car.getCarType();
            newData[5]=car.getVin();
            //testing
            //System.out.println("in purchase car "+newData[2]);

            // Append car details to the "purchased.csv" file
            appendCarDetailsToPurchasedFile(car, username, totalCost);
        }
        else {
            logger.info("Purchase failed, not enough money.");
            System.out.println("Not enough money, needed including tax: " + totalCost);
            System.out.println("Car cost: " + car.getPrice());
            System.out.println("Tax amount: " + (car.getPrice() * taxRate));
            System.out.println("Total cost including tax: " + totalCost);
            System.out.println("Returning to menu");
            return newData;
        }
        return newData;
    }

    /**
     * Appends all relevant car details to the "purchased.csv" file.
     * @param car
     */
    private void appendCarDetailsToPurchasedFile(Car car, String username, double totalCost) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("purchased.csv", true))) {
            // Assuming Car class has getters for each field
            String carDetails = String.join(",",
                car.getId(),
                car.getCarType(),
                car.getModel(),
                car.getColor(),
                car.getCapacity(),
                car.getFuelType(),
                car.getTransmission(),
                car.getVin(),
                String.valueOf(totalCost),
                String.valueOf(car.getCarsAvailable()),
                username
            );

            bw.write(carDetails + "\n");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write to purchased.csv", e);
        }
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
    public Person personBuilder(String [] rowContentsForPersonValues, Finder f,String[][] data){

        return new Customer(f.valueFinderInRow("ID",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("First Name",rowContentsForPersonValues,data)[0]+" "+f.valueFinderInRow("Last Name",rowContentsForPersonValues,data)[0],
                Double.parseDouble(f.valueFinderInRow("Money Available",rowContentsForPersonValues,data)[0]),
                f.valueFinderInRow("Cars Purchased",rowContentsForPersonValues,data)[0],
                "false",
                f.valueFinderInRow("Username",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Password",rowContentsForPersonValues,data)[0]
                );
    }
    public Sedan sedanBuilder(String [] rowContentsForPersonValues, Finder f,String[][] data){

        return new Sedan(f.valueFinderInRow("ID",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Car Type",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Model",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Condition",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Color",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Capacity",rowContentsForPersonValues,data)[0],"0",
                f.valueFinderInRow("Fuel Type",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Transmission",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Vin",rowContentsForPersonValues,data)[0],
                Double.parseDouble(f.valueFinderInRow("Price",rowContentsForPersonValues,data)[0]),
                Integer.parseInt(f.valueFinderInRow("Cars Available",rowContentsForPersonValues,data)[0]));

    }
    public SUV SUVBuilder(String [] rowContentsForPersonValues, Finder f,String[][] data){

        return new SUV(f.valueFinderInRow("ID",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Car Type",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Model",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Condition",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Color",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Capacity",rowContentsForPersonValues,data)[0],
                "0",
                f.valueFinderInRow("Fuel Type",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Transmission",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Vin",rowContentsForPersonValues,data)[0],
                Double.parseDouble(f.valueFinderInRow("Price",rowContentsForPersonValues,data)[0]),
                Integer.parseInt(f.valueFinderInRow("Cars Available",rowContentsForPersonValues,data)[0]));

    }
    public Hatchback hatchbackBuilder(String [] rowContentsForPersonValues, Finder f,String[][] data){

        return new Hatchback(f.valueFinderInRow("ID",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Car Type",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Model",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Condition",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Color",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Capacity",rowContentsForPersonValues,data)[0],"0",
                f.valueFinderInRow("Fuel Type",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Transmission",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Vin",rowContentsForPersonValues,data)[0],
                Double.parseDouble(f.valueFinderInRow("Price",rowContentsForPersonValues,data)[0]),
                Integer.parseInt(f.valueFinderInRow("Cars Available",rowContentsForPersonValues,data)[0]));

    }
    public Pickup pickupBuilder(String [] rowContentsForPersonValues, Finder f,String[][] data){

        return new Pickup(f.valueFinderInRow("ID",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Car Type",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Model",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Condition",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Color",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Capacity",rowContentsForPersonValues,data)[0],
                "0",
                f.valueFinderInRow("Fuel Type",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Transmission",rowContentsForPersonValues,data)[0],
                f.valueFinderInRow("Vin",rowContentsForPersonValues,data)[0],
                Double.parseDouble(f.valueFinderInRow("Price",rowContentsForPersonValues,data)[0]),
                Integer.parseInt(f.valueFinderInRow("Cars Available",rowContentsForPersonValues,data)[0]));

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
                           Printer printer, String[][] data, Scanner scanner, Person user, Finder f, String[][] purchasedCarData){
        String[] newDataToSendToExcelFile = new String[6];

        switch (menuInput){
            case 1:
                Case1 c1=new Case1();
            break;
            case 2:
                Case2 c2=new Case2();
                c2.case2(logger,printer,scanner,fileReader2,data);
            return null;
            case 3:
                Case3 c=new Case3();
                String [] carToBuyData=c.purchaseCar(user, data, f);
                //testing
                //System.out.println(Arrays.toString(carToBuyData) +"Im here");
                return carToBuyData;

            case 4:
                logger.info("Menu Option: 4. View Tickets");
                System.out.println("You must buy a car first!");
                break;
            case 5:
                Case5 c5=new Case5();
                return c5.case5(f,purchasedCarData,user,scanner,fileReader2,data,newDataToSendToExcelFile);

        }
        return newDataToSendToExcelFile;
    }
    public void setCar(Car car){
        this.car=car;
    }

    public static Car getCar() {
        return car;
    }

    public boolean doesUserHaveCar(Finder f,String[][] purchasedCarData,Person person){
        String[] info = new String[11];
        info[0]=person.getUsername();
        //System.out.println(".getusername= "+info[0]);

        int index=f.findColumnIndex(purchasedCarData,"Owner");

        //System.out.println("In doesUserHaveCar index: "+index );

        if(f.findUserInputInTheNewColumn(info,f.getColumnValues(purchasedCarData,index), 0)){
            return true;
        }
        else return false;

    }
}

