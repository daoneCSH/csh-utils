# Banner 모듈 1.0.0 릴리즈 노트

## 주요 기능

### 1. 기본 배너 표시
- 애플리케이션 이름, 버전, 빌드 정보 표시
  - 앱네임 자동 감지
  - 버전 정보 자동 추출
  - 빌드 시간, 빌드 번호 표시
- 시스템 정보 표시
  - OS 정보 (이름, 버전, 아키텍처)
  - Java 버전 (버전, 벤더, 런타임)
  - 메모리 정보 (총 메모리, 사용 가능 메모리)
  - CPU 정보 (코어 수, 아키텍처)
- ASCII 아트 로고 지원
  - 기본 로고 템플릿
  - 커스텀 로고 템플릿
  - 로고 크기 자동 조정

### 2. 커스텀 설정
- 테마 시스템
  - DEFAULT: 기본 테마 (흰 배경, 검은 글씨)
  - COLORFUL: 컬러풀한 테마 (ANSI 색상)
  - DARK: 다크 테마 (검은 배경, 밝은 글씨)
  - LIGHT: 라이트 테마 (밝은 배경, 어두운 글씨)
  - MONOCHROME: 흑백 테마 (회색조)
- 커스텀 메시지 설정
  - 환영 메시지
  - 시작 메시지
  - 종료 메시지
- 로고 표시 여부 설정
  - 로고 크기 조정
  - 로고 위치 조정
  - 로고 색상 설정

### 3. 다양한 출력 형식
- 콘솔 출력 (ConsoleBannerRenderer)
  - ANSI 색상 지원
  - 터미널 크기 자동 감지
  - 유니코드 지원
- 로그 파일 출력 (LogBannerRenderer)
  - 로그 레벨 설정
  - 로그 포맷 설정
  - 로그 파일 자동 생성
- HTML 출력 (HtmlBannerRenderer)
  - 반응형 디자인
  - CSS 스타일링
  - 웹 폰트 지원

### 4. 자동 정보 감지
- 프레임워크 자동 감지
  - Spring Boot
  - Quarkus
  - Micronaut
  - 기타 프레임워크
- 환경 정보 자동 감지
  - OS 정보
  - Java 버전
  - 시스템 리소스
  - 네트워크 정보
- 설정 파일 자동 감지
  - application.properties
  - application.yml
  - 기타 설정 파일

### 5. 템플릿 시스템
- 기본 템플릿
  - 기본 레이아웃
  - 기본 스타일
  - 기본 메시지
- 커스텀 템플릿
  - 레이아웃 커스터마이징
  - 스타일 커스터마이징
  - 메시지 커스터마이징
- 템플릿 변수
  - 시스템 변수
  - 애플리케이션 변수
  - 커스텀 변수

## 사용 예시

### 기본 사용
```java
AppBannerUtil banner = new AppBannerUtil();
banner.addRenderer(new ConsoleBannerRenderer(banner.getConfig()));
banner.display();
```

### 커스텀 설정
```java
BannerConfig config = new BannerConfig.Builder()
    .showLogo(true)
    .showVersion(true)
    .showBuildInfo(true)
    .showSystemInfo(true)
    .theme(BannerTheme.COLORFUL)
    .customMessage("Welcome to My Application!")
    .build();

AppBannerUtil banner = new AppBannerUtil(config);
banner.addRenderer(new ConsoleBannerRenderer(config));
banner.display();
```

### 템플릿 사용
```java
BannerTemplate template = new BannerTemplate.Builder()
    .layout("custom-layout.template")
    .style("custom-style.css")
    .message("custom-message.properties")
    .build();

BannerConfig config = new BannerConfig.Builder()
    .template(template)
    .build();

AppBannerUtil banner = new AppBannerUtil(config);
banner.display();
```

## 변경사항

### 추가된 기능
- 템플릿 시스템
  - 기본 템플릿
  - 커스텀 템플릿
  - 템플릿 변수
- 자동 감지 시스템 강화
  - 프레임워크 감지
  - 환경 정보 감지
  - 설정 파일 감지
- 렌더러 시스템 개선
  - 콘솔 렌더러
  - 로그 렌더러
  - HTML 렌더러

### 개선된 기능
- 배너 레이아웃 개선
  - 반응형 디자인
  - 자동 크기 조정
  - 유니코드 지원
- 성능 최적화
  - 렌더링 성능
  - 메모리 사용량
  - 초기화 시간
- 코드 구조 개선
  - 모듈화
  - 확장성
  - 유지보수성

### 수정된 버그
- ANSI 색상 코드 처리 개선
- 로케일 관련 버그 수정
- 메모리 누수 수정
- 템플릿 변수 처리 버그 수정

## 마이그레이션 가이드

### 0.9.0에서 1.0.0으로 업그레이드
1. 새로운 의존성 추가
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils-banner</artifactId>
    <version>1.0.0</version>
</dependency>
```

2. 코드 변경
- `BannerConfig` 사용 방식 변경
- 새로운 렌더러 추가
- 테마 설정 추가
- 템플릿 시스템 도입

## 알려진 이슈
- 일부 터미널에서 ANSI 색상이 제대로 표시되지 않을 수 있음
- HTML 출력 시 일부 브라우저에서 스타일이 다르게 표시될 수 있음
- 템플릿 변수 처리 시 일부 특수문자 이스케이프 문제

## 향후 계획
- 배너 애니메이션 지원
- 더 많은 테마 추가
- 웹 기반 배너 에디터
- 배너 템플릿 마켓플레이스
- 템플릿 변수 검증 시스템
- 템플릿 캐싱 시스템 