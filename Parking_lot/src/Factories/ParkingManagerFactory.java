package Factories;

import Enums.VehicleType;
import Impl.FourWheelerManagerStartegyImpl;
import Impl.TwoWheelerManagerStrategyImpl;
import Interfaces.ParkingManagerStrategy;

import java.util.HashMap;
import java.util.Map;

public class ParkingManagerFactory {
    private Map<VehicleType, ParkingManagerStrategy> parkingManagerStartegyMap;

    private ParkingLocationFactory parkingLocationFactory;

    public ParkingManagerFactory(ParkingLocationFactory parkingLocationFactory){
        this.parkingLocationFactory=parkingLocationFactory;
        parkingManagerStartegyMap=new HashMap<>();
        parkingManagerStartegyMap.put(VehicleType.TWO_WHEELER,new TwoWheelerManagerStrategyImpl(parkingLocationFactory));
        parkingManagerStartegyMap.put(VehicleType.FOUR_WHEELER,new FourWheelerManagerStartegyImpl(parkingLocationFactory));
    }

    public ParkingManagerStrategy getParkingObject(VehicleType vehicleType){
        if (!parkingManagerStartegyMap.containsKey(vehicleType)) {
            throw new RuntimeException("No Object exists for vehicleType provided: " + vehicleType);
        }
        return parkingManagerStartegyMap.get(vehicleType);
    }
}
