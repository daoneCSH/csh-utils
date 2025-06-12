package io.csh.utils.thread;

/**
 * 스레드 관련 유틸리티 클래스
 */
public class ThreadUtil {
    private ThreadUtil() {
        // Prevent instantiation
    }

    /**
     * 현재 스레드의 스택 트레이스를 문자열로 반환합니다.
     *
     * @return 스택 트레이스 문자열
     */
    public static String getStackTrace() {
        return getStackTrace(Thread.currentThread());
    }

    /**
     * 지정된 스레드의 스택 트레이스를 문자열로 반환합니다.
     *
     * @param thread 스레드
     * @return 스택 트레이스 문자열
     */
    public static String getStackTrace(Thread thread) {
        StringBuilder sb = new StringBuilder();
        sb.append("Thread: ").append(thread.getName())
          .append(" (id: ").append(thread.getId())
          .append(", state: ").append(thread.getState()).append(")\n");

        StackTraceElement[] elements = thread.getStackTrace();
        for (StackTraceElement element : elements) {
            sb.append("\tat ").append(element.toString()).append("\n");
        }
        return sb.toString();
    }

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
        }
    }

    /**
     * 현재 스레드가 인터럽트되었는지 확인합니다.
     *
     * @return 인터럽트되었으면 true
     */
    public static boolean isInterrupted() {
        return Thread.currentThread().isInterrupted();
    }
} 