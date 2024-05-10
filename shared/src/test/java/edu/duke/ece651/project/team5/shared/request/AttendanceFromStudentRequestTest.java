package edu.duke.ece651.project.team5.shared.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AttendanceFromStudentRequestTest {
    @Test
    void testConstructorWithAllParameters() {
        String sessionId = "session123";
        String netId = "student123";
        String status = "PRESENT";
        String latitude = "123.456";
        String longitude = "78.910";
        Long uuid = 123456L;
        String password = "password123";

        AttendanceFromStudentRequest request = new AttendanceFromStudentRequest(sessionId, netId, status, latitude,
                longitude, uuid, password);

        assertEquals(sessionId, request.getSessionId());
        assertEquals(netId, request.getNetId());
        assertEquals(status, request.getStatus());
        assertEquals(latitude, request.getLatitude());
        assertEquals(longitude, request.getLongitude());
        assertEquals(uuid, request.getUuid());
        assertEquals(password, request.getPassword());
    }

    @Test
    void testSettersAndGetters() {
        AttendanceFromStudentRequest request = new AttendanceFromStudentRequest();

        String latitude = "123.456";
        String longitude = "78.910";
        Long uuid = 123456L;
        String password = "password123";

        request.setLatitude(latitude);
        request.setLongitude(longitude);
        request.setUuid(uuid);
        request.setPassword(password);

        assertEquals(latitude, request.getLatitude());
        assertEquals(longitude, request.getLongitude());
        assertEquals(uuid, request.getUuid());
        assertEquals(password, request.getPassword());
    }
}
