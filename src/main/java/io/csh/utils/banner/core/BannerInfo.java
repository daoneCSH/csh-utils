package io.csh.utils.banner.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * BannerInfo는 배너에 표시될 애플리케이션 정보를 관리합니다.
 * <p>
 * 이름, 버전, 빌드 시간, Java/OS 정보, 커스텀 메시지, ASCII 아트 등
 * 배너에 출력될 모든 정보를 포함합니다.
 * </p>
 */
public class BannerInfo {
    /**
     * 빌드 시간 포맷터 (yyyy-MM-dd HH:mm:ss)
     */
    private static final DateTimeFormatter BUILD_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * 애플리케이션 이름
     */
    private final String name;
    /**
     * 애플리케이션 버전
     */
    private final String version;
    /**
     * 빌드 시간
     */
    private final String buildTime;
    /**
     * Java 버전
     */
    private final String javaVersion;
    /**
     * OS 이름
     */
    private final String osInfo;
    /**
     * 커스텀 메시지
     */
    private final String customMessage;
    /**
     * 커스텀 ASCII 아트
     */
    private final String customAsciiArt;

    /**
     * BannerInfo 객체를 생성합니다.
     * @param name 애플리케이션 이름
     * @param version 애플리케이션 버전
     * @param buildTime 빌드 시간
     * @param javaVersion Java 버전
     * @param osInfo OS 정보
     * @param customMessage 커스텀 메시지
     * @param customAsciiArt 커스텀 ASCII 아트
     */
    private BannerInfo(String name, String version, String buildTime, 
                      String javaVersion, String osInfo, String customMessage, String customAsciiArt) {
        this.name = name;
        this.version = version;
        this.buildTime = buildTime;
        this.javaVersion = javaVersion;
        this.osInfo = osInfo;
        this.customMessage = customMessage;
        this.customAsciiArt = customAsciiArt;
    }

    /**
     * 애플리케이션 이름을 반환합니다.
     * @return 이름
     */
    public String getName() {
        return name;
    }

    /**
     * 애플리케이션 버전을 반환합니다.
     * @return 버전
     */
    public String getVersion() {
        return version;
    }

    /**
     * 빌드 시간을 반환합니다.
     * @return 빌드 시간
     */
    public String getBuildTime() {
        return buildTime;
    }

    /**
     * Java 버전을 반환합니다.
     * @return Java 버전
     */
    public String getJavaVersion() {
        return javaVersion;
    }

    /**
     * OS 이름을 반환합니다.
     * @return OS 이름
     */
    public String getOsInfo() {
        return osInfo;
    }

    /**
     * 커스텀 메시지를 반환합니다.
     * @return 커스텀 메시지
     */
    public String getCustomMessage() {
        return customMessage;
    }

    /**
     * 커스텀 ASCII 아트를 반환합니다.
     * @return 커스텀 ASCII 아트
     */
    public String getCustomAsciiArt() {
        return customAsciiArt;
    }

    /**
     * BannerInfo의 빌더를 반환합니다.
     * @return Builder 인스턴스
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * BannerInfo의 빌더를 반환합니다.
     */
    public static class Builder {
        /**
         * 애플리케이션 이름
         */
        private String name;
        /**
         * 애플리케이션 버전
         */
        private String version;
        /**
         * 빌드 시간
         */
        private String buildTime = LocalDateTime.now().format(BUILD_TIME_FORMATTER);
        /**
         * Java 버전
         */
        private String javaVersion = System.getProperty("java.version");
        /**
         * OS 이름
         */
        private String osInfo = System.getProperty("os.name") + " " + 
                              System.getProperty("os.version") + " " + 
                              System.getProperty("os.arch");
        /**
         * 커스텀 메시지
         */
        private String customMessage;
        /**
         * 커스텀 ASCII 아트
         */
        private String customAsciiArt;

        /**
         * BannerInfo.Builder를 생성합니다.
         */
        public Builder() {
        }

        /**
         * 이름을 설정합니다.
         * @param name 애플리케이션 이름
         * @return Builder
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * 버전을 설정합니다.
         * @param version 애플리케이션 버전
         * @return Builder
         */
        public Builder version(String version) {
            this.version = version;
            return this;
        }

        /**
         * 빌드 시간을 설정합니다.
         * @param buildTime 빌드 시간
         * @return Builder
         */
        public Builder buildTime(String buildTime) {
            this.buildTime = buildTime;
            return this;
        }

        /**
         * Java 버전을 설정합니다.
         * @param javaVersion Java 버전
         * @return Builder
         */
        public Builder javaVersion(String javaVersion) {
            this.javaVersion = javaVersion;
            return this;
        }

        /**
         * OS 정보를 설정합니다.
         * @param osInfo OS 정보
         * @return Builder
         */
        public Builder osInfo(String osInfo) {
            this.osInfo = osInfo;
            return this;
        }

        /**
         * 커스텀 메시지를 설정합니다.
         * @param customMessage 커스텀 메시지
         * @return Builder
         */
        public Builder customMessage(String customMessage) {
            this.customMessage = customMessage;
            return this;
        }

        /**
         * 커스텀 ASCII 아트를 설정합니다.
         * @param customAsciiArt 커스텀 ASCII 아트
         * @return Builder
         */
        public Builder customAsciiArt(String customAsciiArt) {
            this.customAsciiArt = customAsciiArt;
            return this;
        }

        /**
         * BannerInfo 객체를 생성합니다.
         * @return BannerInfo 인스턴스
         */
        public BannerInfo build() {
            return new BannerInfo(name, version, buildTime, javaVersion, osInfo, customMessage, customAsciiArt);
        }
    }
} 