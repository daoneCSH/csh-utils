package io.csh.utils.logging;

import java.nio.file.Path;

/**
 * 로그 파일 이벤트를 처리하는 핸들러 인터페이스
 */
public interface LogEventHandler {
    
    /**
     * 로그 파일 로테이션 이벤트 처리
     * @param event 로그 파일 이벤트
     */
    void onLogRotated(LogFileEvent event);
    
    /**
     * 로그 파일 압축 이벤트 처리
     * @param event 로그 파일 이벤트
     */
    void onLogCompressed(LogFileEvent event);
    
    /**
     * 로그 파일 보관 이벤트 처리
     * @param event 로그 파일 이벤트
     */
    void onLogArchived(LogFileEvent event);
    
    /**
     * 로그 파일 삭제 이벤트 처리
     * @param event 로그 파일 이벤트
     */
    void onLogDeleted(LogFileEvent event);
} 