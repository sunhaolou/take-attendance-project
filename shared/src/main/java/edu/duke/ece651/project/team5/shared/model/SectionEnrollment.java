package edu.duke.ece651.project.team5.shared.model;

public class SectionEnrollment {
    private String courseSectionId;
    private String studentNetId;
    private int enrollmentStatusId;

    public SectionEnrollment(String courseSectionId, String studentNetId, int enrollmentStatusId) {
        this.courseSectionId = courseSectionId;
        this.studentNetId = studentNetId;
        this.enrollmentStatusId = enrollmentStatusId;
    }

    public String getCourseSectionId() {
        return courseSectionId;
    }

    public String getStudentNetId() {
        return studentNetId;
    }

    public int getEnrollmentStatusId() {
        return enrollmentStatusId;
    }

    public void setEnrollmentStatusId(int enrollmentStatusId) {
        this.enrollmentStatusId = enrollmentStatusId;
    }

    @Override
    public String toString() {
        return studentNetId + " is " + enrollmentStatusId + " in courseSectionId " + courseSectionId;
    }
}
