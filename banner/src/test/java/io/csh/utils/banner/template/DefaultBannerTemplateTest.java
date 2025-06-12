package io.csh.utils.banner.template;

import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.model.BannerInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * DefaultBannerTemplate 클래스에 대한 테스트 클래스입니다.
 */
class DefaultBannerTemplateTest {

    @Test
    void testApplyWithAllInfo() {
        BannerConfig config = BannerConfig.defaultConfig();
        BannerInfo info = new BannerInfo();
        info.setAppName("Test App");
        info.setVersion("1.0.0");
        info.setBuildTime("2024-03-14");
        info.setCustomMessage("Test Message");

        DefaultBannerTemplate template = new DefaultBannerTemplate(config);
        String result = template.apply(info);

        assertTrue(result.contains("Test App"), "앱 이름이 포함되어야 합니다.");
        assertTrue(result.contains("Version: 1.0.0"), "버전 정보가 포함되어야 합니다.");
        assertTrue(result.contains("Build Time: 2024-03-14"), "빌드 시간이 포함되어야 합니다.");
        assertTrue(result.contains("Test Message"), "커스텀 메시지가 포함되어야 합니다.");
    }

    @Test
    void testApplyWithDisabledOptions() {
        BannerConfig config = new BannerConfig.Builder()
                .showLogo(false)
                .showVersion(false)
                .showBuildInfo(false)
                .showSystemInfo(false)
                .build();

        BannerInfo info = new BannerInfo();
        info.setAppName("Test App");
        info.setVersion("1.0.0");
        info.setBuildTime("2024-03-14");

        DefaultBannerTemplate template = new DefaultBannerTemplate(config);
        String result = template.apply(info);

        assertFalse(result.contains("Test App"), "로고가 비활성화되었으므로 앱 이름이 포함되지 않아야 합니다.");
        assertFalse(result.contains("Version: 1.0.0"), "버전 표시가 비활성화되었으므로 버전 정보가 포함되지 않아야 합니다.");
        assertFalse(result.contains("Build Time: 2024-03-14"), "빌드 정보가 비활성화되었으므로 빌드 시간이 포함되지 않아야 합니다.");
    }

    @Test
    void testGetName() {
        DefaultBannerTemplate template = new DefaultBannerTemplate(BannerConfig.defaultConfig());
        assertEquals("Default", template.getName(), "템플릿 이름은 'Default'여야 합니다.");
    }

    @Test
    void testGetDescription() {
        DefaultBannerTemplate template = new DefaultBannerTemplate(BannerConfig.defaultConfig());
        assertNotNull(template.getDescription(), "템플릿 설명은 null이 아니어야 합니다.");
        assertTrue(template.getDescription().length() > 0, "템플릿 설명은 비어있지 않아야 합니다.");
    }

    @Test
    void testIsValid() {
        DefaultBannerTemplate template = new DefaultBannerTemplate(BannerConfig.defaultConfig());
        assertTrue(template.isValid(), "기본 템플릿은 항상 유효해야 합니다.");
    }
} 