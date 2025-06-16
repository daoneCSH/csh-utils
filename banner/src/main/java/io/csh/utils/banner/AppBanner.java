package io.csh.utils.banner;

import io.csh.utils.banner.core.AppBannerUtil;
import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.core.BannerTheme;
import io.csh.utils.banner.detector.DefaultBannerDetector;
import io.csh.utils.banner.model.BannerInfo;
import io.csh.utils.banner.renderer.ConsoleBannerRenderer;

/**
 * 애플리케이션 배너를 출력하기 위한 Facade 클래스입니다.
 * 복잡한 내부 구현을 숨기고 간단한 API를 제공합니다.
 * 
 * <p>사용 예시:
 * <pre>
 * AppBanner.printDefault();                  // 기본 테마 출력
 * AppBanner.print(BannerTheme.ASCII);        // 특정 테마 출력
 * </pre>
 */
public class AppBanner {

    /**
     * 기본 테마로 배너를 출력합니다.
     */
    public static void printDefault() {
        BannerConfig config = BannerConfig.defaultConfig();
        BannerInfo info = new DefaultBannerDetector().detect();
        AppBannerUtil banner = new AppBannerUtil(config);
        banner.addRenderer(new ConsoleBannerRenderer(config));
        banner.display();
    }

    /**
     * 지정된 테마로 배너를 출력합니다.
     *
     * @param theme 배너 테마
     */
    public static void print(BannerTheme theme) {
        BannerConfig config = new BannerConfig.Builder()
                .theme(theme)
                .build();
        BannerInfo info = new DefaultBannerDetector().detect();
        AppBannerUtil banner = new AppBannerUtil(config);
        banner.addRenderer(new ConsoleBannerRenderer(config));
        banner.display();
    }

    /**
     * 사용자 정의 배너 정보와 설정으로 배너를 출력합니다.
     *
     * @param info 배너 정보
     * @param config 배너 설정
     */
    public static void printCustom(BannerInfo info, BannerConfig config) {
        AppBannerUtil banner = new AppBannerUtil(config);
        banner.addRenderer(new ConsoleBannerRenderer(config));
        banner.updateInfo(info);
        banner.display();
    }
} 