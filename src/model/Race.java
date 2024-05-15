package model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Race {
    private Circuit circuit;
    private Team[] teams;
    private int laps;
    private Team winner;

    public Race (Circuit circuit, Team[] teams, int laps) {
        this.circuit = circuit;
        this.teams = teams;
        this.laps = laps;
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
                "circuit=" + circuit +
                ", teams=" + teams +
                ", laps=" + laps +
                ", winner=" + winner;
    }
}
