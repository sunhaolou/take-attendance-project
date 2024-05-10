package edu.duke.ece651.project.team5.shared.request;

/**
 * This is the request to update the student notification
 */
public class UpdateStudentNotificationRequest {
    //private String userNetId;
    private String courseSectionId;
    //private String password;
    // "true" or "false"
    private String notificationMethod;
    //private String flag;

    // public String getPassword() {
    //     return password;
    // }

    // public String getUserNetId() {
    //     return userNetId;
    // }

    // public void setUserNetId(String userNetId) {
    //     this.userNetId = userNetId;
    // }

    public String getCourseSectionId() {
        return courseSectionId;
    }

    public void setCourseSectionId(String courseSectionId) {
        this.courseSectionId = courseSectionId;
    }

    public String getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(String notificationMethod) {
        this.notificationMethod = notificationMethod;
    }

    // public String getFlag() {
    //     return flag;
    // }

    // public void setFlag(String flag) {
    //     this.flag = flag;
    // }

    public UpdateStudentNotificationRequest(String courseSectionId, String notificationMethod) {
        this.courseSectionId = courseSectionId;
        this.notificationMethod = notificationMethod;
    }

    public UpdateStudentNotificationRequest() {
    }

    // public void setPassword(String password) {
    //     this.password = password;
    // }
}
