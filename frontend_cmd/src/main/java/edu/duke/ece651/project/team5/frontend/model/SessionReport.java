package edu.duke.ece651.project.team5.frontend.model;

public class SessionReport {
    private String courseSectionId;
    private String status;
    private String netId;
    private String time;

    public SessionReport(String courseSectionId, String status, String netId, String time) {
        this.courseSectionId = courseSectionId;
        this.status = status;
        this.netId = netId;
        this.time = time;
    }

    public SessionReport() {
      this("", "", "", "");
    }

    public String getCourseSectionId() {
        return courseSectionId;
    }

    public String getStatus() {
        return status;
    }

    public String getNetId() {
        return netId;
    }

    public String getTime() {
        return time;
    }
}
