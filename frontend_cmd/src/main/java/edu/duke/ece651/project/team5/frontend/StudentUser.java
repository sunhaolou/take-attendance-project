package edu.duke.ece651.project.team5.frontend;

import java.util.List;
import org.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.duke.ece651.project.team5.frontend.model.StudentReport;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Represents a student user in the system.
 */
public class StudentUser extends TextUser {

  /**
   * Constructs a new StudentUser with the specified username, password, input reader, socket, socket input reader, and socket output writer.
   *
   * @param username  The username of the student user.
   * @param password  The password of the student user.
   * @param input     The BufferedReader to read user input.
   * @param socket    The Socket object for communication with the server.
   * @param socketIn  The BufferedReader to read data from the socket.
   * @param socketOut The PrintWriter to write data to the socket.
   */
  public StudentUser(String username, String password, BufferedReader input, Socket socket, BufferedReader socketIn,
      PrintWriter socketOut) {
    super(username, password, input, socket, socketIn, socketOut);
  }

  /**
   * Performs an action based on the user's choice.
   *
   * @throws IOException If an I/O error occurs.
   */
  @Override
  public void takeAction() throws IOException {
    String prompt = "What would you like to do?";
    List<String> options = new ArrayList<>();
    options.add("Get report");
    options.add("Change report preference");
    options.add("exit the program");
    Display.displayToUser(prompt, options);
    int index = readInputWithErrorHandle(options.size());
    if (index == 0) {
      exportAttendance();
    } else if (index == 1) {
      changeNotificationPreference();
    } else {
      exit();
    }
  }

  /**
   * Exports the attendance report for the student user.
   *
   * @throws JsonProcessingException If an error occurs during JSON processing.
   * @throws IOException            If an I/O error occurs.
   */
  @Override
  protected void exportAttendance() throws JsonProcessingException, IOException {
    String prompt = "What format do you want to export?";
    List<String> options = new ArrayList<>();
    options.add("PDF");
    options.add("JSON");
    Display.displayToUser(prompt, options);
    int index = readInputWithErrorHandle(options.size());
    String flag = "student_report";
    JSONObject json = createDefaultJson(flag);
    socketOut.println(json.toString());
    String response = socketIn.readLine();
    StudentReport students = objectMapper.readValue(response, new TypeReference<StudentReport>() {
    });
    try {
      if (index == 0) {
        outGenerator = new PdfOutputGenerator();
      } else if (index == 1) {
        outGenerator = new JsonOutputGenerator();
      }
      outGenerator.exportAttendanceForStudents(students);
      Display.simpleDisplay("Successfully exported.");
    } catch (Exception e) {
      Display.simpleDisplay("Error while getting report from server.");
    }
  }

  /**
   * Allows the student to change their notification preference for a specific course section.
   *
   * @throws IOException If an I/O error occurs.
   */
  private void changeNotificationPreference() throws IOException {

    String sectionPrompt = "Which section do you want to change notification preference for?";
    Display.simpleDisplay(sectionPrompt);
    String courseSectionId = input.readLine();

    String status = "false";
    String statusPrompt = "Do you want to receive notification when your report is updated? (yes/no)";
    Display.simpleDisplay(statusPrompt);
    while (true) {
      try {
        String response = input.readLine();
        if (response.equals("yes")) {
          status = "true";
        } else if (response.equals("no")) {
          status = "false";
        } else {
          throw new IllegalArgumentException();
        }
        break;
      } catch (Exception e) {
        Display.simpleDisplay("Invalid input, please enter yes or no");
      }
    }

    String flag = "student_update_notification";
    JSONObject json = createDefaultJson(flag);
    json.put("courseSectionId", course_section_id);
    json.put("notificationMethod", status);
    json.put("courseSectionId", courseSectionId);

    socketOut.println(json.toString());
    String response = socketIn.readLine();
    Display.simpleDisplay(response);
  }
}
