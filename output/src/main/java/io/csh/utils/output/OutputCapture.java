package io.csh.utils.output;

import io.csh.utils.core.config.SystemProperties;
import io.csh.utils.output.config.OutputConfig;
import io.csh.utils.output.storage.FileManager;

import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * System.out/err 출력을 캡처하여 파일로 저장하는 클래스
 */
public class OutputCapture {
    private static final int QUEUE_CAPACITY = 10000;
    private static final int BATCH_SIZE = 100;
    private static final long FLUSH_INTERVAL = 1000; // milliseconds
    private static OutputCapture INSTANCE;

    private final BlockingQueue<String> outputQueue;
    private final FileManager fileManager;
    private final OutputConfig config;
    private final AtomicBoolean running;
    private final Thread worker;
    private final Object writeLock = new Object();

    private OutputCapture() {
        this.outputQueue = new LinkedBlockingQueue<>(QUEUE_CAPACITY);
        this.config = OutputConfig.getInstance();
        this.fileManager = new FileManager(config);
        this.running = new AtomicBoolean(true);
        this.worker = new Thread(this::processQueue, "OutputCapture");
        
        initialize();
    }

    public static synchronized OutputCapture getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OutputCapture();
        }
        return INSTANCE;
    }

    private void initialize() {
        try {
            fileManager.initialize();
            worker.start();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize OutputCapture: " + e.getMessage(), e);
        }
    }

    public void start() {
        if (!running.get()) {
            running.set(true);
            worker.start();
        }
    }

    public void stop() {
        running.set(false);
        worker.interrupt();
        try {
            worker.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        fileManager.shutdown();
    }

    public void write(String content) {
        try {
            outputQueue.put(content);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to write output", e);
        }
    }

    private void processQueue() {
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
            String content = null;
            try {
                content = outputQueue.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            if (content != null) {
                processOutput(content);
            }
        }
        flush();
    }

    private void processOutput(String content) {
        synchronized (writeLock) {
            try {
                fileManager.write(content);
            } catch (Exception e) {
                System.err.println("Failed to write output: " + e.getMessage());
            }
        }
    }

    private void flush() {
        synchronized (writeLock) {
            try {
                fileManager.flush();
            } catch (Exception e) {
                System.err.println("Failed to flush output: " + e.getMessage());
            }
        }
    }

    public Path getCurrentFile() {
        return fileManager.getCurrentFile();
    }

    public void setOutputDirectory(Path dir) {
        fileManager.setOutputDirectory(dir);
    }
} 