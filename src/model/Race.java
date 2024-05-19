package model;

import model.circuit.Circuit;

public class Race {
    private int raceID;
    private Circuit circuit;
    private Team[] teams;
    private int laps;
    private Team winner;

    public Race() {
    }
    public Race (int raceID, Circuit circuit, Team[] teams, int laps) {
        this.raceID = raceID;
        this.circuit = circuit;
        this.teams = teams;
        this.laps = laps;
    }

    public int getRaceID() {
        return raceID;
    }
    public Circuit getCircuit() {
        return circuit;
    }

    public Team[] getTeams() {
        return teams;
    }

    public int getLaps() {
        return laps;
    }

    public Team getWinner() {
        return winner;
    }

    public void setRaceID(int raceID) {
        this.raceID = raceID;
    }
    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Race " +
                "raceID=" + raceID +
                ", circuit=" + circuit +
                ", teams=" + teams +
                ", laps=" + laps +
                ", winner=" + winner;
    }
}
