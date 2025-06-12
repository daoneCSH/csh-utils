package io.csh.utils.banner.detector;

import io.csh.utils.banner.model.BannerInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * DefaultBannerDetector 클래스에 대한 테스트 클래스입니다.
 */
class DefaultBannerDetectorTest {

    @Test
    void testDetect() {
        DefaultBannerDetector detector = new DefaultBannerDetector();
        BannerInfo info = detector.detect();

        assertNotNull(info, "감지된 배너 정보는 null이 아니어야 합니다.");
        assertNotNull(info.getJavaVersion(), "Java 버전은 null이 아니어야 합니다.");
        assertNotNull(info.getOsName(), "OS 이름은 null이 아니어야 합니다.");
        assertNotNull(info.getOsVersion(), "OS 버전은 null이 아니어야 합니다.");
        assertNotNull(info.getOsArch(), "OS 아키텍처는 null이 아니어야 합니다.");
    }

    @Test
    void testDetectAndUpdate() {
        DefaultBannerDetector detector = new DefaultBannerDetector();
        
        // 초기 정보 설정
        BannerInfo original = new BannerInfo();
        original.setAppName("Original App");
        original.setVersion("1.0.0");
        original.setBuildTime("2024-03-14");
        original.setCustomMessage("Original Message");

        // 감지 및 업데이트
        BannerInfo updated = detector.detectAndUpdate(original);

        // 원본 객체가 업데이트되었는지 확인
        assertSame(original, updated, "업데이트된 객체는 원본 객체와 동일해야 합니다.");
        
        // 시스템 정보가 설정되었는지 확인
        assertNotNull(updated.getJavaVersion(), "Java 버전이 설정되어야 합니다.");
        assertNotNull(updated.getOsName(), "OS 이름이 설정되어야 합니다.");
        assertNotNull(updated.getOsVersion(), "OS 버전이 설정되어야 합니다.");
        assertNotNull(updated.getOsArch(), "OS 아키텍처가 설정되어야 합니다.");

        // 기존 정보가 유지되었는지 확인
        assertEquals("Original App", updated.getAppName(), "앱 이름이 유지되어야 합니다.");
        assertEquals("1.0.0", updated.getVersion(), "버전이 유지되어야 합니다.");
        assertEquals("2024-03-14", updated.getBuildTime(), "빌드 시간이 유지되어야 합니다.");
        assertEquals("Original Message", updated.getCustomMessage(), "커스텀 메시지가 유지되어야 합니다.");
    }

    @Test
    void testDetectAndUpdateWithNullInfo() {
        DefaultBannerDetector detector = new DefaultBannerDetector();
        BannerInfo info = detector.detectAndUpdate(null);

        assertNotNull(info, "null 입력 시 새로운 BannerInfo 객체가 생성되어야 합니다.");
        assertNotNull(info.getJavaVersion(), "Java 버전이 설정되어야 합니다.");
        assertNotNull(info.getOsName(), "OS 이름이 설정되어야 합니다.");
        assertNotNull(info.getOsVersion(), "OS 버전이 설정되어야 합니다.");
        assertNotNull(info.getOsArch(), "OS 아키텍처가 설정되어야 합니다.");
    }

    @Test
    void testIsValid() {
        DefaultBannerDetector detector = new DefaultBannerDetector();
        assertTrue(detector.isValid(), "기본 감지기는 항상 유효해야 합니다.");
    }
} 