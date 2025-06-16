package io.csh.utils.logging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogLevelTest {
    
    @Test
    void testLogLevelOrder() {
        assertTrue(LogLevel.TRACE.getLevel() < LogLevel.DEBUG.getLevel());
        assertTrue(LogLevel.DEBUG.getLevel() < LogLevel.INFO.getLevel());
        assertTrue(LogLevel.INFO.getLevel() < LogLevel.WARN.getLevel());
        assertTrue(LogLevel.WARN.getLevel() < LogLevel.ERROR.getLevel());
    }
    
    @Test
    void testIsEnabled() {
        // ERROR 레벨은 ERROR만 true
        assertFalse(LogLevel.ERROR.isEnabled(LogLevel.WARN));
        assertTrue(LogLevel.ERROR.isEnabled(LogLevel.ERROR));
        // INFO 레벨은 INFO, WARN, ERROR만 true
        assertFalse(LogLevel.INFO.isEnabled(LogLevel.DEBUG));
        assertTrue(LogLevel.INFO.isEnabled(LogLevel.INFO));
        assertTrue(LogLevel.INFO.isEnabled(LogLevel.WARN));
        assertTrue(LogLevel.INFO.isEnabled(LogLevel.ERROR));
    }
} 