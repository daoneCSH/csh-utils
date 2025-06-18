# 로깅 모듈 가이드

## 1. 소개

로깅 모듈은 Java 애플리케이션을 위한 간단하고 효율적인 로깅 시스템을 제공합니다. 이 모듈은 외부 의존성 없이 기본적인 로깅 기능을 제공하며, Java Agent와 일반 Java 애플리케이션 모두에서 사용할 수 있습니다.

## 2. 기본 사용

### 2.1 기본 로깅
```java
import io.csh.utils.integration.CSHUtils;

// 정보 로그
CSHUtils.Logging.info(MyClass.class, "Info message");

// 에러 로그
CSHUtils.Logging.error(MyClass.class, "Error message");

// 예외와 함께 로깅
try {
    // 작업 수행
} catch (Exception e) {
    CSHUtils.Logging.error(MyClass.class, "Error occurred", e);
}
```

### 2.2 로그 레벨
```java
// 디버그 로그
CSHUtils.Logging.debug(MyClass.class, "Debug message");

// 정보 로그
CSHUtils.Logging.info(MyClass.class, "Info message");

// 경고 로그
CSHUtils.Logging.warn(MyClass.class, "Warning message");

// 에러 로그
CSHUtils.Logging.error(MyClass.class, "Error message");
```

## 3. 로그 설정

### 3.1 로그 레벨 설정
```java
import io.csh.utils.logging.LogLevel;

// 로그 레벨 설정
CSHUtils.Logging.setLogLevel(LogLevel.DEBUG);
```

### 3.2 로그 포맷 설정
```java
// 로그 포맷 설정
CSHUtils.Logging.setLogFormat("[%timestamp%] [%level%] [%class%] %message%");
```

## 4. Java Agent에서 사용

```java
import io.csh.utils.integration.CSHUtils;

public class YourAgent {
    public static void premain(String agentArgs) {
        // 로그 기록
        CSHUtils.Logging.info(YourAgent.class, "Agent started");
    }
}
```

## 5. 모범 사례

### 5.1 로그 메시지 작성
- 간결하고 명확한 메시지 사용
- 컨텍스트 정보 포함
- 예외 정보 상세히 기록

### 5.2 로그 레벨 사용
- DEBUG: 개발 시 상세 정보
- INFO: 일반적인 정보
- WARN: 잠재적 문제
- ERROR: 심각한 문제

### 5.3 성능 고려
- 불필요한 로그 최소화
- 로그 레벨 적절히 설정
- 로그 포맷 최적화

## 6. 문제 해결

### 6.1 일반적인 문제
- 로그 파일 접근 권한
- 로그 파일 크기 관리
- 로그 레벨 설정

### 6.2 디버깅
- 로그 레벨 조정
- 로그 포맷 확인
- 예외 스택 트레이스 확인

## 주의사항
1. Java Agent 환경에서 사용 가능
2. 외부 의존성 없이 순수 Java로 구현
3. 메인 애플리케이션의 클래스패스에 의존하지 않음
4. 가벼운 구현으로 성능 영향 최소화

## 예제 코드
```java
import io.csh.utils.logging.Log;

public class Example {
    public void process() {
        // 기본 로깅
        Log.trace("Detailed trace information");
        Log.debug("Debug information");
        Log.info("General information");
        Log.warn("Warning message");
        Log.error("Error message");
        Log.fatal("Fatal error message");

        // 예외 로깅
        try {
            // ... some code ...
        } catch (Exception e) {
            Log.error("Error occurred", e);
        }

        // 중복 제어 로깅
        Log.info("request-123", "Processing request", 60);
    }
}
```

## 로그 레벨

1. **TRACE**
   - 가장 상세한 디버깅 정보
   - 개발 시에만 사용 권장

2. **DEBUG**
   - 개발 시 디버깅 정보
   - 프로덕션 환경에서는 비활성화 권장

3. **INFO**
   - 일반적인 정보
   - 애플리케이션 동작 상태

4. **WARN**
   - 잠재적인 문제
   - 주의가 필요한 상황

5. **ERROR**
   - 오류 발생
   - 예외 처리 필요

6. **FATAL**
   - 치명적인 오류
   - 애플리케이션 종료 필요

## 모범 사례

1. **로그 메시지 작성**
   ```java
   // 좋은 예
   Log.error("사용자 {} 처리 실패: {}", userId, e.getMessage(), e);
   
   // 나쁜 예
   Log.error("오류 발생");
   ```

2. **성능 고려사항**
   - 불필요한 로그 레벨 비활성화
   - 로그 메시지에 객체 직렬화 최소화

3. **보안 고려사항**
   - 민감한 정보 로깅 금지

## 관련 문서
- [설계 문서](../design/logging.md)
- [모듈 README](../../logging/README.md)
- [릴리즈 노트](../releases/logging-1.0.0.md)
- [Output 모듈 가이드](output.md) - 파일 출력 기능이 필요한 경우 참조

## 예제 프로젝트
전체 예제는 [예제 프로젝트](https://github.com/csh-utils/logging-example)를 참조하세요. 