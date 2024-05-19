package model.circuit;

import model.circuit.Circuit;

public class CircuitDirt extends Circuit {
    private String terrain;
    private int jumps;
    private int obstacles;


    public CircuitDirt() {
    }
    public CircuitDirt(String name, String length, String location, String record, String terrain, int jumps, int obstacles) {
        super( name, length, location, record);
        this.terrain = terrain;
        this.jumps = jumps;
        this.obstacles = obstacles;
    }

    public String getTerrain() {
        return terrain;
    }

    public int getJumps() {
        return jumps;
    }

    public int getObstacles() {
        return obstacles;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public void setObstacles(int obstacles) {
        this.obstacles = obstacles;
    }

    @Override
    public String toString() {
        return "CircuitDirt{" +
                "circuitID=" + getCircuitID() +
                ", name='" + getName() + '\'' +
                ", length=" + getLength() +
                ", location='" + getLocation() + '\'' +
                ", record='" + getRecord() + '\'' +
                ", terrain='" + terrain + '\'' +
                ", jumps=" + jumps +
                ", obstacles=" + obstacles +
                '}';
    }

}
