package io.csh.utils.banner.template;

import io.csh.utils.banner.model.BannerInfo;

import java.util.function.Function;

/**
 * 사용자 정의 배너 템플릿 구현체입니다.
 * 사용자가 직접 배너의 레이아웃을 정의할 수 있도록 Function을 통해 템플릿을 제공합니다.
 * 
 * <p>주요 기능:
 * <ul>
 *   <li>사용자 정의 템플릿 레이아웃 지원</li>
 *   <li>Function 인터페이스를 통한 유연한 구현</li>
 *   <li>템플릿 이름과 설명 커스터마이징</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>
 * Function<BannerInfo, String> template = (info) -> {
 *     return String.format("""
 *         Welcome to %s!
 *         Version: %s
 *         """, info.getAppName(), info.getVersion());
 * };
 * CustomBannerTemplate customTemplate = new CustomBannerTemplate(
 *     "Simple", "간단한 환영 메시지 템플릿", template);
 * String banner = customTemplate.apply(bannerInfo);
 * </pre>
 */
public class CustomBannerTemplate implements BannerTemplate {
    private final String name;
    private final String description;
    private final Function<BannerInfo, String> templateFunction;

    /**
     * 사용자 정의 배너 템플릿을 생성합니다.
     * 템플릿의 레이아웃은 Function 인터페이스를 통해 제공됩니다.
     *
     * @param name 템플릿 이름
     * @param description 템플릿 설명
     * @param templateFunction 배너 정보를 문자열로 변환하는 함수
     * @throws IllegalArgumentException name, description, templateFunction 중 하나라도 null인 경우
     */
    public CustomBannerTemplate(String name, String description, Function<BannerInfo, String> templateFunction) {
        this.name = name;
        this.description = description;
        this.templateFunction = templateFunction;
    }

    /**
     * 템플릿의 이름을 반환합니다.
     * 사용자가 지정한 템플릿 이름을 반환합니다.
     *
     * @return 템플릿 이름
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 템플릿의 설명을 반환합니다.
     * 사용자가 지정한 템플릿 설명을 반환합니다.
     *
     * @return 템플릿 설명
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * 배너 정보를 템플릿에 적용하여 문자열로 반환합니다.
     * 사용자가 정의한 템플릿 함수를 사용하여 배너를 생성합니다.
     *
     * @param info 배너 정보
     * @return 템플릿이 적용된 배너 문자열
     * @throws IllegalArgumentException info가 null인 경우
     * @throws RuntimeException 템플릿 함수 실행 중 오류가 발생한 경우
     */
    @Override
    public String apply(BannerInfo info) {
        return templateFunction.apply(info);
    }

    /**
     * 템플릿이 유효한지 검사합니다.
     * 이름, 설명, 템플릿 함수가 모두 null이 아니고, 이름이 비어있지 않은 경우에만 유효하다고 판단합니다.
     *
     * @return 템플릿 유효성 여부
     */
    @Override
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() 
            && description != null 
            && templateFunction != null;
    }
} 