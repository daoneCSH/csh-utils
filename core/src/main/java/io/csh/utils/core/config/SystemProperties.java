package io.csh.utils.core.config;

import io.csh.utils.core.constants.Constants;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * 시스템 프로퍼티를 관리하는 유틸리티 클래스
 * 체인 패턴을 사용하여 다양한 소스에서 프로퍼티 값을 가져올 수 있음
 * 
 * 주요 기능:
 * - PropertyChain: 프로퍼티 체인을 통해 시스템 프로퍼티, application.properties, JAR 파일명 등에서 값을 조회
 * - PropertyManager: 프로퍼티 캐시를 관리하며, 프로퍼티 값을 설정, 조회, 제거, 초기화하는 기능 제공
 */
public final class SystemProperties {
    private static final Map<String, String> propertyCache = new ConcurrentHashMap<>();
    
    private SystemProperties() {
        throw new IllegalStateException("SystemProperties class");
    }
    
    /**
     * 프로퍼티 체인을 시작하는 메서드
     * @param key 프로퍼티 키
     * @return PropertyChain 인스턴스
     */
    public static PropertyChain getProperty(String key) {
        return new PropertyChain(key);
    }
    
    /**
     * 프로퍼티를 관리하는 내부 클래스
     * 
     * 프로퍼티 캐시를 통해 프로퍼티 값을 관리하며, 
     * 프로퍼티 값을 설정, 조회, 제거, 초기화하는 기능을 제공합니다.
     */
    public static final class PropertyManager {
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
    }
    
    /**
     * 프로퍼티 체인을 관리하는 내부 클래스
     * 
     * 체인 패턴을 통해 다양한 소스에서 프로퍼티 값을 조회하고,
     * 기본값을 설정하여 최종 값을 반환합니다.
     */
    public static class PropertyChain {
        private final String key;
        private String value;
        private boolean found;
        
        private PropertyChain(String key) {
            this.key = key;
        }
        
        /**
         * 시스템 프로퍼티에서 값을 확인
         */
        public PropertyChain checkSystemProperty() {
            if (!found) {
                value = System.getProperty(key);
                found = (value != null && !value.isEmpty());
            }
            return this;
        }
        
        /**
         * application.properties에서 값을 확인
         */
        public PropertyChain checkApplicationProperties() {
            if (!found) {
                try {
                    Properties props = new Properties();
                    InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
                    if (input != null) {
                        props.load(input);
                        value = props.getProperty(key);
                        found = (value != null && !value.isEmpty());
                    }
                } catch (IOException e) {
                    System.err.println("application.properties 읽기 실패: " + e.getMessage());
                }
            }
            return this;
        }
        
        /**
         * JAR 파일명에서 값을 확인
         */
        public PropertyChain checkJarFileName() {
            if (!found) {
                String classPath = System.getProperty("java.class.path");
                if (classPath != null) {
                    String[] paths = classPath.split(File.pathSeparator);
                    for (String path : paths) {
                        File file = new File(path);
                        String name = file.getName();
                        if (name.endsWith(".jar")) {
                            value = name.substring(0, name.length() - 4);
                            found = true;
                            break;
                        }
                    }
                }
            }
            return this;
        }
        
        /**
         * 기본값을 설정하고 최종 값을 반환
         * @param defaultValue 기본값
         * @return 찾은 값 또는 기본값
         */
        public String orElse(String defaultValue) {
            String result = found ? value : defaultValue;
            if (result != null) {
                propertyCache.put(key, result);
            }
            return result;
        }
        
        /**
         * Boolean 타입으로 값을 반환
         * @param defaultValue 기본값
         * @return 찾은 값 또는 기본값
         */
        public boolean orElse(boolean defaultValue) {
            if (!found) {
                propertyCache.put(key, String.valueOf(defaultValue));
                return defaultValue;
            }
            boolean result = Boolean.parseBoolean(value);
            propertyCache.put(key, String.valueOf(result));
            return result;
        }
        
        /**
         * Integer 타입으로 값을 반환
         * @param defaultValue 기본값
         * @return 찾은 값 또는 기본값
         */
        public int orElse(int defaultValue) {
            if (!found) {
                propertyCache.put(key, String.valueOf(defaultValue));
                return defaultValue;
            }
            try {
                int result = Integer.parseInt(value);
                propertyCache.put(key, String.valueOf(result));
                return result;
            } catch (NumberFormatException e) {
                propertyCache.put(key, String.valueOf(defaultValue));
                return defaultValue;
            }
        }
        
        /**
         * Long 타입으로 값을 반환
         * @param defaultValue 기본값
         * @return 찾은 값 또는 기본값
         */
        public long orElse(long defaultValue) {
            if (!found) {
                propertyCache.put(key, String.valueOf(defaultValue));
                return defaultValue;
            }
            try {
                long result = Long.parseLong(value);
                propertyCache.put(key, String.valueOf(result));
                return result;
            } catch (NumberFormatException e) {
                propertyCache.put(key, String.valueOf(defaultValue));
                return defaultValue;
            }
        }
    }
} 