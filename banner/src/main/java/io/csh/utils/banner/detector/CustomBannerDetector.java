package io.csh.utils.banner.detector;

import io.csh.utils.banner.model.BannerInfo;

import java.util.function.Function;

/**
 * 사용자 정의 배너 정보 감지기입니다.
 * 사용자가 직접 배너 정보를 감지하는 로직을 정의할 수 있도록 Function을 통해 감지기를 제공합니다.
 * 
 * <p>주요 기능:
 * <ul>
 *   <li>사용자 정의 감지 로직 지원</li>
 *   <li>Function 인터페이스를 통한 유연한 구현</li>
 *   <li>기존 배너 정보 업데이트 지원</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>
 * Function<Void, BannerInfo> detector = (v) -> {
 *     BannerInfo info = new BannerInfo();
 *     info.setAppName("MyApp");
 *     info.setVersion("1.0.0");
 *     return info;
 * };
 * CustomBannerDetector customDetector = new CustomBannerDetector(detector);
 * BannerInfo info = customDetector.detect();
 * </pre>
 */
public class CustomBannerDetector implements BannerDetector {
    private final Function<Void, BannerInfo> detectorFunction;

    /**
     * 사용자 정의 배너 정보 감지기를 생성합니다.
     * 감지 로직은 Function 인터페이스를 통해 제공됩니다.
     *
     * @param detectorFunction 배너 정보를 감지하는 함수
     * @throws IllegalArgumentException detectorFunction이 null인 경우
     */
    public CustomBannerDetector(Function<Void, BannerInfo> detectorFunction) {
        this.detectorFunction = detectorFunction;
    }

    /**
     * 배너 정보를 감지합니다.
     * 사용자가 정의한 감지 함수를 사용하여 배너 정보를 생성합니다.
     *
     * @return 감지된 배너 정보
     * @throws RuntimeException 감지 함수 실행 중 오류가 발생한 경우
     */
    @Override
    public BannerInfo detect() {
        return detectorFunction.apply(null);
    }

    /**
     * 감지된 배너 정보를 기존 배너 정보에 적용합니다.
     * null이 아닌 필드만 업데이트됩니다.
     *
     * @param info 기존 배너 정보
     * @return 업데이트된 배너 정보
     * @throws RuntimeException 감지 함수 실행 중 오류가 발생한 경우
     */
    @Override
    public BannerInfo detectAndUpdate(BannerInfo info) {
        if (info == null) {
            return detect();
        }
        BannerInfo detected = detect();
        info.update(detected);
        return info;
    }

    /**
     * 감지기가 유효한지 검사합니다.
     * 감지 함수가 null이 아닌 경우에만 유효하다고 판단합니다.
     *
     * @return 감지기 유효성 여부
     */
    @Override
    public boolean isValid() {
        return detectorFunction != null;
    }
} 