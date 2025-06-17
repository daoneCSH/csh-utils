package io.csh.utils.banner.core;

/**
 * 배너의 테두리 스타일을 정의하는 열거형입니다.
 * 각 스타일은 테두리를 구성하는 문자들을 정의합니다.
 */
public enum BorderStyle {
    /**
     * 테두리를 사용하지 않습니다.
     */
    NONE("", "", "", "", "", ""),

    /**
     * 단순한 테두리 스타일입니다.
     * 기본적인 ASCII 문자를 사용합니다.
     */
    SIMPLE("+", "-", "|", "+", "+", "+"),

    /**
     * 굵은 테두리 스타일입니다.
     * 굵은 ASCII 문자를 사용합니다.
     */
    BOLD("┏", "━", "┃", "┓", "┛", "┗"),

    /**
     * 이중선 테두리 스타일입니다.
     * 이중선 ASCII 문자를 사용합니다.
     */
    DOUBLE("╔", "═", "║", "╗", "╝", "╚"),

    /**
     * 둥근 모서리 테두리 스타일입니다.
     * 둥근 모서리 ASCII 문자를 사용합니다.
     */
    ROUNDED("╭", "─", "│", "╮", "╯", "╰");

    private final String topLeft;
    private final String horizontal;
    private final String vertical;
    private final String topRight;
    private final String bottomRight;
    private final String bottomLeft;

    /**
     * 테두리 스타일을 생성합니다.
     *
     * @param topLeft 왼쪽 상단 모서리 문자
     * @param horizontal 수평선 문자
     * @param vertical 수직선 문자
     * @param topRight 오른쪽 상단 모서리 문자
     * @param bottomRight 오른쪽 하단 모서리 문자
     * @param bottomLeft 왼쪽 하단 모서리 문자
     */
    BorderStyle(String topLeft, String horizontal, String vertical,
               String topRight, String bottomRight, String bottomLeft) {
        this.topLeft = topLeft;
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.topRight = topRight;
        this.bottomRight = bottomRight;
        this.bottomLeft = bottomLeft;
    }

    /**
     * 왼쪽 상단 모서리 문자를 반환합니다.
     *
     * @return 왼쪽 상단 모서리 문자
     */
    public String getTopLeft() {
        return topLeft;
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
     * 오른쪽 상단 모서리 문자를 반환합니다.
     *
     * @return 오른쪽 상단 모서리 문자
     */
    public String getTopRight() {
        return topRight;
    }

    /**
     * 오른쪽 하단 모서리 문자를 반환합니다.
     *
     * @return 오른쪽 하단 모서리 문자
     */
    public String getBottomRight() {
        return bottomRight;
    }

    /**
     * 왼쪽 하단 모서리 문자를 반환합니다.
     *
     * @return 왼쪽 하단 모서리 문자
     */
    public String getBottomLeft() {
        return bottomLeft;
    }
} 