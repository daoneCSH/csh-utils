# CSH Utils Logging Module

## Overview
CSH Utils Logging Module은 Java 애플리케이션을 위한 간단하고 효율적인 로깅 라이브러리입니다. 
`@CshLog` 어노테이션을 사용하여 클래스에 자동으로 로거를 주입할 수 있으며, 
정적 메서드를 통한 간편한 로깅도 지원합니다.

## Features
- `@CshLog` 어노테이션을 통한 자동 로거 생성
- 정적 메서드를 통한 간편한 로깅
- 로그 레벨 기반 필터링 (TRACE, DEBUG, INFO, WARN, ERROR, FATAL)
- 스레드 정보 및 예외 스택 트레이스 포함
- 커스텀 로그 포맷 지원
- 설정 상태 조회 기능
  - `Logger.getConfigStatus()`: 설정 상태 문자열 반환
  - `Logger.printConfigStatus()`: 설정 상태 콘솔 출력

## Installation
Maven을 사용하여 프로젝트에 추가:
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>logging</artifactId>
    <version>1.0.3</version>
</dependency>
```

## Quick Start
자세한 사용 방법은 [로깅 가이드](../../docs/guides/logging.md)를 참조하세요.

### 기본 사용법
```java
// 정적 메서드 사용
Logger.info("일반 정보 메시지");
Logger.error("오류 메시지", exception);

// @CshLog 어노테이션 사용
@CshLog
public class MyClass {
    public void someMethod() {
        log.info("처리를 시작합니다");
    }
}

// 설정 상태 확인
Logger.printConfigStatus();
```

## Documentation
- [사용 가이드](../../docs/guides/logging.md)
- [설계 문서](../../docs/design/logging.md)
- [릴리즈 노트](../../docs/releases/logging-1.0.3.md)

## Contributing
프로젝트에 기여하고 싶으시다면 [CONTRIBUTING.md](../CONTRIBUTING.md)를 참조하세요.

## License
이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 [LICENSE](../LICENSE) 파일을 참조하세요. 