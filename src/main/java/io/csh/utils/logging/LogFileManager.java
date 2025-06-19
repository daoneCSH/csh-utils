package io.csh.utils.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 로그 파일 관리를 담당하는 클래스
 */
public final class LogFileManager {
    private static volatile LogFileManager instance;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    private PrintWriter fileWriter;
    private File currentLogFile;
    private final LogConfig config;
    private final ScheduledExecutorService scheduler;
    private volatile boolean initialized = false;
    private int currentFileNumber = 1;
    
    private LogFileManager() {
        this.config = LogConfig.getInstance();
        this.scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "LogFileManager-Cleanup");
            t.setDaemon(true);
            return t;
        });
        scheduleCleanup();
    }
    
    /**
     * LogFileManager 싱글톤 인스턴스를 반환합니다.
     * @return LogFileManager 인스턴스
     */
    public static LogFileManager getInstance() {
        if (instance == null) {
            synchronized (LogFileManager.class) {
                if (instance == null) {
                    instance = new LogFileManager();
                }
            }
        }
        return instance;
    }
    
    /**
     * 로그 파일을 초기화합니다.
     */
    public synchronized void initialize() {
        if (initialized) {
            return;
        }
        try {
            openLogFile();
            initialized = true;
        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 로그 파일을 엽니다.
     */
    private void openLogFile() throws IOException {
        File logDir = new File(config.getLogDir());
        if (!logDir.exists()) {
            if (!logDir.mkdirs()) {
                throw new IOException("Cannot create log directory: " + config.getLogDir());
            }
        }
        
        if (!logDir.canWrite()) {
            throw new IOException("Cannot write to log directory: " + config.getLogDir());
        }
        
        String fileName = generateFileName(logDir);
        
        File logFile = new File(logDir, fileName);
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(logFile, true), StandardCharsets.UTF_8);
        fileWriter = new PrintWriter(fw, true);
        currentLogFile = logFile;
    }
    
    /**
     * 파일명을 생성합니다.
     */
    private String generateFileName(File logDir) {
        String today = LocalDate.now().format(DATE_FORMATTER);
        String baseName = config.getLogPrefix() + "_" + today;
        
        // 기존 파일들을 확인하여 넘버 결정
        int fileNumber = 1;
        File[] existingFiles = logDir.listFiles((dir, name) -> 
            name.startsWith(baseName) && name.endsWith(".log"));
        
        if (existingFiles != null) {
            for (File file : existingFiles) {
                String name = file.getName();
                // appname_2025-06-18_1.log에서 1 부분 추출
                int lastUnderscore = name.lastIndexOf('_');
                int dotIndex = name.lastIndexOf('.');
                if (lastUnderscore > 0 && dotIndex > lastUnderscore) {
                    try {
                        int num = Integer.parseInt(name.substring(lastUnderscore + 1, dotIndex));
                        fileNumber = Math.max(fileNumber, num + 1);
                    } catch (NumberFormatException e) {
                        // 숫자가 아니면 무시
                    }
                }
            }
        }
        
        currentFileNumber = fileNumber;
        return baseName + "_" + fileNumber + ".log";
    }
    
    /**
     * 로그 메시지를 파일에 기록합니다.
     * @param message 기록할 로그 메시지
     */
    public synchronized void writeToFile(String message) {
        if (!initialized) {
            Logging.raw("LogFileManager.writeToFile() - Not initialized");
            return;
        }
        if (fileWriter == null) {
            Logging.raw("LogFileManager.writeToFile() - File writer is null");
            return;
        }
        try {
            checkAndRotateFile();
            fileWriter.println(message);
            fileWriter.flush();
        } catch (Exception e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
            e.printStackTrace();
            closeFile();
        }
    }
    
    /**
     * 포맷 없이 순수 텍스트를 파일에 기록합니다.
     * 로그 레벨, 타임스탬프, 스레드 정보 없이 메시지를 그대로 기록합니다.
     * 주로 배너, 아스키아트, 구분선 등을 기록할 때 사용합니다.
     * 
     * @param message 기록할 메시지
     */
    public synchronized void writeRaw(String message) {
        if (!initialized) {
            return;
        }
        if (fileWriter == null) {
            return;
        }
        try {
            checkAndRotateFile();
            fileWriter.println(message);
            fileWriter.flush();
        } catch (Exception e) {
            System.err.println("Failed to write raw message to log file: " + e.getMessage());
            closeFile();
        }
    }
    
    /**
     * 날짜가 바뀌었는지 확인하고 필요시 파일을 교체합니다.
     */
    private void checkAndRotateFile() {
        if (!config.isLogRotationEnabled()) {
            return;
        }
        
        String today = LocalDate.now().format(DATE_FORMATTER);
        String currentFileName = currentLogFile.getName();
        
        if (!currentFileName.contains(today)) {
            try {
                closeFile();
                openLogFile();
            } catch (IOException e) {
                System.err.println("Failed to rotate log file: " + e.getMessage());
            }
        }
    }
    
    /**
     * 파일을 닫습니다.
     */
    private void closeFile() {
        if (fileWriter != null) {
            fileWriter.close();
            fileWriter = null;
        }
        currentLogFile = null;
    }
    
    /**
     * 오래된 로그 파일을 정리합니다.
     */
    private void clearOldLogs() {
        if (config.getLogKeepDays() <= 0) {
            return;
        }
        
        File logDir = new File(config.getLogDir());
        if (!logDir.exists()) {
            return;
        }
        
        File[] files = logDir.listFiles();
        if (files == null) {
            return;
        }
        
        String logPrefix = config.getLogPrefix() + "-";
        LocalDate cutoffDate = LocalDate.now().minusDays(config.getLogKeepDays());
        
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            
            String name = file.getName();
            if (!name.startsWith(logPrefix) || !name.endsWith(".log")) {
                continue;
            }
            
            // 날짜 부분 추출
            String datePart = name.substring(logPrefix.length(), name.length() - 4);
            if (datePart.length() != 8) {
                continue;
            }
            
            try {
                LocalDate fileDate = LocalDate.parse(datePart, DATE_FORMATTER);
                if (fileDate.isBefore(cutoffDate)) {
                    if (file.delete()) {
                        System.out.println("Deleted old log file: " + name);
                    }
                }
            } catch (Exception e) {
                // 날짜 파싱 실패 시 무시
            }
        }
    }
    
    /**
     * 정리 작업을 스케줄링합니다.
     */
    private void scheduleCleanup() {
        scheduler.scheduleAtFixedRate(this::clearOldLogs, 1, 24, TimeUnit.HOURS);
    }
    
    /**
     * 리소스를 정리합니다.
     */
    public void shutdown() {
        scheduler.shutdown();
        closeFile();
    }
} 