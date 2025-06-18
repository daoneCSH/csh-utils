# CSH Utils 설계 개요

## 1. 아키텍처 개요

### 1.1 전체 구조
```
csh-utils/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── io/
        │       └── csh/
        │           └── utils/
        │               ├── banner/
        │               │   ├── AppBanner.java
        │               │   ├── art/
        │               │   │   └── DefaultAsciiArts.java
        │               │   ├── core/
        │               │   │   ├── BannerConfig.java
        │               │   │   └── BannerTheme.java
        │               │   ├── model/
        │               │   │   └── BannerInfo.java
        │               │   └── renderer/
        │               │       └── ConsoleBannerRenderer.java
        │               ├── logging/
        │               │   ├── Logger.java
        │               │   ├── LoggerImpl.java
        │               │   ├── LoggerFactory.java
        │               │   └── SimpleLogger.java
        │               ├── output/
        │               │   ├── OutputWriter.java
        │               │   ├── FileOutputWriter.java
        │               │   ├── OutputWriterFactory.java
        │               │   └── OutputCapture.java
        │               └── core/
        │                   ├── exception/
        │                   │   ├── CshException.java
        │                   │   └── CshRuntimeException.java
        │                   └── util/
        │                       └── CompareUtil.java
        └── resources/
            └── application.properties
```

### 1.2 주요 컴포넌트
- **Banner**: 애플리케이션 시작 시 표시되는 배너 관리
- **Logging**: 로깅 시스템
- **Output**: 출력 관리 시스템
- **Core**: 기본 유틸리티 및 예외 처리

## 2. 핵심 기능

### 2.1 배너 시스템
- 다양한 테마 지원
- ASCII 아트 지원
- 시스템 정보 자동 감지
- 사용자 정의 메시지 지원

### 2.2 로깅 시스템
- 클래스 기반 로깅
- 설정 가능한 로그 레벨
- 파일 및 콘솔 출력 지원
- 중복 로그 제어

### 2.3 출력 관리
- 다양한 출력 대상 지원
- 출력 캡처 기능
- 파일 로테이션 지원
- 설정 기반 출력 관리

## 3. 설계 원칙

### 3.1 Java Agent 호환성
- 클래스로더 분리 환경 지원
- 독립적인 실행 가능
- 최소 의존성

### 3.2 확장성
- 인터페이스 기반 설계
- 플러그인 아키텍처
- 설정 기반 동작

### 3.3 유지보수성
- 명확한 책임 분리
- 테스트 용이성
- 문서화 중심

## 4. 기술 스택

### 4.1 핵심 기술
- Java 17
- Maven
- JUnit

### 4.2 제약사항
- 무거운 프레임워크 사용 금지
- 외부 의존성 최소화
- 코드 생성 라이브러리 사용 금지

## 5. 성능 고려사항

### 5.1 최적화
- 메모리 사용 최소화
- 빠른 초기화
- 효율적인 리소스 관리

### 5.2 모니터링
- 성능 메트릭 수집
- 리소스 사용량 추적
- 병목 현상 감지

## 6. 보안 고려사항

### 6.1 데이터 보안
- 민감 정보 보호
- 로그 데이터 암호화
- 접근 제어

### 6.2 시스템 보안
- 클래스로더 보안
- 리소스 접근 제한
- 예외 처리

## 7. 향후 계획

### 7.1 개선 사항
- 성능 최적화
- 기능 확장
- 문서화 개선

### 7.2 새로운 기능
- 추가 출력 대상 지원
- 고급 로깅 기능
- 모니터링 도구

# 배너 모듈 설계 개요

## 1. 설계 원칙
- **BannerInfo**: 배너에 표시할 정보(이름, 버전, 빌드 시간, Java/OS 정보, 커스텀 메시지 등)만 관리
- **BannerConfig**: 테마(theme), 테두리(borderStyle) 등 배너의 표시 방식만 관리
- **BannerRenderer**: BannerInfo와 BannerConfig를 받아 실제 배너를 출력
- **템플릿/유틸리티**: 배너 정보는 BannerInfo에서만, 표시 방식은 BannerConfig에서만 참조

## 2. 클래스 역할
- BannerInfo: 배너에 표시할 모든 정보의 집합
- BannerConfig: 배너의 스타일(테마, 테두리 등)만 담당
- BannerRenderer: BannerInfo와 BannerConfig를 조합해 배너를 출력
- AppBanner: 빌더 패턴으로 BannerInfo와 BannerConfig를 조합해 사용자가 쉽게 배너를 생성/출력할 수 있도록 지원

## 3. 설계 예시
```java
BannerInfo info = BannerInfo.builder()
    .withName("MyApp")
    .withVersion("1.0.0")
    .withCustomMessage("Welcome!")
    .build();

BannerConfig config = BannerConfig.builder()
    .withTheme("colorful")
    .withBorderStyle("double")
    .build();

new BannerRenderer(config.getTheme(), config.getBorderStyle()).render(info);
```

## 4. 변경 이력
- 2025-06-18: 정보와 스타일의 완전 분리 설계로 리팩터링 