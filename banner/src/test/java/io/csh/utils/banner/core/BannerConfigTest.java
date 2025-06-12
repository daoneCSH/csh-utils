package io.csh.utils.banner.core;

import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

/**
 * BannerConfig 클래스에 대한 테스트 클래스입니다.
 */
class BannerConfigTest {

    @Test
    void testDefaultConfig() {
        BannerConfig config = BannerConfig.defaultConfig();
        
        assertTrue(config.isShowLogo(), "기본 설정에서 로고 표시는 true여야 합니다.");
        assertTrue(config.isShowVersion(), "기본 설정에서 버전 표시는 true여야 합니다.");
        assertTrue(config.isShowBuildInfo(), "기본 설정에서 빌드 정보 표시는 true여야 합니다.");
        assertTrue(config.isShowSystemInfo(), "기본 설정에서 시스템 정보 표시는 true여야 합니다.");
        assertEquals(BannerTheme.DEFAULT, config.getTheme(), "기본 테마는 DEFAULT여야 합니다.");
        assertEquals(Locale.getDefault(), config.getLocale(), "기본 로케일은 시스템 기본값이어야 합니다.");
    }

    @Test
    void testCustomConfig() {
        BannerConfig config = new BannerConfig.Builder()
                .showLogo(false)
                .showVersion(false)
                .showBuildInfo(false)
                .showSystemInfo(false)
                .theme(BannerTheme.COLORFUL)
                .customMessage("Custom Message")
                .logo("Custom Logo")
                .locale(Locale.US)
                .build();

        assertFalse(config.isShowLogo(), "로고 표시가 false로 설정되어야 합니다.");
        assertFalse(config.isShowVersion(), "버전 표시가 false로 설정되어야 합니다.");
        assertFalse(config.isShowBuildInfo(), "빌드 정보 표시가 false로 설정되어야 합니다.");
        assertFalse(config.isShowSystemInfo(), "시스템 정보 표시가 false로 설정되어야 합니다.");
        assertEquals(BannerTheme.COLORFUL, config.getTheme(), "테마가 COLORFUL로 설정되어야 합니다.");
        assertEquals("Custom Message", config.getCustomMessage(), "커스텀 메시지가 설정되어야 합니다.");
        assertEquals("Custom Logo", config.getLogo(), "커스텀 로고가 설정되어야 합니다.");
        assertEquals(Locale.US, config.getLocale(), "로케일이 US로 설정되어야 합니다.");
    }

    @Test
    void testCopy() {
        BannerConfig original = new BannerConfig.Builder()
                .showLogo(false)
                .showVersion(false)
                .theme(BannerTheme.COLORFUL)
                .customMessage("Original Message")
                .build();

        BannerConfig copy = original.copy();

        assertNotSame(original, copy, "복사본은 원본과 다른 객체여야 합니다.");
        assertEquals(original.isShowLogo(), copy.isShowLogo(), "로고 표시 설정이 복사되어야 합니다.");
        assertEquals(original.isShowVersion(), copy.isShowVersion(), "버전 표시 설정이 복사되어야 합니다.");
        assertEquals(original.getTheme(), copy.getTheme(), "테마가 복사되어야 합니다.");
        assertEquals(original.getCustomMessage(), copy.getCustomMessage(), "커스텀 메시지가 복사되어야 합니다.");
    }
} 