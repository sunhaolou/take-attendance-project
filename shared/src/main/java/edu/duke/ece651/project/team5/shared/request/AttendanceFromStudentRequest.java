package edu.duke.ece651.project.team5.shared.request;

public class AttendanceFromStudentRequest extends TakeAndUpdateAttendanceRequest {
    private String latitude;
    private String longitude;
    private Long uuid;
    private String password;

    public Long getUuid() {
        return uuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public AttendanceFromStudentRequest(String sessionId, String netId, String status, String latitude,
            String longitude, Long uuid) {
        super(sessionId, netId, status);
        this.latitude = latitude;
        this.longitude = longitude;
        this.uuid = uuid;
    }

    public AttendanceFromStudentRequest(String sessionId, String netId, String status, String latitude,
            String longitude, Long uuid, String password) {
        super(sessionId, netId, status);
        this.latitude = latitude;
        this.longitude = longitude;
        this.uuid = uuid;
        this.password = password;
    }

    public AttendanceFromStudentRequest(String latitude, String longitude, Long uuid, String password) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.uuid = uuid;
        this.password = password;
    }

    public AttendanceFromStudentRequest() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
