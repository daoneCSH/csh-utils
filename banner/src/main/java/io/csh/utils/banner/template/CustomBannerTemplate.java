package io.csh.utils.banner.template;

import io.csh.utils.banner.model.BannerInfo;

import java.util.function.Function;

/**
 * 사용자 정의 배너 템플릿
 * 
 * <p>이 클래스는 사용자가 정의한 함수를 사용하여 배너를 렌더링합니다.</p>
 * 
 * <p>사용 예시:</p>
 * <pre>
 * {@code
 * Function<BannerInfo, String> template = (info) -> {
 *     return String.format("""
 *         ==========================================
 *         %s v%s
 *         Build Time: %s
 *         ==========================================
 *         """, 
 *         info.getAppName(),
 *         info.getVersion(),
 *         info.getBuildTime());
 * };
 * CustomBannerTemplate customTemplate = new CustomBannerTemplate("Custom Template", "A custom banner template", template);
 * }
 * </pre>
 */
public class CustomBannerTemplate implements BannerTemplate {
    private final String name;
    private final String description;
    private final Function<BannerInfo, String> template;

    /**
     * 사용자 정의 템플릿 함수로 CustomBannerTemplate을 생성합니다.
     *
     * @param name 템플릿 이름
     * @param description 템플릿 설명
     * @param template 배너를 렌더링하는 함수
     * @throws IllegalArgumentException name이 null이거나 비어있는 경우
     * @throws IllegalArgumentException template이 null인 경우
     */
    public CustomBannerTemplate(String name, String description, Function<BannerInfo, String> template) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Template name cannot be null or empty");
        }
        if (template == null) {
            throw new IllegalArgumentException("Template function cannot be null");
        }
        this.name = name;
        this.description = description != null ? description : "";
        this.template = template;
    }

    /**
     * 템플릿의 이름을 반환합니다.
     *
     * @return 템플릿 이름
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 템플릿의 설명을 반환합니다.
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
        if (info == null) {
            throw new IllegalArgumentException("BannerInfo cannot be null");
        }
        if (!isValid()) {
            throw new IllegalStateException("Template is not valid");
        }

        try {
            return template.apply(info);
        } catch (Exception e) {
            throw new RuntimeException("Failed to render banner template: " + e.getMessage(), e);
        }
    }

    /**
     * 템플릿이 유효한지 검사합니다.
     * 이름이 비어있지 않고, 템플릿 함수가 null이 아닌 경우에만 유효하다고 판단합니다.
     *
     * @return 템플릿 유효성 여부
     */
    @Override
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() && template != null;
    }
} 