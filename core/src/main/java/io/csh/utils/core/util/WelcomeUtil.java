package io.csh.utils.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * 애플리케이션 시작 시 버전 정보와 빌드 정보를 표시하는 유틸리티 클래스입니다.
 * ASCII 아트 로고와 프로젝트 설명을 커스터마이징할 수 있습니다.
 *
 * <h2>사용 예시:</h2>
 * <pre>
 * // 기본 사용
 * WelcomeUtil welcome = new WelcomeUtil();
 * welcome.showWelcome();
 *
 * // 커스텀 로고와 설명 사용
 * String customLogo = """
 *     ╔═══════════════════════════════════════╗
 *     ║             My Project                ║
 *     ╚═══════════════════════════════════════╝
 *     """;
 * String customDescription = "My Project - A powerful application";
 * WelcomeUtil welcome = new WelcomeUtil(customLogo, customDescription);
 * welcome.showWelcome();
 * </pre>
 *
 * <h2>버전 정보:</h2>
 * 버전 정보는 다음 순서로 확인됩니다:
 * <ol>
 *     <li>io/csh/utils/v.properties 파일의 VERSION 속성</li>
 *     <li>확인할 수 없는 경우 "unknown" 반환</li>
 * </ol>
 *
 * <h2>빌드 시간:</h2>
 * 빌드 시간은 다음 순서로 확인됩니다:
 * <ol>
 *     <li>META-INF/MANIFEST.MF 파일의 Build-Time 속성</li>
 *     <li>io/csh/utils/v.properties 파일의 BUILD 속성</li>
 *     <li>확인할 수 없는 경우 null 반환</li>
 * </ol>
 *
 * @since 1.0.0
 */
public class WelcomeUtil {
    private static final Logger log = LoggerFactory.getLogger(WelcomeUtil.class);
    private final String logo;
    private final String description;

    /**
     * 기본 생성자입니다.
     * 로고 없이 기본 설명만 표시합니다.
     */
    public WelcomeUtil() {
        this.logo = null;
        this.description = "CSH Utils - A collection of utility libraries for Java applications";
    }

    /**
     * 커스텀 로고와 설명을 지정하는 생성자입니다.
     *
     * @param logo ASCII 아트 로고 문자열
     * @param description 프로젝트 설명
     */
    public WelcomeUtil(String logo, String description) {
        this.logo = logo;
        this.description = description;
    }

    /**
     * 버전 정보와 빌드 정보를 포함한 웰컴 메시지를 표시합니다.
     * 로고가 지정된 경우 먼저 로고를 표시하고,
     * 그 다음 프로젝트 설명, 패키지 이름, 버전, 빌드 시간을 표시합니다.
     */
    public void showWelcome() {
        if (logo != null && !logo.isEmpty()) {
            log.info("\n{}", logo);
        }

        Package objPackage = this.getClass().getPackage();
        String name = objPackage.getSpecificationTitle();
        String version = getVersion();

        log.info("===========================================================================================");
        log.info(description);
        log.info("===========================================================================================");
    
        log.info("* Package name: " + name);
        log.info("* Package version: " + version);
        log.info("* Last build time : " + getBuildTime());
        log.info("===========================================================================================");
    }

    /**
     * 프로젝트의 버전 정보를 반환합니다.
     * io/csh/utils/v.properties 파일에서 VERSION 속성을 읽어옵니다.
     *
     * @return 버전 문자열, 확인할 수 없는 경우 "unknown"
     */
    private String getVersion() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader == null) {
                classLoader = WelcomeUtil.class.getClassLoader();
            }
            if (classLoader != null) {
                Properties props = new Properties();
                InputStream is = classLoader.getResourceAsStream("io/csh/utils/v.properties");
                if (is != null) {
                    props.load(is);
                    return props.getProperty("VERSION", "unknown");
                }
            }
        } catch (Exception e) {
            log.warn("Failed to read Version", e);
        }
        return "unknown";
    }

    /**
     * 프로젝트의 빌드 시간을 반환합니다.
     * META-INF/MANIFEST.MF 또는 v.properties 파일에서 빌드 시간을 읽어옵니다.
     *
     * @return yyyy-MM-dd HH:mm:ss 형식의 빌드 시간 문자열, 확인할 수 없는 경우 null
     */
    private String getBuildTime() {
        String buildTime = null;
        try {
            // 1. MANIFEST.MF에서 먼저 시도
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader == null) {
                classLoader = WelcomeUtil.class.getClassLoader();
            }
            if (classLoader != null) {
                InputStream is = classLoader.getResourceAsStream("META-INF/MANIFEST.MF");
                if (is != null) {
                    Manifest manifest = new Manifest(is);
                    Attributes attributes = manifest.getMainAttributes();
                    buildTime = attributes.getValue("Build-Time");
                }
            }
            
            // 2. MANIFEST.MF에서 찾지 못하면 v.properties에서 시도
            if (buildTime == null) {
                Properties props = new Properties();
                InputStream is = classLoader.getResourceAsStream("io/csh/utils/v.properties");
                if (is != null) {
                    props.load(is);
                    buildTime = props.getProperty("BUILD");
                }
            }
            
            // 3. 날짜 형식 변환
            if (buildTime != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = sdf.parse(buildTime);
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                buildTime = sdf.format(date);
            }
        } catch (Exception e) {
            log.warn("Failed to read or parse Build-Time", e);
        }
        return buildTime;
    }

    /**
     * 프로젝트의 빌드 카운트를 반환합니다.
     * v.properties 파일의 VERSION 속성에서 빌드 카운트를 추출합니다.
     *
     * @return 빌드 카운트 문자열, 확인할 수 없는 경우 "unknown"
     */
    private String getBuildCount() {
        String buildCount = null;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader == null) {
                classLoader = WelcomeUtil.class.getClassLoader();
            }
            if (classLoader != null) {
                Properties props = new Properties();
                InputStream is = classLoader.getResourceAsStream("io/csh/utils/v.properties");
                if (is != null) {
                    props.load(is);
                    String version = props.getProperty("VERSION");
                    if (version != null && version.contains("-")) {
                        buildCount = version.substring(version.lastIndexOf("-") + 1);
                    }
                }
            }
        } catch (Exception e) {
            log.warn("Failed to read Build-Count", e);
        }
        return buildCount != null ? buildCount : "unknown";
    }
} 