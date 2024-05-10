package edu.duke.ece651.project.team5.shared.model;

import java.sql.Timestamp;
import java.util.Map;

import edu.duke.ece651.project.team5.shared.enums.AttendanceStatus;

public interface Session {

    public String getSessionId();

    public String getCourseSectionId();

    public Timestamp getTime();

    public void setSessionId(String sessionId);

    public void setCourseSectionId(String courseSectionId);

    public void setTime(Timestamp time);

    public void setAttendance(Map<String, AttendanceRecord> attendance);

    public Map<String, AttendanceRecord> getAttendance();

    public void takeAttendance(Student student, AttendanceStatus status);

    public AttendanceStatus getAttendanceOfStudent(Student student);
}
