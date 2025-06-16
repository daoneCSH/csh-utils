package io.csh.utils.logging;

import io.csh.utils.logging.config.LoggingProperties;
import io.csh.utils.logging.LogFileManager;
import io.csh.utils.logging.LogEventHandler;
import io.csh.utils.logging.LogFileEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class LogFileManagerTest {

    @TempDir
    Path tempDir;
    
    private Path logDir;
    private LogFileManager manager;
    
    @BeforeEach
    void setUp() throws IOException {
        // 실제 운영 환경과 동일한 구조로 디렉토리 생성
        logDir = tempDir.resolve("logs");
        Files.createDirectories(logDir);
        
        // 기본 설정
        System.setProperty(LoggingProperties.LOG_FILE_PATH, logDir.toString());
        System.setProperty(LoggingProperties.LOG_FILE_NAME, "application.log");
        System.setProperty("app.name", "test-app");
        
        manager = LogFileManager.getInstance();
    }
    
    @AfterEach
    void tearDown() {
        if (manager != null) {
            manager.shutdown();
        }
    }
    
    @Test
    void testLogFileDeletion() throws IOException {
        // Given
        String message = "Test message";
        manager.log(message);
        
        // When
        manager.deleteLogFile("test-app_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "_1.log");
        
        // Then
        assertFalse(Files.exists(logDir.resolve("test-app_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "_1.log")), 
            "로그 파일이 삭제되지 않았습니다.");
    }

    // appname 추출 관련 테스트는 환경에 따라 실패할 수 있으므로 추후 별도 환경에서 진행
    // @Test
    // void testAppNameExtraction() {
    //     // 예시: 시스템 프로퍼티, 환경변수, application.properties, jar 파일명 등에서 appname 추출 테스트
    //     // ...
    // }
} 