import java.io.Serializable;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class ThreadBasedFormat implements LogFormat {
    private final LogFormat formatter;
    public ThreadBasedFormat(LogFormat logFormat) {
        this.formatter = logFormat;
    }
    @Override
    public String format(LogMessage message) {
        return message.getThreadName()+"-" +formatter.format(message);
    }
}
