package edu.duke.ece651.project.team5.shared.model;

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

    public void setCourseSectionId(String courseSectionId) {
        this.courseSectionId = courseSectionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SessionReport [courseSectionId=" + courseSectionId + ", status=" + status + ", netId=" + netId
                + ", time=" + time + "]";
    }

}
