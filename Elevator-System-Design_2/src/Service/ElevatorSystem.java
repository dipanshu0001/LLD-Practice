package Service;

import Model.Elevator;
import Model.ElevatorContainer;
import Model.ElevatorController;
import Model.Floor;

import java.util.List;

public class ElevatorSystem {
    public Scheduler scheduler;

    public List<ElevatorController> elevators;

    public ElevatorSystem(Scheduler scheduler, List<ElevatorController> elevators) {
        this.scheduler = scheduler;
        this.elevators = elevators;
    }

    public void addElevator(int maxWeight, int maxPeople, int liftNumber) {

        elevators.add(generateAElevator(maxWeight, maxPeople, liftNumber));
    }

    private ElevatorController generateAElevator(int maxWeight, int maxPeople, int liftNumber) {
        final ElevatorContainer elevatorContainer = new ElevatorContainer();
        final Elevator elevator = new Elevator(maxWeight, maxPeople, elevatorContainer, liftNumber);
        final ElevatorController elevatorController = new ElevatorController(elevator);
        return elevatorController;
    }

    public void removeElevator(int liftNumber) {
        for (int i = 0; i < elevators.size(); i++) {
            if (elevators.get(i).getElevator().liftNumber == liftNumber) {
                elevators.remove(i);
                break;
            }
        }
    }

    public void addFloor() {
        // code to add floor
    }

    public void removeFloor() {
        // code to remove floor
    }

    public ElevatorController getElevator(Floor floor) {
        return scheduler.getElevator(floor, elevators);
    }
}
