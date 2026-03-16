public class Main {
    public static void main(String[] args) {
        System.out.println("=== Logging System Demo with Properties-Based Configuration ===\n");
        
        // Load configuration from logger.properties file (Spring-like approach)
        LoggerConfigLoader configLoader = new LoggerConfigLoader();
        LoggerConfig config = configLoader.createLoggerConfig();
        
        Logger logger = config.createLogger();
        LoggingManager loggingManager = new LoggingManager(logger, config.getLogLevel());
        
        System.out.println("Current Log Level: " + config.getLogLevel());
        

        // Parameterized logging (log4j-style with {} placeholders)
        String userId = "12345";
        String userName = "Alice";
        loggingManager.info("User {} logged in with name {}", userId, userName);
        
        int orderId = 9876;
        double amount = 250.50;
        loggingManager.debug("Processing order {} with amount {}", orderId, amount);
        
        loggingManager.warn("Low memory detected: {} MB remaining", 128);
        
        loggingManager.error("Failed to connect to database {} at {}", "orders_db", "localhost:5432");
        
        System.out.println("\n=== Changing Log Level at Runtime ===\n");
        
        // Change log level at runtime - now only WARN and ERROR will be logged
        loggingManager.setLogLevel(LogLevel.WARN);
        System.out.println("Log level changed to: WARN");
        
        loggingManager.debug("This debug message will NOT be shown"); // Won't show
        loggingManager.info("This info message will NOT be shown");   // Won't show
        loggingManager.warn("This warning WILL be shown");           // Will show
        loggingManager.error("This error WILL be shown");            // Will show
        
        System.out.println("\n=== Reloading Configuration from Properties File ===\n");
        
        // Reload configuration from file (you can edit logger.properties and reload)
        configLoader.reloadConfiguration();
        LoggerConfig newConfig = configLoader.createLoggerConfig();
        Logger newLogger = newConfig.createLogger();
        loggingManager.setLogger(newLogger);
        loggingManager.setLogLevel(newConfig.getLogLevel());
        
        System.out.println("Configuration reloaded. Current Log Level: " + newConfig.getLogLevel());
        loggingManager.info("Configuration reloaded successfully!");
        
        System.out.println("\n=== Switching to Different Formatter at Runtime ===\n");
        
        // Change to a different formatter at runtime
        LogFormat simpleFormat = new SimpleTextFormat();
        LogAppender newAppender = new ConsolerAppender(simpleFormat);
        Logger anotherLogger = new Logger(newAppender, simpleFormat);
        loggingManager.setLogger(anotherLogger);
        
        loggingManager.error("Now using different formatter with params {}", "test123");
    }
}