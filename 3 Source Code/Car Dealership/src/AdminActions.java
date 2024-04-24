import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdminActions {
    private Scanner scanner;  // Non-static scanner

    public AdminActions(Scanner scanner) {
        this.scanner = scanner;  // Initialize the instance scanner
    }

    // Instance method
    public void addCar() throws IOException {
        System.out.println("Enter the following details to add a car:");
        System.out.print("Capacity: ");
        String capacity = scanner.nextLine();
        System.out.print("CarType: ");
        String carType = scanner.nextLine();
        System.out.print("CarsAvailable: ");
        String carsAvailable = scanner.nextLine();
        System.out.print("Condition: ");
        String condition = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("ID: ");
        String iD = scanner.nextLine();
        System.out.print("Year: ");
        String year = scanner.nextLine();
        System.out.print("Price: ");
        String price = scanner.nextLine();
        System.out.print("Transmission: ");
        String transmission = scanner.nextLine();
        System.out.print("VIN: ");
        String vIN = scanner.nextLine();
        System.out.print("FuelType: ");
        String fuelType = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("HasTurbo: ");
        String hasTurbo = scanner.nextLine();

        String newCar = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
            capacity, carType, carsAvailable, condition, color, iD, year, price, transmission, vIN, fuelType, model, hasTurbo);

        try (FileWriter fw = new FileWriter("car_data_out.csv", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(newCar);
        }
        System.out.println("Car added successfully.");
    }

    // Instance method
    public void removeCar() throws IOException {
        System.out.print("Enter the ID of the car to remove: ");
        String idToRemove = scanner.nextLine();

        List<String> lines = Files.readAllLines(Paths.get("car_data_out.csv"));
        
        // Extract headers
        String[] headers = lines.get(0).split(",");
        int idIndex = -1;
        
        // Find the index of the "ID" column
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equalsIgnoreCase("ID")) {
                idIndex = i;
                break;
            }
        }
        if (idIndex == -1) {
            System.out.println("ID column not found.");
            return;
        }
        final int finalIdIndex = idIndex;

        //remove row that matches the entered id
        System.out.println("printing lines:");
        System.out.println(lines.size());
        List<String> updatedLines = lines.stream()
        .filter(line -> {
            String[] parts = line.split(",");
            // Ensure the line has enough data before accessing the index
            return parts.length > finalIdIndex && !parts[finalIdIndex].equals(idToRemove);
        })
        .collect(Collectors.toList());
        System.out.println("printing lines:");
        System.out.println(updatedLines.size());
        try (FileWriter fw = new FileWriter("car_data_out.csv", false);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String line : updatedLines) {
                bw.write(line + "\n");
            }
        }
        System.out.println("Car removed successfully.");
    }

    // Instance method
    public void displayCars() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("car_data_out.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        }
    }
    // Instance method
    public void addUser() throws IOException {
        System.out.println("Enter the following details to add a user:");
        System.out.print("Money Available: ");
        String moneyAvailable = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Cars Purchased: ");
        String carsPurchased = scanner.nextLine();
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("MinerCars Membership (yes/no): ");
        String membership = scanner.nextLine();

        String newUser = String.format("%s,%s,%s,%s,%s,%s,%s,%s\n",
            moneyAvailable, password, lastName, id, carsPurchased, firstName, username, membership);

        try (FileWriter fw = new FileWriter("user_data_out.csv", true);
            BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(newUser);
        }
        System.out.println("User added successfully.");
    }
    /**
     * Instance method to read the "purchased.csv" file and sum all prices from the Price column.
     * Assumes the price is in a consistent column index across all rows.
     */
    public void getAllRevenue() {
        String filePath = "purchased.csv";
        int priceColumnIndex = 8;

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            double totalPrice = lines.stream()
                .skip(1) // Skip header row
                .mapToDouble(line -> {
                    String[] parts = line.split(",");
                    return Double.parseDouble(parts[priceColumnIndex].trim()); // Convert the price part to double
                })
                .sum(); // Sum all the prices

            System.out.println("Total Price of all purchased cars: $" + totalPrice);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing the prices: " + e.getMessage());
        }
    }

    /**
     * Method to sum prices for cars with matching ID
     */
    public void sumPricesMatchedCarIDs() {
        String filePath = "purchased.csv";
        int matchColumnIndex = 0;
        int priceColumnIndex = 8;

        try {
            // Ask user for the value to match
            System.out.print("Enter the car ID value: ");
            String matchValue = scanner.nextLine();

            List<String> lines = Files.readAllLines(Paths.get(filePath));
            double matchedTotalPrice = lines.stream()
                .skip(1) // Skip header row
                .filter(line -> {
                    String[] parts = line.split(",");
                    return parts.length > matchColumnIndex && parts[matchColumnIndex].trim().equalsIgnoreCase(matchValue);
                })
                .mapToDouble(line -> {
                    String[] parts = line.split(",");
                    return Double.parseDouble(parts[priceColumnIndex].trim()); // Convert the price part to double
                })
                .sum();

            System.out.println("Total Price of all matched purchased cars: $" + matchedTotalPrice);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing the prices: " + e.getMessage());
        }
    }

        /**
     * Method to sum prices for cars with matching ID
     */
    public void sumPricesMatchedCarTypes() {
        String filePath = "purchased.csv";
        int matchColumnIndex = 1;
        int priceColumnIndex = 8;

        try {
            // Ask user for the value to match
            System.out.print("Enter the car type (Sedan, SUV, Pickup, or Hatchback): ");
            String matchValue = scanner.nextLine();

            List<String> lines = Files.readAllLines(Paths.get(filePath));
            double matchedTotalPrice = lines.stream()
                .skip(1) // Skip header row
                .filter(line -> {
                    String[] parts = line.split(",");
                    return parts.length > matchColumnIndex && parts[matchColumnIndex].trim().equalsIgnoreCase(matchValue);
                })
                .mapToDouble(line -> {
                    String[] parts = line.split(",");
                    return Double.parseDouble(parts[priceColumnIndex].trim()); // Convert the price part to double
                })
                .sum();

            System.out.println("Total Price of all matched purchased cars: $" + matchedTotalPrice);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing the prices: " + e.getMessage());
        }
    }

}