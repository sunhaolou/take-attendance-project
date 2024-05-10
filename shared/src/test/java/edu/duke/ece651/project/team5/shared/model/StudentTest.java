package edu.duke.ece651.project.team5.shared.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;
import edu.duke.ece651.project.team5.shared.enums.NotificationMethod;
import java.util.*;

public class StudentTest {
  @Test
  public void testGettersAndSetters() {
      // Create a Student object
      Student student = new Student("student123", "password", "John Doe", "john", "john@example.com", "123456789");

      // Test getters and setters
      assertEquals("student123", student.getNetId());
      assertEquals("password", student.getPassword());
      assertEquals("John Doe", student.getLegalName());
      assertEquals("john", student.getPreferredName());
      assertEquals("john@example.com", student.getEmail());
      assertEquals("123456789", student.getPhone());

      student.setNetId("new_student123");
      student.setPassword("new_password");
      student.setLegalName("Jane Doe");
      student.setPreferredName("jane");
      student.setEmail("jane@example.com");
      student.setPhone("987654321");

      assertEquals("new_student123", student.getNetId());
      assertEquals("new_password", student.getPassword());
      assertEquals("Jane Doe", student.getLegalName());
      assertEquals("jane", student.getPreferredName());
      assertEquals("jane@example.com", student.getEmail());
      assertEquals("987654321", student.getPhone());
  }

  @Test
  public void testEnrollCourseSection() {
      // Create a Section
      Section section = new Section("CS101-001", "Computer Science", "Spring 2024");

      // Create a Student object
      Student student = new Student("student123", "password", "John Doe", "john", "john@example.com", "123456789");

      // Enroll the student in the section
      student.enrollCourseSection(section);

      // Test if the student is enrolled in the section
      assertTrue(student.getEnrolledCourseSections().containsKey("CS101-001"));
      assertEquals(EnrollmentStatus.ENROLLED, student.getEnrolledCourseSections().get("CS101-001"));
    
      assertNotNull(section.getCourseSectionId());
      assertTrue(student.subscribeSection(section.getCourseSectionId()));
  }

  @Test
  public void testDropCourse() {
      // Create a Section
      Section section = new Section("CS101-001", "Computer Science", "Spring 2024");

      // Create a Student object
      Student student = new Student("student123", "password", "John Doe", "john", "john@example.com", "123456789");

      // Enroll the student in the section
      student.enrollCourseSection(section);

      // Drop the student from the section
      student.dropCourse(section);

      // Test if the student is dropped from the section
      assertTrue(student.getEnrolledCourseSections().containsKey("CS101-001"));
      assertEquals(EnrollmentStatus.DROPPED, student.getEnrolledCourseSections().get("CS101-001"));
  }

  @Test
  public void testCreateAndUpdateNotificationPreference() {
      // Create a Student object
      Student student = new Student("student123", "password", "John Doe", "john", "john@example.com", "123456789");

      // Create a NotificationMethod
      NotificationMethod method = NotificationMethod.EMAIL;

      // Create a NotificationPreference
      student.createNotificationPreference("CS101-001", method);

      // Test if the notification preference is created
      assertTrue(student.getNotificationPreferences().containsKey("CS101-001"));
      assertEquals(method, student.getNotificationPreferences().get("CS101-001").getNotificationMethod());

      // Update the notification preference
      NotificationMethod newMethod = NotificationMethod.NOREMINDER;
      student.updateNotificationPreference("CS101-001", newMethod);

      // Test if the notification preference is updated
      assertTrue(student.getNotificationPreferences().containsKey("CS101-001"));
      assertEquals(newMethod, student.getNotificationPreferences().get("CS101-001").getNotificationMethod());
  }

  @Test
  public void testSameStudentById() {
      // Create a Student object
      Student student = new Student("student123", "password", "John Doe", "john", "john@example.com", "123456789");

      // Test if the sameStudentById method returns true for the same id
      assertTrue(student.sameStudentById("student123"));

      // Test if the sameStudentById method returns false for a different id
      assertFalse(student.sameStudentById("student456"));
  }

  @Test
  public void testMatchSearchedName() {
      // Create a Student object
      Student student = new Student("student123", "password", "John Doe", "john", "john@example.com", "123456789");

      // Test if the matchSearchedName method returns true for the same name
      assertTrue(student.matchSearchedName("john"));

      // Test if the matchSearchedName method returns false for a different name
      assertFalse(student.matchSearchedName("jane"));
  }

  @Test
  public void testCompareTo() {
      // Create Student objects
      Student student1 = new Student("student123", "password", "John Doe", "john", "john@example.com", "123456789");
      Student student2 = new Student("student456", "password", "Jane Doe", "jane", "jane@example.com", "987654321");

      // Test if compareTo method returns correct values
      assertTrue(student1.compareTo(student2) > 0); // student1's legal name comes before student2's legal name
      assertTrue(student2.compareTo(student1) < 0); // student2's legal name comes after student1's legal name
  }

  @Test
  public void testSetNotificationPreference(){
    Section section = new Section("CS101-001", "Computer Science", "Spring 2024");

    // Create a Student object
    Student student = new Student("student123", "password", "John Doe", "john", "john@example.com", "123456789");

    // Enroll the student in the section
    student.enrollCourseSection(section);
    Map<String, NotificationPreference> test_pref = new HashMap<>();
    NotificationPreference pref = new NotificationPreference(section.getCourseSectionId(), student.getNetId(), NotificationMethod.EMAIL);
    test_pref.put(section.getCourseSectionId(), pref);
    student.setNotificationPreferences(test_pref);
  }
  
  @Test
  public void test_constructor(){
    Student student = new Student();
    assertNotNull(student);
  }

}
