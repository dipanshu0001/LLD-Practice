package Model;

import Enum.Direction;

import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

public class ElevatorController {
    private Elevator elevator;
    private int currentFloor;
    private Direction currentDirection;
    private final PriorityBlockingQueue<Integer> upRequests;
    private final PriorityBlockingQueue<Integer> downRequests;

    public ElevatorController(Elevator elevator) {
        this.elevator = elevator;
        this.currentFloor = 0;
        this.currentDirection = Direction.IDLE;
        // Min heap for up requests (smallest floor first)
        this.upRequests = new PriorityBlockingQueue<>();
        // Max heap for down requests (largest floor first)
        this.downRequests = new PriorityBlockingQueue<>(11,(a,b)-> b - a);

        System.out.println("🏢 [INIT] Elevator " + elevator.liftNumber + " initialized at floor " + currentFloor);
    }

    public void addRequest(int floor) {
        System.out.println("\n📞 [REQUEST] Elevator " + elevator.liftNumber + " received request for floor " + floor);
        System.out.println("   Current floor: " + currentFloor + " | Direction: " + currentDirection);

        if (floor > currentFloor) {
            upRequests.offer(floor);
            System.out.println("   ✅ Added to UP queue: " + upRequests);
        } else if (floor < currentFloor) {
            downRequests.offer(floor);
            System.out.println("   ✅ Added to DOWN queue: " + downRequests);
        } else {
            System.out.println("   ⚠️  Request ignored - already at floor " + floor);
        }
    }

    public void processNextRequest() {
        System.out.println("\n⚙️  [PROCESSING] Elevator " + elevator.liftNumber + " processing next request...");
        System.out.println("   Current State: Floor " + currentFloor + " | Direction: " + currentDirection);
        System.out.println("   Pending UP requests: " + upRequests);
        System.out.println("   Pending DOWN requests: " + downRequests);

        if (currentDirection == Direction.UP || currentDirection == Direction.IDLE) {
            if (!upRequests.isEmpty()) {
                int targetFloor = upRequests.poll();
                simulateMovement(currentFloor, targetFloor);
                currentFloor = targetFloor;
                currentDirection = Direction.UP;
                System.out.println("   ✅ Completed UP request to floor " + targetFloor);
                return;
            }
        }

        if (currentDirection == Direction.DOWN || currentDirection == Direction.IDLE) {
            if (!downRequests.isEmpty()) {
                int targetFloor = downRequests.poll();
                simulateMovement(currentFloor, targetFloor);
                currentFloor = targetFloor;
                currentDirection = Direction.DOWN;
                System.out.println("   ✅ Completed DOWN request to floor " + targetFloor);
                return;
            }
        }

        // Check the opposite direction if current direction is exhausted
        if (currentDirection == Direction.UP && !downRequests.isEmpty()) {
            int targetFloor = downRequests.poll();
            System.out.println("   🔄 Switching direction from UP to DOWN");
            simulateMovement(currentFloor, targetFloor);
            currentFloor = targetFloor;
            currentDirection = Direction.DOWN;
            System.out.println("   ✅ Completed DOWN request to floor " + targetFloor);
        } else if (currentDirection == Direction.DOWN && !upRequests.isEmpty()) {
            int targetFloor = upRequests.poll();
            System.out.println("   🔄 Switching direction from DOWN to UP");
            simulateMovement(currentFloor, targetFloor);
            currentFloor = targetFloor;
            currentDirection = Direction.UP;
            System.out.println("   ✅ Completed UP request to floor " + targetFloor);
        } else {
            currentDirection = Direction.IDLE;
            System.out.println("   💤 No more requests - Elevator going IDLE");
        }
    }

    private void simulateMovement(int fromFloor, int toFloor) {
        System.out.println("\n   🚀 [MOVING] Elevator " + elevator.liftNumber + " starting movement...");
        System.out.println("   From: Floor " + fromFloor + " → To: Floor " + toFloor);

        int direction = fromFloor < toFloor ? 1 : -1;
        String arrow = direction > 0 ? "⬆️ " : "⬇️ ";

        for (int floor = fromFloor; floor != toFloor; floor += direction) {
            try {
                Thread.sleep(500); // Simulate 0.5 second per floor
                System.out.println("   " + arrow + "Passing floor " + (floor + direction) + "...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("   ⚠️  Movement interrupted!");
            }
        }

        System.out.println("   🛑 [ARRIVED] Elevator " + elevator.liftNumber + " arrived at floor " + toFloor);
        System.out.println("   🚪 Doors opening...");
        try {
            elevator.elevatorContainer.doors.openDoor();
            Thread.sleep(1000); // Simulate door operation
            System.out.println("   ⏳ Elevator " + elevator.liftNumber + " waiting. Use command 'go "
                    + elevator.liftNumber + " <floor>' in main console to select destination.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("   🚪 Doors closing...");
        elevator.elevatorContainer.doors.closeDoor();
    }

    public boolean hasRequests() {
        boolean hasReqs = !upRequests.isEmpty() || !downRequests.isEmpty();
        // System.out.println(" ℹ️ Elevator " + elevator.liftNumber + " has requests: "
        // + hasReqs);
        return hasReqs;
    }

    public int getDistanceToFloor(int targetFloor) {
        int distance = Math.abs(currentFloor - targetFloor);
        System.out.println("   📏 Elevator " + elevator.liftNumber + " distance to floor " + targetFloor + ": "
                + distance + " floors");
        return distance;
    }

    public boolean isMovingTowards(int targetFloor) {
        boolean movingTowards;
        if (currentDirection == Direction.IDLE) {
            movingTowards = true; // Idle elevator can go anywhere
        } else if (currentDirection == Direction.UP) {
            movingTowards = targetFloor >= currentFloor;
        } else if (currentDirection == Direction.DOWN) {
            movingTowards = targetFloor <= currentFloor;
        } else {
            movingTowards = false;
        }

        System.out.println("   🎯 Elevator " + elevator.liftNumber + " moving towards floor " + targetFloor + ": "
                + movingTowards);
        return movingTowards;
    }

    // Getters and Setters
    public Elevator getElevator() {
        return elevator;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        System.out.println("   🔧 Elevator " + elevator.liftNumber + " floor updated: " + this.currentFloor + " → "
                + currentFloor);
        this.currentFloor = currentFloor;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        System.out.println("   🔧 Elevator " + elevator.liftNumber + " direction updated: " + this.currentDirection
                + " → " + currentDirection);
        this.currentDirection = currentDirection;
    }

    public PriorityBlockingQueue<Integer> getUpRequests() {
        return upRequests;
    }

    public PriorityBlockingQueue<Integer> getDownRequests() {
        return downRequests;
    }

    @Override
    public String toString() {
        return "ElevatorController{" +
                "elevator=" + elevator.liftNumber +
                ", currentFloor=" + currentFloor +
                ", currentDirection=" + currentDirection +
                ", upRequests=" + upRequests +
                ", downRequests=" + downRequests +
                '}';
    }
}
