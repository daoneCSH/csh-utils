package io.csh.utils.integration;

import io.csh.utils.logging.Logger;
import io.csh.utils.logging.LoggerFactory;
import io.csh.utils.output.OutputWriter;
import io.csh.utils.output.OutputWriterFactory;

/**
 * CSH Utils의 통합 인터페이스
 * 로깅과 출력 기능을 통합하여 제공합니다.
 */
public final class CSHUtils {
    private CSHUtils() {
        throw new AssertionError("Utility class");
    }

    /**
     * 로깅 기능을 제공하는 내부 클래스
     */
    public static final class Logging {
        private Logging() {
            throw new AssertionError("Utility class");
        }

        /**
         * 정보 로그를 기록합니다.
         * @param clazz 로거를 사용할 클래스
         * @param message 로그 메시지
         */
        public static void info(Class<?> clazz, String message) {
            LoggerFactory.getLogger(clazz).info(message);
        }

        /**
         * 정보 로그를 기록합니다.
         * @param clazz 로거를 사용할 클래스
         * @param message 로그 메시지
         * @param thrown 예외 정보
         */
        public static void info(Class<?> clazz, String message, Throwable thrown) {
            LoggerFactory.getLogger(clazz).info(message, thrown);
        }

        /**
         * 에러 로그를 기록합니다.
         * @param clazz 로거를 사용할 클래스
         * @param message 로그 메시지
         */
        public static void error(Class<?> clazz, String message) {
            LoggerFactory.getLogger(clazz).error(message);
        }

        /**
         * 에러 로그를 기록합니다.
         * @param clazz 로거를 사용할 클래스
         * @param message 로그 메시지
         * @param thrown 예외 정보
         */
        public static void error(Class<?> clazz, String message, Throwable thrown) {
            LoggerFactory.getLogger(clazz).error(message, thrown);
        }
    }

    /**
     * 출력 기능을 제공하는 내부 클래스
     */
    public static final class Output {
        private Output() {
            throw new AssertionError("Utility class");
        }

        /**
         * 메시지를 표준 출력으로 출력합니다.
         * @param message 출력할 메시지
         */
        public static void write(String message) {
            System.out.println(message);
        }

        /**
         * 메시지를 표준 에러 출력으로 출력합니다.
         * @param message 출력할 메시지
         */
        public static void writeError(String message) {
            System.err.println(message);
        }
    }

    /**
     * 로깅과 출력을 통합하여 제공하는 내부 클래스
     */
    public static final class Integration {
        private Integration() {
            throw new AssertionError("Utility class");
        }

        /**
         * 로그를 기록하고 표준 출력으로 출력합니다.
         * @param clazz 로거를 사용할 클래스
         * @param message 로그 메시지
         */
        public static void logAndOutput(Class<?> clazz, String message) {
            Logging.info(clazz, message);
            Output.write(message);
        }

        /**
         * 로그를 기록하고 표준 에러 출력으로 출력합니다.
         * @param clazz 로거를 사용할 클래스
         * @param message 로그 메시지
         * @param thrown 예외 정보
         */
        public static void logAndOutput(Class<?> clazz, String message, Throwable thrown) {
            Logging.info(clazz, message, thrown);
            Output.writeError(message);
            thrown.printStackTrace();
        }
    }
} 