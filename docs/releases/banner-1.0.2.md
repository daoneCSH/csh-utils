# Banner Module 1.0.2 Release Notes

## 개요
배너 모듈의 출력 방식을 개선하고 안정성을 높였습니다.

## 주요 변경사항

### 개선사항
- 테두리 기능을 상단/하단 구분선만 남기도록 단순화
- 폭 계산 로직 단순화로 출력 안정성 개선
- 한글, ASCII 아트 등 다양한 문자 출력 시 깨짐 현상 해결

### 제거된 기능
- BorderStyle enum 및 관련 API 제거
- 줄별 테두리 출력 기능 제거

## 마이그레이션 가이드
기존 코드에서 BorderStyle을 사용하던 경우, 해당 옵션을 제거하고 상단/하단 구분선만 사용하도록 변경해야 합니다.

### 변경 전
```java
AppBanner banner = new AppBanner.Builder()
    .name("MyApp")
    .version("1.0.0")
    .theme(BannerTheme.COLORFUL)
    .borderStyle(BorderStyle.DOUBLE)  // 제거됨
    .build();
```

### 변경 후
```java
AppBanner banner = new AppBanner.Builder()
    .name("MyApp")
    .version("1.0.0")
    .theme(BannerTheme.COLORFUL)
    .build();
```

## 기술적 변경사항
- ConsoleBannerRenderer 클래스 단순화
- 폭 계산 로직 개선 (전각/반각 문자 고려)
- 테두리 관련 코드 제거

## 버그 수정
- 한글, ASCII 아트 등 다양한 문자 출력 시 깨짐 현상 해결
- 테두리 출력 시 줄바꿈 문제 해결

## 향후 계획
- 더 다양한 ASCII 아트 템플릿 추가
- 테마 커스터마이징 기능 강화
- 성능 최적화 