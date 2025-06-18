package io.csh.utils.core.util;

/**
 * 비교 관련 유틸리티 클래스
 */
public final class CompareUtil {
    private CompareUtil() {
        throw new AssertionError("Utility class");
    }

    /**
     * 두 개의 비교 가능한 객체를 비교합니다.
     *
     * @param <T> 비교할 객체의 타입
     * @param o1 첫 번째 객체
     * @param o2 두 번째 객체
     * @return 비교 결과 (o1 &lt; o2: -1, o1 == o2: 0, o1 &gt; o2: 1)
     */
    public static <T extends Comparable<T>> int compare(T o1, T o2) {
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
     * 두 개의 비교 가능한 객체를 비교합니다. null 값은 지정된 기본값으로 처리됩니다.
     *
     * @param <T> 비교할 객체의 타입
     * @param o1 첫 번째 객체
     * @param o2 두 번째 객체
     * @param nullGreater null 값이 더 큰 값으로 처리될지 여부
     * @return 비교 결과 (o1 &lt; o2: -1, o1 == o2: 0, o1 &gt; o2: 1)
     */
    public static <T extends Comparable<T>> int compare(T o1, T o2, boolean nullGreater) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return nullGreater ? 1 : -1;
        }
        if (o2 == null) {
            return nullGreater ? -1 : 1;
        }
        return o1.compareTo(o2);
    }
} 