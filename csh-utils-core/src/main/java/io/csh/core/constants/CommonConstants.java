package io.csh.core.constants;

/**
 * CSH Utils의 공통 상수를 정의하는 클래스입니다.
 */
public final class CommonConstants {
    private CommonConstants() {
        throw new IllegalStateException("Utility class");
    }

    // Common
    public static final String EMPTY_STRING = "";
    public static final String SPACE = " ";
    public static final String COMMA = ",";
    public static final String DOT = ".";
    public static final String COLON = ":";
    public static final String SEMICOLON = ";";
    public static final String UNDERSCORE = "_";
    public static final String HYPHEN = "-";
    public static final String SLASH = "/";
    public static final String BACKSLASH = "\\";
    public static final String NEW_LINE = "\n";
    public static final String TAB = "\t";

    // Date Format
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String TIME_FORMAT_HHMMSS = "HHmmss";
    public static final String TIME_FORMAT_HH_MM_SS = "HH:mm:ss";
    public static final String DATETIME_FORMAT_YYYYMMDD_HHMMSS = "yyyyMMdd HHmmss";
    public static final String DATETIME_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
} 