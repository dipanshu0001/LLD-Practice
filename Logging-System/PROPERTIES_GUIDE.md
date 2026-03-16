# Properties-Based Configuration - Spring-like Approach

## Why the FileNotFoundException Occurs

When Java loads a properties file using `FileInputStream("logger.properties")`, it looks for the file relative to the **current working directory** (where you run the command), NOT relative to your source code.

### The Problem:
- Your properties file is in: `src/logger.properties`
- When you run `java Main`, the working directory might be project root or elsewhere
- Java cannot find `logger.properties` relative to the working directory

### The Solution:
**Use Classpath Loading** (like Spring Boot does)

The `LoggerConfigLoader` now uses:
```java
getClass().getClassLoader().getResourceAsStream("logger.properties")
```

This loads the file from the **classpath** (wherever your .class files are), not from the file system.

---

## How to Run Your Program

### Method 1: Using the run.sh script
```bash
cd /Users/dipanshu/Code-Practices/LLD-code-practice/LLD-Practice/Logging-System
chmod +x run.sh
./run.sh
```

### Method 2: Manual compilation and run
```bash
cd /Users/dipanshu/Code-Practices/LLD-code-practice/LLD-Practice/Logging-System

# Create output directory
mkdir -p out

# Compile Java files
javac -d out src/*.java

# Copy properties file to classpath (important!)
cp src/logger.properties out/

# Run from the out directory (where .class files are)
cd out
java Main
```

### Method 3: Using IDE (IntelliJ IDEA)
1. Right-click on `Main.java` → Run 'Main.main()'
2. IntelliJ automatically copies resources to the output directory
3. The properties file should be in the classpath

---

## How to Use Properties-Based Configuration

### 1. Edit `logger.properties`:
```properties
# Available log levels: TRACE, DEBUG, INFO, WARN, ERROR
logging.level=TRACE

# Available formatters
logging.format=ParameterizedLogFormat

# Available appenders
logging.appender=ConsolerAppender
```

### 2. Load configuration in your code:
```java
// Load from properties file
LoggerConfigLoader configLoader = new LoggerConfigLoader();
LoggerConfig config = configLoader.createLoggerConfig();
Logger logger = config.createLogger();
LoggingManager loggingManager = new LoggingManager(logger, config.getLogLevel());

// Use log4j-style parameterized logging
loggingManager.info("User {} logged in", "12345");
loggingManager.error("Failed to connect to {} at port {}", "database", 5432);
```

### 3. Change configuration at runtime:
```java
// Change log level
loggingManager.setLogLevel(LogLevel.WARN);

// Or reload from properties file
configLoader.reloadConfiguration();
LoggerConfig newConfig = configLoader.createLoggerConfig();
loggingManager.setLogger(newConfig.createLogger());
loggingManager.setLogLevel(newConfig.getLogLevel());
```

---

## Available Formatters

Based on your project structure:
- `SimpleTextFormat`
- `ParameterizedLogFormat` (log4j-style with `{}` placeholders)
- `TimeBasedFormat`
- `ThreadBasedFormat`
- `ClassFunctionBasedFormat`

---

## Log4j-Style Parameterized Logging

Instead of string concatenation:
```java
// ❌ Old way (inefficient)
loggingManager.info("User " + userId + " logged in with name " + userName);
```

Use placeholders:
```java
// ✅ New way (log4j-style)
loggingManager.info("User {} logged in with name {}", userId, userName);
```

**Benefits:**
- No string concatenation if log level is disabled (performance)
- Cleaner, more readable code
- Works with any object type (calls toString() automatically)

---

## Key Takeaway

**The properties file must be in the classpath (where .class files are), not just in the src directory!**

When you compile with `javac -d out src/*.java`, you must also copy the properties file:
```bash
cp src/logger.properties out/
```

This is exactly how Spring Boot works - resources must be in the classpath!

