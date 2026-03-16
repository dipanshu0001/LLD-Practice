public class ConsolerAppender implements LogAppender {
    private final LogFormat logFormat;

    public ConsolerAppender(LogFormat logFormat) {
        this.logFormat = logFormat;
    }

    @Override
    public void append(final String message) {
        System.out.println(message);
    }
}
