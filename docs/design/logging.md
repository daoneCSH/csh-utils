# 로깅 모듈 설계

## 개요
Java Agent 호환성을 고려한 고급 로깅 모듈입니다. 검증된 기존 구현을 기반으로 현대적인 개선을 적용하고, 파일 로깅, 중복 로그 방지, 로그 회전 등의 고급 기능을 추가했습니다.

## 설계 목표
- Java Agent 환경에서의 안정적인 동작
- 최소한의 의존성
- 가벼운 구현
- 쉬운 설정과 사용
- 현대적인 Java 기능 활용
- 로깅과 출력 분리
- 표준 로깅 레벨 지원
- 파일 로깅 및 로그 회전
- 중복 로그 방지
- 성능 최적화

## 로깅 레벨
표준 로깅 레벨을 지원합니다 (우선순위 순, Spiceware Logger와 호환):

1. **TRACE (4)** - 가장 상세한 디버깅 정보
   - 메서드 진입/종료
   - 변수 값 추적
   - 상세한 실행 흐름

2. **DEBUG (3)** - 개발 시 디버깅 정보
   - 조건문 분기 정보
   - 중간 결과값
   - 성능 측정 지점

3. **INFO (2)** - 일반적인 정보성 메시지
   - 애플리케이션 시작/종료
   - 주요 비즈니스 로직 실행
   - 설정 정보

4. **WARN (1)** - 경고 메시지 (잠재적 문제)
   - 성능 저하 가능성
   - 권장되지 않는 사용법
   - 임시 해결책 적용

5. **ERROR (0)** - 오류 및 예외 상황
   - 예외 발생
   - 시스템 오류
   - 복구 불가능한 상황

## 기존 구현의 장점
- 단순하고 안정적인 구현
- Java Agent 환경에서 검증된 동작
- 중복 로그 제어 기능
- 파일 로깅 및 회전 기능

## 개선 사항
1. **인터페이스 기반 설계**
   - 확장성 향상
   - 테스트 용이성
   - 구현체 교체 가능

2. **설정 관리 개선**
   - 시스템 프로퍼티 기반 설정
   - 기본값 제공
   - 설정 검증 강화
   - 로그 레벨 제어
   - 파일 로깅 설정

3. **성능 최적화**
   - 스레드 안전성 강화
   - 메모리 사용 최적화
   - 로그 레벨별 출력 제어
   - StringBuilder 사용
   - 비동기 파일 정리

4. **코드 품질**
   - JavaDoc 문서화
   - 단위 테스트
   - 코드 가독성 향상

5. **관심사 분리**
   - 로깅과 출력 분리
   - 출력은 output 모듈에서 처리
   - 모듈 간 결합도 최소화

6. **고급 기능 추가**
   - 파일 로깅 지원
   - 로그 회전 및 보관
   - 중복 로그 방지
   - 로그 포맷터 분리

## 아키텍처

### 1. 패키지 구조
```
io.csh.utils.logging
├── Logger.java              # 로거 인터페이스
├── LoggerImpl.java          # 로거 구현체
├── LoggerFactory.java       # 로거 팩토리
├── LogLevel.java            # 로그 레벨 열거형
├── LogConfig.java           # 로그 설정 관리
├── LogFileManager.java      # 파일 로깅 관리
├── DuplicateLogFilter.java  # 중복 로그 방지
├── LogFormatter.java        # 로그 포맷터
└── Logging.java             # 간편한 로깅 클래스
```

### 2. 클래스 다이어그램
```
Logger (Interface)
    ↑
LoggerImpl
    ↑
LoggerFactory
    ↑
Logging (Static)

LogConfig (Singleton)
    ↑
LogFileManager (Singleton)
    ↑
DuplicateLogFilter (Static)

LogFormatter (Static)
LogLevel (Enum)
    ├── TRACE(4)
    ├── DEBUG(3)
    ├── INFO(2)
    ├── WARN(1)
    └── ERROR(0)
```

## 주요 클래스 설명

