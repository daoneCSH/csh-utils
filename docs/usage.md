# CSH Utils 라이브러리 사용 가이드

## 개요
CSH Utils는 다양한 유틸리티 기능을 제공하는 라이브러리입니다. 이 문서는 다른 프로젝트에서 CSH Utils를 사용하는 방법과 예시를 제공합니다.

## 설치 방법
Maven을 사용하여 프로젝트에 CSH Utils를 추가하려면 `pom.xml` 파일에 다음 의존성을 추가하세요:

```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 모듈별 사용 가이드

### 1. Banner 모듈
애플리케이션 시작 시 표시되는 배너를 관리하는 모듈입니다.

#### 기본 사용
```java
import io.csh.utils.banner.core.AppBannerUtil;
import io.csh.utils.banner.renderer.ConsoleBannerRenderer;

public class Example {
    public static void main(String[] args) {
        // 기본 설정으로 배너 표시
        AppBannerUtil banner = new AppBannerUtil();
        banner.addRenderer(new ConsoleBannerRenderer(banner.getConfig()));
        banner.display();
    }
}
```

#### 커스텀 설정
```java
import io.csh.utils.banner.core.AppBannerUtil;
import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.core.BannerTheme;
import io.csh.utils.banner.renderer.ConsoleBannerRenderer;

public class CustomBannerExample {
    public static void main(String[] args) {
        // 커스텀 설정 생성
        BannerConfig config = new BannerConfig.Builder()
            .showLogo(true)
            .showVersion(true)
            .showBuildInfo(true)
            .showSystemInfo(true)
            .theme(BannerTheme.COLORFUL)
            .customMessage("Welcome to My Application!")
            .build();

        // 커스텀 설정으로 배너 표시
        AppBannerUtil banner = new AppBannerUtil(config);
        banner.addRenderer(new ConsoleBannerRenderer(config));
        banner.display();
    }
}
```

#### 배너 정보 업데이트
```java
import io.csh.utils.banner.model.BannerInfo;

public class UpdateBannerExample {
    public static void main(String[] args) {
        AppBannerUtil banner = new AppBannerUtil();
        banner.addRenderer(new ConsoleBannerRenderer(banner.getConfig()));
        
        // 배너 정보 업데이트 (null이 아닌 필드만 업데이트됨)
        BannerInfo info = new BannerInfo();
        info.setAppName("My App");
        info.setVersion("1.0.0");
        info.setCustomMessage("Custom Message");
        
        banner.updateInfo(info);
        banner.display();
    }
}
```

#### 자동 정보 감지
```java
import io.csh.utils.banner.detector.DefaultBannerDetector;
import io.csh.utils.banner.model.BannerInfo;

public class AutoDetectExample {
    public static void main(String[] args) {
        // 자동으로 애플리케이션 정보 감지
        DefaultBannerDetector detector = new DefaultBannerDetector();
        BannerInfo info = detector.detect();
        
        // 감지된 정보로 배너 표시
        AppBannerUtil banner = new AppBannerUtil();
        banner.addRenderer(new ConsoleBannerRenderer(banner.getConfig()));
        banner.updateInfo(info);
        banner.display();
    }
}
```

#### 주의사항
- 배너는 애플리케이션 시작 시 한 번만 표시됩니다.
- 배너 정보 업데이트 시 null이 아닌 필드만 업데이트됩니다.
- 자동 감지는 JAR 매니페스트와 application.properties 파일에서 정보를 수집합니다.
- 테마는 DEFAULT, COLORFUL, DARK, LIGHT, MONOCHROME 중에서 선택할 수 있습니다.

### 2. 로깅 모듈
애플리케이션의 로그를 관리하는 모듈입니다.

#### 기본 사용
```java
import io.csh.utils.logging.Logger;

public class Example {
    public static void main(String[] args) {
        Logger.info("일반 정보 메시지");
        Logger.error("일반 오류 메시지");
        Logger.error("예외와 함께", new RuntimeException("예외"));
        Logger.debug("디버그 메시지");
        Logger.warn("경고 메시지");
    }
}
```

#### 로그 레벨 및 디렉토리 설정
```java
import io.csh.utils.logging.LogConfig;
import io.csh.utils.logging.LogLevel;

public class LogConfigExample {
    public static void main(String[] args) {
        // 로그 레벨 설정
        LogConfig.setCurrentLevel(LogLevel.INFO);

        // 로그 디렉토리 확인
        String logDir = LogConfig.getInstance().getLogDirectory();
        System.out.println("로그 디렉토리: " + logDir);
    }
}
```

## 주의사항

### Banner 모듈
- 배너는 애플리케이션 시작 시 한 번만 표시됩니다.
- 커스텀 로고는 ASCII 아트 형식을 지원합니다.
- 테마는 DEFAULT, COLORFUL, DARK, LIGHT, MONOCHROME 중에서 선택할 수 있습니다.

### 로깅 모듈
- Logger의 모든 메서드는 내부적으로 예외를 처리하므로, 호출 시 별도의 예외 처리가 필요 없습니다.
- 로그 파일 및 디렉토리는 자동으로 생성됩니다.
- 파일 로테이션 등은 현재 기본 제공되지 않습니다.

## 추가 정보
더 자세한 정보는 다음 문서를 참조하세요:
- [설계 문서](design.md)
- [로깅 모듈 README](docs/logging/README.md)
- [프로젝트 작업 목록](project-todo.md)

## Logging Module

### Basic Usage
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

### Configuration
로그 설정은 시스템 프로퍼티나 `logging.properties` 파일을 통해 지정할 수 있습니다.

#### System Properties
```bash
# 로그 레벨 설정
-Dspice.log.level=trace

# 로그 파일 크기 설정 (10MB)
-Dspice.log.maxFileSize=10485760

# 백업 파일 수 설정
-Dspice.log.maxBackupCount=5
```

#### Properties File
```properties
# logging.properties
log.level=trace
log.maxFileSize=10485760
log.maxBackupCount=5
log.directory=logs
```

### Log Levels
- TRACE: 가장 상세한 로그 레벨
- DEBUG: 디버깅을 위한 상세 정보
- INFO: 일반적인 정보
- WARN: 경고
- ERROR: 오류

### Log File Rotation
로그 파일은 다음과 같은 규칙으로 관리됩니다:
1. 파일 크기가 설정된 크기를 초과하면 자동으로 새로운 파일로 로테이션
2. 날짜가 변경될 때마다 새로운 파일 생성
3. 백업 파일은 `application.log.YYYY-MM-DD.0`, `application.log.YYYY-MM-DD.1` 등의 형식으로 저장

### Default Values
- 로그 레벨: INFO
- 로그 파일 크기: 10MB
- 백업 파일 수: 5개
- 로그 디렉토리: logs 