package Service;

import Model.ElevatorController;
import Model.Floor;
import Enum.Direction;
import Enum.LiftDistanceCalculationAlgos;
import Enum.ElevatorStatus;

import java.util.List;

public class ShortestDistanceWithDirectionWay implements ArrangeLift {
    @Override
    public LiftDistanceCalculationAlgos getLiftDistanceCalculationAlgo() {
        return LiftDistanceCalculationAlgos.DIRECTION_DISTANCE_BASED;
    }

    @Override
    public ElevatorController getElevator(Floor floor, List<ElevatorController> elevators) {
        System.out.println("\n🔍 [SELECTION] Finding best elevator for floor " + floor.getId());
        System.out.println("   Total elevators available: " + elevators.size());

        if (elevators == null || elevators.isEmpty()) {
            System.out.println("   ❌ No elevators available!");
            return null;
        }

        ElevatorController bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        System.out.println("\n   Evaluating each elevator:");
        for (ElevatorController controller : elevators) {
            System.out.println("\n   📊 Elevator " + controller.getElevator().liftNumber + ":");

            // Skip if elevator is inactive
            if (controller.getElevator().getStatus() != ElevatorStatus.ACTIVE) {
                System.out.println("      ⚠️  Status: " + controller.getElevator().getStatus() + " - SKIPPED");
                continue;
            }

            int distance = controller.getDistanceToFloor(floor.getId());
            Direction elevatorDirection = controller.getCurrentDirection();
            int elevatorFloor = controller.getCurrentFloor();

            System.out.println("      Current Floor: " + elevatorFloor);
            System.out.println("      Current Direction: " + elevatorDirection);
            System.out.println("      Distance: " + distance + " floors");

            // Calculate priority score
            // Lower score = higher priority
            int priorityScore = calculatePriorityScore(
                floor.getId(),
                elevatorFloor,
                elevatorDirection,
                distance
            );

            System.out.println("      Priority Score: " + priorityScore);

            if (bestElevator == null || priorityScore < minDistance) {
                if (bestElevator != null) {
                    System.out.println("      ✨ NEW BEST! (previous best was Elevator " +
                                     bestElevator.getElevator().liftNumber + " with score " + minDistance + ")");
                } else {
                    System.out.println("      ✨ First candidate!");
                }
                bestElevator = controller;
                minDistance = priorityScore;
            } else {
                System.out.println("      ❌ Not better than current best");
            }
        }

        if (bestElevator != null) {
            System.out.println("\n   🏆 SELECTED: Elevator " + bestElevator.getElevator().liftNumber +
                             " (Score: " + minDistance + ")");
        } else {
            System.out.println("\n   ❌ No suitable elevator found!");
        }

        return bestElevator;
    }

    /**
     * Calculate priority score for elevator selection
     * Priority:
     * 1. Elevator moving towards the floor in same direction (distance matters)
     * 2. Idle elevator (distance matters)
     * 3. Elevator moving away or in opposite direction (distance + penalty)
     */
    private int calculatePriorityScore(int requestFloor, int elevatorFloor,
                                       Direction direction, int distance) {
        System.out.println("      🧮 Calculating priority score:");
        System.out.println("         Request Floor: " + requestFloor);
        System.out.println("         Elevator Floor: " + elevatorFloor);
        System.out.println("         Direction: " + direction);
        System.out.println("         Distance: " + distance);

        if (direction == Direction.IDLE) {
            // Idle elevators get base distance score
            System.out.println("         → IDLE elevator - Score = " + distance);
            return distance;
        }

        // Check if elevator is moving towards the request floor
        boolean movingTowards = false;
        if (direction == Direction.UP && requestFloor >= elevatorFloor) {
            movingTowards = true;
            System.out.println("         → Moving UP towards request ✓");
        } else if (direction == Direction.DOWN && requestFloor <= elevatorFloor) {
            movingTowards = true;
            System.out.println("         → Moving DOWN towards request ✓");
        } else {
            System.out.println("         → Moving AWAY from request ✗");
        }

        if (movingTowards) {
            // Best case: elevator is already moving towards the request
            System.out.println("         → Score = " + distance + " (optimal direction)");
            return distance;
        } else {
            // Elevator is moving away or in opposite direction
            // Add penalty to make it less preferable
            int score = distance + 1000;
            System.out.println("         → Score = " + distance + " + 1000 (penalty) = " + score);
            return score;
        }
    }
}
