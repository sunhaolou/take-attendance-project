package edu.duke.ece651.project.team5.shared.request;

/**
 * Represents a request to take and update attendance.
 */
public class TakeAndUpdateAttendanceRequest {
    private String sessionId;
    private String netId;
    private String status;

    public String getSessionId() {
        return sessionId;
    }

    public TakeAndUpdateAttendanceRequest(String sessionId, String netId, String status) {
        this.sessionId = sessionId;
        this.netId = netId;
        this.status = status;
    }

    public TakeAndUpdateAttendanceRequest() {
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}