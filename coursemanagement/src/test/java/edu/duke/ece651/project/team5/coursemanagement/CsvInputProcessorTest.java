package edu.duke.ece651.project.team5.coursemanagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import edu.duke.ece651.project.team5.shared.model.*;
import edu.duke.ece651.project.team5.shared.dao.*;

public class CsvInputProcessorTest {
    private CsvInputProcessor processor;
    private ProfessorDao mockProfessorDao;

    @BeforeEach
    public void setUp() {
        mockProfessorDao = Mockito.mock(ProfessorDao.class);
        processor = new CsvInputProcessor(mockProfessorDao);
    }
    @Test
    public void testCreateEnrollmentValidInput() throws SQLException {
        String validLine = "CS101,Computer Science,001,prof123,jdoe,ENROLLED";
        Mockito.when(mockProfessorDao.get("prof123")).thenReturn(Optional.of(new Professor("prof123", "John Doe")));

        processor.createEnrollment(validLine);
        // Assert expected behavior such as verifying if student was enrolled
    }

    @Test
    public void testCreateEnrollmentInvalidCourseId() throws SQLException {
        String invalidLine = "101CS,Computer Science,001,prof123,jdoe,ENROLLED";
        assertThrows(Throwable.class, () -> processor.createEnrollment(invalidLine));
    }

    @Test
    public void testCreateEnrollmentUnknownStatus() throws SQLException {
        String invalidStatusLine = "CS101,Computer Science,001,prof123,jdoe,ATTENDING";
        assertThrows(Throwable.class, () -> processor.createEnrollment(invalidStatusLine));
    }

    @Test
    public void testCreateEnrollmentInvalidFormat() throws SQLException {
        String invalidLine = "This is not a valid format";
        assertThrows(Throwable.class, () -> processor.createEnrollment(invalidLine));
    }
    // @Test
    // public void testCreateSessionValidInput() throws SQLException, ParseException {
    //     String validLine = "CS101,001,002,2021-12-01 09:00:00";
    //     processor.createSession(validLine);
    //     // Assertions to verify correct creation and storage of session objects
    // }

    @Test
    public void testCreateSessionInvalidTimeFormat() throws SQLException, ParseException {
        String invalidLine = "CS101,001,12/01/2021 09:00";
        assertThrows(Throwable.class, () -> processor.createSession(invalidLine));
    }

    @Test
    public void testCreateSessionInvalidInput() throws SQLException, ParseException {
        String invalidLine = "CS101,Section,Some Random Text";
        assertThrows(Throwable.class, () -> processor.createSession(invalidLine));
    }
    @Test
    public void testParseRosterCsvForSection() throws IOException {
        String fileName = "courseEnrollment.csv"; // This should be a test file located in your test resources
        assertDoesNotThrow(()->processor.parseRosterCsvForSection(fileName));
        // Verify the outcomes based on file contents, such as checking if all students were enrolled
    }



}
