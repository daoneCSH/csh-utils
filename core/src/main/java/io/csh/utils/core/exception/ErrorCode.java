package io.csh.utils.core.exception;

/**
 * 에러 코드 정의
 */
public enum ErrorCode {
    UNKNOWN_ERROR("E0000", "알 수 없는 오류가 발생했습니다."),
    INVALID_ARGUMENT("E0001", "잘못된 인자가 전달되었습니다."),
    FILE_NOT_FOUND("E0002", "파일을 찾을 수 없습니다."),
    IO_ERROR("E0003", "입출력 오류가 발생했습니다."),
    NETWORK_ERROR("E0004", "네트워크 오류가 발생했습니다."),
    DATABASE_ERROR("E0005", "데이터베이스 오류가 발생했습니다."),
    AUTHENTICATION_ERROR("E0006", "인증 오류가 발생했습니다."),
    AUTHORIZATION_ERROR("E0007", "권한이 없습니다."),
    TIMEOUT_ERROR("E0008", "시간 초과가 발생했습니다."),
    CONCURRENT_MODIFICATION("E0009", "동시 수정이 발생했습니다."),
    RESOURCE_NOT_FOUND("E0010", "리소스를 찾을 수 없습니다."),
    SERVICE_UNAVAILABLE("E0011", "서비스를 사용할 수 없습니다."),
    VALIDATION_ERROR("E0012", "유효성 검사 오류가 발생했습니다."),
    CONFIGURATION_ERROR("E0013", "설정 오류가 발생했습니다."),
    DEPENDENCY_ERROR("E0014", "의존성 오류가 발생했습니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
} 