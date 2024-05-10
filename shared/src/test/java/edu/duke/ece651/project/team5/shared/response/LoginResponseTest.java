package edu.duke.ece651.project.team5.shared.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LoginResponseTest {
    @Test
    void testSetterAndGetter() {
        String token = "token123";
        String role = "admin";
        String legalName = "John Doe";
        String message = "Login successfully";

        LoginResponse response = new LoginResponse();

        response.setToken(token);
        response.setRole(role);
        response.setLegalName(legalName);
        response.setMessage(message);

        assertEquals(token, response.getToken());
        assertEquals(role, response.getRole());
        assertEquals(legalName, response.getLegalName());
        assertEquals(message, response.getMessage());
    }

    @Test
    void testConstructor() {
        String token = "token123";
        String role = "admin";
        String legalName = "John Doe";
        String message = "Login successfully";

        LoginResponse response = new LoginResponse(token, role, legalName);

        assertEquals(token, response.getToken());
        assertEquals(role, response.getRole());
        assertEquals(legalName, response.getLegalName());
        assertEquals(message, response.getMessage());
    }

    @Test
    void testMessageConstructor() {
        String message = "Invalid credentials";

        LoginResponse response = new LoginResponse(message);

        assertEquals(message, response.getMessage());
    }
}
