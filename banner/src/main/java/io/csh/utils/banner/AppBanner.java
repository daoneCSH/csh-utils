package io.csh.utils.banner;

import io.csh.utils.banner.art.DefaultAsciiArts;
import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.core.BannerTheme;
import io.csh.utils.banner.core.BorderStyle;
import io.csh.utils.banner.model.BannerInfo;
import io.csh.utils.banner.renderer.BannerRenderer;
import io.csh.utils.banner.renderer.ConsoleBannerRenderer;

import java.util.Locale;

/**
 * 애플리케이션 배너를 출력하기 위한 파사드 클래스입니다.
 * 복잡한 내부 구현을 숨기고 간단한 API를 제공합니다.
 * 
 * <p>주요 기능:
 * <ul>
 *   <li>기본 테마로 배너 출력</li>
 *   <li>특정 테마로 배너 출력</li>
 *   <li>사용자 정의 배너 정보와 설정으로 배너 출력</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>
 * // 1. 기본 테마로 배너 출력
 * AppBanner.printDefault();
 * 
 * // 2. 특정 테마로 배너 출력
 * AppBanner.printThemed(BannerTheme.COLORFUL);  // 컬러풀 테마
 * AppBanner.printThemed(BannerTheme.DARK);      // 다크 테마
 * AppBanner.printThemed(BannerTheme.LIGHT);     // 라이트 테마
 * 
 * // 3. Builder를 사용한 커스텀 배너 출력
 * AppBanner banner = new AppBanner.Builder()
 *     .name("MyApp")
 *     .version("1.0.0")
 *     .customMessage("Welcome to MyApp!")
 *     .theme(BannerTheme.COLORFUL)
 *     .locale(Locale.KOREA)
 *     .build();
 * 
 * banner.print();
 * </pre>
 * 
 * <p>배너 정보는 다음과 같은 방법으로 자동으로 감지됩니다:
 * <ul>
 *   <li>JAR 매니페스트에서 애플리케이션 정보 감지</li>
 *   <li>시스템 속성에서 Java 버전, OS 정보 감지</li>
 *   <li>application.properties 파일에서 추가 정보 감지</li>
 * </ul>
 */
public class AppBanner {
    private final BannerConfig config;
    private final BannerRenderer renderer;
    private final BannerInfo info;

    private AppBanner(Builder builder) {
        this.config = builder.config;
        this.renderer = new ConsoleBannerRenderer(config);
        this.info = new BannerInfo(
            builder.name,
            builder.version,
            builder.buildTime,
            builder.javaVersion,
            builder.osName,
            builder.osVersion,
            builder.osArch,
            builder.customMessage
        );
    }

    /**
     * 기본 배너를 출력합니다.
     */
    public static void printDefault() {
        AppBanner banner = new Builder()
            .name("MyApp")
            .version("1.0.0")
            .build();
        banner.print();
    }

    /**
     * 테마가 적용된 배너를 출력합니다.
     *
     * @param theme 적용할 테마
     */
    public static void printThemed(BannerTheme theme) {
        AppBanner banner = new Builder()
            .name("MyApp")
            .version("1.0.0")
            .theme(theme)
            .build();
        banner.print();
    }

    /**
     * 배너를 출력합니다.
     */
    public void print() {
        renderer.render(info);
    }

    /**
     * Builder class for creating AppBanner instances.
     */
    public static class Builder {
        private BannerConfig config = BannerConfig.defaultConfig();
        private String name;
        private String version;
        private String buildTime;
        private String javaVersion;
        private String osName;
        private String osVersion;
        private String osArch;
        private String customMessage;
        private String asciiArt;
        private BorderStyle borderStyle = BorderStyle.SIMPLE;

        /**
         * Creates a new Builder instance.
         */
        public Builder() {
            // 기본 설정
            config = BannerConfig.builder()
                .showLogo(true)
                .showVersion(true)
                .showBuildInfo(true)
                .showSystemInfo(true)
                .theme(BannerTheme.DEFAULT)
                .asciiArt(DefaultAsciiArts.BASIC)
                .borderStyle(BorderStyle.SIMPLE)
                .showAsciiArt(true)
                .build();
        }

