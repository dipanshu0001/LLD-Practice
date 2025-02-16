package Impl;

import DTO.ParkingSpot;
import Enums.ParkingLocationType;
import Interfaces.ParkingLocationStartegy;

import java.util.*;

public class NearEntranceGateStrategy implements ParkingLocationStartegy {
    /**
     * @return
     */
    @Override
    public ParkingLocationType parkingLocationType() {
        return ParkingLocationType.NEAR_ENTRANCE_GATE;
    }

    /**
     * @return
     */
    @Override
    public Optional<ParkingSpot> findParkingSpot(Map<ParkingLocationType,List<ParkingSpot>> availSports, ParkingLocationType locationType) {
        return availSports.getOrDefault(locationType,new ArrayList<>()).stream().filter(ParkingSpot-> !ParkingSpot.isOccupied()).findFirst();
    }
}