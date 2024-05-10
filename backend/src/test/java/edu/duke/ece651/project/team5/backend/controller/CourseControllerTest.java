package edu.duke.ece651.project.team5.backend.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import edu.duke.ece651.project.team5.backend.config.JwtService;
import edu.duke.ece651.project.team5.backend.config.TestConfig;
import edu.duke.ece651.project.team5.backend.repository.ProfessorDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.backend.service.AuthService;
import edu.duke.ece651.project.team5.backend.service.CourseService;
import edu.duke.ece651.project.team5.backend.service.MyDatabaseUserDetailsService;
import edu.duke.ece651.project.team5.backend.utils.ReportGenerator;
import edu.duke.ece651.project.team5.shared.model.SectionReport;
import edu.duke.ece651.project.team5.shared.model.Student;

@WebMvcTest(CourseController.class)
@Import(TestConfig.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private ReportGenerator reportGenerator;

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
    @WithMockUser
    public void testGetSectionIds() throws Exception {
        List<String> sectionIds = Arrays.asList("sec1", "sec2");
        when(courseService.getSectionIds()).thenReturn(sectionIds);

        mockMvc.perform(get("/section/all/id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is("sec1")))
                .andExpect(jsonPath("$[1]", is("sec2")));
    }

    @Test
    @WithMockUser
    public void testGetProfNameAndSectionIds() throws Exception {
        Map<String, List<String>> profNameAndSectionIds = new HashMap<>();
        profNameAndSectionIds.put("Dr. Smith", Arrays.asList("sec1", "sec2"));
        when(courseService.getProfNameAndSectionIds()).thenReturn(profNameAndSectionIds);

        mockMvc.perform(get("/section/all/profNameAndid")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.['Dr. Smith']", hasSize(2)));
    }

    @Test
    @WithMockUser
    public void testGetEnrolledStudent() throws Exception {
        List<Student> students = Arrays.asList(new Student("John Doe"), new Student("Jane Smith"));
        when(courseService.getEnrolledStudentsFromSection("sec1")).thenReturn(students);

        mockMvc.perform(get("/section/student/sec1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
        // .andExpect(jsonPath("$[0].name", is("John Doe")))
        // .andExpect(jsonPath("$[1].name", is("Jane Smith")));
    }

    @Test
    @WithMockUser
    public void testGetSectionReport() throws Exception {
        Map<String, String> attendance = new HashMap<>();
        attendance.put("netid1", "score1");
        attendance.put("netid2", "score2");
        SectionReport report = new SectionReport(attendance);
        when(reportGenerator.generateSectionReport("sec1")).thenReturn(report);

        mockMvc.perform(get("/section/report")
                .param("sectionId", "sec1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser
    public void testGetEnrolledStudentByName() throws Exception {
        // Arrange
        String courseSectionId = "cs101";
        String legalName = "John Doe";
        List<Student> mockStudents = List.of(new Student("John Doe"), new Student("Jane Doe"));
        when(courseService.getEnrolledStudentsFromSection(courseSectionId, legalName)).thenReturn(mockStudents);

        // Act & Assert
        mockMvc.perform(get("/section/student/{courseSectionId}/{legalName}", courseSectionId, legalName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }
    
    @Test
    @WithMockUser
    public void testGetStuNameAndSectionIds() throws Exception {
        // Arrange
        Map<String, List<String>> mockStuNameAndSecIds = Map.of(
                "John Doe", List.of("cs101", "cs102"),
                "Jane Doe", List.of("cs103"));

        when(courseService.getStuNameAndSectionIds()).thenReturn(mockStuNameAndSecIds);

        // Act & Assert
        mockMvc.perform(get("/section/all/StuNameAndSecid"))
                .andExpect(status().isOk());
             
    }
}
