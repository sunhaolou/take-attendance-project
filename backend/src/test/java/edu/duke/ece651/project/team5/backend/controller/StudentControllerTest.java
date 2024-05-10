package edu.duke.ece651.project.team5.backend.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.backend.config.JwtService;
import edu.duke.ece651.project.team5.backend.config.TestConfig;
import edu.duke.ece651.project.team5.backend.repository.ProfessorDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.backend.service.AuthService;
import edu.duke.ece651.project.team5.backend.service.CourseService;
import edu.duke.ece651.project.team5.backend.service.MyDatabaseUserDetailsService;
import edu.duke.ece651.project.team5.backend.service.StudentService;
import edu.duke.ece651.project.team5.backend.utils.ReportGenerator;
import edu.duke.ece651.project.team5.shared.model.StudentReport;
import edu.duke.ece651.project.team5.shared.request.UpdateStudentNotificationRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
@Import(TestConfig.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private ReportGenerator reportGenerator;

    @MockBean
    private AuthService authService;

    @MockBean
    private StudentService studentService;

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
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity()) // Apply Spring Security configuration
                .build();
    }

    // Add your tests here
    @Test
    @WithMockUser(roles = "STUDENT")
    public void testGetAndChangeNoti() throws Exception {
        String sectionId = "cs101";
        String expectedNotiPref = "Email";
        given(studentService.geteNotiPrefBySec(sectionId)).willReturn(expectedNotiPref);

        mockMvc.perform(get("/student/{sectionid}/getAndChangeNoti", sectionId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedNotiPref));
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void testUpdateNotiPref() throws Exception {
        String sectionId = "cs101";
        String notiPref = "SMS";
        UpdateStudentNotificationRequest request = new UpdateStudentNotificationRequest(sectionId, notiPref);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/student/{sectionid}/updateNotiPref", "section1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Notification Preference Updated"));
        verify(studentService).updateNotiPrefBySec(sectionId, notiPref);
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void testGetStudentReport() throws Exception {
        StudentReport report = new StudentReport();
        given(studentService.generateStudentReport()).willReturn(report);

        mockMvc.perform(get("/student/report"))
                .andExpect(status().isOk());

    }

}
