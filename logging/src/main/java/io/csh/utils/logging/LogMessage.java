package io.csh.utils.logging;

import java.time.LocalDateTime;

/**
 * 비동기 로깅을 위한 로그 메시지 클래스
 */
public class LogMessage {
    private final LogLevel level;
    private final String message;
    private final Throwable throwable;
    private final LocalDateTime timestamp;
    private final String threadName;

    public LogMessage(LogLevel level, String message, Throwable throwable) {
        this.level = level;
        this.message = message;
        this.throwable = throwable;
        this.timestamp = LocalDateTime.now();
        this.threadName = Thread.currentThread().getName();
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getThreadName() {
        return threadName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(timestamp).append(" [").append(threadName).append("] ")
          .append(level).append(" - ")
          .append(message);
        
        if (throwable != null) {
            sb.append("\n").append(throwable.toString());
        }
        
        return sb.toString();
    }
} 