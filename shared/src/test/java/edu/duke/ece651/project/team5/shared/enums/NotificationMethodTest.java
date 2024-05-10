package edu.duke.ece651.project.team5.shared.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NotificationMethodTest {
    @Test
    public void testGetById_ValidId() {
        int validId = 1;
        NotificationMethod method = NotificationMethod.getById(validId);
        assertNotNull(method);
        assertEquals(validId, method.getId());
    }

    @Test
    public void testGetById_InvalidId() {
        int invalidId = 3; // Assuming ID 3 is not present in the enum
        NotificationMethod method = NotificationMethod.getById(invalidId);
        assertNull(method);
    }

    @Test
    public void testGetMethod() {
        NotificationMethod method = NotificationMethod.EMAIL;
        assertEquals("EMAIL", method.getMethod());
    }
}
