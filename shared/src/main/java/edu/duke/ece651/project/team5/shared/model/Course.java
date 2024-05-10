
package edu.duke.ece651.project.team5.shared.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {
  @Id
  private String courseId;
  private String courseName;

  @OneToMany(mappedBy = "courseId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Section> sections;

  public Course() {
    this.courseId = "";
    this.courseName = "";
    this.sections = new ArrayList<>();
  }

  public Course(String courseId, String courseName) {
    this.courseId = courseId;
    this.courseName = courseName;
    this.sections = new ArrayList<>();
  }

  public Course(String courseId, String courseName, List<Section> sections) {
    this.courseId = courseId;
    this.courseName = courseName;
    this.sections = sections;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public List<Section> getSections() {
    return sections;
  }

  public void setSections(List<Section> sections) {
    this.sections = sections;
  }

  public void addSection(Section section) {
    sections.add(section);
  }

  /*
   * public Map<String, EnrollmentStatus> getStudentsMap() {
   * return studentsMap;
   * }
   * 
   * private Map<String, EnrollmentStatus> setDefaultEnrollment(Set<String>
   * studentsIDs) {
   * Map<String, EnrollmentStatus> newStudentsMap = new HashMap<>();
   * for (String studentID : studentsIDs) {
   * // Set default status to ENROLLED
   * newStudentsMap.put(studentID, EnrollmentStatus.ENROLLED);
   * }
   * return newStudentsMap;
   * }
   * 
   * public void updateOneStudentAttendence(Session session, Student student,
   * AttendanceStatus status) {
   * if (!sessions.contains(session)) {
   * throw new
   * IllegalArgumentException("Updating Attendance Status in session does not exist"
   * );
   * }
   * if (studentsMap.containsKey(student.getNetId())) {
   * if (studentsMap.get(student.getNetId()) == EnrollmentStatus.DROPPED) {
   * throw new
   * IllegalArgumentException("Updating Attendance Status of dropped student");
   * }
   * // Update for student
   * // student.markAttendance(session, status);
   * // Update for session:
   * session.takeAttendance(student, status);
   * } else {
   * throw new IllegalArgumentException("Student not found in the Course");
   * }
   * }
   * 
   * public void readData(String data) {
   * }
   * 
   * public void writeData(String data) {
   * }
   * 
   * // public Course(String courseId, Set<Student> students) {
   * // this.courseId = courseId;
   * // this.studentsMap = setDefaultEnrollment(courseId);
   * // this.sessions = new ArrayList<Session>();
   * // }
   * 
   * public Student findStudentByName(String name, Iterable<Student> studentsSet)
   * {
   * for (Student student : studentsSet) {
   * if (student.getPreferredName() == name) {
   * if (!studentsMap.containsKey(student.getNetId())) {
   * throw new IllegalArgumentException("Student not found in the Course: " +
   * this.courseId);
   * }
   * return student;
   * }
   * }
   * throw new IllegalArgumentException("Cannot find student's name");
   * }
   * 
   * public void addSession(Session addingSession) {
   * sessions.add(addingSession);
   * }
   * 
   * public void enrollStudent(Student student) {
   * studentsMap.put(student.getNetId(), EnrollmentStatus.ENROLLED);
   * }
   * 
   * public void dropStudent(Student student) {
   * studentsMap.put(student.getNetId(), EnrollmentStatus.DROPPED);
   * }
   * 
   * public Iterable<String> queryEnrolledStudentsNetIds() {
   * List<String> enrolledStudents = new ArrayList<>();
   * for (Map.Entry<String, EnrollmentStatus> entry : studentsMap.entrySet()) {
   * if (entry.getValue() == EnrollmentStatus.ENROLLED) {
   * String netId = entry.getKey();
   * enrolledStudents.add(netId);
   * }
   * }
   * return enrolledStudents;
   * }
   * 
   * public Iterable<String> queryAllStudentsNetIds() {
   * 
   * return studentsMap.keySet();
   * }
   * 
   * 
   * // public void updateOneStudentEnrollment(String netId, EnrollmentStatus
   * // newStatus) {
   * // if (studentsMap.containsKey(netId)) {
   * // // Update for student class
   * // student.updateEnrollmentStatus(this, newStatus);
   * // // Update for Course class
   * // studentsMap.put(student, newStatus);
   * // } else {
   * // throw new IllegalArgumentException("Student not found in the Course");
   * // }
   * // }
   */

}
