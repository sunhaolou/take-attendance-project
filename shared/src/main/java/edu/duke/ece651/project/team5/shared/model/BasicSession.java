package edu.duke.ece651.project.team5.shared.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.checkerframework.checker.units.qual.C;

import edu.duke.ece651.project.team5.shared.enums.AttendanceStatus;

@MappedSuperclass
public abstract class BasicSession implements Session {
    @Id
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "course_section_id")
    private String courseSectionId;
    private Timestamp time;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "attendance_record", joinColumns = {
            @JoinColumn(name = "session_id", referencedColumnName = "session_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "lectureId", referencedColumnName = "lectureId"),
                    @JoinColumn(name = "studentNetId", referencedColumnName = "studentNetId")
            })
    @MapKey(name = "studentNetId")
    private Map<String, AttendanceRecord> attendance;

    public BasicSession(String sessionId, String courseSectionId, Timestamp time) {
        this.courseSectionId = courseSectionId;
        this.time = time;
        this.sessionId = sessionId;
        attendance = new HashMap<>();
    }

    public BasicSession(String courseSectionId, Timestamp time) {
        this(courseSectionId + new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss").format(time), courseSectionId, time);
    }

    public BasicSession(String sessionId, String courseSectionId, Timestamp time,
            Map<String, AttendanceRecord> attendance) {
        this.courseSectionId = courseSectionId;
        this.time = time;
        this.sessionId = sessionId;
        this.attendance = attendance;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getCourseSectionId() {
        return courseSectionId;
    }

    public Timestamp getTime() {
        return time;
    }

    public Map<String, AttendanceRecord> getAttendance() {
        return attendance;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setCourseSectionId(String courseSectionId) {
        this.courseSectionId = courseSectionId;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setAttendance(Map<String, AttendanceRecord> attendance) {
        this.attendance = attendance;
    }

    public void takeAttendance(Student student, AttendanceStatus status) {
        if (getAttendance().containsKey(student.getNetId())) {
            getAttendance().get(student.getNetId()).setStatus(status);
        } else {
            getAttendance().put(student.getNetId(), new AttendanceRecord(sessionId, student.getNetId(), status));
        }

    }

    public AttendanceStatus getAttendanceOfStudent(Student student) {
        return getAttendance().get(student.getNetId()).getAttendanceStatus();
    }
}
