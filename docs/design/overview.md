# CSH Utils Design Document

## 1. Overview

CSH Utils는 Java 애플리케이션 개발을 위한 유틸리티 라이브러리 모음입니다. 각 모듈은 특정 기능을 제공하며, 모듈 간의 의존성을 최소화하여 유연한 사용을 가능하게 합니다.

## 2. Module Structure

```
csh-utils (Parent)
├── core (기본 유틸리티)
│   ├── constants (상수 정의)
│   ├── exception (예외 처리)
│   └── util (기본 유틸리티 클래스들)
│       ├── StringUtil
│       ├── FileUtil
│       └── CompareUtil
│
├── logging (로깅 관련)
│   ├── Logger
│   ├── LogConfig
│   └── LogLevel
│
├── thread (스레드 관련)
│   ├── ThreadUtil
│   └── ThreadPool
│
├── config (설정 관련)
│   └── ConfigManager
│
└── banner (배너 관련)
    ├── core
    │   ├── AppBannerUtil (메인 클래스)
    │   ├── BannerConfig (배너 설정)
    │   └── BannerTheme (테마 정의)
    │
    ├── model
    │   ├── BannerInfo (배너 정보 모델)
    │   ├── SystemInfo (시스템 정보 모델)
    │   └── BuildInfo (빌드 정보 모델)
    │
    ├── renderer
    │   ├── ConsoleRenderer (콘솔 출력)
    │   ├── LogRenderer (로그 파일 출력)
    │   └── HtmlRenderer (HTML 출력)
    │
    ├── template
    │   ├── BannerTemplate (템플릿 인터페이스)
    │   ├── DefaultTemplate (기본 템플릿)
    │   └── CustomTemplate (사용자 정의 템플릿)
    │
    └── detector
        ├── FrameworkDetector (프레임워크 감지)
        └── EnvironmentDetector (환경 감지)
```

## 3. Module Dependencies

- 모든 모듈은 core 모듈에 의존할 수 있음
- core 모듈은 다른 모듈에 의존하지 않음
- 모듈 간 순환 참조는 허용하지 않음
- 각 모듈은 단일 책임 원칙을 따름

## 4. Banner Module Design

### 4.1 Core Components

#### AppBannerUtil
애플리케이션 시작 시 표시되는 배너를 관리하는 메인 클래스입니다.

주요 기능:
- 기본 정보 표시 (애플리케이션 이름, 버전, 빌드 정보, 실행 환경)
- 커스터마이징 옵션 (ASCII 아트 로고, 커스텀 메시지, 테마 색상)
- 다양한 출력 형식 지원 (콘솔, 로그 파일, HTML)
- 배너 프레임워크 자동 감지
- 배너 설정 파일 지원
- 다국어 지원

```java
public class AppBannerUtil {
    private final BannerConfig config;
    private final BannerInfo info;
    private final List<BannerRenderer> renderers;
    
    // 기본 생성자
    public AppBannerUtil() {
        this(new BannerConfig());
    }
    
    // 커스텀 설정 생성자
    public AppBannerUtil(BannerConfig config) {
        this.config = config;
        this.info = new BannerInfo();
        this.renderers = new ArrayList<>();
    }
    
    // 배너 표시
    public void display() {
        for (BannerRenderer renderer : renderers) {
            renderer.render(info);
        }
    }
}
```

#### BannerConfig
배너의 설정을 관리하는 클래스입니다.

```java
public class BannerConfig {
    private boolean showLogo = true;
    private boolean showVersion = true;
    private boolean showBuildInfo = true;
    private boolean showSystemInfo = true;
    private BannerTheme theme = BannerTheme.DEFAULT;
    private String customMessage;
    private String logo;
    private Locale locale = Locale.getDefault();
}
```

### 4.2 Models

#### BannerInfo
배너에 표시될 정보를 담는 모델 클래스입니다.

```java
public class BannerInfo {
    private String appName;
    private String version;
    private BuildInfo buildInfo;
    private SystemInfo systemInfo;
    private Map<String, String> customInfo;
}
```

### 4.3 Renderers

#### BannerRenderer
배너 출력을 담당하는 인터페이스입니다.

```java
public interface BannerRenderer {
    void render(BannerInfo info);
    void setTheme(BannerTheme theme);
    void setLocale(Locale locale);
}
```

### 4.4 Usage Examples

```java
// 기본 사용
AppBannerUtil banner = new AppBannerUtil();
banner.display();

// 커스텀 설정
BannerConfig config = new BannerConfig()
    .setTheme(BannerTheme.COLORFUL)
    .setShowSystemInfo(false)
    .setCustomMessage("Welcome to My App!")
    .setLogo(customLogo);

AppBannerUtil banner = new AppBannerUtil(config);
banner.addRenderer(new ConsoleRenderer());
banner.addRenderer(new LogRenderer());
banner.display();

// HTML 출력
banner.addRenderer(new HtmlRenderer("banner.html"));
```

## 5. Design Principles

1. **단일 책임 원칙 (SRP)**
   - 각 모듈과 클래스는 하나의 책임만 가짐
   - 기능별로 명확한 분리

2. **개방-폐쇄 원칙 (OCP)**
   - 새로운 기능 추가가 기존 코드 수정 없이 가능
   - 인터페이스 기반 설계

3. **의존성 역전 원칙 (DIP)**
   - 구체적인 구현보다 추상화에 의존
   - 모듈 간 결합도 최소화

4. **인터페이스 분리 원칙 (ISP)**
   - 클라이언트는 필요한 인터페이스만 사용
   - 불필요한 의존성 제거

5. **DRY (Don't Repeat Yourself)**
   - 코드 중복 최소화
   - 공통 기능의 재사용성 극대화

## 6. Future Considerations

1. **확장성**
   - 새로운 렌더러 추가 용이
   - 새로운 템플릿 추가 용이
   - 새로운 프레임워크 통합 용이

2. **성능**
   - 렌더링 성능 최적화
   - 메모리 사용량 최적화

3. **유지보수성**
   - 명확한 문서화
   - 테스트 커버리지 유지
   - 코드 품질 관리

4. **사용자 경험**
   - 직관적인 API 설계
   - 풍부한 커스터마이징 옵션
   - 다양한 출력 형식 지원 