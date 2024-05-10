package edu.duke.ece651.project.team5.shared.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.duke.ece651.project.team5.shared.enums.AttendanceStatus;
import edu.duke.ece651.project.team5.shared.model.AttendanceRecord;

public class AttendanceRecordTest {

    @Test
    public void testAttendanceRecord() {
        String lectureId = "L123";
        String studentNetId = "S456";
        AttendanceStatus status = AttendanceStatus.PRESENT;

        AttendanceRecord attendanceRecord = new AttendanceRecord(lectureId, studentNetId, status);

        assertEquals(lectureId, attendanceRecord.getLectureId());
        assertEquals(studentNetId, attendanceRecord.getStudentNetId());
        assertEquals(status, attendanceRecord.getAttendanceStatus());

        // Test setStatus method
        AttendanceStatus updatedStatus = AttendanceStatus.ABSENT;
        attendanceRecord.setStatus(updatedStatus);
        assertEquals(updatedStatus, attendanceRecord.getAttendanceStatus());

        // Test toString method
        String expectedToString = studentNetId + " is " + updatedStatus.name() + " for lecture " + lectureId;
        assertEquals(expectedToString, attendanceRecord.toString());
    }
}
