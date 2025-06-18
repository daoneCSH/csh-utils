package io.csh.utils.banner.core;

/**
 * BannerConfig는 배너의 표시 설정(테두리 스타일, ASCII 아트 표시 여부 등)을 관리합니다.
 * <p>
 * 배너의 시각적 스타일을 제어하는 역할만 담당합니다.
 * </p>
 */
public class BannerConfig {
    /**
     * ASCII 아트 표시 여부
     */
    private final boolean showAsciiArt;
    /**
     * 테두리 스타일
     */
    private final String borderStyle;

    /**
     * BannerConfig 객체를 생성합니다.
     * @param showAsciiArt ASCII 아트 표시 여부
     * @param borderStyle 테두리 스타일
     */
    private BannerConfig(boolean showAsciiArt, String borderStyle) {
        this.showAsciiArt = showAsciiArt;
        this.borderStyle = borderStyle;
    }

    /**
     * ASCII 아트 표시 여부를 반환합니다.
     * @return true: 표시, false: 미표시
     */
    public boolean isShowAsciiArt() {
        return showAsciiArt;
    }

    /**
     * 테두리 스타일을 반환합니다.
     * @return BorderStyle
     */
    public String getBorderStyle() {
        return borderStyle;
    }

    /**
     * BannerConfig의 빌더를 반환합니다.
     * @return Builder 인스턴스
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * BannerConfig 생성을 위한 빌더 클래스입니다.
     */
    public static class Builder {
        private boolean showAsciiArt = true;
        private String borderStyle = "simple";

        /**
         * BannerConfig.Builder를 생성합니다.
         */
        public Builder() {
        }

        /**
         * ASCII 아트 표시 여부를 설정합니다.
         * @param showAsciiArt true: 표시, false: 미표시
         * @return Builder 인스턴스
         */
        public Builder showAsciiArt(boolean showAsciiArt) {
            this.showAsciiArt = showAsciiArt;
            return this;
        }

        /**
         * 테두리 스타일을 설정합니다.
         * @param borderStyle 테두리 스타일 ("simple", "double", "round", "bold", "dashed", "dotted")
         * @return Builder 인스턴스
         */
        public Builder borderStyle(String borderStyle) {
            this.borderStyle = borderStyle;
            return this;
        }

        /**
         * BannerConfig 객체를 생성합니다.
         * @return BannerConfig 인스턴스
         */
        public BannerConfig build() {
            return new BannerConfig(showAsciiArt, borderStyle);
        }
    }
} 