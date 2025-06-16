package io.csh.utils.banner.core;

import java.util.Locale;

/**
 * 배너의 설정을 관리하는 클래스입니다.
 * 배너의 표시 여부, 테마, 메시지 등을 설정할 수 있습니다.
 * 
 * <p>주요 설정:
 * <ul>
 *   <li>로고 표시 여부</li>
 *   <li>버전 표시 여부</li>
 *   <li>빌드 정보 표시 여부</li>
 *   <li>시스템 정보 표시 여부</li>
 *   <li>테마 설정</li>
 *   <li>사용자 정의 메시지</li>
 *   <li>로케일 설정</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>
 * BannerConfig config = new BannerConfig.Builder()
 *     .showLogo(true)
 *     .showVersion(true)
 *     .theme(BannerTheme.COLORFUL)
 *     .build();
 * </pre>
 */
public class BannerConfig {
    private final boolean showLogo;
    private final boolean showVersion;
    private final boolean showBuildInfo;
    private final boolean showSystemInfo;
    private final BannerTheme theme;
    private final String customMessage;
    private final String logo;
    private final Locale locale;

    private BannerConfig(Builder builder) {
        this.showLogo = builder.showLogo;
        this.showVersion = builder.showVersion;
        this.showBuildInfo = builder.showBuildInfo;
        this.showSystemInfo = builder.showSystemInfo;
        this.theme = builder.theme;
        this.customMessage = builder.customMessage;
        this.logo = builder.logo;
        this.locale = builder.locale;
    }

    /**
     * 로고 표시 여부를 반환합니다.
     *
     * @return 로고 표시 여부
     */
    public boolean isShowLogo() {
        return showLogo;
    }

    /**
     * 버전 표시 여부를 반환합니다.
     *
     * @return 버전 표시 여부
     */
    public boolean isShowVersion() {
        return showVersion;
    }

    /**
     * 빌드 정보 표시 여부를 반환합니다.
     *
     * @return 빌드 정보 표시 여부
     */
    public boolean isShowBuildInfo() {
        return showBuildInfo;
    }

    /**
     * 시스템 정보 표시 여부를 반환합니다.
     *
     * @return 시스템 정보 표시 여부
     */
    public boolean isShowSystemInfo() {
        return showSystemInfo;
    }

    /**
     * 배너 테마를 반환합니다.
     * 테마는 배너의 색상과 스타일을 결정합니다.
     *
     * @return 현재 적용된 배너 테마
     */
    public BannerTheme getTheme() {
        return theme;
    }

    /**
     * 사용자 정의 메시지를 반환합니다.
     * 이 메시지는 배너에 추가로 표시되는 사용자 정의 텍스트입니다.
     *
     * @return 사용자 정의 메시지
     */
    public String getCustomMessage() {
        return customMessage;
    }

    /**
     * 배너 로고를 반환합니다.
     * 로고는 배너의 상단에 표시되는 텍스트나 ASCII 아트입니다.
     *
     * @return 배너 로고
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 로케일을 반환합니다.
     * 로케일은 배너의 언어와 지역화 설정을 결정합니다.
     *
     * @return 현재 설정된 로케일
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * 기본 설정으로 BannerConfig를 생성합니다.
     *
     * @return 기본 설정이 적용된 BannerConfig
     */
    public static BannerConfig defaultConfig() {
        return new Builder().build();
    }

    /**
     * 설정을 복사하여 새로운 BannerConfig를 생성합니다.
     *
     * @return 현재 설정이 복사된 새로운 BannerConfig
     */
    public BannerConfig copy() {
        return new Builder()
                .showLogo(this.showLogo)
                .showVersion(this.showVersion)
                .showBuildInfo(this.showBuildInfo)
                .showSystemInfo(this.showSystemInfo)
                .theme(this.theme)
                .customMessage(this.customMessage)
                .logo(this.logo)
                .locale(this.locale)
                .build();
    }

