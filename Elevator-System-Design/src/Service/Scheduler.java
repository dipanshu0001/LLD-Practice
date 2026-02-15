package Service;

import Model.ElevatorController;
import Model.Floor;

import java.util.List;

public class Scheduler {
    ArrangeLift arrangeLift;

    public Scheduler(ArrangeLift arrangeLift) {
        this.arrangeLift = arrangeLift;
    }

    public ElevatorController getElevator(final Floor floor, final List<ElevatorController> elevators) {
        // logic to schedule the elevator based on the request
        ElevatorController elevator = arrangeLift.getElevator(floor, elevators);
        elevator.addRequest(floor.getId());
        return elevator;
        // further logic to move the elevator to the requested floor
    }
}
