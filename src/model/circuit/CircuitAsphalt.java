package model.circuit;

import model.circuit.Circuit;

public class CircuitAsphalt extends Circuit {
    private String type; // street, permanent, drag, etc.
    private int turns;
    private String tire;

    public CircuitAsphalt() {
    }
    public CircuitAsphalt( String name, String length, String location, String record, String type, int turns, String tire) {
        super( name, length, location, record);
        this.type = type;
        this.turns = turns;
        this.tire = tire;
    }

    public String getType() { return type; }

    public int getTurns() {
        return turns;
    }

    public String getTire() {
        return tire;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void setTire(String tire) {
        this.tire = tire;
    }

    @Override
    public String toString() {
        return "CircuitAsphalt{" +
                "circuitID=" + getCircuitID() +
                ", name='" + getName() + '\'' +
                ", length=" + getLength() +
                ", location='" + getLocation() + '\'' +
                ", record='" + getRecord() + '\'' +
                ", type='" + type + '\'' +
                ", turns=" + turns +
                ", tire='" + tire + '\'' +
                '}';
    }

}
