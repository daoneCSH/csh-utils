package io.csh.utils.logging;

import io.csh.utils.core.config.SystemProperties;
import io.csh.utils.logging.config.LoggingProperties;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;
import java.io.File;
import java.util.Properties;
import java.io.InputStream;

/**
 * 로그 파일 관리를 담당하는 클래스
 */
public class LogFileManager {
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static LogFileManager INSTANCE;
    private final List<LogEventHandler> eventHandlers = new CopyOnWriteArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private volatile Path currentLogFile;
    private volatile long currentFileSize = 0;
    private final long maxFileSize;
    private final boolean overwrite;
    private final String appName;
    private volatile int currentFileNumber = 1;
    private final Object fileLock = new Object();
    
    private LogFileManager() {
        // 1. appName 우선순위: 시스템 프로퍼티 > 환경변수 > 기본값
        String sysAppName = System.getProperty("app.name");
        String envAppName = System.getenv("APP_NAME");
        this.appName = sysAppName != null ? sysAppName
                        : envAppName != null ? envAppName
                        : getDefaultAppName();

        // 2. overwrite 옵션: 시스템 프로퍼티로 제어 (기본 false)
        String overwriteProp = System.getProperty("log.overwrite", "false");
        this.overwrite = Boolean.parseBoolean(overwriteProp);

        this.maxFileSize = 10 * 1024 * 1024; // 10MB
        initializeLogFile();
        scheduleCompression();
        scheduleCleanup();
    }
    
