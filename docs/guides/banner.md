# AppBanner 사용법 및 옵션 안내

## 기본 동작
- 별도의 설정 없이 AppBanner를 생성하면 BASIC 스타일의 ASCII 아트가 기본으로 출력됩니다.
- 배너의 옵션 정보(앱 이름, 버전, 빌드 정보 등)는 테두리로 감싸서 출력됩니다.
- ASCII 아트는 테두리 없이 자유롭게 출력됩니다.
- 커스텀 메시지는 빈 줄과 함께 테두리 안에 출력됩니다.

## 주요 옵션
| 옵션                | 설명                                                         |
|---------------------|--------------------------------------------------------------|
| name(String)        | 애플리케이션 이름                                            |
| version(String)     | 애플리케이션 버전                                            |
| theme(BannerTheme)  | 배너 테마 (DEFAULT, COLORFUL 등)                             |
| borderStyle(BorderStyle) | 옵션 정보에 적용할 테두리 스타일 (SIMPLE, DOUBLE, NONE 등) |
| asciiArt(String)    | 사용자 정의 ASCII 아트 지정                                   |
| showAsciiArt(boolean) | ASCII 아트 출력 여부 (false로 설정 시 아트 미출력)           |
| customMessage(String) | 사용자 정의 메시지 (빈 줄과 함께 테두리 안에 출력)           |
| showBuildInfo(boolean) | 빌드 정보 표시 여부                                         |
| showSystemInfo(boolean) | 시스템 정보 표시 여부                                       |

## 사용 예시

### 1. 기본 배너 출력
```java
AppBanner banner = new AppBanner.Builder()
    .name("MyApp")
    .version("1.0.0")
    .build();
banner.print();
```
- BASIC ASCII 아트와 SIMPLE 테두리로 옵션 정보가 출력됩니다.

### 2. ASCII 아트 없이 출력
```java
AppBanner banner = new AppBanner.Builder()
    .name("MyApp")
    .version("1.0.0")
    .showAsciiArt(false)
    .build();
banner.print();
```
- 옵션 정보만 테두리로 출력되고, ASCII 아트는 출력되지 않습니다.

### 3. 테두리 없이 출력
```java
AppBanner banner = new AppBanner.Builder()
    .name("MyApp")
    .version("1.0.0")
    .borderStyle(BorderStyle.NONE)
    .build();
banner.print();
```
- 옵션 정보도 테두리 없이 출력됩니다.

### 4. 커스텀 메시지와 테두리 스타일
```java
AppBanner banner = new AppBanner.Builder()
    .name("MyApp")
    .version("1.0.0")
    .customMessage("Welcome to MyApp!")
    .borderStyle(BorderStyle.BOLD)
    .build();
banner.print();
```
- 커스텀 메시지가 빈 줄과 함께 테두리 안에 출력됩니다.

### 5. 모든 옵션 조합
```java
AppBanner banner = new AppBanner.Builder()
    .name("MyApp")
    .version("1.0.0")
    .theme(BannerTheme.COLORFUL)
    .borderStyle(BorderStyle.DOUBLE)
    .customMessage("Welcome to MyApp!")
    .showAsciiArt(true)
    .showBuildInfo(true)
    .showSystemInfo(true)
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

## 참고 사항
- ASCII 아트는 항상 테두리 없이 출력되며, 옵션 정보(앱 이름, 버전 등)만 테두리로 감싸집니다.
- `showAsciiArt(false)`로 설정하면 ASCII 아트가 출력되지 않습니다.
- 커스텀 메시지는 `customMessage(String)`으로 지정할 수 있으며, 빈 줄과 함께 테두리 안에 출력됩니다.
- 최소 정보만 출력하고 싶다면 `showBuildInfo(false)`, `showSystemInfo(false)` 옵션을 활용하세요.
- 테두리 스타일은 `borderStyle(BorderStyle)`로 지정할 수 있으며, 옵션 정보에만 적용됩니다.

---

최신 AppBanner 동작 및 옵션은 위와 같습니다. 추가 문의나 개선 요청은 언제든 환영합니다. 