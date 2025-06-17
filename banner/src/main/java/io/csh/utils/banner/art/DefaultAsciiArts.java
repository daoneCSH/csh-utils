package io.csh.utils.banner.art;

/**
 * 기본 ASCII 아트를 제공하는 클래스입니다.
 */
public final class DefaultAsciiArts {
    /**
     * 기본 스타일의 ASCII 아트를 반환합니다.
     */
    public static final String BASIC = """
            ___  ___  _  _       _   _  _____  ___  _     ___
           / __|/ __|| || | ___ | | | ||_   _||_ _|| |   / __|
          | (__ \\__ \\| __ ||___|| |_| |  | |   | | | |__ \\__ \\
           \\___||___/|_||_|      \\___/   |_|  |___||____||___/
        """;

    private DefaultAsciiArts() {
        // 인스턴스화 방지
    }
} 