package io.csh.utils.banner.model;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * 배너에 표시될 정보를 담는 클래스입니다.
 * 애플리케이션의 이름, 버전, 빌드 정보 등을 포함합니다.
 * 
 * <p>주요 정보:
 * <ul>
 *   <li>애플리케이션 이름</li>
 *   <li>애플리케이션 버전</li>
 *   <li>빌드 시간</li>
 *   <li>Java 버전</li>
 *   <li>운영체제 정보</li>
 *   <li>사용자 정의 메시지</li>
 * </ul>
 * 
 * <p>시스템 정보는 생성자에서 자동으로 초기화됩니다.
 */
public class BannerInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String VERSION_PROPERTIES = "/io/csh/utils/v.properties";
    
    private String appName;
    private String version;
    private String buildTime;
    private String javaVersion;
    private String osName;
    private String osVersion;
    private String osArch;
    private String customMessage;

    /**
     * BannerInfo 인스턴스를 생성합니다.
     * 시스템 정보(Java 버전, OS 정보 등)는 자동으로 초기화됩니다.
     */
    public BannerInfo() {
        this.buildTime = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DATE_FORMATTER);
        this.javaVersion = System.getProperty("java.version");
        this.osName = System.getProperty("os.name");
        this.osVersion = System.getProperty("os.version");
        this.osArch = System.getProperty("os.arch");
    }

    /**
     * 배너 정보를 생성합니다.
     *
     * @param appName 애플리케이션 이름
     * @param version 애플리케이션 버전
     * @param buildTime 빌드 시간
     * @param javaVersion Java 버전
     * @param osName 운영체제 이름
     * @param osVersion 운영체제 버전
     * @param osArch 운영체제 아키텍처
     * @param customMessage 사용자 정의 메시지
     */
    public BannerInfo(String appName, String version, String buildTime, String javaVersion,
                     String osName, String osVersion, String osArch, String customMessage) {
        this.appName = appName;
        this.version = version;
        this.buildTime = buildTime;
        this.javaVersion = javaVersion;
        this.osName = osName;
        this.osVersion = osVersion;
        this.osArch = osArch;
        this.customMessage = customMessage;
    }

    /**
     * 애플리케이션 이름을 반환합니다.
     * 이 이름은 배너의 상단에 표시됩니다.
     *
     * @return 애플리케이션 이름
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 애플리케이션 이름을 설정합니다.
     * 이 이름은 배너의 상단에 표시됩니다.
     *
     * @param appName 애플리케이션 이름
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 애플리케이션 버전을 반환합니다.
     * 이 버전은 배너에 표시되는 애플리케이션의 버전 정보입니다.
     *
     * @return 애플리케이션 버전
     */
    public String getVersion() {
        return version;
    }

    /**
     * 애플리케이션 버전을 설정합니다.
     * 이 버전은 배너에 표시되는 애플리케이션의 버전 정보입니다.
     *
     * @param version 애플리케이션 버전
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 빌드 시간을 반환합니다.
     * 이 시간은 애플리케이션이 빌드된 시간을 나타냅니다.
     *
     * @return 빌드 시간
     */
    public String getBuildTime() {
        return buildTime;
    }

    /**
     * 빌드 시간을 설정합니다.
     * 이 시간은 애플리케이션이 빌드된 시간을 나타냅니다.
     *
     * @param buildTime 빌드 시간
     */
    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    /**
     * Java 버전을 반환합니다.
     * 이 버전은 애플리케이션이 실행되는 Java 런타임의 버전입니다.
     *
     * @return Java 버전
     */
    public String getJavaVersion() {
        return javaVersion;
    }

    /**
     * Java 버전을 설정합니다.
     * 이 버전은 애플리케이션이 실행되는 Java 런타임의 버전입니다.
     *
     * @param javaVersion Java 버전
     */
    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    /**
     * 운영체제 이름을 반환합니다.
     * 이 이름은 애플리케이션이 실행되는 운영체제의 이름입니다.
     *
     * @return 운영체제 이름
     */
    public String getOsName() {
        return osName;
    }

    /**
     * 운영체제 이름을 설정합니다.
     * 이 이름은 애플리케이션이 실행되는 운영체제의 이름입니다.
     *
     * @param osName 운영체제 이름
     */
    public void setOsName(String osName) {
        this.osName = osName;
    }

    /**
     * 운영체제 버전을 반환합니다.
     * 이 버전은 애플리케이션이 실행되는 운영체제의 버전입니다.
     *
     * @return 운영체제 버전
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * 운영체제 버전을 설정합니다.
     * 이 버전은 애플리케이션이 실행되는 운영체제의 버전입니다.
     *
     * @param osVersion 운영체제 버전
     */
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    /**
     * 운영체제 아키텍처를 반환합니다.
     * 이 아키텍처는 애플리케이션이 실행되는 운영체제의 아키텍처입니다.
     *
     * @return 운영체제 아키텍처
     */
    public String getOsArch() {
        return osArch;
    }

    /**
     * 운영체제 아키텍처를 설정합니다.
     * 이 아키텍처는 애플리케이션이 실행되는 운영체제의 아키텍처입니다.
     *
     * @param osArch 운영체제 아키텍처
     */
    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    /**
     * 사용자 정의 메시지를 반환합니다.
     * 이 메시지는 배너에 추가로 표시되는 사용자 정의 텍스트입니다.
     *
     * @return 사용자 정의 메시지
     */
    public String getCustomMessage() {
        return customMessage;
    }

    /**
     * 사용자 정의 메시지를 설정합니다.
     * 이 메시지는 배너에 추가로 표시되는 사용자 정의 텍스트입니다.
     *
     * @param customMessage 사용자 정의 메시지
     */
    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    /**
     * 다른 BannerInfo 객체의 정보로 현재 객체를 업데이트합니다.
     * null이 아닌 필드만 업데이트됩니다.
     *
     * @param info 업데이트할 배너 정보
     */
    public void update(BannerInfo info) {
        if (info == null) {
            return;
        }
        if (info.getAppName() != null) {
            this.appName = info.getAppName();
        }
        if (info.getVersion() != null) {
            this.version = info.getVersion();
        }
        if (info.getBuildTime() != null) {
            this.buildTime = info.getBuildTime();
        }
        if (info.getJavaVersion() != null) {
            this.javaVersion = info.getJavaVersion();
        }
        if (info.getOsName() != null) {
            this.osName = info.getOsName();
        }
        if (info.getOsVersion() != null) {
            this.osVersion = info.getOsVersion();
        }
        if (info.getOsArch() != null) {
            this.osArch = info.getOsArch();
        }
        if (info.getCustomMessage() != null) {
            this.customMessage = info.getCustomMessage();
        }
    }
} 