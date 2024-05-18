package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.circuit.*;
import dao.CircuitAsphaltDao;
import dao.CircuitDirtDao;

public class CircuitRepositoryService {
    private CircuitAsphaltDao circuitAsphaltDao;
    private CircuitDirtDao circuitDirtDao;

    public CircuitRepositoryService() throws SQLException {}

    public CircuitAsphalt getCircuitAsphaltById(String circuitID) throws SQLException {
        CircuitAsphalt circuitAsphalt = circuitAsphaltDao.read(circuitID);
        if (circuitAsphalt == null) {
            throw new SQLException("Circuit not found");
        }
    }

    public CircuitDirt getCircuitDirtById(String circuitID) throws SQLException {
    }
}
