package io.csh.utils.logging;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 로거 팩토리
 * 
 * <p>로거 인스턴스를 관리하고 전역 로그 레벨을 제어합니다.</p>
 */
public final class LoggerFactory {
    private static final ConcurrentMap<String, Logger> loggers = new ConcurrentHashMap<>();

    static {
        initializeLogLevel();
    }

    private LoggerFactory() {
        throw new AssertionError("Utility class");
    }

    /**
     * 로그 레벨을 초기화합니다.
     * 시스템 프로퍼티나 환경 변수에서 설정을 읽어옵니다.
     */
    private static void initializeLogLevel() {
        String levelStr = System.getProperty("csh.logging.level");
        if (levelStr == null) {
            levelStr = System.getenv("CSH_LOGGING_LEVEL");
        }
        if (levelStr == null) {
            levelStr = "INFO"; // 기본값
        }
        
        try {
            LogLevel level = LogLevel.fromString(levelStr);
            LoggerImpl.setLogLevel(level);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid log level: " + levelStr + ", using INFO as default");
            LoggerImpl.setLogLevel(LogLevel.INFO);
        }
    }

    /**
     * 로거를 반환합니다.
     *
     * @param clazz 로거를 사용할 클래스
     * @return Logger 인스턴스
     */
    public static Logger getLogger(Class<?> clazz) {
        return loggers.computeIfAbsent(clazz.getName(), k -> LoggerImpl.getInstance(clazz));
    }

    /**
     * 전역 로그 레벨을 설정합니다.
     *
     * @param level 설정할 로그 레벨
     */
    public static void setLogLevel(LogLevel level) {
        LoggerImpl.setLogLevel(level);
    }

    /**
     * 전역 로그 레벨을 반환합니다.
     *
     * @return 현재 로그 레벨
     */
    public static LogLevel getLogLevel() {
        return LoggerImpl.getLogLevel();
    }

    /**
     * 모든 로거 인스턴스를 제거합니다.
     * 주로 테스트나 리소스 정리 시 사용됩니다.
     */
    public static void clear() {
        loggers.clear();
    }
} 