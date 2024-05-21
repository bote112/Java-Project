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
        if (type.equals("asphalt") || type.equals("dirt")) {
            return true;
        }
        System.out.println("Invalid circuit type.");
        return false;
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

        System.out.println("Type: " + circuitAsphalt.getType());
        System.out.println("Turns: " + circuitAsphalt.getTurns());
        System.out.println("Tire: " + circuitAsphalt.getTire());
        //System.out.println(circuitAsphalt);
    }

    private void dirtCircuitInit(Scanner scanner, CircuitDirt circuitDirt) {
        System.out.println("Enter circuit terrain: ");
        String terrain = scanner.nextLine().toLowerCase();
        System.out.println("Enter number of jumps: ");
        int jumps = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter number of obstacles: ");
        int obstacles = Integer.parseInt(scanner.nextLine());

        circuitDirt.setTerrain(terrain);
        circuitDirt.setJumps(jumps);
        circuitDirt.setObstacles(obstacles);
        //System.out.println(circuitDirt);


    }

    private void circuitInit(Scanner scanner, String type) throws SQLException{

        System.out.println("Enter circuit ID: ");
        int circuitID = Integer.parseInt(scanner.nextLine());

        Circuit circuit = setGeneralInfo(scanner);

        if (type.equals("asphalt")) {
            CircuitAsphalt circuitAsphalt = new CircuitAsphalt(circuit);
            asphaltCircuitInit(scanner, circuitAsphalt);
            circuit = circuitAsphalt;
        }
        else if (type.equals("dirt")) {
            CircuitDirt circuitDirt = new CircuitDirt(circuit);
            dirtCircuitInit(scanner, circuitDirt);
            circuit = circuitDirt;
        }
        circuit.setCircuitID(circuitID);
        try {
            dbService.addCircuit(circuit);
            System.out.println("Circuit created: " + circuit);
            AuditManagement.writeToFile("Circuit created: " + circuit);
        } catch (SQLException e) {
            System.out.println("Error creating circuit in init: " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void create(Scanner scanner) {
        System.out.println("Enter circuit ID: ");
        int circuitID = Integer.parseInt(scanner.nextLine());
        if (circuitID < 0) {
            System.out.println("Invalid circuit ID.");
            return;
        }

        System.out.println("Enter circuit type (asphalt/dirt): ");
        String type = scanner.nextLine().toLowerCase();
        if (!typeOfCircuitValidation(type)) {
            System.out.println("Invalid circuit type.");
            return;
        }
        try {
            Circuit circuit = dbService.getCircuit(type, circuitID);
            Circuit circuitAsphaltTest = dbService.getCircuit("asphalt", circuitID);
            Circuit circuitDirtTest = dbService.getCircuit("dirt", circuitID);

            if (circuitAsphaltTest != null || circuitDirtTest != null) {
                System.out.println("Circuit with ID " + circuitID + " already exists.");
                return;
            }
            circuit = new Circuit();
            circuit.setCircuitID(circuitID);
            setGeneralInfoUpdate(scanner, circuit);
            if (type.equals("asphalt")) {
                CircuitAsphalt circuitAsphalt = new CircuitAsphalt(circuit);
                asphaltCircuitInit(scanner, circuitAsphalt);
                dbService.addCircuit(circuitAsphalt);
                AuditManagement.writeToFile("Circuit created: " + circuitAsphalt);
            }
            else if (type.equals("dirt")) {
                CircuitDirt circuitDirt = new CircuitDirt(circuit);
                dirtCircuitInit(scanner, circuitDirt);
                dbService.addCircuit(circuitDirt);
                AuditManagement.writeToFile("Circuit created: " + circuitDirt);
            }
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

    private Circuit setGeneralInfoUpdate(Scanner scanner, Circuit circuit) {
        System.out.println("Enter circuit name: ");
        String name = scanner.nextLine();
        System.out.println("Enter circuit length: ");
        String length = scanner.nextLine();
        System.out.println("Enter circuit location: ");
        String location = scanner.nextLine();
        System.out.println("Enter circuit record: ");
        String record = scanner.nextLine();

        circuit.setName(name);
        circuit.setLength(length);
        circuit.setLocation(location);
        circuit.setRecord(record);

        return circuit;
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
            setGeneralInfoUpdate(scanner, circuit);
            if (type.equals("asphalt")) {
                CircuitAsphalt circuitAsphalt = (CircuitAsphalt) circuit;
                asphaltCircuitInit(scanner, circuitAsphalt);
                dbService.update(circuitAsphalt);
                AuditManagement.writeToFile("Circuit updated: " + circuitAsphalt);
            }
            else if (type.equals("dirt")) {
                CircuitDirt circuitDirt = (CircuitDirt) circuit;
                dirtCircuitInit(scanner, circuitDirt);
                dbService.update(circuitDirt);
                AuditManagement.writeToFile("Circuit updated: " + circuitDirt);
            }
        } catch (SQLException e) {
            System.out.println("Error updating circuit: " + e.getSQLState() + " " + e.getMessage());
        }
    }


}
