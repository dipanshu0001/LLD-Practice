import java.util.HashMap;
import java.util.Map;

public class EventHandler {
    private final static Map<EventEnum,TrafficStartegy> mapping=new HashMap<>();
    static{
        mapping.put(EventEnum.EMERGENCY,new AmbulanceTrafficStartegyImpl());
        mapping.put(EventEnum.HIGH_TRAFFIC,new HighTrafficStartegyImpl());
        mapping.put(EventEnum.NIGHT_TIME,new NightTrafficStartegy());
        mapping.put(EventEnum.PADESTRIAN_ALERT,new PadestrianTrafficStartegy());
    }
    public TrafficStartegy getHandler(EventEnum eventEnum){
        if(!mapping.containsKey(eventEnum)){
            throw new EventNotPresentException(eventEnum.name());
        }
        return mapping.get(eventEnum);
    }
}