    /**
     * 배너 설정을 생성하기 위한 빌더 클래스입니다.
     * 빌더 패턴을 사용하여 유연하고 가독성 있는 설정 생성을 지원합니다.
     */
    public static class Builder {
        private boolean showLogo = true;
        private boolean showVersion = true;
        private boolean showBuildInfo = true;
        private boolean showSystemInfo = true;
        private BannerTheme theme = BannerTheme.DEFAULT;
        private String customMessage;
        private String logo;
        private Locale locale = Locale.getDefault();

        /**
         * 빌더를 생성합니다.
         * 모든 설정은 기본값으로 초기화됩니다.
         */
        public Builder() {
            // 기본값 설정
        }

        /**
         * 로고 표시 여부를 설정합니다.
         * true로 설정하면 배너에 로고가 표시됩니다.
         *
         * @param showLogo 로고 표시 여부
         * @return 빌더 인스턴스
         */
        public Builder showLogo(boolean showLogo) {
            this.showLogo = showLogo;
            return this;
        }

        /**
         * 버전 표시 여부를 설정합니다.
         * true로 설정하면 배너에 애플리케이션 버전이 표시됩니다.
         *
         * @param showVersion 버전 표시 여부
         * @return 빌더 인스턴스
         */
        public Builder showVersion(boolean showVersion) {
            this.showVersion = showVersion;
            return this;
        }

        /**
         * 빌드 정보 표시 여부를 설정합니다.
         * true로 설정하면 배너에 빌드 시간이 표시됩니다.
         *
         * @param showBuildInfo 빌드 정보 표시 여부
         * @return 빌더 인스턴스
         */
        public Builder showBuildInfo(boolean showBuildInfo) {
            this.showBuildInfo = showBuildInfo;
            return this;
        }

        /**
         * 시스템 정보 표시 여부를 설정합니다.
         * true로 설정하면 배너에 Java 버전과 OS 정보가 표시됩니다.
         *
         * @param showSystemInfo 시스템 정보 표시 여부
         * @return 빌더 인스턴스
         */
        public Builder showSystemInfo(boolean showSystemInfo) {
            this.showSystemInfo = showSystemInfo;
            return this;
        }

        /**
         * 배너 테마를 설정합니다.
         * 테마는 배너의 색상과 스타일을 결정합니다.
         *
         * @param theme 배너 테마
         * @return 빌더 인스턴스
         * @throws IllegalArgumentException theme이 null인 경우
         */
        public Builder theme(BannerTheme theme) {
            if (theme == null) {
                throw new IllegalArgumentException("Theme cannot be null");
            }
            this.theme = theme;
            return this;
        }

        /**
         * 배너 로고를 설정합니다.
         * 로고는 배너의 상단에 표시되는 텍스트나 ASCII 아트입니다.
         *
         * @param logo 배너 로고
         * @return 빌더 인스턴스
         */
        public Builder logo(String logo) {
            this.logo = logo;
            return this;
        }

        /**
         * 사용자 정의 메시지를 설정합니다.
         * 이 메시지는 배너에 추가로 표시되는 사용자 정의 텍스트입니다.
         *
         * @param customMessage 사용자 정의 메시지
         * @return 빌더 인스턴스
         */
        public Builder customMessage(String customMessage) {
            this.customMessage = customMessage;
            return this;
        }

        /**
         * 로케일을 설정합니다.
         * 로케일은 배너의 언어와 지역화 설정을 결정합니다.
         *
         * @param locale 로케일
         * @return 빌더 인스턴스
         * @throws IllegalArgumentException locale이 null인 경우
         */
        public Builder locale(Locale locale) {
            if (locale == null) {
                throw new IllegalArgumentException("Locale cannot be null");
            }
            this.locale = locale;
            return this;
        }

        /**
         * 설정된 값으로 BannerConfig를 생성합니다.
         * 필수 설정이 누락된 경우 기본값이 사용됩니다.
         *
         * @return 생성된 BannerConfig
         */
        public BannerConfig build() {
            // 필수 설정 검증
            if (theme == null) {
                theme = BannerTheme.DEFAULT;
            }
            if (locale == null) {
                locale = Locale.getDefault();
            }

            return new BannerConfig(this);
        }
    }
} 