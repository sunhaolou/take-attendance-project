package edu.duke.ece651.project.team5.shared.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SectionAllRequestTest {
    @Test
    void testSetterAndGetter() {
        SectionAllRequest request = new SectionAllRequest();

        String userNetId = "johndoe";
        String password = "password123";
        String flag = "flag";

        request.setUserNetId(userNetId);
        request.setPassword(password);
        request.setFlag(flag);

        assertEquals(userNetId, request.getUserNetId());
        assertEquals(password, request.getPassword());
        assertEquals(flag, request.getFlag());
    }
}
