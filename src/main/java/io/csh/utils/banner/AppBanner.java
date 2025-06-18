package io.csh.utils.banner;

import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.core.BannerInfo;
import io.csh.utils.banner.core.BannerRenderer;

/**
 * AppBanner는 BannerInfo와 BannerConfig를 조합하여 콘솔에 배너를 출력하는 최상위 진입점 클래스입니다.
 * <p>
 * 빌더 패턴을 통해 손쉽게 배너 정보를 설정하고, print() 메서드로 콘솔에 출력할 수 있습니다.
 * </p>
 */
public class AppBanner {
    /**
     * BannerInfo
     */
    private final BannerInfo info;
    /**
     * BannerConfig
     */
    private final BannerConfig config;

    /**
     * AppBanner 객체를 생성합니다.
     * @param info BannerInfo
     * @param config BannerConfig
     */
    private AppBanner(BannerInfo info, BannerConfig config) {
        this.info = info;
        this.config = config;
    }

    /**
     * 배너를 콘솔에 출력합니다.
     */
    public void print() {
        System.out.println(new BannerRenderer(config).render(info));
    }

    /**
     * AppBanner 빌더를 반환합니다.
     * @return Builder 인스턴스
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * 기본 설정으로 AppBanner 객체를 생성합니다.
     * @return AppBanner 인스턴스
     */
    public static AppBanner createDefault() {
        return builder().build();
    }

    /**
     * AppBanner 생성을 위한 빌더 클래스입니다.
     */
    public static class Builder {
        /**
         * BannerInfo 빌더
         */
        private final BannerInfo.Builder infoBuilder = BannerInfo.builder();
        /**
         * BannerConfig 빌더
         */
        private boolean showAsciiArt = true;
        private String borderStyle = "SIMPLE";

        /**
         * Builder 객체를 생성합니다.
         */
        public Builder() {
            this.infoBuilder.build();
        }

        /**
         * 이름을 설정합니다.
         * @param name 애플리케이션 이름
         * @return Builder
         */
        public Builder name(String name) {
            this.infoBuilder.name(name);
            return this;
        }

        /**
         * 버전을 설정합니다.
         * @param version 애플리케이션 버전
         * @return Builder
         */
        public Builder version(String version) {
            this.infoBuilder.version(version);
            return this;
        }

        /**
         * 커스텀 메시지를 설정합니다.
         * @param message 커스텀 메시지
         * @return Builder
         */
        public Builder customMessage(String message) {
            this.infoBuilder.customMessage(message);
            return this;
        }

        /**
         * 커스텀 ASCII 아트를 설정합니다.
         * @param asciiArt 커스텀 ASCII 아트
         * @return Builder
         */
        public Builder customAsciiArt(String asciiArt) {
            this.infoBuilder.customAsciiArt(asciiArt);
            return this;
        }

        /**
         * ASCII 아트 표시 여부를 설정합니다.
         * @param show true: 표시, false: 미표시
         * @return Builder
         */
        public Builder showAsciiArt(boolean show) {
            this.showAsciiArt = show;
            return this;
        }

        /**
         * 테두리 스타일을 설정합니다.
         * @param style 테두리 스타일
         * @return Builder
         */
        public Builder borderStyle(String style) {
            this.borderStyle = style;
            return this;
        }

        /**
         * AppBanner 객체를 생성합니다.
         * @return AppBanner 인스턴스
         */
        public AppBanner build() {
            BannerConfig config = new BannerConfig.Builder()
                    .showAsciiArt(showAsciiArt)
                    .borderStyle(borderStyle)
                    .build();
            return new AppBanner(infoBuilder.build(), config);
        }
    }
}