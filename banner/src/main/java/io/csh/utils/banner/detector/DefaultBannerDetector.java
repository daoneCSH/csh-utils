package io.csh.utils.banner.detector;

import io.csh.utils.banner.model.BannerInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.jar.Manifest;

/**
 * 기본 배너 정보 감지기 구현체입니다.
 * JAR 매니페스트와 시스템 속성을 통해 애플리케이션 정보를 자동으로 감지합니다.
 * 
 * <p>주요 기능:
 * <ul>
 *   <li>JAR 매니페스트에서 애플리케이션 정보 감지</li>
 *   <li>시스템 속성에서 Java 버전, OS 정보 감지</li>
 *   <li>application.properties 파일에서 추가 정보 감지</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>
 * DefaultBannerDetector detector = new DefaultBannerDetector();
 * BannerInfo info = detector.detect();
 * </pre>
 */
public class DefaultBannerDetector implements BannerDetector {
    private static final String MANIFEST_PATH = "/META-INF/MANIFEST.MF";
    private static final String APPLICATION_PROPERTIES = "/application.properties";

    /**
     * 배너 정보를 감지합니다.
     * JAR 매니페스트와 시스템 속성을 통해 애플리케이션 정보를 수집합니다.
     *
     * @return 감지된 배너 정보
     */
    @Override
    public BannerInfo detect() {
        BannerInfo info = new BannerInfo();
        detectFromManifest(info);
        detectFromProperties(info);
        return info;
    }

    /**
     * 감지된 배너 정보를 기존 배너 정보에 적용합니다.
     * null이 아닌 필드만 업데이트됩니다.
     *
     * @param info 기존 배너 정보
     * @return 업데이트된 배너 정보
     */
    @Override
    public BannerInfo detectAndUpdate(BannerInfo info) {
        if (info == null) {
            return detect();
        }
        detectFromManifest(info);
        detectFromProperties(info);
        return info;
    }

    /**
     * 감지기가 유효한지 검사합니다.
     * 기본 감지기는 항상 유효하다고 판단합니다.
     *
     * @return 감지기 유효성 여부
     */
    @Override
    public boolean isValid() {
        return true;
    }

    /**
     * JAR 매니페스트에서 배너 정보를 감지합니다.
     * Implementation-Title, Implementation-Version, Build-Time 등의 정보를 수집합니다.
     *
     * @param info 업데이트할 배너 정보
     */
    private void detectFromManifest(BannerInfo info) {
        try (InputStream is = getClass().getResourceAsStream(MANIFEST_PATH)) {
            if (is != null) {
                Manifest manifest = new Manifest(is);
                var attributes = manifest.getMainAttributes();
                
                String appName = attributes.getValue("Implementation-Title");
                if (appName != null) {
                    info.setAppName(appName);
                }

                String version = attributes.getValue("Implementation-Version");
                if (version != null) {
                    info.setVersion(version);
                }

                String buildTime = attributes.getValue("Build-Time");
                if (buildTime != null) {
                    info.setBuildTime(buildTime);
                }
            }
        } catch (IOException e) {
            // 매니페스트 파일을 찾을 수 없는 경우 무시
        }
    }

    /**
     * application.properties 파일에서 배너 정보를 감지합니다.
     * spring.application.name, application.version 등의 정보를 수집합니다.
     *
     * @param info 업데이트할 배너 정보
     */
    private void detectFromProperties(BannerInfo info) {
        try (InputStream is = getClass().getResourceAsStream(APPLICATION_PROPERTIES)) {
            if (is != null) {
                Properties props = new Properties();
                props.load(is);

                String appName = props.getProperty("spring.application.name");
                if (appName != null && info.getAppName() == null) {
                    info.setAppName(appName);
                }

                String version = props.getProperty("application.version");
                if (version != null && info.getVersion() == null) {
                    info.setVersion(version);
                }
            }
        } catch (IOException e) {
            // properties 파일을 찾을 수 없는 경우 무시
        }
    }
} 