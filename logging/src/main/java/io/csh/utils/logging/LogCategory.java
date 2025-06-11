package io.csh.utils.logging;

/**
 * 로그 카테고리 열거형
 */
public enum LogCategory {
    GENERAL("GENERAL"),
    DB("DB"),
    WEB("WEB"),
    NETWORK("NETWORK"),
    SECURITY("SECURITY"),
    PERFORMANCE("PERFORMANCE");

    private final String value;

    LogCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
} 