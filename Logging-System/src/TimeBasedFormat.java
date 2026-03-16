public class TimeBasedFormat implements LogFormat{
    private final LogFormat logFormat;
    public TimeBasedFormat(LogFormat logFormat) {
        this.logFormat = logFormat;
    }
    @Override
    public String format(LogMessage message) {
        return "[" + message.getTimeStamp() + "] " + logFormat.format(message);
    }
}
