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

public class LogStorage implements Runnable {
    private static final int QUEUE_CAPACITY = 10000;
    private static final int BATCH_SIZE = 100;
    private static final long FLUSH_INTERVAL = 1000; // milliseconds

    private final BlockingQueue<LogMessage> queue;
    private final AtomicBoolean running;
    private final Thread worker;
    private final LoggingConfig config;
    private final LogFormat formatter;
    private final AtomicInteger currentFileIndex;
    private BufferedWriter writer;
    private LocalDate currentDate;
    private long currentFileSize;
    private Path currentLogFile;
    private final boolean overwrite;
    private final String appName;

    public LogStorage() {
        this.queue = new LinkedBlockingQueue<>(QUEUE_CAPACITY);
        this.running = new AtomicBoolean(true);
        this.worker = new Thread(this, "LogStorage");
        this.config = LoggingConfig.getInstance();
        this.formatter = new LogFormat(config.getLogPattern());
        this.currentFileIndex = new AtomicInteger(0);
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

    private String getDefaultAppName() {
        return SystemProperties.getProperty("app.name")
            .checkSystemProperty()
            .checkApplicationProperties()
            .checkJarFileName()
            .orElse("unknown");
    }

    private void initializeWriter() {
        try {
            Path logDir = Paths.get(config.getLogDirectory().toString());
            if (!Files.exists(logDir)) {
                Files.createDirectories(logDir);
            }

            String fileName = getCurrentFileName();
            currentLogFile = logDir.resolve(fileName);
            
            // 파일이 존재하지 않으면 새로 생성
            if (!Files.exists(currentLogFile)) {
                Files.createFile(currentLogFile);
                currentFileSize = 0;
            } else {
                currentFileSize = Files.size(currentLogFile);
            }
            
            if (overwrite && Files.exists(currentLogFile)) {
                Files.delete(currentLogFile);
            }
            writer = new BufferedWriter(new FileWriter(currentLogFile.toFile(), !overwrite));
        } catch (IOException e) {
            System.err.println("로그 파일 초기화 실패: " + e.getMessage());
            throw new RuntimeException("로그 파일 초기화 실패", e);
        }
    }

    private String getCurrentFileName() {
        LocalDate today = LocalDate.now();
        if (!today.equals(currentDate)) {
            currentDate = today;
            currentFileIndex.set(0);
        }

        String baseName = config.getLogFileName();
        if (currentFileIndex.get() == 0) {
            return baseName;
        }
        return String.format("%s.%s.%d", baseName, 
            currentDate.format(DateTimeFormatter.ISO_DATE), 
            currentFileIndex.get());
    }

    public void store(LogMessage message) {
        if (!queue.offer(message)) {
            // 큐가 가득 찼을 때 대기하지 않고 즉시 처리
            try {
                writeLog(message);
            } catch (Exception e) {
                System.err.println("로그 저장 실패: " + e.getMessage());
            }
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
            LogMessage message = queue.poll();
            if (message == null) {
                break;
            }
            writeLog(message);
        }
        flush();
    }

    private void writeLog(LogMessage message) {
        try {
            String formattedMessage = formatter.format(message);
            writer.write(formattedMessage);
            currentFileSize += formattedMessage.getBytes().length;

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
        try {
            writer.close();
            
            // 이전 파일 이름 변경
            String newFileName = String.format("%s.%s.%d", 
                config.getLogFileName(),
                currentDate.format(DateTimeFormatter.ISO_DATE),
                currentFileIndex.get());
            
            Path newPath = currentLogFile.getParent().resolve(newFileName);
            Files.move(currentLogFile, newPath);
            
            currentFileIndex.incrementAndGet();
            initializeWriter();
        } catch (IOException e) {
            System.err.println("로그 파일 로테이션 실패: " + e.getMessage());
            throw new RuntimeException("로그 파일 로테이션 실패", e);
        }
    }

    private void flush() {
        try {
            if (writer != null) {
                writer.flush();
            }
        } catch (IOException e) {
            System.err.println("로그 플러시 실패: " + e.getMessage());
            throw new RuntimeException("로그 플러시 실패", e);
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
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("로그 파일 닫기 실패: " + e.getMessage());
        }
    }
} 