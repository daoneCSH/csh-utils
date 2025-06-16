# Release Notes

## Version 1.0.1 (2024-02-17)

### 개선사항
- Core 모듈 구조 개선
  - 패키지 구조 정리 및 문서화 개선
  - 중복 파일 제거 및 통합
  - 코드 가독성 향상

### 주요 변경사항
1. **Constants 클래스 통합**
   - 모든 상수를 하나의 클래스로 통합
   - 내부 클래스를 사용하여 도메인별로 구분 (Common, System, Error)
   - 생성자에서 예외 발생으로 인스턴스화 방지

2. **SystemProperties 개선**
   - config 패키지로 이동
   - 체인 패턴 구현 개선
   - 문서화 강화

3. **패키지 구조 개선**
   - 각 패키지의 역할을 명확히 구분
   - package-info.java 파일 업데이트
   - 문서화 개선

4. **문서화 개선**
   - 각 패키지의 역할과 주요 클래스 설명 추가
   - 클래스와 메서드의 JavaDoc 개선
   - 코드 가독성 향상

### 사용 예시
```java
// Constants 사용
String emptyString = Constants.Common.EMPTY_STRING;
String osName = Constants.System.OS_NAME;
String errorMsg = Constants.Error.UNKNOWN_ERROR;

// SystemProperties 사용
String appName = SystemProperties.getProperty("app.name")
    .checkSystemProperty()
    .checkApplicationProperties()
    .checkJarFileName()
    .orElse("unknown");

boolean debug = SystemProperties.getProperty("log.debug")
    .checkSystemProperty()
    .orElse(false);
```

## Version 1.0.0 (2024-02-17)

### 초기 릴리즈
- Core 모듈 기본 기능 구현
- 기본 유틸리티 클래스 제공
- 예외 처리 클래스 구현
- 상수 정의
- 설정 관리 기능 