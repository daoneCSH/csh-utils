# CSH Utils Logging Module

## 최근 변경사항 (2024-03-19)
- SLF4J, Logback 의존성 제거
- 단순화된 로깅 구현으로 변경
- `-Dcsh.debug=true` 옵션으로 디버그 출력 제어
- System.out을 사용한 로그 출력

## Overview
CSH Utils의 로깅 모듈은 간단하고 효율적인 로깅 기능을 제공합니다. 외부 의존성 없이 기본적인 로깅 기능을 제공하며, 개발 환경에서 디버그 출력을 제어할 수 있습니다.

## Features
- 다양한 로그 레벨 지원 (TRACE, DEBUG, INFO, WARN, ERROR)
- 간단한 로그 포맷팅
- 디버그 모드 지원
- 설정 가능한 로그 레벨

## Configuration

### 우선순위
로깅 설정은 다음 순서로 적용됩니다 (위에서 아래로 우선순위가 낮아짐):

1. VM/시스템 프로퍼티 (`-D` 옵션)
2. application 프로퍼티 (`application.properties` 또는 `application.yml`)
3. logging 프로퍼티 (`logging.properties`)
4. 기본값

예를 들어, 로그 레벨을 설정하는 경우:
```bash
# 1. VM 옵션 (최우선)
-Dlogging.level=DEBUG

# 2. application.properties
logging.level=INFO

# 3. logging.properties
logging.level=WARN

# 4. 기본값
# 기본값: INFO
```

### 설정 방법

#### JVM 옵션으로 설정
```bash
-Dlogging.level=INFO
-Dlogging.file.path=/path/to/logs
-Dlogging.file.max-size=20MB
-Dlogging.file.retention-days=30
```

#### application.properties로 설정
```properties
logging.level=INFO
logging.file.path=/path/to/logs
logging.file.max-size=20MB
logging.file.retention-days=30
```

#### logging.properties로 설정
```properties
logging.level=INFO
logging.file.path=/path/to/logs
logging.file.max-size=20MB
logging.file.retention-days=30
```

### 기본값
- 로그 레벨: `INFO`
- 로그 파일 경로: `logs` 디렉토리
- 로그 파일 이름: `application.log`
- 최대 파일 크기: `10MB`
- 최대 전체 크기: `1GB`
- 보관 기간: `365`일
- 압축 단위: `WEEK`
- 압축 값: `1`
- 압축 형식: `gz`
- 로그 패턴: `%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n`
- 콘솔 출력: `true`

### 로깅 설정 상태 확인
현재 적용된 로깅 설정을 확인하려면 `LoggingConfig` 클래스의 `printLoggingStatus()` 메서드를 사용할 수 있습니다:

```java
import io.csh.utils.logging.config.LoggingConfig;

public class LoggingExample {
    public static void main(String[] args) {
        // 로깅 설정 상태 출력
        LoggingConfig config = LoggingConfig.getInstance();
        System.out.println(config.printLoggingStatus());
    }
}
```

출력 예시:
```
=== Logging Configuration Status ===
Log Level: INFO
Console Enabled: true
Log File Path: logs
Log File Name: application.log
Log File Max Size: 10MB
Log File Max Total Size: 1GB
Log File Retention Days: 365
Log File Compression: WEEK 1 (gz)
Log Pattern: %d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
===================================
```

이를 통해 현재 적용된 로깅 설정과 우선순위에 따라 어떤 값이 선택되었는지 확인할 수 있습니다.

## Usage Examples

### Basic Logging
```java
// Logger 인스턴스 생성
Logger logger = new Logger("MyClass");

// 기본 로깅
logger.info("애플리케이션 시작");
logger.debug("디버그 메시지");
logger.warn("경고 메시지");
logger.error("오류 메시지");

// 예외와 함께 로깅
try {
    // ... 코드 ...
} catch (Exception e) {
    logger.error("오류 발생", e);
}
```

### Log Message Format
로그 메시지는 다음과 같은 형식으로 출력됩니다:
```
2024-03-19 10:30:45.123 [INFO] MyClass - 애플리케이션 시작
```

예외가 있는 경우:
```
2024-03-19 10:30:45.123 [ERROR] MyClass - 오류 발생
java.lang.RuntimeException: 예외 메시지
    at com.example.MyClass.method(MyClass.java:10)
    at com.example.MyClass.main(MyClass.java:5)
```

## Log Levels

### TRACE
가장 상세한 로그 레벨입니다. 모든 로그 메시지가 출력됩니다.
```java
logger.trace("가장 상세한 로그 메시지");
```

### DEBUG
디버깅을 위한 상세 정보를 기록합니다.
```java
logger.debug("디버깅을 위한 상세 정보");
```

### INFO
일반적인 정보를 기록합니다.
```java
logger.info("일반적인 정보 메시지");
```

### WARN
경고 메시지를 기록합니다.
```java
logger.warn("경고 메시지");
```

### ERROR
오류 메시지를 기록합니다.
```java
logger.error("오류 메시지");
logger.error("오류 발생", exception);
```

## Notes
- 로그 메시지는 UTF-8 인코딩을 사용합니다.
- 로그 메시지는 자동으로 타임스탬프가 추가됩니다.
- 예외가 발생한 경우 스택 트레이스가 자동으로 기록됩니다.
- 디버그 모드가 활성화된 경우에만 로그가 출력됩니다.
- 설정 파일이 없는 경우 기본값이 사용됩니다.

## 테스트 코드 예시

JUnit5 기반 테스트 예시:
```java
import io.csh.utils.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {
    @Test
    void testInfoLogging() {
        Logger logger = new Logger("TestClass");
        assertDoesNotThrow(() -> logger.info("Test info log"));
    }
    
    @Test
    void testDebugLogging() {
        Logger logger = new Logger("TestClass");
        assertDoesNotThrow(() -> logger.debug("Test debug log"));
    }
    
    @Test
    void testWarnLogging() {
        Logger logger = new Logger("TestClass");
        assertDoesNotThrow(() -> logger.warn("Test warn log"));
    }
    
    @Test
    void testErrorLogging() {
        Logger logger = new Logger("TestClass");
        assertDoesNotThrow(() -> logger.error("Test error log"));
        assertDoesNotThrow(() -> logger.error("Test error log with exception", 
            new RuntimeException("Test exception")));
    }
}
```

## 주의사항
- 로그 출력은 `-Dcsh.debug=true` 옵션이 설정된 경우에만 이루어집니다.
- 로그 레벨은 `logging.level` 속성으로 설정할 수 있습니다.
- 로그 메시지는 System.out을 통해 출력됩니다.
- 파일 로깅은 현재 지원되지 않습니다. 