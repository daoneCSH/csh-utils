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

public class Example {
    public static void main(String[] args) {
        // 기본 설정으로 배너 표시
        AppBannerUtil banner = new AppBannerUtil();
        banner.display();
    }
}
```

#### 커스텀 설정
```java
import io.csh.utils.banner.core.AppBannerUtil;
import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.core.BannerTheme;

public class CustomBannerExample {
    public static void main(String[] args) {
        // 커스텀 설정 생성
        BannerConfig config = new BannerConfig()
            .showLogo(true)
            .showVersion(true)
            .showBuildInfo(true)
            .showSystemInfo(true)
            .theme(BannerTheme.COLORFUL)
            .customMessage("Welcome to My Application!")
            .logo("""
                *************************
                *     My Application    *
                *************************
                """);

        // 커스텀 설정으로 배너 표시
        AppBannerUtil banner = new AppBannerUtil(config);
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
        
        // 배너 정보 업데이트
        BannerInfo info = new BannerInfo();
        info.setAppName("My App");
        info.setVersion("1.0.0");
        info.setCustomMessage("Custom Message");
        
        banner.updateInfo(info);
        banner.display();
    }
}
```

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

        Logger.infoDb("DB 정보");
        Logger.errorDb("DB 오류");
        Logger.debugDb("DB 디버그");
        Logger.warnDb("DB 경고");

        Logger.infoWeb("Web 정보");
        Logger.errorWeb("Web 오류");
        Logger.debugWeb("Web 디버그");
        Logger.warnWeb("Web 경고");
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
- 동적 카테고리, 파일 로테이션 등은 현재 기본 제공되지 않습니다.

## 추가 정보
더 자세한 정보는 다음 문서를 참조하세요:
- [설계 문서](design.md)
- [로깅 모듈 README](docs/logging/README.md)
- [프로젝트 작업 목록](project-todo.md) 