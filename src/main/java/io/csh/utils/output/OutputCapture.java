package io.csh.utils.output;

import java.io.IOException;
import java.io.PrintStream;

/**
 * 출력 캡처
 */
public final class OutputCapture implements AutoCloseable {
    private final PrintStream originalOut;
    private final PrintStream originalErr;
    private final OutputWriter writer;

    private OutputCapture(OutputWriter writer) {
        this.writer = writer;
        this.originalOut = System.out;
        this.originalErr = System.err;
    }

    /**
     * OutputCapture 인스턴스를 생성합니다.
     *
     * @param writer 출력 작성자
     * @return OutputCapture 인스턴스
     */
    public static OutputCapture create(OutputWriter writer) {
        return new OutputCapture(writer);
    }

    /**
     * 출력 캡처를 시작합니다.
     */
    public void start() {
        System.setOut(new PrintStream(new OutputWriterOutputStream(writer)));
        System.setErr(new PrintStream(new OutputWriterOutputStream(writer)));
    }

    /**
     * 출력 캡처를 중지합니다.
     */
    public void stop() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Override
    public void close() throws IOException {
        stop();
        writer.close();
    }

    private static class OutputWriterOutputStream extends java.io.OutputStream {
        private final OutputWriter writer;
        private final StringBuilder buffer = new StringBuilder();

        private OutputWriterOutputStream(OutputWriter writer) {
            this.writer = writer;
        }

        @Override
        public void write(int b) throws IOException {
            if (b == '\n') {
                writer.writeLine(buffer.toString());
                buffer.setLength(0);
            } else {
                buffer.append((char) b);
            }
        }
    }
} 