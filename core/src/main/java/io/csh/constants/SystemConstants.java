package io.csh.constants;

/**
 * 시스템 상수 정의 클래스
 */
public final class SystemConstants {
    private SystemConstants() {
        throw new IllegalStateException("Constant class");
    }

    // 시스템 프로퍼티 키
    public static final String OS_NAME = "os.name";
    public static final String OS_VERSION = "os.version";
    public static final String OS_ARCH = "os.arch";
    public static final String USER_NAME = "user.name";
    public static final String USER_HOME = "user.home";
    public static final String USER_DIR = "user.dir";
    public static final String JAVA_VERSION = "java.version";
    public static final String JAVA_VENDOR = "java.vendor";
    public static final String JAVA_HOME = "java.home";
    public static final String JAVA_CLASS_PATH = "java.class.path";
    public static final String FILE_SEPARATOR = "file.separator";
    public static final String PATH_SEPARATOR = "path.separator";
    public static final String LINE_SEPARATOR = "line.separator";

    // 시스템 프로퍼티 값
    public static final String WINDOWS = "Windows";
    public static final String LINUX = "Linux";
    public static final String MAC = "Mac";
    public static final String UNIX = "Unix";
    public static final String SOLARIS = "Solaris";

    // 시스템 환경 변수 키
    public static final String PATH = "PATH";
    public static final String CLASSPATH = "CLASSPATH";
    public static final String JAVA_OPTS = "JAVA_OPTS";
    public static final String CATALINA_HOME = "CATALINA_HOME";
    public static final String CATALINA_BASE = "CATALINA_BASE";

    // 시스템 파일 경로
    public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    public static final String USER_HOME_DIR = System.getProperty("user.home");
    public static final String USER_DIR_PATH = System.getProperty("user.dir");
    public static final String JAVA_HOME_PATH = System.getProperty("java.home");

    // 시스템 정보
    public static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    public static final long MAX_MEMORY = Runtime.getRuntime().maxMemory();
    public static final long TOTAL_MEMORY = Runtime.getRuntime().totalMemory();
    public static final long FREE_MEMORY = Runtime.getRuntime().freeMemory();

    // 시스템 시간
    public static final long SYSTEM_TIME = System.currentTimeMillis();
    public static final long NANO_TIME = System.nanoTime();
} 