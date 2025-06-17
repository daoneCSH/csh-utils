# 로깅 모듈 1.0.3 릴리즈 노트

## 주요 변경사항

### 기능 개선
- `Logger` 클래스에 설정 상태 조회 기능 추가
  - `getConfigStatus()`: 로깅 설정 상태를 문자열로 반환
  - `printConfigStatus()`: 로깅 설정 상태를 콘솔에 출력

### 사용성 개선
- `LoggingConfig` 직접 참조 없이 설정 상태 조회 가능
- 설정 상태 조회 API 단순화

### 코드 품질
- JavaDoc 주석 추가
- 코드 구조 개선
- 의존성 관리 개선

## 사용 예시

```java
// 설정 상태 문자열 가져오기
String configStatus = Logger.getConfigStatus();

// 또는 직접 출력하기
Logger.printConfigStatus();
```

## 호환성
- Java 8 이상 지원
- Maven 3.6 이상 지원

## 의존성
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>logging</artifactId>
    <version>1.0.3</version>
</dependency>
```

## 마이그레이션 가이드
1.0.2 버전에서 1.0.3로 업그레이드 시 다음 사항을 확인하세요:

1. 설정 상태 조회 방식 변경:
   - 기존: `LoggingConfig.getInstance().printLoggingStatus()`
   - 변경: `Logger.getConfigStatus()` 또는 `Logger.printConfigStatus()`

2. 의존성 업데이트:
   - pom.xml의 버전을 1.0.3으로 업데이트

## 알려진 이슈
- 없음

## 향후 계획
- 설정 동적 변경 기능 추가
- 설정 변경 이벤트 처리 기능 추가
- 모니터링 기능 강화 