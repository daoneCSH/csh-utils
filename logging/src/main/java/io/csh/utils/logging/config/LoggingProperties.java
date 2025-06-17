package io.csh.utils.logging.config;

/**
 * 로깅 관련 프로퍼티 키와 기본값을 정의하는 클래스
 */
public final class LoggingProperties {
    private LoggingProperties() {
        throw new AssertionError("Utility class");
    }

    // 로그 레벨 설정
    public static final String LOG_LEVEL = "csh.logging.level";
    public static final String DEFAULT_LOG_LEVEL = "INFO";

    // 로그 포맷 설정
    public static final String LOG_PATTERN = "csh.logging.pattern";
    public static final String DEFAULT_LOG_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n";

    // 로그 파일 설정
    public static final String LOG_FILE_PATH = "csh.logging.file.path";
    public static final String LOG_FILE_NAME = "csh.logging.file.name";
    public static final String LOG_FILE_MAX_SIZE = "csh.logging.file.max-size";
    public static final String LOG_FILE_MAX_TOTAL_SIZE = "csh.logging.file.max-total-size";
    public static final String LOG_FILE_RETENTION_DAYS = "csh.logging.file.retention-days";
    public static final String LOG_FILE_COMPRESSION_UNIT = "csh.logging.file.compression.unit";
    public static final String LOG_FILE_COMPRESSION_VALUE = "csh.logging.file.compression.value";
    public static final String LOG_FILE_COMPRESSION_FORMAT = "csh.logging.file.compression.format";

    // 기본값
    public static final String DEFAULT_LOG_FILE_PATH = "logs/csh-utils";
    public static final String DEFAULT_LOG_FILE_NAME = "csh-utils.log";
    public static final String DEFAULT_LOG_FILE_MAX_SIZE = "10MB";
    public static final String DEFAULT_LOG_FILE_MAX_TOTAL_SIZE = "1GB";
    public static final String DEFAULT_LOG_FILE_RETENTION_DAYS = "365";
    public static final String DEFAULT_LOG_FILE_COMPRESSION_UNIT = "WEEK";
    public static final String DEFAULT_LOG_FILE_COMPRESSION_VALUE = "1";
    public static final String DEFAULT_LOG_FILE_COMPRESSION_FORMAT = "gz";

    // 콘솔 출력 설정
    public static final String LOG_CONSOLE_ENABLED = "csh.logging.console.enabled";
    public static final String DEFAULT_LOG_CONSOLE_ENABLED = "true";

    // 파일 덮어쓰기 설정
    public static final String LOG_OVERWRITE = "csh.logging.overwrite";
    public static final String DEFAULT_LOG_OVERWRITE = "false";
} 