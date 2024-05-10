package edu.duke.ece651.project.team5.shared.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LoginRequestTest {
    @Test
    void testConstructorWithParameters() {
        String userNetId = "user123";
        String password = "password123";

        LoginRequest request = new LoginRequest(userNetId, password);

        assertEquals(userNetId, request.getUserNetId());
        assertEquals(password, request.getPassword());
    }

    @Test
    void testSettersAndGetters() {
        LoginRequest request = new LoginRequest();

        String userNetId = "user123";
        String password = "password123";

        request.setUserNetId(userNetId);
        request.setPassword(password);

        assertEquals(userNetId, request.getUserNetId());
        assertEquals(password, request.getPassword());
    }
}
