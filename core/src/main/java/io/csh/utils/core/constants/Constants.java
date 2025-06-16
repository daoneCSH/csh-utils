package io.csh.utils.core.constants;

/**
 * Core 모듈의 모든 상수를 정의하는 클래스
 * 
 * 이 클래스는 Common, System, Error 내부 클래스를 통해 체계적으로 상수를 관리합니다.
 * 각 내부 클래스는 특정 영역의 상수를 정의하며, 인스턴스화를 방지하기 위해 private 생성자를 사용합니다.
 */
public final class Constants {
    private Constants() {
        throw new IllegalStateException("Constants class");
    }

    /**
     * 공통 상수 정의
     * 
     * 문자열 관련 기본 상수(빈 문자열, 공백, 구분자 등)를 제공합니다.
     */
    public static final class Common {
        private Common() {
            throw new IllegalStateException("Common constants class");
        }

        public static final String EMPTY_STRING = "";
        public static final String SPACE = " ";
        public static final String DOT = ".";
        public static final String COMMA = ",";
        public static final String COLON = ":";
        public static final String SEMICOLON = ";";
        public static final String UNDERSCORE = "_";
        public static final String HYPHEN = "-";
        public static final String SLASH = "/";
        public static final String BACKSLASH = "\\";
        /**
         * OS별 줄바꿈 문자. 일반적으로 System.lineSeparator() 직접 사용을 권장.
         * 공통 유틸리티/테스트 등에서 상수로 필요할 때만 사용.
         */
        public static final String NEWLINE = java.lang.System.lineSeparator();
        public static final String TAB = "\t";
    }

    /**
     * 시스템 관련 상수 정의
     * 
     * OS 정보, 사용자 정보, 파일 경로 구분자 등 시스템 관련 상수를 제공합니다.
     */
    public static final class System {
        private System() {
            throw new IllegalStateException("System constants class");
        }

        public static final String OS_NAME = java.lang.System.getProperty("os.name");
        public static final String OS_VERSION = java.lang.System.getProperty("os.version");
        public static final String OS_ARCH = java.lang.System.getProperty("os.arch");
        public static final String USER_NAME = java.lang.System.getProperty("user.name");
        public static final String USER_HOME = java.lang.System.getProperty("user.home");
        public static final String USER_DIR = java.lang.System.getProperty("user.dir");
        public static final String FILE_SEPARATOR = java.lang.System.getProperty("file.separator");
        public static final String PATH_SEPARATOR = java.lang.System.getProperty("path.separator");
        /**
         * 시스템 프로퍼티에서 가져온 줄바꿈 문자. 특별한 이유가 없다면 System.lineSeparator() 사용 권장.
         */
        public static final String LINE_SEPARATOR = java.lang.System.getProperty("line.separator");
    }

    /**
     * 에러 관련 상수 정의
     * 
     * 일반적인 에러 메시지와 관련된 상수를 제공합니다.
     */
    public static final class Error {
        private Error() {
            throw new IllegalStateException("Error constants class");
        }

        public static final String UNKNOWN_ERROR = "Unknown error occurred";
        public static final String INVALID_ARGUMENT = "Invalid argument";
        public static final String NULL_ARGUMENT = "Argument cannot be null";
        public static final String EMPTY_ARGUMENT = "Argument cannot be empty";
        public static final String FILE_NOT_FOUND = "File not found";
        public static final String FILE_ACCESS_DENIED = "File access denied";
        public static final String FILE_ALREADY_EXISTS = "File already exists";
        public static final String DIRECTORY_NOT_FOUND = "Directory not found";
        public static final String DIRECTORY_ACCESS_DENIED = "Directory access denied";
        public static final String DIRECTORY_ALREADY_EXISTS = "Directory already exists";
    }
} 