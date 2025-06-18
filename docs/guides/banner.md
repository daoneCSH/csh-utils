# Banner Guide

## Overview

`csh-utils-banner`는 애플리케이션 시작 시 콘솔에 배너를 출력하는 유틸리티입니다.

## Features

- ASCII 아트 배너 출력
- 커스텀 메시지 추가
- 다양한 테두리 스타일 지원
- Spring Boot 통합 지원

## Usage

### Basic Usage

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

### Spring Boot Integration

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

## Border Styles

다음 테두리 스타일을 지원합니다:

- `simple`: 기본 테두리
- `double`: 이중 테두리
- `round`: 둥근 모서리 테두리
- `bold`: 굵은 테두리
- `dashed`: 점선 테두리
- `dotted`: 점 테두리

## Dependencies

```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils-banner</artifactId>
    <version>${csh-utils.version}</version>
</dependency>
```

## Examples

### Default Banner
```
                ___  ___  _  _       _   _  _____  ___  _     ___
               / __|/ __|| || | ___ | | | ||_   _||_ _|| |   / __|
              | (__ \\__ \\| __ ||___|| |_| |  | |   | | | | |__ \\__ \\
               \\___||___/|_||_|      \\___/   |_|  |___||____||___/
```

### Custom Banner with Message
```
+------------------------------------------+
|                                          |
|                ___  ___  _  _            |
|               / __|/ __|| || | ___       |
|              | (__ \\__ \\| __ ||___|      |
|               \\___||___/|_||_|           |
|                                          |
| Welcome to My Application!               |
|                                          |
+------------------------------------------+
```

## Design Principles
1. **Simplicity**: Focus on core functionality
2. **Default First**: Provide sensible defaults
3. **Minimal Configuration**: Only essential options exposed

## Components

### BannerInfo
Contains the information to be displayed:
- Application name
- Version
- Build time
- Java version
- OS information
- Custom message (optional)

### BannerConfig
Controls display settings:
- ASCII art visibility (default: true)
- Border style (default: simple)

### BannerRenderer
Handles the actual banner rendering:
- Default ASCII art
- Information formatting
- Border generation

### AppBanner
Main entry point for users:
- Builder pattern for configuration
- Default banner creation
- Print functionality

## Best Practices
1. Use default banner when possible
2. Only configure when necessary
3. Keep custom messages concise
4. Use appropriate border style for your environment

## Troubleshooting
- If ASCII art is not displaying correctly, check your terminal's character encoding
- For border display issues, ensure your terminal supports the chosen border style

## Spring Boot Integration

### 1. Using Banner in Spring Boot Application
```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyApplication.class);
        
        // Set banner
        AppBanner banner = AppBanner.builder()
            .name("MyApp")
            .version("1.0.0")
            .config(BannerConfig.builder()
                .borderStyle("double")
                .build())
            .build();
            
        // Disable Spring Boot banner
        app.setBannerMode(Banner.Mode.OFF);
        
        // Print custom banner
        banner.print();
        
        app.run(args);
    }
}
```

### 2. Spring Boot Auto Configuration
```java
@Configuration
public class BannerAutoConfiguration {
    @Bean
    public AppBanner appBanner() {
        return AppBanner.builder()
            .name("MyApp")
            .version("1.0.0")
            .config(BannerConfig.builder()
                .borderStyle("double")
                .build())
            .build();
    }
}
```

## Related Documents
- [Design Document](../design/banner.md)
- [Module README](../../banner/README.md)
- [Release Notes](../releases/banner-1.0.0.md)

## Example Project
For complete examples, refer to the [Example Project](https://github.com/csh-utils/banner-example). 