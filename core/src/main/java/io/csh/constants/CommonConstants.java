package io.csh.constants;

/**
 * 공통 상수 정의 클래스
 */
public final class CommonConstants {
    private CommonConstants() {
        throw new IllegalStateException("Constant class");
    }

    // 문자 상수
    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String COMMA = ",";
    public static final String DOT = ".";
    public static final String COLON = ":";
    public static final String SEMICOLON = ";";
    public static final String UNDERSCORE = "_";
    public static final String HYPHEN = "-";
    public static final String SLASH = "/";
    public static final String BACKSLASH = "\\";
    public static final String NEWLINE = "\n";
    public static final String TAB = "\t";

    // 숫자 상수
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int NINE = 9;
    public static final int TEN = 10;

    // 시간 상수
    public static final long MILLISECONDS_PER_SECOND = 1000L;
    public static final long SECONDS_PER_MINUTE = 60L;
    public static final long MINUTES_PER_HOUR = 60L;
    public static final long HOURS_PER_DAY = 24L;
    public static final long DAYS_PER_WEEK = 7L;
    public static final long DAYS_PER_MONTH = 30L;
    public static final long DAYS_PER_YEAR = 365L;

    // 파일 크기 상수
    public static final long BYTES_PER_KB = 1024L;
    public static final long BYTES_PER_MB = BYTES_PER_KB * 1024L;
    public static final long BYTES_PER_GB = BYTES_PER_MB * 1024L;
    public static final long BYTES_PER_TB = BYTES_PER_GB * 1024L;

    // 인코딩 상수
    public static final String UTF8 = "UTF-8";
    public static final String EUC_KR = "EUC-KR";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String MS949 = "MS949";

    // 날짜 포맷 상수
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_WITH_MILLIS = "yyyy-MM-dd HH:mm:ss.SSS";

    // 정규식 상수
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String PHONE_PATTERN = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
    public static final String URL_PATTERN = "^(http|https)://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,}(?:/[^/]*)*$";
    public static final String IP_PATTERN = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
} 