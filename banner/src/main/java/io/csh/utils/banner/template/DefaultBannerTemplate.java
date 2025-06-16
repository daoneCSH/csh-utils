package io.csh.utils.banner.template;

import io.csh.utils.banner.core.BannerConfig;
import io.csh.utils.banner.model.BannerInfo;

/**
 * 기본 배너 템플릿 구현체입니다.
 * 애플리케이션 이름, 버전, 빌드 정보, 시스템 정보를 포함하는 기본적인 레이아웃을 제공합니다.
 * 
 * <p>주요 기능:
 * <ul>
 *   <li>로고 표시 (애플리케이션 이름)</li>
 *   <li>버전 정보 표시</li>
 *   <li>빌드 시간 표시</li>
 *   <li>시스템 정보 표시 (Java 버전, OS 정보)</li>
 *   <li>사용자 정의 메시지 지원</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>
 * BannerConfig config = BannerConfig.defaultConfig();
 * DefaultBannerTemplate template = new DefaultBannerTemplate(config);
 * String banner = template.apply(bannerInfo);
 * </pre>
 */
public class DefaultBannerTemplate implements BannerTemplate {
    private final BannerConfig config;

    /**
     * DefaultBannerTemplate을 생성합니다.
     * 설정에 따라 배너의 표시 방식을 결정합니다.
     *
     * @param config 배너 설정
     * @throws IllegalArgumentException config가 null인 경우
     */
    public DefaultBannerTemplate(BannerConfig config) {
        this.config = config;
    }

    /**
     * 템플릿의 이름을 반환합니다.
     * 기본 템플릿의 이름은 "Default"입니다.
     *
     * @return 템플릿 이름
     */
    @Override
    public String getName() {
        return "Default";
    }

    /**
     * 템플릿의 설명을 반환합니다.
     * 기본 템플릿의 특징과 포함되는 정보를 설명합니다.
     *
     * @return 템플릿 설명
     */
    @Override
    public String getDescription() {
        return "기본 배너 템플릿 - 애플리케이션 이름, 버전, 빌드 정보, 시스템 정보를 포함";
    }

    /**
     * 배너 정보를 템플릿에 적용하여 문자열로 반환합니다.
     * 설정에 따라 로고, 버전, 빌드 정보, 시스템 정보를 포함한 배너를 생성합니다.
     *
     * @param info 배너 정보
     * @return 템플릿이 적용된 배너 문자열
     * @throws IllegalArgumentException info가 null인 경우
     */
    @Override
    public String apply(BannerInfo info) {
        StringBuilder banner = new StringBuilder();
        banner.append("\n");

        // 로고 표시
        if (config.isShowLogo() && info.getAppName() != null) {
            banner.append(info.getAppName()).append("\n\n");
        }

        // 버전 정보 표시
        if (config.isShowVersion() && info.getVersion() != null) {
            banner.append("Version: ").append(info.getVersion()).append("\n");
        }

        // csh.utils 버전 표시
        if (config.isShowVersion() && info.getCshUtilsVersion() != null) {
            banner.append("CSH Utils Version: ").append(info.getCshUtilsVersion()).append("\n");
        }

        // 빌드 정보 표시
        if (config.isShowBuildInfo() && info.getBuildTime() != null) {
            banner.append("Build Time: ").append(info.getBuildTime()).append("\n");
        }

        // 시스템 정보 표시
        if (config.isShowSystemInfo()) {
            banner.append("Java Version: ").append(info.getJavaVersion()).append("\n");
            banner.append("OS: ").append(info.getOsName())
                    .append(" ").append(info.getOsVersion())
                    .append(" (").append(info.getOsArch()).append(")")
                    .append("\n");
        }

        // 커스텀 메시지 표시
        if (info.getCustomMessage() != null) {
            banner.append("\n").append(info.getCustomMessage()).append("\n");
        }

        banner.append("\n");
        return banner.toString();
    }

    /**
     * 템플릿이 유효한지 검사합니다.
     * 설정이 null이 아닌 경우에만 유효하다고 판단합니다.
     *
     * @return 템플릿 유효성 여부
     */
    @Override
    public boolean isValid() {
        return config != null;
    }
} 