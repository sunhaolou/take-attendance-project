package edu.duke.ece651.project.team5.shared.model;

import java.util.*;

import edu.duke.ece651.project.team5.shared.enums.*;
import javax.persistence.*;

@Entity
public class Section {
    @Id
    private String courseSectionId;
    private String courseId;
    private String sectionId;

    @ManyToMany(mappedBy = "courseSections")
    List<Professor> professors;

    @OneToMany(mappedBy = "courseSectionId", targetEntity = Lecture.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Session> sessions;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "enrollment_statuses", joinColumns = @JoinColumn(name = "courseSectionId"))
    @MapKeyColumn(name = "netId")
    @Column(name = "enrollment_status")
    @Enumerated(EnumType.STRING)

    Map<String, EnrollmentStatus> enrollment;

    public Section() {
        this.courseSectionId = "";
        this.courseId = "";
        this.sectionId = "";
        this.professors = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.enrollment = new HashMap<>();
    }

    public Section(String courseSectionId) {
        this(courseSectionId, "", "", new ArrayList<>(), new HashMap<>());

    }

    public Section(String courseSectionId, String courseId, String sectionId) {
        this(courseSectionId, courseId, sectionId, new ArrayList<>(), new HashMap<>());
    }

    public Section(String courseSectionId, String courseId, String sectionId, List<Session> sessions) {
        this.courseSectionId = courseSectionId;
        this.courseId = courseId;
        this.sectionId = sectionId;
        this.professors = new ArrayList<>();
        this.sessions = sessions;
        this.enrollment = new HashMap<>();
    }

    public Section(String courseSectionId, String courseId, String sectionId, List<Session> sessions,
            Map<String, EnrollmentStatus> enrollment) {
        this.courseSectionId = courseSectionId;
        this.courseId = courseId;
        this.sectionId = sectionId;
        this.professors = new ArrayList<>();
        this.sessions = sessions;
        this.enrollment = enrollment;
    }

    public Section(String courseSectionId, String courseId, String sectionId, List<Professor> professors,
            List<Session> sessions, Map<String, EnrollmentStatus> enrollment) {
        this.courseSectionId = courseSectionId;
        this.courseId = courseId;
        this.sectionId = sectionId;
        this.professors = professors;
        this.sessions = sessions;
        this.enrollment = enrollment;
    }

    public String getCourseSectionId() {
        return courseSectionId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public Map<String, EnrollmentStatus> getEnrollment() {
        return enrollment;
    }

    public void setCourseSectionId(String courseSectionId) {
        this.courseSectionId = courseSectionId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public void addProfessor(Professor professor) {
        for (Professor p : professors) {
            if (p.getNetId().equals(professor.getNetId())) {
                return;
            }
        }
        professors.add(professor);
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void setEnrollment(Map<String, EnrollmentStatus> enrollment) {
        this.enrollment = enrollment;
    }

    // public Map<String, EnrollmentStatus> getStudentsMap() {
    // return studentsMap;
    // }

    // private Map<String, EnrollmentStatus> setDefaultEnrollment(Set<String>
    // studentsIDs) {
    // Map<String, EnrollmentStatus> newStudentsMap = new HashMap<>();
    // for (String studentID : studentsIDs) {
    // // Set default status to ENROLLED
    // newStudentsMap.put(studentID, EnrollmentStatus.ENROLLED);
    // }
    // return newStudentsMap;
    // }

    // public void updateOneStudentAttendence(Session session, Student student,
    // AttendanceStatus status) {
    // if (!sessions.contains(session)) {
    // throw new IllegalArgumentException("Updating Attendance Status in session
    // does not exist");
    // }
    // if (studentsMap.containsKey(student.getNetId())) {
    // if (studentsMap.get(student.getNetId()) == EnrollmentStatus.DROPPED) {
    // throw new IllegalArgumentException("Updating Attendance Status of dropped
    // student");
    // }
    // // Update for student
    // student.markAttendance(session, status);
    // // Update for session:
    // session.takeAttendance(student, status);
    // } else {
    // throw new IllegalArgumentException("Student not found in the Course");
    // }
    // }

    // public void readData(String data) {
    // }

    // public void writeData(String data) {
    // }

    // // public Course(String courseId, Set<Student> students) {
    // // this.courseId = courseId;
    // // this.studentsMap = setDefaultEnrollment(courseId);
    // // this.sessions = new ArrayList<Session>();
    // // }

    // public Student findStudentByName(String name, Iterable<Student> studentsSet)
    // {
    // for (Student student : studentsSet) {
    // if (student.getPreferredName() == name) {
    // if (!studentsMap.containsKey(student.getNetId())) {
    // throw new IllegalArgumentException("Student not found in the Course: " +
    // this.courseId);
    // }
    // return student;
    // }
    // }
    // throw new IllegalArgumentException("Cannot find student's name");
    // }

    public void addSession(Session addingSession) {
        sessions.add(addingSession);
    }

    public void enrollStudent(Student student) {
        enrollment.put(student.getNetId(), EnrollmentStatus.ENROLLED);
    }

    public void enrollStudent(String netId) {
        enrollment.put(netId, EnrollmentStatus.ENROLLED);
    }

    public void dropStudent(Student student) {
        enrollment.put(student.getNetId(), EnrollmentStatus.DROPPED);
    }

    public void dropStudent(String netId) {
        enrollment.put(netId, EnrollmentStatus.DROPPED);
    }

    // public Iterable<String> queryEnrolledStudentsNetIds() {
    // List<String> enrolledStudents = new ArrayList<>();
    // for (Map.Entry<String, EnrollmentStatus> entry : studentsMap.entrySet()) {
    // if (entry.getValue() == EnrollmentStatus.ENROLLED) {
    // String netId = entry.getKey();
    // enrolledStudents.add(netId);
    // }
    // }
    // return enrolledStudents;
    // }

    // public Iterable<String> queryAllStudentsNetIds() {

    // return studentsMap.keySet();
    // }

    // // public void updateOneStudentEnrollment(String netId, EnrollmentStatus
    // // newStatus) {
    // // if (studentsMap.containsKey(netId)) {
    // // // Update for student class
    // // student.updateEnrollmentStatus(this, newStatus);
    // // // Update for Course class
    // // studentsMap.put(student, newStatus);
    // // } else {
    // // throw new IllegalArgumentException("Student not found in the Course");
    // // }
    // // }

}
