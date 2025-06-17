# Output Capture 모듈 1.0.2 릴리즈 노트

## 주요 변경사항

### 기능 추가
- System.out/err 출력 자동 캡처 기능
- 비동기 파일 쓰기 지원
- 파일 크기 기반 로테이션
- 날짜 기반 파일 구분
- 설정 가능한 파일 보관 개수

### 성능 최적화
- 비동기 큐를 사용한 출력 처리
- 배치 처리로 I/O 최적화
- 메모리 사용량 최적화
- 동시성 처리 개선

### 설정 기능
- VM 옵션으로 설정 가능
- application.properties로 설정 가능
- 기본값 제공
- 런타임 설정 변경 지원

## 호환성
- Java 8 이상 지원
- Maven 3.6 이상 지원

## 의존성
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>output-capture</artifactId>
    <version>1.0.2</version>
</dependency>
```

## 설정 옵션
```properties
# 출력 파일 저장 디렉토리 (기본값: output)
output.capture.dir=output

# 출력 파일 이름 (기본값: output)
output.capture.file=output

# 최대 파일 크기 (기본값: 10MB)
output.capture.max-size=10485760

# 최대 파일 개수 (기본값: 10)
output.capture.max-files=10

# 기존 파일에 추가할지 여부 (기본값: true)
output.capture.append=true
```

## 사용 예시
```java
// 애플리케이션 시작 시 초기화
OutputCapture.getInstance();

// 이후 System.out.println() 또는 System.err.println()으로 출력되는 모든 내용이 자동으로 파일에 저장됩니다
System.out.println("이 내용은 자동으로 파일에 저장됩니다.");
System.err.println("에러 메시지도 자동으로 파일에 저장됩니다.");

// 애플리케이션 종료 시
OutputCapture.getInstance().shutdown();
```

## 알려진 이슈
- 대용량 출력 시 메모리 사용량 증가 가능성
- 동시성 환경에서 파일 접근 충돌 가능성
- 일부 OS에서 파일 권한 문제 발생 가능

## 향후 계획
- 압축 기능 추가
- 암호화 지원
- 원격 저장소 연동
- 모니터링 기능 강화
- 성능 메트릭 수집 