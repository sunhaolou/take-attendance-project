package edu.duke.ece651.project.team5.shared.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TakeAndUpdateAttendanceRequestTest {
    @Test
    void testSetterAndGetter() {
        TakeAndUpdateAttendanceRequest request = new TakeAndUpdateAttendanceRequest();

        String sessionId = "session123";
        String netId = "johndoe";
        String status = "present";

        request.setSessionId(sessionId);
        request.setNetId(netId);
        request.setStatus(status);

        assertEquals(sessionId, request.getSessionId());
        assertEquals(netId, request.getNetId());
        assertEquals(status, request.getStatus());
    }

    @Test
    void testConstructor() {
        String sessionId = "session123";
        String netId = "johndoe";
        String status = "present";

        TakeAndUpdateAttendanceRequest request = new TakeAndUpdateAttendanceRequest(sessionId, netId, status);

        assertEquals(sessionId, request.getSessionId());
        assertEquals(netId, request.getNetId());
        assertEquals(status, request.getStatus());
    }
}
