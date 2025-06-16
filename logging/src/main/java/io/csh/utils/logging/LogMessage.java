package io.csh.utils.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogMessage {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private final LocalDateTime timestamp;
    private final LogLevel level;
    private final String loggerName;
    private final String message;
    private final Throwable throwable;
    
    public LogMessage(LogLevel level, String loggerName, String message) {
        this(level, loggerName, message, null);
    }
    
    public LogMessage(LogLevel level, String loggerName, String message, Throwable throwable) {
        this.timestamp = LocalDateTime.now();
        this.level = level;
        this.loggerName = loggerName;
        this.message = message;
        this.throwable = throwable;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public LogLevel getLevel() {
        return level;
    }
    
    public String getLoggerName() {
        return loggerName;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Throwable getThrowable() {
        return throwable;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(formatter.format(timestamp))
          .append(" [").append(Thread.currentThread().getName()).append("] ")
          .append(level).append(" ").append(loggerName).append(" - ").append(message);
        if (throwable != null) {
            sb.append("\n").append(throwable);
        }
        return sb.toString();
    }
} 