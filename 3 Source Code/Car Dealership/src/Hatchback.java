/**
* This Hatchback class extends Car
*/
public class Hatchback extends Car{

    public Hatchback(String id, String carType, String model, String condition, String color, String capacity, String mileage, String fuelType, String transmission, String vin, double price, int carsAvailable) {
        super(id, carType, model, condition, color, capacity, mileage, fuelType, transmission, vin, price, carsAvailable);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getCarType() {
        return carType;
    }

    @Override
    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public String getMileage() {
        return mileage;
    }

    @Override
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }

    @Override
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String getTransmission() {
        return transmission;
    }

    @Override
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @Override
    public String getVin() {
        return vin;
    }

    @Override
    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getCarsAvailable() {
        return carsAvailable;
    }

    @Override
    public void setCarsAvailable(int carsAvailable) {
        this.carsAvailable = carsAvailable;
    }
}
