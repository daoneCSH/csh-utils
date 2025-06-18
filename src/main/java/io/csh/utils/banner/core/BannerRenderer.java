package io.csh.utils.banner.core;

import java.util.ArrayList;
import java.util.List;
import io.csh.utils.banner.art.DefaultAsciiArts;

/**
 * BannerRenderer는 BannerInfo와 BannerConfig를 조합하여 콘솔에 배너를 출력하는 역할을 합니다.
 * <p>
 * 테두리 스타일, ASCII 아트, 애플리케이션 정보, 커스텀 메시지 등을 포함한 배너를 생성합니다.
 * </p>
 */
public class BannerRenderer {
    private static final int MIN_WIDTH = 40;

    private final BannerConfig config;

    /**
     * BannerRenderer 객체를 생성합니다.
     * @param config BannerConfig
     */
    public BannerRenderer(BannerConfig config) {
        this.config = config;
    }

    /**
     * BannerInfo와 BannerConfig를 조합하여 배너 문자열을 생성합니다.
     * @param info BannerInfo
     * @return 배너 문자열
     */
    public String render(BannerInfo info) {
        List<String> lines = new ArrayList<>();
        
        // 1. Custom ASCII art (if exists)
        if (info.getCustomAsciiArt() != null && !info.getCustomAsciiArt().isEmpty()) {
            for (String artLine : info.getCustomAsciiArt().split("\\n")) {
                lines.add(artLine);
            }
        }

        // 2. Default ASCII art (if enabled)
        if (config.isShowAsciiArt()) {
            for (String artLine : DefaultAsciiArts.DEFAULT.split("\n")) {
                lines.add(artLine);
            }
        }

        // 3. Application info
        lines.add("Application: " + info.getName() + " v" + info.getVersion());
        lines.add("Build Time: " + info.getBuildTime());
        lines.add("Java Version: " + info.getJavaVersion());
        lines.add("OS: " + info.getOsInfo());

        // 4. Custom message (if exists)
        if (info.getCustomMessage() != null && !info.getCustomMessage().isEmpty()) {
            for (String msgLine : info.getCustomMessage().split("\\n")) {
                lines.add(msgLine);
            }
        }

        // 5. 테두리 길이 계산
        int maxLen = lines.stream().mapToInt(BannerRenderer::visibleLength).max().orElse(0);
        int width = Math.max(maxLen, MIN_WIDTH);

        // 6. 테두리와 내용 출력
        StringBuilder result = new StringBuilder();
        result.append(borderLine("top", width, config.getBorderStyle())).append("\n");
        for (String line : lines) {
            result.append(borderLine("side", width, config.getBorderStyle(), line)).append("\n");
        }
        result.append(borderLine("bottom", width, config.getBorderStyle()));

        return result.toString();
    }

    private static int visibleLength(String s) {
        return s == null ? 0 : s.replaceAll("\\e\\[[;\\d]*m", "").length();
    }

    /**
     * 테두리 라인을 생성합니다.
     * @param type 테두리 타입
     * @param width 라인 길이
     * @param style 테두리 스타일
     * @return 테두리 문자열
     */
    private String borderLine(String type, int width, String style) {
        switch (style) {
            case "double":
                if (type.equals("top")) return "╔" + repeat("═", width + 2) + "╗";
                if (type.equals("bottom")) return "╚" + repeat("═", width + 2) + "╝";
                break;
            case "rounded":
                if (type.equals("top")) return "╭" + repeat("─", width + 2) + "╮";
                if (type.equals("bottom")) return "╰" + repeat("─", width + 2) + "╯";
                break;
            case "bold":
                if (type.equals("top")) return "┏" + repeat("━", width + 2) + "┓";
                if (type.equals("bottom")) return "┗" + repeat("━", width + 2) + "┛";
                break;
            case "dotted":
                if (type.equals("top")) return "┌" + repeat("·", width + 2) + "┐";
                if (type.equals("bottom")) return "└" + repeat("·", width + 2) + "┘";
                break;
            case "dashed":
                if (type.equals("top")) return "┌" + repeat("-", width + 2) + "┐";
                if (type.equals("bottom")) return "└" + repeat("-", width + 2) + "┘";
                break;
            default:
                if (type.equals("top")) return "┌" + repeat("─", width + 2) + "┐";
                if (type.equals("bottom")) return "└" + repeat("─", width + 2) + "┘";
        }
        return "";
    }

    /**
     * 내용 라인을 테두리로 감쌉니다.
     * @param content 내용
     * @param width 전체 길이
     * @param style 테두리 스타일
     * @return 테두리로 감싼 문자열
     */
    private String borderLine(String type, int width, String style, String content) {
        String pad = repeat(" ", width - visibleLength(content));
        switch (style) {
            case "double": return "║ " + content + pad + " ║";
            case "rounded": return "│ " + content + pad + " │";
            case "bold": return "┃ " + content + pad + " ┃";
            case "dotted": return "│ " + content + pad + " │";
            case "dashed": return "| " + content + pad + " |";
            default: return "│ " + content + pad + " │";
        }
    }

    private static String repeat(String s, int n) {
        return s.repeat(Math.max(0, n));
    }
} 