package model;

public class Championship {
    private String name;
    private int year;
    private Race[] races;
    private Team champion;

    public Championship(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public Race[] getRaces() {
        return races;
    }

    public Team getChampion() {
        return champion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Championship{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", champion=" + champion +
                '}';
    }
}
