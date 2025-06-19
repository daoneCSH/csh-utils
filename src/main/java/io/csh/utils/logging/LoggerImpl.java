package io.csh.utils.logging;

/**
 * 로거 구현체
 * 
 * <p>표준 로깅 레벨을 모두 지원하며, 설정된 로그 레벨에 따라
 * 출력을 제어합니다. 파일 로깅, 중복 로그 방지, 로그 회전 등의
 * 고급 기능을 제공합니다.</p>
 */
public final class LoggerImpl implements Logger {
    private final String name;
    private static volatile LogLevel currentLevel;
    private final LogConfig config;
    private final LogFileManager fileManager;

    static {
        initializeLogLevel();
    }

    private LoggerImpl(Class<?> clazz) {
        this.name = clazz.getName();
        this.config = LogConfig.getInstance();
        this.fileManager = LogFileManager.getInstance();
        this.fileManager.initialize();
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
            currentLevel = level;
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid log level: " + levelStr + ", using INFO as default");
            currentLevel = LogLevel.INFO;
        }
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
     * 로그 메시지를 출력합니다.
     *
     * @param level 로그 레벨
     * @param message 로그 메시지
     */
    private void log(LogLevel level, String message) {
        if (!level.isEnabled(currentLevel)) {
            return;
        }
        
        String formattedMessage = LogFormatter.format(level, name, message);
        outputLog(formattedMessage, level);
    }

    /**
     * 예외 정보를 포함한 로그 메시지를 출력합니다.
     *
     * @param level 로그 레벨
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    private void log(LogLevel level, String message, Throwable thrown) {
        if (!level.isEnabled(currentLevel)) {
            return;
        }
        
        String formattedMessage = LogFormatter.formatWithException(level, name, message, thrown);
        outputLog(formattedMessage, level);
    }

    /**
     * 로그를 출력합니다.
     *
     * @param message 포맷팅된 로그 메시지
     * @param level 로그 레벨
     */
    private void outputLog(String message, LogLevel level) {
        // 콘솔 출력
        if (config.isConsoleOutput()) {
            if (level == LogLevel.ERROR || level == LogLevel.WARN) {
                System.err.println(message);
            } else {
                System.out.println(message);
            }
        }
        
        // 파일 출력
        fileManager.writeToFile(message);
    }

    /**
     * 중복 로그 방지를 적용하여 로그를 출력합니다.
     *
     * @param level 로그 레벨
     * @param id 로그 식별자
     * @param message 로그 메시지
     * @param minIntervalSeconds 최소 간격 (초)
     */
    public void logWithDuplicateFilter(LogLevel level, String id, String message, int minIntervalSeconds) {
        if (!level.isEnabled(currentLevel)) {
            return;
        }
        
        if (!DuplicateLogFilter.shouldLog(id, minIntervalSeconds)) {
            return;
        }
        
        String formattedMessage = LogFormatter.formatSimple(level, id, message);
        outputLog(formattedMessage, level);
    }

    @Override
    public void trace(String message) {
        log(LogLevel.TRACE, message);
    }

    @Override
    public void trace(String message, Throwable thrown) {
        log(LogLevel.TRACE, message, thrown);
    }

    @Override
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    @Override
    public void debug(String message, Throwable thrown) {
        log(LogLevel.DEBUG, message, thrown);
    }

    @Override
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    @Override
    public void info(String message, Throwable thrown) {
        log(LogLevel.INFO, message, thrown);
    }

    @Override
    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    @Override
    public void warn(String message, Throwable thrown) {
        log(LogLevel.WARN, message, thrown);
    }

    @Override
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    @Override
    public void error(String message, Throwable thrown) {
        log(LogLevel.ERROR, message, thrown);
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