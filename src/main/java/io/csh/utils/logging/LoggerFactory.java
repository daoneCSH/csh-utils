package io.csh.utils.logging;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 로거 팩토리
 */
public final class LoggerFactory {
    private static final ConcurrentMap<String, Logger> loggers = new ConcurrentHashMap<>();

    private LoggerFactory() {
        throw new AssertionError("Utility class");
    }

    /**
     * 로거를 반환합니다.
     *
     * @param clazz 로거를 사용할 클래스
     * @return Logger 인스턴스
     */
    public static Logger getLogger(Class<?> clazz) {
        return loggers.computeIfAbsent(clazz.getName(), k -> LoggerImpl.getInstance(clazz));
    }
} 