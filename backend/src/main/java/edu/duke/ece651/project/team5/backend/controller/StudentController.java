package edu.duke.ece651.project.team5.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.context.SecurityContextHolder;

import edu.duke.ece651.project.team5.backend.service.StudentService;
import edu.duke.ece651.project.team5.backend.utils.*;
import edu.duke.ece651.project.team5.shared.model.StudentReport;
import edu.duke.ece651.project.team5.shared.request.UpdateStudentNotificationRequest;
import java.util.*;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private ReportGenerator reportGenerator;

    @Autowired
    private StudentService studentService;

 
    @GetMapping("/{sectionid}/getAndChangeNoti")
    public ResponseEntity<String> getAndChangeNoti(@PathVariable String sectionid) {
        String notiPref = studentService.geteNotiPrefBySec(sectionid);
        return ResponseEntity.ok().body(notiPref);
    }

    @PostMapping("/{sectionid}/updateNotiPref")
    public ResponseEntity<String> updateNotiPref(@RequestBody UpdateStudentNotificationRequest request) {
        String sectionid = request.getCourseSectionId();
        String notiPref = request.getNotificationMethod();
        studentService.updateNotiPrefBySec(sectionid, notiPref);
        return ResponseEntity.ok().body("Notification Preference Updated");
    }

    @GetMapping("/report")
    public ResponseEntity<StudentReport> getStudentReport() {
        StudentReport report = studentService.generateStudentReport();
        return ResponseEntity.ok().body(report);
    }
}
