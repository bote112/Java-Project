package dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import model.Championship;
import daoservices.DatabaseConnection;
import model.Race;
import model.Team;


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
    }

    @Override
    public Championship read(String championshipID) throws SQLException {
        return null;
    }

    public void delete(Championship championship) throws SQLException {
    }

    public void update(Championship championship) throws SQLException {
    }
}
