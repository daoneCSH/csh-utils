# CSH Utils 출력 캡처 모듈

## 개요
출력 캡처 모듈은 Java 애플리케이션에서 System.out과 System.err로 출력되는 모든 내용을 자동으로 캡처하여 파일로 저장하는 간단하고 효율적인 라이브러리입니다. 로깅 시스템과는 독립적으로 동작하며, 단순히 콘솔에 출력되는 모든 내용을 파일로 저장합니다.

## 주요 기능
- System.out과 System.err 출력 자동 캡처
- 비동기 파일 쓰기로 성능 최적화
- 파일 크기 기반 로테이션
- 날짜 기반 파일 구분
- 설정 가능한 파일 보관 기간
- 압축 지원
- 스레드 안전한 동작

## 설치
프로젝트에 다음 의존성을 추가하세요:

```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>output-capture</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 빠른 시작
```java
// 출력 캡처 초기화
OutputCapture.getInstance().initialize();

// 출력이 자동으로 캡처되어 파일에 저장됩니다
System.out.println("이 내용은 자동으로 파일에 저장됩니다");

// 종료 시
OutputCapture.getInstance().shutdown();
```

## 설정
VM 옵션이나 application.properties에서 설정할 수 있습니다:

```properties
# 출력 디렉토리 (기본값: output)
csh.output.dir=logs

# 출력 파일 이름 (기본값: output.log)
csh.output.file=app.log

# 최대 파일 크기 (기본값: 10MB)
csh.output.max-size=10MB

# 최대 파일 개수 (기본값: 10)
csh.output.max-files=10

# 추가 모드 (기본값: true)
csh.output.append=true

# 압축 설정
csh.output.compression.enabled=true
csh.output.compression.format=gz

# 보관 기간 (기본값: 30일)
csh.output.retention.days=30
```

## 문서
- [사용자 가이드](docs/guides/output-capture.md)
- [설계 문서](docs/design/output-capture.md)
- [릴리즈 노트](docs/releases/output-capture-1.0.0.md)

## 기여
프로젝트에 기여하고 싶으시다면 [CONTRIBUTING.md](../../CONTRIBUTING.md)를 참조하세요.

## 라이선스
이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 [LICENSE](../../LICENSE) 파일을 참조하세요. 