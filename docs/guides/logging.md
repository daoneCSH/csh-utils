# 로깅 설정 가이드

## 설정 우선순위

로깅 설정은 다음 순서로 적용됩니다 (위에서 아래로 우선순위가 낮아짐):

1. VM/시스템 프로퍼티 (`-D` 옵션)
2. application 프로퍼티 (`application.properties` 또는 `application.yml`)
3. logging 프로퍼티 (`logging.properties`)
4. 기본값

예를 들어, 로그 레벨을 설정하는 경우:
```bash
# 1. VM 옵션 (최우선)
-Dcsh.logging.level=DEBUG

# 2. application.properties
csh.logging.level=INFO

# 3. logging.properties
csh.logging.level=WARN

# 4. 기본값
# 기본값: INFO
```

## 설정 방법

### JVM 옵션으로 설정
```bash
-Dcsh.logging.level=INFO
-Dcsh.logging.file.path=/path/to/logs
-Dcsh.logging.file.name=application.log
-Dcsh.logging.file.max-size=20MB
-Dcsh.logging.file.max-total-size=1GB
-Dcsh.logging.file.retention-days=30
-Dcsh.logging.overwrite=true
```

### application.properties로 설정
```properties
csh.logging.level=INFO
csh.logging.file.path=/path/to/logs
csh.logging.file.name=application.log
csh.logging.file.max-size=20MB
csh.logging.file.max-total-size=1GB
csh.logging.file.retention-days=30
csh.logging.overwrite=true
```

### logging.properties로 설정
```properties
# 로그 레벨 설정 (TRACE, DEBUG, INFO, WARN, ERROR)
csh.logging.level=INFO

# 로그 파일 설정
csh.logging.file.path=logs
csh.logging.file.name=application.log
csh.logging.file.max-size=10MB
csh.logging.file.max-total-size=1GB
csh.logging.file.retention-days=365

# 로그 파일 덮어쓰기 설정 (true: 기존 파일 삭제 후 새로 생성, false: 기존 파일 유지)
csh.logging.overwrite=false

# 로그 파일 압축 설정
csh.logging.file.compression.unit=WEEK
csh.logging.file.compression.value=1
csh.logging.file.compression.format=gz

# 로그 패턴 설정
csh.logging.pattern=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n

# 콘솔 출력 설정
csh.logging.console.enabled=true
```

## 기본값
- 로그 레벨: `INFO`
- 로그 파일 경로: `logs` 디렉토리
- 로그 파일 이름: `application.log`
- 최대 파일 크기: `10MB`
- 최대 전체 크기: `1GB`
- 보관 기간: `365`일
- 압축 단위: `WEEK`
- 압축 값: `1`
- 압축 형식: `gz`
- 로그 패턴: `%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n`
- 콘솔 출력: `true`
- 파일 덮어쓰기: `false`

## 로그 파일 덮어쓰기 옵션

`csh.logging.overwrite` 설정은 로그 파일의 생성 방식을 제어합니다:

1. `csh.logging.overwrite=true`로 설정하면:
   - 애플리케이션 시작 시 기존 로그 파일을 삭제하고 새로 생성
   - 로그 파일 로테이션 시에도 기존 파일을 덮어씀
   - 이전 로그 내용이 모두 삭제됨

2. `csh.logging.overwrite=false`로 설정하면 (기본값):
   - 기존 로그 파일이 없을 때만 새로 생성
   - 로그 파일 로테이션 시 기존 파일을 보존
   - 이전 로그 내용이 유지됨

## 로깅 설정 상태 확인
현재 적용된 로깅 설정을 확인하려면 `LoggingConfig` 클래스의 `printLoggingStatus()` 메서드를 사용할 수 있습니다:

```java
import io.csh.utils.logging.config.LoggingConfig;

public class LoggingExample {
    public static void main(String[] args) {
        // 로깅 설정 상태 출력
        LoggingConfig config = LoggingConfig.getInstance();
        System.out.println(config.printLoggingStatus());
    }
}
```

출력 예시:
```
=== Logging Configuration Status ===
Log Level: INFO
Console Enabled: true
Log File Path: logs
Log File Name: application.log
Log File Max Size: 10MB
Log File Max Total Size: 1GB
Log File Retention Days: 365
Log File Compression: WEEK 1 (gz)
Log Pattern: %d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
Overwrite: false
===================================
```

이를 통해 현재 적용된 로깅 설정과 우선순위에 따라 어떤 값이 선택되었는지 확인할 수 있습니다. 