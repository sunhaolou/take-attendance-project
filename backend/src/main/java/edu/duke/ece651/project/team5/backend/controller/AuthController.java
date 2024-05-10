
package edu.duke.ece651.project.team5.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.duke.ece651.project.team5.backend.config.JwtService;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.shared.request.*;
import edu.duke.ece651.project.team5.shared.response.LoginResponse;

import edu.duke.ece651.project.team5.backend.service.AuthService;

@Controller
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtTokenUtil;



    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> handleLogin(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.authenticate(loginRequest);
        return ResponseEntity
                .ok()
                .body(loginResponse);
    }



}
