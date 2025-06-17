package io.csh.utils.logging;

import io.csh.utils.core.config.PropertyManager;
import io.csh.utils.logging.config.LoggingConfig;
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
    private static final Logger DEFAULT_LOGGER = new Logger("Default");

    private Logger(String name) {
        this.name = name;
        String levelStr = PropertyManager.getProperty("logging.level");
        this.level = levelStr != null ? LogLevel.valueOf(levelStr) : LogLevel.INFO;
        this.outputManager = LogOutputManager.getInstance();
    }

    /**
     * 로깅 설정 상태를 반환합니다.
     * @return 로깅 설정 상태 문자열
     */
    public static String getConfigStatus() {
        return LoggingConfig.getInstance().printLoggingStatus();
    }

    /**
     * 로깅 설정 상태를 출력합니다.
     */
    public static void printConfigStatus() {
        System.out.println(getConfigStatus());
    }

    /**
     * 로거를 생성합니다.
     * @param name 로거 이름
     * @return Logger 인스턴스
     */
    public static Logger of(String name) {
        return new Logger(name);
    }

    /**
     * 현재 클래스의 이름을 사용하여 로거를 생성합니다.
     * @return Logger 인스턴스
     */
    public static Logger of() {
        return new Logger(getCallerClassName());
    }

    /**
     * 현재 클래스의 이름을 사용하여 로거를 생성합니다.
     * @param clazz 클래스
     * @return Logger 인스턴스
     */
    public static Logger of(Class<?> clazz) {
        return new Logger(clazz.getSimpleName());
    }

    private static String getCallerClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // 0: getStackTrace
        // 1: getCallerClassName
        // 2: of
        // 3: caller
        return stackTrace[3].getClassName();
    }

    // 정적 로깅 메서드
    public static void trace(String message) {
        DEFAULT_LOGGER.log(LogLevel.TRACE, message, null);
    }

    public static void trace(String message, Throwable throwable) {
        DEFAULT_LOGGER.log(LogLevel.TRACE, message, throwable);
    }

    public static void debug(String message) {
        DEFAULT_LOGGER.log(LogLevel.DEBUG, message, null);
    }

    public static void debug(String message, Throwable throwable) {
        DEFAULT_LOGGER.log(LogLevel.DEBUG, message, throwable);
    }

    public static void info(String message) {
        DEFAULT_LOGGER.log(LogLevel.INFO, message, null);
    }

    public static void info(String message, Throwable throwable) {
        DEFAULT_LOGGER.log(LogLevel.INFO, message, throwable);
    }

    public static void warn(String message) {
        DEFAULT_LOGGER.log(LogLevel.WARN, message, null);
    }

    public static void warn(String message, Throwable throwable) {
        DEFAULT_LOGGER.log(LogLevel.WARN, message, throwable);
    }

    public static void error(String message) {
        DEFAULT_LOGGER.log(LogLevel.ERROR, message, null);
    }

    public static void error(String message, Throwable throwable) {
        DEFAULT_LOGGER.log(LogLevel.ERROR, message, throwable);
    }

    public static void fatal(String message) {
        DEFAULT_LOGGER.log(LogLevel.FATAL, message, null);
    }

    public static void fatal(String message, Throwable throwable) {
        DEFAULT_LOGGER.log(LogLevel.FATAL, message, throwable);
    }

    protected void log(LogLevel level, String message, Throwable throwable) {
        // 테스트 모드에서는 로그 레벨 체크를 건너뜁니다
        if (!Boolean.getBoolean("csh.test") && !this.level.isEnabled(level)) {
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