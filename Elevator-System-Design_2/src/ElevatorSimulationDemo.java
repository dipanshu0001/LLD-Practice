import Model.*;
import Service.Scheduler;
import Service.ShortestDistanceWithDirectionWay;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorSimulationDemo {
    private static ShortestDistanceWithDirectionWay selector;
    private static ExecutorService executorService;
    private static Building building;
    private static Scheduler scheduler;
    private static Scanner scanner = new Scanner(System.in);
    private static int numberOfFloors;
    private static int numberOfElevators;

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("🏢 ELEVATOR SYSTEM SIMULATION - Distance + Direction Based");
        System.out.println("═══════════════════════════════════════════════════════════\n");

        // Initialize elevator system
        initializeElevatorSystem();

        // Start background thread to process elevator requests
        executorService = Executors.newFixedThreadPool(3);
        startElevatorProcessing();

        // Start interactive user input loop
        runInteractiveLoop();
    }

    private static void initializeElevatorSystem() {
        System.out.println("📊 Please enter the number of floors and elevators (e.g., '20 3'):");
        numberOfFloors = scanner.nextInt();
        numberOfElevators = scanner.nextInt();
        scanner.nextLine();
        building = new Building(numberOfFloors, numberOfElevators);

        scheduler = new Scheduler(new ShortestDistanceWithDirectionWay());

        // System.out.println("\n═══════════════════════════════════════════════════════════");
        // System.out.println("📍 Initial State:");
        // System.out.println(" Elevator 1: Floor 0, Direction IDLE");
        // System.out.println(" Elevator 2: Floor 5, Direction IDLE");
        // System.out.println(" Elevator 3: Floor 8, Direction IDLE");
        // System.out.println("═══════════════════════════════════════════════════════════");
    }

    private static void startElevatorProcessing() {
        // Start background threads for each elevator to process requests
        for (ElevatorController controller : building.getElevators()) {
            executorService.submit(() -> {
                while (true) {
                    try {
                        Thread.sleep(2000); // Check for requests every 2 seconds
                        if (controller.hasRequests()) {
                            controller.processNextRequest();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }
    }

    private static void runInteractiveLoop() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║           INTERACTIVE ELEVATOR CONTROL                    ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println("\nCommands:");
        System.out.println("  • Enter floor number (0-" + (numberOfFloors - 1) + ") to request elevator (Pickup)");
        System.out.println("  • Type 'go <elevator_id> <floor>' to select destination for a specific elevator");
        System.out.println("  • Type 'status' to see all elevator states");
        System.out.println("  • Type 'exit' or 'quit' to shutdown system");
        System.out.println("\n═══════════════════════════════════════════════════════════\n");

        while (true) {
            try {
                System.out.print("\n🎮 Enter command (floor number/status/exit): ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                    System.out.println("\n🛑 Shutting down elevator system...");
                    executorService.shutdownNow();
                    System.out.println("✅ System shutdown complete. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                }

                if (input.equalsIgnoreCase("status")) {
                    displaySystemStatus();
                    continue;
                }

                if (input.toLowerCase().startsWith("go ")) {
                    handleInternalRequest(input);
                    continue;
                }

                try {
                    int requestedFloor = Integer.parseInt(input);

                    if (requestedFloor < 0 || requestedFloor > numberOfFloors - 1) {
                        System.out.println("⚠️  Invalid floor! Please enter a floor between 0 and " + numberOfFloors);
                        continue;
                    }

                    handleFloorRequest(requestedFloor);

                } catch (NumberFormatException e) {
                    System.out.println(
                            "⚠️  Invalid input! Please enter a floor number, 'status', 'go <elevator_id> <floor>', or 'exit'.");
                }

            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    private static void handleFloorRequest(int requestedFloor) {
        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("📞 NEW REQUEST: Floor " + requestedFloor);
        System.out.println("════════════════════════════════════════════════════════════");

        Floor floor = new Floor(requestedFloor, null);
        ElevatorController selected = scheduler.getElevator(floor, building.getElevators());

        if (selected != null) {
            System.out.println("\n✅ Request assigned to Elevator " + selected.getElevator().liftNumber);
            System.out.println("⏳ Elevator will process the request shortly...");
        } else {
            System.out.println("❌ No available elevator found!");
        }
        System.out.println("════════════════════════════════════════════════════════════");
    }

    private static void handleInternalRequest(String input) {
        // Format: go <elevator_id> <floor>
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            System.out.println("⚠️  Invalid command format! Use: go <elevator_id> <floor>");
            return;
        }

        try {
            int elevatorId = Integer.parseInt(parts[1]);
            int destinationFloor = Integer.parseInt(parts[2]);

            if (destinationFloor < 0 || destinationFloor > numberOfFloors - 1) {
                System.out.println("⚠️  Invalid floor! Please enter a floor between 0 and " + (numberOfFloors - 1));
                return;
            }

            // Find elevator by ID
            ElevatorController targetElevator = null;
            for (ElevatorController controller : building.getElevators()) {
                if (controller.getElevator().liftNumber == elevatorId) {
                    targetElevator = controller;
                    break;
                }
            }

            if (targetElevator != null) {
                System.out.println("\n════════════════════════════════════════════════════════════");
                System.out.println("🎯 INTERNAL REQUEST: Elevator " + elevatorId + " → Floor " + destinationFloor);
                System.out.println("════════════════════════════════════════════════════════════");
                targetElevator.addRequest(destinationFloor);
            } else {
                System.out.println("❌ Elevator with ID " + elevatorId + " not found!");
            }

        } catch (NumberFormatException e) {
            System.out.println("⚠️  Invalid ID or Floor number!");
        }
    }

    private static void displaySystemStatus() {
        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("📊 CURRENT SYSTEM STATUS");
        System.out.println("════════════════════════════════════════════════════════════");

        for (ElevatorController controller : building.getElevators()) {
            Elevator elevator = controller.getElevator();
            System.out.println("\n🏢 Elevator " + elevator.liftNumber + ":");
            System.out.println("   Floor: " + controller.getCurrentFloor());
            System.out.println("   Direction: " + controller.getCurrentDirection());
            System.out.println("   Status: " + elevator.getStatus());
            System.out.println("   Pending UP requests: " + controller.getUpRequests());
            System.out.println("   Pending DOWN requests: " + controller.getDownRequests());
            System.out.println("   Has requests: " + controller.hasRequests());
        }

        System.out.println("\n════════════════════════════════════════════════════════════");
    }
}
