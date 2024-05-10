package edu.duke.ece651.project.team5.backend.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.duke.ece651.project.team5.backend.model.QRCode;
import edu.duke.ece651.project.team5.backend.repository.AttendanceRecordDao;
import edu.duke.ece651.project.team5.backend.repository.LectureDao;
import edu.duke.ece651.project.team5.backend.repository.QRCodeDao;
import edu.duke.ece651.project.team5.backend.repository.SectionDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.backend.utils.ReportGenerator;
import edu.duke.ece651.project.team5.shared.enums.AttendanceStatus;
import edu.duke.ece651.project.team5.shared.model.*;
import edu.duke.ece651.project.team5.shared.response.*;

/**
 * The SessionService class provides methods to manage sessions and attendance
 * for a course.
 */
@Service
public class SessionService {

    @Autowired
    private LectureDao sessionDao;

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ReportGenerator reportGenerator;

    @Autowired
    private AttendanceRecordDao attendanceRecordDao;

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private QRCodeDao qrCodeDao;

    /**
     * Takes attendance for a session and updates the session in the database.
     * 
     * @param session the Session object for which attendance is being taken
     * @param student the Student object for whom attendance is being recorded
     * @param status  the attendance status as a string (e.g., "PRESENT", "ABSENT")
     * @throws SQLException if there is an error updating the session in the
     *                      database
     */
    public void takeAttendance(String sessionId, String studentNetId, String status) {
        Optional<Lecture> session = sessionDao.findBySessionId(sessionId);
        Optional<Student> student = studentDao.findByNetId(studentNetId);

        if (student.isEmpty()) {
            throw new IllegalArgumentException("Student not found");
        }
        if (session.isEmpty()) {
            throw new IllegalArgumentException("Session not found");
        }
        AttendanceStatus statusEnum = AttendanceStatus.valueOf(status.toUpperCase());
        session.get().takeAttendance(student.get(), statusEnum);
        Optional<AttendanceRecord> existingRecord = attendanceRecordDao.findByLectureIdAndStudentNetId(sessionId,
                studentNetId);
        AttendanceRecord record;
        if (existingRecord.isPresent()) {
            record = existingRecord.get();
            record.setStatus(statusEnum);
            attendanceRecordDao.save(record);

        } else {
            record = new AttendanceRecord(sessionId, studentNetId, statusEnum);
            session.get().getAttendance().put(studentNetId, record);
            sessionDao.save(session.get());
            // attendanceRecordDao.saveAndFlush(record);
        }
    }

    /**
     * Retrieves all sessions of a given section.
     * 
     * @param section the Section object for which to retrieve sessions
     * @return a list of Session objects belonging to the section
     */
    public List<Session> getAllSessionOfSection(Section section) {
        return section.getSessions();
    }

    /**
     * Retrieves the IDs of all sessions of a given section.
     * 
     * @param section the Section object for which to retrieve session IDs
     * @return a list of session IDs belonging to the section
     */
    public List<String> getAllSessionIdsOfSection(String courseSectionId) {
        Optional<Section> section = sectionDao.findByCourseSectionId(courseSectionId);
        if (section.isEmpty()) {
            throw new IllegalArgumentException("Section not found");
        }
        System.out.println(section.get().getSessions().size());
        return section.get().getSessions().stream().map(session -> session.getSessionId()).toList();
    }

    /**
     * Updates the attendance for a session, generates a report for the student, and
     * updates the session in the database.
     * 
     * @param session         the Session object for which attendance is being
     *                        updated
     * @param student         the Student object for whom attendance is being
     *                        updated
     * @param status          the updated attendance status as a string (e.g.,
     *                        "PRESENT", "ABSENT")
     * @param reportGenerator the ReportGenerator object used to generate the report
     * @throws SQLException             if there is an error updating the session in
     *                                  the database
     * @throws IllegalArgumentException if the report generation fails
     */
    public void updateAttendance(String sessionId, String studentNetId, String status) {
        takeAttendance(sessionId, studentNetId, status);
        Student student = studentDao.findByNetId(studentNetId).get();
        Lecture session = sessionDao.findBySessionId(sessionId).get();
        if (student.subscribeSection(session.getCourseSectionId())) {
            try {
                // reportGenerator.generateOneReportForUpdate(student);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new IllegalArgumentException("Failed to generate report");
            }
        }
    }

    public Boolean compareLocation(Long uuid, String latitude, String longitude) {
        Optional<QRCode> qrCode = qrCodeDao.findById(uuid);
        if (qrCode.isEmpty()) {
            throw new IllegalArgumentException("QRCode not found");
        }

        // String DUKE_LATITUDE = "36.0014";
        // String DUKE_LONGITUDE = "-78.9382";
        String classLatitude = qrCode.get().getLatitude();
        String classLongitude = qrCode.get().getLongitude();
        double distance = distance(Double.parseDouble(classLatitude), Double.parseDouble(classLongitude),
                Double.parseDouble(latitude), Double.parseDouble(longitude), "K");

        System.out.println(distance);
        // 100 meters
        return distance < 0.10;

    }

    public QRCodeResponse getQRCode(String sessionId, String latitude, String longitude) {
        try {
            QRCode qrCode = new QRCode(latitude, longitude);
            qrCodeDao.save(qrCode);
            Long uuid = qrCode.getId();
            String qrCodeImage = qrCodeService.generateQRCodeBase64(
                    "https://vcm-37897.vm.duke.edu:3000/attendance/form" + "?sessionId=" + sessionId + "&uuid=" + uuid,
                    250,
                    250);
            QRCodeResponse qrCodeResponse = new QRCodeResponse(qrCodeImage, qrCode.getId(), qrCode.getExpirationTime());
            return qrCodeResponse;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Boolean checkQRCodeExpiration(String sessionId, Long uuid) {
        Optional<QRCode> qrCode = qrCodeDao.findById(uuid);
        if (qrCode.isEmpty()) {
            throw new IllegalArgumentException("QRCode not found");
        }
        if (qrCode.get().getExpirationTime().before(new java.util.Date())) {
            return true;
        }
        return false;
    }

    private double distance(double lat1, double lon1, double lat2, double lon2, String sr) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (sr.equals("K")) {
            dist = dist * 1.609344;
        } else if (sr.equals("N")) {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    public double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
