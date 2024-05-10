
package edu.duke.ece651.project.team5.backend.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import edu.duke.ece651.project.team5.backend.model.EmailEngine;
import edu.duke.ece651.project.team5.backend.repository.SectionDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.backend.service.CourseService;
import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;
import edu.duke.ece651.project.team5.shared.model.Section;
import edu.duke.ece651.project.team5.shared.model.SectionReport;
import edu.duke.ece651.project.team5.shared.model.Student;

public class ReportGeneratorTest {

    @InjectMocks
    private ReportGenerator reportGenerator;

    @Mock
    private CourseService courseService;

    @Mock
    private SectionDao sectionDao;
    @MockBean
    private EmailEngine emailEngine;
    @Mock
    private StudentDao studentDao;
    @Mock
    private Student student;
    EmailEngine mockEmailEngine = mock(EmailEngine.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(student.getEnrolledCourseSectionIds()).thenReturn(Arrays.asList("section1", "section2"));
        // Mock the behavior of sectionDao as necessary, depending on how
        // getEnrolledSections uses it
        // For example:
        when(student.getEmail()).thenReturn("student@example.com");
        when(student.getNetId()).thenReturn("net123");
        when(student.getLegalName()).thenReturn("l");
        Section section1 = new Section();
        section1.setCourseSectionId("section1");
        Optional<Section> optionalSection1 = Optional.of(section1);
        when(sectionDao.findByCourseSectionId("section1")).thenReturn(optionalSection1);

        Section section2 = new Section();
        section2.setCourseSectionId("section2");
        Optional<Section> optionalSection2 = Optional.of(section2);
        when(sectionDao.findByCourseSectionId("section2")).thenReturn(optionalSection2);
    }

    @Test
    public void testGenerateReport() {
        List<Student> students = Arrays.asList(
                new Student("student1", "Student One"),
                new Student("student2", "Student Two"));
        when(studentDao.findAll()).thenReturn(students);

        // Assuming generateOneReport doesn't throw an exception
        try {

            doNothing().when(reportGenerator).generateOneReport(any(Student.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        reportGenerator.init();
        reportGenerator.generateReport();

        verify(studentDao, times(1)).findAll();
        try {

            verify(reportGenerator, times(students.size())).generateOneReport(any(Student.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGenerateSectionReport() throws Exception {
        String sectionId = "sec101";
        Section section = new Section();
        section.setCourseSectionId(sectionId);
        when(sectionDao.findByCourseSectionId(sectionId)).thenReturn(Optional.of(section));

        List<Student> students = Arrays.asList(new Student("student1", "Student One"));
        when(courseService.getEnrolledStudentsFromSection(sectionId)).thenReturn(students);

        SectionReport report = reportGenerator.generateSectionReport(sectionId);
        assertNotNull(report);

        verify(sectionDao, times(1)).findByCourseSectionId(sectionId);
        verify(courseService, times(1)).getEnrolledStudentsFromSection(sectionId);
    }

    // @Test
    // void testGenerateOneReportForUpdate_Success() throws Exception {
    // // Setup the content generation
    // doNothing().when(mockEmailEngine).sendEmail(anyString(), anyString(),
    // anyString());
    // when(student.getLegalName()).thenReturn("Student One");

    // // Assume the student has required fields setup

    // // Perform the method test
    // Student student1 = new Student("student1", "Student One");
    // student1.setEmail("easdasd@dasdasd");
    // reportGenerator.generateOneReportForUpdate(student1);

    // // Verify email was sent
    // verify(emailEngine).sendEmail(eq("student@example.com"), eq("attendence
    // report"), anyString());
    // verifyNoMoreInteractions(emailEngine);
    // }
    @Test
    public void testGetEnrolledSections() {
        // Create a student with enrolled course section ids

        // Create mock sections
        Section section1 = new Section();
        section1.setCourseSectionId("section1");
        Section section2 = new Section();
        section2.setCourseSectionId("section2");

        Student student = new Student();
        Map<String, EnrollmentStatus> enrolledCourseSectionIds = new HashMap<>();
        enrolledCourseSectionIds.put("section1", EnrollmentStatus.ENROLLED);
        enrolledCourseSectionIds.put("section2", EnrollmentStatus.ENROLLED);
        student.setEnrolledCourses(enrolledCourseSectionIds);
        // Mock the behavior of sectionDao
        when(sectionDao.findByCourseSectionId("section1")).thenReturn(Optional.of(section1));
        when(sectionDao.findByCourseSectionId("section2")).thenReturn(Optional.of(section2));

        // Call the method under test
        List<Section> sections = reportGenerator.getEnrolledSections(student);

        // Verify the sections returned
        assertEquals(2, sections.size());
        assertTrue(sections.contains(section1));
        assertTrue(sections.contains(section2));

        // Verify the calls to sectionDao
        verify(sectionDao, times(1)).findByCourseSectionId("section1");
        verify(sectionDao, times(1)).findByCourseSectionId("section2");
        verifyNoMoreInteractions(sectionDao);
    }
}