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
} 