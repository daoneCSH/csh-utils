package io.csh.utils.output.storage;

import io.csh.utils.output.config.OutputConfig;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 출력 파일 관리를 담당하는 클래스
 */
public class FileManager {
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final AtomicInteger currentIndex = new AtomicInteger(1);

    private final OutputConfig config;
    private final Object fileLock = new Object();
    private BufferedWriter writer;
    private Path currentFile;
    private long currentFileSize;
    private final boolean append;

    public FileManager(OutputConfig config) {
        this.config = config;
        this.append = config.isAppend();
    }

    public void initialize() throws IOException {
        synchronized (fileLock) {
            Path outputDir = config.getOutputDirectory();
            if (!Files.exists(outputDir)) {
                Files.createDirectories(outputDir);
            }

            currentFile = getCurrentFileName();
            if (!append && Files.exists(currentFile)) {
                Files.delete(currentFile);
            }
            
            writer = new BufferedWriter(new FileWriter(currentFile.toFile(), append));
            currentFileSize = Files.size(currentFile);
        }
    }

    private Path getCurrentFileName() {
        String baseName = config.getFileName();
        String extension = "";
        int dotIndex = baseName.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = baseName.substring(dotIndex);
            baseName = baseName.substring(0, dotIndex);
        }

        return Paths.get(config.getOutputDirectory().toString(),
            String.format("%s-%s-%d%s",
                baseName,
                LocalDateTime.now().format(FILE_DATE_FORMATTER),
                currentIndex.get(),
                extension));
    }

    public void write(String content) throws IOException {
        synchronized (fileLock) {
            if (writer == null) {
                initialize();
            }

            if (shouldRotate()) {
                rotate();
            }

            writer.write(content);
            writer.newLine();
            currentFileSize += content.length() + System.lineSeparator().length();
        }
    }

    public void flush() throws IOException {
        synchronized (fileLock) {
            if (writer != null) {
                writer.flush();
            }
        }
    }

    private boolean shouldRotate() {
        return currentFileSize >= config.getMaxFileSize();
    }

    private void rotate() throws IOException {
        synchronized (fileLock) {
            if (writer != null) {
                writer.close();
                writer = null;
            }

            String currentDate = LocalDateTime.now().format(FILE_DATE_FORMATTER);
            String currentFileName = currentFile.getFileName().toString();
            
            if (currentFileName.contains(currentDate)) {
                currentIndex.incrementAndGet();
            } else {
                currentIndex.set(1);
            }

            initialize();
        }
    }

    public void shutdown() {
        synchronized (fileLock) {
            try {
                if (writer != null) {
                    writer.close();
                    writer = null;
                }
            } catch (IOException e) {
                System.err.println("Failed to close writer: " + e.getMessage());
            }
        }
    }

    public Path getCurrentFile() {
        return currentFile;
    }

    public void setOutputDirectory(Path dir) {
        synchronized (fileLock) {
            try {
                if (!Files.exists(dir)) {
                    Files.createDirectories(dir);
                }
                config.setOutputDirectory(dir);
                if (writer != null) {
                    writer.close();
                    writer = null;
                }
                initialize();
            } catch (IOException e) {
                throw new RuntimeException("Failed to set output directory", e);
            }
        }
    }
} 