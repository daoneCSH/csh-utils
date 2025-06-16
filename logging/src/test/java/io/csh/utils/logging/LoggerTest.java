package io.csh.utils.logging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private Logger logger;

    @BeforeEach
    void setUp() {
        System.setProperty("csh.debug", "true");
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        logger = new Logger("TestLogger");
    }

    @Test
    void testInfoLogging() {
        String message = "테스트 정보 메시지";
        logger.info(message);
        String output = outContent.toString();
        assertTrue(output.contains(message));
        assertTrue(output.contains("INFO"));
        assertTrue(output.contains("TestLogger"));
    }

    @Test
    void testErrorLoggingWithException() {
        String message = "테스트 에러 메시지";
        Exception exception = new RuntimeException("테스트 예외");
        logger.error(message, exception);
        String output = outContent.toString();
        assertTrue(output.contains(message));
        assertTrue(output.contains("ERROR"));
        assertTrue(output.contains(exception.toString()));
    }

    @Test
    void testLogFormat() {
        Logger logger = new Logger("TestLogger");
        logger.info("테스트 메시지");
        logger.error("에러 발생", new Exception("예외 메시지"));
        // 로그 출력이 예외 없이 동작하는지만 확인
    }
} 