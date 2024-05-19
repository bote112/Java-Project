package dao;

import java.sql.*;

import model.Car;
import service.DatabaseConnection;

public class CarDao implements DaoInterface<Car> {

    private static CarDao carDao;
    private Connection connection = DatabaseConnection.getConnection();
    public CarDao()  throws  SQLException {
    }

    public static CarDao getInstance() throws SQLException {
        if (carDao == null) {
            carDao = new CarDao();
        }
        return carDao;
    }
    @Override
    public void add(Car car) throws SQLException {
        String sql = "INSERT INTO schema.car (model, color, maxSpeed, power) VALUES (?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, String.valueOf(car.getModel()));
            statement.setString(2, String.valueOf(car.getColor()));
            statement.setString(3, car.getMaxSpeed());
            statement.setString(4, car.getPower());
            statement.executeUpdate();
        }
    }
    @Override
    public Car read(String carID) throws SQLException {
        String sql = "SELECT * FROM schema.car WHERE carID = ?";
        ResultSet resultSet = null;

        try ( PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, carID);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Car car = new Car();
                car.setModel(resultSet.getString("model"));
                car.setColor(resultSet.getString("color"));
                car.setMaxSpeed(resultSet.getString("maxSpeed"));
                car.setPower(resultSet.getString("power"));
                return car;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;
    }

    @Override
    public void delete(Car car) throws SQLException {
        String sql = "DELETE FROM schema.car WHERE carID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, car.getCarID());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Car car) throws SQLException {
        String sql = "UPDATE schema.car SET model = ?, color = ?, maxSpeed = ?, power = ? WHERE carID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, car.getModel());
            statement.setString(2, car.getColor());
            statement.setString(3, car.getMaxSpeed());
            statement.setString(4, car.getPower());
            statement.setInt(5, car.getCarID());
            statement.executeUpdate();
        }
    }
}
