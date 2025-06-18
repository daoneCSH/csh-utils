package io.csh.utils.logging;

/**
 * Enum for log levels.
 *
 * Log levels are defined in order of priority. If a higher level is set,
 * lower level logs will not be output.
 *
 * Order: TRACE &lt; DEBUG &lt; INFO &lt; WARN &lt; ERROR
 */
public enum LogLevel {
    /**
     * Most detailed debug information.
     * - Method entry/exit
     * - Variable tracking
     * - Detailed execution flow
     */
    TRACE(0),
    
    /**
     * Debug information for development.
     * - Branching info
     * - Intermediate results
     * - Performance measurement points
     */
    DEBUG(1),
    
    /**
     * General informational messages.
     * - Application start/stop
     * - Major business logic
     * - Configuration info
     */
    INFO(2),
    
    /**
     * Warning messages (potential issues).
     * - Possible performance degradation
     * - Deprecated usage
     * - Temporary workarounds
     */
    WARN(3),
    
    /**
     * Error and exception situations.
     * - Exception occurred
     * - System error
     * - Unrecoverable situations
     */
    ERROR(4);

    private final int level;

    /**
     * Create LogLevel.
     * @param level level value
     */
    LogLevel(int level) {
        this.level = level;
    }

    /**
     * Check if this level is enabled.
     * @param currentLevel current log level
     * @return true if enabled
     */
    public boolean isEnabled(LogLevel currentLevel) {
        return this.level >= currentLevel.level;
    }

    /**
     * Find LogLevel from string.
     * @param level log level string
     * @return LogLevel instance
     * @throws IllegalArgumentException if unsupported
     */
    public static LogLevel fromString(String level) {
        if (level == null) {
            return INFO; // default
        }
        
        try {
            return valueOf(level.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported log level: " + level);
        }
    }

    /**
     * Get level value.
     * @return level value
     */
    public int getLevel() {
        return level;
    }
} 