        /**
         * Sets the ASCII art for the banner.
         *
         * @param name 애플리케이션 이름
         * @return Builder 인스턴스
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * 애플리케이션 버전을 설정합니다.
         *
         * @param version 애플리케이션 버전
         * @return Builder 인스턴스
         */
        public Builder version(String version) {
            this.version = version;
            return this;
        }

        /**
         * 빌드 시간을 설정합니다.
         *
         * @param buildTime 빌드 시간
         * @return Builder 인스턴스
         */
        public Builder buildTime(String buildTime) {
            this.buildTime = buildTime;
            return this;
        }

        /**
         * Java 버전을 설정합니다.
         *
         * @param javaVersion Java 버전
         * @return Builder 인스턴스
         */
        public Builder javaVersion(String javaVersion) {
            this.javaVersion = javaVersion;
            return this;
        }

        /**
         * 운영체제 이름을 설정합니다.
         *
         * @param osName 운영체제 이름
         * @return Builder 인스턴스
         */
        public Builder osName(String osName) {
            this.osName = osName;
            return this;
        }

        /**
         * 운영체제 버전을 설정합니다.
         *
         * @param osVersion 운영체제 버전
         * @return Builder 인스턴스
         */
        public Builder osVersion(String osVersion) {
            this.osVersion = osVersion;
            return this;
        }

        /**
         * 운영체제 아키텍처를 설정합니다.
         *
         * @param osArch 운영체제 아키텍처
         * @return Builder 인스턴스
         */
        public Builder osArch(String osArch) {
            this.osArch = osArch;
            return this;
        }

        /**
         * 사용자 정의 메시지를 설정합니다.
         *
         * @param customMessage 사용자 정의 메시지
         * @return Builder 인스턴스
         */
        public Builder customMessage(String customMessage) {
            this.customMessage = customMessage;
            BannerConfig.Builder builder = BannerConfig.builder()
                .showLogo(config.isShowLogo())
                .showVersion(config.isShowVersion())
                .showBuildInfo(config.isShowBuildInfo())
                .showSystemInfo(config.isShowSystemInfo())
                .theme(config.getTheme())
                .customMessage(customMessage)
                .locale(config.getLocale())
                .asciiArt(config.getAsciiArt())
                .borderStyle(config.getBorderStyle())
                .showAsciiArt(config.isShowAsciiArt());
            this.config = builder.build();
            return this;
        }

        /**
         * 배너 테마를 설정합니다.
         *
         * @param theme 배너 테마
         * @return Builder 인스턴스
         */
        public Builder theme(BannerTheme theme) {
            BannerConfig.Builder builder = BannerConfig.builder()
                .showLogo(config.isShowLogo())
                .showVersion(config.isShowVersion())
                .showBuildInfo(config.isShowBuildInfo())
                .showSystemInfo(config.isShowSystemInfo())
                .theme(theme)
                .customMessage(config.getCustomMessage())
                .locale(config.getLocale())
                .asciiArt(config.getAsciiArt())
                .borderStyle(config.getBorderStyle())
                .showAsciiArt(config.isShowAsciiArt());
            this.config = builder.build();
            return this;
        }

        /**
         * ASCII 아트를 설정합니다.
         *
         * @param asciiArt ASCII 아트 문자열
         * @return Builder 인스턴스
         */
        public Builder asciiArt(String asciiArt) {
            this.asciiArt = asciiArt;
            BannerConfig.Builder builder = BannerConfig.builder()
                .showLogo(config.isShowLogo())
                .showVersion(config.isShowVersion())
                .showBuildInfo(config.isShowBuildInfo())
                .showSystemInfo(config.isShowSystemInfo())
                .theme(config.getTheme())
                .customMessage(config.getCustomMessage())
                .locale(config.getLocale())
                .asciiArt(asciiArt)
                .borderStyle(config.getBorderStyle())
                .showAsciiArt(config.isShowAsciiArt());
            this.config = builder.build();
            return this;
        }

