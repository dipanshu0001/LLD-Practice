package Interfaces;

import DTO.ParkingSpot;
import Enums.ParkingLocationType;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ParkingLocationStartegy {
    ParkingLocationType parkingLocationType();
    Optional<ParkingSpot> findParkingSpot(Map<ParkingLocationType, List<ParkingSpot>>mapping,ParkingLocationType locationType);
}
