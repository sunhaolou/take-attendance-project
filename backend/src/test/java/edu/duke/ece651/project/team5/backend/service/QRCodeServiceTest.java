package edu.duke.ece651.project.team5.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

public class QRCodeServiceTest {

    @InjectMocks
    private QRCodeService qrCodeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateQRCodeBase64() throws Exception {
        String qrText = "https://example.com";
        int width = 200;
        int height = 200;

        String result = qrCodeService.generateQRCodeBase64(qrText, width, height);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.matches("^[A-Za-z0-9+/=]+$"));
    }
}
