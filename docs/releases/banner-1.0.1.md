# Banner Module v1.0.1

## 변경사항

### 기능 개선
- 배너의 날짜 형식을 "yyyy-MM-dd HH:mm:ss" 형식으로 변경
- 한국 시간대(Asia/Seoul) 적용
- csh.utils 버전 정보 표시 기능 추가
  - v.properties 파일에서 자동으로 버전 정보 로드
  - 버전 정보를 읽을 수 없는 경우 "unknown" 표시

### 상세 변경사항
1. 날짜 형식 변경
   - 기존: ISO-8601 형식 (예: 2025-06-16T15:13:46.292013600)
   - 변경: 사용자 친화적 형식 (예: 2025-06-16 15:13:46)

2. 시간대 설정
   - 한국 시간대(Asia/Seoul) 적용
   - 로컬 시간대 대신 한국 시간 기준으로 표시

3. csh.utils 버전 표시
   - 배너에 CSH Utils 버전 정보 추가
   - 형식: "CSH Utils Version: x.x.x"
   - v.properties 파일에서 자동으로 버전 정보 로드

### 예시 출력
```
Version: 3.4.5
CSH Utils Version: 1.0.0
Build Time: 2025-06-16 15:13:46
Java Version: 17.0.14
OS: Windows 11 10.0 (amd64)
```

## 기술적 변경사항
1. BannerInfo 클래스
   - DateTimeFormatter 상수 추가
   - cshUtilsVersion 필드 추가
   - 생성자에서 한국 시간대 적용
   - v.properties 파일에서 버전 정보 로드 기능 추가

2. DefaultBannerTemplate 클래스
   - csh.utils 버전 표시 로직 추가

3. ConsoleBannerRenderer 클래스
   - csh.utils 버전 표시 로직 추가

## 의존성
- Java 17 이상
- 기존 의존성 유지

## 마이그레이션 가이드
- 기존 코드와의 호환성 유지
- 추가 설정 불필요
- 자동으로 새로운 형식 적용
- v.properties 파일이 classpath에 있어야 버전 정보 표시 가능

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

## 향후 계획
- [ ] 더 많은 테마 추가
- [ ] 배너 애니메이션 지원
- [ ] 웹 기반 배너 에디터
- [ ] 배너 템플릿 마켓플레이스 