package edu.duke.ece651.project.team5.frontend.model;

public abstract class Person {
    private String netId;
    private byte[] salt;
    private String password;
    private String legalName;
    private String email;
    private String phone;

    public Person() {
        netId = "";
        salt = new byte[16];
        password = "";
        legalName = "";
        email = "";
        phone = "";
    }

    public Person(String netId, String password, String legalName, String email, String phone, byte[] salt) {
        this.netId = netId;
        this.salt = salt;
        this.password = password;
        this.legalName = legalName;
        this.email = email;
        this.phone = phone;
    }

    public String getNetId() {
        return netId;
    }

    public String getPassword() {
        return password;
    }

    public String getLegalName() {
        return legalName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
