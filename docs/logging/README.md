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

### System Properties
JVM 옵션으로 로깅 설정을 지정할 수 있습니다:

```bash
# 디버그 모드 활성화
-Dcsh.debug=true

# 로그 레벨 설정 (TRACE, DEBUG, INFO, WARN, ERROR)
-Dlogging.level=INFO
```

### Properties File
`logging.properties` 파일을 통해 설정할 수 있습니다:

```properties
# 로그 레벨
logging.level=INFO
```

### Default Values
- 로그 레벨: INFO
- 디버그 모드: false

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