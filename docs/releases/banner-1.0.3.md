# Banner 모듈 1.0.3 릴리즈 노트

## 변경사항

### 기능 개선
- 테두리 출력 방식 개선
  - 테두리 우측 마감 위치 조정 (2칸 우측 이동)
  - 빈 줄 처리 로직 추가
  - 일관된 간격과 정렬 유지

### 버그 수정
- 커스텀 메시지가 출력되지 않는 문제 해결
  - `AppBanner.Builder`에서 `customMessage`가 `BannerConfig`에 전달되도록 수정

### 기타
- Java 8+ 호환성 개선
  - Text Blocks 대신 일반 문자열 사용
  - 하위 버전 호환성 확보

## 사용 예시

### 기본 배너 출력
```java
AppBanner banner = new AppBanner.Builder()
    .name("MyApp")
    .version("1.0.0")
    .build();
banner.print();
```

### 커스텀 메시지와 테두리 스타일
```java
AppBanner banner = new AppBanner.Builder()
    .name("MyApp")
    .version("1.0.0")
    .customMessage("Welcome to MyApp!")
    .borderStyle(BorderStyle.BOLD)
    .build();
banner.print();
```

## 출력 예시
```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ MyApp                             ┃
┃ Version: 1.0.0                    ┃
┃ Build Time: 2025-06-17 10:20:13   ┃
┃ Java Version: 24.0.1              ┃
┃ OS: Windows 11 10.0 (amd64)       ┃
┃                                   ┃
┃ Welcome to MyApp!                 ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
``` 