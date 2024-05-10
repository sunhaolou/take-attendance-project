
package edu.duke.ece651.project.team5.backend.model;

import java.sql.Timestamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QRCodeTest {

    @Test
    public void testQRCodeConstructor() {
        String latitude = "40.7128";
        String longitude = "-74.0060";
        QRCode qrCode = new QRCode(latitude, longitude);

        Assertions.assertEquals(latitude, qrCode.getLatitude());
        Assertions.assertEquals(longitude, qrCode.getLongitude());
        Assertions.assertNotNull(qrCode.getExpirationTime());
    }

    @Test
    public void testQRCodeSettersAndGetters() {
        QRCode qrCode = new QRCode();

        String latitude = "37.7749";
        String longitude = "-122.4194";
        qrCode.setLatitude(latitude);
        qrCode.setLongitude(longitude);

        Assertions.assertEquals(latitude, qrCode.getLatitude());
        Assertions.assertEquals(longitude, qrCode.getLongitude());
    }

    @Test
    public void testQRCodeId() {
        QRCode qrCode = new QRCode();
        Long id = 12345L;
        qrCode.setId(id);

        Assertions.assertEquals(id, qrCode.getId());
    }

    @Test
    public void testQRCodeExpirationTime() {
        QRCode qrCode = new QRCode();
        qrCode.setExpirationTime(null);

        Assertions.assertNull(qrCode.getExpirationTime());

        qrCode.setExpirationTime(new Timestamp(System.currentTimeMillis()));

        Assertions.assertNotNull(qrCode.getExpirationTime());
    }
}