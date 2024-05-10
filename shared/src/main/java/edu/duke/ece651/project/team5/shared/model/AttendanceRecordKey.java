package edu.duke.ece651.project.team5.shared.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class AttendanceRecordKey implements Serializable {

  private String lectureId;
  private String studentNetId;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    AttendanceRecordKey that = (AttendanceRecordKey) o;
    return Objects.equals(lectureId, that.lectureId) &&
        Objects.equals(studentNetId, that.studentNetId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lectureId, studentNetId);
  }
}