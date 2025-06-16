package io.csh.utils.logging.format;

import io.csh.utils.logging.LogMessage;

import java.time.format.DateTimeFormatter;

public class LogFormat {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    private final String pattern;

    public LogFormat(String pattern) {
        this.pattern = pattern;
    }

    public String format(LogMessage message) {
        String result = pattern;
        
        // 날짜 포맷
        result = result.replace("%d{yyyy-MM-dd HH:mm:ss.SSS}", 
            DATE_FORMATTER.format(message.getTimestamp()));
        
        // 스레드 이름
        result = result.replace("%thread", 
            Thread.currentThread().getName());
        
        // 로그 레벨
        result = result.replace("%-5level", 
            String.format("%-5s", message.getLevel()));
        
        // 로거 이름
        result = result.replace("%logger{36}", 
            abbreviate(message.getLoggerName(), 36));
        
        // 메시지
        result = result.replace("%msg", 
            message.getMessage());
        
        // 줄바꿈
        result = result.replace("%n", 
            System.lineSeparator());
        
        // 예외 스택 트레이스
        if (message.getThrowable() != null) {
            result += "\n" + formatThrowable(message.getThrowable());
        }
        
        return result;
    }
    
    private String abbreviate(String name, int maxLength) {
        if (name.length() <= maxLength) {
            return name;
        }
        
        String[] parts = name.split("\\.");
        if (parts.length == 1) {
            return name.substring(0, maxLength - 3) + "...";
        }
        
        StringBuilder result = new StringBuilder();
        int remainingLength = maxLength - 3; // "..." 길이
        
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (i == parts.length - 1) {
                // 마지막 부분은 가능한 한 많이 표시
                if (result.length() + part.length() <= maxLength) {
                    result.append(part);
                } else {
                    result.append(part.substring(0, remainingLength));
                }
            } else {
                // 중간 부분은 첫 글자만 표시
                if (result.length() + 2 <= remainingLength) {
                    result.append(part.charAt(0)).append(".");
                } else {
                    break;
                }
            }
        }
        
        return result.append("...").toString();
    }
    
    private String formatThrowable(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        sb.append(throwable.toString());
        
        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append("\n\tat ").append(element);
        }
        
        Throwable cause = throwable.getCause();
        if (cause != null) {
            sb.append("\nCaused by: ").append(formatThrowable(cause));
        }
        
        return sb.toString();
    }
} 