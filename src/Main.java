import model.*;

import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Ferrari", "Red", 350, 1000);
        Driver driver = new Driver(car, "Michael Schumacher", 52, "Germany");
        System.out.println(driver);

        Team team = new Team("Ferrari", "Shell", 16, 1000000, driver);
        System.out.println(team);

        CircuitAsphalt circuit = new CircuitAsphalt("Monza", 5793, "Italy", "1:21.046", "street", 5, "medium");
        System.out.println(circuit);
        Race race = new Race(circuit, new Team[]{team}, 53);
        System.out.println(race);
        Championship championship = new Championship("Formula 1", 2021);
        System.out.println(championship);


    }
}
