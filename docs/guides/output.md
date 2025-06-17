# Output 모듈 사용 가이드

## 개요
Output 모듈은 Java Agent 호환성을 고려한 간단하고 가벼운 파일 출력 기능을 제공합니다.

## 주요 기능
- 기본 파일 출력 작업
- 파일 로테이션 지원
- 자동 디렉토리 생성
- 스레드 안전 구현
- 외부 의존성 없음

## 의존성 추가
```xml
<dependency>
    <groupId>io.csh.utils</groupId>
    <artifactId>output</artifactId>
    <version>${csh.utils.version}</version>
</dependency>
```

## 설정

### 시스템 프로퍼티
```properties
# 출력 디렉토리 경로 (기본값: logs)
csh.output.file.path=logs

# 기존 파일 덮어쓰기 (기본값: false)
csh.output.overwrite=false

# 파일 로테이션 사용 (기본값: true)
csh.output.rotation.enabled=true

# 파일 보관 기간 (기본값: 30일)
csh.output.keep.days=30
```

## 사용 방법

### 기본 사용법
```java
import io.csh.utils.output.OutputWriter;
import io.csh.utils.output.OutputWriterFactory;

// 기본 설정으로 작성기 생성
OutputWriter writer = OutputWriterFactory.getWriter("application");

// 메시지 쓰기
writer.writeLine("테스트 메시지입니다");
writer.writeLine("다른 테스트 메시지입니다");

// 버퍼 비우기
writer.flush();

// 사용 완료 후 닫기
writer.close();
```

### 커스텀 설정
```java
import io.csh.utils.output.config.OutputConfig;

OutputConfig config = new OutputConfig();
config.setOutputPath("custom-logs");
config.setRotationEnabled(true);
config.setKeepDays(7);

OutputWriter writer = OutputWriterFactory.getWriter("custom", config);
```

### 로깅 모듈과의 연동
Output 모듈은 로깅 모듈에서 자동으로 사용됩니다. 로깅 모듈을 사용하면 모든 로그 메시지가 output 모듈을 통해 파일에 기록됩니다.

```java
import io.csh.utils.logging.Log;

// 이 메시지들은 output 파일에 기록됩니다
Log.info("정보 메시지입니다");
Log.error("에러 메시지입니다");
```

## 파일 로테이션
파일 로테이션이 활성화된 경우:
- 파일은 날짜 기반으로 이름이 지정됩니다 (예: application-2024-03-20.log)
- 자정에 자동으로 새 파일이 생성됩니다
- 오래된 파일은 설정된 일수 동안 보관됩니다

## 스레드 안전성
Output 모듈은 스레드 안전하며, 추가적인 동기화 없이도 다중 스레드 환경에서 사용할 수 있습니다.

## Java Agent 호환성
이 모듈은 Java Agent 환경에서 작동하도록 설계되었습니다:
- 외부 의존성 없음
- 순수 Java 기능만 사용
- 가벼운 구현
- 클래스로더 격리 지원

## 관련 문서
- [설계 문서](../design/output.md)
- [모듈 README](../../output/README.md)
- [릴리즈 노트](../releases/output-1.0.0.md)

## 문제 해결
- 파일 권한 문제: 출력 디렉토리에 쓰기 권한이 있는지 확인
- 메모리 사용량: 버퍼 크기와 로테이션 설정 조정
- 성능 이슈: 비동기 쓰기 고려

