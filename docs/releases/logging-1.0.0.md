# Logging 모듈 1.0.0 릴리즈 노트

## 주요 기능

### 1. 기본 로깅 기능
- 로그 레벨 지원
  - TRACE: 가장 상세한 로그 레벨
  - DEBUG: 디버깅을 위한 상세 정보
  - INFO: 일반적인 정보
  - WARN: 경고
  - ERROR: 오류
- 타임스탬프, 스레드 정보 포함
- 예외 스택 트레이스 로깅
- 로그 메시지 포맷팅
  - 기본 포맷: [시간] [레벨] [스레드] [클래스] - 메시지
  - 커스텀 포맷 지원

### 2. 로그 파일 관리
- 자동 파일 생성
  - 앱네임 기반 파일명 생성
  - 날짜 기반 파일명 생성
  - 파일 번호 자동 증가
- 로그 파일 로테이션
  - 파일 크기 기반 로테이션
  - 날짜 기반 로테이션
  - 파일 번호 기반 로테이션
- 백업 파일 관리
  - 최대 백업 파일 수 설정
  - 백업 파일 압축
  - 오래된 파일 자동 삭제

### 3. 설정 관리
- 시스템 프로퍼티 기반 설정
```properties
# 로그 레벨 설정
-Dspice.log.level=trace

# 로그 파일 크기 설정 (10MB)
-Dspice.log.maxFileSize=10485760

# 백업 파일 수 설정
-Dspice.log.maxBackupCount=5

# 로그 파일 경로 설정
-Dspice.log.file.path=/path/to/logs

# 로그 파일명 설정
-Dspice.log.file.name=app.log

# 로그 파일 압축 설정
-Dspice.log.file.compression=true
```

- Properties 파일 기반 설정
```properties
# logging.properties
log.level=trace
log.maxFileSize=10485760
log.maxBackupCount=5
log.directory=logs
log.file.name=app.log
log.file.compression=true
```

- 로그 레벨 동적 변경
- 설정 변경 감지 및 리로드

### 4. 비동기 로깅
- AsyncLogger 지원
  - 버퍼 크기 설정
  - 플러시 간격 설정
  - 배치 처리
- 성능 최적화
  - 비동기 쓰기
  - 버퍼링
  - 배치 처리

### 5. 이벤트 기반 로깅
- LogEventHandler 인터페이스
  - 로그 이벤트 처리
  - 파일 이벤트 처리
  - 커스텀 이벤트 처리
- LogFileEvent
  - 파일 생성 이벤트
  - 파일 로테이션 이벤트
  - 파일 삭제 이벤트
- LogOutputManager
  - 콘솔 출력
  - 파일 출력
  - 커스텀 출력

## 사용 예시

### 기본 사용
```java
// 기본 로깅
Logger.info("애플리케이션 시작");
Logger.debug("디버그 메시지");
Logger.warn("경고 메시지");
Logger.error("오류 메시지");

// 예외와 함께 로깅
try {
    // ... 코드 ...
} catch (Exception e) {
    Logger.error("오류 발생", e);
}
```

### 비동기 로깅
```java
// 비동기 로거 설정
AsyncLogConfig config = new AsyncLogConfig.Builder()
    .bufferSize(1000)
    .flushInterval(1000)
    .build();

// 비동기 로거 사용
AsyncLogger logger = new AsyncLogger(config);
logger.info("비동기 로그 메시지");
```

### 이벤트 핸들러 사용
```java
// 이벤트 핸들러 등록
LogFileManager.getInstance().addEventHandler(new LogEventHandler() {
    @Override
    public void onLogFileCreated(LogFileEvent event) {
        System.out.println("새 로그 파일 생성: " + event.getFilePath());
    }

    @Override
    public void onLogFileRotated(LogFileEvent event) {
        System.out.println("로그 파일 로테이션: " + event.getFilePath());
    }
});
```

## 변경사항

### 추가된 기능
- 이벤트 기반 로깅 시스템
- 로그 메시지 포맷팅 시스템
- 로그 출력 관리 시스템
- 설정 변경 감지 및 리로드

### 개선된 기능
- 로그 파일 관리 개선
  - 앱네임 기반 파일명
  - 파일 번호 자동 증가
  - 압축 기능 강화
- 로그 포맷 개선
- 성능 최적화
- 메모리 사용량 최적화

### 수정된 버그
- 파일 핸들 누수 수정
- 동시성 이슈 수정
- 로그 파일 권한 문제 수정
- 설정 리로드 시 메모리 누수 수정

## 마이그레이션 가이드

### 0.9.0에서 1.0.0으로 업그레이드
1. 새로운 의존성 추가
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils-logging</artifactId>
    <version>1.0.0</version>
</dependency>
```

2. 코드 변경
- Logger 사용 방식 변경
- 설정 파일 업데이트
- 비동기 로깅 도입
- 이벤트 핸들러 추가

## 알려진 이슈
- 대용량 로그 파일 처리 시 메모리 사용량 증가
- 일부 OS에서 파일 권한 문제 발생 가능
- 설정 변경 감지 시 일부 이벤트 누락 가능성

## 향후 계획
- 로그 집계 시스템
- 로그 시각화 도구
- 로그 알림 시스템
- 로그 분석 API
- 로그 포맷 커스터마이징 강화
- 로그 이벤트 시스템 확장 