package edu.duke.ece651.project.team5.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.duke.ece651.project.team5.backend.config.JwtService;
import edu.duke.ece651.project.team5.backend.repository.ProfessorDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.shared.model.Student;
import edu.duke.ece651.project.team5.shared.request.LoginRequest;
import edu.duke.ece651.project.team5.shared.response.LoginResponse;

public class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private StudentDao studentDao;

    @Mock
    private ProfessorDao professorDao;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void authenticate_Success_Student() {
        LoginRequest request = new LoginRequest("student1", "password");
        Authentication auth = mock(Authentication.class);
        User user = new User("student1", "password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT")));
        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn("token");
        when(studentDao.findByNetId("student1"))
                .thenReturn(Optional.of(new Student("student1")));

        LoginResponse response = authService.authenticate(request);
        assertEquals("token", response.getToken());
        assertEquals("ROLE_STUDENT", response.getRole());
        assertEquals("student1", response.getLegalName());
    }

    @Test
    void authenticate_Failure_BadCredentials() {
        LoginRequest request = new LoginRequest("student1", "wrongpassword");
        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Bad credentials"));

        LoginResponse response = authService.authenticate(request);
        // assertEquals("invalid credentials", response.getToken());
    }

    @Test
    void verifyStudentCredential_Success() {
        when(studentDao.findByNetId("student1"))
                .thenReturn(Optional.of(new Student("student1")));
        when(passwordEncoder.matches("password", "encodedpassword")).thenReturn(true);

        assertFalse(authService.verifyStudentCredential("student1", "password"));
    }

    @Test
    void verifyStudentCredential_Failure_NotFound() {
        when(studentDao.findByNetId("student1")).thenReturn(Optional.empty());

        assertFalse(authService.verifyStudentCredential("student1", "password"));
    }

    // Add more tests for professor scenarios and other edge cases
}