### 1. Logger 인터페이스
```java
public interface Logger {
    void trace(String message);
    void trace(String message, Throwable thrown);
    void debug(String message);
    void debug(String message, Throwable thrown);
    void info(String message);
    void info(String message, Throwable thrown);
    void warn(String message);
    void warn(String message, Throwable thrown);
    void error(String message);
    void error(String message, Throwable thrown);
    
    boolean isTraceEnabled();
    boolean isDebugEnabled();
    boolean isInfoEnabled();
    boolean isWarnEnabled();
    boolean isErrorEnabled();
}
```
- 로깅 기능 정의
- 표준 로그 레벨 지원
- 예외 로깅 지원
- 레벨별 활성화 확인

### 2. LogLevel 열거형
```java
public enum LogLevel {
    TRACE(4), DEBUG(3), INFO(2), WARN(1), ERROR(0);
    
    private final int level;
    
    public boolean isEnabled(LogLevel currentLevel);
    public static LogLevel fromString(String level);
}
```
- 로그 레벨 정의 (Spiceware Logger와 호환)
- 레벨별 우선순위 관리
- 문자열 변환 지원

### 3. LoggerImpl
```java
public final class LoggerImpl implements Logger {
    private final String name;
    private final LogConfig config;
    private final LogFileManager fileManager;
    
    public static Logger getInstance(Class<?> clazz);
    public void logWithDuplicateFilter(LogLevel level, String id, String message, int minIntervalSeconds);
    // ... 모든 로그 레벨 메서드들
}
```
- Logger 인터페이스 구현
- 로그 메시지 포맷팅
- 스레드 안전한 구현
- 레벨별 출력 제어
- 파일 로깅 지원
- 중복 로그 방지

### 4. LoggerFactory
```java
public final class LoggerFactory {
    public static Logger getLogger(Class<?> clazz);
    public static void setLogLevel(LogLevel level);
    public static LogLevel getLogLevel();
    public static void clearDuplicateFilter();
    public static void shutdown();
}
```
- 로거 인스턴스 관리
- 싱글톤 패턴 적용
- 설정 기반 로거 생성
- 전역 로그 레벨 제어
- 리소스 정리

### 5. LogConfig
```java
public final class LogConfig {
    private String logDir = "logs";
    private boolean logRotationEnabled = true;
    private int logKeepDays = 30;
    private String logPrefix = "csh";
    private boolean consoleOutput = true;
    private boolean fileOutput = false;
    
    public static LogConfig getInstance();
    // ... getter/setter 메서드들
}
```
- 로그 설정 관리
- 시스템 프로퍼티 기반 설정
- 기본값 제공
- 설정 검증

### 6. LogFileManager
```java
public final class LogFileManager {
    public static LogFileManager getInstance();
    public void initialize();
    public void writeToFile(String message);
    public void shutdown();
}
```
- 파일 로깅 관리
- 로그 파일 생성 및 관리
- 로그 회전 처리
- 오래된 로그 파일 정리
- 비동기 정리 작업

### 7. DuplicateLogFilter
```java
public final class DuplicateLogFilter {
    public static boolean shouldLog(String id, int minIntervalSeconds);
    public static void removeLogRecord(String id);
    public static void clear();
}
```
- 중복 로그 방지
- ID 기반 중복 체크
- 시간 간격 설정
- 메모리 효율적 관리

### 8. LogFormatter
```java
public final class LogFormatter {
    public static String format(LogLevel level, String loggerName, String message);
    public static String formatWithException(LogLevel level, String loggerName, String message, Throwable thrown);
    public static String formatSimple(LogLevel level, String id, String message);
    public static String formatStackTrace(Throwable thrown);
}
```
- 로그 메시지 포맷팅
- 다양한 포맷 지원
- 예외 스택 트레이스 포맷팅
- 성능 최적화

### 9. Logging (간편한 로깅)
```java
public final class Logging {
    public static void trace(String message);
    public static void debug(String message);
    public static void info(String message);
    public static void warn(String message);
    public static void error(String message);
    // ... 예외 포함 메서드들
    
    public static void setLogLevel(LogLevel level);
    public static LogLevel getLogLevel();
    public static boolean isTraceEnabled();
    // ... 레벨별 활성화 확인 메서드들
}
```
- 간편한 로깅 인터페이스
- 클래스명 자동 감지
- 스택 트레이스 기반 호출 클래스 식별
- 정적 메서드 제공

