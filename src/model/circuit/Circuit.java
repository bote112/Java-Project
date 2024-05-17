package model.circuit;

public class Circuit {
    protected  int circuitID;
    protected  String name;
    protected  String length;
    protected  String location;
    protected  String record;

    public Circuit() {
    }
    public Circuit(int circuitID, String name, String length, String location, String record) {
        this.circuitID = circuitID;
        this.name = name;
        this.length = length;
        this.location = location;
        this.record = record;
    }

    public int getCircuitID() {
        return circuitID;
    }
    public String getName() {
        return name;
    }

    public String getLength() {
        return length;
    }

    public String getLocation() {
        return location;
    }
    public String getRecord() {
        return record;
    }

    public void setCircuitID(int circuitID) {
        this.circuitID = circuitID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRecord(String record) {
        this.record = record;
    }

}
