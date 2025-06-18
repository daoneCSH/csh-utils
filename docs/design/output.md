# Output 모듈 설계

## 개요
Output 모듈은 Java Agent 호환성을 고려한 간단하고 가벼운 파일 출력 기능을 제공합니다. 최소한의 의존성으로 파일 쓰기, 로테이션, 관리를 처리합니다.

## 설계 목표
- Java Agent 환경 호환성
- 최소한의 의존성
- 가벼운 구현
- 쉬운 설정
- 스레드 안전성
- 파일 관리 기능

## 아키텍처

### 1. 패키지 구조
```
io.csh.utils.output
├── OutputWriter.java           # 출력 작성기 인터페이스
├── FileOutputWriter.java       # 파일 출력 작성기
├── OutputWriterFactory.java    # 출력 작성기 팩토리
└── OutputCapture.java          # 출력 캡처
```

### 2. 클래스 다이어그램
```
OutputWriter (인터페이스)
    ↑
FileOutputWriter
    ↑
OutputWriterFactory
```

## 주요 클래스 설명

### 1. OutputWriter 인터페이스
```java
public interface OutputWriter {
    void writeLine(String line);
    void flush();
}
```
- 기본 파일 출력 작업 정의
- 파일 쓰기에 대한 깔끔한 추상화 제공
- 라인 단위 쓰기 지원

### 2. FileOutputWriter
```java
public class FileOutputWriter implements OutputWriter {
    public static FileOutputWriter getInstance(String name);
    public void writeLine(String line);
    public void flush();
}
```
- OutputWriter 인터페이스 구현
- 파일 생성 및 관리 처리
- 파일 로테이션 지원
- 파일 리소스 관리

### 3. OutputWriterFactory
```java
public final class OutputWriterFactory {
    public static OutputWriter getWriter(String name);
}
```
- OutputWriter 인스턴스 관리
- 스레드 안전한 접근 제공
- 리소스 정리 처리

### 4. OutputCapture
```java
public final class OutputCapture {
    public static OutputCapture getInstance();
    public void start();
    public void stop();
    public String getOutput();
    public String getError();
    public void clear();
}
```
- 표준 출력과 에러 출력 캡처
- 출력 스트림 관리
- 캡처된 출력 조회

## 스레드 안전성
- ConcurrentHashMap으로 작성기 관리
- 파일 접근을 위한 원자적 연산
- 동기화된 파일 쓰기
- 스레드 안전한 설정

## 파일 로테이션 전략
1. **일별 로테이션**
   - 날짜 기반 파일명 (YYYY-MM-DD)
   - 자정에 새 파일 생성
   - 설정된 일수 동안 보관

2. **정리**
   - 오래된 파일 자동 제거
   - 설정된 보관 기간 기반
   - 로테이션 중 처리

## 오류 처리
- 우아한 실패 처리
- System.err로 오류 로깅
- 오류 발생 시 리소스 정리
- 스레드 안전한 오류 처리

## 성능 고려사항
- 버퍼링된 쓰기
- 효율적인 파일 로테이션
- 최소한의 메모리 사용
- 리소스 정리

## Java Agent 호환성
- 외부 의존성 없음
- 순수 Java 구현
- 클래스로더 격리 지원
- 최소한의 리소스 사용

## 사용 예시
```java
// 기본 사용
OutputWriter writer = OutputWriterFactory.getWriter("application");
writer.writeLine("Log message");

// 출력 캡처
OutputCapture capture = OutputCapture.getInstance();
capture.start();
System.out.println("Captured output");
String output = capture.getOutput();
capture.stop();
```

## 향후 개선 사항
- 비동기 쓰기 지원
- 압축 지원
- 암호화 지원
- 다양한 출력 형식
- 파일 로테이션 기능 개선 