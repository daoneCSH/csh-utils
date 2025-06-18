# CSH Utils Logging Module

## 최근 변경사항 (2024-06-18)
- **🎉 새로운 간편한 사용법**: `Logging` 클래스 추가
- 표준 로깅 레벨 지원 (TRACE, DEBUG, INFO, WARN, ERROR)
- 클래스명 자동 감지 기능
- 스택 트레이스 기반 호출 클래스 식별
- Java Agent 호환성 개선

## Overview
CSH Utils의 로깅 모듈은 간단하고 효율적인 로깅 기능을 제공합니다. 외부 의존성 없이 기본적인 로깅 기능을 제공하며, Java Agent와 일반 Java 애플리케이션 모두에서 사용할 수 있습니다.

## Features
- **간편한 사용법**: `Logging.info("메시지")` 형태로 바로 사용
- **표준 로그 레벨**: TRACE, DEBUG, INFO, WARN, ERROR 지원
- **클래스명 자동 감지**: 스택 트레이스로 호출 클래스 자동 파악
- **설정 가능한 로그 레벨**: 런타임 및 시스템 프로퍼티로 제어
- **Java Agent 호환**: 클래스로더 분리 환경에서 안정적 동작
- **스레드 안전**: 멀티스레드 환경에서 안전하게 사용

## Quick Start

### 간편한 사용법 (권장)
```java
import io.csh.utils.logging.Logging;

// 기본 로깅
Logging.info("애플리케이션 시작");
Logging.debug("디버그 메시지");
Logging.warn("경고 메시지");
Logging.error("오류 메시지");

// 예외와 함께 로깅
try {
    // 작업 수행
} catch (Exception e) {
    Logging.error("오류 발생", e);
}
```

### 모든 로그 레벨 지원
```java
Logging.trace("메서드 진입");           // 가장 상세한 디버깅
Logging.debug("데이터 처리 중");        // 개발 시 디버깅
Logging.info("애플리케이션 시작");      // 일반적인 정보
Logging.warn("성능 저하 감지");         // 경고 메시지
Logging.error("오류 발생", exception);  // 오류 및 예외
```

## Configuration

### 로그 레벨 설정

#### 1. 런타임 설정 (최우선)
```java
import io.csh.utils.logging.LogLevel;

Logging.setLogLevel(LogLevel.DEBUG);
```

#### 2. 시스템 프로퍼티
```bash
# JVM 옵션으로 설정
-Dcsh.logging.level=DEBUG

# 환경 변수로 설정
export CSH_LOGGING_LEVEL=INFO
```

#### 3. 설정 우선순위
1. **런타임 설정**: `Logging.setLogLevel(LogLevel.DEBUG)`
2. **시스템 프로퍼티**: `-Dcsh.logging.level=INFO`
3. **환경 변수**: `CSH_LOGGING_LEVEL=WARN`
4. **기본값**: `INFO`

### 로그 포맷
로그는 다음과 같은 형식으로 출력됩니다:
```
2024-06-18 14:30:45.123 [main] INFO  MyClass - 애플리케이션 시작
2024-06-18 14:30:45.124 [main] DEBUG MyClass - 데이터 처리 중
2024-06-18 14:30:45.125 [main] WARN  MyClass - 성능 저하 감지
2024-06-18 14:30:45.126 [main] ERROR MyClass - 오류 발생
```

포맷 구성:
- **타임스탬프**: `yyyy-MM-dd HH:mm:ss.SSS`
- **스레드명**: 실행 중인 스레드 이름
- **로그 레벨**: TRACE, DEBUG, INFO, WARN, ERROR
- **클래스명**: 로그를 호출한 클래스 (자동 감지)
- **메시지**: 실제 로그 내용

## Usage Examples

### Basic Logging
```java
import io.csh.utils.logging.Logging;

public class MyApplication {
    public static void main(String[] args) {
        Logging.info("애플리케이션 시작");
        
        try {
            processData();
        } catch (Exception e) {
            Logging.error("애플리케이션 오류", e);
        }
        
        Logging.info("애플리케이션 종료");
    }
    
    private static void processData() {
        Logging.trace("processData() 메서드 진입");
        Logging.debug("데이터 처리 시작");
        
        // 데이터 처리 로직
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                Logging.warn("중간 지점 도달");
            }
            Logging.trace("처리 중: {}", i);
        }
        
        Logging.trace("processData() 메서드 종료");
    }
}
```

### 조건부 로깅 (성능 최적화)
```java
// 비용이 많이 드는 연산의 경우
if (Logging.isDebugEnabled()) {
    String expensiveData = generateExpensiveDebugData();
    Logging.debug("디버그 데이터: {}", expensiveData);
}

// 조건부 INFO 로깅
if (Logging.isInfoEnabled()) {
    Logging.info("정보 메시지");
}
```

