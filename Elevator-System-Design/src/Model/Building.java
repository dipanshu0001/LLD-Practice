package Model;

import java.util.List;

public class Building {
    private List<Floor> floors;

    private List<ElevatorController> elevators;

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public List<ElevatorController> getElevators() {
        return elevators;
    }

    public void setElevators(List<ElevatorController> elevators) {
        this.elevators = elevators;
    }

    public Building(int numberOfFloor, int numberOfElevators) {
        this.floors = createFloors(numberOfFloor);
        this.elevators = createElevators(numberOfElevators);
    }

    private List<ElevatorController> createElevators(int numberOfElevators) {
        return java.util.stream.IntStream.rangeClosed(1, numberOfElevators)
                .mapToObj(i -> new ElevatorController(
                        new Elevator(13, 950, new ElevatorContainer(), i)))
                .toList();
    }

    private List<Floor> createFloors(int numberOfFloor) {
        return java.util.stream.IntStream.rangeClosed(1, numberOfFloor)
                .mapToObj(i -> new Floor(i, new ButtonPanel()))
                .toList();
    }

}
