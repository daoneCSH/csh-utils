package io.csh.utils.logging;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AsyncLoggerTest {
    
    @BeforeEach
    void setUp() {
        // 각 테스트 전에 새로운 executor 생성
        AsyncLogger.initialize();
    }
    
    @AfterEach
    void tearDown() {
        AsyncLogger.shutdown();
    }

    @Test
    void testAsyncInfoLogging() {
        assertDoesNotThrow(() -> AsyncLogger.info("Test async info log"));
    }

    @Test
    void testAsyncDebugLogging() {
        assertDoesNotThrow(() -> AsyncLogger.debug("Test async debug log"));
    }

    @Test
    void testAsyncWarnLogging() {
        assertDoesNotThrow(() -> AsyncLogger.warn("Test async warn log"));
    }

    @Test
    void testAsyncErrorLogging() {
        assertDoesNotThrow(() -> AsyncLogger.error("Test async error log"));
        assertDoesNotThrow(() -> AsyncLogger.error("Test async error log with exception", 
            new RuntimeException("Test async exception")));
    }
} 