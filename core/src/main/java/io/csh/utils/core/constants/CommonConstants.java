package io.csh.utils.core.constants;

/**
 * Common constants used throughout the application.
 */
public final class CommonConstants {
    private CommonConstants() {
        throw new IllegalStateException("Constant class");
    }

    /** Empty string constant */
    public static final String EMPTY = "";
    
    /** Space character constant */
    public static final String SPACE = " ";
    
    /** Comma character constant */
    public static final String COMMA = ",";
    
    /** Colon character constant */
    public static final String COLON = ":";
    
    /** Dot character constant */
    public static final String DOT = ".";
    
    /** Semicolon character constant */
    public static final String SEMICOLON = ";";
    
    /** Underscore character constant */
    public static final String UNDERSCORE = "_";
    
    /** Hyphen character constant */
    public static final String HYPHEN = "-";
    
    /** Slash character constant */
    public static final String SLASH = "/";
    
    /** Backslash character constant */
    public static final String BACKSLASH = "\\";
    
    /** Newline character constant */
    public static final String NEWLINE = "\n";
    
    /** Tab character constant */
    public static final String TAB = "\t";
    
    /** Zero constant */
    public static final int ZERO = 0;
    
    /** One constant */
    public static final int ONE = 1;
    
    /** Two constant */
    public static final int TWO = 2;
    
    /** Three constant */
    public static final int THREE = 3;
    
    /** Four constant */
    public static final int FOUR = 4;
    
    /** Five constant */
    public static final int FIVE = 5;
    
    /** Six constant */
    public static final int SIX = 6;
    
    /** Seven constant */
    public static final int SEVEN = 7;
    
    /** Eight constant */
    public static final int EIGHT = 8;
    
    /** Nine constant */
    public static final int NINE = 9;
    
    /** Ten constant */
    public static final int TEN = 10;
    
    /** Hours per day constant */
    public static final long HOURS_PER_DAY = 24L;
    
    /** Days per week constant */
    public static final long DAYS_PER_WEEK = 7L;
    
    /** Days per month constant */
    public static final long DAYS_PER_MONTH = 30L;
    
    /** Days per year constant */
    public static final long DAYS_PER_YEAR = 365L;
    
    /** Milliseconds per second constant */
    public static final long MILLISECONDS_PER_SECOND = 1000L;
    
    /** Seconds per minute constant */
    public static final long SECONDS_PER_MINUTE = 60L;
    
    /** Minutes per hour constant */
    public static final long MINUTES_PER_HOUR = 60L;
    
    /** Bytes per kilobyte constant */
    public static final long BYTES_PER_KB = 1024L;
    
    /** Bytes per megabyte constant */
    public static final long BYTES_PER_MB = BYTES_PER_KB * 1024L;
    
    /** Bytes per gigabyte constant */
    public static final long BYTES_PER_GB = BYTES_PER_MB * 1024L;
    
    /** Bytes per terabyte constant */
    public static final long BYTES_PER_TB = BYTES_PER_GB * 1024L;
    
    /** UTF-8 character encoding constant */
    public static final String UTF8 = "UTF-8";
    
    /** EUC-KR character encoding constant */
    public static final String EUC_KR = "EUC-KR";
    
    /** ISO-8859-1 character encoding constant */
    public static final String ISO_8859_1 = "ISO-8859-1";
    
    /** MS949 character encoding constant */
    public static final String MS949 = "MS949";
    
    /** Date format constant (yyyy-MM-dd) */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    
    /** Time format constant (HH:mm:ss) */
    public static final String TIME_FORMAT = "HH:mm:ss";
    
    /** Date and time format constant (yyyy-MM-dd HH:mm:ss) */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /** Date and time format with milliseconds constant (yyyy-MM-dd HH:mm:ss.SSS) */
    public static final String DATETIME_FORMAT_WITH_MILLIS = "yyyy-MM-dd HH:mm:ss.SSS";
    
    /** Email pattern constant */
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    
    /** Phone number pattern constant */
    public static final String PHONE_PATTERN = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
    
    /** URL pattern constant */
    public static final String URL_PATTERN = "^(http|https)://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,}(?:/[^/]*)*$";
    
    /** IP address pattern constant */
    public static final String IP_PATTERN = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
} 