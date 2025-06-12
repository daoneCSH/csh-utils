package io.csh.utils.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 로깅 설정을 관리하는 클래스
 */
public class LogConfig {
    private static final String CONFIG_FILE = "logging.properties";
    private static LogConfig instance;
    private final Properties properties;
    private final String logDirectory;
    private final long maxFileSize;
    private final int maxBackupCount;
    private LogLevel currentLevel;

    private LogConfig() {
        properties = new Properties();
        String configPath = System.getProperty("user.dir") + File.separator + CONFIG_FILE;
        
        try (FileInputStream fis = new FileInputStream(configPath)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("설정 파일을 찾을 수 없습니다: " + configPath);
        }

        // 기본값 설정
        this.logDirectory = properties.getProperty("log.directory", "logs");
        
        // 로그 파일 크기 설정 (시스템 프로퍼티 우선, 그 다음 설정 파일)
        String maxFileSizeStr = System.getProperty("spice.log.maxFileSize", 
                                 properties.getProperty("log.maxFileSize", "10485760")); // 기본값 10MB
        long tempMaxFileSize;
        try {
            tempMaxFileSize = Long.parseLong(maxFileSizeStr);
        } catch (NumberFormatException e) {
            System.err.println("잘못된 로그 파일 크기 설정: " + maxFileSizeStr + ", 기본값 10MB로 설정됩니다.");
            tempMaxFileSize = 10 * 1024 * 1024; // 10MB
        }
        this.maxFileSize = tempMaxFileSize;
        
        // 백업 파일 수 설정 (시스템 프로퍼티 우선, 그 다음 설정 파일)
        String maxBackupCountStr = System.getProperty("spice.log.maxBackupCount", 
                                    properties.getProperty("log.maxBackupCount", "5")); // 기본값 5개
        int tempMaxBackupCount;
        try {
            tempMaxBackupCount = Integer.parseInt(maxBackupCountStr);
        } catch (NumberFormatException e) {
            System.err.println("잘못된 백업 파일 수 설정: " + maxBackupCountStr + ", 기본값 5개로 설정됩니다.");
            tempMaxBackupCount = 5;
        }
        this.maxBackupCount = tempMaxBackupCount;
        
        // 로그 레벨 설정 (시스템 프로퍼티 우선, 그 다음 설정 파일)
        String levelStr = System.getProperty("spice.log.level", 
                           properties.getProperty("log.level", "INFO"));
        try {
            this.currentLevel = LogLevel.valueOf(levelStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("잘못된 로그 레벨 설정: " + levelStr + ", 기본값 INFO로 설정됩니다.");
            this.currentLevel = LogLevel.INFO;
        }
    }

    public static synchronized LogConfig getInstance() {
        if (instance == null) {
            instance = new LogConfig();
        }
        return instance;
    }

    public String getLogDirectory() {
        return logDirectory;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public int getMaxBackupCount() {
        return maxBackupCount;
    }

    public boolean isEnabled(LogLevel level) {
        return level.ordinal() >= currentLevel.ordinal();
    }

    /**
     * 현재 설정된 로그 레벨을 반환합니다.
     */
    public LogLevel getCurrentLevel() {
        return currentLevel;
    }
} 