package io.csh.utils.banner.renderer;

import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.core.BannerTheme;
import io.csh.utils.banner.model.BannerInfo;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ConsoleBannerRenderer 클래스에 대한 테스트 클래스입니다.
 */
class ConsoleBannerRendererTest {

    @Test
    void testRenderWithAllInfo() {
        // 출력을 캡처하기 위한 설정
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // 테스트 데이터 설정
            BannerConfig config = BannerConfig.defaultConfig();
            BannerInfo info = new BannerInfo();
            info.setAppName("Test App");
            info.setVersion("1.0.0");
            info.setBuildTime("2024-03-14");
            info.setCustomMessage("Test Message");

            // 렌더러 실행
            ConsoleBannerRenderer renderer = new ConsoleBannerRenderer(config);
            renderer.render(info);

            // 출력 검증
            String output = outContent.toString();
            assertTrue(output.contains("Test App"), "앱 이름이 출력되어야 합니다.");
            assertTrue(output.contains("Version: 1.0.0"), "버전 정보가 출력되어야 합니다.");
            assertTrue(output.contains("Build Time: 2024-03-14"), "빌드 시간이 출력되어야 합니다.");
            assertTrue(output.contains("Test Message"), "커스텀 메시지가 출력되어야 합니다.");
        } finally {
            // 원래 출력 스트림으로 복구
            System.setOut(originalOut);
        }
    }

    @Test
    void testRenderWithDisabledOptions() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // 모든 표시 옵션을 비활성화
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

            ConsoleBannerRenderer renderer = new ConsoleBannerRenderer(config);
            renderer.render(info);

            String output = outContent.toString();
            assertFalse(output.contains("Test App"), "로고가 비활성화되었으므로 앱 이름이 출력되지 않아야 합니다.");
            assertFalse(output.contains("Version: 1.0.0"), "버전 표시가 비활성화되었으므로 버전 정보가 출력되지 않아야 합니다.");
            assertFalse(output.contains("Build Time: 2024-03-14"), "빌드 정보가 비활성화되었으므로 빌드 시간이 출력되지 않아야 합니다.");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testRenderWithCustomTheme() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            BannerConfig config = new BannerConfig.Builder()
                    .theme(BannerTheme.COLORFUL)
                    .build();

            BannerInfo info = new BannerInfo();
            info.setAppName("Test App");

            ConsoleBannerRenderer renderer = new ConsoleBannerRenderer(config);
            renderer.render(info);

            String output = outContent.toString();
            assertTrue(output.contains("\033[1;36m"), "COLORFUL 테마의 ANSI 코드가 포함되어야 합니다.");
            assertTrue(output.contains("\033[0m"), "리셋 ANSI 코드가 포함되어야 합니다.");
        } finally {
            System.setOut(originalOut);
        }
    }
} 