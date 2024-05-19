import service.CircuitService;
import service.TeamService;
import service.CarService;
import service.DriverService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        CircuitService circuitService = new CircuitService();
        TeamService teamService = new TeamService();
        CarService carService = new CarService();
        DriverService driverService = new DriverService();

        while (true) {
            menu();
            String command = scanner.nextLine().toLowerCase();
            System.out.println("Command received: " + command);

            switch (command) {
                case "create":
                case "read":
                case "delete":
                case "update":
                    processCommand(command, circuitService, teamService, carService, driverService, scanner);
                    break;
                case "quit":
                    System.out.println("Exiting");
                    scanner.close();
                    return;
                default:
                    System.out.println("Wrong command");
            }
        }
    }

    private static void menu() {
        System.out.println("Available commands:");
        System.out.println("create ");
        System.out.println("read ");
        System.out.println("update ");
        System.out.println("delete ");
        System.out.println("quit");
        System.out.println("Enter command:");
    }

    private static void processCommand(String command, CircuitService circuitService, TeamService teamService, CarService carService, DriverService driverService, Scanner scanner) throws SQLException {
        System.out.println("Is this for a circuit, team, car or driver?");
        String type = scanner.nextLine().toLowerCase();
        switch (type) {
            case "circuit":
                processCircuitCommand(command, circuitService, scanner);
                break;
            case "team":
                processTeamCommand(command, teamService, scanner);
                break;
            case "car":
                processCarCommand(command, carService, scanner);
                break;
            case "driver":
                processDriverCommand(command, driverService, scanner);
                break;
            default:
                System.out.println("Invalid type, please specify 'circuit', 'team', 'car' or 'driver'.");
        }
    }
    private static void processCircuitCommand(String command, CircuitService circuitService, Scanner scanner) {
        switch (command) {
            case "create":
                circuitService.create(scanner);
                break;
            case "read":
                circuitService.read(scanner);
                break;
            case "delete":
                circuitService.delete(scanner);
                break;
            case "update":
                circuitService.update(scanner);
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    private static void processTeamCommand(String command, TeamService teamService, Scanner scanner) throws SQLException {
        switch (command) {
            case "create":
                teamService.create(scanner);
                break;
            case "read":
                teamService.read(scanner);
                break;
            case "delete":
                teamService.delete(scanner);
                break;
            case "update":
                teamService.update(scanner);
                break;
            default:
                System.out.println("Invalid command");
        }
    }
    private static void processCarCommand(String command, CarService carService, Scanner scanner) throws SQLException {
        switch (command) {
            case "create":
                carService.create(scanner);
                break;
            case "read":
                carService.read(scanner);
                break;
            case "delete":
                carService.delete(scanner);
                break;
            case "update":
                carService.update(scanner);
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    private static void processDriverCommand(String command, DriverService driverService, Scanner scanner) throws SQLException {
        switch (command) {
            case "create":
                driverService.create(scanner);
                break;
            case "read":
                driverService.read(scanner);
                break;
            case "delete":
                driverService.delete(scanner);
                break;
            case "update":
                driverService.update(scanner);
                break;
            default:
                System.out.println("Invalid command");
        }
    }
}