package io.csh.utils.banner.core;

/**
 * 배너의 테마를 정의하는 열거형입니다.
 * 각 테마는 ANSI 색상 코드를 사용하여 배너의 스타일을 지정합니다.
 * 
 * <p>지원하는 테마:
 * <ul>
 *   <li>DEFAULT - 기본 테마, 색상 없음</li>
 *   <li>COLORFUL - 화려한 컬러 테마 (청록색)</li>
 *   <li>DARK - 어두운 테마 (회색)</li>
 *   <li>LIGHT - 밝은 테마 (흰색)</li>
 *   <li>MONOCHROME - 흑백 테마</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>
 * String coloredText = BannerTheme.COLORFUL.apply("Hello World");
 * </pre>
 */
public enum BannerTheme {
    /**
     * 기본 테마입니다.
     * 색상 코드를 사용하지 않고 일반 텍스트로 표시됩니다.
     */
    DEFAULT("", ""),

    /**
     * 컬러풀 테마입니다.
     * ANSI 색상 코드를 사용하여 청록색으로 표시됩니다.
     */
    COLORFUL("\033[1;36m", "\033[0m"),

    /**
     * 다크 테마입니다.
     * ANSI 색상 코드를 사용하여 회색으로 표시됩니다.
     */
    DARK("\033[1;30m", "\033[0m"),

    /**
     * 라이트 테마입니다.
     * ANSI 색상 코드를 사용하여 흰색으로 표시됩니다.
     */
    LIGHT("\033[1;37m", "\033[0m"),

    /**
     * 모노크롬 테마입니다.
     * 색상 코드를 사용하지 않고 흑백으로 표시됩니다.
     */
    MONOCHROME("", "");

    private final String prefix;
    private final String suffix;

    /**
     * 테마를 생성합니다.
     *
     * @param prefix ANSI 색상 코드 접두사
     * @param suffix ANSI 색상 코드 접미사
     */
    BannerTheme(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    /**
     * 테마의 ANSI 색상 코드 접두사를 반환합니다.
     * 이 접두사는 텍스트의 시작 부분에 적용됩니다.
     *
     * @return ANSI 색상 코드 접두사
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * 테마의 ANSI 색상 코드 접미사를 반환합니다.
     * 이 접미사는 텍스트의 끝 부분에 적용되어 색상 효과를 초기화합니다.
     *
     * @return ANSI 색상 코드 접미사
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * 테마를 텍스트에 적용합니다.
     * 텍스트의 앞뒤에 ANSI 색상 코드를 추가하여 스타일을 적용합니다.
     *
     * @param text 테마를 적용할 텍스트
     * @return 테마가 적용된 텍스트
     */
    public String apply(String text) {
        return prefix + text + suffix;
    }
} 