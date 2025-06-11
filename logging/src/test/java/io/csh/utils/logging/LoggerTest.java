package io.csh.utils.logging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {
    @Test
    void testInfoLogging() {
        assertDoesNotThrow(() -> Logger.info("Test info log"));
    }

    @Test
    void testDebugLogging() {
        assertDoesNotThrow(() -> Logger.debug("Test debug log"));
    }

    @Test
    void testWarnLogging() {
        assertDoesNotThrow(() -> Logger.warn("Test warn log"));
    }

    @Test
    void testErrorLogging() {
        assertDoesNotThrow(() -> Logger.error("Test error log"));
        assertDoesNotThrow(() -> Logger.error("Test error log with exception", new RuntimeException("Test exception")));
    }
} 