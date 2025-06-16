# Core 모듈 1.0.0 릴리즈 노트

## 주요 기능

### 1. 기본 유틸리티
- StringUtil
  - 문자열 검증 (isBlank, isNotBlank)
  - 문자열 변환 (toUpperCase, toLowerCase)
  - 문자열 포맷팅 (format)
  - 문자열 파싱 (parseInt, parseLong)
- FileUtil
  - 파일 읽기/쓰기 (readFile, writeFile)
  - 파일 검증 (exists, isDirectory)
  - 파일 변환 (toPath, toFile)
- CompareUtil
  - 객체 비교 (equals)
  - 컬렉션 비교 (equals)
  - null 안전 비교 (nullSafeEquals)

### 2. 상수 정의
- 시스템 상수
  - OS 관련 상수 (OS_NAME, OS_VERSION)
  - 환경 변수 상수 (JAVA_HOME, USER_HOME)
  - 시스템 프로퍼티 상수 (java.version, os.name)
- 에러 코드
  - 시스템 에러 코드 (SYSTEM_ERROR)
  - 애플리케이션 에러 코드 (APPLICATION_ERROR)
  - 네트워크 에러 코드 (NETWORK_ERROR)
- 기본값 상수
  - 기본 설정값 (DEFAULT_ENCODING)
  - 기본 제한값 (MAX_FILE_SIZE)
  - 기본 타임아웃값 (DEFAULT_TIMEOUT)

### 3. 예외 처리
- 커스텀 예외 클래스
  - SystemException
  - ApplicationException
  - ValidationException
- 예외 처리 유틸리티
  - 예외 변환 (convertException)
  - 예외 체인 관리 (getRootCause)
  - 예외 로깅 (logException)

### 4. 설정 관리
- ConfigManager
  - 설정 파일 로드
  - 설정 값 조회
  - 설정 값 검증
  - 설정 값 변경 감지
- 설정 상수
  - 기본 설정 파일 경로
  - 설정 파일 형식
  - 설정 값 타입

## 사용 예시

### StringUtil
```java
// 문자열 검증
StringUtil.isBlank(""); // true
StringUtil.isNotBlank("hello"); // true

// 문자열 변환
StringUtil.toUpperCase("hello"); // "HELLO"
StringUtil.toLowerCase("HELLO"); // "hello"

// 문자열 포맷팅
StringUtil.format("Hello, %s!", "World"); // "Hello, World!"
```

### FileUtil
```java
// 파일 읽기
String content = FileUtil.readFile("file.txt");

// 파일 쓰기
FileUtil.writeFile("file.txt", "Hello, World!");

// 파일 검증
boolean exists = FileUtil.exists("file.txt");
boolean isDirectory = FileUtil.isDirectory("dir");
```

### CompareUtil
```java
// 객체 비교
boolean equal = CompareUtil.equals(obj1, obj2);

// 컬렉션 비교
boolean equal = CompareUtil.equals(list1, list2);

// null 안전 비교
boolean equal = CompareUtil.nullSafeEquals(obj1, obj2);
```

### ConfigManager
```java
// 설정 파일 로드
ConfigManager.load("config.properties");

// 설정 값 조회
String value = ConfigManager.getString("key");

// 설정 값 검증
boolean isValid = ConfigManager.validate("key");

// 설정 값 변경 감지
ConfigManager.addChangeListener((key, oldValue, newValue) -> {
    System.out.println("Config changed: " + key);
});
```

## 변경사항

### 추가된 기능
- ConfigManager 클래스 추가
- 설정 관련 상수 추가
- 설정 변경 감지 기능

### 개선된 기능
- 성능 최적화
- 메모리 사용량 최적화
- 코드 가독성 개선

### 수정된 버그
- null 처리 개선
- 동시성 이슈 수정
- 메모리 누수 수정

## 마이그레이션 가이드

### 0.9.0에서 1.0.0으로 업그레이드
1. 새로운 의존성 추가
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

2. 코드 변경
- 유틸리티 메서드 사용 방식 변경
- 예외 처리 방식 변경
- 상수 사용 방식 변경
- ConfigManager 사용 추가

## 알려진 이슈
- 대용량 파일 처리 시 메모리 사용량 증가
- 일부 OS에서 파일 권한 문제 발생 가능
- 설정 파일 변경 감지 시 일부 이벤트 누락 가능성

## 향후 계획
- 더 많은 유틸리티 클래스 추가
- 성능 최적화
- 문서화 개선
- 테스트 커버리지 향상
- 설정 관리 기능 강화 