## 설정

### 로그 레벨 설정
```properties
# application.properties
csh.logging.level=INFO

# 시스템 프로퍼티
-Dcsh.logging.level=DEBUG

# 환경 변수
CSH_LOGGING_LEVEL=WARN
```

### 파일 로깅 설정
```properties
# 파일 로깅 활성화
-Dcsh.logging.file=true
-Dcsh.logging.dir=/path/to/logs
-Dcsh.logging.rotation.enabled=true
-Dcsh.logging.keep.days=30
-Dcsh.logging.prefix=myapp
-Dcsh.logging.console=true
```

### 설정 우선순위
1. **런타임 설정**: `Logging.setLogLevel(LogLevel.DEBUG)`
2. **시스템 프로퍼티**: `-Dcsh.logging.level=INFO`
3. **환경 변수**: `CSH_LOGGING_LEVEL=WARN`
4. **기본값**: `INFO`

## Java Agent 호환성
- 클래스로더 분리 환경 고려
- 순수 Java 기능만 사용
- 외부 의존성 제거
- 독립적인 설정 관리
- 가벼운 구현

## 성능 고려사항
- 메모리 사용 최소화
- 스레드 안전성 보장
- 중복 로그 제어
- 레벨별 출력 최적화
- StringBuilder 사용
- 비동기 파일 정리
- 파일 I/O 최적화

## 사용 예시

### 기본 사용
```java
// 간편한 로깅 (권장)
Logging.trace("Entering method");
Logging.debug("Processing data: {}", data);
Logging.info("Application started");
Logging.warn("Performance degradation detected");
Logging.error("Error occurred", e);

// 기존 방식
Logger logger = LoggerFactory.getLogger(MyClass.class);
logger.trace("Entering method");
logger.debug("Processing data: {}", data);
logger.info("Application started");
logger.warn("Performance degradation detected");
logger.error("Error occurred", e);
```

### 고급 기능 사용
```java
// 중복 로그 방지
LoggerImpl loggerImpl = (LoggerImpl) LoggerFactory.getLogger(MyClass.class);
loggerImpl.logWithDuplicateFilter(LogLevel.INFO, "connection-retry", "Connection failed", 5);

// 레벨 확인
if (Logging.isDebugEnabled()) {
    Logging.debug("Expensive debug operation");
}
```

## 마이그레이션 가이드

### 기존 spice.agent의 Logger에서 마이그레이션 시 고려사항:

1. **설정 프로퍼티 변경**
   - `spice.log.level` → `csh.logging.level`

2. **로거 생성 방식 변경**
   - `Logger.info()` → `Logging.info()` (간편한 방식)
   - `Logger.info()` → `LoggerFactory.getLogger().info()` (기존 방식)

3. **중복 로그 제어 방식**
   - `Logger.info(id, message, seconds)` → `LoggerImpl.logWithDuplicateFilter(level, id, message, seconds)`

4. **파일 로깅 설정**
   - `spice.log.dir` → `csh.logging.dir`
   - 로그 회전 설정: `csh.logging.rotation.enabled`
   - 보관 기간: `csh.logging.keep.days`

5. **로그 레벨 호환성**
   - Spiceware Logger와 동일한 내림차순 레벨 (TRACE=4, ERROR=0)

### 마이그레이션 예시

**Spiceware Logger:**
```java
Logger.info("id", "Message");
Logger.info("id", "Message", 5); // 중복 방지
```

**CSH Utils Logger:**
```java
// 간편한 방식
Logging.info("Message");

// 중복 방지
LoggerImpl loggerImpl = (LoggerImpl) LoggerFactory.getLogger(MyClass.class);
loggerImpl.logWithDuplicateFilter(LogLevel.INFO, "id", "Message", 5);
```

## 향후 개선 사항
- 비동기 로깅 지원
- 로그 필터링
- 로그 포맷 커스터마이징
- 로그 집계 및 모니터링
- 로그 압축 기능
- 로그 암호화 기능 