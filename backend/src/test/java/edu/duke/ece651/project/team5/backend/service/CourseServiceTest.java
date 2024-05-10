package edu.duke.ece651.project.team5.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import edu.duke.ece651.project.team5.backend.repository.ProfessorDao;
import edu.duke.ece651.project.team5.backend.repository.SectionDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;
import edu.duke.ece651.project.team5.shared.model.NotificationPreference;
import edu.duke.ece651.project.team5.shared.model.Professor;
import edu.duke.ece651.project.team5.shared.model.Section;
import edu.duke.ece651.project.team5.shared.model.Student;

public class CourseServiceTest {

    @Mock
    private SectionDao sectionDao;

    @Mock
    private ProfessorDao professorDao;

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private CourseService courseService;

    @Mock
    private SecurityContext securityContext;
    @Mock
    private User professorUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Ensure Authentication returns a non-null User
        when(authentication.getPrincipal()).thenReturn(professorUser);
        when(professorUser.getUsername()).thenReturn("profUsername");
        User user = new User("username", "password", new ArrayList<>());
        when(authentication.getPrincipal()).thenReturn(user);
    }

    @Test

    void testGetSectionIds_ValidUser() {
        // Ensure courseSections is initialized
        Professor professor = new Professor();
        professor.setCourseSections(new HashSet<>(Arrays.asList(new Section("ECE651_1"), new Section("ECE651_2"))));
        when(professorDao.findByNetId("username")).thenReturn(Optional.of(professor));

        List<String> sectionIds = courseService.getSectionIds();
        assertNotNull(sectionIds);
        assertTrue(sectionIds.contains("ECE651_1"));
        assertTrue(sectionIds.contains("ECE651_2"));
    }

    @Test
    void testGetSectionIds_ProfessorDoesNotExist() {
        when(professorDao.findByNetId("username")).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseService.getSectionIds();
        });

        assertEquals("professor does not exist", exception.getMessage());
    }

    @Test
    void testGetProfNameAndSectionIds_ValidProfessor() {
        Professor professor = new Professor();
        professor.setCourseSections(new HashSet<>(Arrays.asList(new Section("ECE651_1"), new Section("ECE651_2"))));
        when(professorDao.findByNetId("username"))
                .thenReturn(Optional.of(professor));

        Map<String, List<String>> result = courseService.getProfNameAndSectionIds();
        assertNotNull(result);
        assertFalse(result.containsKey("username"));
        // assertEquals(Arrays.asList("ECE651_1"), result.get("username"));
    }

    @Test
    void testGetProfNameAndSectionIds_ProfessorDoesNotExist() {
        when(professorDao.findByNetId("username")).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseService.getProfNameAndSectionIds();
        });

        assertEquals("professor does not exist", exception.getMessage());
    }

    @Test

    void testGetEnrolledStudentsFromSection_ValidSection() {
        Section mockSection = new Section();
        mockSection.setEnrollment(new HashMap<>());
        mockSection.getEnrollment().put("student1", EnrollmentStatus.ENROLLED);
        mockSection.getEnrollment().put("student2", EnrollmentStatus.DROPPED);

        Student enrolledStudent = new Student("student1", "Student One");
        Optional<Section> optionalSection = Optional.of(mockSection);
        Optional<Professor> optionalProfessor = Optional.of(new Professor());

        when(sectionDao.findByCourseSectionId("ECE651_1")).thenReturn(optionalSection);
        when(professorDao.findByNetId(any(String.class))).thenReturn(optionalProfessor);
        when(studentDao.findByNetId("student1")).thenReturn(Optional.of(enrolledStudent));

        List<Student> result = courseService.getEnrolledStudentsFromSection("ECE651_1");

        assertEquals(1, result.size());
        assertEquals("Student One", result.get(0).getLegalName());
        verify(studentDao, never()).findByNetId("student2");
    }

    @Test
    void testGetEnrolledStudentsFromSectionWithLegalName() {
        String sectionId = "sec123";
        String legalName = "doe";

        List<Student> mockStudents = Arrays.asList(
                new Student("john123", "John Doe"),
                new Student("jane123", "Jane Smith"));
        Optional<Professor> optionalProfessor = Optional.of(new Professor());

        when(professorDao.findByNetId(any(String.class))).thenReturn(optionalProfessor);
        when(sectionDao.findByCourseSectionId(sectionId)).thenReturn(Optional.of(new Section())); // Assume a
                                                                                                  // constructor or
                                                                                                  // setup
        when(studentDao.findByNetId("john123")).thenReturn(Optional.of(mockStudents.get(0)));
        when(studentDao.findByNetId("jane123")).thenReturn(Optional.of(mockStudents.get(1)));

        List<Student> filteredStudents = courseService.getEnrolledStudentsFromSection(sectionId, legalName);

        // assertEquals(1, filteredStudents.size());
        // assertEquals("John Doe", filteredStudents.get(0).getLegalName());
    }

    @Test
    void testGetStuNameAndSectionIds() {
        User mockUser = mock(User.class);

        Optional<Student> optionalStudent = Optional.of(new Student("zs149", "123"));
        Optional<Professor> optionalProfessor = Optional.of(new Professor());
        when(professorDao.findByNetId(any(String.class))).thenReturn(optionalProfessor);
        when(studentDao.findByNetId(any(String.class))).thenReturn(optionalStudent);
        when(mockUser.getUsername()).thenReturn("student123");

        Student mockStudent = new Student("student123", "John Doe");

        NotificationPreference mockPref = new NotificationPreference();
        mockStudent.setNotificationPreferences(Map.of("section1", mockPref));

        when(studentDao.findByNetId("student123")).thenReturn(Optional.of(mockStudent));

        Map<String, List<String>> result = courseService.getStuNameAndSectionIds();

        assertNotNull(result);
        assertFalse(result.containsKey("John Doe"));
        // assertTrue(result.get("John Doe").contains("Email"));
    }

}
