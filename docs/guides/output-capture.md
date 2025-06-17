# 출력 캡처 모듈 사용 가이드

## 목차
1. [시작하기](#시작하기)
2. [기본 사용법](#기본-사용법)
3. [설정](#설정)
4. [고급 기능](#고급-기능)
5. [모범 사례](#모범-사례)

## 시작하기

### 필수 요구사항
- Java 8 이상
- Maven 3.6 이상

### 설치
프로젝트의 `pom.xml`에 다음 의존성을 추가하세요:

```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>output-capture</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 기본 사용법

### 초기화
```java
// 출력 캡처 초기화
OutputCapture.getInstance().initialize();

// 애플리케이션 코드
System.out.println("이 내용은 캡처됩니다");
System.err.println("에러 메시지도 캡처됩니다");

// 종료 시
OutputCapture.getInstance().shutdown();
```

### 설정
설정은 다음 방법으로 할 수 있습니다:
1. VM 옵션
2. 환경 변수
3. application.properties
4. 기본값

application.properties 설정 예시:
```properties
# 출력 디렉토리
csh.output.dir=logs

# 출력 파일 이름
csh.output.file=app.log

# 최대 파일 크기
csh.output.max-size=10MB

# 최대 파일 개수
csh.output.max-files=10

# 추가 모드
csh.output.append=true

# 압축 설정
csh.output.compression.enabled=true
csh.output.compression.format=gz

# 보관 기간
csh.output.retention.days=30
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
- csh.output.retention.days로 설정 가능

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
1. finally 블록에서 shutdown() 호출
2. 초기화 오류 처리
3. 파일 시스템 오류 모니터링

## 문제 해결

### 일반적인 문제
1. 파일 권한 오류
2. 디스크 공간 문제
3. 설정 문제

### 해결 방법
1. 파일 권한 확인
2. 디스크 공간 모니터링
3. 설정 값 검증

## API 레퍼런스

### OutputCapture
출력 캡처 기능의 메인 클래스입니다.

```java
public class OutputCapture {
    public static OutputCapture getInstance()
    public void initialize()
    public void shutdown()
    public String printConfig()
}
```

### OutputConfig
설정 관리 클래스입니다.

```java
public class OutputConfig {
    public static OutputConfig getInstance()
    public Path getOutputDirectory()
    public String getFileName()
    public long getMaxFileSize()
    public int getMaxFiles()
    public boolean isAppend()
    public boolean isCompressionEnabled()
    public String getCompressionFormat()
    public int getRetentionDays()
}
``` 