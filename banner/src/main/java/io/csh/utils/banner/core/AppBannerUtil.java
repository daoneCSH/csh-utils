package io.csh.utils.banner.core;

import io.csh.utils.banner.model.BannerInfo;
import io.csh.utils.banner.renderer.BannerRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * 애플리케이션 시작 시 표시되는 배너를 관리하는 클래스입니다.
 * 배너의 표시, 커스터마이징, 다양한 출력 형식 지원 등의 기능을 제공합니다.
 * 
 * <p>주요 기능:
 * <ul>
 *   <li>배너 설정 관리 (BannerConfig)</li>
 *   <li>배너 정보 관리 (BannerInfo)</li>
 *   <li>다양한 렌더러 지원 (BannerRenderer)</li>
 *   <li>배너 정보 자동 감지 (BannerDetector)</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>
 * AppBannerUtil banner = new AppBannerUtil();
 * banner.addRenderer(new ConsoleBannerRenderer(config));
 * banner.display();
 * </pre>
 */
public class AppBannerUtil {
    private BannerConfig config;
    private final BannerInfo info;
    private final List<BannerRenderer> renderers;

    /**
     * 기본 설정으로 AppBannerUtil을 생성합니다.
     */
    public AppBannerUtil() {
        this(BannerConfig.defaultConfig());
    }

    /**
     * 지정된 설정으로 AppBannerUtil을 생성합니다.
     *
     * @param config 배너 설정
     */
    public AppBannerUtil(BannerConfig config) {
        this.config = config;
        this.info = new BannerInfo();
        this.renderers = new ArrayList<>();
    }

    /**
     * 배너 설정을 반환합니다.
     * 이 설정은 배너의 표시 여부, 테마, 메시지 등을 제어합니다.
     *
     * @return 현재 배너 설정
     */
    public BannerConfig getConfig() {
        return config;
    }

    /**
     * 배너 정보를 반환합니다.
     * 이 정보는 애플리케이션 이름, 버전, 빌드 정보 등을 포함합니다.
     *
     * @return 현재 배너 정보
     */
    public BannerInfo getInfo() {
        return info;
    }

    /**
     * 배너를 표시합니다.
     * 등록된 모든 렌더러를 사용하여 배너를 출력합니다.
     * 각 렌더러는 자신의 방식으로 배너를 표시합니다.
     */
    public void display() {
        for (BannerRenderer renderer : renderers) {
            renderer.render(info);
        }
    }

    /**
     * 렌더러를 추가합니다.
     * 여러 렌더러를 등록하여 다양한 형식으로 배너를 출력할 수 있습니다.
     *
     * @param renderer 추가할 렌더러
     */
    public void addRenderer(BannerRenderer renderer) {
        renderers.add(renderer);
    }

    /**
     * 배너 정보를 업데이트합니다.
     * 새로운 정보로 기존 배너 정보를 업데이트합니다.
     * null이 아닌 필드만 업데이트됩니다.
     *
     * @param info 업데이트할 배너 정보
     */
    public void updateInfo(BannerInfo info) {
        this.info.update(info);
    }

    /**
     * 설정을 업데이트합니다.
     *
     * @param config 업데이트할 설정
     */
    public void updateConfig(BannerConfig config) {
        this.config = config;
    }
} 