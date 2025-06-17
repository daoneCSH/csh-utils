package io.csh.utils.output.config;

import io.csh.utils.core.config.SystemProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 출력 캡처 설정을 관리하는 클래스
 */
public class OutputConfig {
    private static final OutputConfig INSTANCE = new OutputConfig();
    
    private Path outputDirectory;
    private String fileName;
    private long maxFileSize;
    private int maxFiles;
    private boolean append;
    private boolean compressionEnabled;
    private String compressionFormat;
    private int retentionDays;
    
    private OutputConfig() {
        initializeFromProperties();
    }
    
    public static OutputConfig getInstance() {
        return INSTANCE;
    }
    
    private void initializeFromProperties() {
        // 출력 디렉토리
        String outputDir = SystemProperties.getProperty(OutputProperties.OUTPUT_DIR)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(OutputProperties.DEFAULT_OUTPUT_DIR);
        this.outputDirectory = Paths.get(outputDir);
        
        // 파일 이름
        this.fileName = SystemProperties.getProperty(OutputProperties.FILE_NAME)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(OutputProperties.DEFAULT_FILE_NAME);
        
        // 최대 파일 크기
        String maxSize = SystemProperties.getProperty(OutputProperties.MAX_FILE_SIZE)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(OutputProperties.DEFAULT_MAX_FILE_SIZE);
        this.maxFileSize = parseSize(maxSize);
        
        // 최대 파일 개수
        String maxFiles = SystemProperties.getProperty(OutputProperties.MAX_FILES)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(OutputProperties.DEFAULT_MAX_FILES);
        this.maxFiles = Integer.parseInt(maxFiles);
        
        // 추가 모드
        String append = SystemProperties.getProperty(OutputProperties.APPEND)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(OutputProperties.DEFAULT_APPEND);
        this.append = Boolean.parseBoolean(append);
        
        // 압축 설정
        String compressionEnabled = SystemProperties.getProperty(OutputProperties.COMPRESSION_ENABLED)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(OutputProperties.DEFAULT_COMPRESSION_ENABLED);
        this.compressionEnabled = Boolean.parseBoolean(compressionEnabled);
        
        // 압축 형식
        this.compressionFormat = SystemProperties.getProperty(OutputProperties.COMPRESSION_FORMAT)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(OutputProperties.DEFAULT_COMPRESSION_FORMAT);
        
        // 보관 기간
        String retentionDays = SystemProperties.getProperty(OutputProperties.RETENTION_DAYS)
            .checkSystemProperty()
            .checkApplicationProperties()
            .orElse(OutputProperties.DEFAULT_RETENTION_DAYS);
        this.retentionDays = Integer.parseInt(retentionDays);
    }
    
    private long parseSize(String size) {
        size = size.toUpperCase();
        if (size.endsWith("MB")) {
            return Long.parseLong(size.substring(0, size.length() - 2)) * 1024 * 1024;
        } else if (size.endsWith("KB")) {
            return Long.parseLong(size.substring(0, size.length() - 2)) * 1024;
        } else if (size.endsWith("GB")) {
            return Long.parseLong(size.substring(0, size.length() - 2)) * 1024 * 1024 * 1024;
        } else if (size.endsWith("B")) {
            return Long.parseLong(size.substring(0, size.length() - 1));
        }
        return Long.parseLong(size);
    }
    
    public Path getOutputDirectory() {
        return outputDirectory;
    }
    
    public void setOutputDirectory(Path outputDirectory) {
        this.outputDirectory = outputDirectory;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public long getMaxFileSize() {
        return maxFileSize;
    }
    
    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
    
    public int getMaxFiles() {
        return maxFiles;
    }
    
    public void setMaxFiles(int maxFiles) {
        this.maxFiles = maxFiles;
    }
    
    public boolean isAppend() {
        return append;
    }
    
    public void setAppend(boolean append) {
        this.append = append;
    }
    
    public boolean isCompressionEnabled() {
        return compressionEnabled;
    }
    
    public void setCompressionEnabled(boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
    }
    
    public String getCompressionFormat() {
        return compressionFormat;
    }
    
    public void setCompressionFormat(String compressionFormat) {
        this.compressionFormat = compressionFormat;
    }
    
    public int getRetentionDays() {
        return retentionDays;
    }
    
    public void setRetentionDays(int retentionDays) {
        this.retentionDays = retentionDays;
    }
    
    /**
     * 현재 설정 상태를 문자열로 반환
     * @return 설정 상태 문자열
     */
    public String printConfig() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Output Capture Configuration ===\n");
        sb.append("Output Directory: ").append(outputDirectory).append("\n");
        sb.append("File Name: ").append(fileName).append("\n");
        sb.append("Max File Size: ").append(formatSize(maxFileSize)).append("\n");
        sb.append("Max Files: ").append(maxFiles).append("\n");
        sb.append("Append Mode: ").append(append).append("\n");
        sb.append("Compression Enabled: ").append(compressionEnabled).append("\n");
        sb.append("Compression Format: ").append(compressionFormat).append("\n");
        sb.append("Retention Days: ").append(retentionDays).append("\n");
        sb.append("===================================");
        return sb.toString();
    }
    
    /**
     * 바이트 크기를 읽기 쉬운 형식으로 변환
     * @param size 바이트 크기
     * @return 포맷된 크기 문자열
     */
    private String formatSize(long size) {
        if (size >= 1024 * 1024 * 1024) {
            return String.format("%.1f GB", size / (1024.0 * 1024 * 1024));
        } else if (size >= 1024 * 1024) {
            return String.format("%.1f MB", size / (1024.0 * 1024));
        } else if (size >= 1024) {
            return String.format("%.1f KB", size / 1024.0);
        } else {
            return size + " B";
        }
    }
} 