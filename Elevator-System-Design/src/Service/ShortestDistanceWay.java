package Service;

import Model.ElevatorController;
import Model.Floor;
import Enum.LiftDistanceCalculationAlgos;

import java.util.List;

import static Enum.LiftDistanceCalculationAlgos.ABSOLUTE_DISTANCE;

public class ShortestDistanceWay implements ArrangeLift{

    @Override
    public LiftDistanceCalculationAlgos getLiftDistanceCalculationAlgo() {
        return ABSOLUTE_DISTANCE;
    }

    @Override
    public ElevatorController getElevator(Floor floor, List<ElevatorController> elevators) {
        ElevatorController closestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorController elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - floor.getId());
            if (distance < minDistance) {
                minDistance = distance;
                closestElevator = elevator;
            }
        }
        return closestElevator;
    }
}
