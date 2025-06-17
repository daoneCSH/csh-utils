# Logging 모듈 1.0.2 릴리즈 노트

## 주요 변경사항

### 1. 설정 키 통일
- 모든 설정 키를 `csh.logging.` 접두사로 통일
- `log.overwrite`를 `csh.logging.overwrite`로 변경
- 설정 키 불일치 시 명확한 에러 메시지 출력

### 2. 로그 출력 개선
- 기본값 사용 시 불필요한 로그 출력 제거
- 잘못된 설정에 대한 에러 메시지 개선
- 로그 레벨 유효성 검사 강화

### 3. 로그 파일 관리 기능 개선
- LogFileManager 클래스 개선
  - 파일 로테이션 로직 개선
  - 파일 크기 기반 로테이션
  - 날짜 기반 로테이션
- 압축 기능 추가
  - GZIP 압축 지원
  - 압축 파일 자동 생성
  - 압축 파일 관리

### 4. 로그 포맷팅 시스템 개선
- LogFormat 클래스 추가
  - 커스텀 포맷 패턴 지원
  - 날짜/시간 포맷팅 개선
  - 스레드 정보 포맷팅
- 포맷 변수 지원
  - %d: 날짜/시간
  - %thread: 스레드 이름
  - %-5level: 로그 레벨
  - %logger: 로거 이름
  - %msg: 로그 메시지
  - %n: 줄바꿈

### 5. 설정 관리 개선
- LoggingConfig 클래스 개선
  - 시스템 프로퍼티 연동 강화
  - 설정 값 검증 기능 추가
  - 설정 변경 감지 기능
- 설정 프로퍼티 추가
  - 로그 파일 압축 설정
  - 로그 보관 기간 설정
  - 로그 파일 크기 제한 설정

## 사용 예시

### 비동기 로깅
```java
// 비동기 로거 생성
AsyncLogger logger = new AsyncLogger("MyApp");

// 로그 메시지 출력
logger.info("애플리케이션 시작");
logger.error("오류 발생", new RuntimeException("테스트"));

// 로거 종료
logger.shutdown();
```

### 로그 파일 관리
```java
// 로그 파일 관리자 생성
LogFileManager manager = new LogFileManager("MyApp");

// 로그 메시지 기록
manager.log("로그 메시지");

// 로그 파일 압축
manager.compressLogFiles();
```

### 커스텀 포맷
```java
// 커스텀 포맷 생성
LogFormat format = new LogFormat.Builder()
    .withTimestamp("yyyy-MM-dd HH:mm:ss.SSS")
    .withThreadName()
    .withLogLevel()
    .withLoggerName()
    .withMessage()
    .build();

// 포맷 적용
logger.setFormat(format);
```

## 설정 예시

### JVM 옵션
```bash
-Dcsh.logging.level=INFO
-Dcsh.logging.file.path=/path/to/logs
-Dcsh.logging.file.name=application.log
-Dcsh.logging.file.max-size=20MB
-Dcsh.logging.file.max-total-size=1GB
-Dcsh.logging.file.retention-days=30
-Dcsh.logging.overwrite=true
```

### application.properties
```properties
csh.logging.level=INFO
csh.logging.file.path=/path/to/logs
csh.logging.file.name=application.log
csh.logging.file.max-size=20MB
csh.logging.file.max-total-size=1GB
csh.logging.file.retention-days=30
csh.logging.overwrite=true
```

## 주의사항
- 기존 `log.overwrite` 설정을 사용하던 경우 `csh.logging.overwrite`로 변경 필요
- 잘못된 설정 키 사용 시 명확한 에러 메시지가 출력됨
- 기본값 사용 시 불필요한 로그가 출력되지 않음

## 마이그레이션 가이드

### 1.0.1에서 1.0.2로 업그레이드
1. 새로운 의존성 추가
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils-logging</artifactId>
    <version>1.0.2</version>
</dependency>
```

2. 코드 변경
- AsyncLogger 사용 방식 변경
- LogFileManager 사용 방식 변경
- LogFormat 사용 추가
- 설정 프로퍼티 업데이트

## 알려진 이슈
- 대용량 로그 파일 처리 시 메모리 사용량 증가
- 동시성 환경에서 로그 파일 접근 충돌 가능성
- 일부 OS에서 파일 권한 문제 발생 가능

## 향후 계획
- 로그 집계 시스템 도입
- 로그 시각화 도구 개발
- 로그 알림 시스템 구현
- 로그 분석 API 개발
- 로그 포맷 커스터마이징 강화
- 로그 이벤트 시스템 확장 