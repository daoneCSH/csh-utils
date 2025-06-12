package io.csh.utils.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 스레드 풀 관리 유틸리티
 */
public class ThreadPool {
    private static final AtomicInteger threadNumber = new AtomicInteger(1);
    private static final ThreadFactory defaultThreadFactory = r -> {
        Thread t = new Thread(r, "csh-thread-" + threadNumber.getAndIncrement());
        t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    };

    private static ExecutorService executorService;

    private ThreadPool() {
        // Prevent instantiation
    }

    /**
     * 스레드 풀을 초기화합니다.
     *
     * @param poolSize 스레드 풀 크기
     */
    public static void initialize(int poolSize) {
        if (executorService != null) {
            throw new IllegalStateException("ThreadPool is already initialized");
        }
        executorService = Executors.newFixedThreadPool(poolSize, defaultThreadFactory);
    }

    /**
     * 작업을 스레드 풀에 제출합니다.
     *
     * @param task 실행할 작업
     */
    public static void submit(Runnable task) {
        if (executorService == null) {
            throw new IllegalStateException("ThreadPool is not initialized");
        }
        executorService.submit(task);
    }

    /**
     * 스레드 풀을 종료합니다.
     */
    public static void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
            executorService = null;
        }
    }

    /**
     * 스레드 풀이 종료되었는지 확인합니다.
     *
     * @return 스레드 풀이 종료되었으면 true
     */
    public static boolean isShutdown() {
        return executorService == null || executorService.isShutdown();
    }
} 