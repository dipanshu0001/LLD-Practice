package DTO;

import Enums.ParkingLocationType;
import Enums.VehicleType;

public class ParkingSpot {
//    TODO this will hold the a unit of parking which have like
//     fields->id,isEmpty,vehicle ,price,type of spot
    private int id;
    private boolean isOccupied;
    private Double price;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    private Vehicle vehicle;

    private ParkingLocationType parkingLocation;

    public ParkingLocationType getParkingLocation(){
        return parkingLocation;
    }

    public void setParkingLocation(ParkingLocationType parkingLocation){
        this.parkingLocation=parkingLocation;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ParkingSpot(int price) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }


    public ParkingSpot(int id, boolean isOccupied,double price) {
        this.id = id;
        this.isOccupied = isOccupied;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", isOccupied=" + isOccupied +
                ", price=" + price +
                ", parkingLocation=" + parkingLocation +
                ", vehicleType=" + (vehicle != null ? vehicle.getVehicleNumber() : "EMPTY") +
                '}';
    }

    public void parkVehicle(Vehicle vehicle) {
        if (!isOccupied) {
            this.isOccupied = true;
            this.vehicle = vehicle;
            System.out.println(vehicle.getVehicleType() + " parked at spot ID: " + id);
        } else {
            throw new RuntimeException("Spot ID " + id + " is already occupied!");
        }
    }
    public void removeVehicle() {
        if (isOccupied) {
            System.out.println(vehicle.getVehicleNumber() + " removed from spot ID: " + id);
            this.isOccupied = false;
            this.vehicle = null;
        } else {
            throw new RuntimeException("Spot ID " + id + " is already empty!");
        }
    }
}
