package edu.duke.ece651.project.team5.shared.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
    @Id
    private String netId;
    private String password;
    private String legalName;
    private String email;
    private String phone;

    // public Person() {
    // this("", "", "", "", "", new byte[16]);
    // }

    public Person(String netId, String password, String legalName, String email, String phone) {
        this.netId = netId;
        this.password = password;
        this.legalName = legalName;
        this.email = email;
        this.phone = phone;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
