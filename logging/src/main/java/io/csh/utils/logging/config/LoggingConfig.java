package io.csh.utils.logging.config;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import io.csh.utils.logging.LogLevel;
import io.csh.utils.core.config.PropertyManager;

/**
 * 로깅 설정을 관리하는 클래스
 */
public class LoggingConfig {
    private static final LoggingConfig INSTANCE = new LoggingConfig();
    
    private String logFilePath;
    private String logFileName;
    private String logFileMaxSize;
    private String logFileMaxTotalSize;
    private String logFileRetentionDays;
    private String logFileCompressionUnit;
    private String logFileCompressionValue;
    private String logFileCompressionFormat;
    private String logPattern;
    
    private LoggingConfig() {
        initializeFromSystemProperties();
    }
    
    public static LoggingConfig getInstance() {
        return INSTANCE;
    }
    
    private void initializeFromSystemProperties() {
        logFilePath = System.getProperty(LoggingProperties.LOG_FILE_PATH, LoggingProperties.DEFAULT_LOG_FILE_PATH);
        logFileName = System.getProperty(LoggingProperties.LOG_FILE_NAME, LoggingProperties.DEFAULT_LOG_FILE_NAME);
        logFileMaxSize = System.getProperty(LoggingProperties.LOG_FILE_MAX_SIZE, LoggingProperties.DEFAULT_LOG_FILE_MAX_SIZE);
        logFileMaxTotalSize = System.getProperty(LoggingProperties.LOG_FILE_MAX_TOTAL_SIZE, LoggingProperties.DEFAULT_LOG_FILE_MAX_TOTAL_SIZE);
        logFileRetentionDays = System.getProperty(LoggingProperties.LOG_FILE_RETENTION_DAYS, LoggingProperties.DEFAULT_LOG_FILE_RETENTION_DAYS);
        logFileCompressionUnit = System.getProperty(LoggingProperties.LOG_FILE_COMPRESSION_UNIT, LoggingProperties.DEFAULT_LOG_FILE_COMPRESSION_UNIT);
        logFileCompressionValue = System.getProperty(LoggingProperties.LOG_FILE_COMPRESSION_VALUE, LoggingProperties.DEFAULT_LOG_FILE_COMPRESSION_VALUE);
        logFileCompressionFormat = System.getProperty(LoggingProperties.LOG_FILE_COMPRESSION_FORMAT, LoggingProperties.DEFAULT_LOG_FILE_COMPRESSION_FORMAT);
        logPattern = System.getProperty("logging.pattern", "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
    }
    
    public Path getLogDirectory() {
        return Paths.get(logFilePath);
    }
    
    public String getLogFileName() {
        return logFileName;
    }
    
    public String getLogFileMaxSize() {
        return logFileMaxSize;
    }
    
    public void setLogFileMaxSize(String logFileMaxSize) {
        this.logFileMaxSize = logFileMaxSize;
    }
    
    public String getLogFileMaxTotalSize() {
        return logFileMaxTotalSize;
    }
    
    public void setLogFileMaxTotalSize(String logFileMaxTotalSize) {
        this.logFileMaxTotalSize = logFileMaxTotalSize;
    }
    
    public String getLogFileRetentionDays() {
        return logFileRetentionDays;
    }
    
    public void setLogFileRetentionDays(String logFileRetentionDays) {
        this.logFileRetentionDays = logFileRetentionDays;
    }
    
    public String getLogFileCompressionUnit() {
        return logFileCompressionUnit;
    }
    
    public void setLogFileCompressionUnit(String logFileCompressionUnit) {
        this.logFileCompressionUnit = logFileCompressionUnit;
    }
    
    public String getLogFileCompressionValue() {
        return logFileCompressionValue;
    }
    
    public void setLogFileCompressionValue(String logFileCompressionValue) {
        this.logFileCompressionValue = logFileCompressionValue;
    }
    
    public String getLogFileCompressionFormat() {
        return logFileCompressionFormat;
    }
    
    public void setLogFileCompressionFormat(String logFileCompressionFormat) {
        this.logFileCompressionFormat = logFileCompressionFormat;
    }

    public LogLevel getLogLevel() {
        String level = PropertyManager.getProperty(LoggingProperties.LOG_LEVEL);
        return level != null ? LogLevel.valueOf(level) : LogLevel.valueOf(LoggingProperties.DEFAULT_LOG_LEVEL);
    }

    public boolean isConsoleEnabled() {
        String value = PropertyManager.getProperty(LoggingProperties.LOG_CONSOLE_ENABLED);
        return value != null ? Boolean.parseBoolean(value) : Boolean.parseBoolean(LoggingProperties.DEFAULT_LOG_CONSOLE_ENABLED);
    }

    public String getLogPattern() {
        return logPattern;
    }

    public void setLogPattern(String logPattern) {
        this.logPattern = logPattern;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }
} 