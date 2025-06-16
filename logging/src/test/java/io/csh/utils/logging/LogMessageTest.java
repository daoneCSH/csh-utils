package io.csh.utils.logging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogMessageTest {
    
    @Test
    void testLogMessageCreation() {
        String className = "TestClass";
        String message = "테스트 메시지";
        LogMessage logMessage = new LogMessage(LogLevel.INFO, className, message, null);
        
        String output = logMessage.toString();
        
        assertTrue(output.contains(className));
        assertTrue(output.contains(message));
        assertTrue(output.contains("INFO"));
        assertTrue(output.contains(Thread.currentThread().getName()));
    }
    
    @Test
    void testLogMessageWithException() {
        String className = "TestClass";
        String message = "테스트 메시지";
        Exception exception = new RuntimeException("테스트 예외");
        LogMessage logMessage = new LogMessage(LogLevel.ERROR, className, message, exception);
        
        String output = logMessage.toString();
        
        assertTrue(output.contains(className));
        assertTrue(output.contains(message));
        assertTrue(output.contains("ERROR"));
        assertTrue(output.contains(exception.toString()));
    }
    
    @Test
    void testTimestampFormat() {
        LogMessage logMessage = new LogMessage(LogLevel.INFO, "TestClass", "메시지", null);
        String output = logMessage.toString();
        
        // 타임스탬프 형식 검증 (yyyy-MM-dd HH:mm:ss.SSS)
        assertTrue(output.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}.*"));
    }

    @Test
    void testLogMessageFormat() {
        LogMessage logMessage = new LogMessage(LogLevel.INFO, "TestLogger", "테스트 메시지", null);
        String result = logMessage.toString();
        assertTrue(result.contains("INFO"));
        assertTrue(result.contains("TestLogger"));
        assertTrue(result.contains("테스트 메시지"));
    }

    @Test
    void testLogMessageWithThrowable() {
        Exception ex = new Exception("예외 메시지");
        LogMessage logMessage = new LogMessage(LogLevel.ERROR, "TestLogger", "에러 발생", ex);
        String result = logMessage.toString();
        assertTrue(result.contains("ERROR"));
        assertTrue(result.contains("에러 발생"));
        assertTrue(result.contains("예외 메시지"));
    }
} 