package io.csh.utils.logging;

import java.nio.file.Path;
import java.time.LocalDateTime;

/**
 * 로그 파일 관련 이벤트를 나타내는 클래스
 */
public class LogFileEvent {
    private final Path filePath;
    private final LocalDateTime timestamp;
    
    /**
     * 로그 파일 이벤트 생성
     * @param filePath 이벤트가 발생한 파일 경로
     */
    public LogFileEvent(Path filePath) {
        this.filePath = filePath;
        this.timestamp = LocalDateTime.now();
    }
    
    /**
     * 이벤트가 발생한 파일 경로 반환
     * @return 파일 경로
     */
    public Path getFilePath() {
        return filePath;
    }
    
    /**
     * 이벤트가 발생한 파일 이름 반환
     * @return 파일 이름
     */
    public String getFileName() {
        return filePath.getFileName().toString();
    }
    
    /**
     * 이벤트 발생 시간 반환
     * @return 이벤트 발생 시간
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
} 