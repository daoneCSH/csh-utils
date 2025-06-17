# Output 모듈

Java Agent 호환성을 고려한 간단하고 가벼운 파일 출력 모듈입니다.

## 주요 기능
- 기본 파일 출력 작업
- 파일 로테이션 지원
- 자동 디렉토리 생성
- 스레드 안전 구현
- 외부 의존성 없음

## 의존성 추가
```xml
<dependency>
    <groupId>io.csh.utils</groupId>
    <artifactId>output</artifactId>
    <version>${csh.utils.version}</version>
</dependency>
```

## 빠른 시작

```java
import io.csh.utils.output.OutputWriter;
import io.csh.utils.output.OutputWriterFactory;

// 기본 설정으로 작성기 생성
OutputWriter writer = OutputWriterFactory.getWriter("application");

// 메시지 쓰기
writer.writeLine("테스트 메시지입니다");
writer.flush();
```

## 문서
- [사용자 가이드](../../docs/guides/output.md)
- [설계 문서](../../docs/design/output.md)
- [릴리즈 노트](../../docs/releases/output-1.0.0.md)

## 설정
자세한 설정 옵션은 [사용자 가이드](../../docs/guides/output.md#설정)를 참조하세요.

## 의존성
- Java 8 이상
- 외부 의존성 없음

## 예제 프로젝트
전체 예제는 [예제 프로젝트](https://github.com/csh-utils/output-example)를 참조하세요.

## 라이선스
이 모듈은 csh-utils 프로젝트의 일부입니다. 라이선스 정보는 메인 프로젝트를 참조하세요. 