package edu.duke.ece651.project.team5.backend.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.duke.ece651.project.team5.backend.service.CourseService;
import edu.duke.ece651.project.team5.backend.utils.ReportGenerator;
import edu.duke.ece651.project.team5.shared.model.SectionReport;
import edu.duke.ece651.project.team5.shared.model.Student;
import edu.duke.ece651.project.team5.backend.model.Pair;

@Controller
@RequestMapping("section")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private ReportGenerator reportGenerator;

    @GetMapping("/all/id")
    public ResponseEntity<List<String>> getSectionIds() {
        List<String> SectionIds = courseService.getSectionIds();
        return ResponseEntity.ok().body(SectionIds);
    }

    @GetMapping("/all/profNameAndid")
    public ResponseEntity<Map<String, List<String>>> getProfNameAndSectionIds() {
        Map<String, List<String>> ProfNameAndSectionIds = courseService.getProfNameAndSectionIds();
        return ResponseEntity.ok().body(ProfNameAndSectionIds);
    }

    @GetMapping("/student/{courseSectionId}")
    public ResponseEntity<List<Student>> getEnrolledStudent(@PathVariable String courseSectionId) {
        List<Student> enrolledStudents = courseService.getEnrolledStudentsFromSection(courseSectionId);

        return ResponseEntity.ok().body(enrolledStudents);
    }

    @GetMapping("/student/{courseSectionId}/{legalName}")
    public ResponseEntity<List<Student>> getEnrolledStudentByName(@PathVariable String courseSectionId,
            @PathVariable String legalName) {
        List<Student> enrolledStudents = courseService.getEnrolledStudentsFromSection(courseSectionId, legalName);

        return ResponseEntity.ok().body(enrolledStudents);
    }

    @GetMapping("/report")
    public ResponseEntity<SectionReport> getSectionReport(@RequestParam String sectionId) {
        return ResponseEntity.ok().body(reportGenerator.generateSectionReport(sectionId));

    }

    @GetMapping("/all/StuNameAndSecid")
    public ResponseEntity<Map<String, List<String>>> getStuNameAndSectionIds() {
        Map<String, List<String>> StuNameAndSectionIds = courseService.getStuNameAndSectionIds();
        return ResponseEntity.ok().body(StuNameAndSectionIds);
    }

}
