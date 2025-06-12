package io.csh.utils.banner.renderer;

import io.csh.utils.banner.model.BannerInfo;

/**
 * 배너를 렌더링하는 인터페이스입니다.
 * 다양한 형식의 배너 출력을 지원하기 위한 구현체들이 이 인터페이스를 구현합니다.
 * 
 * <p>주요 구현체:
 * <ul>
 *   <li>ConsoleBannerRenderer - 콘솔에 배너를 출력</li>
 *   <li>FileBannerRenderer - 파일에 배너를 출력</li>
 *   <li>LogBannerRenderer - 로그 시스템에 배너를 출력</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>
 * BannerRenderer renderer = new ConsoleBannerRenderer(config);
 * renderer.render(bannerInfo);
 * </pre>
 */
public interface BannerRenderer {
    /**
     * 배너 정보를 사용하여 배너를 렌더링합니다.
     * 구현체는 이 메서드를 통해 배너를 원하는 형식으로 출력합니다.
     *
     * @param info 렌더링할 배너 정보
     * @throws IllegalArgumentException info가 null인 경우
     * @throws RuntimeException 렌더링 중 오류가 발생한 경우
     */
    void render(BannerInfo info);
} 