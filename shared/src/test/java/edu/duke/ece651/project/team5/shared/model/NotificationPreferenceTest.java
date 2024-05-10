package edu.duke.ece651.project.team5.shared.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.project.team5.shared.enums.NotificationMethod;

public class NotificationPreferenceTest {
  @Test
  public void testGettersAndSetters() {
      // Create a NotificationPreference object
      NotificationPreference preference = new NotificationPreference("CS101-001", "student123", NotificationMethod.EMAIL);

      // Test getters and setters
      assertEquals("CS101-001", preference.getCourseSectionId());
      assertEquals("student123", preference.getStudentNetId());
      assertEquals(NotificationMethod.EMAIL, preference.getNotificationMethod());

      preference.setNotificationMethod(NotificationMethod.NOREMINDER);

      // Test if notification method is set correctly
      assertEquals(NotificationMethod.NOREMINDER, preference.getNotificationMethod());
  }

  @Test
  public void testToString() {
      // Create a NotificationPreference object
      NotificationPreference preference = new NotificationPreference("CS101-001", "student123", NotificationMethod.EMAIL);

      // Test toString method
      assertEquals("student123 prefers EMAIL notification for courseSectionId CS101-001", preference.toString());
  }

  @Test
  public void test_constructors(){
    NotificationPreference preference = new NotificationPreference();
    assertEquals("", preference.getCourseSectionId());
    assertEquals("", preference.getStudentNetId());
    assertEquals(NotificationMethod.EMAIL, preference.getNotificationMethod());

    NotificationPreference preference2 = new NotificationPreference(10,"CS101-001", "student123", NotificationMethod.EMAIL);
    assertEquals(10, preference2.getNotificationPreferenceId());
    assertEquals("CS101-001", preference2.getCourseSectionId());
    assertEquals("student123", preference2.getStudentNetId());
    assertEquals(NotificationMethod.EMAIL, preference2.getNotificationMethod());
    
}

}
