import java.util.ArrayList;
import java.util.List;

public class TrafficEventNotifier {
    List<TrafficObserver> subList;
    public TrafficEventNotifier(){
        subList=new ArrayList<>();
    }
    public void addObserver(TrafficObserver observer){
        subList.add(observer);
    }
    public void removeObserver(TrafficObserver observer){
        subList.remove(observer);
    }
    public void notifyEvent(EventEnum event){
        subList.forEach(observer -> observer.update(event));
    }
}