    public static synchronized LogFileManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogFileManager();
        }
        return INSTANCE;
    }
    
    private String getDefaultAppName() {
        return SystemProperties.getProperty("app.name")
            .checkSystemProperty()
            .checkApplicationProperties()
            .checkJarFileName()
            .orElse("unknown");
    }
    
    private void initializeLogFile() {
        String logPath = System.getProperty(LoggingProperties.LOG_FILE_PATH, LoggingProperties.DEFAULT_LOG_FILE_PATH);
        
        try {
            Path logDir = Paths.get(logPath);
            if (!Files.exists(logDir)) {
                Files.createDirectories(logDir);
            }

            // 현재 날짜로 파일명 생성
            String date = LocalDateTime.now().format(FILE_DATE_FORMATTER);
            String fileName = String.format("%s_%s_%d.log", appName, date, currentFileNumber);
            currentLogFile = logDir.resolve(fileName);
            
            if (overwrite) {
                // Overwrite 옵션이 true면 기존 파일 삭제 후 새로 생성
                if (Files.exists(currentLogFile)) {
                    Files.delete(currentLogFile);
                }
                Files.createFile(currentLogFile);
            } else {
                // Overwrite 옵션이 false면 기존 파일이 없을 때만 생성
                if (!Files.exists(currentLogFile)) {
                    Files.createFile(currentLogFile);
                }
            }
            
            currentFileSize = Files.size(currentLogFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize log file: " + e.getMessage(), e);
        }
    }
    
    public void log(String message) throws IOException {
        synchronized (fileLock) {
            if (currentLogFile == null) {
                initializeLogFile();
            }
            
            // 디렉토리가 존재하는지 확인하고 생성
            Path parent = currentLogFile.getParent();
            if (!Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            
            if (!Files.exists(currentLogFile)) {
                Files.createFile(currentLogFile);
            }
            
            String formattedMessage = formatLogMessage(message);
            byte[] messageBytes = (formattedMessage + System.lineSeparator()).getBytes("UTF-8");
            
            Files.write(currentLogFile, messageBytes, StandardOpenOption.APPEND);
            currentFileSize += messageBytes.length;
            
            if (shouldRotateLogFile()) {
                rotateLogFile();
            }
        }
    }
    
    private String formatLogMessage(String message) {
        return String.format("%s [%s] %s",
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                Thread.currentThread().getName(),
                message);
    }
    
    private boolean shouldRotateLogFile() {
        String maxSizeStr = System.getProperty(LoggingProperties.LOG_FILE_MAX_SIZE,
                LoggingProperties.DEFAULT_LOG_FILE_MAX_SIZE);
        long maxSize = parseSize(maxSizeStr);
        return currentFileSize >= maxSize;
    }
    
    private void rotateLogFile() throws IOException {
        // 다음 파일 번호 증가
        currentFileNumber++;
        
        // 새로운 파일명 생성
        String date = LocalDateTime.now().format(FILE_DATE_FORMATTER);
        String newFileName = String.format("%s_%s_%d.log", appName, date, currentFileNumber);
        Path newLogFile = currentLogFile.getParent().resolve(newFileName);
        
        if (overwrite) {
            // Overwrite 옵션이 true면 기존 파일 삭제 후 새로 생성
            if (Files.exists(newLogFile)) {
                Files.delete(newLogFile);
            }
        }
        
        // 현재 로그 파일을 새 파일로 이동
        Files.move(currentLogFile, newLogFile);
        
        // 새로운 로그 파일 생성
        Files.createFile(currentLogFile);
        currentFileSize = 0;
        
        notifyLogRotated(newLogFile);
    }
    
    public void compressLogFiles() {
        try {
            List<Path> logFiles = Files.list(currentLogFile.getParent())
                    .filter(path -> path.toString().endsWith(".log"))
                    .filter(path -> !path.equals(currentLogFile))
                    .collect(Collectors.toList());
            
            for (Path logFile : logFiles) {
                compressLogFile(logFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to compress log files", e);
        }
    }
    
    private void compressLogFile(Path logFile) throws IOException {
        Path compressedFile = logFile.resolveSibling(logFile.getFileName() + ".gz");
        
        try (GZIPOutputStream gzipOut = new GZIPOutputStream(Files.newOutputStream(compressedFile))) {
            Files.copy(logFile, gzipOut);
        }
        
        Files.delete(logFile);
        notifyLogCompressed(compressedFile);
    }
    
    public void cleanupOldLogs() {
        try {
            String retentionDaysStr = System.getProperty(LoggingProperties.LOG_FILE_RETENTION_DAYS,
                    LoggingProperties.DEFAULT_LOG_FILE_RETENTION_DAYS);
            int retentionDays = Integer.parseInt(retentionDaysStr);
            
            LocalDateTime cutoffDate = LocalDateTime.now().minusDays(retentionDays);
            
            List<Path> oldFiles = Files.list(currentLogFile.getParent())
                    .filter(path -> {
                        String fileName = path.getFileName().toString();
                        if (fileName.endsWith(".gz")) {
                            fileName = fileName.substring(0, fileName.length() - 3);
                        }
                        if (fileName.contains(".")) {
                            String dateStr = fileName.substring(fileName.lastIndexOf(".") + 1);
                            try {
                                LocalDateTime fileDate = LocalDateTime.parse(dateStr, FILE_DATE_FORMATTER);
                                return fileDate.isBefore(cutoffDate);
                            } catch (Exception e) {
                                return false;
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
            
            for (Path oldFile : oldFiles) {
                Files.delete(oldFile);
                notifyLogDeleted(oldFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to cleanup old logs", e);
        }
    }
    
    public void deleteLogFile(String fileName) throws IOException {
        Path fileToDelete = currentLogFile.getParent().resolve(fileName);
        if (Files.exists(fileToDelete)) {
            Files.delete(fileToDelete);
            notifyLogDeleted(fileToDelete);
        }
    }
    
    private void scheduleCompression() {
        String unit = System.getProperty(LoggingProperties.LOG_FILE_COMPRESSION_UNIT,
                LoggingProperties.DEFAULT_LOG_FILE_COMPRESSION_UNIT);
        String value = System.getProperty(LoggingProperties.LOG_FILE_COMPRESSION_VALUE,
                LoggingProperties.DEFAULT_LOG_FILE_COMPRESSION_VALUE);
        
        long period = parseCompressionPeriod(unit, value);
        scheduler.scheduleAtFixedRate(this::compressLogFiles, period, period, TimeUnit.MILLISECONDS);
    }
    
    private void scheduleCleanup() {
        scheduler.scheduleAtFixedRate(this::cleanupOldLogs, 1, 1, TimeUnit.DAYS);
    }
    
    private long parseCompressionPeriod(String unit, String value) {
        int period = Integer.parseInt(value);
        switch (unit.toUpperCase()) {
            case "DAY":
                return TimeUnit.DAYS.toMillis(period);
            case "WEEK":
                return TimeUnit.DAYS.toMillis(period * 7);
            case "MONTH":
                return TimeUnit.DAYS.toMillis(period * 30);
            default:
                return TimeUnit.DAYS.toMillis(1);
        }
    }
    
    private long parseSize(String sizeStr) {
        sizeStr = sizeStr.toUpperCase();
        if (sizeStr.endsWith("MB")) {
            return Long.parseLong(sizeStr.substring(0, sizeStr.length() - 2)) * 1024 * 1024;
        } else if (sizeStr.endsWith("KB")) {
            return Long.parseLong(sizeStr.substring(0, sizeStr.length() - 2)) * 1024;
        } else if (sizeStr.endsWith("B")) {
            return Long.parseLong(sizeStr.substring(0, sizeStr.length() - 1));
        }
        return Long.parseLong(sizeStr);
    }
    
    public void addEventHandler(LogEventHandler handler) {
        eventHandlers.add(handler);
    }
    
    public void removeEventHandler(LogEventHandler handler) {
        eventHandlers.remove(handler);
    }
    
    private void notifyLogRotated(Path rotatedFile) {
        LogFileEvent event = new LogFileEvent(rotatedFile);
        eventHandlers.forEach(handler -> handler.onLogRotated(event));
    }
    
    private void notifyLogCompressed(Path compressedFile) {
        LogFileEvent event = new LogFileEvent(compressedFile);
        eventHandlers.forEach(handler -> handler.onLogCompressed(event));
    }
    
    private void notifyLogArchived(Path archivedFile) {
        LogFileEvent event = new LogFileEvent(archivedFile);
        eventHandlers.forEach(handler -> handler.onLogArchived(event));
    }
    
    private void notifyLogDeleted(Path deletedFile) {
        LogFileEvent event = new LogFileEvent(deletedFile);
        eventHandlers.forEach(handler -> handler.onLogDeleted(event));
    }
    
    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    
    public void setLogDirectory(Path logDir) {
        try {
            if (!Files.exists(logDir)) {
                Files.createDirectories(logDir);
            }
            currentLogFile = logDir.resolve(currentLogFile.getFileName());
            initializeLogFile();
        } catch (IOException e) {
            throw new RuntimeException("Failed to set log directory", e);
        }
    }
} 