package edu.duke.ece651.project.team5.shared.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.duke.ece651.project.team5.shared.enums.AttendanceStatus;

public class BasicSessionTest {

    private Lecture session;
    private Student student;
    private AttendanceRecord attendanceRecord;

    @Before
    public void setup() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Map<String, AttendanceRecord> attendance = new HashMap<>();
        attendanceRecord = new AttendanceRecord("sessionId", "studentNetId", AttendanceStatus.PRESENT);
        attendance.put("studentNetId", attendanceRecord);

        session = new Lecture("sessionId", "courseSectionId", timestamp, attendance);
        student = new Student("studentNetId", "password", "legalName", "email", "phone");
    }

    @Test
    public void testGetters() {
        assertEquals("sessionId", session.getSessionId());
        assertEquals("courseSectionId", session.getCourseSectionId());
        assertEquals(attendanceRecord, session.getAttendance().get("studentNetId"));
    }

    @Test
    public void testSetters() {
        session.setSessionId("newSessionId");
        session.setCourseSectionId("newCourseSectionId");

        assertEquals("newSessionId", session.getSessionId());
        assertEquals("newCourseSectionId", session.getCourseSectionId());
    }

    @Test
    public void testTakeAttendance() {
        session.takeAttendance(student, AttendanceStatus.ABSENT);
        assertEquals(AttendanceStatus.ABSENT, session.getAttendanceOfStudent(student));
    }

  

}
