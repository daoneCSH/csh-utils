# CSH Utils Logging Module

## 최근 변경사항 (2025-06-11)
- Logger, AsyncLogger의 모든 메서드는 static 방식으로 제공됩니다.
- Logger의 각 메서드는 내부적으로 예외(CshException)를 처리하며, 실패 시 System.err에 메시지를 출력합니다.
- LogConfig의 로그 디렉토리 경로는 getLogDirectory()로 확인할 수 있습니다.
- JUnit 기반의 Logger 테스트 코드가 추가되었습니다.

## Overview
CSH Utils의 로깅 모듈은 간단하고 효율적인 로깅 기능을 제공합니다. 로그 레벨, 파일 크기, 백업 파일 수 등을 설정할 수 있으며, 자동 로그 로테이션을 지원합니다.

## Features
- 다양한 로그 레벨 지원 (TRACE, DEBUG, INFO, WARN, ERROR)
- 로그 파일 자동 로테이션
- 설정 가능한 로그 파일 크기와 백업 파일 수
- 시스템 프로퍼티 또는 설정 파일을 통한 설정 관리

## Configuration

### System Properties
JVM 옵션으로 로깅 설정을 지정할 수 있습니다:

```bash
# 로그 레벨 설정 (TRACE, DEBUG, INFO, WARN, ERROR)
-Dspice.log.level=trace

# 로그 파일 최대 크기 설정 (바이트 단위)
-Dspice.log.maxFileSize=10485760  # 10MB

# 최대 백업 파일 수 설정
-Dspice.log.maxBackupCount=5
```

### Properties File
`logging.properties` 파일을 통해 설정할 수 있습니다:

```properties
# 로그 레벨
log.level=trace

# 로그 파일 최대 크기 (바이트 단위)
log.maxFileSize=10485760

# 최대 백업 파일 수
log.maxBackupCount=5

# 로그 디렉토리
log.directory=logs
```

### Default Values
- 로그 레벨: INFO
- 로그 파일 크기: 10MB
- 백업 파일 수: 5개
- 로그 디렉토리: logs

## Usage Examples

### Basic Logging
```java
// 기본 로깅
Logger.info("애플리케이션 시작");
Logger.debug("디버그 메시지");
Logger.warn("경고 메시지");
Logger.error("오류 메시지");

// 예외와 함께 로깅
try {
    // ... 코드 ...
} catch (Exception e) {
    Logger.error("오류 발생", e);
}
```

### Log File Rotation
로그 파일은 다음과 같은 규칙으로 관리됩니다:

1. 파일 크기가 설정된 크기를 초과하면 자동으로 새로운 파일로 로테이션됩니다.
2. 날짜가 변경될 때마다 새로운 파일이 생성됩니다.
3. 백업 파일은 다음과 같은 형식으로 저장됩니다:
   - `application.log.YYYY-MM-DD.0`
   - `application.log.YYYY-MM-DD.1`
   - `application.log.YYYY-MM-DD.2`
   - ...

### Configuration Examples

1. 상세 로깅 (TRACE 레벨)과 50MB 파일 크기:
```bash
-Dspice.log.level=trace -Dspice.log.maxFileSize=52428800
```

2. 에러만 로깅하고 1GB 파일 크기:
```bash
-Dspice.log.level=error -Dspice.log.maxFileSize=1073741824
```

3. 3개의 백업 파일만 유지:
```bash
-Dspice.log.maxBackupCount=3
```

4. 커스텀 로그 디렉토리:
```properties
log.directory=custom/logs
```

## Log Levels

### TRACE
가장 상세한 로그 레벨입니다. 모든 로그 메시지가 출력됩니다.
```java
Logger.trace("가장 상세한 로그 메시지");
```

### DEBUG
디버깅을 위한 상세 정보를 기록합니다.
```java
Logger.debug("디버깅을 위한 상세 정보");
```

### INFO
일반적인 정보를 기록합니다.
```java
Logger.info("일반적인 정보 메시지");
```

### WARN
경고 메시지를 기록합니다.
```java
Logger.warn("경고 메시지");
```

### ERROR
오류 메시지를 기록합니다.
```java
Logger.error("오류 메시지");
Logger.error("오류 발생", exception);
```

## Notes
- 로그 파일은 UTF-8 인코딩을 사용합니다.
- 로그 메시지는 자동으로 타임스탬프가 추가됩니다.
- 예외가 발생한 경우 스택 트레이스가 자동으로 기록됩니다.
- 설정 파일이 없는 경우 기본값이 사용됩니다.
- 잘못된 설정이 입력된 경우 기본값이 사용됩니다.

## 개요
CSH Utils의 로깅 모듈은 애플리케이션의 로그를 효율적으로 관리하고 기록하기 위한 유틸리티를 제공합니다.

## 주요 기능

### 1. 로그 레벨
- TRACE: 가장 상세한 로그 레벨
- DEBUG: 디버깅을 위한 상세 정보
- INFO: 일반적인 정보성 메시지
- WARN: 경고 메시지
- ERROR: 오류 메시지

### 2. 로그 파일 관리
- 단일 로그 파일 사용
- 로그 파일 자동 생성 및 디렉토리 관리

## 사용 방법

### 1. 기본 로깅

```java
Logger.info("일반 정보 메시지");
Logger.error("일반 오류 메시지");
Logger.error("예외와 함께", new RuntimeException("예외"));
Logger.debug("디버그 메시지");
Logger.warn("경고 메시지");
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
- 로그 파일: `logs/application.log`

### 2. 로그 메시지 형식
```
[타임스탬프] [로그레벨] - 메시지
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
- 파일 로테이션 등은 현재 기본 제공되지 않습니다. 