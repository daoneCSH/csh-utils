package io.csh.logging;

import io.csh.core.exception.CshException;
import io.csh.core.exception.ErrorCode;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * CSH Utils의 로깅 유틸리티 클래스입니다.
 */
public final class Logger {
    private Logger() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 로거를 가져옵니다.
     *
     * @param clazz 로거를 가져올 클래스
     * @return SLF4J 로거
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * 로거를 가져옵니다.
     *
     * @param name 로거 이름
     * @return SLF4J 로거
     */
    public static Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    /**
     * TRACE 레벨 로그를 출력합니다.
     *
     * @param logger 로거
     * @param message 로그 메시지
     */
    public static void trace(Logger logger, String message) {
        if (logger.isTraceEnabled()) {
            logger.trace(message);
        }
    }

    /**
     * TRACE 레벨 로그를 출력합니다.
     *
     * @param logger 로거
     * @param format 로그 메시지 포맷
     * @param args 포맷 인자
     */
    public static void trace(Logger logger, String format, Object... args) {
        if (logger.isTraceEnabled()) {
            logger.trace(format, args);
        }
    }

    /**
     * DEBUG 레벨 로그를 출력합니다.
     *
     * @param logger 로거
     * @param message 로그 메시지
     */
    public static void debug(Logger logger, String message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }

    /**
     * DEBUG 레벨 로그를 출력합니다.
     *
     * @param logger 로거
     * @param format 로그 메시지 포맷
     * @param args 포맷 인자
     */
    public static void debug(Logger logger, String format, Object... args) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, args);
        }
    }

    /**
     * INFO 레벨 로그를 출력합니다.
     *
     * @param logger 로거
     * @param message 로그 메시지
     */
    public static void info(Logger logger, String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

    /**
     * INFO 레벨 로그를 출력합니다.
     *
     * @param logger 로거
     * @param format 로그 메시지 포맷
     * @param args 포맷 인자
     */
    public static void info(Logger logger, String format, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(format, args);
        }
    }

    /**
     * WARN 레벨 로그를 출력합니다.
     *
     * @param logger 로거
     * @param message 로그 메시지
     */
    public static void warn(Logger logger, String message) {
        if (logger.isWarnEnabled()) {
            logger.warn(message);
        }
    }

    /**
     * WARN 레벨 로그를 출력합니다.
     *
     * @param logger 로거
     * @param format 로그 메시지 포맷
     * @param args 포맷 인자
     */
    public static void warn(Logger logger, String format, Object... args) {
        if (logger.isWarnEnabled()) {
            logger.warn(format, args);
        }
    }

    /**
     * ERROR 레벨 로그를 출력합니다.
     *
     * @param logger 로거
     * @param message 로그 메시지
     */
    public static void error(Logger logger, String message) {
        if (logger.isErrorEnabled()) {
            logger.error(message);
        }
    }

    /**
     * ERROR 레벨 로그를 출력합니다.
     *
     * @param logger 로거
     * @param format 로그 메시지 포맷
     * @param args 포맷 인자
     */
    public static void error(Logger logger, String format, Object... args) {
        if (logger.isErrorEnabled()) {
            logger.error(format, args);
        }
    }

    /**
     * ERROR 레벨 로그를 출력합니다.
     *
     * @param logger 로거
     * @param message 로그 메시지
     * @param t 예외
     */
    public static void error(Logger logger, String message, Throwable t) {
        if (logger.isErrorEnabled()) {
            logger.error(message, t);
        }
    }
} 