package io.csh.utils.logging;

import org.junit.jupiter.api.Test;

/**
 * Logging 클래스의 간편한 사용법을 테스트합니다.
 */
public class LoggingTest {

    @Test
    public void testSimpleLogging() {
        // 간편한 로깅 사용법 테스트
        Logging.trace("TRACE 메시지");
        Logging.debug("DEBUG 메시지");
        Logging.info("INFO 메시지");
        Logging.warn("WARN 메시지");
        Logging.error("ERROR 메시지");
    }

    @Test
    public void testExceptionLogging() {
        try {
            // 의도적으로 예외 발생
            throw new RuntimeException("테스트 예외");
        } catch (Exception e) {
            Logging.error("예외 발생", e);
        }
    }

    @Test
    public void testLogLevelControl() {
        // 로그 레벨 설정 테스트
        Logging.setLogLevel(LogLevel.DEBUG);
        Logging.info("DEBUG 레벨에서 INFO 메시지");
        Logging.debug("DEBUG 레벨에서 DEBUG 메시지");
        
        Logging.setLogLevel(LogLevel.WARN);
        Logging.info("WARN 레벨에서 INFO 메시지 (출력되지 않음)");
        Logging.warn("WARN 레벨에서 WARN 메시지");
        Logging.error("WARN 레벨에서 ERROR 메시지");
    }

    @Test
    public void testConditionalLogging() {
        // 조건부 로깅 테스트
        if (Logging.isDebugEnabled()) {
            Logging.debug("조건부 DEBUG 메시지");
        }
        
        if (Logging.isInfoEnabled()) {
            Logging.info("조건부 INFO 메시지");
        }
    }
} 