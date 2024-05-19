package service;

import daoservices.CarRepositoryService;
import model.Car;
import utils.AuditManagement;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class CarService {
    private CarRepositoryService dbService;

    public CarService() throws SQLException {
        this.dbService = new CarRepositoryService();
    }

    public void create(Scanner scanner) throws SQLException {
        System.out.println("Enter car model: ");
        String model = scanner.nextLine().toLowerCase();
        System.out.println("Enter car color: ");
        String color = scanner.nextLine().toLowerCase();
        System.out.println("Enter car max speed: ");
        String maxSpeed = scanner.nextLine().toLowerCase();
        System.out.println("Enter car power: ");
        String power = scanner.nextLine().toLowerCase();
        Car car = new Car(model, color, maxSpeed, power);
        dbService.addCar(car);
        AuditManagement.writeToFile("Car created: " + car);
    }

    public void read(Scanner scanner) throws SQLException {
        System.out.println("Enter car ID: ");
        String carID = scanner.nextLine().toLowerCase();
        try {
            dbService.getCarById(carID);
            AuditManagement.writeToFile("Car read: " + carID);
        } catch (SQLException e) {
            System.out.println("Error reading car: " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void delete(Scanner scanner) throws SQLException {
        System.out.println("Enter car ID: ");
        String carID = scanner.nextLine().toLowerCase();
        try {
            dbService.removeCar(carID);
            AuditManagement.writeToFile("Car deleted: " + carID);
        } catch (SQLException e) {
            System.out.println("Error deleting car: " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void update(Scanner scanner) throws SQLException {
        System.out.println("Enter car model: ");
        String model = scanner.nextLine().toLowerCase();
        System.out.println("Enter car color: ");
        String color = scanner.nextLine().toLowerCase();
        System.out.println("Enter car max speed: ");
        String maxSpeed = scanner.nextLine().toLowerCase();
        System.out.println("Enter car power: ");
        String power = scanner.nextLine().toLowerCase();
        Car car = new Car(model, color, maxSpeed, power);
        try {
            dbService.updateCar(car);
            AuditManagement.writeToFile("Car updated: " + car);
        } catch (SQLException e) {
            System.out.println("Error updating car: " + e.getSQLState() + " " + e.getMessage());
        }
    }
}
