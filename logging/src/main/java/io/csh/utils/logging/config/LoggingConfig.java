package io.csh.utils.logging.config;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import io.csh.utils.logging.LogLevel;
import io.csh.utils.core.config.SystemProperties;

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
        initializeFromProperties();
    }
    
    public static LoggingConfig getInstance() {
        return INSTANCE;
    }
    
    private void initializeFromProperties() {
        // SystemProperties를 통해 설정을 가져옴
        logFilePath = SystemProperties.getProperty(LoggingProperties.LOG_FILE_PATH)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(LoggingProperties.DEFAULT_LOG_FILE_PATH);
            
        logFileName = SystemProperties.getProperty(LoggingProperties.LOG_FILE_NAME)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(LoggingProperties.DEFAULT_LOG_FILE_NAME);
            
        logFileMaxSize = SystemProperties.getProperty(LoggingProperties.LOG_FILE_MAX_SIZE)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(LoggingProperties.DEFAULT_LOG_FILE_MAX_SIZE);
            
        logFileMaxTotalSize = SystemProperties.getProperty(LoggingProperties.LOG_FILE_MAX_TOTAL_SIZE)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(LoggingProperties.DEFAULT_LOG_FILE_MAX_TOTAL_SIZE);
            
        logFileRetentionDays = SystemProperties.getProperty(LoggingProperties.LOG_FILE_RETENTION_DAYS)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(LoggingProperties.DEFAULT_LOG_FILE_RETENTION_DAYS);
            
        logFileCompressionUnit = SystemProperties.getProperty(LoggingProperties.LOG_FILE_COMPRESSION_UNIT)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(LoggingProperties.DEFAULT_LOG_FILE_COMPRESSION_UNIT);
            
        logFileCompressionValue = SystemProperties.getProperty(LoggingProperties.LOG_FILE_COMPRESSION_VALUE)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(LoggingProperties.DEFAULT_LOG_FILE_COMPRESSION_VALUE);
            
        logFileCompressionFormat = SystemProperties.getProperty(LoggingProperties.LOG_FILE_COMPRESSION_FORMAT)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(LoggingProperties.DEFAULT_LOG_FILE_COMPRESSION_FORMAT);
            
        logPattern = SystemProperties.getProperty(LoggingProperties.LOG_PATTERN)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(LoggingProperties.DEFAULT_LOG_PATTERN);
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
        String level = SystemProperties.getProperty(LoggingProperties.LOG_LEVEL)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(LoggingProperties.DEFAULT_LOG_LEVEL);
        return LogLevel.valueOf(level);
    }

    public boolean isConsoleEnabled() {
        String value = SystemProperties.getProperty(LoggingProperties.LOG_CONSOLE_ENABLED)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(LoggingProperties.DEFAULT_LOG_CONSOLE_ENABLED);
        return Boolean.parseBoolean(value);
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

    /**
     * 현재 로깅 설정 상태를 출력합니다.
     * @return 로깅 설정 상태 문자열
     */
    public String printLoggingStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Logging Configuration Status ===\n");
        sb.append("Log Level: ").append(getLogLevel()).append("\n");
        sb.append("Console Enabled: ").append(isConsoleEnabled()).append("\n");
        sb.append("Log File Path: ").append(logFilePath).append("\n");
        sb.append("Log File Name: ").append(logFileName).append("\n");
        sb.append("Log File Max Size: ").append(logFileMaxSize).append("\n");
        sb.append("Log File Max Total Size: ").append(logFileMaxTotalSize).append("\n");
        sb.append("Log File Retention Days: ").append(logFileRetentionDays).append("\n");
        sb.append("Log File Compression: ").append(logFileCompressionUnit)
          .append(" ").append(logFileCompressionValue)
          .append(" (").append(logFileCompressionFormat).append(")\n");
        sb.append("Log Pattern: ").append(logPattern).append("\n");
        sb.append("Overwrite: ").append(System.getProperty("csh.logging.overwrite", "false")).append("\n");
        sb.append("===================================");
        return sb.toString();
    }
} 