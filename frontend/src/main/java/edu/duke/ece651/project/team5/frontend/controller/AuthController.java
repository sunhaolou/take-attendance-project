
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
import edu.duke.ece651.project.team5.shared.request.*;
import edu.duke.ece651.project.team5.shared.response.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("userNetId") String userNetId, @RequestParam("password") String password,
            Model model) {
        try {
            LoginRequest loginRequest = new LoginRequest(userNetId, password); // Assuming userNetId and password are
                                                                               // defined
            String requestBody = objectMapper.writeValueAsString(loginRequest);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/auth/login")) // Ensure correct protocol and host
                    .header("Content-Type", "application/json") // Set the content type
                    .header("Accept", "application/json") // Set the content type
                    .POST(BodyPublishers.ofString(requestBody)) // Use a StringBodyPublisher to send JSON
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            LoginResponse loginResponse = objectMapper.readValue(response.body(),
                    new TypeReference<LoginResponse>() {
                    });
            if (response.statusCode() != 200) {
                model.addAttribute("message", "Login Failed");
                return "login";
            }

            tokenStore.setToken(loginResponse.getToken());
            String legalName = loginResponse.getLegalName();
            String role = loginResponse.getRole();
            System.out.println("Role: " + role);
            System.out.println("Legal Name: " + legalName);

            if (loginResponse.getRole().equals("ROLE_PROFESSOR")) {
                return "redirect:/professor/dashboard";
            } else if (loginResponse.getRole().equals("ROLE_STUDENT")) {
                tokenStore.setToken(loginResponse.getToken());
                return "redirect:/student/dashboard";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login";

    }
}
