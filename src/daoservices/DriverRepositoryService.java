package daoservices;

import dao.DriverDao;
import model.Driver;
import model.Car;
import dao.CarDao;

import java.sql.*;

public class DriverRepositoryService  {
    private DriverDao driverDao = DriverDao.getInstance();
    private CarDao carDao = CarDao.getInstance();

    public DriverRepositoryService() throws SQLException {}

    public Driver getDriverById(String driverID) throws SQLException {
        Driver driver = driverDao.read(driverID);
        if (driver != null) {
            if(driver.getCar() == null) {
                System.out.println("No car found for the given driver.");
                return driver;
            }
            else {
                String carIDS = String.valueOf(driver.getCar().getCarID());
                Car car = carDao.read(carIDS);
                driver.setCar(car);
                System.out.println(driver);
            }
        } else {
            System.out.println("No driver found with the given ID.");
        }
        return driver;
    }

    public void addDriver(Driver driver) throws SQLException {
        if (driver != null) {
            driverDao.add(driver);
        }
    }

    public void removeDriver(String driverID) throws SQLException {
        Driver driver = getDriverById(driverID);
        if (driver == null) return;

        driverDao.delete(driver);
        System.out.println("Removed " + driver);
    }

    public void updateDriver(Driver driver) throws SQLException {
        if (driver != null) {
            driverDao.update(driver);
        }
    }
}
