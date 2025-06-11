package io.csh.utils.logging;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 비동기 로깅을 위한 클래스
 */
public class AsyncLogger {
    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void initialize() {
        if (executor.isShutdown()) {
            executor = Executors.newSingleThreadExecutor();
        }
    }

    public static void debug(String message) {
        executor.submit(() -> {
            try {
                Logger.debug(message);
            } catch (Exception e) {
                System.err.println("Failed to write async debug log: " + e.getMessage());
            }
        });
    }

    public static void info(String message) {
        executor.submit(() -> {
            try {
                Logger.info(message);
            } catch (Exception e) {
                System.err.println("Failed to write async info log: " + e.getMessage());
            }
        });
    }

    public static void warn(String message) {
        executor.submit(() -> {
            try {
                Logger.warn(message);
            } catch (Exception e) {
                System.err.println("Failed to write async warn log: " + e.getMessage());
            }
        });
    }

    public static void error(String message) {
        executor.submit(() -> {
            try {
                Logger.error(message);
            } catch (Exception e) {
                System.err.println("Failed to write async error log: " + e.getMessage());
            }
        });
    }

    public static void error(String message, Throwable t) {
        executor.submit(() -> {
            try {
                Logger.error(message, t);
            } catch (Exception e) {
                System.err.println("Failed to write async error log: " + e.getMessage());
            }
        });
    }

    public static void shutdown() {
        executor.shutdown();
    }
} 