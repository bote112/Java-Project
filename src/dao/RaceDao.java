package dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import model.Race;
import model.circuit.*;
import model.Team;
import daoservices.DatabaseConnection;

public class RaceDao implements DaoInterface <Race> {
    private static RaceDao raceDao;

    private Connection connection = DatabaseConnection.getConnection();

    public RaceDao() throws SQLException {
    }

    public static RaceDao getInstance() throws SQLException {
        if (raceDao == null) {
            raceDao = new RaceDao();
        }
        return raceDao;
    }

    @Override
    public void add(Race race) throws SQLException {
        String sql = "INSERT INTO schema.race(circuit, laps, winner) VALUES (?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, race.getCircuit().getCircuitID());
            statement.setInt(2, race.getLaps());
            statement.setInt(3, race.getWinner().getTeamID());
            statement.executeUpdate();
        }

        for(Team team : race.getTeams()) {
            sql = "INSERT INTO schema.race_team (raceID, teamID) VALUES (?, ?);";
            try (PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setInt(1, race.getRaceID());
                statement.setInt(2, team.getTeamID());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public Race read(String raceID) throws SQLException {
        String sql = "SELECT * FROM schema.race WHERE raceID = ?";
        //pentru a putea accesa ulterior arrayul de echipe, trebuie initializat race aici
        Race race = null;
        ResultSet resultSet = null;

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, raceID);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                race = new Race();
                race.setRaceID(resultSet.getInt("raceID"));
//pentru ca nu exista circuitDao, nu poate folosi getInstance
                //race.setCircuit(CircuitDao.getInstance().read(String.valueOf(resultSet.getInt("circuit"))));
                race.setLaps(resultSet.getInt("laps"));

            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        if (race != null) {
            sql = "SELECT teamID FROM schema.race_team WHERE raceID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, raceID);
                resultSet = statement.executeQuery();
                List<Team> teams = new ArrayList<>();
                while (resultSet.next()) {
                    teams.add(TeamDao.getInstance().read(String.valueOf(resultSet.getInt("teamID"))));
                }
                race.setTeams(teams.toArray(new Team[0]));
            }
        }
        return null;
    }

    @Override
    public void delete(Race race) throws SQLException {
        String sql = "DELETE FROM schema.race WHERE raceID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, race.getRaceID());
            statement.executeUpdate();
        }

        sql = "DELETE FROM schema.race_team WHERE raceID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, race.getRaceID());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Race race) throws SQLException {
        String sql = "UPDATE schema.race SET circuit = ?, laps = ?, winner = ? WHERE raceID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, race.getCircuit().getCircuitID());
            statement.setInt(2, race.getLaps());
            statement.setInt(3, race.getRaceID());
            statement.executeUpdate();
        }

        sql = "DELETE FROM schema.race_team WHERE raceID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, race.getRaceID());
            statement.executeUpdate();
        }

        for (Team team : race.getTeams()) {
            sql = "INSERT INTO schema.race_team (raceID, teamID) VALUES (?, ?);";
            try (PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setInt(1, race.getRaceID());
                statement.setInt(2, team.getTeamID());
                statement.executeUpdate();
            }
        }
    }
}
