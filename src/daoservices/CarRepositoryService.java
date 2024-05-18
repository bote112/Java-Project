package daoservices;

import dao.CarDao;
import model.Car;

import java.sql.*;

public class CarRepositoryService {
    private CarDao carDao = CarDao.getInstance();

    public CarRepositoryService() throws SQLException {}

    public Car getCarById(String carID) throws SQLException {
        Car car = carDao.read(carID);
        if (car != null) {
            System.out.println(car);
        } else {
            System.out.println("No car found with the given ID.");
        }
        return car;
    }

    public void addCar(Car car) throws SQLException {
        if (car != null) {
            carDao.add(car);
        }
    }

    public void removeCar(String carID) throws SQLException {
        Car car = getCarById(carID);
        if (car == null) return;

        carDao.delete(car);
        System.out.println("Removed " + car);
    }

    public void updateCar(Car car) throws SQLException {
        if (car != null) {
            carDao.update(car);
        }
    }
}
