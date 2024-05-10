package edu.duke.ece651.project.team5.shared.model;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;
import edu.duke.ece651.project.team5.shared.enums.NotificationMethod;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends Person implements Comparable<Student> {

    @Column(name = "preferred_name")
    private String preferredName;

    // private AttendanceRecordDao AttendanceRecordDao;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "enrollment_statuses", joinColumns = @javax.persistence.JoinColumn(name = "netId"))
    @MapKeyColumn(name = "courseSectionId")
    @Column(name = "enrollment_status")
    @Enumerated(EnumType.STRING)

    // courseSectionId, EnrollmentStatus
    private Map<String, EnrollmentStatus> enrolledCourseSections;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_notification_preference", joinColumns = {
            @JoinColumn(name = "netid", referencedColumnName = "netid") }, inverseJoinColumns = {
                    @JoinColumn(name = "notification_preference_id", referencedColumnName = "notification_preference_id") })
    @MapKey(name = "courseSectionId")

    private Map<String, NotificationPreference> notificationPreferences;

    // private Map<String, AttendanceStatus> attendanceHistory;

    public Map<String, NotificationPreference> getNotificationPreferences() {
        return notificationPreferences;
    }

    public Boolean subscribeSection(String courseSectionId) {
        return notificationPreferences.get(courseSectionId).getNotificationMethod().getMethod().equals("EMAIL");
    }

    public void setNotificationPreferences(Map<String, NotificationPreference> notificationPreferences) {
        this.notificationPreferences = notificationPreferences;
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
        this(netId, password, legalName, preferredName, email, phone, new HashMap<>(),
                notificationPreferences);
    }

    public Student(String netId, String password, String legalName, String preferredName, String email, String phone,

            Map<String, EnrollmentStatus> enrolledCourseSections,
            Map<String, NotificationPreference> notificationPreferences) {
        super(netId, password, legalName, email, phone);
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

    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public void setEnrolledCourses(Map<String, EnrollmentStatus> enrolledCourseSections) {
        this.enrolledCourseSections = enrolledCourseSections;
    }

    public String getNetId() {
        return super.getNetId();
    }

    public void setNetId(String netId) {
        super.setNetId(netId);
        ;
    }

    public String getLegalName() {
        return super.getLegalName();
    }

    public void setLegalName(String legalName) {
        super.setLegalName(legalName);
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public String getPhone() {
        return super.getPhone();
    }

    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    public Boolean sameStudentById(String id) {
        if (id == super.getNetId())
            return true;
        return false;
    }

    // public Map<String, AttendanceRecord> getAttendanceHistory() throws
    // SQLException {
    // return AttendanceRecordDao.getAttendanceRecordByStudentNetId(netId);
    // }

    public Map<String, EnrollmentStatus> getEnrolledCourseSections() {
        return enrolledCourseSections;
    }

    public Boolean matchSearchedName(String nameToSearch) {
        if (nameToSearch == preferredName) {
            return true;
        }
        return false;
    }

    public void updateEnrollmentStatus(String courseSectionId, EnrollmentStatus enrolled) {
        enrolledCourseSections.put(courseSectionId, enrolled);
    }

    public void enrollCourseSection(Section section) {
        section.enrollStudent(this);
        enrolledCourseSections.put(section.getCourseSectionId(), EnrollmentStatus.ENROLLED);
        NotificationPreference pref = new NotificationPreference(section.getCourseSectionId(), this.getNetId(),
                NotificationMethod.EMAIL);
        notificationPreferences.put(section.getCourseSectionId(), pref);
    }

    public void dropCourse(Section section) {
        section.dropStudent(this);
        enrolledCourseSections.put(section.getCourseSectionId(), EnrollmentStatus.DROPPED);
    }

    public void createNotificationPreference(String courseSectionId, NotificationMethod method) {
        NotificationPreference preference = new NotificationPreference(courseSectionId, getNetId(), method);
        notificationPreferences.put(courseSectionId, preference);
    }

    public void updateNotificationPreference(String courseSectionId, NotificationMethod method) {
        NotificationPreference preference = notificationPreferences.get(courseSectionId);
        preference.setNotificationMethod(method);
        notificationPreferences.put(courseSectionId, preference);
    }

    public List<String> getEnrolledCourseSectionIds() {
        List<String> courseSectionIds = new ArrayList<>();
        for (String courseSectionId : enrolledCourseSections.keySet()) {
            if (enrolledCourseSections.get(courseSectionId) == EnrollmentStatus.ENROLLED) {
                courseSectionIds.add(courseSectionId);
            }
        }
        return courseSectionIds;
    }

    @Override
    public int compareTo(Student otherStudent) {
        return this.getLegalName().compareTo(otherStudent.getLegalName());
    }

}
