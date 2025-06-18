package io.csh.utils.logging;

/**
 * 간편한 로깅을 위한 정적 유틸리티 클래스
 * 
 * <p>LoggerFactory.getLogger() 없이 바로 사용할 수 있는 간편한 로깅 메서드를 제공합니다.</p>
 * 
 * <p>사용 예시:</p>
 * <pre>
 * Logging.info("애플리케이션 시작");
 * Logging.debug("데이터: {}", data);
 * Logging.warn("경고 메시지");
 * Logging.error("오류 발생", exception);
 * </pre>
 */
public final class Logging {
    
    private Logging() {
        throw new AssertionError("Utility class");
    }

    /**
     * TRACE 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     */
    public static void trace(String message) {
        getLogger().trace(message);
    }

    /**
     * TRACE 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    public static void trace(String message, Throwable thrown) {
        getLogger().trace(message, thrown);
    }

    /**
     * DEBUG 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     */
    public static void debug(String message) {
        getLogger().debug(message);
    }

    /**
     * DEBUG 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    public static void debug(String message, Throwable thrown) {
        getLogger().debug(message, thrown);
    }

    /**
     * INFO 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     */
    public static void info(String message) {
        getLogger().info(message);
    }

    /**
     * INFO 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    public static void info(String message, Throwable thrown) {
        getLogger().info(message, thrown);
    }

    /**
     * WARN 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     */
    public static void warn(String message) {
        getLogger().warn(message);
    }

    /**
     * WARN 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    public static void warn(String message, Throwable thrown) {
        getLogger().warn(message, thrown);
    }

    /**
     * ERROR 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     */
    public static void error(String message) {
        getLogger().error(message);
    }

    /**
     * ERROR 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    public static void error(String message, Throwable thrown) {
        getLogger().error(message, thrown);
    }

    /**
     * TRACE 레벨이 활성화되어 있는지 확인합니다.
     *
     * @return 활성화 여부
     */
    public static boolean isTraceEnabled() {
        return getLogger().isTraceEnabled();
    }

    /**
     * DEBUG 레벨이 활성화되어 있는지 확인합니다.
     *
     * @return 활성화 여부
     */
    public static boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }

    /**
     * INFO 레벨이 활성화되어 있는지 확인합니다.
     *
     * @return 활성화 여부
     */
    public static boolean isInfoEnabled() {
        return getLogger().isInfoEnabled();
    }

    /**
     * WARN 레벨이 활성화되어 있는지 확인합니다.
     *
     * @return 활성화 여부
     */
    public static boolean isWarnEnabled() {
        return getLogger().isWarnEnabled();
    }

    /**
     * ERROR 레벨이 활성화되어 있는지 확인합니다.
     *
     * @return 활성화 여부
     */
    public static boolean isErrorEnabled() {
        return getLogger().isErrorEnabled();
    }

    /**
     * 현재 로그 레벨을 설정합니다.
     *
     * @param level 설정할 로그 레벨
     */
    public static void setLogLevel(LogLevel level) {
        LoggerFactory.setLogLevel(level);
    }

    /**
     * 현재 로그 레벨을 반환합니다.
     *
     * @return 현재 로그 레벨
     */
    public static LogLevel getLogLevel() {
        return LoggerFactory.getLogLevel();
    }

    /**
     * 호출한 클래스의 로거를 반환합니다.
     * 스택 트레이스를 통해 호출한 클래스를 자동으로 감지합니다.
     *
     * @return Logger 인스턴스
     */
    private static Logger getLogger() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // getLogger() -> trace/debug/info/warn/error() -> Logging.trace/debug/info/warn/error() -> 호출한 메서드
        // 따라서 인덱스 3이 실제 호출한 클래스
        if (stackTrace.length > 3) {
            String className = stackTrace[3].getClassName();
            try {
                Class<?> clazz = Class.forName(className);
                return LoggerFactory.getLogger(clazz);
            } catch (ClassNotFoundException e) {
                // 클래스를 찾을 수 없는 경우 기본 로거 사용
                return LoggerFactory.getLogger(Logging.class);
            }
        }
        // 스택 트레이스가 부족한 경우 기본 로거 사용
        return LoggerFactory.getLogger(Logging.class);
    }
} 