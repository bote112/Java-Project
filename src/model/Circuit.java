package model;

public class Circuit {
    private String name;
    private int length;
    private String location;
    private String record;

    public Circuit(String name, int length, String location, String record) {
        this.name = name;
        this.length = length;
        this.location = location;
        this.record = record;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public String getLocation() {
        return location;
    }
    public String getRecord() {
        return record;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRecord(String record) {
        this.record = record;
    }

}
