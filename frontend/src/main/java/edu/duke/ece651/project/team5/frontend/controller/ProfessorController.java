package edu.duke.ece651.project.team5.frontend.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.duke.ece651.project.team5.shared.request.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.frontend.utils.JsonOutputGenerator;
import edu.duke.ece651.project.team5.frontend.utils.PdfOutputGenerator;
import edu.duke.ece651.project.team5.frontend.utils.XmlOutputGenerator;
import edu.duke.ece651.project.team5.shared.model.*;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JsonOutputGenerator jsonOutputGenerator;

    @Autowired
    private XmlOutputGenerator xmlOutputGenerator;
    @Autowired
    private PdfOutputGenerator pdfOutputGenerator;
    // @Autowired

    @GetMapping("/dashboard")
    public String showDashboardPage(Model model) {
        try {

            // System.out.println("Token: " + tokenStore.getToken());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/section/all/profNameAndid"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + tokenStore.getToken())
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, List<String>> ProfNameAndSectionIds = objectMapper.readValue(response.body(),
                    new TypeReference<Map<String, List<String>>>() {
                    });
            // List<String> SectionIds = mapper.readValue(response.body(), new
            // TypeReference<List<String>>() {
            // });
            String ProfName = ProfNameAndSectionIds.keySet().iterator().next();
            List<String> sectionIds = ProfNameAndSectionIds.get(ProfName);
            System.out.println("Profname: " + ProfName);
            System.out.println("Status Code: " + response.statusCode());
            model.addAttribute("message", "Login Successful");
            model.addAttribute("profName", ProfName);
            model.addAttribute("sectionIds", sectionIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "professor_dashboard";
    }

    @GetMapping("/{sectionId}/dashboard")
    public String showSectionDashboardPage(Model model, @PathVariable String sectionId) {
        model.addAttribute("sectionId", sectionId);
        return "section_dashboard";
    }

    @GetMapping("/{sectionId}/sessions")
    public String showSectionSessionsPage(Model model, @PathVariable String sectionId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/session/" + sectionId + "/all"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + tokenStore.getToken())
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            List<String> sessionIds = objectMapper.readValue(response.body(), new TypeReference<List<String>>() {
            });
            System.out.println("Status Code: " + response.statusCode());

            model.addAttribute("sessionIds", sessionIds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "sessions";
    }

    @GetMapping("/{sectionId}/searchStudent")
    public String showStudentSearchPage(Model model, @PathVariable String sectionId) {
        model.addAttribute("sectionId", sectionId);
        model.addAttribute("foundStudents", true); // Default to true
        return "search_student";
    }

    @PostMapping("/{sectionId}/searchStudentResult")
    public String searchStudent(@PathVariable String sectionId, @RequestParam("legalName") String legalName,
            Model model) {
        List<Student> students = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/section/student/" + sectionId + "/" + legalName))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + tokenStore.getToken())
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            students = objectMapper.readValue(response.body(), new TypeReference<List<Student>>() {
            });
            System.out.println("Status Code: " + response.statusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (students.isEmpty()) {
            model.addAttribute("foundStudents", false);
            return "search_student"; // Return to the search form
        } else {
            model.addAttribute("foundStudents", true);
            model.addAttribute("students", students);
            return "search_student_result"; // Show the search results
        }
    }

    @GetMapping("/{sectionId}/{sessionId}")
    public String showSectionStudentsPage(Model model, @PathVariable String sectionId, @PathVariable String sessionId) {
        System.out.println("Section ID: " + sectionId);
        System.out.println("Session ID: " + sessionId);
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/section/student/" + sectionId))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + tokenStore.getToken())
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            List<Student> students = objectMapper.readValue(response.body(), new TypeReference<List<Student>>() {
            });
            System.out.println("Status Code: " + response.statusCode());

            model.addAttribute("sessionId", sessionId);
            model.addAttribute("students", students);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "attendance_home";
    }

    @GetMapping("/{sectionId}/{sessionId}/{netId}")
    public String showStudentAttendancePage(Model model, @PathVariable String sectionId, @PathVariable String sessionId,
            @PathVariable String netId) {

        model.addAttribute("sessionId", sessionId);
        model.addAttribute("netId", netId);

        return "attendance_student";
    }

    @GetMapping("/{sectionId}/{netId}/sessions")
    public String showStudentSessionPage(Model model, @PathVariable String sectionId, @PathVariable String netId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/session/" + sectionId + "/all"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + tokenStore.getToken())
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            List<String> sessionIds = objectMapper.readValue(response.body(), new TypeReference<List<String>>() {
            });
            System.out.println("Status Code: " + response.statusCode());
            model.addAttribute("netId", netId);
            model.addAttribute("sessionIds", sessionIds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "student_session";
    }

    @GetMapping("/section/report")
    public String getReport(Model model, @RequestParam String sectionId) {
        try {
            SectionReport report = getSectionReport(sectionId);
            Map<String, String> attendance = report.getAttendance();

            model.addAttribute("attendance", attendance);
            model.addAttribute("sectionId", sectionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "section_report";
    }

    @GetMapping("/section/exportPdfReport")
    public String generatorSectionPdfReport(Model model, @RequestParam String sectionId) {
        try {
            SectionReport report = getSectionReport(sectionId);
            pdfOutputGenerator.exportAttendanceForSection(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/professor/dashboard";
    }

    @GetMapping("/section/exportJsonReport")
    public String generatorSectionJsonReport(Model model, @RequestParam String sectionId) {
        try {
            SectionReport report = getSectionReport(sectionId);
            jsonOutputGenerator.exportAttendanceForSection(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/professor/dashboard";
    }

    @GetMapping("/section/exportXmlReport")
    public String generatorSectionXmlReport(Model model, @RequestParam String sectionId) {
        try {
            SectionReport report = getSectionReport(sectionId);
            xmlOutputGenerator.exportAttendanceForSection(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/professor/dashboard";
    }

    public SectionReport getSectionReport(String sectionId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/section/report?sectionId=" + sectionId))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenStore.getToken())
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("status code: " + response.statusCode());
        SectionReport report = objectMapper.readValue(response.body(), SectionReport.class);
        return report;

    }

}
