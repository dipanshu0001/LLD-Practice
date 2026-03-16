import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads logger configuration from properties file (Spring-like approach)
 * Allows runtime configuration changes by reloading the properties file
 */
public class LoggerConfigLoader {
    private static final String DEFAULT_CONFIG_FILE = "logger.properties";

    private Properties properties;

    private String configFilePath;

    public LoggerConfigLoader() {
        this(DEFAULT_CONFIG_FILE);
    }

    public LoggerConfigLoader(String configFilePath) {
        this.configFilePath = configFilePath;
        this.properties = new Properties();
        loadConfiguration();
    }

    /**
     * Load (or reload) configuration from properties file
     */
    public void loadConfiguration() {
        // Strategy 1: Try to load from classpath (works in IDE and with proper build)
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFilePath)) {
            if (input != null) {
                properties.load(input);
                System.out.println("✓ Configuration loaded from classpath: " + configFilePath);
                return;
            }
        } catch (IOException e) {
            System.err.println("Failed to load from classpath: " + e.getMessage());
        }

        // Strategy 2: Try loading from current directory
        try (InputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
            System.out.println("✓ Configuration loaded from current directory: " + configFilePath);
            return;
        } catch (IOException e) {
            // Continue to next strategy
        }

        // Strategy 3: Try loading from src directory (development mode)
        try (InputStream input = new FileInputStream("src/" + configFilePath)) {
            properties.load(input);
            System.out.println("✓ Configuration loaded from src directory: src/" + configFilePath);
            return;
        } catch (IOException e) {
            // Continue to next strategy
        }

        // Strategy 4: Try loading from Logging-System/src directory (if running from project root)
        try (InputStream input = new FileInputStream("Logging-System/src/" + configFilePath)) {
            properties.load(input);
            System.out.println("✓ Configuration loaded from Logging-System/src: " + configFilePath);
            return;
        } catch (IOException e) {
            // All strategies failed
        }

        // All strategies failed - use defaults
        System.err.println("✗ Failed to load configuration from: " + configFilePath);
        System.err.println("✗ Tried: classpath, current dir, src/, Logging-System/src/");
        System.err.println("✓ Using default configuration");
        setDefaultProperties();
    }

    /**
     * Reload configuration at runtime
     */
    public void reloadConfiguration() {
        System.out.println("Reloading configuration...");
        loadConfiguration();
    }

    /**
     * Get LogLevel from configuration
     */
    public LogLevel getLogLevel() {
        String level = properties.getProperty("logging.level", "TRACE").toUpperCase();
        try {
            return LogLevel.valueOf(level);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid log level: " + level + ". Using INFO.");
            return LogLevel.INFO;
        }
    }

    /**
     * Get LogFormat instance from configuration using reflection
     */
    public LogFormat getLogFormat() {
        String formatClass = properties.getProperty("logging.format", "SimpleTextFormat");
        try {
            Class<?> clazz = Class.forName(formatClass);
            return (LogFormat) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Failed to create LogFormat: " + formatClass + ". Using SimpleTextFormat.");
            return new SimpleTextFormat();
        }
    }

    /**
     * Get LogAppender instance from configuration
     */
    public LogAppender getLogAppender(LogFormat logFormat) {
        String appenderClass = properties.getProperty("logging.appender", "ConsolerAppender");
        try {
            Class<?> clazz = Class.forName(appenderClass);
            return (LogAppender) clazz.getDeclaredConstructor(LogFormat.class).newInstance(logFormat);
        } catch (Exception e) {
            System.err.println("Failed to create LogAppender: " + appenderClass + ". Using ConsolerAppender.");
            return new ConsolerAppender(logFormat);
        }
    }

    /**
     * Get property value
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Set default properties if file not found
     */
    private void setDefaultProperties() {
        properties.setProperty("logging.level", "INFO");
        properties.setProperty("logging.format", "SimpleTextFormat");
        properties.setProperty("logging.appender", "ConsolerAppender");
    }

    /**
     * Create LoggerConfig from properties file
     */
    public LoggerConfig createLoggerConfig() {
        LogFormat format = getLogFormat();
        LogAppender appender = getLogAppender(format);
        LogLevel level = getLogLevel();

        return new LoggerConfig.Builder()
                .setLogFormat(format)
                .setLogAppender(appender)
                .setLogLevel(level)
                .build();
    }
}

