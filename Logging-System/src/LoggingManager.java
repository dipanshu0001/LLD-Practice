import java.time.LocalDateTime;

public class LoggingManager {
    private Logger logger;
    private LogLevel level;

    public LoggingManager(Logger logger, LogLevel level) {
        this.logger = logger;
        this.level = level;
    }
    
    // Runtime configuration methods
    public void setLogLevel(LogLevel level) {
        this.level = level;
    }
    
    public LogLevel getLogLevel() {
        return this.level;
    }
    
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    public Logger getLogger() {
        return this.logger;
    }

    public void info(String message) {
        logMessage(LogLevel.INFO, message, null);
    }

    public void info(String message, Object... params) {
        logMessage(LogLevel.INFO, message, params);
    }

    public void warn(String message) {
        logMessage(LogLevel.WARN, message, null);
    }

    public void warn(String message, Object... params) {
        logMessage(LogLevel.WARN, message, params);
    }

    public void error(String message) {
        logMessage(LogLevel.ERROR, message, null);
    }

    public void error(String message, Object... params) {
        logMessage(LogLevel.ERROR, message, params);
    }

    public void trace(String message) {
        logMessage(LogLevel.TRACE, message, null);
    }

    public void trace(String message, Object... params) {
        logMessage(LogLevel.TRACE, message, params);
    }

    public void debug(String message) {
        logMessage(LogLevel.DEBUG, message, null);
    }

    public void debug(String message, Object... params) {
        logMessage(LogLevel.DEBUG, message, params);
    }

    private void logMessage(LogLevel logLevel, String message, Object[] params) {
        if (logLevel.getPriority() >= level.getPriority()) {
            final StackTraceElement[] stack = Thread.currentThread().getStackTrace();
            final String threadName = Thread.currentThread().getName();
            final String className = stack[3].getClassName();
            final String methodName = stack[3].getMethodName();
            final String timeStamp = LocalDateTime.now().toString();

            final LogMessage logMessage =
                    new LogMessage(message, logLevel, className, methodName, threadName, timeStamp, params);
            logger.log(logMessage);
        }
    }

}
