package edu.duke.ece651.project.team5.shared.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SessionReportTest {
  @Test
  public void testGettersAndSetters() {
      // Create a SessionReport object
      SessionReport sessionReport = new SessionReport();

      // Test getters and setters
      sessionReport.setCourseSectionId("CS101-001");
      sessionReport.setStatus("Completed");
      sessionReport.setNetId("student123");
      sessionReport.setTime("2024-04-10 10:00:00");

      assertEquals("CS101-001", sessionReport.getCourseSectionId());
      assertEquals("Completed", sessionReport.getStatus());
      assertEquals("student123", sessionReport.getNetId());
      assertEquals("2024-04-10 10:00:00", sessionReport.getTime());
  }

  @Test
  public void testToString() {
      // Create a SessionReport object
      SessionReport sessionReport = new SessionReport("CS101-001", "Completed", "student123", "2024-04-10 10:00:00");

      // Test toString method
      String expectedToString = "SessionReport [courseSectionId=CS101-001, status=Completed, netId=student123, time=2024-04-10 10:00:00]";
      assertEquals(expectedToString, sessionReport.toString());
  }

}
