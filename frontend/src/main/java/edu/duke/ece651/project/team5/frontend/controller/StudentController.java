package edu.duke.ece651.project.team5.frontend.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.shared.request.UpdateStudentNotificationRequest;
import edu.duke.ece651.project.team5.frontend.utils.JsonOutputGenerator;
import edu.duke.ece651.project.team5.frontend.utils.PdfOutputGenerator;
import edu.duke.ece651.project.team5.frontend.utils.XmlOutputGenerator;
import edu.duke.ece651.project.team5.shared.model.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JsonOutputGenerator jsonOutputGenerator;

    @Autowired
    private PdfOutputGenerator pdfOutputGenerator;

    @Autowired
    private XmlOutputGenerator xmlOutputGenerator;

    @GetMapping("/dashboard")
    public String showDashboardPage(Model model) {
        model.addAttribute("message", "Hi, Student! What would you like to do today?");
        model.addAttribute("option1", "Change Notification Preference");
        model.addAttribute("option2", "Get Attendance Report");
        return "student_dashboard";
    }

    @GetMapping("/changeNotiPref")
    public String showNotiSections(Model model) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/section/all/StuNameAndSecid"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + tokenStore.getToken())
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, List<String>> StuNameAndSecids = objectMapper.readValue(response.body(),
                    new TypeReference<Map<String, List<String>>>() {
                    });
            String StuName = StuNameAndSecids.keySet().iterator().next();
            List<String> sectionIds = StuNameAndSecids.get(StuName);
            System.out.println("Status Code: " + response.statusCode());
            model.addAttribute("message", "Login Successful");
            model.addAttribute("stuName", StuName);
            model.addAttribute("sectionIds", sectionIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "student_noti_sections";
    }

    @GetMapping("/{sectionId}/changeNotiPref")
    public String showNotiForSec(Model model, @PathVariable String sectionId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/student/" + sectionId + "/getAndChangeNoti"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + tokenStore.getToken())
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String notiPref = response.body();
            System.out.println("Status Code: " + response.statusCode());
            model.addAttribute("message", "Login Successful");
            model.addAttribute("sectionId", sectionId);
            model.addAttribute("notiPref", notiPref);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "student_change_sec_noti";
    }

    @PostMapping("/updateNotiPref")
    public String updateNotiPref(@RequestParam("sectionId") String sectionId,
            @RequestParam("newNotiPref") String newNotiPref,
            RedirectAttributes redirectAttributes) {
        try {
            UpdateStudentNotificationRequest updateNotiRequest = new UpdateStudentNotificationRequest(sectionId,
                    newNotiPref);

            String requestBody = objectMapper.writeValueAsString(updateNotiRequest);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/student/" + sectionId + "/updateNotiPref"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + tokenStore.getToken())
                    .POST(BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status Code: " + response.statusCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/student/dashboard";

    }

    @GetMapping("/report")
    public String getReport(Model model) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/student/report"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + tokenStore.getToken())
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            StudentReport report = objectMapper.readValue(response.body(), StudentReport.class);
            String score = report.getScore();
            List<SessionReport> reports = report.getReports();
            model.addAttribute("score", score);
            model.addAttribute("reports", reports);
            System.out.println("Status Code: " + response.statusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "student_report";
    }

    @GetMapping("/exportJsonReport")
    public String exportJsonReport(Model model) {
        try {
            StudentReport report = getReport();
            jsonOutputGenerator.exportAttendanceForStudents(report);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return "redirect:/student/dashboard";
    }

    @GetMapping("/exportPdfReport")
    public String exportPdfReport(Model model) {
        try {
            StudentReport report = getReport();
            pdfOutputGenerator.exportAttendanceForStudents(report);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return "redirect:/student/dashboard";

    }

    @GetMapping("/exportXmlReport")
    public String exportXmlReport(Model model) {
        try {
            StudentReport report = getReport();
            xmlOutputGenerator.exportAttendanceForStudents(report);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return "redirect:/student/dashboard";

    }

    public StudentReport getReport() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/student/report"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenStore.getToken())
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StudentReport report = objectMapper.readValue(response.body(), StudentReport.class);
        return report;
    }

    // @GetMapping("/netid")
    // public String showDashboardPage(Model model) {
    // try {
    // HttpRequest request = HttpRequest.newBuilder()
    // .uri(new URI("http://localhost:8080/student/dashboard"))
    // .header("Content-Type", "application/json")
    // .header("Authorization", "Bearer " + tokenStore.getToken())
    // .GET()
    // .build();

    // HttpClient client = HttpClient.newHttpClient();
    // HttpResponse<String> response = client.send(request,
    // HttpResponse.BodyHandlers.ofString());
    // Map<String, List<String>> ProfNameAndSectionIds =
    // objectMapper.readValue(response.body(),
    // new TypeReference<Map<String, List<String>>>() {
    // });
    // // List<String> SectionIds = mapper.readValue(response.body(), new
    // // TypeReference<List<String>>() {
    // // });
    // String ProfName = ProfNameAndSectionIds.keySet().iterator().next();
    // List<String> sectionIds = ProfNameAndSectionIds.get(ProfName);
    // System.out.println("Profname: " + ProfName);
    // System.out.println("Status Code: " + response.statusCode());
    // model.addAttribute("message", "Login Successful");
    // model.addAttribute("profName", ProfName);
    // model.addAttribute("sectionIds", sectionIds);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return "professor_dashboard";
    // }
}
