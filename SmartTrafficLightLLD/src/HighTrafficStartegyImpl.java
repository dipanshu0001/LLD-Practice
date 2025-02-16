public class HighTrafficStartegyImpl implements TrafficStartegy{

    @Override
    public void execute() {
        System.out.println("High Traffic so switching to more green light and less red light time");
    }
}
