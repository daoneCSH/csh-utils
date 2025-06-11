package io.csh.utils.logging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogCategoryTest {
    
    @Test
    void testCategoryValues() {
        assertEquals("GENERAL", LogCategory.GENERAL.getValue());
        assertEquals("DB", LogCategory.DB.getValue());
        assertEquals("WEB", LogCategory.WEB.getValue());
        assertEquals("NETWORK", LogCategory.NETWORK.getValue());
        assertEquals("SECURITY", LogCategory.SECURITY.getValue());
        assertEquals("PERFORMANCE", LogCategory.PERFORMANCE.getValue());
    }

    @Test
    void testCategoryEnumeration() {
        LogCategory[] categories = LogCategory.values();
        assertEquals(6, categories.length);
        
        assertTrue(containsCategory(categories, "GENERAL"));
        assertTrue(containsCategory(categories, "DB"));
        assertTrue(containsCategory(categories, "WEB"));
        assertTrue(containsCategory(categories, "NETWORK"));
        assertTrue(containsCategory(categories, "SECURITY"));
        assertTrue(containsCategory(categories, "PERFORMANCE"));
    }

    private boolean containsCategory(LogCategory[] categories, String value) {
        for (LogCategory category : categories) {
            if (category.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
} 