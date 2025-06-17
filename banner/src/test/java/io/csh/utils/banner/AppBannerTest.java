package io.csh.utils.banner;

import io.csh.utils.banner.art.DefaultAsciiArts;
import io.csh.utils.banner.core.BannerTheme;
import io.csh.utils.banner.core.BorderStyle;
import org.junit.jupiter.api.Test;

import java.util.Locale;

/**
 * AppBanner 테스트 클래스입니다.
 */
class AppBannerTest {

    @Test
    void testDefaultBanner() {
        // 기본 설정으로 배너 출력 (BASIC ASCII 아트와 SIMPLE 테두리)
        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .build();

        banner.print();
    }

    @Test
    void testThemedBanner() {
        // 컬러풀 테마로 배너 출력
        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .theme(BannerTheme.COLORFUL)
            .build();

        banner.print();
    }

    @Test
    void testBannerWithNoAsciiArt() {
        // ASCII 아트 없이 배너 출력
        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .showAsciiArt(false)
            .build();

        banner.print();
    }

    @Test
    void testBannerWithNoBorder() {
        // 테두리 없이 배너 출력
        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .borderStyle(BorderStyle.NONE)
            .build();

        banner.print();
    }

    @Test
    void testBannerWithCustomAsciiArt() {
        // 사용자 정의 ASCII 아트로 배너 출력
        String customArt = """
            ███████╗██╗   ██╗███████╗████████╗███████╗███╗   ███╗
            ██╔════╝╚██╗ ██╔╝██╔════╝╚══██╔══╝██╔════╝████╗ ████║
            ███████╗ ╚████╔╝ █████╗     ██║   █████╗  ██╔████╔██║
            ╚════██║  ╚██╔╝  ██╔══╝     ██║   ██╔══╝  ██║╚██╔╝██║
            ███████║   ██║   ███████╗   ██║   ███████╗██║ ╚═╝ ██║
            ╚══════╝   ╚═╝   ╚══════╝   ╚═╝   ╚══════╝╚═╝     ╚═╝
            """;

        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .asciiArt(customArt)
            .build();

        banner.print();
    }

    @Test
    void testBannerWithAllOptions() {
        // 모든 옵션을 포함한 배너 출력
        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .theme(BannerTheme.COLORFUL)
            .borderStyle(BorderStyle.DOUBLE)
            .customMessage("Welcome to MyApp!")
            .locale(Locale.KOREAN)
            .build();

        banner.print();
    }

    @Test
    void testBannerWithMinimalInfo() {
        // 최소한의 정보만 포함한 배너 출력
        AppBanner banner = new AppBanner.Builder()
            .name("MyApp")
            .version("1.0.0")
            .showBuildInfo(false)
            .showSystemInfo(false)
            .build();

        banner.print();
    }
} 