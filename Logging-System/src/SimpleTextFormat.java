public class SimpleTextFormat implements LogFormat {
    @Override
    public String format(final LogMessage message) {
        return message.getMessage();
    }
}
