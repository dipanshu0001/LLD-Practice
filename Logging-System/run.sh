#!/bin/bash
# Compile and run the logging system with properties file in classpath

cd "$(dirname "$0")"

echo "Current directory: $(pwd)"

# Clean and create output directory
rm -rf out
mkdir -p out

# Compile all Java files
echo "Compiling..."
javac -d out src/*.java 2>&1

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful"

    # Copy properties file to classpath root
    cp src/logger.properties out/
    echo "✓ Properties file copied to classpath"

    # Verify Main.class exists
    if [ -f "out/com/dipanshu/logging/examples/Main.class" ]; then
        echo "✓ Main.class found (com.dipanshu.logging.examples.Main)"
        echo ""
        echo "Running Main..."
        echo "===================="
        cd out
        java com.dipanshu.logging.examples.Main
    else
        echo "✗ Error: Main.class not found in out/ directory"
        echo "Available files in out/:"
        ls -la out/
        exit 1
    fi
else
    echo "✗ Compilation failed!"
    exit 1
fi

