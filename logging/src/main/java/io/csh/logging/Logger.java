package io.csh.logging;

import lombok.experimental.UtilityClass;

/**
 * Utility class for logging operations.
 */
@UtilityClass
public class Logger {
    /**
     * Logs an error message with an exception.
     *
     * @param message the error message
     * @param e the exception
     */
    public static void error(String message, Throwable e) {
        System.err.println("[ERROR] " + message);
        if (e != null) {
            e.printStackTrace();
        }
    }

    /**
     * Logs an error message.
     *
     * @param message the error message
     */
    public static void error(String message) {
        error(message, null);
    }

    /**
     * Logs an info message.
     *
     * @param message the info message
     */
    public static void info(String message) {
        System.out.println("[INFO] " + message);
    }

    /**
     * Logs a debug message.
     *
     * @param message the debug message
     */
    public static void debug(String message) {
        System.out.println("[DEBUG] " + message);
    }

    /**
     * Logs a warning message.
     *
     * @param message the warning message
     */
    public static void warn(String message) {
        System.out.println("[WARN] " + message);
    }
} 