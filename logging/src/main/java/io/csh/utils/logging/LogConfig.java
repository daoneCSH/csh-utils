package io.csh.utils.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import lombok.Getter;

/**
 * 로깅 설정을 관리하는 클래스
 */
@Getter
public class LogConfig {
    private static final String CONFIG_FILE = "logging.properties";
    private static final String LOG_LEVEL_PROPERTY = "log.level";
    private static final String LOG_FILE_PROPERTY = "log.file";
    private static final String LOG_PATTERN_PROPERTY = "log.pattern";

    private static LogLevel currentLevel = LogLevel.INFO;
    private static String logFile = "application.log";
    private static String logPattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n";

    private static final LogConfig INSTANCE = new LogConfig();
    private final String logDirectory;
    private final LogLevel minLevel;

    static {
        loadConfig();
    }

    private LogConfig() {
        this.logDirectory = System.getProperty("user.home") + "/logs";
        this.minLevel = LogLevel.INFO;
    }

    public static LogConfig getInstance() {
        return INSTANCE;
    }

    public String getLogDirectory() {
        return logDirectory;
    }

    /**
     * 설정 파일에서 로깅 설정을 로드합니다.
     */
    public static void loadConfig() {
        File configFile = new File(CONFIG_FILE);
        if (!configFile.exists()) {
            return;
        }

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configFile)) {
            props.load(fis);
            
            String levelStr = props.getProperty(LOG_LEVEL_PROPERTY);
            if (levelStr != null) {
                currentLevel = LogLevel.valueOf(levelStr.toUpperCase());
            }

            String fileStr = props.getProperty(LOG_FILE_PROPERTY);
            if (fileStr != null) {
                logFile = fileStr;
            }

            String patternStr = props.getProperty(LOG_PATTERN_PROPERTY);
            if (patternStr != null) {
                logPattern = patternStr;
            }
        } catch (IOException e) {
            System.err.println("Failed to load logging configuration: " + e.getMessage());
        }
    }

    /**
     * 현재 로그 레벨을 반환합니다.
     */
    public static LogLevel getCurrentLevel() {
        return currentLevel;
    }

    /**
     * 로그 레벨을 설정합니다.
     */
    public static void setCurrentLevel(LogLevel level) {
        currentLevel = level;
    }

    /**
     * 로그 파일 경로를 반환합니다.
     */
    public static String getLogFile() {
        return logFile;
    }

    /**
     * 로그 파일 경로를 설정합니다.
     */
    public static void setLogFile(String file) {
        logFile = file;
    }

    /**
     * 로그 패턴을 반환합니다.
     */
    public static String getLogPattern() {
        return logPattern;
    }

    /**
     * 로그 패턴을 설정합니다.
     */
    public static void setLogPattern(String pattern) {
        logPattern = pattern;
    }

    public boolean isEnabled(LogLevel level) {
        return level.ordinal() >= currentLevel.ordinal();
    }
} 