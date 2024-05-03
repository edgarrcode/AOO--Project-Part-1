/**
* This Pickup class extends Car
*/
public class Pickup extends Car{

    /**
     * Constructs a new Pickup with the specified details.
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
    public Pickup(String id, String carType, String model, String condition, String color, String capacity, String mileage, String fuelType, String transmission, String vin, double price, int carsAvailable) {
        super(id, carType, model, condition, color, capacity, mileage, fuelType, transmission, vin, price, carsAvailable);
    }

    /**
     * Returns the ID of the pickup.
     *
     * @return the ID of the pickup
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the pickup.
     *
     * @param id the new ID of the pickup
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the car type of the pickup.
     *
     * @return the car type of the pickup
     */
    @Override
    public String getCarType() {
        return carType;
    }

    /**
     * Sets the car type of the pickup.
     *
     * @param carType the new car type of the pickup
     */
    @Override
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * Returns the model of the pickup.
     *
     * @return the model of the pickup
     */
    @Override
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the pickup.
     *
     * @param model the new model of the pickup
     */
    @Override
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns the condition of the pickup.
     *
     * @return the condition of the pickup
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the condition of the pickup.
     *
     * @param condition the new condition of the pickup
     */
    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Returns the color of the pickup.
     *
     * @return the color of the pickup
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the pickup.
     *
     * @param color the new color of the pickup
     */
    @Override
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Returns the capacity of the pickup.
     *
     * @return the capacity of the pickup
     */
    @Override
    public String getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the pickup.
     *
     * @param capacity the new capacity of the pickup
     */
    @Override
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the mileage of the pickup.
     *
     * @return the mileage of the pickup
     */
    @Override
    public String getMileage() {
        return mileage;
    }

    /**
     * Sets the mileage of the pickup.
     *
     * @param mileage the new mileage of the pickup
     */
    @Override
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    /**
     * Returns the fuel type of the pickup.
     *
     * @return the fuel type of the pickup
     */
    @Override
    public String getFuelType() {
        return fuelType;
    }

    /**
     * Sets the fuel type of the pickup.
     *
     * @param fuelType the new fuel type of the pickup
     */
    @Override
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * Returns the transmission of the pickup.
     *
     * @return the transmission of the pickup
     */
    @Override
    public String getTransmission() {
        return transmission;
    }

    /**
     * Sets the transmission of the pickup.
     *
     * @param transmission the new transmission of the pickup
     */
    @Override
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * Returns the VIN of the pickup.
     *
     * @return the VIN of the pickup
     */
    @Override
    public String getVin() {
        return vin;
    }

    /**
     * Sets the VIN of the pickup.
     *
     * @param vin the new VIN of the pickup
     */
    @Override
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Returns the price of the pickup.
     *
     * @return the price of the pickup
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the pickup.
     *
     * @param price the new price of the pickup
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
