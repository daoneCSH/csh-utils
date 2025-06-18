# 로깅 모듈 설계

## 개요
Java Agent 호환성을 고려한 가벼운 로깅 모듈입니다. 검증된 기존 구현을 기반으로 현대적인 개선을 적용했습니다.

## 설계 목표
- Java Agent 환경에서의 안정적인 동작
- 최소한의 의존성
- 가벼운 구현
- 쉬운 설정과 사용
- 현대적인 Java 기능 활용
- 로깅과 출력 분리

## 기존 구현의 장점
- 단순하고 안정적인 구현
- Java Agent 환경에서 검증된 동작
- 중복 로그 제어 기능

## 개선 사항
1. **인터페이스 기반 설계**
   - 확장성 향상
   - 테스트 용이성
   - 구현체 교체 가능

2. **설정 관리 개선**
   - 시스템 프로퍼티 기반 설정
   - 기본값 제공
   - 설정 검증 강화

3. **성능 최적화**
   - 스레드 안전성 강화
   - 메모리 사용 최적화

4. **코드 품질**
   - JavaDoc 문서화
   - 단위 테스트
   - 코드 가독성 향상

5. **관심사 분리**
   - 로깅과 출력 분리
   - 출력은 output 모듈에서 처리
   - 모듈 간 결합도 최소화

## 아키텍처

### 1. 패키지 구조
```
io.csh.utils.logging
├── Logger.java           # 로거 인터페이스
├── LoggerImpl.java       # 로거 구현체
├── LoggerFactory.java    # 로거 팩토리
└── SimpleLogger.java     # 간단한 로거 구현
```

### 2. 클래스 다이어그램
```
Logger (Interface)
    ↑
LoggerImpl
    ↑
LoggerFactory
    ↑
SimpleLogger
```

## 주요 클래스 설명

### 1. Logger 인터페이스
```java
public interface Logger {
    void info(String message);
    void info(String message, Throwable thrown);
    void error(String message);
    void error(String message, Throwable thrown);
}
```
- 로깅 기능 정의
- 다양한 로그 레벨 지원
- 예외 로깅 지원

### 2. LoggerImpl
```java
public final class LoggerImpl implements Logger {
    private final java.util.logging.Logger logger;
    
    public static Logger getInstance(Class<?> clazz);
    public void info(String message);
    public void info(String message, Throwable thrown);
    public void error(String message);
    public void error(String message, Throwable thrown);
}
```
- Logger 인터페이스 구현
- 로그 메시지 포맷팅
- 스레드 안전한 구현

### 3. LoggerFactory
```java
public final class LoggerFactory {
    public static Logger getLogger(Class<?> clazz);
}
```
- 로거 인스턴스 관리
- 싱글톤 패턴 적용
- 설정 기반 로거 생성

### 4. SimpleLogger
```java
public final class SimpleLogger implements Logger {
    public static Logger getInstance(Class<?> clazz);
    public void info(String message);
    public void info(String message, Throwable thrown);
    public void error(String message);
    public void error(String message, Throwable thrown);
}
```
- 간단한 로거 구현
- 기본적인 로깅 기능 제공
- 최소한의 의존성

## Java Agent 호환성
- 클래스로더 분리 환경 고려
- 순수 Java 기능만 사용
- 외부 의존성 제거
- 독립적인 설정 관리

## 성능 고려사항
- 메모리 사용 최소화
- 스레드 안전성 보장
- 중복 로그 제어

## 사용 예시
```java
// 기본 사용
Logger logger = LoggerFactory.getLogger(MyClass.class);
logger.info("Application started");

// 예외 로깅
try {
    // ...
} catch (Exception e) {
    logger.error("Error occurred", e);
}

// SimpleLogger 사용
Logger simpleLogger = SimpleLogger.getInstance(MyClass.class);
simpleLogger.info("Simple logging");
```

## 마이그레이션 가이드
기존 spice.agent의 Logger에서 마이그레이션 시 고려사항:
1. 설정 프로퍼티 변경
   - `spice.log.level` → `csh.logging.level`
2. 로거 생성 방식 변경
   - `Logger.info()` → `LoggerFactory.getLogger().info()`
3. 중복 로그 제어 방식 유지
   - `Logger.info(id, message, seconds)` 형식 지원
4. 파일 출력 관련 설정은 output 모듈로 이동
   - `spice.log.dir` → `csh.output.file.path`
   - 로그 로테이션 설정 → output 모듈

## 향후 개선 사항
- 비동기 로깅 지원
- 로그 필터링
- 로그 포맷 커스터마이징
- 중복 로그 제어 기능 추가 