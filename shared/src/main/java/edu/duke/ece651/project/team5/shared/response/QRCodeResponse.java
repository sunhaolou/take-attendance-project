package edu.duke.ece651.project.team5.shared.response;

import java.sql.Timestamp;

public class QRCodeResponse {
    private String qrCodeBase64;
    private Long Id;
    private Timestamp expirationTime;

    public String getQrCodeBase64() {
        return qrCodeBase64;
    }

    public void setQrCodeBase64(String qrCodeBase64) {
        this.qrCodeBase64 = qrCodeBase64;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Timestamp getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Timestamp expirationTime) {
        this.expirationTime = expirationTime;
    }

    public QRCodeResponse() {
    }

    public QRCodeResponse(String qrCodeBase64, Long id, Timestamp expirationTime) {
        this.qrCodeBase64 = qrCodeBase64;
        Id = id;
        this.expirationTime = expirationTime;
    }

}
