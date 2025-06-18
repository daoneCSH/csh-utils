package io.csh.utils.logging;

/**
 * 로거 인터페이스
 * 
 * <p>표준 로깅 레벨을 모두 지원하며, 각 레벨별로 예외 정보를 포함한
 * 오버로딩 메서드를 제공합니다.</p>
 */
public interface Logger {
    /**
     * TRACE 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     */
    void trace(String message);

    /**
     * TRACE 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    void trace(String message, Throwable thrown);

    /**
     * DEBUG 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     */
    void debug(String message);

    /**
     * DEBUG 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    void debug(String message, Throwable thrown);

    /**
     * INFO 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     */
    void info(String message);

    /**
     * INFO 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    void info(String message, Throwable thrown);

    /**
     * WARN 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     */
    void warn(String message);

    /**
     * WARN 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    void warn(String message, Throwable thrown);

    /**
     * ERROR 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     */
    void error(String message);

    /**
     * ERROR 레벨 로그를 기록합니다.
     *
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    void error(String message, Throwable thrown);

    /**
     * TRACE 레벨이 활성화되어 있는지 확인합니다.
     *
     * @return 활성화 여부
     */
    boolean isTraceEnabled();

    /**
     * DEBUG 레벨이 활성화되어 있는지 확인합니다.
     *
     * @return 활성화 여부
     */
    boolean isDebugEnabled();

    /**
     * INFO 레벨이 활성화되어 있는지 확인합니다.
     *
     * @return 활성화 여부
     */
    boolean isInfoEnabled();

    /**
     * WARN 레벨이 활성화되어 있는지 확인합니다.
     *
     * @return 활성화 여부
     */
    boolean isWarnEnabled();

    /**
     * ERROR 레벨이 활성화되어 있는지 확인합니다.
     *
     * @return 활성화 여부
     */
    boolean isErrorEnabled();
} 