package io.csh.utils.logging;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 로거 팩토리
 * 
 * <p>로거 인스턴스를 관리하고 전역 로그 레벨을 제어합니다.
 * 파일 로깅, 중복 로그 방지 등의 고급 기능을 지원합니다.</p>
 */
public final class LoggerFactory {
    private static final ConcurrentMap<String, Logger> loggers = new ConcurrentHashMap<>();

    static {
        // LoggerImpl에서 초기화하므로 여기서는 제거
    }

    private LoggerFactory() {
        throw new AssertionError("Utility class");
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
        // 기존 로거 인스턴스들을 무효화하여 새로운 설정이 적용되도록 함
        loggers.clear();
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
    
    /**
     * 중복 로그 필터를 초기화합니다.
     */
    public static void clearDuplicateFilter() {
        DuplicateLogFilter.clear();
    }
    
    /**
     * 로그 파일 매니저를 종료합니다.
     */
    public static void shutdown() {
        LogFileManager.getInstance().shutdown();
    }

    /**
     * 로그 설정을 리셋합니다.
     * 주로 테스트에서 사용됩니다.
     */
    public static void resetConfig() {
        LogConfig.reset();
    }
} 