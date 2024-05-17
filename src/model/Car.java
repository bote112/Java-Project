package model;

public class Car {
    private int carID;
    private String model;
    private String color;
    private int maxSpeed;
    private int power;

    public Car() {
    }
    public Car(int carID,String model, String color, int maxSpeed, int power) {
        this.carID = carID;
        this.model = model;
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.power = power;
    }

    public int getCarID() {
        return carID;
    }
    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getPower() {
        return power;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carID=" + carID +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", power=" + power +
                '}';
    }
}
