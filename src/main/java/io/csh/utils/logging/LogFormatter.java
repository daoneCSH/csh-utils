package io.csh.utils.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 로그 메시지 포맷팅을 담당하는 클래스
 */
public final class LogFormatter {
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    private LogFormatter() {}
    
    /**
     * 기본 로그 메시지를 포맷팅합니다.
     * 
     * @param level 로그 레벨
     * @param loggerName 로거 이름
     * @param message 로그 메시지
     * @return 포맷팅된 로그 메시지
     */
    public static String format(LogLevel level, String loggerName, String message) {
        StringBuilder sb = new StringBuilder(256);
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        String threadName = Thread.currentThread().getName();
        
        sb.append(timestamp)
          .append(" [").append(threadName).append("] ")
          .append(String.format("%-5s", level.name()))
          .append(" ").append(loggerName)
          .append(" - ").append(message);
        
        return sb.toString();
    }
    
    /**
     * 예외 정보를 포함한 로그 메시지를 포맷팅합니다.
     * 
     * @param level 로그 레벨
     * @param loggerName 로거 이름
     * @param message 로그 메시지
     * @param thrown 예외
     * @return 포맷팅된 로그 메시지
     */
    public static String formatWithException(LogLevel level, String loggerName, String message, Throwable thrown) {
        StringBuilder sb = new StringBuilder(512);
        sb.append(format(level, loggerName, message));
        
        if (thrown != null) {
            sb.append("\n").append(formatStackTrace(thrown));
        }
        
        return sb.toString();
    }
    
    /**
     * 예외의 스택 트레이스를 포맷팅합니다.
     * 
     * @param thrown 예외
     * @return 포맷팅된 스택 트레이스
     */
    public static String formatStackTrace(Throwable thrown) {
        if (thrown == null) {
            return "";
        }
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        try {
            thrown.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
    
    /**
     * 간단한 로그 메시지를 포맷팅합니다 (Spiceware 스타일).
     * 
     * @param level 로그 레벨
     * @param id 로그 ID
     * @param message 로그 메시지
     * @return 포맷팅된 로그 메시지
     */
    public static String formatSimple(LogLevel level, String id, String message) {
        StringBuilder sb = new StringBuilder(128);
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        
        sb.append(timestamp)
          .append(" ").append(level.name())
          .append(" [CSH:").append(id).append("] ")
          .append(message);
        
        return sb.toString();
    }
    
    /**
     * 객체를 문자열로 변환합니다.
     * 
     * @param obj 변환할 객체
     * @return 문자열
     */
    public static String toString(Object obj) {
        return obj == null ? "null" : obj.toString();
    }
} 