## 예제 프로젝트
전체 예제는 [예제 프로젝트](https://github.com/csh-utils/output-example)를 참조하세요.

# Output 모듈 사용자 가이드

## 목차
1. [시작하기](#시작하기)
2. [기본 사용법](#기본-사용법)
3. [설정](#설정)
4. [고급 기능](#고급-기능)
5. [모범 사례](#모범-사례)
6. [문제 해결](#문제-해결)
7. [API 레퍼런스](#api-레퍼런스)

## 시작하기

### 필수 요구사항
- Java 8 이상
- Maven 3.6 이상

### 설치
프로젝트의 `pom.xml`에 다음 의존성을 추가하세요:

```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>output</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 기본 사용법

### 초기화
```java
// 출력 캡처 시작
OutputCapture capture = OutputCapture.getInstance();
capture.start();

// 애플리케이션 코드
System.out.println("이 내용은 캡처됩니다");
System.err.println("에러 메시지도 캡처됩니다");

// 종료 시
capture.stop();
```

## 설정

시스템 프로퍼티를 통해 다음과 같은 설정이 가능합니다:

```properties
# 출력 디렉토리 (기본값: output)
csh.utils.output.dir=logs

# 파일 이름 (기본값: output.log)
csh.utils.output.filename=app.log

# 최대 파일 크기 (기본값: 10MB)
csh.utils.output.maxFileSize=10MB

# 최대 파일 개수 (기본값: 10)
csh.utils.output.maxFiles=10

# 보관 기간(일) (기본값: 30)
csh.utils.output.retentionDays=30

# 압축 사용 여부 (기본값: true)
csh.utils.output.compression=true

# 압축 형식 (기본값: gz)
csh.utils.output.compressionFormat=gz

# 파일 추가 모드 (기본값: false)
csh.utils.output.append=false
```

## 고급 기능

### 파일 로테이션
다음 경우에 파일이 자동으로 로테이션됩니다:
- 현재 파일 크기가 최대 크기를 초과할 때
- 새로운 날짜가 시작될 때
- 최대 파일 개수에 도달했을 때

### 압축
출력 파일은 자동으로 압축될 수 있습니다:
- 기본적으로 활성화됨
- gz 형식 지원
- 파일 로테이션 후 압축 수행

### 파일 보관
- 보관 기간이 지난 파일은 자동으로 삭제됨
- 기본 보관 기간은 30일
- csh.utils.output.retentionDays로 설정 가능

## 모범 사례

### 성능
1. 비동기 쓰기 사용
2. 적절한 파일 크기 설정
3. 합리적인 보관 기간 설정

### 보안
1. 적절한 파일 권한 설정
2. 안전한 출력 디렉토리 구성
3. 민감한 정보 적절히 처리

### 유지보수
1. 디스크 공간 사용량 모니터링
2. 정기적인 파일 로테이션 확인
3. 압축 설정 검증

### 오류 처리
1. finally 블록에서 stop() 호출
2. 초기화 오류 처리
3. 파일 시스템 오류 모니터링

## 문제 해결

### 일반적인 문제
1. 출력이 파일에 기록되지 않는 경우
   - 출력 캡처가 시작되었는지 확인
   - 파일 시스템 권한 확인
   - 디스크 공간 확인

2. 파일 로테이션이 작동하지 않는 경우
   - 최대 파일 크기 설정 확인
   - 파일 시스템 권한 확인
   - 디스크 공간 확인

3. 압축이 작동하지 않는 경우
   - 압축 설정 확인
   - 파일 시스템 권한 확인
   - 디스크 공간 확인

### 해결 방법
1. 파일 권한 확인
2. 디스크 공간 모니터링
3. 설정 값 검증

## API 레퍼런스

### OutputCapture
출력 캡처 기능의 메인 클래스입니다.

```java
public class OutputCapture {
    // 싱글톤 인스턴스 가져오기
    public static OutputCapture getInstance();

    // 출력 캡처 시작
    public void start();

    // 출력 캡처 중지
    public void stop();

    // 출력 캡처 상태 확인
    public boolean isActive();

    // 현재 상태 정보 가져오기
    public String getStatus();
}
```

### OutputConfig
설정 관리 클래스입니다.

```java
public class OutputConfig {
    // 출력 디렉토리 가져오기
    public String getOutputDir();

    // 파일 이름 가져오기
    public String getFileName();

    // 최대 파일 크기 가져오기
    public long getMaxFileSize();

    // 최대 파일 수 가져오기
    public int getMaxFiles();

    // 보관 기간 가져오기
    public int getRetentionDays();

    // 압축 사용 여부 가져오기
    public boolean isCompressionEnabled();

    // 압축 형식 가져오기
    public String getCompressionFormat();

    // 추가 모드 여부 가져오기
    public boolean isAppend();
} 