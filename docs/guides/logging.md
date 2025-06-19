# 로깅 모듈 가이드

## 1. 소개

로깅 모듈은 Java 애플리케이션을 위한 고급 로깅 시스템을 제공합니다. 이 모듈은 외부 의존성 없이 기본적인 로깅 기능부터 파일 로깅, 중복 로그 방지, 로그 회전 등의 고급 기능까지 제공하며, Java Agent와 일반 Java 애플리케이션 모두에서 사용할 수 있습니다.

**🎉 새로운 간편한 사용법**: `LoggerFactory.getLogger()` 없이 바로 사용할 수 있는 `Logging` 클래스를 제공합니다.

## 2. 기본 사용

### 2.1 간편한 로깅 (권장)
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

### 2.2 모든 로그 레벨 지원
```java
// TRACE - 가장 상세한 디버깅 정보
Logging.trace("메서드 진입");

// DEBUG - 개발 시 디버깅 정보
Logging.debug("처리 중인 데이터: {}", data);

// INFO - 일반적인 정보성 메시지
Logging.info("애플리케이션 시작");

// WARN - 경고 메시지 (잠재적 문제)
Logging.warn("성능 저하 감지");

// ERROR - 오류 및 예외 상황
Logging.error("오류 발생", exception);
```

### 2.3 기존 방식 (고급 사용자용)
```java
import io.csh.utils.logging.Logger;
import io.csh.utils.logging.LoggerFactory;

Logger logger = LoggerFactory.getLogger(MyClass.class);
logger.info("메시지");
```

## 3. 로그 설정

### 3.1 로그 레벨 설정
```java
import io.csh.utils.logging.LogLevel;

// 런타임에 로그 레벨 변경
Logging.setLogLevel(LogLevel.DEBUG);

// 현재 로그 레벨 확인
LogLevel currentLevel = Logging.getLogLevel();
```

### 3.2 시스템 프로퍼티로 설정
```bash
# JVM 옵션으로 설정
-Dcsh.logging.level=DEBUG

# 환경 변수로 설정
export CSH_LOGGING_LEVEL=INFO
```

### 3.3 설정 우선순위
1. **런타임 설정**: `Logging.setLogLevel(LogLevel.DEBUG)`
2. **시스템 프로퍼티**: `-Dcsh.logging.level=INFO`
3. **환경 변수**: `CSH_LOGGING_LEVEL=WARN`
4. **기본값**: `INFO`

## 4. 고급 기능

### 4.1 파일 로깅

파일 로깅을 활성화하면 로그가 파일에도 저장됩니다:

```bash
# 파일 로깅 활성화
-Dcsh.logging.file=true
-Dcsh.logging.dir=/path/to/logs
-Dcsh.logging.rotation.enabled=true
-Dcsh.logging.keep.days=30
-Dcsh.logging.prefix=myapp
```

**설정 옵션:**
- `csh.logging.file`: 파일 로깅 활성화 (기본값: false)
- `csh.logging.console`: 콘솔 출력 활성화 (기본값: true)
- `csh.logging.dir`: 로그 디렉토리 (기본값: logs)
- `csh.logging.rotation.enabled`: 로그 회전 활성화 (기본값: true)
- `csh.logging.keep.days`: 로그 보관 기간 (기본값: 30)
- `csh.logging.prefix`: 로그 파일 접두사 (기본값: csh)

### 4.2 중복 로그 방지

동일한 ID의 로그가 짧은 시간 내에 반복되는 것을 방지할 수 있습니다:

```java
import io.csh.utils.logging.LoggerImpl;

LoggerImpl loggerImpl = (LoggerImpl) LoggerFactory.getLogger(MyClass.class);

// 5초 간격으로 중복 방지
loggerImpl.logWithDuplicateFilter(LogLevel.INFO, "connection-retry", "Connection failed", 5);
```

### 4.3 조건부 로깅

성능 최적화를 위해 조건부 로깅을 사용할 수 있습니다:

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

## 5. Java Agent에서 사용

```java
import io.csh.utils.logging.Logging;

public class YourAgent {
    public static void premain(String agentArgs) {
        // 간편한 로깅 사용
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

## 6. 로그 포맷

### 6.1 기본 형식
로그는 다음과 같은 형식으로 출력됩니다:

```
2024-06-18 14:30:45.123 [main] INFO  MyClass - 애플리케이션 시작
2024-06-18 14:30:45.124 [main] DEBUG MyClass - 데이터 처리 중
2024-06-18 14:30:45.125 [main] WARN  MyClass - 성능 저하 감지
2024-06-18 14:30:45.126 [main] ERROR MyClass - 오류 발생
java.lang.RuntimeException: 테스트 예외
    at MyClass.method(MyClass.java:10)
    ...
