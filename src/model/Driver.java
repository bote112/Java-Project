package model;

public class Driver {
    private Car car;
    private String name;
    private int age;
    private String country;

    public Driver(Car car, String name, int age, String country) {
        this.car = car;
        this.name = name;
        this.age = age;
        this.country = country;
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
                "car=" + car +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                '}';
    }
}
