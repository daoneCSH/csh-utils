package io.csh.utils.logging;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 로그 파일 로테이션을 관리하는 클래스
 */
public class LogRotation {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
    
    private final String logFile;
    private final long maxFileSize;
    private final int maxBackupCount;
    private long currentFileSize;
    private String currentDate;

    public LogRotation(String logFile, long maxFileSize, int maxBackupCount) {
        if (maxFileSize <= 0) {
            throw new IllegalArgumentException("최대 파일 크기는 0보다 커야 합니다.");
        }
        if (maxBackupCount < 0) {
            throw new IllegalArgumentException("최대 백업 파일 수는 0 이상이어야 합니다.");
        }
        
        this.logFile = logFile;
        this.maxFileSize = maxFileSize;
        this.maxBackupCount = maxBackupCount;
        this.currentFileSize = 0;
        this.currentDate = dateFormat.format(new Date());
    }

    /**
     * 로그 파일 크기를 업데이트하고 필요한 경우 로테이션을 수행합니다.
     */
    public synchronized void updateFileSize(long bytesWritten) {
        currentFileSize += bytesWritten;
        if (currentFileSize >= maxFileSize) {
            rotate();
        }
    }

    /**
     * 날짜가 변경되었는지 확인하고 필요한 경우 로테이션을 수행합니다.
     */
    public synchronized void checkDateChange() {
        String today = dateFormat.format(new Date());
        if (!today.equals(currentDate)) {
            rotate();
            currentDate = today;
        }
    }

    /**
     * 로그 파일을 로테이션합니다.
     */
    private void rotate() {
        try {
            File currentLogFile = new File(logFile);
            if (!currentLogFile.exists()) {
                return;
            }

            // 기존 백업 파일들의 번호를 증가
            for (int i = maxBackupCount - 1; i >= 0; i--) {
                File oldFile = new File(getBackupFileName(i));
                File newFile = new File(getBackupFileName(i + 1));
                if (oldFile.exists()) {
                    if (i == maxBackupCount - 1) {
                        oldFile.delete();
                    } else {
                        oldFile.renameTo(newFile);
                    }
                }
            }

            // 현재 로그 파일을 백업
            File backupFile = new File(getBackupFileName(0));
            currentLogFile.renameTo(backupFile);

            // 새로운 로그 파일 생성
            currentLogFile.createNewFile();
            currentFileSize = 0;
        } catch (IOException e) {
            System.err.println("로그 파일 로테이션 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 백업 파일 이름을 생성합니다.
     */
    private String getBackupFileName(int index) {
        if (index == 0) {
            return logFile + "." + currentDate;
        }
        return logFile + "." + currentDate + "." + index;
    }
} 