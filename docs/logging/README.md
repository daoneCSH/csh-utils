# CSH Utils Logging Module

## 최근 변경사항 (2025-06-11)
- Logger, AsyncLogger의 모든 메서드는 static 방식으로 제공됩니다.
- Logger의 각 메서드는 내부적으로 예외(CshException)를 처리하며, 실패 시 System.err에 메시지를 출력합니다.
- LogConfig의 로그 디렉토리 경로는 getLogDirectory()로 확인할 수 있습니다.
- 동적 카테고리 관련 API는 현재 제공되지 않습니다(코드에서 제거됨).
- JUnit 기반의 Logger 테스트 코드가 추가되었습니다.

## 개요
CSH Utils의 로깅 모듈은 애플리케이션의 로그를 효율적으로 관리하고 기록하기 위한 유틸리티를 제공합니다. 
표준 카테고리별로 별도의 로그 파일을 생성합니다.

## 주요 기능

### 1. 로그 레벨
- TRACE: 가장 상세한 로그 레벨
- DEBUG: 디버깅을 위한 상세 정보
- INFO: 일반적인 정보성 메시지
- WARN: 경고 메시지
- ERROR: 오류 메시지

### 2. 로그 카테고리

#### 표준 카테고리
- DB: 데이터베이스 관련 로그
- WEB: 웹 관련 로그
- NETWORK: 네트워크 관련 로그
- SECURITY: 보안 관련 로그
- PERFORMANCE: 성능 관련 로그
- GENERAL: 일반 로그

### 3. 로그 파일 관리
- 카테고리별 별도 로그 파일 생성
- 로그 파일 자동 생성 및 디렉토리 관리

## 사용 방법

### 1. 표준 카테고리 사용

```java
Logger.info("일반 정보 메시지");
Logger.error("일반 오류 메시지");
Logger.error("예외와 함께", new RuntimeException("예외"));
Logger.debug("디버그 메시지");
Logger.warn("경고 메시지");

Logger.infoDb("DB 정보");
Logger.errorDb("DB 오류");
Logger.debugDb("DB 디버그");
Logger.warnDb("DB 경고");

Logger.infoWeb("Web 정보");
Logger.errorWeb("Web 오류");
Logger.debugWeb("Web 디버그");
Logger.warnWeb("Web 경고");
```

### 2. 로그 레벨 및 디렉토리 설정

```java
// 로그 레벨 설정
LogConfig.setCurrentLevel(LogLevel.INFO);

// 로그 디렉토리 확인
String logDir = LogConfig.getInstance().getLogDirectory();
```

## 로그 파일 구조

### 1. 파일 위치
- 기본 로그 디렉토리: `logs/`
- 각 카테고리별 로그 파일: `logs/{category}.log`
  - 예: `logs/DB.log`, `logs/WEB.log`, `logs/GENERAL.log`

### 2. 로그 메시지 형식
```
[타임스탬프] [로그레벨] 카테고리 - 메시지
예외 정보 (있을 경우)
```

## 테스트 코드 예시

JUnit5 기반 테스트 예시:
```java
import io.csh.utils.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {
    @Test
    void testInfoLogging() {
        assertDoesNotThrow(() -> Logger.info("Test info log"));
    }
    @Test
    void testDebugLogging() {
        assertDoesNotThrow(() -> Logger.debug("Test debug log"));
    }
    @Test
    void testWarnLogging() {
        assertDoesNotThrow(() -> Logger.warn("Test warn log"));
    }
    @Test
    void testErrorLogging() {
        assertDoesNotThrow(() -> Logger.error("Test error log"));
        assertDoesNotThrow(() -> Logger.error("Test error log with exception", new RuntimeException("Test exception")));
    }
}
```

## 주의사항
- Logger의 모든 메서드는 내부적으로 예외를 처리하므로, 호출 시 별도의 예외 처리가 필요 없습니다.
- 로그 파일 및 디렉토리는 자동으로 생성됩니다.
- 동적 카테고리, 파일 로테이션 등은 현재 기본 제공되지 않습니다. 