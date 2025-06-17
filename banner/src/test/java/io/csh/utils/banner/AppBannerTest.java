package io.csh.utils.banner;

import io.csh.utils.banner.art.DefaultAsciiArts;
import io.csh.utils.banner.core.BannerTheme;
import io.csh.utils.banner.core.BorderStyle;
import org.junit.jupiter.api.Test;

import java.util.Locale;

/**
 * AppBanner 테스트 클래스입니다.
 */
public class AppBannerTest {

    @Test
    void testDefaultBanner() {
        // 기본 테마로 배너 출력
        AppBanner.printDefault();
    }

    @Test
    void testThemedBanner() {
        // 각 테마별 배너 출력
        AppBanner.print(BannerTheme.DEFAULT);
        AppBanner.print(BannerTheme.COLORFUL);
        AppBanner.print(BannerTheme.DARK);
        AppBanner.print(BannerTheme.LIGHT);
        AppBanner.print(BannerTheme.MONOCHROME);
    }

    @Test
    void testCustomBannerWithSpringBootArt() {
        // Spring Boot 스타일 ASCII 아트와 이중선 테두리 사용
        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .customMessage("Welcome to MyApp!")
            .theme(BannerTheme.COLORFUL)
            .asciiArt(DefaultAsciiArts.SPRING_BOOT)
            .borderStyle(BorderStyle.DOUBLE)
            .build();

        banner.print();
    }

    @Test
    void testCustomBannerWithJavaArt() {
        // Java 스타일 ASCII 아트와 둥근 모서리 테두리 사용
        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .customMessage("Welcome to MyApp!")
            .theme(BannerTheme.COLORFUL)
            .asciiArt(DefaultAsciiArts.MINI)
            .borderStyle(BorderStyle.ROUNDED)
            .build();

        banner.print();
    }

    @Test
    void testCustomBannerWithSimpleArt() {
        // Simple 스타일 ASCII 아트와 굵은 선 테두리 사용
        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .customMessage("Welcome to MyApp!")
            .theme(BannerTheme.COLORFUL)
            .asciiArt(DefaultAsciiArts.SIMPLE)
            .borderStyle(BorderStyle.BOLD)
            .build();

        banner.print();
    }

    @Test
    void testCustomBannerWithMiniArt() {
        // Mini 스타일 ASCII 아트와 단순 테두리 사용
        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .customMessage("Welcome to MyApp!")
            .theme(BannerTheme.COLORFUL)
            .asciiArt(DefaultAsciiArts.MINI)
            .borderStyle(BorderStyle.SIMPLE)
            .build();

        banner.print();
    }

    @Test
    void testCustomBannerWithCustomArt() {
        // 사용자 정의 ASCII 아트와 테두리 없음
        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .customMessage("Welcome to MyApp!")
            .theme(BannerTheme.COLORFUL)
            .asciiArt("""
                █▀█ █▀█ █▀█
                █▀▄ █▀▄ █▀▄
                ▀▀▀ ▀▀▀ ▀▀▀
                """)
            .borderStyle(BorderStyle.NONE)
            .build();

        banner.print();
    }

    @Test
    void testCustomBannerWithKoreanLocale() {
        // 한글 로케일 사용
        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .customMessage("환영합니다!")
            .theme(BannerTheme.COLORFUL)
            .asciiArt(DefaultAsciiArts.SPRING_BOOT)
            .borderStyle(BorderStyle.DOUBLE)
            .locale(Locale.KOREA)
            .build();

        banner.print();
    }
} 