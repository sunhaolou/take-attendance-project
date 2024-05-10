package edu.duke.ece651.project.team5.frontend.controller;



import org.springframework.stereotype.Component;

@Component
public class TokenStore {
    private String token;

    public synchronized void setToken(String token) {
        this.token = token;
    }

    public synchronized String getToken() {
        return token;
    }
}
