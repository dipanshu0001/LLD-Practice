package Service;

import Model.Elevator;
import Model.ElevatorController;

import java.util.List;

public class ElevatorRunningCommand {

    List<ElevatorController> elevators;

    public ElevatorRunningCommand(List<ElevatorController> elevators) {
        this.elevators = elevators;
    }

    public void startElevatorSystem() {
        // This method can be used to start the elevator system, for example, by starting a thread for each elevator controller
        for (ElevatorController elevatorController : elevators) {
            new Thread(() -> {
                while (true) {
                    elevatorController.processNextRequest();
                    try {
                        Thread.sleep(1000); // Simulate time taken to move between floors
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }
    }


}
