package io.csh.utils.logging;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 중복 로그를 방지하는 필터 클래스
 */
public final class DuplicateLogFilter {
    private static final ConcurrentMap<String, Long> lastLogTimes = new ConcurrentHashMap<>();
    private static final int MAX_ENTRIES = 1000;
    
    private DuplicateLogFilter() {}
    
    /**
     * 중복 로그인지 확인합니다.
     * 
     * @param id 로그 식별자
     * @param minIntervalSeconds 최소 간격 (초)
     * @return true if log should be allowed
     */
    public static boolean shouldLog(String id, int minIntervalSeconds) {
        if (minIntervalSeconds <= 0) {
            return true;
        }
        
        long now = System.currentTimeMillis();
        Long lastTime = lastLogTimes.get(id);
        
        if (lastTime == null) {
            // 새로운 로그
            lastLogTimes.put(id, now);
            cleanupIfNeeded();
            return true;
        }
        
        long timeDiff = now - lastTime;
        if (timeDiff >= minIntervalSeconds * 1000L) {
            // 충분한 시간이 지났음
            lastLogTimes.put(id, now);
            return true;
        }
        
        // 중복 로그
        return false;
    }
    
    /**
     * 특정 ID의 로그 기록을 제거합니다.
     * 
     * @param id 로그 식별자
     */
    public static void removeLogRecord(String id) {
        lastLogTimes.remove(id);
    }
    
    /**
     * 모든 로그 기록을 제거합니다.
     */
    public static void clear() {
        lastLogTimes.clear();
    }
    
    /**
     * 맵 크기가 제한을 초과하면 오래된 항목들을 제거합니다.
     */
    private static void cleanupIfNeeded() {
        if (lastLogTimes.size() > MAX_ENTRIES) {
            // 간단한 정리: 맵의 절반을 제거
            int toRemove = MAX_ENTRIES / 2;
            lastLogTimes.entrySet().stream()
                .limit(toRemove)
                .forEach(entry -> lastLogTimes.remove(entry.getKey()));
        }
    }
    
    /**
     * 현재 저장된 로그 기록 수를 반환합니다.
     * 
     * @return 로그 기록 수
     */
    public static int size() {
        return lastLogTimes.size();
    }
} 