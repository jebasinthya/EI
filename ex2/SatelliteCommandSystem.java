import java.util.Scanner;
import java.util.logging.Logger;

enum Orientation {
    NORTH, SOUTH, EAST, WEST
}

class Satellite {
    private Orientation orientation;
    private boolean solarPanelsActive;
    private int dataCollected;

    private static final Logger logger = Logger.getLogger(Satellite.class.getName());

    public Satellite() {
        this.orientation = Orientation.NORTH;
        this.solarPanelsActive = false;
        this.dataCollected = 0;
        logger.info("Satellite initialized: " + getStatus());
    }

    public void rotate(Orientation newOrientation) {
        this.orientation = newOrientation;
        logger.info("Satellite rotated to: " + orientation);
    }

    public void activatePanels() {
        if (!solarPanelsActive) {
            solarPanelsActive = true;
            logger.info("Solar panels activated.");
        } else {
            logger.warning("Solar panels already active.");
        }
    }

    public void deactivatePanels() {
        if (solarPanelsActive) {
            solarPanelsActive = false;
            logger.info("Solar panels deactivated.");
        } else {
            logger.warning("Solar panels already inactive.");
        }
    }

    public void collectData() {
        if (solarPanelsActive) {
            dataCollected += 10;
            logger.info("Data collected successfully. Total data: " + dataCollected);
        } else {
            logger.warning("Cannot collect data. Solar panels are inactive.");
        }
    }

    public String getStatus() {
        return "Orientation: " + orientation +
                ", Solar Panels: " + (solarPanelsActive ? "Active" : "Inactive") +
                ", Data Collected: " + dataCollected;
    }
}

interface Command {
    void execute();
}

class RotateCommand implements Command {
    private Satellite satellite;
    private Orientation orientation;

    public RotateCommand(Satellite satellite, Orientation orientation) {
        this.satellite = satellite;
        this.orientation = orientation;
    }

    @Override
    public void execute() {
        satellite.rotate(orientation);
    }
}

class ActivatePanelsCommand implements Command {
    private Satellite satellite;

    public ActivatePanelsCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() {
        satellite.activatePanels();
    }
}

class DeactivatePanelsCommand implements Command {
    private Satellite satellite;

    public DeactivatePanelsCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() {
        satellite.deactivatePanels();
    }
}

class CollectDataCommand implements Command {
    private Satellite satellite;

    public CollectDataCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() {
        satellite.collectData();
    }
}

class CommandInvoker {
    public void executeCommand(Command command) {
        try {
            command.execute();
        } catch (Exception e) {
            System.err.println("Error executing command: " + e.getMessage());
        }
    }
}

public class SatelliteCommandSystem {
    public static void main(String[] args) {
        Satellite satellite = new Satellite();
        CommandInvoker invoker = new CommandInvoker();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Satellite Command System ===");
        System.out.println("Available commands:");
        System.out.println("rotate <NORTH|SOUTH|EAST|WEST>");
        System.out.println("activatePanels");
        System.out.println("deactivatePanels");
        System.out.println("collectData");
        System.out.println("status");
        System.out.println("exit");

        while (true) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+");
            String command = parts[0].toLowerCase();

            switch (command) {
                case "rotate":
                    if (parts.length < 2) {
                        System.out.println("Error: Specify direction (NORTH, SOUTH, EAST, WEST).");
                    } else {
                        try {
                            Orientation dir = Orientation.valueOf(parts[1].toUpperCase());
                            invoker.executeCommand(new RotateCommand(satellite, dir));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid direction. Use NORTH, SOUTH, EAST, or WEST.");
                        }
                    }
                    break;

                case "activatepanels":
                    invoker.executeCommand(new ActivatePanelsCommand(satellite));
                    break;

                case "deactivatepanels":
                    invoker.executeCommand(new DeactivatePanelsCommand(satellite));
                    break;

                case "collectdata":
                    invoker.executeCommand(new CollectDataCommand(satellite));
                    break;

                case "status":
                    System.out.println("Satellite Status: " + satellite.getStatus());
                    break;

                case "exit":
                    System.out.println("Exiting Satellite Command System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Unknown command. Try again.");
            }
        }
    }
}
