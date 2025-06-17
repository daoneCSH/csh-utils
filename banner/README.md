# Banner Module

## Overview
배너 모듈은 애플리케이션 시작 시 콘솔에 배너를 출력하는 기능을 제공합니다.

## Features
- ASCII 아트 지원
- 다양한 테마 지원 (DEFAULT, COLORFUL, DARK, LIGHT, MONOCHROME)
- 상단/하단 구분선으로 시각적 구분
- 한글, 특수문자 등 다양한 문자 지원

## Usage
```java
import io.csh.utils.banner.AppBanner;
import io.csh.utils.banner.art.DefaultAsciiArts;
import io.csh.utils.banner.core.BannerTheme;

AppBanner banner = new AppBanner.Builder()
    .name("MyApp")
    .version("1.0.0")
    .customMessage("Welcome to MyApp!")
    .theme(BannerTheme.COLORFUL)
    .asciiArt(DefaultAsciiArts.SPRING_BOOT)
    .build();

banner.print();
```

## Changelog
See [CHANGELOG.md](CHANGELOG.md) for details. 