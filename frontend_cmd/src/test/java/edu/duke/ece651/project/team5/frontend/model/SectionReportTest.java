package edu.duke.ece651.project.team5.frontend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.project.team5.frontend.model.SectionReport;

public class SectionReportTest {

  @Test
  public void testGetAttendance() {
    // Create a SectionReport instance
    SectionReport sectionReport = new SectionReport();

    // Ensure that the attendance map is initially empty
    assertEquals(new HashMap<String, String>(), sectionReport.getAttendance());
  }

  @Test
  public void testAddRecord() {
    // Create a SectionReport instance
    SectionReport sectionReport = new SectionReport();

    // Add a record to the attendance map
    sectionReport.addRecord("alice123", "present");

    // Ensure that the record was added correctly
    Map<String, String> expectedAttendance = new HashMap<>();
    expectedAttendance.put("alice123", "present");
    assertEquals(expectedAttendance, sectionReport.getAttendance());
  }

  @Test
  public void testSetAttendance() {
    // Create a SectionReport instance
    SectionReport sectionReport = new SectionReport();

    // Create a map with some records
    Map<String, String> attendanceMap = new HashMap<>();
    attendanceMap.put("alice123", "present");
    attendanceMap.put("bob456", "absent");

    // Set the attendance map
    sectionReport.setAttendance(attendanceMap);

    // Ensure that the attendance map was set correctly
    assertEquals(attendanceMap, sectionReport.getAttendance());
  }
}
