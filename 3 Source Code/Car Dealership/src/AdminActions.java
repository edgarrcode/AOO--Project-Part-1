import java.io.*;
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

        String newCar = String.format("\n%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
            capacity, carType, carsAvailable, condition, color, iD, year, price, transmission, vIN, fuelType, model, hasTurbo);

        try (FileWriter fw = new FileWriter("admincar_out.csv", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(newCar);
        }
        System.out.println("Car added successfully.");
    }

    // Instance method
    public void removeCar() throws IOException {
        System.out.print("Enter the ID of the car to remove: ");
        String idToRemove = scanner.nextLine();

        List<String> lines = Files.readAllLines(Paths.get("admincar_out.csv"));
        
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
        try (FileWriter fw = new FileWriter("admincar_out.csv", false);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String line : updatedLines) {
                bw.write(line + "\n");
            }
        }
        System.out.println("Car removed successfully.");
    }

    // Instance method
    public void displayCars() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("admincar_out.csv"))) {
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

        String newUser = String.format("\n%s,%s,%s,%s,%s,%s,%s,%s",
            moneyAvailable, password, lastName, id, carsPurchased, firstName, username, membership);

        try (FileWriter fw = new FileWriter("user_data_out.csv", true);
            BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(newUser);
        }
        System.out.println("User added successfully.");
    }
}