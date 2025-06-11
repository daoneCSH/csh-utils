package io.csh.core.exception;

import lombok.Getter;

/**
 * Error codes used throughout the application.
 */
@Getter
public enum ErrorCode {
    // 시스템 에러 (1000 ~ 1999)
    SYSTEM_ERROR(1000, "System error occurred."),
    INVALID_ARGUMENT(1001, "Invalid argument provided."),
    NULL_POINTER(1002, "Null pointer exception occurred."),
    ILLEGAL_STATE(1003, "Illegal state occurred."),
    ILLEGAL_ACCESS(1004, "Illegal access occurred."),

    // 파일 에러 (2000 ~ 2999)
    FILE_NOT_FOUND(2000, "File not found."),
    FILE_ACCESS_DENIED(2001, "File access denied."),
    FILE_READ_ERROR(2002, "File read error occurred."),
    FILE_WRITE_ERROR(2003, "File write error occurred."),
    FILE_DELETE_ERROR(2004, "File delete error occurred."),

    // 네트워크 에러 (3000 ~ 3999)
    NETWORK_ERROR(3000, "Network error occurred."),
    CONNECTION_TIMEOUT(3001, "Connection timeout occurred."),
    CONNECTION_REFUSED(3002, "Connection refused."),
    SOCKET_ERROR(3003, "Socket error occurred."),

    // 데이터베이스 에러 (4000 ~ 4999)
    DATABASE_ERROR(4000, "Database error occurred."),
    SQL_ERROR(4001, "SQL error occurred."),
    TRANSACTION_ERROR(4002, "Transaction error occurred."),
    CONNECTION_POOL_ERROR(4003, "Connection pool error occurred."),

    // 보안 에러 (5000 ~ 5999)
    SECURITY_ERROR(5000, "Security error occurred."),
    AUTHENTICATION_ERROR(5001, "Authentication error occurred."),
    AUTHORIZATION_ERROR(5002, "Authorization error occurred."),
    ENCRYPTION_ERROR(5003, "Encryption error occurred."),
    DECRYPTION_ERROR(5004, "Decryption error occurred.");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
} 