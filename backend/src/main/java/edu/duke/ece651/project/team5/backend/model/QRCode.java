package edu.duke.ece651.project.team5.backend.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class QRCode {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private Long id;
    private Timestamp expirationTime;
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public QRCode(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        expirationTime = new Timestamp(System.currentTimeMillis() + 1000 * 30);
    }

    public QRCode() {

        expirationTime = new Timestamp(System.currentTimeMillis() + 1000 * 30);
    }

    public Timestamp getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Timestamp expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
