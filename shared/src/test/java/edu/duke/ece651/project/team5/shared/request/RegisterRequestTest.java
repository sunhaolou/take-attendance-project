package edu.duke.ece651.project.team5.shared.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RegisterRequestTest {
    @Test
    void testSetterAndGetter() {
        RegisterRequest request = new RegisterRequest();

        String legalName = "John Doe";
        String userNetId = "johndoe";
        String password = "password123";
        String flag = "flag";
        String email = "john.doe@example.com";
        String phone = "1234567890";
        String preferredName = "Johnny";

        request.setLegalName(legalName);
        request.setUserNetId(userNetId);
        request.setPassword(password);
        request.setFlag(flag);
        request.setEmail(email);
        request.setPhone(phone);
        request.setPreferredName(preferredName);

        assertEquals(legalName, request.getLegalName());
        assertEquals(userNetId, request.getUserNetId());
        assertEquals(password, request.getPassword());
        assertEquals(flag, request.getFlag());
        assertEquals(email, request.getEmail());
        assertEquals(phone, request.getPhone());
        assertEquals(preferredName, request.getPreferredName());
    }
}
