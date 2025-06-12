package io.csh.utils.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {
    @Test
    void testConstants() {
        assertEquals("UTF-8", Constants.DEFAULT_ENCODING);
        assertEquals("yyyy-MM-dd HH:mm:ss", Constants.DEFAULT_DATE_FORMAT);
        assertEquals("Asia/Seoul", Constants.DEFAULT_TIME_ZONE);
    }
} 