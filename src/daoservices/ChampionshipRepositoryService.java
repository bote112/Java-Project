package daoservices;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import dao.ChampionshipDao;
import model.Championship;

public class ChampionshipRepositoryService {
    private ChampionshipDao championshipDao = ChampionshipDao.getInstance();

    public ChampionshipRepositoryService() throws SQLException {}

    public Championship getChampionshipById(String championshipID) throws SQLException {
        Championship championship = championshipDao.read(championshipID);
        if (championship != null) {
            System.out.println(championship);
        } else {
            System.out.println("No championship found with the given ID.");
        }
        return championship;
    }

    public void addChampionship(Championship championship) throws SQLException {
        if (championship != null) {
            championshipDao.add(championship);
        }
    }

    public void removeChampionship(String championshipID) throws SQLException {
        Championship championship = getChampionshipById(championshipID);
        if (championship == null) return;

        championshipDao.delete(championship);
        System.out.println("Removed " + championship);
    }

    public void updateChampionship(Championship championship) throws SQLException {
        if (championship != null) {
            championshipDao.update(championship);
        }
    }

}
