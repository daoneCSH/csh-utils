package io.csh.utils.logging;

import io.csh.utils.core.exception.CshException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 로깅을 위한 유틸리티 클래스
 */
public class Logger {
    private static final LogConfig config = LogConfig.getInstance();
    private static final String LOG_FILE = config.getLogDirectory() + "/application.log";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static FileWriter writer;
    private static LogRotation logRotation;

    static {
        try {
            // 로그 디렉토리 생성
            File logDir = new File(config.getLogDirectory());
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            // 로그 파일 생성
            File logFile = new File(LOG_FILE);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            // 로그 로테이션 설정
            logRotation = new LogRotation(LOG_FILE, config.getMaxFileSize(), config.getMaxBackupCount());
            
            // 파일 writer 초기화
            writer = new FileWriter(logFile, true);
        } catch (IOException e) {
            System.err.println("로그 파일 초기화 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 디버그 레벨 로그를 기록합니다.
     */
    public static void debug(String message) {
        try {
            log(LogLevel.DEBUG, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write debug log: " + e.getMessage());
        }
    }

    /**
     * 정보 레벨 로그를 기록합니다.
     */
    public static void info(String message) {
        try {
            log(LogLevel.INFO, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write info log: " + e.getMessage());
        }
    }

    /**
     * 경고 레벨 로그를 기록합니다.
     */
    public static void warn(String message) {
        try {
            log(LogLevel.WARN, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write warn log: " + e.getMessage());
        }
    }

    /**
     * 에러 레벨 로그를 기록합니다.
     */
    public static void error(String message) {
        try {
            log(LogLevel.ERROR, message, null);
        } catch (CshException e) {
            System.err.println("Failed to write error log: " + e.getMessage());
        }
    }

    /**
     * 에러 레벨 로그를 예외와 함께 기록합니다.
     */
    public static void error(String message, Throwable throwable) {
        try {
            log(LogLevel.ERROR, message, throwable);
        } catch (CshException e) {
            System.err.println("Failed to write error log: " + e.getMessage());
        }
    }

    /**
     * 로그를 기록합니다.
     */
    private static synchronized void log(LogLevel level, String message, Throwable throwable) throws CshException {
        try {
            // 날짜 변경 체크
            logRotation.checkDateChange();

            // 로그 메시지 생성
            String logMessage = String.format("%s [%s] - %s",
                    dateFormat.format(new Date()),
                    level.name(),
                    message);

            // 예외 정보 추가
            if (throwable != null) {
                logMessage += "\n" + throwable.toString();
                for (StackTraceElement element : throwable.getStackTrace()) {
                    logMessage += "\n\tat " + element.toString();
                }
            }

            // 로그 파일에 기록
            writer.write(logMessage + "\n");
            writer.flush();

            // 파일 크기 업데이트 및 로테이션 체크
            logRotation.updateFileSize(logMessage.getBytes().length + 1); // +1 for newline

        } catch (IOException e) {
            throw new CshException("Failed to write log: " + e.getMessage());
        }
    }

    /**
     * 로그 파일을 닫습니다.
     */
    public static void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to close log file: " + e.getMessage());
        }
    }
} 