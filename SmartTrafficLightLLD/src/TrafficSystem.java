public class TrafficSystem implements TrafficObserver{
    private TrafficStartegy trafficStartegy;
    private EventHandler eventHandler;

    public TrafficSystem(EventHandler _eventHandler){
        eventHandler=_eventHandler;
    }

    public void setTrafficStartegy(TrafficStartegy _trafficStartegy){
        trafficStartegy=_trafficStartegy;
    }
    /**
     *
     */
    @Override
    public void update(EventEnum event) {
        System.out.println("Received an event: "+event);
        eventHandler.getHandler(event).execute();
//        trafficStartegy.execute();
    }
}
