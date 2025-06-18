package io.csh.utils.banner.core;

/**
 * 배너의 테두리 스타일을 정의하는 열거형입니다.
 * 
 * 지원되는 테두리 스타일:
 * <ul>
 *     <li><b>simple</b> - 기본 테두리 스타일 (+-|)</li>
 *     <li><b>double</b> - 이중 테두리 스타일 (╔═╗║╚╝)</li>
 *     <li><b>round</b> - 둥근 모서리 테두리 스타일 (╭─╮│╰╯)</li>
 *     <li><b>bold</b> - 굵은 테두리 스타일 (┏━┓┃┗┛)</li>
 *     <li><b>dashed</b> - 점선 테두리 스타일 (┌─┐│└┘)</li>
 *     <li><b>dotted</b> - 점 테두리 스타일 (┌┄┐┆└┘)</li>
 * </ul>
 * 
 * 사용 예시:
 * <pre>
 * AppBanner.builder()
 *     .borderStyle("double")
 *     .build()
 *     .print();
 * </pre>
 */
public enum BorderStyle {
    /**
     * 기본 테두리 스타일
     * <pre>
     * +------------------+
     * |                  |
     * |     Content      |
     * |                  |
     * +------------------+
     * </pre>
     */
    SIMPLE("+-|"),

    /**
     * 이중 테두리 스타일
     * <pre>
     * ╔══════════════════╗
     * ║                  ║
     * ║     Content      ║
     * ║                  ║
     * ╚══════════════════╝
     * </pre>
     */
    DOUBLE("╔═╗║╚╝"),

    /**
     * 둥근 모서리 테두리 스타일
     * <pre>
     * ╭──────────────────╮
     * │                  │
     * │     Content      │
     * │                  │
     * ╰──────────────────╯
     * </pre>
     */
    ROUND("╭─╮│╰╯"),

    /**
     * 굵은 테두리 스타일
     * <pre>
     * ┏━━━━━━━━━━━━━━━━━━┓
     * ┃                  ┃
     * ┃     Content      ┃
     * ┃                  ┃
     * ┗━━━━━━━━━━━━━━━━━━┛
     * </pre>
     */
    BOLD("┏━┓┃┗┛"),

    /**
     * 점선 테두리 스타일
     * <pre>
     * ┌──────────────────┐
     * │                  │
     * │     Content      │
     * │                  │
     * └──────────────────┘
     * </pre>
     */
    DASHED("┌─┐│└┘"),

    /**
     * 점 테두리 스타일
     * <pre>
     * ┌┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┐
     * ┆                  ┆
     * ┆     Content      ┆
     * ┆                  ┆
     * └┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┘
     * </pre>
     */
    DOTTED("┌┄┐┆└┘");

    private final String characters;

    /**
     * BorderStyle을 생성합니다.
     * @param characters 테두리 스타일 문자
     */
    BorderStyle(String characters) {
        this.characters = characters;
    }

    /**
     * 테두리 스타일의 문자를 반환합니다.
     * @return 테두리 스타일 문자
     */
    public String getCharacters() {
        return characters;
    }

    /**
     * 문자열로부터 BorderStyle을 찾습니다.
     * @param style 테두리 스타일 문자열
     * @return BorderStyle 인스턴스
     * @throws IllegalArgumentException 지원하지 않는 스타일인 경우
     */
    public static BorderStyle fromString(String style) {
        try {
            return valueOf(style.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported border style: " + style);
        }
    }
} 