# CSH Utils Logging Module

## Overview
CSH Utils Logging Module은 Java 애플리케이션을 위한 고성능 로깅 라이브러리입니다. 비동기 처리와 배치 처리를 통해 최적화된 성능을 제공하며, 다양한 로깅 기능을 지원합니다.

## Features
- 비동기 로그 처리로 애플리케이션 성능 최적화
- 로그 레벨 기반 필터링 (DEBUG, INFO, WARN, ERROR)
- 자동 로그 파일 회전 및 압축
- 스레드 정보 및 예외 스택 트레이스 포함
- 배치 처리로 디스크 I/O 최소화

## Installation
Maven을 사용하여 프로젝트에 추가:
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils-logging</artifactId>
    <version>1.0.2</version>
</dependency>
```

## Quick Start
```java
import io.csh.utils.logging.Logger;

public class Example {
    public static void main(String[] args) {
        // 기본 로깅
        Logger.info("애플리케이션이 시작되었습니다.");
        
        // 예외 정보 포함 로깅
        try {
            // ... 코드 ...
        } catch (Exception e) {
            Logger.error("오류가 발생했습니다.", e);
        }
    }
}
```

## Documentation
자세한 사용 방법과 설정 옵션은 다음 문서를 참조하세요:
- [사용 가이드](../docs/usage)
- [설계 문서](../docs/design)

## Configuration

로깅 설정은 다음 순서로 적용됩니다 (위에서 아래로 우선순위가 낮아짐):

1. VM/시스템 프로퍼티 (`-D` 옵션)
2. application 프로퍼티 (`application.properties` 또는 `application.yml`)
3. logging 프로퍼티 (`logging.properties`)
4. 기본값

자세한 설정 방법과 예제는 [로깅 설정 가이드](../../docs/usage/logging-configuration.md)를 참조하세요.

## Contributing
프로젝트에 기여하고 싶으시다면 [CONTRIBUTING.md](../CONTRIBUTING.md)를 참조하세요.

## License
이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 [LICENSE](../LICENSE) 파일을 참조하세요. 