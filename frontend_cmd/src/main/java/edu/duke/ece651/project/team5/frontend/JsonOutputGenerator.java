package edu.duke.ece651.project.team5.frontend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.frontend.model.StudentReport;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The JsonOutputGenerator class provides methods to export attendance data to JSON format.
 */
public class JsonOutputGenerator implements OutputGenerator {

  /**
   * Exports attendance data in JSON format for a professor.
   *
   * @param response The attendance data received as a JSON string.
   * @throws JsonProcessingException If there is an error processing the JSON.
   */
  @Override
  public void exportAttendanceForProfessor(String response) throws JsonProcessingException {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      Object json = objectMapper.readValue(response, Object.class);
      FileWriter fileWriter = new FileWriter("attendance_report.json");
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
   * @throws IOException If there is an error writing to the file.
   */
  @Override
  public void exportAttendanceForStudents(StudentReport studentReport) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String json = objectMapper.writeValueAsString(studentReport);
      FileWriter fileWriter = new FileWriter("attendance_report.json");
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, json);
      fileWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
