package io.csh.utils.core.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.InvocationTargetException;

class ConstantsTest {

    @Test
    void testCommonConstants() {
        assertEquals("", Constants.Common.EMPTY_STRING);
        assertEquals(" ", Constants.Common.SPACE);
        assertEquals(".", Constants.Common.DOT);
        assertEquals(",", Constants.Common.COMMA);
        assertEquals(":", Constants.Common.COLON);
        assertEquals(";", Constants.Common.SEMICOLON);
        assertEquals("_", Constants.Common.UNDERSCORE);
        assertEquals("-", Constants.Common.HYPHEN);
        assertEquals("/", Constants.Common.SLASH);
        assertEquals("\\", Constants.Common.BACKSLASH);
        assertEquals(System.lineSeparator(), Constants.Common.NEWLINE);
        assertEquals("\t", Constants.Common.TAB);
    }

    @Test
    void testSystemConstants() {
        assertNotNull(Constants.System.OS_NAME);
        assertNotNull(Constants.System.OS_VERSION);
        assertNotNull(Constants.System.OS_ARCH);
        assertNotNull(Constants.System.USER_NAME);
        assertNotNull(Constants.System.USER_HOME);
        assertNotNull(Constants.System.USER_DIR);
        assertNotNull(Constants.System.FILE_SEPARATOR);
        assertNotNull(Constants.System.PATH_SEPARATOR);
        assertNotNull(Constants.System.LINE_SEPARATOR);
    }

    @Test
    void testErrorConstants() {
        assertEquals("Unknown error occurred", Constants.Error.UNKNOWN_ERROR);
        assertEquals("Invalid argument", Constants.Error.INVALID_ARGUMENT);
        assertEquals("Argument cannot be null", Constants.Error.NULL_ARGUMENT);
        assertEquals("Argument cannot be empty", Constants.Error.EMPTY_ARGUMENT);
        assertEquals("File not found", Constants.Error.FILE_NOT_FOUND);
        assertEquals("File access denied", Constants.Error.FILE_ACCESS_DENIED);
        assertEquals("File already exists", Constants.Error.FILE_ALREADY_EXISTS);
        assertEquals("Directory not found", Constants.Error.DIRECTORY_NOT_FOUND);
        assertEquals("Directory access denied", Constants.Error.DIRECTORY_ACCESS_DENIED);
        assertEquals("Directory already exists", Constants.Error.DIRECTORY_ALREADY_EXISTS);
    }

    @Test
    void testConstantsInstantiation() throws Exception {
        InvocationTargetException ex1 = assertThrows(InvocationTargetException.class, () -> {
            var constructor = Constants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });
        assertTrue(ex1.getCause() instanceof IllegalStateException);

        InvocationTargetException ex2 = assertThrows(InvocationTargetException.class, () -> {
            var constructor = Constants.Common.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });
        assertTrue(ex2.getCause() instanceof IllegalStateException);

        InvocationTargetException ex3 = assertThrows(InvocationTargetException.class, () -> {
            var constructor = Constants.System.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });
        assertTrue(ex3.getCause() instanceof IllegalStateException);

        InvocationTargetException ex4 = assertThrows(InvocationTargetException.class, () -> {
            var constructor = Constants.Error.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });
        assertTrue(ex4.getCause() instanceof IllegalStateException);
    }
} 