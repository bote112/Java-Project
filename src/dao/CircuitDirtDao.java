package dao;

import java.sql.*;

import model.circuit.*;
import service.DatabaseConnection;

public class CircuitDirtDao implements DaoInterface <CircuitDirt> {
    private static CircuitDirtDao circuitDirtDao;

    private Connection connection = DatabaseConnection.getConnection();

    public CircuitDirtDao() throws SQLException {
    }

    public static CircuitDirtDao getInstance() throws SQLException {
        if (circuitDirtDao == null) {
            circuitDirtDao = new CircuitDirtDao();
        }
        return circuitDirtDao;
    }

    @Override
    public void add(CircuitDirt circuitDirt) throws SQLException {
        String sql = "INSERT INTO racing.circuit_dirt (circuitID, terrain, jumps, obstacles) VALUES (?, ?, ?, ?);";
        String sql2 = "INSERT INTO racing.circuit (name, length, location, record) VALUES (?, ?, ?, ?);";

        String sql3 = "select circuitID from racing.circuit order by circuitID desc limit 1";
        int circuitID = 0;

        try (PreparedStatement statement = connection.prepareStatement(sql2);) {
            statement.setString(1, circuitDirt.getName());
            statement.setString(2, circuitDirt.getLength());
            statement.setString(3, circuitDirt.getLocation());
            statement.setString(4, circuitDirt.getRecord());

            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(sql3);) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                circuitID = resultSet.getInt("circuitID");
            }
        }

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, circuitID);
            statement.setString(2, circuitDirt.getTerrain());
            statement.setInt(3, circuitDirt.getJumps());
            statement.setInt(4, circuitDirt.getObstacles());
            statement.executeUpdate();
        }
    }

    @Override
    public CircuitDirt read(String circuitID) throws SQLException {
        String sql = "SELECT * FROM racing.circuit_dirt WHERE circuitID = ?";
        String sql2 = "SELECT * FROM racing.circuit WHERE circuitID = ?";
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        String name = null;
        String length = null;
        String location = null;
        String record = null;

        try (PreparedStatement statement = connection.prepareStatement(sql2);) {
            statement.setString(1, circuitID);
            resultSet2 = statement.executeQuery();

            while (resultSet2.next()) {
                name = resultSet2.getString("name");
                length = resultSet2.getString("length");
                location = resultSet2.getString("location");
                record = resultSet2.getString("record");
            }
        } finally {
            if (resultSet2 != null) {
                resultSet2.close();
            }
        }
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, circuitID);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CircuitDirt circuitDirt = new CircuitDirt();
                circuitDirt.setCircuitID(resultSet.getInt("circuitID"));
                circuitDirt.setName(name);
                circuitDirt.setLength(length);
                circuitDirt.setLocation(location);
                circuitDirt.setRecord(record);
                circuitDirt.setTerrain(resultSet.getString("terrain"));
                circuitDirt.setJumps(resultSet.getInt("jumps"));
                circuitDirt.setObstacles(resultSet.getInt("obstacles"));
                return circuitDirt;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;
    }

    @Override
    public void delete(CircuitDirt circuitDirt) throws SQLException {
        String sql = "DELETE FROM racing.circuit_dirt WHERE circuitID = ?";
        String sql2 = "DELETE FROM racing.circuit WHERE circuitID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, circuitDirt.getCircuitID());
            statement.executeUpdate();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql2);) {
            statement.setInt(1, circuitDirt.getCircuitID());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(CircuitDirt circuitDirt) throws SQLException {
        String sql = "UPDATE racing.circuit_dirt SET terrain = ?, jumps = ?, obstacles = ? WHERE circuitID = ?";
        String sql2 = "UPDATE racing.circuit SET name = ?, length = ?, location = ?, record = ? WHERE circuitID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql2);) {
            statement.setString(1, circuitDirt.getName());
            statement.setString(2, circuitDirt.getLength());
            statement.setString(3, circuitDirt.getLocation());
            statement.setString(4, circuitDirt.getRecord());
            statement.setInt(5, circuitDirt.getCircuitID());
            statement.executeUpdate();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, circuitDirt.getTerrain());
            statement.setInt(2, circuitDirt.getJumps());
            statement.setInt(3, circuitDirt.getObstacles());
            statement.setInt(4, circuitDirt.getCircuitID());
            statement.executeUpdate();
        }
    }
}
