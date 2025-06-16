# Banner 모듈 1.0.1 릴리즈

## 변경사항

### Facade 패턴 적용
- `AppBanner` 클래스 추가
  - 복잡한 내부 구현을 숨기고 간단한 API 제공
  - 단일 import로 배너 기능 사용 가능
  - 기본 테마, 커스텀 테마, 사용자 정의 배너 지원

### API 개선
- `printDefault()`: 기본 테마로 배너 출력
- `print(BannerTheme)`: 지정된 테마로 배너 출력
- `printCustom(BannerInfo, BannerConfig)`: 사용자 정의 배너 출력

### 내부 구현 개선
- `BannerConfig` 빌더 패턴 적용
- `AppBannerUtil`과 `ConsoleBannerRenderer` 연동 개선
- 배너 정보 자동 감지 기능 강화

## 사용 예시

```java
// 기본 테마로 배너 출력
AppBanner.printDefault();

// ASCII 테마로 배너 출력
AppBanner.print(BannerTheme.ASCII);

// 사용자 정의 배너 출력
BannerInfo info = new BannerInfo();
info.setAppName("MyApp");
info.setVersion("1.0.0");

BannerConfig config = new BannerConfig.Builder()
    .theme(BannerTheme.COLORFUL)
    .showLogo(true)
    .showVersion(true)
    .build();

AppBanner.printCustom(info, config);
```

## 호환성
- Java 8 이상
- 기존 배너 기능과 완벽한 호환성 유지
- 기존 API는 deprecated 처리하지 않음

## 마이그레이션 가이드
기존 코드에서 새로운 Facade 클래스로 마이그레이션하는 방법:

```java
// 기존 코드
BannerConfig config = new BannerConfig();
config.setTheme(BannerTheme.DEFAULT);
BannerInfo info = new DefaultBannerDetector().detect();
new ConsoleBannerRenderer().render(info);

// 새로운 코드
AppBanner.printDefault();
```

## 향후 계획
- [ ] 더 많은 테마 추가
- [ ] 배너 애니메이션 지원
- [ ] 웹 기반 배너 에디터
- [ ] 배너 템플릿 마켓플레이스 