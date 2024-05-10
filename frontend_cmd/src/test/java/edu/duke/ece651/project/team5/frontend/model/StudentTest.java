package edu.duke.ece651.project.team5.frontend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.project.team5.frontend.model.Student;
import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;

public class StudentTest {

    @Test
    public void testDefaultConstructor() {
        Student student = new Student();

        assertEquals("", student.getNetId());
        assertEquals("", student.getPassword());
        assertEquals("", student.getLegalName());
        assertEquals("", student.getPreferredName());
        assertEquals("", student.getEmail());
        assertEquals("", student.getPhone());
        assertEquals(new HashMap<String, EnrollmentStatus>(), student.getEnrolledCourseSections());
        assertEquals(new HashMap<String, NotificationPreference>(), student.getNotificationPreferences());
    }

    @Test
    public void testParameterizedConstructor() {
        Student student = new Student("alice123", "password123", "Alice", "Alice Smith", "alice@example.com", "1234567890");

        assertEquals("alice123", student.getNetId());
        assertEquals("password123", student.getPassword());
        assertEquals("Alice", student.getLegalName());
        assertEquals("Alice Smith", student.getPreferredName());
        assertEquals("alice@example.com", student.getEmail());
        assertEquals("1234567890", student.getPhone());
        assertEquals(new HashMap<String, EnrollmentStatus>(), student.getEnrolledCourseSections());
        assertEquals(new HashMap<String, NotificationPreference>(), student.getNotificationPreferences());
    }
}
