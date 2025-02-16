// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
       TrafficEventNotifier trafficEventNotifier=new TrafficEventNotifier();

       TrafficSystem trafficSystem=new TrafficSystem(new EventHandler());
       trafficEventNotifier.addObserver(trafficSystem);
       trafficSystem.update(EventEnum.HIGH_TRAFFIC);
       trafficSystem.update(EventEnum.EMERGENCY);
       trafficSystem.update(EventEnum.XYZ);
    }
}