package daoservices;

import  dao.TeamDao;
import model.Team;

import dao.DriverDao;
import model.Driver;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class TeamRepositoryService {
    private TeamDao teamDao = TeamDao.getInstance();
    private DriverDao driverDao = DriverDao.getInstance();

    public TeamRepositoryService() throws SQLException {}

    public Team getTeamById(String teamID) throws SQLException {
        Team team = teamDao.read(teamID);
        if (team != null) {
            if (team.getDriver() == null) {
                System.out.println("No driver found for the given team.");
                return team;
            } else {
                String driverID = String.valueOf(team.getDriver().getDriverID());
                Driver driver = driverDao.read(driverID);
                team.setDriver(driver);
                System.out.println(team);
            }
        } else {
            System.out.println("No team found with the given ID.");
        }
        return team;
    }

    public List <Team> getAllTeams() throws SQLException {
        List <Team> teams = teamDao.getAll();
        if (teams != null) {
            for (Team team : teams) {
                System.out.println(team);
            }
        } else {
            throw new SQLException("No teams found.");
        }
        return teams;
    }

    public void addTeam(Team team) throws SQLException {
        if (team != null) {
            teamDao.add(team);
        }
    }

    public void removeTeam(String teamID) throws SQLException {
        Team team = getTeamById(teamID);
        if (team == null) return;

        teamDao.delete(team);
        System.out.println("Removed" + team);
    }

    public void updateTeam(Team team) throws SQLException {
        if (team != null) {
            teamDao.update(team);
        }
    }

}
