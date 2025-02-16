package Impl;

import DTO.EntranceGate;
import DTO.ParkingSpot;
import DTO.Ticket;
import DTO.Vehicle;
import Enums.ParkingLocationType;
import Factories.ParkingManagerFactory;
import Interfaces.ParkingManagerStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntranceGateManager {
    //TODO its work is to find the parkingSpot,makeBooking,createTicket
    private final List<EntranceGate> entranceGateList;
    private final ParkingManagerFactory parkingManagerFactory;
    public EntranceGateManager(ParkingManagerFactory parkingManagerFactory){
        this.parkingManagerFactory = parkingManagerFactory;
        this.entranceGateList=new ArrayList<>();
        addEntranceGate();
    }
    public void addEntranceGate(){
        EntranceGate entranceGate=new EntranceGate();
        entranceGateList.add(entranceGate);
    }
    public Ticket makeCarParkIfPossible(Vehicle vehicle, ParkingLocationType locationType){
        ParkingManagerStrategy parkingManagerStrategy=parkingManagerFactory.getParkingObject(vehicle.getVehicleType());
        Optional<ParkingSpot> parkingSpotOpt=parkingManagerStrategy.findParkingSpot(locationType);
        if(parkingSpotOpt.isEmpty()){
            throw new RuntimeException("Sorry currently no parking slot exist for vehicle type "+vehicle.getVehicleType()+"and locationType "+locationType);
        }
        parkingManagerStrategy.addVehicleToParkingSpot(vehicle,parkingSpotOpt.get());
        return generateTicket(vehicle,parkingSpotOpt.get());
    }

    private Ticket generateTicket(Vehicle vehicle,ParkingSpot parkingSpot) {
        return new Ticket(LocalDateTime.now(), parkingSpot);
    }
}

