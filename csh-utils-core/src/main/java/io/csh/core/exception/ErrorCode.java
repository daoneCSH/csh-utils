package io.csh.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * CSH Utils의 에러 코드를 정의하는 열거형입니다.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common Errors
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    INVALID_ARGUMENT("잘못된 인자가 전달되었습니다."),
    NULL_POINTER("널 포인터가 발생했습니다."),
    ILLEGAL_STATE("잘못된 상태입니다."),
    ILLEGAL_ARGUMENT("잘못된 인자입니다."),

    // Logging Errors
    LOGGER_NOT_FOUND("로거를 찾을 수 없습니다."),
    LOG_LEVEL_NOT_SUPPORTED("지원하지 않는 로그 레벨입니다.");

    private final String message;
} 