# 로깅 모듈 설계 문서

## 목차
1. [아키텍처 개요](#아키텍처-개요)
2. [핵심 컴포넌트](#핵심-컴포넌트)
3. [클래스 다이어그램](#클래스-다이어그램)
4. [주요 인터페이스](#주요-인터페이스)
5. [설계 결정사항](#설계-결정사항)

## 아키텍처 개요

로깅 모듈은 다음과 같은 주요 컴포넌트로 구성됩니다:

1. **Logger**: 로깅의 핵심 인터페이스
2. **LogMessage**: 로그 메시지 표현
3. **LogOutputManager**: 로그 출력 관리 (콘솔 출력)
4. **LogProcessor**: 어노테이션 처리기

## 핵심 컴포넌트

### 1. Logger
- 로깅의 기본 인터페이스
- 정적 메서드와 인스턴스 메서드 제공
- 로그 레벨 기반 필터링
- 콘솔 출력 지원

### 2. LogMessage
- 로그 메시지의 구조화된 표현
- 타임스탬프, 레벨, 메시지, 예외 정보 포함
- 포맷팅 및 출력 담당

### 3. LogOutputManager
- 로그 출력 관리
- 콘솔 출력 지원
- 출력 스트림 관리

### 4. LogProcessor
- `@CshLog` 어노테이션 처리
- 로거 자동 생성
- 컴파일 타임 코드 생성

## 클래스 다이어그램

```
+----------------+     +------------------+     +-------------------+
|     Logger     |     |   LogMessage     |     | LogOutputManager |
+----------------+     +------------------+     +-------------------+
| +trace()       |     | +timestamp       |     | +write()         |
| +debug()       |     | +level           |     | +flush()         |
| +info()        |     | +message         |     | +close()         |
| +warn()        |     | +throwable       |     +-------------------+
| +error()       |     | +format()        |
| +fatal()       |     +------------------+
+----------------+

+----------------+
| LogProcessor   |
+----------------+
| +process()     |
| +generate()    |
+----------------+
```

## 주요 인터페이스

### Logger
```java
public interface Logger {
    void trace(String message);
    void trace(String message, Throwable throwable);
    void debug(String message);
    void debug(String message, Throwable throwable);
    void info(String message);
    void info(String message, Throwable throwable);
    void warn(String message);
    void warn(String message, Throwable throwable);
    void error(String message);
    void error(String message, Throwable throwable);
    void fatal(String message);
    void fatal(String message, Throwable throwable);
}
```

### LogMessage
```java
public class LogMessage {
    private final LocalDateTime timestamp;
    private final LogLevel level;
    private final String message;
    private final Throwable throwable;
    
    public String format();
}
```

## 설계 결정사항

### 1. 어노테이션 기반 로거 생성
- 장점:
  - 보일러플레이트 코드 감소
  - 일관된 로깅 패턴 적용
  - 컴파일 타임 검증
- 단점:
  - 컴파일 시간 증가
  - 런타임 유연성 제한

### 2. 정적 메서드 지원
- 장점:
  - 간편한 사용
  - 전역 접근성
- 단점:
  - 테스트 어려움
  - 의존성 주입 제한

### 3. 로그 레벨 필터링
- 장점:
  - 성능 최적화
  - 로그 볼륨 제어
- 단점:
  - 설정 복잡성
  - 런타임 변경 제한

### 4. 콘솔 출력 관리
- 장점:
  - 중앙화된 로그 관리
  - 일관된 포맷팅
- 단점:
  - 동시성 처리 필요
  - 리소스 관리 복잡성 