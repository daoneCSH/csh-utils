package io.csh.util;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

/**
 * 파일 처리 유틸리티 클래스
 */
@UtilityClass
public class FileUtil {
    /**
     * 파일이 존재하는지 확인
     *
     * @param path 파일 경로
     * @return 파일이 존재하면 true
     */
    public static boolean exists(String path) {
        return Files.exists(Paths.get(path));
    }

    /**
     * 파일이 디렉토리인지 확인
     *
     * @param path 파일 경로
     * @return 디렉토리이면 true
     */
    public static boolean isDirectory(String path) {
        return Files.isDirectory(Paths.get(path));
    }

    /**
     * 파일이 일반 파일인지 확인
     *
     * @param path 파일 경로
     * @return 일반 파일이면 true
     */
    public static boolean isFile(String path) {
        return Files.isRegularFile(Paths.get(path));
    }

    /**
     * 파일을 읽어서 문자열로 반환
     *
     * @param path 파일 경로
     * @return 파일 내용
     * @throws IOException 파일 읽기 실패 시
     */
    public static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }

    /**
     * 파일을 읽어서 문자열 리스트로 반환
     *
     * @param path 파일 경로
     * @return 파일 내용 라인 리스트
     * @throws IOException 파일 읽기 실패 시
     */
    public static List<String> readLines(String path) throws IOException {
        return Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
    }

    /**
     * 문자열을 파일에 쓰기
     *
     * @param path 파일 경로
     * @param content 쓸 내용
     * @throws IOException 파일 쓰기 실패 시
     */
    public static void writeFile(String path, String content) throws IOException {
        Files.write(Paths.get(path), content.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 문자열 리스트를 파일에 쓰기
     *
     * @param path 파일 경로
     * @param lines 쓸 라인 리스트
     * @throws IOException 파일 쓰기 실패 시
     */
    public static void writeLines(String path, List<String> lines) throws IOException {
        Files.write(Paths.get(path), lines, StandardCharsets.UTF_8);
    }

    /**
     * 파일 삭제
     *
     * @param path 파일 경로
     * @return 삭제 성공 시 true
     * @throws IOException 파일 삭제 실패 시
     */
    public static boolean deleteFile(String path) throws IOException {
        return Files.deleteIfExists(Paths.get(path));
    }

    /**
     * 디렉토리 생성
     *
     * @param path 디렉토리 경로
     * @throws IOException 디렉토리 생성 실패 시
     */
    public static void createDirectory(String path) throws IOException {
        Files.createDirectories(Paths.get(path));
    }

    /**
     * 파일 복사
     *
     * @param source 원본 파일 경로
     * @param target 대상 파일 경로
     * @throws IOException 파일 복사 실패 시
     */
    public static void copyFile(String source, String target) throws IOException {
        Files.copy(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 파일 이동
     *
     * @param source 원본 파일 경로
     * @param target 대상 파일 경로
     * @throws IOException 파일 이동 실패 시
     */
    public static void moveFile(String source, String target) throws IOException {
        Files.move(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
    }
} 