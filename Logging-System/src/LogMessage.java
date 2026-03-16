
public class LogMessage {
    private String message;

    private LogLevel logLevel;

    private String className;

    private String methodName;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    private String timeStamp;
    
    private Object[] params;

    public LogMessage(String message, LogLevel logLevel, String className, String methodName, String threadName, String timeStamp) {
        this(message, logLevel, className, methodName, threadName, timeStamp, null);
    }

    public LogMessage(String message, LogLevel logLevel, String className, String methodName, String threadName, String timeStamp, Object[] params) {
        this.message = message;
        this.logLevel = logLevel;
        this.className = className;
        this.methodName = methodName;
        this.threadName = threadName;
        this.timeStamp = timeStamp;
        this.params = params;
    }
    
    public Object[] getParams() {
        return params;
    }
    
    public void setParams(Object[] params) {
        this.params = params;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    private String threadName;

    public String getMessage() {
        return message;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getThreadName() {
        return threadName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

}
