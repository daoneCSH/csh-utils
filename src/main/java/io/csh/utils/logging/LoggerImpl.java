package io.csh.utils.logging;

/**
 * 로거 구현체
 */
public final class LoggerImpl implements Logger {
    private final String name;

    private LoggerImpl(Class<?> clazz) {
        this.name = clazz.getName();
    }

    /**
     * 로거 인스턴스를 반환합니다.
     *
     * @param clazz 로거를 사용할 클래스
     * @return LoggerImpl 인스턴스
     */
    public static LoggerImpl getInstance(Class<?> clazz) {
        return new LoggerImpl(clazz);
    }

    @Override
    public void info(String message) {
        System.out.println("[INFO] " + name + " - " + message);
    }

    @Override
    public void info(String message, Throwable thrown) {
        System.out.println("[INFO] " + name + " - " + message);
        thrown.printStackTrace(System.out);
    }

    @Override
    public void error(String message) {
        System.err.println("[ERROR] " + name + " - " + message);
    }

    @Override
    public void error(String message, Throwable thrown) {
        System.err.println("[ERROR] " + name + " - " + message);
        thrown.printStackTrace(System.err);
    }
} 