# Core 모듈 1.0.1 릴리즈 노트

## 주요 변경사항

### 1. SystemProperties 클래스 추가
- 체인 패턴을 사용한 프로퍼티 관리 시스템 도입
  - 시스템 프로퍼티, application.properties, JAR 파일명 등 다양한 소스에서 값을 조회
  - 프로퍼티 값의 타입 변환(String, Boolean, Integer, Long) 지원
  - 프로퍼티 체인을 통한 유연한 값 조회

### 2. PropertyManager 클래스 개선
- 프로퍼티 캐시 기능 추가
  - ConcurrentHashMap을 사용한 스레드 안전한 캐시 구현
  - 프로퍼티 값의 빠른 조회 지원
- 프로퍼티 관리 기능 강화
  - 프로퍼티 설정/조회/제거/초기화 기능 개선
  - 프로퍼티 존재 여부 확인 기능 추가

### 3. 유틸리티 클래스 개선
- StringUtil
  - 문자열 처리 기능 강화
  - null 안전한 문자열 조작 메서드 추가
  - 문자열 패딩, 자르기, 변환 기능 개선
- FileUtil
  - 파일 처리 기능 강화
  - 파일 읽기/쓰기 성능 개선
  - 파일 복사/이동 기능 추가
- CompareUtil
  - 객체 비교 기능 강화
  - null 안전한 비교 메서드 추가
  - 비교 가능한 객체의 유연한 비교 지원

## 사용 예시

### SystemProperties 사용
```java
// 체인 패턴을 사용한 프로퍼티 조회
String value = SystemProperties.getProperty("app.name")
    .fromSystem()
    .fromProperties()
    .fromJarName()
    .orElse("default");

// 타입 변환 지원
int timeout = SystemProperties.getProperty("app.timeout")
    .fromSystem()
    .orElse(30); // 기본값 30
```

### PropertyManager 사용
```java
// 프로퍼티 설정
PropertyManager.setProperty("app.name", "MyApp");

// 프로퍼티 조회
String appName = PropertyManager.getProperty("app.name");

// 프로퍼티 존재 확인
if (PropertyManager.hasProperty("app.name")) {
    // ...
}
```

### 개선된 유틸리티 사용
```java
// StringUtil
String padded = StringUtil.lpad("123", 5, '0'); // "00123"
String truncated = StringUtil.truncate("Hello World", 5, "..."); // "He..."

// FileUtil
FileUtil.copyFile("source.txt", "target.txt");
FileUtil.createDirectory("logs");

// CompareUtil
int result = CompareUtil.compare(obj1, obj2, true); // null 값이 더 큰 값으로 처리
```

## 마이그레이션 가이드

### 1.0.0에서 1.0.1로 업그레이드
1. 새로운 의존성 추가
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils-core</artifactId>
    <version>1.0.1</version>
</dependency>
```

2. 코드 변경
- SystemProperties 사용으로 프로퍼티 관리 방식 변경
- PropertyManager의 새로운 메서드 활용
- 개선된 유틸리티 메서드 사용

## 알려진 이슈
- 대용량 프로퍼티 파일 처리 시 메모리 사용량 증가
- 동시성 환경에서 프로퍼티 캐시 동기화 이슈 가능성
- 일부 OS에서 파일 권한 문제 발생 가능

## 향후 계획
- 프로퍼티 변경 이벤트 시스템 도입
- 프로퍼티 값 검증 기능 강화
- 유틸리티 클래스 성능 최적화
- 테스트 커버리지 향상
- 문서화 개선 