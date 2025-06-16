# AppBanner 단일 클래스 설계 가이드

이 문서는 `csh-utils-banner` 모듈에서 배너 기능 사용 시 `import`가 지나치게 많아지는 문제를 해결하기 위한 설계 가이드를 설명합니다.

---

## 🎯 목적

현재 배너를 사용하려면 다음과 같은 import가 필요합니다:

```java
import io.csh.utils.banner.core.AppBannerUtil;
import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.core.BannerTheme;
import io.csh.utils.banner.detector.DefaultBannerDetector;
import io.csh.utils.banner.model.BannerInfo;
import io.csh.utils.banner.renderer.ConsoleBannerRenderer;
```

➡️ 이를 다음 한 줄로 줄이고자 합니다:

```java
import io.csh.utils.banner.AppBanner;
```

---

## ✅ 해결 방안: Facade 유틸 클래스 생성

### 📦 클래스 위치
```
io.csh.utils.banner.AppBanner
```

### 📄 AppBanner.java 예시

```java
package io.csh.utils.banner;

import io.csh.utils.banner.core.AppBannerUtil;
import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.core.BannerTheme;
import io.csh.utils.banner.detector.DefaultBannerDetector;
import io.csh.utils.banner.model.BannerInfo;
import io.csh.utils.banner.renderer.ConsoleBannerRenderer;

public class AppBanner {

    public static void printDefault() {
        BannerConfig config = new BannerConfig();
        config.setTheme(BannerTheme.DEFAULT);
        BannerInfo info = new DefaultBannerDetector().detect();
        new ConsoleBannerRenderer().render(AppBannerUtil.render(info, config));
    }

    public static void print(BannerTheme theme) {
        BannerConfig config = new BannerConfig();
        config.setTheme(theme);
        BannerInfo info = new DefaultBannerDetector().detect();
        new ConsoleBannerRenderer().render(AppBannerUtil.render(info, config));
    }

    public static void printCustom(BannerInfo info, BannerConfig config) {
        new ConsoleBannerRenderer().render(AppBannerUtil.render(info, config));
    }
}
```

---

## 🔍 사용 예시

```java
import io.csh.utils.banner.AppBanner;

public class Main {
    public static void main(String[] args) {
        AppBanner.printDefault();                  // 기본 테마 출력
        AppBanner.print(BannerTheme.ASCII);        // 특정 테마 출력
    }
}
```

---

## 💡 확장 가능 예시

- `printHtml()` : HTML 기반 배너 출력
- `printToLog()` : Logger로 출력
- `loadFromYaml()` : 외부 설정 연동

---

## 📝 결론

단일 `AppBanner` 클래스를 통해 사용자 API를 단순화하면 사용성과 유지보수가 크게 향상됩니다. 내부 모듈은 숨기고 공개 인터페이스만 유지하는 것이 핵심입니다.
