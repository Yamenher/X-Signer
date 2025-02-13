#!/bin/bash
# Script by trindadedev.

CACHE_DIR="$HOME/.cache/formatters"
mkdir -p "$CACHE_DIR"

# --- Java ---
JAVA_FORMATTER="$CACHE_DIR/google-java-format.jar"
JAVA_FORMATTER_VERSION="1.25.2"
JAVA_FORMATTER_URL="https://github.com/google/google-java-format/releases/download/v$JAVA_FORMATTER_VERSION/google-java-format-$JAVA_FORMATTER_VERSION-all-deps.jar"

if [ ! -f "$JAVA_FORMATTER" ]; then
    echo "Downloading Google Java Formatter..."
    wget -q "$JAVA_FORMATTER_URL" -O "$JAVA_FORMATTER"
fi

echo "Formatting Java files..."
find ../ -name "*.java" -exec java -jar "$JAVA_FORMATTER" --aosp --replace {} +