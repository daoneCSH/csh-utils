package io.csh.util;

import lombok.experimental.UtilityClass;

/**
 * 문자열 처리 유틸리티 클래스
 */
@UtilityClass
public class StringUtil {
    /**
     * 문자열이 null이거나 빈 문자열인지 확인
     *
     * @param str 확인할 문자열
     * @return null이거나 빈 문자열이면 true
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 문자열이 null이 아니고 빈 문자열이 아닌지 확인
     *
     * @param str 확인할 문자열
     * @return null이 아니고 빈 문자열이 아니면 true
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 문자열의 첫 글자를 소문자로 변환
     *
     * @param str 변환할 문자열
     * @return 첫 글자가 소문자로 변환된 문자열
     */
    public static String lowerFirst(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 문자열의 첫 글자를 대문자로 변환
     *
     * @param str 변환할 문자열
     * @return 첫 글자가 대문자로 변환된 문자열
     */
    public static String upperFirst(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 문자열의 앞뒤 공백을 제거
     *
     * @param str 제거할 문자열
     * @return 공백이 제거된 문자열
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 문자열을 지정된 구분자로 분리
     *
     * @param str 분리할 문자열
     * @param delimiter 구분자
     * @return 분리된 문자열 배열
     */
    public static String[] split(String str, String delimiter) {
        if (isEmpty(str)) {
            return new String[0];
        }
        return str.split(delimiter);
    }

    /**
     * 문자열의 지정된 범위를 지움
     *
     * @param str 원본 문자열
     * @param start 시작 인덱스
     * @param end 종료 인덱스
     * @return 지정된 범위가 지워진 문자열
     */
    public static String erase(String str, int start, int end) {
        if (isEmpty(str)) {
            return str;
        }
        if (start < 0) {
            start = 0;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start >= end) {
            return str;
        }
        return str.substring(0, start) + str.substring(end);
    }

    /**
     * 문자열을 지정된 길이만큼 왼쪽에 패딩
     *
     * @param str 원본 문자열
     * @param length 목표 길이
     * @param padChar 패딩 문자
     * @return 패딩된 문자열
     */
    public static String lpad(String str, int length, char padChar) {
        if (str == null) {
            return null;
        }
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = str.length(); i < length; i++) {
            sb.append(padChar);
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     * 문자열을 지정된 길이만큼 오른쪽에 패딩
     *
     * @param str 원본 문자열
     * @param length 목표 길이
     * @param padChar 패딩 문자
     * @return 패딩된 문자열
     */
    public static String rpad(String str, int length, char padChar) {
        if (str == null) {
            return null;
        }
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder(length);
        sb.append(str);
        for (int i = str.length(); i < length; i++) {
            sb.append(padChar);
        }
        return sb.toString();
    }
} 