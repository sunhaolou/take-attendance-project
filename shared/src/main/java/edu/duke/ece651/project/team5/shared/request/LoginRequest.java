package edu.duke.ece651.project.team5.shared.request;

/**
 * This is the request to login
 */
public class LoginRequest {
    private String userNetId;
    private String password;

    public LoginRequest(String userNetId, String password) {
        this.userNetId = userNetId;
        this.password = password;
    }

    public String getUserNetId() {
        return userNetId;
    }

    public void setUserNetId(String userNetId) {
        this.userNetId = userNetId;
    }

    public LoginRequest() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
