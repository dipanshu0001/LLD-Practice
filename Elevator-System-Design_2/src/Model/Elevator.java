package Model;

import Enum.ElevatorStatus;

public class Elevator {
    public int maxPeople;
    public int maxWeight;
    public int liftNumber;
    public ElevatorStatus status= ElevatorStatus.ACTIVE;
    public ElevatorContainer elevatorContainer;

    public Elevator(int maxPeople, int maxWeight, ElevatorContainer elevatorContainer,int liftNumber) {
        this.maxPeople = maxPeople;
        this.maxWeight = maxWeight;
        this.elevatorContainer = elevatorContainer;
        this.liftNumber = liftNumber;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public void setStatus(ElevatorStatus status) {
        this.status = status;
    }

    public ElevatorContainer getElevatorContainer() {
        return elevatorContainer;
    }

    public void setElevatorContainer(ElevatorContainer elevatorContainer) {
        this.elevatorContainer = elevatorContainer;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "maxPeople=" + maxPeople +
                ", maxWeight=" + maxWeight +
                ", status=" + status +
                ", elevatorContainer=" + elevatorContainer +
                '}';
    }
}
