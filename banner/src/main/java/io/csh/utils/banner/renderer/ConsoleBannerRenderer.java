package io.csh.utils.banner.renderer;

import io.csh.utils.banner.core.BannerConfig;
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

        StringBuilder content = new StringBuilder();

        // ASCII 아트 표시
        if (config.getAsciiArt() != null) {
            content.append(config.getAsciiArt()).append("\n");
        }

        // 로고 표시
        if (config.isShowLogo() && info.getAppName() != null) {
            content.append(info.getAppName()).append("\n\n");
        }

        // 버전 정보 표시
        if (config.isShowVersion() && info.getVersion() != null) {
            content.append("Version: ").append(info.getVersion()).append("\n");
        }

        // 빌드 정보 표시
        if (config.isShowBuildInfo() && info.getBuildTime() != null) {
            content.append("Build Time: ").append(info.getBuildTime()).append("\n");
        }

        // 시스템 정보 표시
        if (config.isShowSystemInfo()) {
            content.append("Java Version: ").append(info.getJavaVersion()).append("\n");
            content.append("OS: ")
                   .append(info.getOsName()).append(" ")
                   .append(info.getOsVersion()).append(" (")
                   .append(info.getOsArch()).append(")\n");
        }

        // 커스텀 메시지 표시
        if (info.getCustomMessage() != null) {
            content.append("\n").append(info.getCustomMessage()).append("\n");
        }

        // 각 줄을 배열로 분리
        String[] lines = content.toString().split("\n");
        int maxWidth = 0;
        for (String line : lines) {
            int width = getDisplayWidth(line);
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        // 상단/하단 구분선 생성
        String border = repeatChar('━', maxWidth);

        // 출력
        StringBuilder banner = new StringBuilder();
        banner.append(border).append("\n");
        for (String line : lines) {
            banner.append(line).append("\n");
        }
        banner.append(border);

        System.out.println(banner.toString());
    }

    // 콘솔에서 실제 표시되는 폭을 계산 (간단 버전)
    private int getDisplayWidth(String s) {
        int width = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            width += (Character.isIdeographic(c) || c > 0xFF) ? 2 : 1;
        }
        return width;
    }

    private String repeatChar(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
} 