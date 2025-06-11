package io.csh.exception;

/**
 * CSH Utils 기본 예외 클래스
 */
public class CshException extends Exception {
    /**
     * 기본 생성자
     */
    public CshException() {
        super();
    }

    /**
     * 메시지를 포함한 생성자
     *
     * @param message 예외 메시지
     */
    public CshException(String message) {
        super(message);
    }

    /**
     * 메시지와 원인을 포함한 생성자
     *
     * @param message 예외 메시지
     * @param cause 원인 예외
     */
    public CshException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 원인을 포함한 생성자
     *
     * @param cause 원인 예외
     */
    public CshException(Throwable cause) {
        super(cause);
    }

    /**
     * 메시지, 원인, suppression, writableStackTrace를 포함한 생성자
     *
     * @param message 예외 메시지
     * @param cause 원인 예외
     * @param enableSuppression suppression 활성화 여부
     * @param writableStackTrace 스택 트레이스 작성 가능 여부
     */
    protected CshException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
} 