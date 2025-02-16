import DTO.EntranceGate;
import DTO.ParkingSpot;
import DTO.Ticket;
import DTO.Vehicle;
import Enums.ParkingLocationType;
import Enums.VehicleType;
import Factories.ParkingLocationFactory;
import Factories.ParkingManagerFactory;
import Impl.EntranceGateManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ParkingLocationFactory parkingLocationFactory=new ParkingLocationFactory();
        ParkingManagerFactory parkingManagerFactory=new ParkingManagerFactory(parkingLocationFactory);
        EntranceGateManager entranceGateManager=new EntranceGateManager(parkingManagerFactory);
        ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();
        parkingSpots.add(new ParkingSpot(1, false, 50.0));
        parkingSpots.add(new ParkingSpot(2, false, 40.0));
        parkingSpots.add(new ParkingSpot(3, false, 30.0));
        parkingSpots.add(new ParkingSpot(4, false, 60.0));

        // Setting parking location type for each spot
        for (ParkingSpot spot : parkingSpots) {
            spot.setParkingLocation(ParkingLocationType.BASEMENT);
        }
        parkingManagerFactory.getParkingObject(VehicleType.TWO_WHEELER).addParkingLot(parkingSpots);
        Ticket t=entranceGateManager.makeCarParkIfPossible(new Vehicle("jfkadf", VehicleType.TWO_WHEELER), ParkingLocationType.BASEMENT);
        Ticket t2=entranceGateManager.makeCarParkIfPossible(new Vehicle("123-789-456", VehicleType.TWO_WHEELER), ParkingLocationType.BASEMENT);
        Map<ParkingLocationType, List<ParkingSpot>> mp=parkingManagerFactory.getParkingObject(VehicleType.TWO_WHEELER).getParkingSpot();
        for(ParkingLocationType key:mp.keySet()){
            System.out.print(key);
            for(ParkingSpot parkingSpot:mp.get(key)){
                System.out.println("\t"+parkingSpot.toString());
            }
            System.out.println();
        }
        System.out.println(t.toString());
    }
}