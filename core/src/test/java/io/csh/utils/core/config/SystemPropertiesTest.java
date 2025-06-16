package io.csh.utils.core.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SystemPropertiesTest {

    @Test
    void testSystemProperty() {
        // Given
        String key = "test.key";
        String value = "test.value";
        System.setProperty(key, value);

        // When
        String result = SystemProperties.getProperty(key)
            .checkSystemProperty()
            .orElse("default");

        // Then
        assertEquals(value, result);
        assertEquals(value, SystemProperties.PropertyManager.getProperty(key));

        // Cleanup
        System.clearProperty(key);
        SystemProperties.PropertyManager.removeProperty(key);
    }

    @Test
    void testDefaultValue() {
        // Given
        String key = "non.existent.key";

        // When
        String result = SystemProperties.getProperty(key)
            .checkSystemProperty()
            .orElse("default");

        // Then
        assertEquals("default", result);
        assertEquals("default", SystemProperties.PropertyManager.getProperty(key));

        // Cleanup
        SystemProperties.PropertyManager.removeProperty(key);
    }

    @Test
    void testBooleanValue() {
        // Given
        String key = "test.boolean";
        System.setProperty(key, "true");

        // When
        boolean result = SystemProperties.getProperty(key)
            .checkSystemProperty()
            .orElse(false);

        // Then
        assertTrue(result);
        assertEquals("true", SystemProperties.PropertyManager.getProperty(key));

        // Cleanup
        System.clearProperty(key);
        SystemProperties.PropertyManager.removeProperty(key);
    }

    @Test
    void testIntegerValue() {
        // Given
        String key = "test.int";
        System.setProperty(key, "42");

        // When
        int result = SystemProperties.getProperty(key)
            .checkSystemProperty()
            .orElse(0);

        // Then
        assertEquals(42, result);
        assertEquals("42", SystemProperties.PropertyManager.getProperty(key));

        // Cleanup
        System.clearProperty(key);
        SystemProperties.PropertyManager.removeProperty(key);
    }

    @Test
    void testLongValue() {
        // Given
        String key = "test.long";
        System.setProperty(key, "9223372036854775807");

        // When
        long result = SystemProperties.getProperty(key)
            .checkSystemProperty()
            .orElse(0L);

        // Then
        assertEquals(Long.MAX_VALUE, result);
        assertEquals("9223372036854775807", SystemProperties.PropertyManager.getProperty(key));

        // Cleanup
        System.clearProperty(key);
        SystemProperties.PropertyManager.removeProperty(key);
    }

    @Test
    void testInvalidNumberFormat() {
        // Given
        String key = "test.invalid";
        System.setProperty(key, "not.a.number");

        // When
        int result = SystemProperties.getProperty(key)
            .checkSystemProperty()
            .orElse(42);

        // Then
        assertEquals(42, result);
        assertEquals("42", SystemProperties.PropertyManager.getProperty(key));

        // Cleanup
        System.clearProperty(key);
        SystemProperties.PropertyManager.removeProperty(key);
    }

    @Test
    void testPropertyChain() {
        // Given
        String key = "test.chain";
        System.setProperty(key, "system.value");

        // When
        String result = SystemProperties.getProperty(key)
            .checkSystemProperty()
            .checkApplicationProperties()
            .checkJarFileName()
            .orElse("default");

        // Then
        assertEquals("system.value", result);
        assertEquals("system.value", SystemProperties.PropertyManager.getProperty(key));

        // Cleanup
        System.clearProperty(key);
        SystemProperties.PropertyManager.removeProperty(key);
    }

    @Test
    void testPropertyManager() {
        // Given
        String key = "test.manager";
        String value = "test.value";

        // When
        SystemProperties.PropertyManager.setProperty(key, value);

        // Then
        assertTrue(SystemProperties.PropertyManager.hasProperty(key));
        assertEquals(value, SystemProperties.PropertyManager.getProperty(key));

        // When
        SystemProperties.PropertyManager.removeProperty(key);

        // Then
        assertFalse(SystemProperties.PropertyManager.hasProperty(key));
        assertNull(SystemProperties.PropertyManager.getProperty(key));

        // When
        SystemProperties.PropertyManager.setProperty(key, value);
        SystemProperties.PropertyManager.clearProperties();

        // Then
        assertFalse(SystemProperties.PropertyManager.hasProperty(key));
        assertNull(SystemProperties.PropertyManager.getProperty(key));
    }
} 