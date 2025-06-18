package io.csh.utils.output;

import java.io.IOException;

/**
 * 출력 작성자 인터페이스
 */
public interface OutputWriter {
    /**
     * 한 줄을 출력합니다.
     *
     * @param line 출력할 줄
     * @throws IOException 출력 중 오류가 발생한 경우
     */
    void writeLine(String line) throws IOException;

    /**
     * 출력을 닫습니다.
     *
     * @throws IOException 닫기 중 오류가 발생한 경우
     */
    void close() throws IOException;
} 