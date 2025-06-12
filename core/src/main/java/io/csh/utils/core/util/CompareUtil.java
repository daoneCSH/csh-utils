package io.csh.utils.core.util;

import lombok.experimental.UtilityClass;

/**
 * Utility class for comparison operations.
 */
@UtilityClass
public class CompareUtil {
    /**
     * Compares two comparable objects.
     *
     * @param <T> the type of objects to be compared
     * @param o1 the first object to be compared
     * @param o2 the second object to be compared
     * @return the comparison result (o1 < o2: -1, o1 == o2: 0, o1 > o2: 1)
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
     * Checks if the given object is not null.
     *
     * @param <T> the type of the object to check
     * @param obj the object to check
     * @param message the message to use in the exception if the check fails
     * @return the object if it is not null
     * @throws NullPointerException if the object is null
     */
    public static <T> T checkNotNull(T obj, String message) {
        if (obj == null) {
            throw new NullPointerException(message);
        }
        return obj;
    }

    /**
     * Checks if the given object is not null.
     *
     * @param <T> the type of the object to check
     * @param obj the object to check
     * @return the object if it is not null
     * @throws NullPointerException if the object is null
     */
    public static <T> T checkNotNull(T obj) {
        return checkNotNull(obj, "Object must not be null");
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
} 