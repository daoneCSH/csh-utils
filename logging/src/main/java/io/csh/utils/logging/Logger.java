package io.csh.utils.logging;

import io.csh.utils.core.config.PropertyManager;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class Logger {
    protected final String name;
    protected final LogLevel level;
    private static final boolean DEBUG = Boolean.getBoolean("csh.debug");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private final LogOutputManager outputManager;

    public Logger(String name) {
        this.name = name;
        String level = PropertyManager.getProperty("logging.level");
        this.level = LogLevel.valueOf(level != null ? level : "INFO");
        this.outputManager = LogOutputManager.getInstance();
    }

    public void trace(String message) {
        log(LogLevel.TRACE, message, null);
    }

    public void trace(String message, Throwable throwable) {
        log(LogLevel.TRACE, message, throwable);
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message, null);
    }

    public void debug(String message, Throwable throwable) {
        log(LogLevel.DEBUG, message, throwable);
    }

    public void info(String message) {
        log(LogLevel.INFO, message, null);
    }

    public void info(String message, Throwable throwable) {
        log(LogLevel.INFO, message, throwable);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message, null);
    }

    public void warn(String message, Throwable throwable) {
        log(LogLevel.WARN, message, throwable);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message, null);
    }

    public void error(String message, Throwable throwable) {
        log(LogLevel.ERROR, message, throwable);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message, null);
    }

    public void fatal(String message, Throwable throwable) {
        log(LogLevel.FATAL, message, throwable);
    }

    protected void log(LogLevel level, String message, Throwable throwable) {
        if (!this.level.isEnabled(level)) {
            return;
        }

        LogMessage logMessage = new LogMessage(
            level,
            name,
            message,
            throwable
        );

        outputManager.output(logMessage);

        if (level == LogLevel.FATAL) {
            // TODO: API 연동 구현
        }

        if (DEBUG) {
            System.out.println(logMessage);
        }
    }
} 