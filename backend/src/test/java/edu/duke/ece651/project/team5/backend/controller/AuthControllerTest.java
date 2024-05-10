// package edu.duke.ece651.project.team5.backend.controller;
package edu.duke.ece651.project.team5.backend.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.backend.config.JwtService;
import edu.duke.ece651.project.team5.backend.config.TestConfig;
import edu.duke.ece651.project.team5.backend.repository.ProfessorDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.backend.service.AuthService;
import edu.duke.ece651.project.team5.backend.service.MyDatabaseUserDetailsService;
import edu.duke.ece651.project.team5.shared.request.LoginRequest;
import edu.duke.ece651.project.team5.shared.response.LoginResponse;

@WebMvcTest(AuthController.class)
@Import(TestConfig.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private StudentDao studentDao;

    @MockBean
    private ProfessorDao professorDao;

    @MockBean
    private MyDatabaseUserDetailsService userDetailsService;

    @InjectMocks
    private AuthController authController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testHandleLoginSuccess() throws Exception {
        LoginRequest loginRequest = new LoginRequest("user", "password");
        LoginResponse loginResponse = new LoginResponse("token", "ROLE_USER", "John Doe");
        String actualJson = new ObjectMapper().writeValueAsString(loginResponse);
        given(authService.authenticate(loginRequest)).willReturn(loginResponse);

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk());
        // .andExpect(content().json(actualJson));

        // verify(authService).authenticate(loginRequest);
    }

}