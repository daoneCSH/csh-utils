# Banner 모듈 설계 문서

## 개요
Banner 모듈은 애플리케이션 시작 시 표시되는 배너를 관리하는 모듈입니다. 복잡한 내부 구현을 숨기고 간단한 API를 제공하기 위해 Facade 패턴을 적용했습니다.

## 아키텍처

### 1. 패키지 구조
```
io.csh.utils.banner
├── AppBanner.java              # Facade 클래스
├── core/
│   ├── AppBannerUtil.java      # 배너 관리 클래스
│   ├── BannerConfig.java       # 배너 설정 클래스
│   └── BannerTheme.java        # 배너 테마 열거형
├── model/
│   └── BannerInfo.java         # 배너 정보 클래스
├── renderer/
│   └── ConsoleBannerRenderer.java  # 콘솔 출력 렌더러
└── detector/
    └── DefaultBannerDetector.java  # 배너 정보 감지기
```

### 2. 클래스 다이어그램
```
[AppBanner] --> [AppBannerUtil]
[AppBanner] --> [BannerConfig]
[AppBanner] --> [BannerTheme]
[AppBanner] --> [BannerInfo]
[AppBanner] --> [ConsoleBannerRenderer]
[AppBanner] --> [DefaultBannerDetector]

[AppBannerUtil] --> [BannerConfig]
[AppBannerUtil] --> [BannerInfo]
[AppBannerUtil] --> [BannerRenderer]

[ConsoleBannerRenderer] --> [BannerConfig]
[ConsoleBannerRenderer] --> [BannerInfo]

[DefaultBannerDetector] --> [BannerInfo]
```

## 설계 패턴

### 1. Facade 패턴
- **목적**: 복잡한 내부 구현을 숨기고 간단한 API 제공
- **구현**: `AppBanner` 클래스
- **장점**:
  - 단일 import로 배너 기능 사용 가능
  - 내부 구현 변경에 영향을 받지 않음
  - 사용자 친화적인 API 제공

### 2. Builder 패턴
- **목적**: 복잡한 객체 생성 과정 단순화
- **구현**: `BannerConfig.Builder` 클래스
- **장점**:
  - 유연한 설정 구성
  - 가독성 있는 코드
  - 불변 객체 생성

### 3. Strategy 패턴
- **목적**: 다양한 테마와 렌더러 지원
- **구현**: `BannerTheme`, `BannerRenderer` 인터페이스
- **장점**:
  - 새로운 테마/렌더러 쉽게 추가 가능
  - 런타임에 테마/렌더러 변경 가능

## 주요 클래스 설명

### 1. AppBanner
```java
public class AppBanner {
    public static void printDefault();
    public static void print(BannerTheme theme);
    public static void printCustom(BannerInfo info, BannerConfig config);
}
```
- Facade 클래스
- 정적 메서드로 간단한 API 제공
- 내부 구현은 `AppBannerUtil`에 위임

### 2. BannerConfig
```java
public class BannerConfig {
    public static class Builder {
        public Builder theme(BannerTheme theme);
        public Builder showLogo(boolean showLogo);
        public Builder showVersion(boolean showVersion);
        // ...
        public BannerConfig build();
    }
}
```
- 배너 설정 관리
- 빌더 패턴으로 객체 생성
- 불변 객체로 설계

### 3. BannerInfo
```java
public class BannerInfo {
    private String appName;
    private String version;
    private String buildTime;
    // ...
}
```
- 배너에 표시될 정보 관리
- 시스템 정보 자동 감지
- 사용자 정의 정보 지원

## 확장성

### 1. 새로운 테마 추가
1. `BannerTheme` 열거형에 새로운 테마 추가
2. 테마별 스타일 정의
3. `ConsoleBannerRenderer`에서 테마 적용

### 2. 새로운 렌더러 추가
1. `BannerRenderer` 인터페이스 구현
2. `AppBannerUtil`에 렌더러 등록
3. `AppBanner`에서 렌더러 사용

## 성능 고려사항
1. 배너 정보 캐싱
2. 렌더러 재사용
3. 불필요한 객체 생성 최소화

## 테스트 전략
1. 단위 테스트
   - 각 클래스별 기능 테스트
   - 경계 조건 테스트
2. 통합 테스트
   - 전체 배너 출력 테스트
   - 다양한 설정 조합 테스트
3. 성능 테스트
   - 배너 출력 성능 측정
   - 메모리 사용량 측정

## 향후 개선 계획
1. 배너 애니메이션 지원
2. 웹 기반 배너 에디터
3. 배너 템플릿 마켓플레이스
4. 더 많은 테마 추가 