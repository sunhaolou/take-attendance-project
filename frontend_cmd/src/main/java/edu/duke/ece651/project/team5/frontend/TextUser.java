package edu.duke.ece651.project.team5.frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.frontend.model.Student;

/**
 * Represents a text-based user in the system.
 */
public class TextUser {
  protected OutputGenerator outGenerator;
  protected final BufferedReader input;
  protected final BufferedReader socketIn;
  protected final PrintWriter socketOut;
  protected final Socket socket;
  protected String userNetID;
  protected String password;
  protected String course_section_id;
  protected ObjectMapper objectMapper;

  /**
   * Constructs a new TextUser with the specified userNetID, password, input reader, socket, socket input reader, and socket output writer.
   *
   * @param userNetID  The net ID of the user.
   * @param password   The password of the user.
   * @param input      The BufferedReader to read user input.
   * @param socket     The Socket object for communication with the server.
   * @param socketIn   The BufferedReader to read data from the socket.
   * @param socketOut  The PrintWriter to write data to the socket.
   */
  public TextUser(String userNetID, String password, BufferedReader input, Socket socket, BufferedReader socketIn,
      PrintWriter socketOut) {
    this.userNetID = userNetID;
    this.password = password;
    this.socket = socket;
    this.input = input;
    this.socketIn = socketIn;
    this.socketOut = socketOut;
    this.objectMapper = new ObjectMapper();
  }

  /**
   * Performs an action based on the user's choice.
   *
   * @throws IOException If an I/O error occurs.
   */
  public void takeAction() throws IOException {}

  /**
   * Allows the user to choose a course.
   *
   * @throws IOException If an I/O error occurs.
   */
  protected void chooseCourse() throws IOException {
    String flag = "section_all_id";
    JSONObject json = createDefaultJson(flag);
    socketOut.println(json.toString());
    String response = socketIn.readLine();

    if (response.isEmpty() || response.equals("[]")) {
      throw new IllegalArgumentException("There are no existing courses to choose from.");
    } else {
      if (response.length() > 1) {
        response = response.substring(1, response.length() - 1);
      } else {
        throw new IllegalArgumentException("Invalid course ID.");
      }

      List<String> courses = Arrays.stream(response.split(","))
          .map(s -> s.trim().replace("\"", ""))
          .collect(Collectors.toList());
      String prompt = "Choose one course from the following:";
      Collections.sort(courses);
      Display.displayToUser(prompt, courses);
      int index = readInputWithErrorHandle(courses.size());
      course_section_id = courses.get(index);
    }
  }

  /**
   * Exports the attendance report.
   *
   * @throws JsonProcessingException If an error occurs during JSON processing.
   * @throws IOException            If an I/O error occurs.
   */
  protected void exportAttendance() throws JsonProcessingException, IOException {}

  /**
   * Creates a default JSONObject with the specified flag.
   *
   * @param flag The flag for the request.
   * @return The created JSONObject.
   */
  protected JSONObject createDefaultJson(String flag) {
    JSONObject json = new JSONObject();
    json.put("userNetId", userNetID);
    json.put("password", password);
    json.put("flag", flag);
    return json;
  }

  /**
   * Reads input from the user with error handling.
   *
   * @param length The length of the input options.
   * @return The chosen option.
   * @throws IOException If an I/O error occurs.
   */
  protected int readInputWithErrorHandle(int length) throws IOException {
    while (true) {
      try {
        String s = input.readLine();
        return chooseOneOption(s, length);
      } catch (NumberFormatException e) {
        Display.simpleDisplay("The input should be a number between 1 and " + length + ". Please try again:");
      } catch (IllegalArgumentException e) {
        Display.simpleDisplay("The input should be between 1 and " + length + ". Please try again:");
      }
    }
  }

  /**
   * Chooses one option from the input.
   *
   * @param action The chosen action.
   * @param length The length of the options.
   * @return The chosen option.
   */
  protected int chooseOneOption(String action, int length) {
    int choice;
    choice = Integer.parseInt(action);
    if (choice > length || choice < 1) {
      throw new IllegalArgumentException();
    }
    return choice - 1;
  }

  /**
   * Converts the response string to a list of strings.
   *
   * @param response The response string.
   * @return The list of strings.
   * @throws JsonProcessingException If an error occurs during JSON processing.
   */
  protected List<String> getStringListFromResponse(String response) throws JsonProcessingException {
    List<String> sessions = objectMapper.readValue(response, new TypeReference<List<String>>() {});
    Collections.sort(sessions);
    return sessions;
  }

  /**
   * Converts the response string to a list of students.
   *
   * @param response The response string.
   * @return The list of students.
   * @throws JsonProcessingException If an error occurs during JSON processing.
   */
  protected List<Student> getStudentListFromResponse(String response) throws JsonProcessingException {
    List<Student> students = objectMapper.readValue(response, new TypeReference<List<Student>>() {});
    Collections.sort(students);
    return students;
  }

  /**
   * Gets the net ID of the user.
   *
   * @return The user's net ID.
   */
  public String getUserNetID() {
    return userNetID;
  }

  /**
   * Sets the net ID of the user.
   *
   * @param userNetID The user's net ID.
   */
  public void setUserNetID(String userNetID) {
    this.userNetID = userNetID;
  }

  /**
   * Sets the ObjectMapper for JSON processing.
   *
   * @param objectMapper The ObjectMapper.
   */
  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * Gets the password of the user.
   *
   * @return The user's password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the user.
   *
   * @param password The user's password.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Exits the program.
   */
  protected void exit() {
    throw new IllegalArgumentException("Successfully exited.");
  }
}
