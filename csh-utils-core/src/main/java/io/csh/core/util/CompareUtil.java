package io.csh.core.util;

import lombok.experimental.UtilityClass;

/**
 * 비교 연산 유틸리티 클래스
 */
@UtilityClass
public class CompareUtil {
    /**
     * 두 객체를 비교
     *
     * @param o1 첫 번째 객체
     * @param o2 두 번째 객체
     * @return 비교 결과 (o1 < o2: -1, o1 == o2: 0, o1 > o2: 1)
     */
    public static <T extends Comparable<T>> int compareTo(T o1, T o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        return o1.compareTo(o2);
    }

    /**
     * 두 객체가 동일한지 확인
     *
     * @param o1 첫 번째 객체
     * @param o2 두 번째 객체
     * @return 동일하면 true
     */
    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        return o1.equals(o2);
    }

    /**
     * 객체가 null인지 확인하고 null이면 예외 발생
     *
     * @param obj 확인할 객체
     * @param message 예외 메시지
     * @return null이 아닌 객체
     * @throws IllegalArgumentException 객체가 null인 경우
     */
    public static <T> T checkNotNull(T obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
        return obj;
    }

    /**
     * 객체가 null인지 확인하고 null이면 예외 발생
     *
     * @param obj 확인할 객체
     * @return null이 아닌 객체
     * @throws IllegalArgumentException 객체가 null인 경우
     */
    public static <T> T checkNotNull(T obj) {
        return checkNotNull(obj, "Object must not be null");
    }
} 