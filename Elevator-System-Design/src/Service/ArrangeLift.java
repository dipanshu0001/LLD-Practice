package Service;

import Model.ElevatorController;
import Enum.LiftDistanceCalculationAlgos;
import Model.Floor;

import java.util.List;

public interface ArrangeLift {
    LiftDistanceCalculationAlgos getLiftDistanceCalculationAlgo();
    ElevatorController getElevator(Floor floor, List<ElevatorController> elevators);
}
