package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Team;
import model.Driver;
import daoservices.DatabaseConnection;

public class TeamDao implements DaoInterface<Team>{

    private static TeamDao teamDao;
    private Connection connection = DatabaseConnection.getConnection();

    public TeamDao() throws  SQLException {
    }

    public static TeamDao getInstance() throws SQLException {
        if (teamDao == null) {
            teamDao = new TeamDao();
        }
        return teamDao;
    }

    @Override
    public void add(Team team) throws SQLException {
        String sql = "INSERT INTO schema.team (name, sponsor, trophies, budget, driver) VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, team.getName());
            statement.setString(2, team.getSponsor());
            statement.setInt(3, team.getTrophies());
            statement.setInt(4, team.getBudget());
            statement.setInt(5, team.getDriver().getDriverID());
            statement.executeUpdate();
        }
    }

    @Override
    public Team read(String teamID) throws SQLException {
        String sql = "SELECT * FROM schema.team WHERE teamID = ?";
        ResultSet resultSet = null;

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, teamID);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Team team = new Team();
                team.setName(resultSet.getString("name"));
                team.setSponsor(resultSet.getString("sponsor"));
                team.setTrophies(resultSet.getInt("trophies"));
                team.setBudget(resultSet.getInt("budget"));
                return team;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;
    }

    @Override
    public void delete(Team team) throws SQLException {
        String sql = "DELETE FROM schema.team WHERE teamID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, team.getTeamID());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Team team) throws SQLException {
        String sql = "UPDATE schema.team SET name = ?, sponsor = ?, trophies = ?, budget = ?, driver = ? WHERE teamID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, team.getName());
            statement.setString(2, team.getSponsor());
            statement.setInt(3, team.getTrophies());
            statement.setInt(4, team.getBudget());
            statement.setInt(5, team.getDriver().getDriverID());
            statement.setInt(6, team.getTeamID());
            statement.executeUpdate();
        }
    }
}
