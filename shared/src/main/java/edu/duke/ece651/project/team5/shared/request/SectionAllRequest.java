package edu.duke.ece651.project.team5.shared.request;

/**
 * This is the request to get all sections
 */
public class SectionAllRequest {
    private String userNetId;
    private String password;
    private String flag;



    public SectionAllRequest() {
    }


    public String getUserNetId() {
        return userNetId;
    }

    public void setUserNetId(String userNetId) {
        this.userNetId = userNetId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
