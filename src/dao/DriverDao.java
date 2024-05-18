package dao;

import java.sql.*;

import model.Driver;
import service.DatabaseConnection;


public class DriverDao implements DaoInterface<Driver> {

    private static DriverDao driverDao;
    private Connection connection = DatabaseConnection.getConnection();
    public DriverDao()  throws  SQLException {
    }

    public static DriverDao getInstance() throws SQLException {
        if (driverDao == null) {
            driverDao = new DriverDao();
        }
        return driverDao;
    }

    @Override
    public void add(Driver driver) throws SQLException {
        String sql = "INSERT INTO schema.driver (carID, name, age, country) VALUES (?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, driver.getCar().getCarID());
            statement.setString(2, driver.getName());
            statement.setInt(3, driver.getAge());
            statement.setString(4, driver.getCountry());
            statement.executeUpdate();
        }
    }

    @Override
    public Driver read(String driverID) throws SQLException {
        String sql = "SELECT * FROM schema.driver WHERE driverID = ?";
        ResultSet resultSet = null;

        try ( PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, driverID);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Driver driver = new Driver();
                driver.setName(resultSet.getString("name"));
                driver.setAge(resultSet.getInt("age"));
                driver.setCountry(resultSet.getString("country"));
                return driver;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;
    }

    @Override
    public void delete(Driver driver) throws SQLException {
        String sql = "DELETE FROM schema.driver WHERE driverID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, driver.getDriverID());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Driver driver) throws SQLException {
        String sql = "UPDATE schema.driver SET carID = ?, name = ?, age = ?, country = ? WHERE driverID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, driver.getCar().getCarID());
            statement.setString(2, driver.getName());
            statement.setInt(3, driver.getAge());
            statement.setString(4, driver.getCountry());
            statement.setInt(5, driver.getDriverID());
            statement.executeUpdate();
        }
    }
}
