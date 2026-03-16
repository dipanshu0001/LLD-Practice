public class LoggerConfig {
    private LogLevel logLevel;
    private LogFormat logFormat;
    private LogAppender logAppender;

    private LoggerConfig(Builder builder) {
        this.logLevel = builder.logLevel;
        this.logFormat = builder.logFormat;
        this.logAppender = builder.logAppender;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public LogFormat getLogFormat() {
        return logFormat;
    }

    public void setLogFormat(LogFormat logFormat) {
        this.logFormat = logFormat;
    }

    public LogAppender getLogAppender() {
        return logAppender;
    }

    public void setLogAppender(LogAppender logAppender) {
        this.logAppender = logAppender;
    }

    public Logger createLogger() {
        return LogFactory.getLogObject(logAppender, logFormat);
    }

    public static class Builder {
        private LogLevel logLevel = LogLevel.INFO;
        private LogFormat logFormat;
        private LogAppender logAppender;

        public Builder setLogLevel(LogLevel logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public Builder setLogFormat(LogFormat logFormat) {
            this.logFormat = logFormat;
            return this;
        }

        public Builder setLogAppender(LogAppender logAppender) {
            this.logAppender = logAppender;
            return this;
        }

        public LoggerConfig build() {
            if (logFormat == null) {
                throw new IllegalStateException("LogFormat must be set");
            }
            if (logAppender == null) {
                throw new IllegalStateException("LogAppender must be set");
            }
            return new LoggerConfig(this);
        }
    }
}

