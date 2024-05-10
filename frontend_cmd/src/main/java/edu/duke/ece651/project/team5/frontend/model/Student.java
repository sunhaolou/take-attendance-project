package edu.duke.ece651.project.team5.frontend.model;

import java.util.HashMap;
import java.util.Map;

import edu.duke.ece651.project.team5.shared.enums.*;
import edu.duke.ece651.project.team5.shared.utils.PasswordHasher;

public class Student extends Person implements Comparable<Student> {

    private String preferredName;

    // private AttendanceRecordDao AttendanceRecordDao;

    // courseSectionId, EnrollmentStatus
    private Map<String, EnrollmentStatus> enrolledCourseSections;
    // courseSectionId, NotificationPreference
    private Map<String, NotificationPreference> notificationPreferences;

    public Map<String, NotificationPreference> getNotificationPreferences() {
        return notificationPreferences;
    }

    public Student(String netId) {
        this(netId, netId);
    }

    public Student(String netId, String legalName) {
        this(netId, "", legalName, "", "", "");
    }

    public Student() {
        this("");
    }

    public Student(String netId, String password, String legalName, String preferredName, String email, String phone) {
        this(netId, password, legalName, preferredName, email, phone, new HashMap<>());
    }

    public Student(String netId, String password, String legalName, String preferredName, String email, String phone,
            Map<String, NotificationPreference> notificationPreferences) {
        this(netId, password, legalName, preferredName, email, phone, PasswordHasher.generateSalt(), new HashMap<>(),
                notificationPreferences);
    }

    public Student(String netId, String password, String legalName, String preferredName, String email, String phone,
            byte[] salt,
            Map<String, EnrollmentStatus> enrolledCourseSections,
            Map<String, NotificationPreference> notificationPreferences) {
        super(netId, password, legalName, email, phone, salt);
        this.preferredName = preferredName;
        this.enrolledCourseSections = enrolledCourseSections;
        this.notificationPreferences = notificationPreferences;
    }

    public Student(String netId, String legalName, String email, String phone) {
        this(netId, "", legalName, email, phone);
    }

    public Student(String netId, String password, String legalName, String email, String phone) {
        this(netId, password, legalName, legalName, email, phone);
    }

    public Student(String netId, String password, String legalName, String preferredName, String email, String phone,
            Map<String, EnrollmentStatus> enrolledCourseSections,
            Map<String, NotificationPreference> notificationPreferences,
            byte[] salt) {
        this(netId, password, legalName, preferredName, email, phone, salt,enrolledCourseSections, notificationPreferences);
    }

    public String getPassword() {
        return super.getPassword();
    }

    public String getNetId() {
        return super.getNetId();
    }

    public String getLegalName() {
        return super.getLegalName();
    }

    public String getPreferredName() {
        return preferredName;
    }

    public String getEmail() {
        return super.getEmail();
    }

    public String getPhone() {
        return super.getPhone();
    }

    public Map<String, EnrollmentStatus> getEnrolledCourseSections() {
        return enrolledCourseSections;
    }

    @Override
    public int compareTo(Student otherStudent) {
        return this.getLegalName().compareTo(otherStudent.getLegalName());
    }
}
