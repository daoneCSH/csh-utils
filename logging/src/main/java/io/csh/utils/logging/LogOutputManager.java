package io.csh.utils.logging;

import java.io.PrintStream;

public class LogOutputManager {
    private static final LogOutputManager INSTANCE = new LogOutputManager();
    private PrintStream output;

    private LogOutputManager() {
        this.output = System.out;
    }

    public static LogOutputManager getInstance() {
        return INSTANCE;
    }

    public void output(LogMessage logMessage) {
        output.println(logMessage);
    }

    public void setOutput(PrintStream output) {
        this.output = output;
    }

    /**
     * 테스트를 위해 출력 스트림을 초기화합니다.
     */
    public void reset() {
        this.output = System.out;
    }
} 