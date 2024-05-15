package model;

public class Car {
    private String model;
    private String color;
    private int maxSpeed;
    private int power;

    public Car(String model, String color, int maxSpeed, int power) {
        this.model = model;
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.power = power;
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
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", power=" + power +
                '}';
    }
}
