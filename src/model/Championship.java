package model;

public class Championship {
    private int championshipID;
    private String name;
    private int year;
    private Race[] races;
    private Team champion;

    public Championship() {
    }
    public Championship(int championshipID, String name, int year) {
        this.championshipID = championshipID;
        this.name = name;
        this.year = year;
    }

    public int getChampionshipID() {
        return championshipID;
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

    public void setChampionshipID(int championshipID) {
        this.championshipID = championshipID;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRaces(Race[] races) {
        this.races = races;
    }

    public void setChampion(Team champion) {
        this.champion = champion;
    }

    @Override
    public String toString() {
        return "Championship{" +
                "championshipID=" + championshipID +
                "name='" + name + '\'' +
                ", year=" + year +
                ", champion=" + champion +
                '}';
    }
}
