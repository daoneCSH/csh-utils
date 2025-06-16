package io.csh.utils.banner.renderer;

import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.core.BannerTheme;
import io.csh.utils.banner.model.BannerInfo;

/**
 * 콘솔에 배너를 출력하는 렌더러입니다.
 * ANSI 색상 코드를 사용하여 테마를 적용하고, 설정에 따라 다양한 정보를 표시합니다.
 * 
 * <p>주요 기능:
 * <ul>
 *   <li>ANSI 색상 코드를 사용한 테마 적용</li>
 *   <li>로고, 버전, 빌드 정보, 시스템 정보 표시</li>
 *   <li>사용자 정의 메시지 지원</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>
 * BannerConfig config = BannerConfig.defaultConfig();
 * ConsoleBannerRenderer renderer = new ConsoleBannerRenderer(config);
 * renderer.render(bannerInfo);
 * </pre>
 */
public class ConsoleBannerRenderer implements BannerRenderer {
    private final BannerConfig config;

    /**
     * ConsoleBannerRenderer를 생성합니다.
     * 설정에 따라 배너의 표시 방식을 결정합니다.
     *
     * @param config 배너 설정
     * @throws IllegalArgumentException config가 null인 경우
     */
    public ConsoleBannerRenderer(BannerConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("BannerConfig cannot be null");
        }
        this.config = config;
    }

    /**
     * 배너 정보를 콘솔에 출력합니다.
     * 설정에 따라 로고, 버전, 빌드 정보, 시스템 정보를 표시하고,
     * 테마를 적용하여 색상을 지정합니다.
     *
     * @param info 렌더링할 배너 정보
     * @throws IllegalArgumentException info가 null인 경우
     */
    @Override
    public void render(BannerInfo info) {
        if (info == null) {
            throw new IllegalArgumentException("BannerInfo cannot be null");
        }

        StringBuilder banner = new StringBuilder();
        banner.append("\n");

        // 로고 표시
        if (config.isShowLogo() && info.getAppName() != null) {
            banner.append(applyTheme(info.getAppName())).append("\n\n");
        }

        // 버전 정보 표시
        if (config.isShowVersion() && info.getVersion() != null) {
            banner.append("Version: ").append(applyTheme(info.getVersion())).append("\n");
        }

        // 빌드 정보 표시
        if (config.isShowBuildInfo() && info.getBuildTime() != null) {
            banner.append("Build Time: ").append(applyTheme(info.getBuildTime())).append("\n");
        }

        // 시스템 정보 표시
        if (config.isShowSystemInfo()) {
            banner.append("Java Version: ").append(applyTheme(info.getJavaVersion())).append("\n");
            banner.append("OS: ").append(applyTheme(info.getOsName() + " " + info.getOsVersion() + " (" + info.getOsArch() + ")")).append("\n");
        }

        // 커스텀 메시지 표시
        if (info.getCustomMessage() != null) {
            banner.append("\n").append(applyTheme(info.getCustomMessage())).append("\n");
        }

        banner.append("\n");
        System.out.println(banner.toString());
    }

    /**
     * 텍스트에 테마를 적용합니다.
     * 설정된 테마의 ANSI 색상 코드를 사용하여 텍스트의 스타일을 지정합니다.
     *
     * @param text 테마를 적용할 텍스트
     * @return 테마가 적용된 텍스트
     */
    private String applyTheme(String text) {
        return config.getTheme().apply(text);
    }
} 