# CSH Utils

## 소개
CSH Utils는 Java 애플리케이션 개발을 위한 유틸리티 라이브러리입니다. 
로깅, 설정 관리, 스레드 관리 등 다양한 기능을 제공합니다.

## 주요 기능
- 로깅 시스템 (logging)
- 배너 표시 (banner)
- 설정 관리 (config)
- 스레드 관리 (thread)
- 기본 유틸리티 (core)

## 시작하기

### 요구사항
- Java 17 이상
- Maven 3.9.9 이상

### 설치
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils</artifactId>
    <version>1.0.1</version>
</dependency>
```

### 사용 예제
```java
// 로깅 예제
import io.csh.utils.logging.Logger;
Logger.info("Hello, World!");

// 배너 예제
import io.csh.utils.banner.AppBanner;
AppBanner.printDefault();
```

## 모듈 구성
- **core**: 핵심 유틸리티 기능
- **logging**: 로깅 기능
- **thread**: 스레드 관련 유틸리티
- **config**: 설정 관리
- **banner**: 애플리케이션 배너 출력
- **output**: 시스템 출력 관리

## 문서
- [사용 가이드](docs/guides/)
- [설계 문서](docs/design/)
- [릴리즈 노트](docs/releases/)

## 라이선스
이 프로젝트는 [MIT 라이선스](LICENSE)를 따릅니다.

## 기여하기
[기여 가이드](CONTRIBUTING.md)를 참조하세요. 