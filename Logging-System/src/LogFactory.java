public class LogFactory {
    public static Logger logger;

    public static Logger getLogObject(final LogAppender appender,final LogFormat format) {
        if (logger == null) {
            return logger = new Logger(appender, format);
        }
        return logger;
    }
}
