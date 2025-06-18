package io.csh.utils.output;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 파일 출력 작성자
 */
public final class FileOutputWriter implements OutputWriter {
    private final PrintWriter writer;
    private final String fileName;

    private FileOutputWriter(String fileName) throws IOException {
        this.fileName = fileName;
        File logDir = new File("logs");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
        String formattedFileName = String.format("%s_%s.log", fileName, LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        this.writer = new PrintWriter(new File(logDir, formattedFileName));
    }

    /**
     * FileOutputWriter 인스턴스를 생성합니다.
     *
     * @param fileName 파일 이름
     * @return FileOutputWriter 인스턴스
     * @throws IOException 파일 생성 중 오류가 발생한 경우
     */
    public static FileOutputWriter getInstance(String fileName) throws IOException {
        return new FileOutputWriter(fileName);
    }

    @Override
    public void writeLine(String line) throws IOException {
        writer.println(line);
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
} 