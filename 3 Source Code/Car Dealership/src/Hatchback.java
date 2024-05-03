/**
* This Hatchback class extends Car
*/
public class Hatchback extends Car{

    /**
     * Constructs a new Hatchback with the specified details.
     *
     * @param id the ID of the car
     * @param carType the type of the car
     * @param model the model of the car
     * @param condition the condition of the car
     * @param color the color of the car
     * @param capacity the capacity of the car
     * @param mileage the mileage of the car
     * @param fuelType the fuel type of the car
     * @param transmission the transmission of the car
     * @param vin the VIN of the car
     * @param price the price of the car
     * @param carsAvailable the number of cars available
     */
    public Hatchback(String id, String carType, String model, String condition, String color, String capacity, String mileage, String fuelType, String transmission, String vin, double price, int carsAvailable) {
        super(id, carType, model, condition, color, capacity, mileage, fuelType, transmission, vin, price, carsAvailable);
    }

    /**
     * Returns the ID of the car.
     *
     * @return the ID of the car
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the car.
     *
     * @param id the new ID of the car
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the type of the car.
     *
     * @return the type of the car
     */
    @Override
    public String getCarType() {
        return carType;
    }

    /**
     * Sets the type of the car.
     *
     * @param carType the new type of the car
     */
    @Override
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * Returns the model of the car.
     *
     * @return the model of the car
     */
    @Override
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the car.
     *
     * @param model the new model of the car
     */
    @Override
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns the condition of the car.
     *
     * @return the condition of the car
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the condition of the car.
     *
     * @param condition the new condition of the car
     */
    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Returns the color of the car.
     *
     * @return the color of the car
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the car.
     *
     * @param color the new color of the car
     */
    @Override
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Returns the capacity of the car.
     *
     * @return the capacity of the car
     */
    @Override
    public String getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the car.
     *
     * @param capacity the new capacity of the car
     */
    @Override
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the mileage of the car.
     *
     * @return the mileage of the car
     */
    @Override
    public String getMileage() {
        return mileage;
    }

    /**
     * Sets the mileage of the car.
     *
     * @param mileage the new mileage of the car
     */
    @Override
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }


    /**
     * Returns the fuel type of the car.
     *
     * @return the fuel type of the car
     */
    @Override
    public String getFuelType() {
        return fuelType;
    }

    /**
     * Sets the fuel type of the car.
     *
     * @param fuelType the new fuel type of the car
     */
    @Override
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * Returns the transmission of the car.
     *
     * @return the transmission of the car
     */
    @Override
    public String getTransmission() {
        return transmission;
    }

    /**
     * Sets the transmission of the car.
     *
     * @param transmission the new transmission of the car
     */
    @Override
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * Returns the VIN of the car.
     *
     * @return the VIN of the car
     */
    @Override
    public String getVin() {
        return vin;
    }

    /**
     * Sets the VIN of the car.
     *
     * @param vin the new VIN of the car
     */
    @Override
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Returns the price of the car.
     *
     * @return the price of the car
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the car.
     *
     * @param price the new price of the car
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the number of cars available.
     *
     * @return the number of cars available
     */
    @Override
    public int getCarsAvailable() {
        return carsAvailable;
    }

    /**
     * Sets the number of cars available.
     *
     * @param carsAvailable the new number of cars available
     */
    @Override
    public void setCarsAvailable(int carsAvailable) {
        this.carsAvailable = carsAvailable;
    }
}
