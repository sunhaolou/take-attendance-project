package edu.duke.ece651.project.team5.frontend.utils;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.shared.model.StudentReport;
import edu.duke.ece651.project.team5.shared.model.SectionReport;

/**
 * The JsonOutputGenerator class provides methods to export attendance data to
 * JSON format.
 */

@Component
public class JsonOutputGenerator implements OutputGenerator {
      @Autowired
    private ObjectMapper objectMapper;

    /**
     * Exports attendance data in JSON format for a professor.
     *
     * @param response The attendance data received as a JSON string.
     * @throws JsonProcessingException If there is an error processing the JSON.
     */
    @Override
    public void exportAttendanceForSection(SectionReport sectionReport) throws JsonProcessingException {
        try {

            String json = objectMapper.writeValueAsString(sectionReport);
            FileWriter fileWriter = new FileWriter("section_attendance_report.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, json);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exports attendance data in JSON format for students.
     *
     * @param studentReport The attendance report for a student.
     * @throws JsonProcessingException If there is an error processing the JSON.
     * @throws IOException             If there is an error writing to the file.
     */
    @Override
    public void exportAttendanceForStudents(StudentReport studentReport) {
        try {
            String json = objectMapper.writeValueAsString(studentReport);
            FileWriter fileWriter = new FileWriter("student_attendance_report.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, json);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
