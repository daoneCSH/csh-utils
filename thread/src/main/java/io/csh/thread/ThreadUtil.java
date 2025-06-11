package io.csh.thread;

import io.csh.utils.logging.Logger;
import lombok.experimental.UtilityClass;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 스레드 유틸리티 클래스
 */
@UtilityClass
public class ThreadUtil {
    private static final AtomicInteger threadNumber = new AtomicInteger(1);

    /**
     * 현재 스레드를 지정된 시간만큼 대기시킵니다.
     *
     * @param millis 대기 시간 (밀리초)
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.error("Thread interrupted", e);
        }
    }

    /**
     * 현재 스레드를 지정된 시간만큼 대기시킵니다.
     *
     * @param time 대기 시간
     * @param unit 시간 단위
     */
    public static void sleep(long time, TimeUnit unit) {
        sleep(unit.toMillis(time));
    }

    /**
     * 현재 스레드의 이름을 반환합니다.
     *
     * @return 스레드 이름
     */
    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    /**
     * 현재 스레드의 ID를 반환합니다.
     *
     * @return 스레드 ID
     */
    public static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    /**
     * 현재 스레드의 우선순위를 반환합니다.
     *
     * @return 스레드 우선순위
     */
    public static int getCurrentThreadPriority() {
        return Thread.currentThread().getPriority();
    }

    /**
     * 현재 스레드의 상태를 반환합니다.
     *
     * @return 스레드 상태
     */
    public static Thread.State getCurrentThreadState() {
        return Thread.currentThread().getState();
    }

    /**
     * 현재 스레드가 데몬 스레드인지 확인합니다.
     *
     * @return 데몬 스레드이면 true
     */
    public static boolean isCurrentThreadDaemon() {
        return Thread.currentThread().isDaemon();
    }

    /**
     * 현재 스레드가 중단되었는지 확인합니다.
     *
     * @return 중단되었으면 true
     */
    public static boolean isCurrentThreadInterrupted() {
        return Thread.currentThread().isInterrupted();
    }

    /**
     * 현재 스레드의 스택 트레이스를 반환합니다.
     *
     * @return 스택 트레이스
     */
    public static StackTraceElement[] getCurrentThreadStackTrace() {
        return Thread.currentThread().getStackTrace();
    }

    /**
     * 현재 스레드의 스택 트레이스를 문자열로 반환합니다.
     *
     * @return 스택 트레이스 문자열
     */
    public static String getCurrentThreadStackTraceString() {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = getCurrentThreadStackTrace();
        for (StackTraceElement element : stackTrace) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * 현재 스레드의 컨텍스트 클래스 로더를 반환합니다.
     *
     * @return 컨텍스트 클래스 로더
     */
    public static ClassLoader getCurrentThreadContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 현재 스레드의 컨텍스트 클래스 로더를 설정합니다.
     *
     * @param classLoader 컨텍스트 클래스 로더
     */
    public static void setCurrentThreadContextClassLoader(ClassLoader classLoader) {
        Thread.currentThread().setContextClassLoader(classLoader);
    }

    /**
     * 데몬 스레드 팩토리를 생성합니다.
     *
     * @param namePrefix 스레드 이름 접두사
     * @return 데몬 스레드 팩토리
     */
    public static ThreadFactory createDaemonThreadFactory(String namePrefix) {
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, namePrefix + "-" + threadNumber.getAndIncrement());
                t.setDaemon(true);
                t.setUncaughtExceptionHandler((thread, throwable) -> {
                    Logger.error("Uncaught exception in thread " + thread.getName(), throwable);
                });
                return t;
            }
        };
    }
} 