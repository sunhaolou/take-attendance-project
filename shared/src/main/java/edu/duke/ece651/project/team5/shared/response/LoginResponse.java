package edu.duke.ece651.project.team5.shared.response;

public class LoginResponse {
    private String token;
    private String role;
    private String LegalName;
    private String message;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getLegalName() {
        return LegalName;
    }
    public void setLegalName(String legalName) {
        LegalName = legalName;
    }

    public LoginResponse(String token, String role, String legalName) {
        this.token = token;
        this.role = role;
        LegalName = legalName;
        message = "Login successfully";
    }
    public LoginResponse(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LoginResponse() {
    }
    
    
}
