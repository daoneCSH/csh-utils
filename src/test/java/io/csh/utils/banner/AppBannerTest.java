package io.csh.utils.banner;

import org.junit.jupiter.api.Test;

/**
 * 배너 출력 테스트
 */
public class AppBannerTest {

    @Test
    void testDefaultBanner() {
        // Given
        AppBanner banner = AppBanner.createDefault();

        // When
        banner.print();

        // Then
        // No assertions needed as this is a visual test
    }

    @Test
    void testCustomBanner() {
        // Given
        String customMsg = "Welcome to TestApp!\nEnjoy your experience.";
        String customAsciiArt = """
                ███████╗██████╗ ██╗ ██████╗███████╗
                ██╔════╝██╔══██╗██║██╔════╝██╔════╝
                █████╗  ██████╔╝██║██║     █████╗  
                ██╔══╝  ██╔══██╗██║██║     ██╔══╝  
                ██║     ██║  ██║██║╚██████╗███████╗
                ╚═╝     ╚═╝  ╚═╝╚═╝ ╚═════╝╚══════╝
                """;
        
        AppBanner banner = AppBanner.builder()
            .name("TestApp")
            .version("1.0.0")
            .customMessage(customMsg)
            .customAsciiArt(customAsciiArt)
            .showAsciiArt(true)
            .borderStyle("simple")
            .build();

        // When
        banner.print();

        // Then
        // No assertions needed as this is a visual test
    }
} 