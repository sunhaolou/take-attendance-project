package edu.duke.ece651.project.team5.frontend.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.shared.request.*;
import edu.duke.ece651.project.team5.shared.response.*;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("")
    public String updateStudentAttendance(@RequestParam("sectionId") String sectionId,
            @RequestParam("sessionId") String sessionId,
            @RequestParam("netId") String netId,
            @RequestParam("status") String status,
            RedirectAttributes redirectAttributes) {
        try {
            TakeAndUpdateAttendanceRequest attendanceRequest = new TakeAndUpdateAttendanceRequest(sessionId, netId,
                    status);

            String requestBody = objectMapper.writeValueAsString(attendanceRequest);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/session/takeAttendance"))
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
        return "redirect:/professor/" + sectionId + "/" + sessionId; // Redirect back to
                                                                     // the home page

    }

    @GetMapping("/qrcode")
    public String showQRCode(Model model, @RequestParam String sessionId, @RequestParam String latitude,
            @RequestParam String longitude) {
        try {
            // System.out.println("Latitude: " + latitude);
            // System.out.println("Longitude: " + longitude);
            // System.out.println("Session ID: " + sessionId);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/session/qrcode?sessionId=" + sessionId + "&latitude=" + latitude
                            + "&longitude=" + longitude))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + tokenStore.getToken())
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Message: " + response.body());
            QRCodeResponse qrCodeResponse = objectMapper.readValue(response.body(),
                    new TypeReference<QRCodeResponse>() {
                    });
            model.addAttribute("qrCodeImage", qrCodeResponse.getQrCodeBase64());
            model.addAttribute("expirationTime", qrCodeResponse.getExpirationTime());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error generating QR code");
        }
        return "qr_code";
    }

    @GetMapping("/form")
    public String showAttendanceForm(Model model, @RequestParam String sessionId, @RequestParam String uuid) {
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("uuid", uuid);
        return "attendance_form";
    }

    @PostMapping("/submit-attendance")
    public String submitAttendance(Model model, @RequestParam String netId, @RequestParam String password,
            @RequestParam double latitude,
            @RequestParam double longitude, @RequestParam String sessionId, @RequestParam String uuid) {
        AttendanceFromStudentRequest attendanceRequest = new AttendanceFromStudentRequest(sessionId, netId, "PRESENT",
                Double.toString(latitude),
                Double.toString(longitude),
                Long.parseLong(uuid), password);
        try {

            String requestBody = objectMapper.writeValueAsString(attendanceRequest);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/session/attendance/student"))
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return "attendance_success";
            }
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Message: " + response.body());
            model.addAttribute("message", response.body());
            System.out.println("Message: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "attendance_form";
    }
}
