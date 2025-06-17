package io.csh.utils.logging.config;

import io.csh.utils.config.SystemProperties;
import lombok.Getter;

@Getter
public class LoggingConfig {
    private final String level;
    private final boolean consoleEnabled;
    private final String consolePattern;
    private final String timestampFormat;
    private final boolean threadFormat;
    private final boolean levelFormat;
    private final boolean loggerFormat;
    private final boolean messageFormat;

    public LoggingConfig() {
        this.level = SystemProperties.getProperty(LoggingProperties.LEVEL, LoggingProperties.DEFAULT_LEVEL);
        this.consoleEnabled = Boolean.parseBoolean(
            SystemProperties.getProperty(LoggingProperties.CONSOLE_ENABLED, LoggingProperties.DEFAULT_CONSOLE_ENABLED)
        );
        this.consolePattern = SystemProperties.getProperty(
            LoggingProperties.CONSOLE_PATTERN, LoggingProperties.DEFAULT_CONSOLE_PATTERN
        );
        this.timestampFormat = SystemProperties.getProperty(
            LoggingProperties.FORMAT_TIMESTAMP, LoggingProperties.DEFAULT_FORMAT_TIMESTAMP
        );
        this.threadFormat = Boolean.parseBoolean(
            SystemProperties.getProperty(LoggingProperties.FORMAT_THREAD, LoggingProperties.DEFAULT_FORMAT_THREAD)
        );
        this.levelFormat = Boolean.parseBoolean(
            SystemProperties.getProperty(LoggingProperties.FORMAT_LEVEL, LoggingProperties.DEFAULT_FORMAT_LEVEL)
        );
        this.loggerFormat = Boolean.parseBoolean(
            SystemProperties.getProperty(LoggingProperties.FORMAT_LOGGER, LoggingProperties.DEFAULT_FORMAT_LOGGER)
        );
        this.messageFormat = Boolean.parseBoolean(
            SystemProperties.getProperty(LoggingProperties.FORMAT_MESSAGE, LoggingProperties.DEFAULT_FORMAT_MESSAGE)
        );
    }

    public String getFormattedConfig() {
        return String.format("""
            Logging Configuration:
            - Level: %s
            - Console Enabled: %s
            - Console Pattern: %s
            - Timestamp Format: %s
            - Thread Format: %s
            - Level Format: %s
            - Logger Format: %s
            - Message Format: %s
            """,
            level,
            consoleEnabled,
            consolePattern,
            timestampFormat,
            threadFormat,
            levelFormat,
            loggerFormat,
            messageFormat
        );
    }
} 