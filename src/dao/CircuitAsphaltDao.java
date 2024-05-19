package dao;

import java.sql.*;

import service.DatabaseConnection;
import model.circuit.*;

public class CircuitAsphaltDao implements DaoInterface<CircuitAsphalt> {

    private static CircuitAsphaltDao circuitAsphaltDao;
    private Connection connection = DatabaseConnection.getConnection();

    public CircuitAsphaltDao()  throws  SQLException {
    }

    public static CircuitAsphaltDao getInstance() throws SQLException {
        if (circuitAsphaltDao == null) {
            circuitAsphaltDao = new CircuitAsphaltDao();
        }
        return circuitAsphaltDao;
    }

    @Override
    public void add(CircuitAsphalt circuitAsphalt) throws SQLException {
        String sql = "INSERT INTO schema.circuit_asphalt (type, turns, tire) VALUES (?, ?, ?);";
        String sql2 = "INSERT INTO schema.circuit (name, length, location, record) VALUES (?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql2);) {
            statement.setString(1, circuitAsphalt.getName());
            statement.setString(2, circuitAsphalt.getLength());
            statement.setString(3, circuitAsphalt.getLocation());
            statement.setString(4, circuitAsphalt.getRecord());

            statement.executeUpdate();

        }
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, circuitAsphalt.getType());
            statement.setInt(2, circuitAsphalt.getTurns());
            statement.setString(3, circuitAsphalt.getTire());
            statement.executeUpdate();
        }
    }

    @Override
    public CircuitAsphalt read(String circuitID) throws SQLException {
        String sql = "SELECT * FROM schema.circuit_asphalt WHERE circuitID = ?";
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
                    CircuitAsphalt circuitAsphalt = new CircuitAsphalt();
                    circuitAsphalt.setCircuitID(resultSet.getInt("circuitID"));
                    circuitAsphalt.setName(name);
                    circuitAsphalt.setLength(length);
                    circuitAsphalt.setLocation(location);
                    circuitAsphalt.setRecord(record);
                    circuitAsphalt.setType(resultSet.getString("type"));
                    circuitAsphalt.setTurns(resultSet.getInt("turns"));
                    circuitAsphalt.setTire(resultSet.getString("tire"));
                    return circuitAsphalt;
                }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;
    }

    @Override
    public void delete(CircuitAsphalt circuitAsphalt) throws SQLException {
        String sql = "DELETE FROM schema.circuit_asphalt WHERE circuitID = ?";
        String sql2 = "DELETE FROM schema.circuit WHERE circuitID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, circuitAsphalt.getCircuitID());
            statement.executeUpdate();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql2);) {
            statement.setInt(1, circuitAsphalt.getCircuitID());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(CircuitAsphalt circuitAsphalt) throws SQLException {
        String sql = "UPDATE schema.circuit_asphalt SET type = ?, turns = ?, tire = ? WHERE circuitID = ?";
        String sql2 = "UPDATE schema.circuit SET name = ?, length = ?, location = ?, record = ? WHERE circuitID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql2);) {
                statement.setString(1, circuitAsphalt.getName());
                statement.setString(2, circuitAsphalt.getLength());
                statement.setString(3, circuitAsphalt.getLocation());
                statement.setString(4, circuitAsphalt.getRecord());
                statement.setInt(5, circuitAsphalt.getCircuitID());
                statement.executeUpdate();
            }
            try (PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, circuitAsphalt.getType());
                statement.setInt(2, circuitAsphalt.getTurns());
                statement.setString(3, circuitAsphalt.getTire());
                statement.setInt(4, circuitAsphalt.getCircuitID());
                statement.executeUpdate();
            }
    }
}
