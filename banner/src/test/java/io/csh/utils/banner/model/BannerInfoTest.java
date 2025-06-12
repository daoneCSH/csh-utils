package io.csh.utils.banner.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * BannerInfo 클래스에 대한 테스트 클래스입니다.
 */
class BannerInfoTest {

    @Test
    void testDefaultConstructor() {
        BannerInfo info = new BannerInfo();
        
        assertNotNull(info.getBuildTime(), "빌드 시간은 null이 아니어야 합니다.");
        assertNotNull(info.getJavaVersion(), "Java 버전은 null이 아니어야 합니다.");
        assertNotNull(info.getOsName(), "OS 이름은 null이 아니어야 합니다.");
        assertNotNull(info.getOsVersion(), "OS 버전은 null이 아니어야 합니다.");
        assertNotNull(info.getOsArch(), "OS 아키텍처는 null이 아니어야 합니다.");
    }

    @Test
    void testSettersAndGetters() {
        BannerInfo info = new BannerInfo();
        
        info.setAppName("Test App");
        assertEquals("Test App", info.getAppName(), "앱 이름이 올바르게 설정되어야 합니다.");

        info.setVersion("1.0.0");
        assertEquals("1.0.0", info.getVersion(), "버전이 올바르게 설정되어야 합니다.");

        info.setBuildTime("2024-03-14");
        assertEquals("2024-03-14", info.getBuildTime(), "빌드 시간이 올바르게 설정되어야 합니다.");

        info.setCustomMessage("Test Message");
        assertEquals("Test Message", info.getCustomMessage(), "커스텀 메시지가 올바르게 설정되어야 합니다.");
    }

    @Test
    void testUpdate() {
        BannerInfo original = new BannerInfo();
        original.setAppName("Original App");
        original.setVersion("1.0.0");
        original.setBuildTime("2024-03-14");
        original.setCustomMessage("Original Message");

        BannerInfo update = new BannerInfo();
        update.setAppName("Updated App");
        update.setVersion("2.0.0");
        update.setCustomMessage("Updated Message");

        original.update(update);

        assertEquals("Updated App", original.getAppName(), "앱 이름이 업데이트되어야 합니다.");
        assertEquals("2.0.0", original.getVersion(), "버전이 업데이트되어야 합니다.");
        assertEquals("2024-03-14", original.getBuildTime(), "빌드 시간은 변경되지 않아야 합니다.");
        assertEquals("Updated Message", original.getCustomMessage(), "커스텀 메시지가 업데이트되어야 합니다.");
    }

    @Test
    void testUpdateWithNullValues() {
        BannerInfo original = new BannerInfo();
        original.setAppName("Original App");
        original.setVersion("1.0.0");
        original.setBuildTime("2024-03-14");
        original.setCustomMessage("Original Message");

        BannerInfo update = new BannerInfo();
        update.setAppName(null);
        update.setVersion(null);
        update.setCustomMessage(null);

        original.update(update);

        assertEquals("Original App", original.getAppName(), "null 값으로 업데이트 시 앱 이름은 변경되지 않아야 합니다.");
        assertEquals("1.0.0", original.getVersion(), "null 값으로 업데이트 시 버전은 변경되지 않아야 합니다.");
        assertEquals("Original Message", original.getCustomMessage(), "null 값으로 업데이트 시 커스텀 메시지는 변경되지 않아야 합니다.");
    }
} 