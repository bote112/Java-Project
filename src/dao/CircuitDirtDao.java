package dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import model.circuit.*;
import daoservices.DatabaseConnection;

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
        String sql = "INSERT INTO schema.circuit_dirt (terrain, jumps, obstacles) VALUES (?, ?, ?);";
        String sql2 = "INSERT INTO schema.circuit (name, length, location, record) VALUES (?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql2);) {
            statement.setString(1, circuitDirt.getName());
            statement.setString(2, circuitDirt.getLength());
            statement.setString(3, circuitDirt.getLocation());
            statement.setString(4, circuitDirt.getRecord());

            statement.executeUpdate();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, circuitDirt.getTerrain());
            statement.setInt(2, circuitDirt.getJumps());
            statement.setInt(3, circuitDirt.getObstacles());
            statement.executeUpdate();
        }
    }

    @Override
    public CircuitDirt read(String circuitID) throws SQLException {
        String sql = "SELECT * FROM schema.circuit_dirt WHERE circuitID = ?";
        String sql2 = "SELECT * FROM schema.circuit WHERE circuitID = ?";
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
        String sql = "DELETE FROM schema.circuit_dirt WHERE circuitID = ?";
        String sql2 = "DELETE FROM schema.circuit WHERE circuitID = ?";

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
        String sql = "UPDATE schema.circuit_dirt SET terrain = ?, jumps = ?, obstacles = ? WHERE circuitID = ?";
        String sql2 = "UPDATE schema.circuit SET name = ?, length = ?, location = ?, record = ? WHERE circuitID = ?";

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
