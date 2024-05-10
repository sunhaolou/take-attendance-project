// package edu.duke.ece651.project.team5.coursemanagement;
// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
// import edu.duke.ece651.project.team5.shared.dao.*;
// import edu.duke.ece651.project.team5.shared.model.*;

// import static org.junit.jupiter.api.Assertions.*;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// // import org.junit.Before;
// // import org.junit.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// public class CourseManagementTest {
//     private CourseManagement courseManagement;
//     private BufferedReader mockInput;
//     private CsvInputProcessor mockInputProcessor;
//     private CourseDao mockCourseDao;
//     private StudentDao mockStudentDao;
//     private SectionDao mockSectionDao;
//     private ProfessorDao mockProfessorDao;

//     @BeforeEach
//     public void setUp() throws Exception {
//         mockInput = mock(BufferedReader.class);
//         mockCourseDao = mock(CourseDao.class);
//         mockStudentDao = mock(StudentDao.class);
//         mockSectionDao = mock(SectionDao.class);
//         mockProfessorDao = mock(ProfessorDao.class);
//         mockInputProcessor = mock(CsvInputProcessor.class);
//         DatabaseInitializer initializer = new DatabaseInitializer();
//         initializer.initialize();
//         initializer.addTestData();
//         courseManagement = new CourseManagement(mockInput);
//         setPrivateField(courseManagement, "courseDao", mockCourseDao);
//         setPrivateField(courseManagement, "studentDao", mockStudentDao);
//         setPrivateField(courseManagement, "sectionDao", mockSectionDao);
//         setPrivateField(courseManagement, "professorDao", mockProfessorDao);
//         setPrivateField(courseManagement, "inputProcessor", mockInputProcessor);
//     }

//     private void setPrivateField(Object object, String fieldName, Object value) throws Exception {
//         java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);
//         field.setAccessible(true);
//         field.set(object, value);
//     }

//     @Test
//     public void testChooseProfessor() throws IOException, SQLException {
//         when(mockProfessorDao.getAll()).thenReturn(Arrays.asList(new Professor("1", "John Doe"), new Professor("2", "Jane Doe")));
//         when(mockInput.readLine()).thenReturn("0");  // Simulate user selecting the first professor

//         courseManagement.chooseProfessor();

//         verify(mockInput, times(1)).readLine();  // Ensure input was read once
//         verify(mockProfessorDao, times(1)).getAll();  // Ensure getAll was called to fetch professors
//     }

    // @Test
    // public void testCreateNewCourseManually() throws IOException, SQLException {
    //     when(mockInput.readLine()).thenReturn("ECE101", "Introduction to Electrical Engineering", "Introduction", "0", "0");

    //     courseManagement.createNewCourseManually();

    //     verify(mockCourseDao, times(1)).add(any(Course.class));
    // }

    // @Test
    // public void testUpdateEnrollmentStatus() throws IOException, SQLException {
    //     Section mockSection = new Section("ECE101_01", "ECE101", "01", new ArrayList<>(), new ArrayList<>());
    //     when(mockSectionDao.get(anyString())).thenReturn(java.util.Optional.of(mockSection));
    //     when(mockInput.readLine()).thenReturn("0", "Yes");  // Simulate user choosing to enroll a student and confirming

    //     courseManagement.updateEnrollmentStatus();

    //     verify(mockSectionDao, times(1)).update(any(Section.class));  // Verify that the section was updated in the database
    // }

    // // Additional test methods for chooseCourse, doActions, createCourseByFile, etc.

// }