```

### 6.2 중복 방지 형식 (Spiceware 스타일)
```
2024-06-18 14:30:45.123 INFO [CSH:connection-retry] Connection failed
```

포맷 구성:
- **타임스탬프**: `yyyy-MM-dd HH:mm:ss.SSS`
- **스레드명**: 실행 중인 스레드 이름
- **로그 레벨**: TRACE, DEBUG, INFO, WARN, ERROR
- **클래스명**: 로그를 호출한 클래스 (자동 감지)
- **메시지**: 실제 로그 내용

## 7. 모범 사례

### 7.1 로그 메시지 작성
```java
// 좋은 예
Logging.info("사용자 {} 로그인 성공", userId);
Logging.error("파일 {} 읽기 실패: {}", fileName, e.getMessage(), e);

// 나쁜 예
Logging.info("로그인");
Logging.error("오류");
```

### 7.2 로그 레벨 사용 가이드
- **TRACE**: 메서드 진입/종료, 변수 값 추적
- **DEBUG**: 조건문 분기, 중간 결과값, 성능 측정
- **INFO**: 애플리케이션 시작/종료, 주요 비즈니스 로직
- **WARN**: 성능 저하 가능성, 권장되지 않는 사용법
- **ERROR**: 예외 발생, 시스템 오류, 복구 불가능한 상황

### 7.3 성능 고려사항
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

### 7.4 파일 로깅 고려사항
```java
// 파일 로깅 사용 시 디스크 공간 모니터링
// 로그 회전 및 보관 기간 적절히 설정
// 중요 로그는 별도 파일로 분리 고려
```

## 8. 완전한 예제

```java
import io.csh.utils.logging.Logging;
import io.csh.utils.logging.LogLevel;
import io.csh.utils.logging.Logger;
import io.csh.utils.logging.LoggerFactory;
import io.csh.utils.logging.LoggerImpl;

public class MyApplication {
    public static void main(String[] args) {
        // 로그 레벨 설정
        Logging.setLogLevel(LogLevel.DEBUG);
        
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
        
        if (Logging.isDebugEnabled()) {
            Logging.debug("데이터 처리 시작");
        }
        
        // 데이터 처리 로직
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                Logging.warn("중간 지점 도달");
            }
            
            if (Logging.isTraceEnabled()) {
                Logging.trace("처리 중: {}", i);
            }
        }
        
        // 중복 로그 방지 예제
        LoggerImpl loggerImpl = (LoggerImpl) LoggerFactory.getLogger(MyApplication.class);
        loggerImpl.logWithDuplicateFilter(LogLevel.INFO, "data-process", "데이터 처리 완료", 10);
        
        Logging.trace("processData() 메서드 종료");
    }
}
```

## 9. 문제 해결

### 9.1 로그가 출력되지 않는 경우
1. 로그 레벨 확인: `Logging.getLogLevel()`
2. 로그 레벨 설정: `Logging.setLogLevel(LogLevel.DEBUG)`
3. 시스템 프로퍼티 확인: `-Dcsh.logging.level=DEBUG`

### 9.2 파일 로깅이 작동하지 않는 경우
1. 파일 로깅 활성화 확인: `-Dcsh.logging.file=true`
2. 로그 디렉토리 권한 확인
3. 디스크 공간 확인
4. 로그 파일 생성 확인

### 9.3 클래스명이 잘못 표시되는 경우
- 스택 트레이스 기반으로 자동 감지되므로 정상 동작
- 필요시 기존 방식 사용: `LoggerFactory.getLogger(MyClass.class)`

## 10. 주의사항

1. **Java Agent 호환성**: 클래스로더 분리 환경에서 안정적 동작
2. **외부 의존성 없음**: 순수 Java만 사용
3. **가벼운 구현**: 메모리 사용량 최소화
4. **스레드 안전**: 멀티스레드 환경에서 안전하게 사용 가능
5. **파일 I/O**: 파일 로깅 사용 시 성능 영향 고려
6. **디스크 공간**: 로그 회전 및 보관 기간 적절히 설정

## 11. 관련 문서

- [설계 문서](../design/logging.md)
- [모듈 README](../modules/logging/README.md)
- [Output 모듈 가이드](output.md) - 파일 출력 기능이 필요한 경우 참조

## 12. 마이그레이션 가이드

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

### Spiceware Logger에서 마이그레이션

**Spiceware Logger:**
```java
Logger.info("id", "Message");
```

**CSH Utils Logger:**
```java
LoggerImpl loggerImpl = (LoggerImpl) LoggerFactory.getLogger(MyClass.class);
loggerImpl.logWithDuplicateFilter(LogLevel.INFO, "id", "Message", 0);
``` 