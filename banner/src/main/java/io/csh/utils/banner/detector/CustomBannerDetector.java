package io.csh.utils.banner.detector;

import io.csh.utils.banner.model.BannerInfo;

import java.util.function.Function;

/**
 * 사용자 정의 배너 정보 감지기
 * 
 * <p>이 클래스는 사용자가 정의한 함수를 사용하여 배너 정보를 감지합니다.</p>
 * 
 * <p>사용 예시:</p>
 * <pre>
 * {@code
 * Function<Void, BannerInfo> detector = (v) -> {
 *     BannerInfo info = new BannerInfo();
 *     info.setAppName("MyApp");
 *     info.setVersion("1.0.0");
 *     return info;
 * };
 * CustomBannerDetector customDetector = new CustomBannerDetector(detector);
 * }
 * </pre>
 */
public class CustomBannerDetector implements BannerDetector {
    private final Function<Void, BannerInfo> detector;

    /**
     * 사용자 정의 감지 함수로 CustomBannerDetector를 생성합니다.
     *
     * @param detector 배너 정보를 감지하는 함수
     */
    public CustomBannerDetector(Function<Void, BannerInfo> detector) {
        this.detector = detector;
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
        return detector.apply(null);
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
        return detector != null;
    }
} 