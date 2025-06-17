package io.csh.utils.logging.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 로깅 관련 프로퍼티 키와 기본값을 정의하는 클래스
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoggingProperties {
    // 로깅 레벨
    public static final String LEVEL = "logging.level";
    public static final String DEFAULT_LEVEL = "INFO";

    // 콘솔 출력
    public static final String CONSOLE_ENABLED = "logging.console.enabled";
    public static final String DEFAULT_CONSOLE_ENABLED = "true";
    public static final String CONSOLE_PATTERN = "logging.console.pattern";
    public static final String DEFAULT_CONSOLE_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n";

    // 로그 포맷
    public static final String FORMAT_TIMESTAMP = "logging.format.timestamp";
    public static final String DEFAULT_FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FORMAT_THREAD = "logging.format.thread";
    public static final String DEFAULT_FORMAT_THREAD = "true";
    public static final String FORMAT_LEVEL = "logging.format.level";
    public static final String DEFAULT_FORMAT_LEVEL = "true";
    public static final String FORMAT_LOGGER = "logging.format.logger";
    public static final String DEFAULT_FORMAT_LOGGER = "true";
    public static final String FORMAT_MESSAGE = "logging.format.message";
    public static final String DEFAULT_FORMAT_MESSAGE = "true";
} 