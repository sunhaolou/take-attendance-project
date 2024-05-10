package edu.duke.ece651.project.team5.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import edu.duke.ece651.project.team5.backend.config.JwtService;
import edu.duke.ece651.project.team5.backend.config.TestConfig;
import edu.duke.ece651.project.team5.backend.repository.ProfessorDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.backend.service.AuthService;
import edu.duke.ece651.project.team5.backend.service.CourseService;
import edu.duke.ece651.project.team5.backend.service.MyDatabaseUserDetailsService;
import edu.duke.ece651.project.team5.backend.service.SessionService;
import edu.duke.ece651.project.team5.backend.service.StudentService;
import edu.duke.ece651.project.team5.backend.utils.ReportGenerator;
import edu.duke.ece651.project.team5.shared.request.AttendanceFromStudentRequest;
import edu.duke.ece651.project.team5.shared.request.TakeAndUpdateAttendanceRequest;
import edu.duke.ece651.project.team5.shared.response.QRCodeResponse;
import java.sql.Timestamp;

@WebMvcTest(SessionController.class)
@Import(TestConfig.class)
public class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @InjectMocks
    private SessionController sessionController;

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

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void getSessionIds() throws Exception {
        List<String> sessionIds = Arrays.asList("session1", "session2");
        when(sessionService.getAllSessionIdsOfSection("cs101")).thenReturn(sessionIds);

        mockMvc.perform(get("/session/cs101/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("session1"))
                .andExpect(jsonPath("$[1]").value("session2"));
        verify(sessionService).getAllSessionIdsOfSection("cs101");
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void takeAttendance() throws Exception {
        TakeAndUpdateAttendanceRequest request = new TakeAndUpdateAttendanceRequest("session1", "student123",
                "PRESENT");

        mockMvc.perform(post("/session/takeAttendance")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sessionId\":\"session1\",\"netId\":\"student123\",\"status\":\"PRESENT\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Attendance taken successfully"));

        verify(sessionService).takeAttendance("session1", "student123", "PRESENT");
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void updateAttendance() throws Exception {
        TakeAndUpdateAttendanceRequest request = new TakeAndUpdateAttendanceRequest("session1", "student123", "ABSENT");

        mockMvc.perform(post("/session/updateAttendance")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sessionId\":\"session1\",\"netId\":\"student123\",\"status\":\"ABSENT\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Attendance taken successfully"));

        verify(sessionService).updateAttendance("session1", "student123", "ABSENT");
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void takeAttendanceFromStudent_QRCodeExpired() throws Exception {
        AttendanceFromStudentRequest request = new AttendanceFromStudentRequest("session1", "netId123", "password",
                "123.45", "67.89", 1L);
        when(sessionService.checkQRCodeExpiration("session1", 1L)).thenReturn(true);

        mockMvc.perform(post("/session/attendance/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"sessionId\":\"session1\",\"netId\":\"netId123\",\"password\":\"password\",\"longitude\":\"123.45\",\"latitude\":\"67.89\",\"uuid\":1}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("QR Code expired"));

        verify(sessionService, never()).takeAttendance(any(), any(), any());
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void getQRCode_Success() throws Exception {
        QRCodeResponse qrCodeResponse = new QRCodeResponse("encodedString", 1L, new Timestamp(1L));
        when(sessionService.getQRCode("session1", "67.89", "123.45")).thenReturn(qrCodeResponse);

        mockMvc.perform(get("/session/qrcode")
                .param("sessionId", "session1")
                .param("latitude", "67.89")
                .param("longitude", "123.45"))
                .andExpect(status().isOk());

        verify(sessionService).getQRCode("session1", "67.89", "123.45");
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void takeAttendanceFromStudent_StudentTooFar() throws Exception {
        AttendanceFromStudentRequest request = new AttendanceFromStudentRequest("session1", "netId123", "password",
                "123.45", "67.89", 1L);
        when(sessionService.checkQRCodeExpiration("session1", 1L)).thenReturn(false);
        when(sessionService.compareLocation(1L, "67.89", "123.45")).thenReturn(false);

        mockMvc.perform(post("/session/attendance/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"sessionId\":\"session1\",\"netId\":\"netId123\",\"password\":\"password\",\"longitude\":\"123.45\",\"latitude\":\"67.89\",\"uuid\":1}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Student too far away"));
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void takeAttendanceFromStudent_InvalidPassword() throws Exception {
        AttendanceFromStudentRequest request = new AttendanceFromStudentRequest("session1", "netId123", "password",
                "123.45", "67.89", 1L);
        when(sessionService.checkQRCodeExpiration("session1", 1L)).thenReturn(false);
        when(sessionService.compareLocation(1L, "67.89", "123.45")).thenReturn(true);
        when(authService.verifyStudentCredential("netId123", "password")).thenReturn(false);

        mockMvc.perform(post("/session/attendance/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"sessionId\":\"session1\",\"netId\":\"netId123\",\"password\":\"password\",\"longitude\":\"123.45\",\"latitude\":\"67.89\",\"uuid\":1}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid password"));
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    void takeAttendanceFromStudent_ErrorTakingAttendance() throws Exception {
        AttendanceFromStudentRequest request = new AttendanceFromStudentRequest("session1", "netId123", "password",
                "123.45", "67.89", 1L);
        when(sessionService.checkQRCodeExpiration("session1", 1L)).thenReturn(false);
        when(sessionService.compareLocation(1L, "67.89", "123.45")).thenReturn(true);
        when(authService.verifyStudentCredential("netId123", "password")).thenReturn(true);
        doThrow(new RuntimeException("Database error")).when(sessionService).takeAttendance("session1", "netId123",
                "PRESENT");

        mockMvc.perform(post("/session/attendance/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"sessionId\":\"session1\",\"netId\":\"netId123\",\"password\":\"password\",\"longitude\":\"123.45\",\"latitude\":\"67.89\",\"uuid\":1}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error taking attendance"));
    }

}
