package dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import model.Championship;
import service.DatabaseConnection;
import model.Race;


public class ChampionshipDao implements DaoInterface<Championship> {

    private static ChampionshipDao championshipDao;
    private Connection connection = DatabaseConnection.getConnection();

    public ChampionshipDao() throws SQLException {
    }

    public static ChampionshipDao getInstance() throws SQLException {
        if (championshipDao == null) {
            championshipDao = new ChampionshipDao();
        }
        return championshipDao;
    }

    @Override
    public void add(Championship championship) throws SQLException {
        String sql = "INSERT INTO schema.championship (name, year, champion) VALUES (?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, championship.getName());
            statement.setInt(2, championship.getYear());
            statement.setInt(3, championship.getChampion().getTeamID());
            statement.executeUpdate();
        }

        for (Race race : championship.getRaces()) {
            sql = "INSERT INTO schema.championship_race (championshipID, raceID) VALUES (?, ?);";
            try (PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setInt(1, championship.getChampionshipID());
                statement.setInt(2, race.getRaceID());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public Championship read(String championshipID) throws SQLException {
        String sql = "SELECT * FROM schema.championship WHERE championshipID = ?";
        Championship championship = null;
        ResultSet resultSet = null;

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, championshipID);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                championship = new Championship();
                championship.setChampionshipID(resultSet.getInt("championshipID"));
                championship.setName(resultSet.getString("name"));
                championship.setYear(resultSet.getInt("year"));
                championship.setChampion(TeamDao.getInstance().read(String.valueOf(resultSet.getInt("champion"))));
            }
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
            }

        if (championship != null) {
            sql = "SELECT raceID FROM schema.championship WHERE championshipID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, championshipID);
                resultSet = statement.executeQuery();
                List<Race> races = new ArrayList<>();
                while (resultSet.next()) {
                    races.add(RaceDao.getInstance().read(String.valueOf(resultSet.getInt("raceID"))));
                }
                championship.setRaces(races.toArray(new Race[0]));
            }
        }
        return null;
    }

    public void delete(Championship championship) throws SQLException {
        String sql = "DELETE FROM schema.championship WHERE championshipID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, championship.getChampionshipID());
            statement.executeUpdate();
        }

        sql = "DELETE FROM schema.championship_race WHERE championshipID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, championship.getChampionshipID());
            statement.executeUpdate();
        }
    }

    public void update(Championship championship) throws SQLException {
        String sql = "UPDATE schema.championship SET name = ?, year = ?, champion = ? WHERE championshipID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, championship.getName());
            statement.setInt(2, championship.getYear());
            statement.setInt(3, championship.getChampion().getTeamID());
            statement.setInt(4, championship.getChampionshipID());
            statement.executeUpdate();
        }

        sql = "DELETE FROM schema.championship_race WHERE championshipID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, championship.getChampionshipID());
            statement.executeUpdate();
        }
        for (Race race : championship.getRaces()) {
            sql = "INSERT INTO schema.championship_race (championshipID, raceID) VALUES (?, ?);";
            try (PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setInt(1, championship.getChampionshipID());
                statement.setInt(2, race.getRaceID());
                statement.executeUpdate();
            }
        }
    }
}
