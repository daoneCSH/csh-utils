package io.csh.utils.logging;

/**
 * 비동기 로깅 설정 클래스
 */
public class AsyncLogConfig {
    private final int queueSize;
    private final int batchSize;
    private final long flushInterval;
    private final int threadPoolSize;
    private final boolean discardOnOverflow;

    private AsyncLogConfig(Builder builder) {
        this.queueSize = builder.queueSize;
        this.batchSize = builder.batchSize;
        this.flushInterval = builder.flushInterval;
        this.threadPoolSize = builder.threadPoolSize;
        this.discardOnOverflow = builder.discardOnOverflow;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public long getFlushInterval() {
        return flushInterval;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public boolean isDiscardOnOverflow() {
        return discardOnOverflow;
    }

    public static class Builder {
        private int queueSize = 10000;
        private int batchSize = 100;
        private long flushInterval = 1000; // milliseconds
        private int threadPoolSize = 1;
        private boolean discardOnOverflow = true;

        public Builder queueSize(int queueSize) {
            if (queueSize <= 0) {
                throw new IllegalArgumentException("Queue size must be positive");
            }
            this.queueSize = queueSize;
            return this;
        }

        public Builder batchSize(int batchSize) {
            if (batchSize <= 0) {
                throw new IllegalArgumentException("Batch size must be positive");
            }
            this.batchSize = batchSize;
            return this;
        }

        public Builder flushInterval(long flushInterval) {
            if (flushInterval <= 0) {
                throw new IllegalArgumentException("Flush interval must be positive");
            }
            this.flushInterval = flushInterval;
            return this;
        }

        public Builder threadPoolSize(int threadPoolSize) {
            if (threadPoolSize <= 0) {
                throw new IllegalArgumentException("Thread pool size must be positive");
            }
            this.threadPoolSize = threadPoolSize;
            return this;
        }

        public Builder discardOnOverflow(boolean discardOnOverflow) {
            this.discardOnOverflow = discardOnOverflow;
            return this;
        }

        public AsyncLogConfig build() {
            return new AsyncLogConfig(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
} 