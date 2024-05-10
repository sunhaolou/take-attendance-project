package edu.duke.ece651.project.team5.shared.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

public class QRCodeResponseTest {
    @Test
    void testSetterAndGetter() {
        String qrCodeBase64 = "abcd1234";
        Long id = 12345L;
        Timestamp expirationTime = new Timestamp(System.currentTimeMillis());

        QRCodeResponse response = new QRCodeResponse();

        response.setQrCodeBase64(qrCodeBase64);
        response.setId(id);
        response.setExpirationTime(expirationTime);

        assertEquals(qrCodeBase64, response.getQrCodeBase64());
        assertEquals(id, response.getId());
        assertEquals(expirationTime, response.getExpirationTime());
    }

    @Test
    void testConstructor() {
        String qrCodeBase64 = "abcd1234";
        Long id = 12345L;
        Timestamp expirationTime = new Timestamp(System.currentTimeMillis());

        QRCodeResponse response = new QRCodeResponse(qrCodeBase64, id, expirationTime);

        assertEquals(qrCodeBase64, response.getQrCodeBase64());
        assertEquals(id, response.getId());
        assertEquals(expirationTime, response.getExpirationTime());
    }

    @Test
    void testDefaultConstructor() {
        QRCodeResponse response = new QRCodeResponse();

        assertNotNull(response);
    }
}
