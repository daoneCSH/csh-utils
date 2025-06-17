package io.csh.utils.output.config;

/**
 * 출력 캡처 설정 속성을 정의하는 클래스
 */
public class OutputProperties {
    // 출력 디렉토리
    public static final String OUTPUT_DIR = "csh.output.dir";
    public static final String DEFAULT_OUTPUT_DIR = "output";
    
    // 파일 이름
    public static final String FILE_NAME = "csh.output.file";
    public static final String DEFAULT_FILE_NAME = "output.log";
    
    // 최대 파일 크기
    public static final String MAX_FILE_SIZE = "csh.output.max-size";
    public static final String DEFAULT_MAX_FILE_SIZE = "10MB";
    
    // 최대 파일 개수
    public static final String MAX_FILES = "csh.output.max-files";
    public static final String DEFAULT_MAX_FILES = "10";
    
    // 추가 모드
    public static final String APPEND = "csh.output.append";
    public static final String DEFAULT_APPEND = "true";
    
    // 압축 설정
    public static final String COMPRESSION_ENABLED = "csh.output.compression.enabled";
    public static final String DEFAULT_COMPRESSION_ENABLED = "true";
    
    // 압축 형식
    public static final String COMPRESSION_FORMAT = "csh.output.compression.format";
    public static final String DEFAULT_COMPRESSION_FORMAT = "gz";
    
    // 보관 기간
    public static final String RETENTION_DAYS = "csh.output.retention.days";
    public static final String DEFAULT_RETENTION_DAYS = "30";
    
    private OutputProperties() {
        // 인스턴스화 방지
    }
} 