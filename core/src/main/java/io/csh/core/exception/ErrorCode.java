package io.csh.core.exception;

import lombok.Getter;

/**
 * 에러 코드 정의 클래스
 */
@Getter
public enum ErrorCode {
    // 시스템 에러 (1000 ~ 1999)
    SYSTEM_ERROR(1000, "시스템 에러가 발생했습니다."),
    INVALID_ARGUMENT(1001, "잘못된 인자가 전달되었습니다."),
    NULL_POINTER(1002, "널 포인터가 발생했습니다."),
    ILLEGAL_STATE(1003, "잘못된 상태입니다."),
    ILLEGAL_ACCESS(1004, "잘못된 접근입니다."),

    // 파일 에러 (2000 ~ 2999)
    FILE_NOT_FOUND(2000, "파일을 찾을 수 없습니다."),
    FILE_ACCESS_DENIED(2001, "파일 접근이 거부되었습니다."),
    FILE_READ_ERROR(2002, "파일 읽기 에러가 발생했습니다."),
    FILE_WRITE_ERROR(2003, "파일 쓰기 에러가 발생했습니다."),
    FILE_DELETE_ERROR(2004, "파일 삭제 에러가 발생했습니다."),

    // 네트워크 에러 (3000 ~ 3999)
    NETWORK_ERROR(3000, "네트워크 에러가 발생했습니다."),
    CONNECTION_TIMEOUT(3001, "연결 시간이 초과되었습니다."),
    CONNECTION_REFUSED(3002, "연결이 거부되었습니다."),
    SOCKET_ERROR(3003, "소켓 에러가 발생했습니다."),

    // 데이터베이스 에러 (4000 ~ 4999)
    DATABASE_ERROR(4000, "데이터베이스 에러가 발생했습니다."),
    SQL_ERROR(4001, "SQL 에러가 발생했습니다."),
    TRANSACTION_ERROR(4002, "트랜잭션 에러가 발생했습니다."),
    CONNECTION_POOL_ERROR(4003, "커넥션 풀 에러가 발생했습니다."),

    // 보안 에러 (5000 ~ 5999)
    SECURITY_ERROR(5000, "보안 에러가 발생했습니다."),
    AUTHENTICATION_ERROR(5001, "인증 에러가 발생했습니다."),
    AUTHORIZATION_ERROR(5002, "권한 에러가 발생했습니다."),
    ENCRYPTION_ERROR(5003, "암호화 에러가 발생했습니다."),
    DECRYPTION_ERROR(5004, "복호화 에러가 발생했습니다.");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
} 