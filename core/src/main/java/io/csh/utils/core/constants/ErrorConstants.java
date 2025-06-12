package io.csh.utils.core.constants;

/**
 * Constants for error messages and codes.
 */
public final class ErrorConstants {
    private ErrorConstants() {
        throw new IllegalStateException("Constant class");
    }

    /** Default error message */
    public static final String ERROR_MESSAGE = "An error occurred.";
    
    /** Error message with code */
    public static final String ERROR_MESSAGE_WITH_CODE = "An error occurred. (Code: %d)";
    
    /** Error message with message */
    public static final String ERROR_MESSAGE_WITH_MESSAGE = "An error occurred. (Message: %s)";
    
    /** Error message with code and message */
    public static final String ERROR_MESSAGE_WITH_CODE_AND_MESSAGE = "An error occurred. (Code: %d, Message: %s)";
    
    /** Default exception message */
    public static final String EXCEPTION_MESSAGE = "An exception occurred.";
    
    /** Exception message with type */
    public static final String EXCEPTION_MESSAGE_WITH_TYPE = "An exception occurred. (Type: %s)";
    
    /** Exception message with message */
    public static final String EXCEPTION_MESSAGE_WITH_MESSAGE = "An exception occurred. (Message: %s)";
    
    /** Exception message with type and message */
    public static final String EXCEPTION_MESSAGE_WITH_TYPE_AND_MESSAGE = "An exception occurred. (Type: %s, Message: %s)";
    
    /** Stack trace message */
    public static final String STACK_TRACE_MESSAGE = "Stack trace:";
    
    /** Stack trace format */
    public static final String STACK_TRACE_FORMAT = "%s: %s%n%s";
    
    /** Stack trace cause format */
    public static final String STACK_TRACE_CAUSE_FORMAT = "Caused by: %s: %s%n%s";
    
    /** Minimum error code */
    public static final int ERROR_CODE_MIN = 1000;
    
    /** Maximum error code */
    public static final int ERROR_CODE_MAX = 9999;
    
    /** Minimum system error code */
    public static final int ERROR_CODE_SYSTEM_MIN = 1000;
    
    /** Maximum system error code */
    public static final int ERROR_CODE_SYSTEM_MAX = 1999;
    
    /** Minimum file error code */
    public static final int ERROR_CODE_FILE_MIN = 2000;
    
    /** Maximum file error code */
    public static final int ERROR_CODE_FILE_MAX = 2999;
    
    /** Minimum network error code */
    public static final int ERROR_CODE_NETWORK_MIN = 3000;
    
    /** Maximum network error code */
    public static final int ERROR_CODE_NETWORK_MAX = 3999;
    
    /** Minimum database error code */
    public static final int ERROR_CODE_DATABASE_MIN = 4000;
    
    /** Maximum database error code */
    public static final int ERROR_CODE_DATABASE_MAX = 4999;
    
    /** Minimum security error code */
    public static final int ERROR_CODE_SECURITY_MIN = 5000;
    
    /** Maximum security error code */
    public static final int ERROR_CODE_SECURITY_MAX = 5999;
    
    /** Error code message format */
    public static final String ERROR_CODE_MESSAGE = "Error code: %d";
    
    /** Error code message with message format */
    public static final String ERROR_CODE_MESSAGE_WITH_MESSAGE = "Error code: %d, Message: %s";
    
    /** Error code message with cause format */
    public static final String ERROR_CODE_MESSAGE_WITH_CAUSE = "Error code: %d, Cause: %s";
    
    /** Error code message with message and cause format */
    public static final String ERROR_CODE_MESSAGE_WITH_MESSAGE_AND_CAUSE = "Error code: %d, Message: %s, Cause: %s";
} 