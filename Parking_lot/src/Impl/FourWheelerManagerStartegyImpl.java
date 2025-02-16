package Impl;

import DTO.ParkingSpot;
import DTO.Vehicle;
import Enums.ParkingLocationType;
import Enums.VehicleType;
import Factories.ParkingLocationFactory;
import Interfaces.ParkingManagerStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class FourWheelerManagerStartegyImpl implements ParkingManagerStrategy {
    /**
     * @return
     */
    private ParkingLocationFactory parkingLocationFactory;
    private Map<ParkingLocationType,List<ParkingSpot>> locationTypeParkingSpotMap;

    public FourWheelerManagerStartegyImpl(ParkingLocationFactory parkingLocationFactory) {
        this.parkingLocationFactory = parkingLocationFactory;
        this.locationTypeParkingSpotMap = new HashMap<>();
    }

    @Override
    public VehicleType parkingType() {
        return VehicleType.FOUR_WHEELER;
    }

    /**
     * @param parkingSpot
     */
    @Override
    public void removeVehicleFromParkingSpot(ParkingSpot parkingSpot) {
        parkingSpot.removeVehicle();
    }

    /**
     * @param vehicle
     * @param parkingSpot
     */
    @Override
    public void addVehicleToParkingSpot(Vehicle vehicle, ParkingSpot parkingSpot) {
        parkingSpot.setVehicle(vehicle);
    }

    /**
     * @param parkingLocation
     * @return
     */
    @Override
    public Optional<ParkingSpot> findParkingSpot(ParkingLocationType parkingLocation) {
        return parkingLocationFactory.getLocationObject(parkingLocation).findParkingSpot(locationTypeParkingSpotMap,parkingLocation);
    }

    /**
     * @param parkingSpotList
     */
    @Override
    public void addParkingLot(List<ParkingSpot> parkingSpotList) {
        parkingSpotList.stream().collect(Collectors.groupingBy(ParkingSpot::getParkingLocation))
                .forEach((type,newValue)->{
                    this.locationTypeParkingSpotMap.merge(type,newValue,(oldList,newList)->{
                        oldList.addAll(newList);
                        return oldList;
                    });
                });
    }

    public Map<ParkingLocationType,List<ParkingSpot>> getParkingSpot(){
        return locationTypeParkingSpotMap;
    }
}
