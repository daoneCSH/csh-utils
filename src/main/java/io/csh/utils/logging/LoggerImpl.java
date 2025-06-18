package io.csh.utils.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 로거 구현체
 * 
 * <p>표준 로깅 레벨을 모두 지원하며, 설정된 로그 레벨에 따라
 * 출력을 제어합니다.</p>
 */
public final class LoggerImpl implements Logger {
    private final String name;
    private static volatile LogLevel currentLevel = LogLevel.INFO;
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private LoggerImpl(Class<?> clazz) {
        this.name = clazz.getName();
    }

    /**
     * 로거 인스턴스를 반환합니다.
     *
     * @param clazz 로거를 사용할 클래스
     * @return LoggerImpl 인스턴스
     */
    public static LoggerImpl getInstance(Class<?> clazz) {
        return new LoggerImpl(clazz);
    }

    /**
     * 전역 로그 레벨을 설정합니다.
     *
     * @param level 설정할 로그 레벨
     */
    public static void setLogLevel(LogLevel level) {
        currentLevel = level;
    }

    /**
     * 전역 로그 레벨을 반환합니다.
     *
     * @return 현재 로그 레벨
     */
    public static LogLevel getLogLevel() {
        return currentLevel;
    }

    /**
     * 로그 메시지를 포맷팅합니다.
     *
     * @param level 로그 레벨
     * @param message 로그 메시지
     * @return 포맷팅된 로그 메시지
     */
    private String formatMessage(LogLevel level, String message) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        String threadName = Thread.currentThread().getName();
        return String.format("%s [%s] %-5s %s - %s", 
            timestamp, threadName, level.name(), name, message);
    }

    @Override
    public void trace(String message) {
        if (LogLevel.TRACE.isEnabled(currentLevel)) {
            System.out.println(formatMessage(LogLevel.TRACE, message));
        }
    }

    @Override
    public void trace(String message, Throwable thrown) {
        if (LogLevel.TRACE.isEnabled(currentLevel)) {
            System.out.println(formatMessage(LogLevel.TRACE, message));
            thrown.printStackTrace(System.out);
        }
    }

    @Override
    public void debug(String message) {
        if (LogLevel.DEBUG.isEnabled(currentLevel)) {
            System.out.println(formatMessage(LogLevel.DEBUG, message));
        }
    }

    @Override
    public void debug(String message, Throwable thrown) {
        if (LogLevel.DEBUG.isEnabled(currentLevel)) {
            System.out.println(formatMessage(LogLevel.DEBUG, message));
            thrown.printStackTrace(System.out);
        }
    }

    @Override
    public void info(String message) {
        if (LogLevel.INFO.isEnabled(currentLevel)) {
            System.out.println(formatMessage(LogLevel.INFO, message));
        }
    }

    @Override
    public void info(String message, Throwable thrown) {
        if (LogLevel.INFO.isEnabled(currentLevel)) {
            System.out.println(formatMessage(LogLevel.INFO, message));
            thrown.printStackTrace(System.out);
        }
    }

    @Override
    public void warn(String message) {
        if (LogLevel.WARN.isEnabled(currentLevel)) {
            System.err.println(formatMessage(LogLevel.WARN, message));
        }
    }

    @Override
    public void warn(String message, Throwable thrown) {
        if (LogLevel.WARN.isEnabled(currentLevel)) {
            System.err.println(formatMessage(LogLevel.WARN, message));
            thrown.printStackTrace(System.err);
        }
    }

    @Override
    public void error(String message) {
        if (LogLevel.ERROR.isEnabled(currentLevel)) {
            System.err.println(formatMessage(LogLevel.ERROR, message));
        }
    }

    @Override
    public void error(String message, Throwable thrown) {
        if (LogLevel.ERROR.isEnabled(currentLevel)) {
            System.err.println(formatMessage(LogLevel.ERROR, message));
            thrown.printStackTrace(System.err);
        }
    }

    @Override
    public boolean isTraceEnabled() {
        return LogLevel.TRACE.isEnabled(currentLevel);
    }

    @Override
    public boolean isDebugEnabled() {
        return LogLevel.DEBUG.isEnabled(currentLevel);
    }

    @Override
    public boolean isInfoEnabled() {
        return LogLevel.INFO.isEnabled(currentLevel);
    }

    @Override
    public boolean isWarnEnabled() {
        return LogLevel.WARN.isEnabled(currentLevel);
    }

    @Override
    public boolean isErrorEnabled() {
        return LogLevel.ERROR.isEnabled(currentLevel);
    }
} 