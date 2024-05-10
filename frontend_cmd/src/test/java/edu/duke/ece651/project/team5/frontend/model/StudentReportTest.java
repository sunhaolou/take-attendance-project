package edu.duke.ece651.project.team5.frontend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.project.team5.frontend.model.SessionReport;
import edu.duke.ece651.project.team5.frontend.model.StudentReport;

public class StudentReportTest {

    @Test
    public void testDefaultConstructor() {
        StudentReport studentReport = new StudentReport();

        assertTrue(studentReport.getReports().isEmpty());
        assertEquals(null, studentReport.getScore());
    }

    @Test
    public void testParameterizedConstructor() {
        List<SessionReport> reports = new ArrayList<>();
        reports.add(new SessionReport("CSCI101", "present", "alice123", "2024-04-20 10:00:00"));
        reports.add(new SessionReport("CSCI102", "absent", "bob456", "2024-04-21 09:00:00"));

        StudentReport studentReport = new StudentReport(reports, "A");

        assertEquals(reports, studentReport.getReports());
        assertEquals("A", studentReport.getScore());
    }
}
