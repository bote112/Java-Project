package model;

public class Car {
    private int carID;
    private String model;
    private String color;
    private String maxSpeed;
    private String power;

    public Car() {
    }
    public Car(String model, String color, String maxSpeed, String power) {
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

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public String getPower() {
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

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carID=" + carID +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", power=" + power +
                '}';
    }
}
