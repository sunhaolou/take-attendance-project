package edu.duke.ece651.project.team5.shared.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StudentReportTest {
  @Test
  public void testGettersAndSetters() {
      // Create SessionReport objects
      SessionReport sessionReport1 = new SessionReport("ECE651_1_001", "PRESENT", "hg161", "2021-04-01 12:00:00");
      SessionReport sessionReport2 = new SessionReport("ECE651_1_002", "ABSENT", "hg161", "2021-04-02 12:00:00");

      // Create a list of SessionReport objects
      List<SessionReport> sessionReports = new ArrayList<>();
      sessionReports.add(sessionReport1);
      sessionReports.add(sessionReport2);

      // Create a StudentReport object
      String score = "90";
      StudentReport studentReport = new StudentReport(sessionReports, score);

      // Test getters
      assertEquals(sessionReports, studentReport.getReports());
      assertEquals(score, studentReport.getScore());

      // Create a new list of SessionReport objects
      List<SessionReport> newSessionReports = new ArrayList<>();
      SessionReport newSessionReport = new SessionReport("ECE651_1_003", "PRESENT", "hg161", "2021-04-03 12:00:00");
      newSessionReports.add(newSessionReport);

      // Test setters
      studentReport.setReports(newSessionReports);
      studentReport.setScore("95");
      assertEquals(newSessionReports, studentReport.getReports());
      assertEquals("95", studentReport.getScore());
  }

  @Test
  public void testToString() {
      // Create SessionReport objects
      SessionReport sessionReport1 = new SessionReport("ECE651_1_001", "PRESENT", "hg161", "2021-04-01 12:00:00");
      SessionReport sessionReport2 = new SessionReport("ECE651_1_002", "ABSENT", "hg161", "2021-04-02 12:00:00");

      // Create a list of SessionReport objects
      List<SessionReport> sessionReports = new ArrayList<>();
      sessionReports.add(sessionReport1);
      sessionReports.add(sessionReport2);

      // Create a StudentReport object
      String score = "90";
      StudentReport studentReport = new StudentReport(sessionReports, score);

      // Test toString method
      String expectedToString = sessionReport1.toString() + sessionReport2.toString() + "Score: " + score + "\n";
      assertEquals(expectedToString, studentReport.toString());
  }

  @Test
  void test_constructors(){
    StudentReport report = new StudentReport();
    report.setScore("100");
    assertEquals("100", report.getScore());
  }

}
