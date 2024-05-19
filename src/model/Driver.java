package model;

public class Driver {
    private int driverID;
    private Car car;
    private String name;
    private int age;
    private String country;

    public Driver() {
    }
    public Driver( Car car, String name, int age, String country) {
        this.car = car;
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public int getDriverID() {
        return driverID;
    }
    public Car getCar() {
        return car;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverID=" + driverID +
                ", car=" + car +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                '}';
    }
}
