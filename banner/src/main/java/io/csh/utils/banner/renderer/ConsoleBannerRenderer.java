package io.csh.utils.banner.renderer;

import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.core.BannerTheme;
import io.csh.utils.banner.core.BorderStyle;
import io.csh.utils.banner.model.BannerInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    private final List<String> lines = new ArrayList<>();
    private final List<String> optionLines = new ArrayList<>();

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

        // ASCII 아트 추가
        if (config.isShowAsciiArt() && config.getAsciiArt() != null) {
            lines.add(config.getAsciiArt());
            lines.add(""); // 빈 줄 추가
        }

        // 옵션 정보 수집
        // 애플리케이션 이름 추가
        if (config.isShowLogo()) {
            optionLines.add(info.getAppName());
        }

        // 버전 정보 추가
        if (config.isShowVersion()) {
            optionLines.add("Version: " + info.getVersion());
        }

        // 빌드 정보 추가
        if (config.isShowBuildInfo()) {
            optionLines.add("Build Time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        // 시스템 정보 추가
        if (config.isShowSystemInfo()) {
            optionLines.add("Java Version: " + System.getProperty("java.version"));
            optionLines.add("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        }

        // 사용자 정의 메시지 추가
        if (config.getCustomMessage() != null && !config.getCustomMessage().isEmpty()) {
            optionLines.add("");
            optionLines.add(config.getCustomMessage());
        }

        // ASCII 아트 출력
        for (String line : lines) {
            System.out.println(line);
        }

        // 옵션 정보 출력
        if (config.getBorderStyle() == BorderStyle.NONE) {
            // 테두리 없이 출력
            for (String line : optionLines) {
                System.out.println(line);
            }
        } else {
            // 테두리와 함께 출력
            int maxWidth = optionLines.stream()
                    .mapToInt(String::length)
                    .max()
                    .orElse(0);

            BorderStyle style = config.getBorderStyle();
            String horizontalLine = String.valueOf(style.getHorizontal()).repeat(maxWidth + 4);

            // 상단 테두리
            System.out.println(style.getTopLeft() + horizontalLine + style.getTopRight());

            // 내용
            for (String line : optionLines) {
                if (line.isEmpty()) {
                    // 빈 줄은 그대로 출력
                    System.out.println(style.getVertical() + " ".repeat(maxWidth + 4) + style.getVertical());
                } else {
                    String paddedLine = line + " ".repeat(maxWidth - line.length() + 1);
                    System.out.println(style.getVertical() + " " + paddedLine + "  " + style.getVertical());
                }
            }

            // 하단 테두리
            System.out.println(style.getBottomLeft() + horizontalLine + style.getBottomRight());
        }
    }
} 