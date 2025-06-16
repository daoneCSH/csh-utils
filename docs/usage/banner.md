# Banner 모듈 사용 가이드

## 개요
Banner 모듈은 애플리케이션 시작 시 표시되는 배너를 관리하는 모듈입니다. 복잡한 내부 구현을 숨기고 간단한 API를 제공하여 쉽게 사용할 수 있습니다.

## 시작하기

### 의존성 추가
```xml
<dependency>
    <groupId>io.csh</groupId>
    <artifactId>csh-utils-banner</artifactId>
    <version>1.0.1</version>
</dependency>
```

### 기본 사용법
```java
import io.csh.utils.banner.AppBanner;

// 기본 테마로 배너 출력
AppBanner.printDefault();
```

## API 설명

### 1. 기본 테마 출력
```java
AppBanner.printDefault();
```
- 기본 테마를 사용하여 배너를 출력합니다.
- 애플리케이션 이름, 버전, 빌드 정보, 시스템 정보를 포함합니다.

### 2. 특정 테마 출력
```java
AppBanner.print(BannerTheme.ASCII);
```
- 지정된 테마를 사용하여 배너를 출력합니다.
- 지원되는 테마:
  - `DEFAULT`: 기본 테마
  - `ASCII`: ASCII 아트 스타일
  - `COLORFUL`: 컬러풀한 테마
  - `DARK`: 다크 테마
  - `LIGHT`: 라이트 테마
  - `MONOCHROME`: 흑백 테마

### 3. 사용자 정의 배너 출력
```java
// 배너 정보 설정
BannerInfo info = new BannerInfo();
info.setAppName("MyApp");
info.setVersion("1.0.0");
info.setCustomMessage("Welcome to MyApp!");

// 배너 설정
BannerConfig config = new BannerConfig.Builder()
    .theme(BannerTheme.COLORFUL)
    .showLogo(true)
    .showVersion(true)
    .showBuildInfo(true)
    .showSystemInfo(true)
    .build();

// 배너 출력
AppBanner.printCustom(info, config);
```

## 고급 사용법

### 1. 배너 정보 커스터마이징
```java
BannerInfo info = new BannerInfo();
info.setAppName("MyApp");
info.setVersion("1.0.0");
info.setBuildTime("2024-03-21");
info.setCustomMessage("Welcome to MyApp!");
```

### 2. 배너 설정 커스터마이징
```java
BannerConfig config = new BannerConfig.Builder()
    .theme(BannerTheme.COLORFUL)
    .showLogo(true)
    .showVersion(true)
    .showBuildInfo(true)
    .showSystemInfo(true)
    .customMessage("Custom Message")
    .logo("Custom Logo")
    .locale(Locale.ENGLISH)
    .build();
```

## 주의사항
1. 배너 설정은 `BannerConfig.Builder`를 통해 생성해야 합니다.
2. 사용자 정의 배너 정보는 `BannerInfo` 객체를 통해 제공됩니다.
3. 테마는 `BannerTheme` 열거형을 통해 지정할 수 있습니다.

## 문제 해결
1. 배너가 표시되지 않는 경우
   - 콘솔 출력이 가능한지 확인
   - 권한 설정 확인
   - 로케일 설정 확인

2. 테마가 적용되지 않는 경우
   - ANSI 색상 코드 지원 여부 확인
   - 터미널 설정 확인

## 관련 문서
- [Banner 모듈 설계 문서](../design/banner.md)
- [Banner 모듈 릴리즈 노트](../releases/banner-1.0.1.md) 