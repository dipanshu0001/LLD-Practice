package Factories;

import Enums.ParkingLocationType;
import Impl.*;
import Interfaces.ParkingLocationStartegy;

import java.util.HashMap;
import java.util.Map;

public class ParkingLocationFactory {
    private final Map<ParkingLocationType,ParkingLocationStartegy> parkingLocationStartegyMap;
    public ParkingLocationFactory(){
        parkingLocationStartegyMap=new HashMap<>();
        addMapping();
    }
    private void addMapping(){
        parkingLocationStartegyMap.put(ParkingLocationType.BASEMENT,new BasementStrategy());
        parkingLocationStartegyMap.put(ParkingLocationType.HANDICAPPED_ACCESSIBLE,new HandicappedStrategy());
        parkingLocationStartegyMap.put(ParkingLocationType.NEAR_ELEVATOR,new NearElevatorStrategy());
        parkingLocationStartegyMap.put(ParkingLocationType.NEAR_EXIT_GATE,new NearExitGateStrategy());
        parkingLocationStartegyMap.put(ParkingLocationType.NEAR_ENTRANCE_GATE,new NearEntranceGateStrategy());
        parkingLocationStartegyMap.put(ParkingLocationType.ROOFTOP,new RoofTopStrategy());
        parkingLocationStartegyMap.put(ParkingLocationType.REGULAR_SPOT,new RegularSpotStrategy());
    }
    public ParkingLocationStartegy getLocationObject(ParkingLocationType parkingLocationType){
        return parkingLocationStartegyMap.get(parkingLocationType);
    }
}
