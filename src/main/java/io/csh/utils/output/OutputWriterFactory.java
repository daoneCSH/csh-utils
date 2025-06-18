package io.csh.utils.output;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 출력 작성자 팩토리
 */
public final class OutputWriterFactory {
    private static final ConcurrentMap<String, OutputWriter> writers = new ConcurrentHashMap<>();

    private OutputWriterFactory() {
        throw new AssertionError("Utility class");
    }

    /**
     * 출력 작성자를 반환합니다.
     *
     * @param name 출력 작성자 이름
     * @return OutputWriter 인스턴스
     * @throws IOException 출력 작성자 생성 중 오류가 발생한 경우
     */
    public static OutputWriter getWriter(String name) throws IOException {
        return writers.computeIfAbsent(name, k -> {
            try {
                return FileOutputWriter.getInstance(k);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create output writer: " + k, e);
            }
        });
    }
} 