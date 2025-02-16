public class EventNotPresentException extends RuntimeException{
    public EventNotPresentException(String message){
        super("Event Not Present in the mapping for its class"+message);
    }
}
