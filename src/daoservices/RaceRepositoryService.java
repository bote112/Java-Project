package daoservices;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import dao.RaceDao;
import model.Race;

public class RaceRepositoryService {
    private RaceDao raceDao = RaceDao.getInstance();

    public RaceRepositoryService() throws SQLException {}

    public Race getRaceById(String raceID) throws SQLException {
        Race race = raceDao.read(raceID);
        if (race != null) {
            System.out.println(race);
        } else {
            System.out.println("No race found with the given ID.");
        }
        return race;
    }

    public List<Race> getAllRaces() throws SQLException {
        List<Race> races = raceDao.getAll();
        if (races != null) {
            for (Race race : races) {
                System.out.println(race);
            }
        } else {
            throw new SQLException("No races found.");
        }
        return races;
    }

    public void addRace(Race race) throws SQLException {
        if (race != null) {
            raceDao.add(race);
        }
    }

    public void removeRace(String raceID) throws SQLException {
        Race race = getRaceById(raceID);
        if(race == null) return;

        raceDao.delete(race);
        System.out.println("Removed " + race);
    }

    public void updateRace(Race race) throws SQLException {
        if (race != null) {
            raceDao.update(race);
        }
    }
}
