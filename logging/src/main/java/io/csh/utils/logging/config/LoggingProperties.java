package io.csh.utils.logging.config;

/**
 * 로깅 관련 프로퍼티 키와 기본값을 정의하는 클래스
 */
public final class LoggingProperties {
    private LoggingProperties() {
        throw new AssertionError("Utility class");
    }

    // 로그 레벨 설정
    public static final String LOG_LEVEL = "logging.level";
    public static final String DEFAULT_LOG_LEVEL = "INFO";

    // 로그 포맷 설정
    public static final String LOG_PATTERN = "logging.pattern";
    public static final String DEFAULT_LOG_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n";

    // 로그 파일 설정
    public static final String LOG_FILE_PATH = "logging.file.path";
    public static final String LOG_FILE_NAME = "logging.file.name";
    public static final String LOG_FILE_MAX_SIZE = "logging.file.max-size";
    public static final String LOG_FILE_MAX_TOTAL_SIZE = "logging.file.max-total-size";
    public static final String LOG_FILE_RETENTION_DAYS = "logging.file.retention-days";
    public static final String LOG_FILE_COMPRESSION_UNIT = "logging.file.compression.unit";
    public static final String LOG_FILE_COMPRESSION_VALUE = "logging.file.compression.value";
    public static final String LOG_FILE_COMPRESSION_FORMAT = "logging.file.compression.format";

    // 기본값
    public static final String DEFAULT_LOG_FILE_PATH = "logs";
    public static final String DEFAULT_LOG_FILE_NAME = "application.log";
    public static final String DEFAULT_LOG_FILE_MAX_SIZE = "10MB";
    public static final String DEFAULT_LOG_FILE_MAX_TOTAL_SIZE = "1GB";
    public static final String DEFAULT_LOG_FILE_RETENTION_DAYS = "365";
    public static final String DEFAULT_LOG_FILE_COMPRESSION_UNIT = "WEEK";
    public static final String DEFAULT_LOG_FILE_COMPRESSION_VALUE = "1";
    public static final String DEFAULT_LOG_FILE_COMPRESSION_FORMAT = "gz";

    // 콘솔 출력 설정
    public static final String LOG_CONSOLE_ENABLED = "logging.console.enabled";
    public static final String DEFAULT_LOG_CONSOLE_ENABLED = "true";
} 