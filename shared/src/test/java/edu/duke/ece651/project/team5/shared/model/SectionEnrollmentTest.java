package edu.duke.ece651.project.team5.shared.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SectionEnrollmentTest {
  @Test
  public void testGettersAndSetters() {
      // Create a SectionEnrollment object
      String courseSectionId = "CS101-001";
      String studentNetId = "student123";
      int enrollmentStatusId = 1;
      SectionEnrollment enrollment = new SectionEnrollment(courseSectionId, studentNetId, enrollmentStatusId);

      // Test getters
      assertEquals(courseSectionId, enrollment.getCourseSectionId());
      assertEquals(studentNetId, enrollment.getStudentNetId());
      assertEquals(enrollmentStatusId, enrollment.getEnrollmentStatusId());

      // Test setters
      int newEnrollmentStatusId = 2;
      enrollment.setEnrollmentStatusId(newEnrollmentStatusId);
      assertEquals(newEnrollmentStatusId, enrollment.getEnrollmentStatusId());
  }

  @Test
  public void testToString() {
      // Create a SectionEnrollment object
      String courseSectionId = "CS101-001";
      String studentNetId = "student123";
      int enrollmentStatusId = 1;
      SectionEnrollment enrollment = new SectionEnrollment(courseSectionId, studentNetId, enrollmentStatusId);

      // Test toString method
      String expectedToString = studentNetId + " is " + enrollmentStatusId + " in courseSectionId " + courseSectionId;
      assertEquals(expectedToString, enrollment.toString());
  }

}
