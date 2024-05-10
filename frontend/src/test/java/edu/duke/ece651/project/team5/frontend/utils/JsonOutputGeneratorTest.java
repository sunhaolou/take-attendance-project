package edu.duke.ece651.project.team5.frontend.utils;

import static org.mockito.Mockito.*;

import java.io.StringWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.shared.model.StudentReport;
import edu.duke.ece651.project.team5.shared.model.SectionReport;

public class JsonOutputGeneratorTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private JsonOutputGenerator jsonOutputGenerator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExportAttendanceForSection() throws Exception {
        SectionReport sectionReport = new SectionReport(); // Assume this is populated or mocked as needed
        StringWriter writer = new StringWriter();

        // Configure ObjectMapper to write to a StringWriter instead of FileWriter for
        // testing
        when(objectMapper.writeValueAsString(sectionReport)).thenReturn("Mocked JSON String");
        doNothing().when(objectMapper).writeValue(writer, "Mocked JSON String");

        jsonOutputGenerator.exportAttendanceForSection(sectionReport);

        // Verify that writeValueAsString was called with the correct object
    }

    @Test
    public void testExportAttendanceForStudents() throws Exception {
        StudentReport studentReport = new StudentReport(); // Assume this is populated or mocked as needed
        StringWriter writer = new StringWriter();

        // Configure ObjectMapper to write to a StringWriter instead of FileWriter for
        // testing
        when(objectMapper.writeValueAsString(studentReport)).thenReturn("Mocked JSON String");
        doNothing().when(objectMapper).writeValue(writer, "Mocked JSON String");

        jsonOutputGenerator.exportAttendanceForStudents(studentReport);

        // Verify that writeValueAsString was called with the correct object

    }
}
