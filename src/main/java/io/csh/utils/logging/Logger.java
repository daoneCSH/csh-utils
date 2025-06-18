package io.csh.utils.logging;

/**
 * 로거 인터페이스
 */
public interface Logger {
    /**
     * 정보 로그를 기록합니다.
     *
     * @param message 로그 메시지
     */
    void info(String message);

    /**
     * 정보 로그를 기록합니다.
     *
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    void info(String message, Throwable thrown);

    /**
     * 에러 로그를 기록합니다.
     *
     * @param message 로그 메시지
     */
    void error(String message);

    /**
     * 에러 로그를 기록합니다.
     *
     * @param message 로그 메시지
     * @param thrown 예외 정보
     */
    void error(String message, Throwable thrown);
} 