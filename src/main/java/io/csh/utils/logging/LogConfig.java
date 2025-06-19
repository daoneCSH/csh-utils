package io.csh.utils.logging;

import java.io.File;

/**
 * 로그 설정을 관리하는 클래스
 */
public final class LogConfig {
    private static volatile LogConfig instance;
    
    private String logDir = "logs";
    private boolean logRotationEnabled = true;
    private int logKeepDays = 30;
    private String logPrefix = "csh";
    private boolean consoleOutput = true;
    
    private LogConfig() {}
    
    /**
     * LogConfig 싱글톤 인스턴스를 반환합니다.
     * @return LogConfig 인스턴스
     */
    public static LogConfig getInstance() {
        if (instance == null) {
            synchronized (LogConfig.class) {
                if (instance == null) {
                    instance = new LogConfig();
                    instance.initializeFromSystemProperties();
                }
            }
        }
        return instance;
    }
    
    /**
     * 시스템 프로퍼티에서 설정을 초기화합니다.
     */
    private void initializeFromSystemProperties() {
        String logDirProp = System.getProperty("csh.logging.dir");
        if (logDirProp != null) {
            this.logDir = logDirProp;
        }
        
        String rotationProp = System.getProperty("csh.logging.rotation.enabled");
        if (rotationProp != null) {
            this.logRotationEnabled = Boolean.parseBoolean(rotationProp);
        }
        
        String keepDaysProp = System.getProperty("csh.logging.keep.days");
        if (keepDaysProp != null) {
            try {
                this.logKeepDays = Integer.parseInt(keepDaysProp);
            } catch (NumberFormatException e) {
                // 기본값 유지
            }
        }
        
        String prefixProp = System.getProperty("csh.logging.prefix");
        if (prefixProp != null) {
            this.logPrefix = prefixProp;
        }
        
        String consoleProp = System.getProperty("csh.logging.console");
        if (consoleProp != null) {
            this.consoleOutput = Boolean.parseBoolean(consoleProp);
        }
    }
    
    /**
     * 인스턴스를 리셋합니다.
     * 주로 테스트에서 사용됩니다.
     */
    public static void reset() {
        synchronized (LogConfig.class) {
            instance = null;
        }
    }
    
    /**
     * 로그 디렉토리 경로를 반환합니다.
     * @return 로그 디렉토리 경로
     */
    public String getLogDir() {
        return logDir;
    }
    
    /**
     * 로그 디렉토리 경로를 설정합니다.
     * @param logDir 로그 디렉토리 경로
     */
    public void setLogDir(String logDir) {
        this.logDir = logDir;
    }
    
    /**
     * 로그 회전 활성화 여부를 반환합니다.
     * @return 로그 회전 활성화 여부
     */
    public boolean isLogRotationEnabled() {
        return logRotationEnabled;
    }
    
    /**
     * 로그 회전 활성화 여부를 설정합니다.
     * @param logRotationEnabled 로그 회전 활성화 여부
     */
    public void setLogRotationEnabled(boolean logRotationEnabled) {
        this.logRotationEnabled = logRotationEnabled;
    }
    
    /**
     * 로그 보관 일수를 반환합니다.
     * @return 로그 보관 일수
     */
    public int getLogKeepDays() {
        return logKeepDays;
    }
    
    /**
     * 로그 보관 일수를 설정합니다.
     * @param logKeepDays 로그 보관 일수
     */
    public void setLogKeepDays(int logKeepDays) {
        this.logKeepDays = logKeepDays;
    }
    
    /**
     * 로그 파일 접두사를 반환합니다.
     * @return 로그 파일 접두사
     */
    public String getLogPrefix() {
        return logPrefix;
    }
    
    /**
     * 로그 파일 접두사를 설정합니다.
     * @param logPrefix 로그 파일 접두사
     */
    public void setLogPrefix(String logPrefix) {
        this.logPrefix = logPrefix;
    }
    
    /**
     * 콘솔 출력 활성화 여부를 반환합니다.
     * @return 콘솔 출력 활성화 여부
     */
    public boolean isConsoleOutput() {
        return consoleOutput;
    }
    
    /**
     * 콘솔 출력 활성화 여부를 설정합니다.
     * @param consoleOutput 콘솔 출력 활성화 여부
     */
    public void setConsoleOutput(boolean consoleOutput) {
        this.consoleOutput = consoleOutput;
    }
} 