# csh-utils

유틸리티 모듈 모음

## 모듈 구성

- `csh-utils-banner`: 콘솔 배너 출력 유틸리티

## csh-utils-banner

애플리케이션 시작 시 콘솔에 배너를 출력하는 유틸리티입니다.

### 주요 기능

- ASCII 아트 배너 출력
- 커스텀 메시지 추가
- 다양한 테두리 스타일 지원
- Spring Boot 통합 지원

### 사용 방법

#### 기본 사용법

```java
// 기본 배너 출력
AppBanner.createDefault().print();

// 커스텀 배너 생성
AppBanner.builder()
    .message("Welcome to My Application!")
    .showAsciiArt(true)
    .borderStyle("double")
    .build()
    .print();
```

#### Spring Boot 통합

```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyApplication.class);
        
        // 배너 설정
        AppBanner.builder()
            .message("My Spring Boot Application")
            .showAsciiArt(true)
            .borderStyle("round")
            .build()
            .print();
            
        app.run(args);
    }
}
```

### 테두리 스타일

다음 테두리 스타일을 지원합니다:

- `simple`: 기본 테두리
- `double`: 이중 테두리
- `round`: 둥근 모서리 테두리
- `bold`: 굵은 테두리
- `dashed`: 점선 테두리
- `dotted`: 점 테두리

### 의존성

```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils-banner</artifactId>
    <version>${csh-utils.version}</version>
</dependency>
```

## 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.

## Modules

### Banner Module
A simple banner module for displaying application information with a default ASCII art.

#### Features
- Default ASCII art display
- Application information (name, version, build time)
- System information (Java version, OS details)
- Optional border styles
- Simple configuration

#### Quick Start
```java
// Display default banner
AppBanner.createDefault().print();

// Custom banner
AppBanner.builder()
    .name("MyApp")
    .version("1.0.0")
    .config(BannerConfig.builder()
        .showAsciiArt(false)  // Hide ASCII art
        .borderStyle("double") // Change border style
        .build())
    .print();
```

For more details, see the [Banner Guide](docs/guides/banner.md).

## Getting Started

### Requirements
- Java 8 or higher
- Maven 3.6 or higher

### Installation
Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## Documentation
- [User Guides](docs/guides/)
- [Design Documents](docs/design/)
- [Release Notes](docs/releases/)

## Contributing
Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 주요 기능

### 1. 배너 모듈
애플리케이션 시작 시 표시되는 ASCII 아트 스타일의 배너를 생성하고 관리합니다.

```java
// 기본 배너 출력
AppBanner.createDefault().print();

// 커스텀 배너 생성
AppBanner.builder()
    .withName("MyApp")
    .withVersion("1.0.0")
    .withTheme("colorful")
    .withBorderStyle("double")
    .withCustomMessage("Welcome!")
    .build()
    .print();
```

자세한 사용법은 [배너 모듈 가이드](docs/guides/banner.md)를 참조하세요.

### 2. 로깅 모듈
애플리케이션의 로그를 관리하고 출력을 캡처합니다.

```java
// 간편한 로깅 사용
import io.csh.utils.logging.Logging;

Logging.info("Application started");
Logging.debug("Debug message");
Logging.error("Error occurred", exception);

// 고급 로깅 사용
import io.csh.utils.logging.Logger;
import io.csh.utils.logging.LoggerFactory;

Logger logger = LoggerFactory.getLogger(YourClass.class);
logger.info("Application started");
```

자세한 사용법은 [로깅 모듈 가이드](docs/guides/logging.md)를 참조하세요.

### 3. 통합 인터페이스
로깅과 출력을 통합하여 제공합니다.

```java
import io.csh.utils.integration.CSHUtils;

// 로깅만 사용
CSHUtils.Logging.info(MyClass.class, "Application started");

// 출력만 사용
CSHUtils.Output.write("Output message");

// 로깅과 출력 동시 사용
CSHUtils.Integration.logAndOutput(MyClass.class, "Important message");
```

## 주의사항

1. 이 모듈은 Java Agent에서도 사용됩니다.
2. 외부 의존성을 최소화하여 가볍게 유지합니다.
3. Spring, Log4j2 등 무거운 프레임워크는 사용하지 않습니다.

## 참고사항

- `src/main/java/io/csh/utils/agent/AgentEntry.java`는 테스트용 구현입니다.
- 실제 Java Agent는 별도 프로젝트에서 개발 중이며, 이 모듈을 사용할 예정입니다.
- 이 테스트 코드는 모듈이 Java Agent 환경에서도 잘 동작하는지 확인하기 위한 것입니다.

## 소개
CSH Utils는 Java 애플리케이션 개발을 위한 유틸리티 라이브러리입니다. 
Java Agent 호환성을 고려하여 설계되었으며, 로깅과 출력 기능을 제공합니다.

## 주요 기능
- 로깅 시스템
- 출력 관리
- 통합 인터페이스

## 시작하기

### 요구사항
- Java 17 이상
- Maven 3.9.9 이상

### 사용 예제
```java
import io.csh.utils.integration.CSHUtils;

public class MyClass {
    public void example() {
        // 로깅만 사용
        CSHUtils.Logging.info(MyClass.class, "Application started");
        
        // 출력만 사용
        CSHUtils.Output.write("Output message");
        
        // 로깅과 출력 동시 사용
        CSHUtils.Integration.logAndOutput(MyClass.class, "Important message");
    }
}
```

## 모듈 구성
- **core**: 핵심 유틸리티 기능
- **logging**: 로깅 기능 (파일 출력 포함)
- **banner**: 애플리케이션 배너 출력
- **integration**: 로깅과 출력 통합 인터페이스

## 문서
- [사용 가이드](docs/guides/usage.md)
- [설계 문서](docs/design/overview.md)
- [릴리즈 노트](docs/releases/)

## 기여하기
[기여 가이드](CONTRIBUTING.md)를 참조하세요. 