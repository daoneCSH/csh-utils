package io.csh.utils.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * 로깅 시스템의 설정을 관리하는 클래스
 * 로그 레벨, 포맷, 파일 경로 등 로깅 관련 설정을 담당
 */
public final class PropertyManager {
    private static final Map<String, String> propertyCache = new ConcurrentHashMap<>();
    
    private PropertyManager() {
        throw new IllegalStateException("PropertyManager class");
    }
    
    /**
     * 프로퍼티 값을 가져옴
     * @param key 프로퍼티 키
     * @return 프로퍼티 값
     */
    public static String getProperty(String key) {
        return propertyCache.get(key);
    }
    
    /**
     * 프로퍼티 값을 설정
     * @param key 프로퍼티 키
     * @param value 프로퍼티 값
     */
    public static void setProperty(String key, String value) {
        if (value != null) {
            propertyCache.put(key, value);
        }
    }
    
    /**
     * 프로퍼티 값을 제거
     * @param key 프로퍼티 키
     */
    public static void removeProperty(String key) {
        propertyCache.remove(key);
    }
    
    /**
     * 모든 프로퍼티를 제거
     */
    public static void clearProperties() {
        propertyCache.clear();
    }
    
    /**
     * 프로퍼티가 존재하는지 확인
     * @param key 프로퍼티 키
     * @return 프로퍼티 존재 여부
     */
    public static boolean hasProperty(String key) {
        return propertyCache.containsKey(key);
    }
    
    /**
     * application.properties에서 프로퍼티를 로드
     */
    public static void loadApplicationProperties() {
        try {
            Properties props = new Properties();
            InputStream input = PropertyManager.class.getClassLoader().getResourceAsStream("application.properties");
            if (input != null) {
                props.load(input);
                for (String key : props.stringPropertyNames()) {
                    propertyCache.put(key, props.getProperty(key));
                }
            }
        } catch (IOException e) {
            System.err.println("application.properties 읽기 실패: " + e.getMessage());
        }
    }
} 