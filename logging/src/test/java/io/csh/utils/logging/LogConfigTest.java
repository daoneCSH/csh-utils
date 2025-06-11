package io.csh.utils.logging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogConfigTest {
    
    @Test
    void testLogLevelConfiguration() {
        LogConfig.setCurrentLevel(LogLevel.DEBUG);
        assertEquals(LogLevel.DEBUG, LogConfig.getCurrentLevel());
        
        LogConfig.setCurrentLevel(LogLevel.INFO);
        assertEquals(LogLevel.INFO, LogConfig.getCurrentLevel());
    }

    @Test
    void testLogDirectory() {
        String logDir = LogConfig.getInstance().getLogDirectory();
        assertNotNull(logDir);
        assertTrue(logDir.length() > 0);
    }

    @Test
    void testLogLevelEnabled() {
        LogConfig config = LogConfig.getInstance();
        
        // INFO 레벨 설정 시
        LogConfig.setCurrentLevel(LogLevel.INFO);
        assertTrue(config.isEnabled(LogLevel.INFO));
        assertTrue(config.isEnabled(LogLevel.WARN));
        assertTrue(config.isEnabled(LogLevel.ERROR));
        assertFalse(config.isEnabled(LogLevel.DEBUG));
        assertFalse(config.isEnabled(LogLevel.TRACE));
        
        // DEBUG 레벨 설정 시
        LogConfig.setCurrentLevel(LogLevel.DEBUG);
        assertTrue(config.isEnabled(LogLevel.DEBUG));
        assertTrue(config.isEnabled(LogLevel.INFO));
        assertTrue(config.isEnabled(LogLevel.WARN));
        assertTrue(config.isEnabled(LogLevel.ERROR));
        assertFalse(config.isEnabled(LogLevel.TRACE));
    }
} 