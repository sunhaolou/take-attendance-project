package edu.duke.ece651.project.team5.frontend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.project.team5.frontend.model.NotificationPreference;
import edu.duke.ece651.project.team5.frontend.model.NotificationMethod;

public class NotificationPreferenceTest {

  @Test
  public void testDefaultConstructor() {
    NotificationPreference preference = new NotificationPreference();

    assertEquals(4, preference.getNotificationPreferenceId());
    assertEquals("", preference.getCourseSectionId());
    assertEquals("", preference.getStudentNetId());
    assertEquals(NotificationMethod.EMAIL, preference.getNotificationMethod());
  }

  @Test
  public void testParameterizedConstructor() {
    NotificationPreference preference = new NotificationPreference("CSCI101", "alice123", NotificationMethod.EMAIL);

    assertEquals(1, preference.getNotificationPreferenceId()); // Since it's a new instance, id should start from 1
    assertEquals("CSCI101", preference.getCourseSectionId());
    assertEquals("alice123", preference.getStudentNetId());
    assertEquals(NotificationMethod.EMAIL, preference.getNotificationMethod());
  }

  @Test
  public void testCustomIdConstructor() {
    NotificationPreference preference = new NotificationPreference(100, "CSCI101", "bob456", NotificationMethod.EMAIL);

    assertEquals(100, preference.getNotificationPreferenceId());
    assertEquals("CSCI101", preference.getCourseSectionId());
    assertEquals("bob456", preference.getStudentNetId());
    assertEquals(NotificationMethod.EMAIL, preference.getNotificationMethod());
  }

  @Test
  public void testSetNotificationMethod() {
    NotificationPreference preference = new NotificationPreference();

    preference.setNotificationMethod(NotificationMethod.NOREMINDER);

    assertEquals(NotificationMethod.NOREMINDER, preference.getNotificationMethod());
  }

  @Test
  public void testToString() {
    NotificationPreference preference = new NotificationPreference("CSCI101", "alice123", NotificationMethod.EMAIL);

    String expected = "alice123 prefers EMAIL notification for courseSectionId CSCI101";
    assertEquals(expected, preference.toString());
  }
}
