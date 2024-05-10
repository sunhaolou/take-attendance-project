package edu.duke.ece651.project.team5.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.duke.ece651.project.team5.backend.service.AuthService;
import edu.duke.ece651.project.team5.backend.service.SessionService;
import edu.duke.ece651.project.team5.shared.request.AttendanceFromStudentRequest;
import edu.duke.ece651.project.team5.shared.request.TakeAndUpdateAttendanceRequest;
import edu.duke.ece651.project.team5.shared.response.*;

@Controller
@RequestMapping("session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AuthService authService;

    @GetMapping("/{courseSectionId}/all")
    public ResponseEntity<List<String>> getSessionIds(@PathVariable String courseSectionId) {
        List<String> sessionIds = sessionService.getAllSessionIdsOfSection(courseSectionId);
        // System.out.println(sessionIds.size());
        return ResponseEntity.ok().body(sessionIds);
    }

    @PostMapping("/takeAttendance")
    public ResponseEntity<String> takeAttendance(
            @RequestBody TakeAndUpdateAttendanceRequest takeAndUpdateAttendanceRequest) {

        String sessionId = takeAndUpdateAttendanceRequest.getSessionId();
        String studentNetId = takeAndUpdateAttendanceRequest.getNetId();
        String status = takeAndUpdateAttendanceRequest.getStatus();

        sessionService.takeAttendance(sessionId, studentNetId, status);
        return ResponseEntity.ok().body("Attendance taken successfully");
    }

    @PostMapping("/updateAttendance")
    public ResponseEntity<String> updateAttendance(
            @RequestBody TakeAndUpdateAttendanceRequest takeAndUpdateAttendanceRequest) {

        String sessionId = takeAndUpdateAttendanceRequest.getSessionId();
        String studentNetId = takeAndUpdateAttendanceRequest.getNetId();
        String status = takeAndUpdateAttendanceRequest.getStatus();

        sessionService.updateAttendance(sessionId, studentNetId, status);
        return ResponseEntity.ok().body("Attendance taken successfully");
    }

    @PostMapping("/attendance/student")
    public ResponseEntity<String> takeAttendanceFromStudent(
            @RequestBody AttendanceFromStudentRequest attendanceFromStudentRequest) {
        String sessionId = attendanceFromStudentRequest.getSessionId();
        String studentNetId = attendanceFromStudentRequest.getNetId();
        String password = attendanceFromStudentRequest.getPassword();
        String longitude = attendanceFromStudentRequest.getLongitude();
        String latitude = attendanceFromStudentRequest.getLatitude();
        Long uuid = attendanceFromStudentRequest.getUuid();
        Boolean isExpired = sessionService.checkQRCodeExpiration(sessionId, uuid);

        // check if the QR code is expired
        if (isExpired) {
            return ResponseEntity.badRequest().body("QR Code expired");
        }
        // check if the student is too far away
        Boolean result = sessionService.compareLocation(uuid,
                latitude, longitude);
        if (result == false) {
            return ResponseEntity.badRequest().body("Student too far away");
        }
        // check if the student has right credentials
        if (!authService.verifyStudentCredential(studentNetId, password)) {
            return ResponseEntity.badRequest().body("Invalid password");
        }
        try {
            sessionService.takeAttendance(sessionId, studentNetId, "PRESENT");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error taking attendance");
        }
        return ResponseEntity.ok().body("Attendance taken successfully");

    }

    @GetMapping("/qrcode")
    public ResponseEntity<QRCodeResponse> getQRCode(@RequestParam String sessionId, @RequestParam String latitude,
            @RequestParam String longitude) {
        QRCodeResponse qrCode = sessionService.getQRCode(sessionId, latitude, longitude);
        return ResponseEntity.ok().body(qrCode);
    }

}
