package edu.duke.ece651.project.team5.shared.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lecture")

public class Lecture extends BasicSession {

  public Lecture() {
    super("lectureId", "courseSectionId", new Timestamp(System.currentTimeMillis()), new HashMap<>());
  }

  public Lecture(String sessionId) {
    super(sessionId, sessionId, new Timestamp(new Date().getTime()));
  }

  public Lecture(String courseSectionId, Timestamp time) {
    super(courseSectionId, time);
  }

  public Lecture(String lectureId, String courseSectionId, Timestamp time) {
    super(lectureId, courseSectionId, time);
  }

  public Lecture(String lectureId, String courseSectionId, Timestamp time, Map<String, AttendanceRecord> attendance) {
    super(lectureId, courseSectionId, time, attendance);
  }

  public String getLectureId() {
    return getSessionId();
  }

  public void setLectureId(String lectureId) {
    setSessionId(lectureId);
  }

}