        /**
         * 테두리 스타일을 설정합니다.
         *
         * @param borderStyle 테두리 스타일
         * @return Builder 인스턴스
         */
        public Builder borderStyle(BorderStyle borderStyle) {
            this.borderStyle = borderStyle;
            BannerConfig.Builder builder = BannerConfig.builder()
                .showLogo(config.isShowLogo())
                .showVersion(config.isShowVersion())
                .showBuildInfo(config.isShowBuildInfo())
                .showSystemInfo(config.isShowSystemInfo())
                .theme(config.getTheme())
                .customMessage(config.getCustomMessage())
                .locale(config.getLocale())
                .asciiArt(config.getAsciiArt())
                .borderStyle(borderStyle)
                .showAsciiArt(config.isShowAsciiArt());
            this.config = builder.build();
            return this;
        }

        /**
         * 로케일을 설정합니다.
         *
         * @param locale 로케일
         * @return Builder 인스턴스
         */
        public Builder locale(Locale locale) {
            BannerConfig.Builder builder = BannerConfig.builder()
                .showLogo(config.isShowLogo())
                .showVersion(config.isShowVersion())
                .showBuildInfo(config.isShowBuildInfo())
                .showSystemInfo(config.isShowSystemInfo())
                .theme(config.getTheme())
                .customMessage(config.getCustomMessage())
                .locale(locale)
                .asciiArt(config.getAsciiArt())
                .borderStyle(config.getBorderStyle())
                .showAsciiArt(config.isShowAsciiArt());
            this.config = builder.build();
            return this;
        }

        /**
         * ASCII 아트 표시 여부를 설정합니다.
         *
         * @param showAsciiArt ASCII 아트 표시 여부
         * @return Builder 인스턴스
         */
        public Builder showAsciiArt(boolean showAsciiArt) {
            BannerConfig.Builder builder = BannerConfig.builder()
                .showLogo(config.isShowLogo())
                .showVersion(config.isShowVersion())
                .showBuildInfo(config.isShowBuildInfo())
                .showSystemInfo(config.isShowSystemInfo())
                .theme(config.getTheme())
                .customMessage(config.getCustomMessage())
                .locale(config.getLocale())
                .asciiArt(config.getAsciiArt())
                .borderStyle(config.getBorderStyle())
                .showAsciiArt(showAsciiArt);
            this.config = builder.build();
            return this;
        }

        /**
         * 빌드 정보 표시 여부를 설정합니다.
         *
         * @param showBuildInfo 빌드 정보 표시 여부
         * @return Builder 인스턴스
         */
        public Builder showBuildInfo(boolean showBuildInfo) {
            BannerConfig.Builder builder = BannerConfig.builder()
                .showLogo(config.isShowLogo())
                .showVersion(config.isShowVersion())
                .showBuildInfo(showBuildInfo)
                .showSystemInfo(config.isShowSystemInfo())
                .theme(config.getTheme())
                .customMessage(config.getCustomMessage())
                .locale(config.getLocale())
                .asciiArt(config.getAsciiArt())
                .borderStyle(config.getBorderStyle())
                .showAsciiArt(config.isShowAsciiArt());
            this.config = builder.build();
            return this;
        }

        /**
         * 시스템 정보 표시 여부를 설정합니다.
         *
         * @param showSystemInfo 시스템 정보 표시 여부
         * @return Builder 인스턴스
         */
        public Builder showSystemInfo(boolean showSystemInfo) {
            BannerConfig.Builder builder = BannerConfig.builder()
                .showLogo(config.isShowLogo())
                .showVersion(config.isShowVersion())
                .showBuildInfo(config.isShowBuildInfo())
                .showSystemInfo(showSystemInfo)
                .theme(config.getTheme())
                .customMessage(config.getCustomMessage())
                .locale(config.getLocale())
                .asciiArt(config.getAsciiArt())
                .borderStyle(config.getBorderStyle())
                .showAsciiArt(config.isShowAsciiArt());
            this.config = builder.build();
            return this;
        }

        /**
         * AppBanner 인스턴스를 생성합니다.
         *
         * @return 생성된 AppBanner 인스턴스
         */
        public AppBanner build() {
            return new AppBanner(this);
        }
    }
} 