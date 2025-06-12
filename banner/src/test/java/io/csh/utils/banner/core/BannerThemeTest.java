package io.csh.utils.banner.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * BannerTheme 열거형에 대한 테스트 클래스입니다.
 */
class BannerThemeTest {

    @Test
    void testDefaultTheme() {
        String text = "Test Banner";
        String result = BannerTheme.DEFAULT.apply(text);
        assertEquals(text, result, "DEFAULT 테마는 텍스트를 변경하지 않아야 합니다.");
    }

    @Test
    void testColorfulTheme() {
        String text = "Test Banner";
        String result = BannerTheme.COLORFUL.apply(text);
        assertTrue(result.startsWith("\033[1;36m"), "COLORFUL 테마는 청록색 접두사를 가져야 합니다.");
        assertTrue(result.endsWith("\033[0m"), "COLORFUL 테마는 리셋 접미사를 가져야 합니다.");
    }

    @Test
    void testDarkTheme() {
        String text = "Test Banner";
        String result = BannerTheme.DARK.apply(text);
        assertTrue(result.startsWith("\033[1;30m"), "DARK 테마는 회색 접두사를 가져야 합니다.");
        assertTrue(result.endsWith("\033[0m"), "DARK 테마는 리셋 접미사를 가져야 합니다.");
    }

    @Test
    void testLightTheme() {
        String text = "Test Banner";
        String result = BannerTheme.LIGHT.apply(text);
        assertTrue(result.startsWith("\033[1;37m"), "LIGHT 테마는 흰색 접두사를 가져야 합니다.");
        assertTrue(result.endsWith("\033[0m"), "LIGHT 테마는 리셋 접미사를 가져야 합니다.");
    }

    @Test
    void testMonochromeTheme() {
        String text = "Test Banner";
        String result = BannerTheme.MONOCHROME.apply(text);
        assertEquals(text, result, "MONOCHROME 테마는 텍스트를 변경하지 않아야 합니다.");
    }

    @Test
    void testThemePrefixAndSuffix() {
        for (BannerTheme theme : BannerTheme.values()) {
            assertNotNull(theme.getPrefix(), "테마의 접두사는 null이 아니어야 합니다.");
            assertNotNull(theme.getSuffix(), "테마의 접미사는 null이 아니어야 합니다.");
        }
    }
} 