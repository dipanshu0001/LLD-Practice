package Impl;

import DTO.ParkingSpot;
import Enums.ParkingLocationType;
import Interfaces.ParkingLocationStartegy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NearExitGateStrategy implements ParkingLocationStartegy {
    /**
     * @return
     */
    @Override
    public ParkingLocationType parkingLocationType() {
        return ParkingLocationType.NEAR_EXIT_GATE;
    }

    /**
     * @param mapping
     * @param locationType
     * @return
     */
    @Override
    public Optional<ParkingSpot> findParkingSpot(Map<ParkingLocationType, List<ParkingSpot>> mapping, ParkingLocationType locationType) {
        return mapping.getOrDefault(locationType,new ArrayList<>()).stream().filter(ParkingSpot-> !ParkingSpot.isOccupied()).findFirst();
    }
}
