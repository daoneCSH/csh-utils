package io.csh.utils.banner.detector;

import io.csh.utils.banner.model.BannerInfo;

/**
 * 배너 정보를 감지하는 인터페이스입니다.
 * 애플리케이션의 메타데이터, 시스템 정보 등을 자동으로 감지하여 배너 정보를 구성합니다.
 * 
 * <p>주요 구현체:
 * <ul>
 *   <li>DefaultBannerDetector - 기본 배너 정보 감지기</li>
 *   <li>CustomBannerDetector - 사용자 정의 배너 정보 감지기</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>
 * BannerDetector detector = new DefaultBannerDetector();
 * BannerInfo info = detector.detect();
 * </pre>
 */
public interface BannerDetector {
    /**
     * 배너 정보를 감지합니다.
     * 애플리케이션의 메타데이터와 시스템 정보를 수집하여 BannerInfo 객체를 생성합니다.
     *
     * @return 감지된 배너 정보
     * @throws RuntimeException 정보 감지 중 오류가 발생한 경우
     */
    BannerInfo detect();

    /**
     * 감지된 배너 정보를 기존 배너 정보에 적용합니다.
     * null이 아닌 필드만 업데이트됩니다.
     *
     * @param info 기존 배너 정보
     * @return 업데이트된 배너 정보
     * @throws RuntimeException 정보 감지 중 오류가 발생한 경우
     */
    BannerInfo detectAndUpdate(BannerInfo info);

    /**
     * 감지기가 유효한지 검사합니다.
     * 감지기의 필수 요소가 모두 설정되어 있는지 확인합니다.
     *
     * @return 감지기 유효성 여부
     */
    boolean isValid();
} 