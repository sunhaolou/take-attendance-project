package edu.duke.ece651.project.team5.shared.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTest {
    private Admin admin;

    @Mock
    private Admin mockedAdmin;

    @BeforeEach
    public void setUp() {
        admin = new Admin("testAdmin", "password123");
    }

    @Test
    public void testGetUsername() {
        assertEquals("testAdmin", admin.getUsername());
    }

    @Test
    public void testSetUsername() {
        admin.setUsername("newAdmin");
        assertEquals("newAdmin", admin.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", admin.getPassword());
    }

    @Test
    public void testSetPassword() {
        admin.setPassword("newPassword");
        assertEquals("newPassword", admin.getPassword());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("testAdmin", admin.getUsername());
        assertEquals("password123", admin.getPassword());
    }

    @Test
    public void testEquals() {
        mockedAdmin = new Admin("testAdmin", "password123");

    }

    @Test
    public void testNotEquals() {
        mockedAdmin = new Admin("admin2", "password456");

    }
}
