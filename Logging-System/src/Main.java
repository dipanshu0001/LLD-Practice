import logging.config.LoggerConfig;
import logging.config.LoggerConfigLoader;
import logging.core.ConsolerAppender;
import logging.core.LogLevel;
import logging.core.Logger;
import logging.format.SimpleTextFormat;

public class Main {
    public static void main(String[] var0) {
        System.out.println("=== Logging System Demo with Properties-Based Configuration ===\n");
        LoggerConfigLoader var1 = new LoggerConfigLoader();
        LoggerConfig var2 = var1.createLoggerConfig();
        Logger var3 = var2.createLogger();
        LoggingManager var4 = new LoggingManager(var3, var2.getLogLevel());
        System.out.println("Current Log Level: " + var2.getLogLevel());
        String var5 = "12345";
        String var6 = "Alice";
        var4.info("User {} logged in with name {}", new Object[]{var5, var6});
        short var7 = 9876;
        double var8 = (double)250.5F;
        var4.debug("Processing order {} with amount {}", new Object[]{Integer.valueOf(var7), var8});
        var4.warn("Low memory detected: {} MB remaining", new Object[]{128});
        var4.error("Failed to connect to database {} at {}", new Object[]{"orders_db", "localhost:5432"});
        System.out.println("\n=== Changing Log Level at Runtime ===\n");
        var4.setLogLevel(LogLevel.WARN);
        System.out.println("Log level changed to: WARN");
        var4.debug("This debug message will NOT be shown");
        var4.info("This info message will NOT be shown");
        var4.warn("This warning WILL be shown");
        var4.error("This error WILL be shown");
        System.out.println("\n=== Reloading Configuration from Properties File ===\n");
        var1.reloadConfiguration();
        LoggerConfig var10 = var1.createLoggerConfig();
        Logger var11 = var10.createLogger();
        var4.setLogger(var11);
        var4.setLogLevel(var10.getLogLevel());
        System.out.println("Configuration reloaded. Current Log Level: " + var10.getLogLevel());
        var4.info("Configuration reloaded successfully!");
        System.out.println("\n=== Switching to Different Formatter at Runtime ===\n");
        SimpleTextFormat var12 = new SimpleTextFormat();
        ConsolerAppender var13 = new ConsolerAppender(var12);
        Logger var14 = new Logger(var13, var12);
        var4.setLogger(var14);
        var4.error("Now using different formatter with params {}", new Object[]{"test123"});
    }
}