### Java Agent에서 사용
```java
import io.csh.utils.logging.Logging;

public class YourAgent {
    public static void premain(String agentArgs) {
        Logging.info("Agent 시작");
        
        try {
            // Agent 초기화 작업
            Logging.debug("Agent 초기화 중...");
        } catch (Exception e) {
            Logging.error("Agent 초기화 실패", e);
        }
    }
}
```

### 기존 방식 (고급 사용자용)
```java
import io.csh.utils.logging.Logger;
import io.csh.utils.logging.LoggerFactory;

Logger logger = LoggerFactory.getLogger(MyClass.class);
logger.info("메시지");
```

## Log Levels

### TRACE (0)
가장 상세한 디버깅 정보
- 메서드 진입/종료
- 변수 값 추적
- 상세한 실행 흐름

```java
Logging.trace("메서드 진입");
Logging.trace("변수 값: {}", variable);
```

### DEBUG (1)
개발 시 디버깅 정보
- 조건문 분기 정보
- 중간 결과값
- 성능 측정 지점

```java
Logging.debug("조건 분기: {}", condition);
Logging.debug("중간 결과: {}", result);
```

### INFO (2)
일반적인 정보성 메시지
- 애플리케이션 시작/종료
- 주요 비즈니스 로직 실행
- 설정 정보

```java
Logging.info("애플리케이션 시작");
Logging.info("사용자 {} 로그인", userId);
```

### WARN (3)
경고 메시지 (잠재적 문제)
- 성능 저하 가능성
- 권장되지 않는 사용법
- 임시 해결책 적용

```java
Logging.warn("성능 저하 감지");
Logging.warn("권장되지 않는 API 사용");
```

### ERROR (4)
오류 및 예외 상황
- 예외 발생
- 시스템 오류
- 복구 불가능한 상황

```java
Logging.error("파일 읽기 실패", exception);
Logging.error("데이터베이스 연결 실패");
```

## 모범 사례

### 로그 메시지 작성
```java
// 좋은 예
Logging.info("사용자 {} 로그인 성공", userId);
Logging.error("파일 {} 읽기 실패: {}", fileName, e.getMessage(), e);

// 나쁜 예
Logging.info("로그인");
Logging.error("오류");
```

### 성능 고려사항
```java
// 비용이 많이 드는 연산은 조건부 로깅 사용
if (Logging.isDebugEnabled()) {
    String expensiveData = generateExpensiveDebugData();
    Logging.debug("디버그 데이터: {}", expensiveData);
}

// 문자열 연결 대신 조건부 로깅 사용
// 나쁜 예: Logging.debug("데이터: " + expensiveObject.toString());
// 좋은 예: if (Logging.isDebugEnabled()) { Logging.debug("데이터: {}", expensiveObject); }
```

## 문제 해결

### 로그가 출력되지 않는 경우
1. 로그 레벨 확인: `Logging.getLogLevel()`
2. 로그 레벨 설정: `Logging.setLogLevel(LogLevel.DEBUG)`
3. 시스템 프로퍼티 확인: `-Dcsh.logging.level=DEBUG`

### 클래스명이 잘못 표시되는 경우
- 스택 트레이스 기반으로 자동 감지되므로 정상 동작
- 필요시 기존 방식 사용: `LoggerFactory.getLogger(MyClass.class)`

## 마이그레이션 가이드

### 기존 코드에서 새로운 방식으로 변경

**기존 방식:**
```java
Logger logger = LoggerFactory.getLogger(MyClass.class);
logger.info("메시지");
```

**새로운 방식:**
```java
Logging.info("메시지");
```

**변경 사항:**
- `LoggerFactory.getLogger()` 제거
- `Logging.` 접두사로 변경
- 클래스명 자동 감지로 수동 지정 불필요

## Notes
- 로그 메시지는 UTF-8 인코딩을 사용합니다.
- 로그 메시지는 자동으로 타임스탬프가 추가됩니다.
- 예외가 발생한 경우 스택 트레이스가 자동으로 기록됩니다.
- 클래스명은 스택 트레이스를 통해 자동으로 감지됩니다.
- Java Agent 환경에서 안정적으로 동작합니다.
- 외부 의존성 없이 순수 Java만 사용합니다.

## 테스트 코드 예시

JUnit5 기반 테스트 예시:
```java
import io.csh.utils.logging.Logging;
import io.csh.utils.logging.LogLevel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoggingTest {
    @Test
    void testSimpleLogging() {
        Logging.setLogLevel(LogLevel.DEBUG);
        
        Logging.trace("TRACE 메시지");
        Logging.debug("DEBUG 메시지");
        Logging.info("INFO 메시지");
        Logging.warn("WARN 메시지");
        Logging.error("ERROR 메시지");
        
        assertTrue(Logging.isDebugEnabled());
    }
    
    @Test
    void testExceptionLogging() {
        try {
            throw new RuntimeException("테스트 예외");
        } catch (Exception e) {
            Logging.error("예외 발생", e);
        }
    }
}
``` 