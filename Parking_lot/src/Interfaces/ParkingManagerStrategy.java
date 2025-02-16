package Interfaces;

import DTO.ParkingSpot;
import DTO.Vehicle;
import Enums.ParkingLocationType;
import Enums.VehicleType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ParkingManagerStrategy {
    VehicleType parkingType();
    void removeVehicleFromParkingSpot(ParkingSpot parkingSpot);
    void addVehicleToParkingSpot(Vehicle vehicle,ParkingSpot parkingSpot);

    Optional<ParkingSpot> findParkingSpot(ParkingLocationType parkingLocation);

    void addParkingLot(List<ParkingSpot> parkingSpotList);

    Map<ParkingLocationType,List<ParkingSpot>> getParkingSpot();

}
