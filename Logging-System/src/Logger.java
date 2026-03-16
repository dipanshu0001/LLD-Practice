//This will be the internal Faccade sytem whic will control the flow.
public class Logger {
    private final LogAppender logAppender;
    private final LogFormat logFormat;

    public Logger(LogAppender logAppender, LogFormat logFormat) {
        this.logAppender = logAppender;
        this.logFormat = logFormat;
    }

    public void log(LogMessage message) {
        final String formattedLog= logFormat.format(message);
        logAppender.append(formattedLog);
    }
}
