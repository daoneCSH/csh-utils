package io.csh.constants;

/**
 * 에러 관련 상수 정의 클래스
 */
public final class ErrorConstants {
    private ErrorConstants() {
        throw new IllegalStateException("Constant class");
    }

    // 에러 메시지
    public static final String ERROR_MESSAGE = "에러가 발생했습니다.";
    public static final String ERROR_MESSAGE_WITH_CODE = "에러가 발생했습니다. (코드: %d)";
    public static final String ERROR_MESSAGE_WITH_MESSAGE = "에러가 발생했습니다. (메시지: %s)";
    public static final String ERROR_MESSAGE_WITH_CODE_AND_MESSAGE = "에러가 발생했습니다. (코드: %d, 메시지: %s)";

    // 예외 메시지
    public static final String EXCEPTION_MESSAGE = "예외가 발생했습니다.";
    public static final String EXCEPTION_MESSAGE_WITH_TYPE = "예외가 발생했습니다. (타입: %s)";
    public static final String EXCEPTION_MESSAGE_WITH_MESSAGE = "예외가 발생했습니다. (메시지: %s)";
    public static final String EXCEPTION_MESSAGE_WITH_TYPE_AND_MESSAGE = "예외가 발생했습니다. (타입: %s, 메시지: %s)";

    // 스택 트레이스
    public static final String STACK_TRACE_MESSAGE = "스택 트레이스:";
    public static final String STACK_TRACE_FORMAT = "%s: %s%n%s";
    public static final String STACK_TRACE_CAUSE_FORMAT = "Caused by: %s: %s%n%s";

    // 에러 코드 범위
    public static final int ERROR_CODE_MIN = 1000;
    public static final int ERROR_CODE_MAX = 9999;
    public static final int ERROR_CODE_SYSTEM_MIN = 1000;
    public static final int ERROR_CODE_SYSTEM_MAX = 1999;
    public static final int ERROR_CODE_FILE_MIN = 2000;
    public static final int ERROR_CODE_FILE_MAX = 2999;
    public static final int ERROR_CODE_NETWORK_MIN = 3000;
    public static final int ERROR_CODE_NETWORK_MAX = 3999;
    public static final int ERROR_CODE_DATABASE_MIN = 4000;
    public static final int ERROR_CODE_DATABASE_MAX = 4999;
    public static final int ERROR_CODE_SECURITY_MIN = 5000;
    public static final int ERROR_CODE_SECURITY_MAX = 5999;

    // 에러 코드 메시지
    public static final String ERROR_CODE_MESSAGE = "에러 코드: %d";
    public static final String ERROR_CODE_MESSAGE_WITH_MESSAGE = "에러 코드: %d, 메시지: %s";
    public static final String ERROR_CODE_MESSAGE_WITH_CAUSE = "에러 코드: %d, 원인: %s";
    public static final String ERROR_CODE_MESSAGE_WITH_MESSAGE_AND_CAUSE = "에러 코드: %d, 메시지: %s, 원인: %s";
} 