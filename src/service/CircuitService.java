package service;

import model.circuit.*;
import utils.AuditManagement;
import daoservices.CircuitRepositoryService;

import java.sql.*;
import java.util.Scanner;

public class CircuitService {

    private CircuitRepositoryService dbService;

    public CircuitService() throws SQLException {
        this.dbService = new CircuitRepositoryService();
    }

    public boolean typeOfCircuitValidation(String type) {
        return type.equals("asphalt") || type.equals("dirt");
    }

    private Circuit setGeneralInfo(Scanner scanner) {
        System.out.println("Enter circuit name: ");
        String name = scanner.nextLine().toLowerCase();
        System.out.println("Enter circuit length: ");
        String length = scanner.nextLine().toLowerCase();
        System.out.println("Enter circuit location: ");
        String location = scanner.nextLine().toLowerCase();
        System.out.println("Enter circuit record: ");
        String record = scanner.nextLine().toLowerCase();
        return new Circuit(name, length, location, record);
    }

    private void asphaltCircuitInit(Scanner scanner, CircuitAsphalt circuitAsphalt) {
        System.out.println("Enter circuit type: ");
        String type = scanner.nextLine().toLowerCase();
        System.out.println("Enter number of turns: ");
        int turns = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter tire type: ");
        String tire = scanner.nextLine().toLowerCase();

        circuitAsphalt.setType(type);
        circuitAsphalt.setTurns(turns);
        circuitAsphalt.setTire(tire);
    }

    private void dirtCircuitInit(Scanner scanner, CircuitDirt circuitDirt) {
        System.out.println("Enter circuit terrain: ");
        String terrain = scanner.nextLine().toLowerCase();
        System.out.println("Enter number of jumps: ");
        int jumps = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter dirt type: ");
        int obstacles = Integer.parseInt(scanner.nextLine());

        circuitDirt.setTerrain(terrain);
        circuitDirt.setJumps(jumps);
        circuitDirt.setObstacles(obstacles);
    }

    private void circuitInit(Scanner scanner, String type) throws SQLException{
        Circuit circuit = setGeneralInfo(scanner);
        if (type.equals("asphalt")) {
            CircuitAsphalt circuitAsphalt = new CircuitAsphalt();
            circuitAsphalt.setName(circuit.getName());
            circuitAsphalt.setLength(circuit.getLength());
            circuitAsphalt.setLocation(circuit.getLocation());
            circuitAsphalt.setRecord(circuit.getRecord());
            asphaltCircuitInit(scanner, circuitAsphalt);
            dbService.addCircuit(circuitAsphalt);
            AuditManagement.writeToFile("Circuit created: " + circuitAsphalt);
        }
        else if (type.equals("dirt")) {
            CircuitDirt circuitDirt = new CircuitDirt();
            circuitDirt.setName(circuit.getName());
            circuitDirt.setLength(circuit.getLength());
            circuitDirt.setLocation(circuit.getLocation());
            circuitDirt.setRecord(circuit.getRecord());
            dirtCircuitInit(scanner, circuitDirt);
            dbService.addCircuit(circuitDirt);
            AuditManagement.writeToFile("Circuit created: " + circuitDirt);
        }
        else {
            System.out.println("Invalid circuit type.");
        }
    }

    public void create(Scanner scanner) {
        System.out.println("Enter circuit type (asphalt/dirt): ");
        String type = scanner.nextLine().toLowerCase();
        if (!typeOfCircuitValidation(type)) {
            System.out.println("Invalid circuit type.");
            return;
        }
        try {
            circuitInit(scanner, type);
        } catch (SQLException e) {
            System.out.println("Error creating circuit: " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void read(Scanner scanner) {
        System.out.println("Enter circuit ID: ");
        String circuitID = scanner.nextLine().toLowerCase();
        try {
            dbService.getCircuitAsphaltById(circuitID);
            AuditManagement.writeToFile("Circuit read: " + circuitID);
        } catch (SQLException e) {
            System.out.println("Error reading asphalt circuit: " + e.getSQLState() + " " + e.getMessage());
        }

        try {
            dbService.getCircuitDirtById(circuitID);
            AuditManagement.writeToFile("Circuit read: " + circuitID);
        } catch (SQLException e) {
            System.out.println("Error reading dirt circuit: " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void delete(Scanner scanner) {
        System.out.println("Enter circuit ID: ");
        int circuitID = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter circuit type (asphalt/dirt): ");
        String type = scanner.nextLine().toLowerCase();
        if (!typeOfCircuitValidation(type)) {
            System.out.println("Invalid circuit type.");
            return;
        }
        try {
            dbService.removeCircuit(type, circuitID);
            AuditManagement.writeToFile("Circuit deleted: " + circuitID);
        } catch (SQLException e) {
            System.out.println("Error deleting circuit: " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void update(Scanner scanner) {
        System.out.println("Enter circuit ID: ");
        int circuitID = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter circuit type (asphalt/dirt): ");
        String type = scanner.nextLine().toLowerCase();
        if (!typeOfCircuitValidation(type)) {
            System.out.println("Invalid circuit type.");
            return;
        }
        try {
            Circuit circuit = dbService.getCircuit(type, circuitID);
            if (circuit == null) {
                System.out.println("Circuit with ID " + circuitID + " does not exist.");
                return;
            }
            circuitInit(scanner, type);
            dbService.update(circuit);
            AuditManagement.writeToFile("Circuit updated: " + circuit);
        } catch (SQLException e) {
            System.out.println("Error updating circuit: " + e.getSQLState() + " " + e.getMessage());
        }
    }


}
