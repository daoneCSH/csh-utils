package io.csh.utils.banner.core;

/**
 * BorderStyle는 배너의 테두리 스타일을 정의합니다.
 * <p>
 * SIMPLE, DOUBLE, ROUNDED, BOLD, DOTTED, DASHED 등 다양한 스타일을 제공합니다.
 * </p>
 */
public enum BorderStyle {
    /**
     * 단순 테두리
     */
    SIMPLE("+", "-", "|"),

    /**
     * 이중 테두리
     */
    DOUBLE("╔", "═", "║"),

    /**
     * 둥근 테두리
     */
    ROUNDED("╭", "─", "│"),

    /**
     * 굵은 테두리
     */
    BOLD("┏", "━", "┃"),

    /**
     * 점선 테두리
     */
    DOTTED("┌", "·", "│"),

    /**
     * 대시 테두리
     */
    DASHED("┌", "┄", "│");

    private final String corner;
    private final String horizontal;
    private final String vertical;

    /**
     * BorderStyle을 생성합니다.
     * @param corner 모서리 문자
     * @param horizontal 수평선 문자
     * @param vertical 수직선 문자
     */
    BorderStyle(String corner, String horizontal, String vertical) {
        this.corner = corner;
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    /**
     * 모서리 문자를 반환합니다.
     *
     * @return 모서리 문자
     */
    public String getCorner() {
        return corner;
    }

    /**
     * 수평선 문자를 반환합니다.
     *
     * @return 수평선 문자
     */
    public String getHorizontal() {
        return horizontal;
    }

    /**
     * 수직선 문자를 반환합니다.
     *
     * @return 수직선 문자
     */
    public String getVertical() {
        return vertical;
    }

    /**
     * 테두리 스타일에 따른 상단 테두리를 생성합니다.
     *
     * @param width 테두리 너비
     * @return 상단 테두리
     */
    public String createTopBorder(int width) {
        return corner + horizontal.repeat(width - 2) + corner;
    }

    /**
     * 테두리 스타일에 따른 하단 테두리를 생성합니다.
     *
     * @param width 테두리 너비
     * @return 하단 테두리
     */
    public String createBottomBorder(int width) {
        return corner + horizontal.repeat(width - 2) + corner;
    }

    /**
     * 테두리 스타일에 따른 좌측 테두리를 생성합니다.
     *
     * @return 좌측 테두리
     */
    public String createLeftBorder() {
        return vertical;
    }

    /**
     * 테두리 스타일에 따른 우측 테두리를 생성합니다.
     *
     * @return 우측 테두리
     */
    public String createRightBorder() {
        return vertical;
    }

    /**
     * 스타일 이름을 반환합니다.
     * @return 스타일 이름
     */
    public String getStyleName() {
        return this.name();
    }
} 