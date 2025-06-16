package io.csh.utils.logging;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class AsyncLogger extends Logger implements Runnable {
    private static final int QUEUE_CAPACITY = 1000;
    private final BlockingQueue<LogMessage> queue;
    private final AtomicBoolean running;
    private final Thread worker;

    public AsyncLogger(String name) {
        super(name);
        this.queue = new LinkedBlockingQueue<>(QUEUE_CAPACITY);
        this.running = new AtomicBoolean(true);
        this.worker = new Thread(this, "AsyncLogger-" + name);
        this.worker.start();
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                LogMessage message = queue.take();
                super.log(message.getLevel(), message.getMessage(), message.getThrowable());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    protected void log(LogLevel level, String message, Throwable throwable) {
        if (!this.level.isEnabled(level)) {
            return;
        }

        LogMessage logMessage = new LogMessage(level, name, message, throwable);
        queue.offer(logMessage);

        if (level == LogLevel.FATAL) {
            // TODO: API 연동 구현
        }
    }

    public void shutdown() {
        running.set(false);
        worker.interrupt();
    }
} 