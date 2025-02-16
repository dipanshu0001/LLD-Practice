package Impl;

import DTO.ParkingSpot;
import DTO.Vehicle;
import Enums.ParkingLocationType;
import Enums.VehicleType;
import Factories.ParkingLocationFactory;
import Interfaces.ParkingManagerStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TwoWheelerManagerStrategyImpl implements ParkingManagerStrategy {

    private ParkingLocationFactory parkingLocationFactory;
    private Map<ParkingLocationType,List<ParkingSpot>> locationTypeParkingSpotMap;

    public TwoWheelerManagerStrategyImpl(ParkingLocationFactory parkingLocationFactory) {
        this.parkingLocationFactory = parkingLocationFactory;
        locationTypeParkingSpotMap =new HashMap<>();
    }

    /**
     * @return
     */
    @Override
    public VehicleType parkingType() {
        return VehicleType.TWO_WHEELER;
    }

    /**
     *
     */
    @Override
    public void removeVehicleFromParkingSpot(ParkingSpot parkingSpot) {
        parkingSpot.removeVehicle();
    }

    /**
     *
     */
    @Override
    public void addVehicleToParkingSpot(Vehicle vehicle,ParkingSpot parkingSpot) {
        parkingSpot.setVehicle(vehicle);
        parkingSpot.setOccupied(true);
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
