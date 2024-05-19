package service;

import model.Team;
import daoservices.TeamRepositoryService;
import model.Driver;
import daoservices.DriverRepositoryService;
import utils.AuditManagement;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TeamService {

    private TeamRepositoryService dbService;
    private DriverRepositoryService driverService;

    public TeamService() throws SQLException {
        dbService = new TeamRepositoryService();
        driverService = new DriverRepositoryService();
    }

    private Team setGeneralInfo(Scanner scanner) throws SQLException {
        System.out.println("Enter team name: ");
        String name = scanner.nextLine().toLowerCase();
        System.out.println("Enter team sponsor: ");
        String sponsor = scanner.nextLine().toLowerCase();
        System.out.println("Enter team trophies: ");
        int trophies = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter team budget: ");
        int budget = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter driver ID: ");
        String driverID = scanner.nextLine();
        Driver driver = driverService.getDriverById(driverID);
        if (driver == null) {
            System.out.println("Driver with ID " + driverID + " does not exist.");
            return null;
        }
        return new Team(name, sponsor, trophies, budget, driver);
    }

    public void create(Scanner scanner) throws SQLException {
        Team team = setGeneralInfo(scanner);
        if (team == null) {
            return;
        }
        dbService.addTeam(team);
        AuditManagement.writeToFile("Team created: " + team);
    }

    public void read(Scanner scanner) throws SQLException {
        System.out.println("Enter team ID: ");
        String teamID = scanner.nextLine();
        try {
            dbService.getTeamById(teamID);
            AuditManagement.writeToFile("Team read: " + teamID);
        } catch (SQLException e) {
            System.out.println("Error reading team: " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void delete(Scanner scanner) throws SQLException {
        System.out.println("Enter team ID: ");
        String teamID = scanner.nextLine();
        try {
            dbService.removeTeam(teamID);
            AuditManagement.writeToFile("Team deleted: " + teamID);
        } catch (SQLException e) {
            System.out.println("Error deleting team: " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void update(Scanner scanner) throws SQLException {
        Team team = setGeneralInfo(scanner);
        if (team == null) {
            return;
        }
        dbService.updateTeam(team);
        AuditManagement.writeToFile("Team updated: " + team);
    }

}
