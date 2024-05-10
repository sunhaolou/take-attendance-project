package edu.duke.ece651.project.team5.shared.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EnrollmentStatusTest {
  @Test
    public void testGetById() {
        assertEquals(EnrollmentStatus.ENROLLED, EnrollmentStatus.getById(1));
        assertEquals(EnrollmentStatus.DROPPED, EnrollmentStatus.getById(2));
    }

    @Test
    public void testGetById_NotFound() {
        assertNull(EnrollmentStatus.getById(3));
    }

    @Test
    public void testGetId() {
        assertEquals(1, EnrollmentStatus.ENROLLED.getId());
        assertEquals(2, EnrollmentStatus.DROPPED.getId());
    }

    @Test
    public void testGetStatus() {
        assertEquals("ENROLLED", EnrollmentStatus.ENROLLED.getStatus());
        assertEquals("DROPPED", EnrollmentStatus.DROPPED.getStatus());
    }
}
