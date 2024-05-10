package edu.duke.ece651.project.team5.frontend.model;

public class NotificationPreference {
    private static Integer nextId = 1;
    private Integer notificationPreferenceId;
    private String courseSectionId;
    private String studentNetId;
    private NotificationMethod method;

    public NotificationPreference() {
        this.notificationPreferenceId = getNextId();
        this.courseSectionId = "";
        this.studentNetId = "";
        this.method = NotificationMethod.EMAIL;

    }

    public NotificationPreference(String courseSectionId, String studentNetId, NotificationMethod method) {
        this.notificationPreferenceId = getNextId();
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

    private synchronized Integer getNextId() {
        return nextId++;
    }

    public void setNotificationMethod(NotificationMethod update_method) {
        this.method = update_method;
    }

    @Override
    public String toString() {
        return studentNetId + " prefers " + method.name() + " notification for courseSectionId " + courseSectionId;
    }
}