package edu.duke.ece651.project.team5.shared.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.google.common.base.Objects;

import edu.duke.ece651.project.team5.shared.enums.AttendanceStatus;
import javax.persistence.ManyToOne;

@Entity
@IdClass(AttendanceRecordKey.class)
@Table(name = "attendance_records")
public class AttendanceRecord {

    @Id
    
    private String lectureId;
    @Id
    private String studentNetId;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    public AttendanceRecord() {
    }

    public AttendanceRecord(String lectureId, String studentNetId, AttendanceStatus status) {
        this.lectureId = lectureId;
        this.studentNetId = studentNetId;
        this.status = status;
        // this(getNextId(), lectureId, studentNetId, status);
    }

    public String getLectureId() {
        return lectureId;
    }

    public String getStudentNetId() {
        return studentNetId;
    }

    public AttendanceStatus getAttendanceStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus update_status) {
        this.status = update_status;
    }

    @Override
    public String toString() {
        return studentNetId + " is " + status.name() + " for lecture " + lectureId;
    }

}