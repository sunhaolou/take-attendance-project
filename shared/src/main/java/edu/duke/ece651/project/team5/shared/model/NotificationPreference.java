package edu.duke.ece651.project.team5.shared.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Column;
import edu.duke.ece651.project.team5.shared.enums.NotificationMethod;

@Entity
public class NotificationPreference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_preference_id")
    private Integer notificationPreferenceId;
    @Column(name = "course_section_id")
    private String courseSectionId;
    @Column(name = "student_net_id")
    private String studentNetId;
    @Enumerated(EnumType.STRING)
    private NotificationMethod method;

    public NotificationPreference() {

        this.courseSectionId = "";
        this.studentNetId = "";
        this.method = NotificationMethod.EMAIL;
    }

    public NotificationPreference(String courseSectionId, String studentNetId, NotificationMethod method) {

        this.courseSectionId = courseSectionId;
        this.studentNetId = studentNetId;
        this.method = method;
    }

    public NotificationPreference(Integer notificationPreferenceId, String courseSectionId, String studentNetId,
            NotificationMethod method) {
        this.notificationPreferenceId = notificationPreferenceId;
        this.courseSectionId = courseSectionId;
        this.studentNetId = studentNetId;
        this.method = method;
    }

    public Integer getNotificationPreferenceId() {
        return notificationPreferenceId;
    }

    public String getCourseSectionId() {
        return courseSectionId;
    }

    public String getStudentNetId() {
        return studentNetId;
    }

    public NotificationMethod getNotificationMethod() {
        return method;
    }

    public void setNotificationMethod(NotificationMethod update_method) {
        this.method = update_method;
    }

    @Override
    public String toString() {
        return studentNetId + " prefers " + method.name() + " notification for courseSectionId " + courseSectionId;
    }
}