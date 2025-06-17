# 로깅 모듈 사용 가이드

## 목차
1. [시작하기](#시작하기)
2. [기본 사용법](#기본-사용법)
3. [고급 기능](#고급-기능)
4. [설정](#설정)
5. [모범 사례](#모범-사례)

## 시작하기

### 의존성 추가
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>logging</artifactId>
    <version>1.0.3</version>
</dependency>
```

## 기본 사용법

### 1. 정적 메서드를 사용한 로깅
```java
import io.csh.utils.logging.Logger;

public class StaticLoggingExample {
    public static void main(String[] args) {
        // 기본 로깅
        Logger.info("애플리케이션이 시작되었습니다.");
        
        // 예외 정보 포함 로깅
        try {
            // ... 코드 ...
        } catch (Exception e) {
            Logger.error("오류가 발생했습니다.", e);
        }
        
        // 다양한 로그 레벨 사용
        Logger.trace("상세 디버깅 정보");
        Logger.debug("디버그 정보");
        Logger.info("일반 정보");
        Logger.warn("경고 메시지");
        Logger.error("오류 메시지");
        Logger.fatal("치명적 오류 메시지");
    }
}
```

### 2. @CshLog 어노테이션을 사용한 로깅
```java
import io.csh.utils.logging.CshLog;

@CshLog
public class AnnotatedLoggingExample {
    private final String name;
    
    public AnnotatedLoggingExample(String name) {
        this.name = name;
    }
    
    public void process() {
        // 자동 생성된 로거 사용
        log.info("처리를 시작합니다: {}", name);
        
        try {
            // ... 비즈니스 로직 ...
            log.debug("중간 처리 결과: {}", result);
        } catch (Exception e) {
            log.error("처리 중 오류 발생", e);
            throw e;
        }
        
        log.info("처리가 완료되었습니다.");
    }
}
```

### 3. 로깅 설정 상태 확인
```java
import io.csh.utils.logging.Logger;

public class LoggingConfigExample {
    public void checkConfig() {
        // 설정 상태 문자열 가져오기
        String configStatus = Logger.getConfigStatus();
        System.out.println("현재 로깅 설정:");
        System.out.println(configStatus);
        
        // 또는 직접 출력하기
        Logger.printConfigStatus();
    }
}
```

## 고급 기능

### 1. 설정 상태 조회
- `Logger.getConfigStatus()`: 현재 로깅 설정 상태를 문자열로 반환
- `Logger.printConfigStatus()`: 현재 로깅 설정 상태를 콘솔에 출력

### 2. 로그 레벨
- TRACE: 가장 상세한 디버깅 정보
- DEBUG: 개발 시 디버깅 정보
- INFO: 일반적인 정보
- WARN: 경고 메시지
- ERROR: 오류 메시지
- FATAL: 치명적 오류 메시지

### 3. 로그 포맷
기본 포맷: `%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n`
- %d: 타임스탬프
- %thread: 스레드 이름
- %-5level: 로그 레벨
- %logger{36}: 로거 이름
- %msg: 로그 메시지
- %n: 줄바꿈

## 설정

### 1. application.properties
```properties
# 로그 레벨 설정
csh.logging.level=INFO

# 로그 파일 설정
csh.logging.file.path=logs
csh.logging.file.name=application.log
csh.logging.file.max-size=10MB
csh.logging.file.max-total-size=1GB
csh.logging.file.retention-days=365

# 로그 패턴 설정
csh.logging.pattern=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n

# 콘솔 출력 설정
csh.logging.console.enabled=true
```

### 2. VM 옵션
```bash
-Dcsh.logging.level=DEBUG
-Dcsh.logging.file.path=/path/to/logs
-Dcsh.logging.file.max-size=20MB
-Dcsh.logging.file.retention-days=30
```

## 모범 사례

### 1. 로그 레벨 사용
- TRACE: 매우 상세한 디버깅 정보
- DEBUG: 개발 시 디버깅 정보
- INFO: 일반적인 정보
- WARN: 잠재적 문제
- ERROR: 오류 상황
- FATAL: 치명적 오류

### 2. 예외 로깅
```java
try {
    // ... 코드 ...
} catch (Exception e) {
    log.error("오류 발생: {}", e.getMessage(), e);
    throw e;
}
```

### 3. 설정 상태 확인
```java
// 애플리케이션 시작 시
Logger.printConfigStatus();

// 또는 설정 상태를 변수에 저장
String configStatus = Logger.getConfigStatus();
```

### 4. 성능 고려사항
- 불필요한 문자열 연결 피하기
- 로그 레벨에 따른 조건부 로깅
- 대용량 객체 로깅 주의
- 로그 파일 크기 관리 