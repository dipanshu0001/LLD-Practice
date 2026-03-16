public class ClassFunctionBasedFormat implements LogFormat {
    private LogFormat logFormat;

    public ClassFunctionBasedFormat(LogFormat logFormat) {
        this.logFormat = logFormat;
    }
    @Override
    public String format(final LogMessage message) {
        return message.getClassName() + "." + message.getMethodName() + ": " + logFormat.format(message);
    }
}
