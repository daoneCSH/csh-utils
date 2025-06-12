package io.csh.utils.banner.template;

import io.csh.utils.banner.model.BannerInfo;

/**
 * 배너 템플릿을 정의하는 인터페이스입니다.
 * 배너의 레이아웃과 스타일을 결정하는 템플릿을 구현합니다.
 * 
 * <p>주요 구현체:
 * <ul>
 *   <li>DefaultBannerTemplate - 기본 배너 템플릿</li>
 *   <li>CustomBannerTemplate - 사용자 정의 배너 템플릿</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>
 * BannerTemplate template = new DefaultBannerTemplate(config);
 * String banner = template.apply(bannerInfo);
 * </pre>
 */
public interface BannerTemplate {
    /**
     * 배너 템플릿의 이름을 반환합니다.
     * 이 이름은 템플릿을 식별하는데 사용됩니다.
     *
     * @return 템플릿 이름
     */
    String getName();

    /**
     * 배너 템플릿의 설명을 반환합니다.
     * 이 설명은 템플릿의 용도와 특징을 설명합니다.
     *
     * @return 템플릿 설명
     */
    String getDescription();

    /**
     * 배너 정보를 템플릿에 적용하여 문자열로 반환합니다.
     * 템플릿의 레이아웃과 스타일에 따라 배너 정보를 포맷팅합니다.
     *
     * @param info 배너 정보
     * @return 템플릿이 적용된 배너 문자열
     * @throws IllegalArgumentException info가 null인 경우
     */
    String apply(BannerInfo info);

    /**
     * 템플릿이 유효한지 검사합니다.
     * 템플릿의 필수 요소가 모두 설정되어 있는지 확인합니다.
     *
     * @return 템플릿 유효성 여부
     */
    boolean isValid();
} 