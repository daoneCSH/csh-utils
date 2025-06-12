package io.csh.utils.logging;

import io.csh.utils.core.exception.CshException;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 로깅 유틸리티 클래스
 */
public class Logger {
    private static final LogConfig config = LogConfig.getInstance();
    private static final ConcurrentHashMap<LogCategory, FileWriter> categoryWriters = new ConcurrentHashMap<>();
    private static final ReentrantLock writerLock = new ReentrantLock();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void debug(String message) {
        try {
            log(LogCategory.GENERAL, LogLevel.DEBUG, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write debug log: " + e.getMessage());
        }
    }

    public static void info(String message) {
        try {
            log(LogCategory.GENERAL, LogLevel.INFO, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write info log: " + e.getMessage());
        }
    }

    public static void warn(String message) {
        try {
            log(LogCategory.GENERAL, LogLevel.WARN, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write warn log: " + e.getMessage());
        }
    }

    public static void error(String message) {
        try {
            log(LogCategory.GENERAL, LogLevel.ERROR, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write error log: " + e.getMessage());
        }
    }

    public static void error(String message, Throwable t) {
        try {
            log(LogCategory.GENERAL, LogLevel.ERROR, message, t);
        } catch (CshException e) {
            System.err.println("Failed to write error log: " + e.getMessage());
        }
    }

    public static void debugDb(String message) {
        try {
            log(LogCategory.DB, LogLevel.DEBUG, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write DB debug log: " + e.getMessage());
        }
    }

    public static void infoDb(String message) {
        try {
            log(LogCategory.DB, LogLevel.INFO, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write DB info log: " + e.getMessage());
        }
    }

    public static void warnDb(String message) {
        try {
            log(LogCategory.DB, LogLevel.WARN, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write DB warn log: " + e.getMessage());
        }
    }

    public static void errorDb(String message) {
        try {
            log(LogCategory.DB, LogLevel.ERROR, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write DB error log: " + e.getMessage());
        }
    }

    public static void debugWeb(String message) {
        try {
            log(LogCategory.WEB, LogLevel.DEBUG, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write Web debug log: " + e.getMessage());
        }
    }

    public static void infoWeb(String message) {
        try {
            log(LogCategory.WEB, LogLevel.INFO, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write Web info log: " + e.getMessage());
        }
    }

    public static void warnWeb(String message) {
        try {
            log(LogCategory.WEB, LogLevel.WARN, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write Web warn log: " + e.getMessage());
        }
    }

    public static void errorWeb(String message) {
        try {
            log(LogCategory.WEB, LogLevel.ERROR, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write Web error log: " + e.getMessage());
        }
    }

    private static void log(LogCategory category, LogLevel level, String message, Throwable t) throws CshException {
        if (config.isEnabled(level)) {
            String logMessage = String.format("%s [%s] %s - %s",
                    dateFormat.format(System.currentTimeMillis()),
                    level.name(),
                    category.getValue(),
                    message);

            if (t != null) {
                logMessage += "\n" + t.toString();
            }

            try {
                FileWriter writer = getWriter(category);
                writerLock.lock();
                try {
                    writer.write(logMessage + "\n");
                    writer.flush();
                } finally {
                    writerLock.unlock();
                }
            } catch (Exception e) {
                throw new CshException("Failed to write log", e);
            }
        }
    }

    private static FileWriter getWriter(LogCategory category) throws CshException {
        return categoryWriters.computeIfAbsent(category, k -> {
            try {
                File logDir = new File(config.getLogDirectory());
                if (!logDir.exists()) {
                    logDir.mkdirs();
                }
                return new FileWriter(new File(logDir, category.getValue() + ".log"), true);
            } catch (Exception e) {
                throw new CshException("Failed to create log writer", e);
            }
        });
    }
} 