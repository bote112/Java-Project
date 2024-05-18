package daoservices;

import java.sql.*;

import model.circuit.*;
import dao.CircuitAsphaltDao;
import dao.CircuitDirtDao;

public class CircuitRepositoryService {
    private CircuitAsphaltDao circuitAsphaltDao;
    private CircuitDirtDao circuitDirtDao;

    public CircuitRepositoryService() throws SQLException {}

    public CircuitAsphalt getCircuitAsphaltById(String circuitID) throws SQLException {
        CircuitAsphalt circuitAsphalt = circuitAsphaltDao.read(circuitID);
        if (circuitAsphalt != null) {
            System.out.println(circuitAsphalt);
        } else {
            System.out.println("No asphalt circuit found with the given ID.");
        }
        return circuitAsphalt;
    }

    public CircuitDirt getCircuitDirtById(String circuitID) throws SQLException {
        CircuitDirt circuitDirt = circuitDirtDao.read(circuitID);
        if (circuitDirt != null) {
            System.out.println(circuitDirt);
        } else {
            System.out.println("No dirt circuit found with the given ID.");
        }
        return circuitDirt;
    }

    public void addCircuit(Circuit circuit) throws SQLException {
        if (circuit != null) {
            switch (circuit) {
                case CircuitAsphalt circuitAsphalt -> circuitAsphaltDao.add(circuitAsphalt);
                case CircuitDirt circuitDirt -> circuitDirtDao.add(circuitDirt);
                default -> throw new IllegalStateException("Unexpected value: " + circuit);
            }
        }
    }

    public void removeCircuit(String typeOfCircuit, int circuitID) throws SQLException {
        Circuit circuit = getCircuit(typeOfCircuit, circuitID);
        if (circuit == null) return;

        switch (circuit) {
            case CircuitAsphalt circuitAsphalt -> circuitAsphaltDao.delete(circuitAsphalt);
            case CircuitDirt circuitDirt -> circuitDirtDao.delete(circuitDirt);
            default -> throw new IllegalStateException("Unexpected value: " + circuit);
        }

        System.out.println("Removed " + circuit);
    }

    public Circuit getCircuit(String typeOfCircuit, int circuitID) {
        Circuit circuit = null;
        try {
            if(typeOfCircuit.equals("asphalt")) {
                circuit = getCircuitAsphaltById(String.valueOf(circuitID));
            } else if(typeOfCircuit.equals("dirt")) {
                circuit = getCircuitDirtById(String.valueOf(circuitID));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getSQLState() + " " + e.getMessage());
        }
        return circuit;
    }

    public void update(Circuit circuit) throws SQLException {
        if (circuit != null) {
            switch (circuit) {
                case CircuitAsphalt circuitAsphalt -> circuitAsphaltDao.update(circuitAsphalt);
                case CircuitDirt circuitDirt -> circuitDirtDao.update(circuitDirt);
                default -> throw new IllegalStateException("Unexpected value: " + circuit);
            }
        }
    }
}
