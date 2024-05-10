package edu.duke.ece651.project.team5.frontend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.project.team5.frontend.model.SessionReport;

public class SessionReportTest {

    @Test
    public void testParameterizedConstructor() {
        SessionReport sessionReport = new SessionReport("CSCI101", "present", "alice123", "2024-04-20 10:00:00");

        assertEquals("CSCI101", sessionReport.getCourseSectionId());
        assertEquals("present", sessionReport.getStatus());
        assertEquals("alice123", sessionReport.getNetId());
        assertEquals("2024-04-20 10:00:00", sessionReport.getTime());
    }

    @Test
    public void testDefaultConstructor() {
        SessionReport sessionReport = new SessionReport();

        assertEquals("", sessionReport.getCourseSectionId());
        assertEquals("", sessionReport.getStatus());
        assertEquals("", sessionReport.getNetId());
        assertEquals("", sessionReport.getTime());
    }
}
