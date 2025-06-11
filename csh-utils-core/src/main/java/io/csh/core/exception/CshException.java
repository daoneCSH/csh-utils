package io.csh.core.exception;

import lombok.Getter;

/**
 * CSH Utils의 기본 예외 클래스입니다.
 */
@Getter
public class CshException extends RuntimeException {
    private final ErrorCode errorCode;

    public CshException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CshException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public CshException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CshException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
} 