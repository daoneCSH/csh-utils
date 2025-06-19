# Usage Guide

## Overview

`csh-utils`는 Java 애플리케이션을 위한 유틸리티 모듈 모음입니다.

## Modules

### csh-utils-banner

애플리케이션 시작 시 콘솔에 배너를 출력하는 유틸리티입니다.

#### Features
- ASCII 아트 배너 출력
- 커스텀 메시지 추가
- 다양한 테두리 스타일 지원
- Spring Boot 통합 지원

#### Usage
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

## Dependencies

### Maven
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils-banner</artifactId>
    <version>${csh-utils.version}</version>
</dependency>
```

### Gradle
```groovy
implementation 'io.csh:csh-utils-banner:${csh-utils.version}'
```

## Requirements

- Java 17 or higher
- Maven 3.6+ or Gradle 7.0+

## License

This project is licensed under the MIT License.

## 2. 설정 우선순위

모든 설정값은 다음 우선순위를 따릅니다:
1. 시스템 프로퍼티 (-D 옵션)
2. application.properties/yml
3. 기본값 (코드 하드코딩)

예시:
```java
String value = System.getProperty("my.property");
if (value == null) {
    value = applicationProperties.getProperty("my.property");
}
if (value == null) {
    value = "default";
}
```

## 3. 시작하기

### 3.1 의존성 추가
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 3.2 기본 사용 예시
```java
import io.csh.utils.banner.AppBanner;

// 기본 배너 출력
AppBanner.builder()
    .name("MyApp")
    .version("1.0.0")
    .print();
```

## 4. 모듈별 가이드

- [배너 모듈 가이드](banner.md)
- [로깅 모듈 가이드](logging.md)

## 5. Java Agent 환경
- 모든 모듈은 Java Agent 환경에서 동작하도록 설계되었습니다.
- 외부 프레임워크/라이브러리 의존성 없이 동작합니다.

## 6. 빌드/테스트/로그 파일 관리
- 빌드: `mvn clean install -f pom.xml > build.log 2>&1`
- 테스트: `mvn test -Dtest=TestClassName > test.log 2>&1`
- 모든 빌드/테스트 결과는 반드시 로그 파일로 남겨야 하며, 표준 출력과 에러가 모두 캡처되어야 합니다.

## 7. 문제 해결
- 로그 파일을 통해 빌드/테스트 결과 및 에러를 확인하세요.
- 설정 우선순위에 따라 값이 적용되는지 확인하세요.
- Java Agent 환경에서의 동작을 반드시 검증하세요.

---

자세한 사용법과 예제는 각 모듈별 가이드를 참고하세요. 