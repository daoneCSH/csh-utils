package io.csh.utils.logging.storage;

import io.csh.utils.core.config.SystemProperties;
import io.csh.utils.logging.LogMessage;
import io.csh.utils.logging.config.LoggingConfig;
import io.csh.utils.logging.config.LoggingProperties;
import io.csh.utils.logging.format.LogFormat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Properties;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * 로그 데이터 저장을 담당하는 클래스
 */
public class LogStorage implements Runnable {
    private static final int QUEUE_CAPACITY = 10000;
    private static final int BATCH_SIZE = 100;
    private static final long FLUSH_INTERVAL = 1000; // milliseconds
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Pattern INDEX_PATTERN = Pattern.compile(".*-(\\d+)\\.log$");
    private static LogStorage INSTANCE;

    private final BlockingQueue<String> logQueue;
    private final AtomicBoolean running;
    private final Thread worker;
    private final LoggingConfig config;
    private final LogFormat formatter;
    private final AtomicInteger currentIndex = new AtomicInteger(1);
    private BufferedWriter writer;
    private LocalDate currentDate;
    private long currentFileSize;
    private Path currentLogFile;
    private final boolean overwrite;
    private final String appName;
    private final Object writeLock = new Object();

    private LogStorage() {
        this.logQueue = new LinkedBlockingQueue<>();
        this.running = new AtomicBoolean(true);
        this.worker = new Thread(this, "LogStorage");
        this.config = LoggingConfig.getInstance();
        this.formatter = new LogFormat(config.getLogPattern());
        this.currentDate = LocalDate.now();
        
        // 1. appName 우선순위: 시스템 프로퍼티 > 환경변수 > 기본값
        String sysAppName = System.getProperty("app.name");
        String envAppName = System.getenv("APP_NAME");
        this.appName = sysAppName != null ? sysAppName
                        : envAppName != null ? envAppName
                        : getDefaultAppName();

        // 2. overwrite 옵션: 시스템 프로퍼티로 제어 (기본 false)
        String overwriteProp = System.getProperty("log.overwrite", "false");
        this.overwrite = Boolean.parseBoolean(overwriteProp);

        initializeWriter();
        worker.start();
    }

    public static synchronized LogStorage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogStorage();
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

    private void initializeWriter() {
        try {
            Path logDir = config.getLogDirectory();
            if (!Files.exists(logDir)) {
                Files.createDirectories(logDir);
            }

            String fileName = getCurrentFileName();
            Path logFile = logDir.resolve(fileName);
            
            if (overwrite) {
                if (Files.exists(logFile)) {
                    Files.delete(logFile);
                }
                Files.createFile(logFile);
            } else {
                if (!Files.exists(logFile)) {
                    Files.createFile(logFile);
                }
            }
            
            writer = new BufferedWriter(new FileWriter(logFile.toFile(), true));
            currentFileSize = Files.size(logFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize log writer: " + e.getMessage(), e);
        }
    }

    private String getCurrentFileName() {
        String baseName = config.getLogFileName();
        String extension = "";
        int dotIndex = baseName.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = baseName.substring(dotIndex);
        }

        return String.format("%s-%s-%d%s", 
            baseName,
            LocalDateTime.now().format(FILE_DATE_FORMATTER),
            currentIndex.get(),
            extension);
    }

    public void writeLog(String message) {
        try {
            logQueue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to write log", e);
        }
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                processBatch();
                Thread.sleep(FLUSH_INTERVAL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        flush();
    }

    private void processBatch() {
        for (int i = 0; i < BATCH_SIZE; i++) {
            String message = null;
            try {
                message = logQueue.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            processLog(message);
        }
        // 배치 처리 후에도 즉시 flush
        flush();
    }

    private void processLog(String message) {
        try {
            synchronized (writeLock) {
                if (writer == null) {
                    initializeWriter();
                }
                
                writer.write(message);
                writer.newLine();
                writer.flush();
            }
            currentFileSize += message.getBytes().length;

            if (shouldRotate()) {
                rotateFile();
            }
        } catch (IOException e) {
            System.err.println("로그 쓰기 실패: " + e.getMessage());
            throw new RuntimeException("로그 쓰기 실패", e);
        }
    }

    private boolean shouldRotate() {
        return currentFileSize >= parseSize(config.getLogFileMaxSize());
    }

    private void rotateFile() {
        synchronized (writeLock) {
            try {
                if (writer != null) {
                    writer.close();
                    writer = null;
                }
                
                String currentDate = LocalDateTime.now().format(FILE_DATE_FORMATTER);
                String currentFileName = getCurrentFileName();
                
                if (currentFileName.contains(currentDate)) {
                    currentIndex.incrementAndGet();
                } else {
                    currentIndex.set(1);
                }
                
                initializeWriter();
            } catch (IOException e) {
                System.err.println("로그 파일 로테이션 실패: " + e.getMessage());
                throw new RuntimeException("로그 파일 로테이션 실패", e);
            }
        }
    }

    private void flush() {
        synchronized (writeLock) {
            try {
                if (writer != null) {
                    writer.flush();
                }
            } catch (IOException e) {
                System.err.println("로그 flush 실패: " + e.getMessage());
            }
        }
    }

    private long parseSize(String size) {
        size = size.toUpperCase();
        if (size.endsWith("MB")) {
            return Long.parseLong(size.substring(0, size.length() - 2)) * 1024 * 1024;
        } else if (size.endsWith("KB")) {
            return Long.parseLong(size.substring(0, size.length() - 2)) * 1024;
        } else if (size.endsWith("B")) {
            return Long.parseLong(size.substring(0, size.length() - 1));
        }
        return Long.parseLong(size);
    }

    public void shutdown() {
        running.set(false);
        worker.interrupt();
        try {
            worker.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        flush();
        try {
            synchronized (writeLock) {
                if (writer != null) {
                    writer.close();
                    writer = null;
                }
            }
        } catch (IOException e) {
            System.err.println("로그 파일 닫기 실패: " + e.getMessage());
        }
    }

    public Path getCurrentLogFile() {
        return currentLogFile;
    }
} 