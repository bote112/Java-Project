package service;

import daoservices.DriverRepositoryService;
import daoservices.CarRepositoryService;
import model.Car;
import model.Driver;
import utils.AuditManagement;

import java.sql.SQLException;
import java.util.Scanner;

public class DriverService {
    private DriverRepositoryService dbService;
    private CarRepositoryService carService;

    public DriverService() throws SQLException {
        this.dbService = new DriverRepositoryService();
        this.carService = new CarRepositoryService();
    }

    public void create(Scanner scanner) throws SQLException {
        System.out.println("Enter driver name: ");
        String name = scanner.nextLine().toLowerCase();
        System.out.println("Enter driver age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter driver country: ");
        String country = scanner.nextLine().toLowerCase();
        System.out.println("Enter car ID: ");
        String carID = scanner.nextLine();
        Car car = carService.getCarById(carID);
        if (car == null) {
            System.out.println("Car with ID " + carID + " does not exist.");
            return;
        }
        Driver driver = new Driver(car, name, age, country);
        dbService.addDriver(driver);
        AuditManagement.writeToFile("Driver created: " + driver);
    }

    public void read(Scanner scanner) throws SQLException {
        System.out.println("Enter driver ID: ");
        String driverID = scanner.nextLine();
        try {
            dbService.getDriverById(driverID);
            AuditManagement.writeToFile("Driver read: " + driverID);
        } catch (SQLException e) {
            System.out.println("Error reading driver: " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void delete(Scanner scanner) throws SQLException {
        System.out.println("Enter driver ID: ");
        String driverID = scanner.nextLine();
        try {
            dbService.removeDriver(driverID);
            AuditManagement.writeToFile("Driver deleted: " + driverID);
        } catch (SQLException e) {
            System.out.println("Error deleting driver: " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void update(Scanner scanner) throws SQLException {
        System.out.println("Enter driver name: ");
        String name = scanner.nextLine().toLowerCase();
        System.out.println("Enter driver age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter driver country: ");
        String country = scanner.nextLine().toLowerCase();
        System.out.println("Enter car ID: ");
        String carID = scanner.nextLine();
        Car car = carService.getCarById(carID);
        if (car == null) {
            System.out.println("Car with ID " + carID + " does not exist.");
            return;
        }
        Driver driver = new Driver(car, name, age, country);
        try {
            dbService.updateDriver(driver);
            AuditManagement.writeToFile("Driver updated: " + driver);
        } catch (SQLException e) {
            System.out.println("Error updating driver: " + e.getSQLState() + " " + e.getMessage());
        }
    }
}