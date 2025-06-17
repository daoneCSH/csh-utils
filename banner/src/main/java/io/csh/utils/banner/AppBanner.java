package io.csh.utils.banner;

import io.csh.utils.banner.core.AppBannerUtil;
import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.core.BannerTheme;
import io.csh.utils.banner.core.BorderStyle;
import io.csh.utils.banner.detector.DefaultBannerDetector;
import io.csh.utils.banner.model.BannerInfo;
import io.csh.utils.banner.renderer.ConsoleBannerRenderer;

import java.util.Locale;

/**
 * 애플리케이션 배너를 출력하기 위한 Facade 클래스입니다.
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
 * AppBanner.print(BannerTheme.COLORFUL);  // 컬러풀 테마
 * AppBanner.print(BannerTheme.DARK);      // 다크 테마
 * AppBanner.print(BannerTheme.LIGHT);     // 라이트 테마
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
    private final BannerInfo info;
    private final BannerConfig config;

    private AppBanner(Builder builder) {
        this.info = new BannerInfo();
        this.info.setAppName(builder.name);
        this.info.setVersion(builder.version);
        this.info.setCustomMessage(builder.customMessage);

        this.config = new BannerConfig.Builder()
            .theme(builder.theme)
            .showLogo(true)
            .showVersion(true)
            .showBuildInfo(true)
            .showSystemInfo(true)
            .customMessage(builder.customMessage)
            .logo(builder.name)
            .locale(builder.locale)
            .asciiArt(builder.asciiArt)
            .borderStyle(builder.borderStyle)
            .build();
    }

    /**
     * 배너를 출력합니다.
     */
    public void print() {
        AppBannerUtil banner = new AppBannerUtil(config);
        banner.addRenderer(new ConsoleBannerRenderer(config));
        banner.updateInfo(info);
        banner.display();
    }

    /**
     * 기본 테마로 배너를 출력합니다.
     * 배너 정보는 자동으로 감지되며, 기본 설정이 적용됩니다.
     * 
     * <p>기본 설정:
     * <ul>
     *   <li>테마: DEFAULT</li>
     *   <li>로고 표시: true</li>
     *   <li>버전 표시: true</li>
     *   <li>빌드 정보 표시: true</li>
     *   <li>시스템 정보 표시: true</li>
     * </ul>
     */
    public static void printDefault() {
        BannerConfig config = BannerConfig.defaultConfig();
        BannerInfo info = new DefaultBannerDetector().detect();
        AppBannerUtil banner = new AppBannerUtil(config);
        banner.addRenderer(new ConsoleBannerRenderer(config));
        banner.updateInfo(info);
        banner.display();
    }

    /**
     * 지정된 테마로 배너를 출력합니다.
     * 배너 정보는 자동으로 감지되며, 지정된 테마가 적용됩니다.
     *
     * @param theme 배너 테마 (DEFAULT, COLORFUL, DARK, LIGHT, MONOCHROME)
     * @throws IllegalArgumentException theme이 null인 경우
     */
    public static void print(BannerTheme theme) {
        if (theme == null) {
            throw new IllegalArgumentException("Theme cannot be null");
        }
        BannerConfig config = new BannerConfig.Builder()
                .theme(theme)
                .build();
        BannerInfo info = new DefaultBannerDetector().detect();
        AppBannerUtil banner = new AppBannerUtil(config);
        banner.addRenderer(new ConsoleBannerRenderer(config));
        banner.updateInfo(info);
        banner.display();
    }

    /**
     * 배너를 생성하기 위한 빌더 클래스입니다.
     */
    public static class Builder {
        private String name;
        private String version;
        private String customMessage;
        private BannerTheme theme = BannerTheme.DEFAULT;
        private Locale locale = Locale.getDefault();
        private String asciiArt;
        private BorderStyle borderStyle = BorderStyle.NONE;

        /**
         * 애플리케이션 이름을 설정합니다.
         *
         * @param name 애플리케이션 이름
         * @return 빌더 인스턴스
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * 애플리케이션 버전을 설정합니다.
         *
         * @param version 애플리케이션 버전
         * @return 빌더 인스턴스
         */
        public Builder version(String version) {
            this.version = version;
            return this;
        }

        /**
         * 사용자 정의 메시지를 설정합니다.
         *
         * @param customMessage 사용자 정의 메시지
         * @return 빌더 인스턴스
         */
        public Builder customMessage(String customMessage) {
            this.customMessage = customMessage;
            return this;
        }

        /**
         * 배너 테마를 설정합니다.
         *
         * @param theme 배너 테마
         * @return 빌더 인스턴스
         */
        public Builder theme(BannerTheme theme) {
            this.theme = theme;
            return this;
        }

        /**
         * 로케일을 설정합니다.
         *
         * @param locale 로케일
         * @return 빌더 인스턴스
         */
        public Builder locale(Locale locale) {
            this.locale = locale;
            return this;
        }

        /**
         * ASCII 아트를 설정합니다.
         * 이 아트는 배너의 상단에 표시됩니다.
         *
         * @param asciiArt ASCII 아트
         * @return 빌더 인스턴스
         */
        public Builder asciiArt(String asciiArt) {
            this.asciiArt = asciiArt;
            return this;
        }

        /**
         * 테두리 스타일을 설정합니다.
         * 테두리는 배너의 외곽을 꾸며줍니다.
         *
         * @param borderStyle 테두리 스타일
         * @return 빌더 인스턴스
         */
        public Builder borderStyle(BorderStyle borderStyle) {
            if (borderStyle == null) {
                throw new IllegalArgumentException("BorderStyle cannot be null");
            }
            this.borderStyle = borderStyle;
            return this;
        }

        /**
         * AppBanner 인스턴스를 생성합니다.
         *
         * @return AppBanner 인스턴스
         * @throws IllegalStateException 필수 필드가 설정되지 않은 경우
         */
        public AppBanner build() {
            if (name == null) {
                throw new IllegalStateException("Name is required");
            }
            if (version == null) {
                throw new IllegalStateException("Version is required");
            }
            return new AppBanner(this);
        }
    }
} 