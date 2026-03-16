import java.util.logging.LogRecord;

public interface LogAppender {
    void append(String message);
}
