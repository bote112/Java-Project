package model;

public class Team {
    private int teamID;
    private Driver driver;
    private String name;
    private String sponsor;
    private int trophies;
    private int budget;

    public Team() {
    }
    public Team(String name, String sponsor, int trophies, int budget, Driver driver) {
        this.name = name;
        this.sponsor = sponsor;
        this.trophies = trophies;
        this.budget = budget;
        this.driver = driver;
    }

    public int getTeamID() {
        return  teamID;
    }

    public String getName() {
        return name;
    }

    public String getSponsor() {
        return sponsor;
    }

    public int getTrophies() {
        return trophies;
    }

    public int getBudget() {
        return budget;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setTeamID(int  teamID) {
        this. teamID =  teamID;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamID=" +  teamID +
                ", name='" + name + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", trophies=" + trophies +
                ", budget=" + budget +
                ", driver=" + driver +
                '}';
    }

}
