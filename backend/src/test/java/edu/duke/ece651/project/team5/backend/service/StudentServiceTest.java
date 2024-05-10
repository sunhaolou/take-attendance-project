package edu.duke.ece651.project.team5.backend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import edu.duke.ece651.project.team5.backend.repository.SectionDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.backend.utils.ReportGenerator;
import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;
import edu.duke.ece651.project.team5.shared.enums.NotificationMethod;
import edu.duke.ece651.project.team5.shared.model.*;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentDao studentDao;

    @Mock
    private SectionDao sectionDao;

    @Mock
    private ReportGenerator reportGenerator;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(mock(User.class));
    }

    @Test
    public void testGetEnrolledSections() {
        // Set up
        Student student = new Student();
        student.setEnrolledCourses(Map.of("CS101", EnrollmentStatus.ENROLLED));
        Section expectedSection = new Section();
        when(sectionDao.findByCourseSectionId("CS101")).thenReturn(Optional.of(expectedSection));

        // Execute
        List<Section> sections = studentService.getEnrolledSections(student);

        // Verify
        assertEquals(1, sections.size());
        assertTrue(sections.contains(expectedSection));
    }

    @Test
    public void testGetStudentByNetId_StudentExists() {
        // Set up
        Student expectedStudent = new Student();
        expectedStudent.setNetId("s123");
        when(studentDao.findByNetId("s123")).thenReturn(Optional.of(expectedStudent));

        // Execute
        Student result = studentService.getStudentByNetId("s123");

        // Verify
        assertEquals("s123", result.getNetId());
    }

    @Test
    public void testGetStudentByNetId_StudentDoesNotExist() {
        // Set up
        when(studentDao.findByNetId("s123")).thenReturn(Optional.empty());

        // Assert
        assertThrows(IllegalArgumentException.class, () -> studentService.getStudentByNetId("s123"));
    }

    @Test
    public void testSearchStudentsByLegalName() {
        // Set up
        Section section = new Section();
        section.setEnrollment(Map.of("s123", EnrollmentStatus.ENROLLED, "s456", EnrollmentStatus.ENROLLED));
        when(sectionDao.findByCourseSectionId("CS101")).thenReturn(Optional.of(section));

        Student student1 = new Student("s123", "John Doe");
        Student student2 = new Student("s456", "Jane Smith");
        when(studentDao.findByNetId("s123")).thenReturn(Optional.of(student1));
        when(studentDao.findByNetId("s456")).thenReturn(Optional.of(student2));

        // Execute
        List<Student> result = studentService.searchStudentsByLegalName("Doe", "CS101");

        // Verify
        assertEquals(1, result.size());
        assertTrue(result.contains(student1));
        assertFalse(result.contains(student2));
    }
@Test
    public void testUpdateStudentNotificationPreference_ValidMethodEmail() {
        Student student = new Student();
        String sectionId = "CS101";
        Map<String, NotificationPreference> prefs = new HashMap<>();
        NotificationPreference np = new NotificationPreference();
        prefs.put(sectionId, np);
        student.setNotificationPreferences(prefs);

        studentService.updateStudentNotificationPreference(student, sectionId, "true");

        assertEquals(NotificationMethod.EMAIL, np.getNotificationMethod());
        verify(studentDao).saveAndFlush(student);
    }

    @Test
    public void testGetNotificationPreferenceBySection() {
        String sectionId = "CS101";
        Student student = new Student();
        Map<String, NotificationPreference> prefs = new HashMap<>();
        NotificationPreference np = new NotificationPreference();
        prefs.put(sectionId, np);
        student.setNotificationPreferences(prefs);

        when(studentDao.findByNetId(any())).thenReturn(Optional.of(student));

        String result = studentService.geteNotiPrefBySec(sectionId);

        assertEquals("EMAIL", result);
    }

    @Test
    public void testUpdateNotificationPreferenceBySection_ValidMethod() {
        String sectionId = "CS101";
        String newMethod = "EMAIL";
        Student student = new Student();
        Map<String, NotificationPreference> prefs = new HashMap<>();
        NotificationPreference np = new NotificationPreference();
        prefs.put(sectionId, np);
        student.setNotificationPreferences(prefs);

        when(studentDao.findByNetId(any())).thenReturn(Optional.of(student));

        String result = studentService.updateNotiPrefBySec(sectionId, newMethod);

        assertEquals("Notification Preference Updated", result);
        assertEquals(NotificationMethod.EMAIL, np.getNotificationMethod());
        verify(studentDao).saveAndFlush(student);
    }

    @Test
    public void testGenerateStudentReport() {
        Student student = new Student();
        StudentReport expectedReport = new StudentReport();
        when(studentDao.findByNetId(any())).thenReturn(Optional.of(student));
        when(reportGenerator.generateStudentReport(student)).thenReturn(expectedReport);

        StudentReport result = studentService.generateStudentReport();

        assertSame(expectedReport, result);
    }
   
}
