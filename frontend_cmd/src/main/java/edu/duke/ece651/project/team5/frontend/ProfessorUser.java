package edu.duke.ece651.project.team5.frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

import org.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;

import edu.duke.ece651.project.team5.frontend.model.Student;

/**
 * The ProfessorUser class represents a user with professor privileges in the
 * system.
 * It extends the TextUser class and provides functionality specific to
 * professors.
 */
public class ProfessorUser extends TextUser {

  /**
   * Constructs a ProfessorUser object with the given parameters.
   *
   * @param username  The username of the professor.
   * @param password  The password of the professor.
   * @param input     The buffered reader for user input.
   * @param socket    The socket for communication with the server.
   * @param socketIn  The buffered reader for socket input.
   * @param socketOut The print writer for socket output.
   */
  public ProfessorUser(String username, String password, BufferedReader input, Socket socket, BufferedReader socketIn,
      PrintWriter socketOut) {
    super(username, password, input, socket, socketIn, socketOut);
  }

  /**
   * Takes action based on the user's choice.
   * Displays a menu of options and executes the selected action.
   *
   * @throws IOException If an I/O error occurs.
   */
  @Override
  public void takeAction() throws IOException {
    String prompt = "What would you like to do for course " + course_section_id + " ?";
    List<String> options = new ArrayList<>();
    options.add("take attendance");
    options.add("browse and select one student");
    options.add("search for a student");
    options.add("select another course");
    options.add("export attendance record for all students");
    options.add("exit the program");
    Display.displayToUser(prompt, options);
    int index = readInputWithErrorHandle(options.size());
    if (index == 0) {
      takeAttendance();
    } else if (index == 1) {
      browseAndSelect();
    } else if (index == 2) {
      searchAndSelect();
    } else if (index == 3) {
      chooseCourse();
    } else if (index == 4) {
      exportAttendance();
    } else {
      exit();
    }
  }

  /**
   * Takes attendance for all students in the course section.
   *
   * @throws IOException If an I/O error occurs.
   */
  public void takeAttendance() throws IOException {
    String sessionId = selectSessionId();
    List<Student> all_students = getCurrentlyEnrolledStudents();
    String prompt;
    for (Student student : all_students) {
      prompt = "Name: " + student.getPreferredName() + ", NetId: " + student.getNetId();
      recordOneAttendance(sessionId, student.getNetId(), prompt, false);
    }
  }

  /**
   * Gets the list of currently enrolled students in the course section.
   *
   * @return A list of Student objects representing the enrolled students.
   * @throws JsonProcessingException If there is an error processing the JSON
   *                                 response.
   * @throws IOException             If an I/O error occurs.
   */
  private List<Student> getCurrentlyEnrolledStudents() throws JsonProcessingException, IOException {
    String flag = "get_student_for_section";
    JSONObject json = createDefaultJson(flag);
    json.put("courseSectionId", course_section_id);
    socketOut.println(json.toString());
    String response = socketIn.readLine();
    List<Student> student_list = getStudentListFromResponse(response);
    return student_list;
  }

  /**
   * Selects a session ID from the available sessions for attendance recording.
   *
   * @return The selected session ID.
   * @throws IOException If an I/O error occurs.
   */
  private String selectSessionId() throws IOException {
    String flag = "session_all_id";
    JSONObject json = createDefaultJson(flag);
    json.put("courseSectionId", course_section_id);
    socketOut.println(json.toString());
    String response = socketIn.readLine();
    List<String> session_list = getStringListFromResponse(response);
    if (session_list.size() == 0) {
      throw new IllegalArgumentException("There are no sessions for this course.");
    }
    String prompt = "Which session do you want to change the attendance for?";
    Display.displayToUser(prompt, session_list);
    int index = readInputWithErrorHandle(session_list.size());
    return session_list.get(index);
  }

  /**
   * Updates the attendance record for a specific student.
   *
   * @param netId The NetID of the student.
   * @throws IOException If an I/O error occurs.
   */
  public void updateOneAttendance(String netId) throws IOException {
    try {
      String sessionId = selectSessionId();
      recordOneAttendance(sessionId, netId, "How would you like to change the student's attendance?", true);
    } catch (IllegalArgumentException e) {
      Display.simpleDisplay(e.getMessage());
      return;
    }
  }

  /**
   * Records attendance for a specific student in a session.
   *
   * @param sessionId The ID of the session.
   * @param netId     The NetID of the student.
   * @param prompt    The prompt message for the user.
   * @param update    Indicates if this is an update operation.
   * @throws IOException If an I/O error occurs.
   */
  public void recordOneAttendance(String sessionId, String netId, String prompt, boolean update) throws IOException {
    List<String> options = new ArrayList<>();
    options.add("present");
    options.add("tardy");
    options.add("absent");
    Display.displayToUser(prompt, options);
    int index = readInputWithErrorHandle(options.size());
    String flag;
    if (update) {
      flag = "session_attendance_update";
    } else {
      flag = "session_attendance";
    }
    JSONObject json = createDefaultJson(flag);

    json.put("sessionId", sessionId);
    json.put("netId", netId);
    json.put("status", options.get(index));
    socketOut.println(json.toString());
    String response = socketIn.readLine();
    Display.simpleDisplay(response);
  }

  /**
   * Allows the professor to browse through the list of enrolled students and
   * select one to update attendance.
   *
   * @throws IOException If an I/O error occurs.
   */
  public void browseAndSelect() throws IOException {
    List<Student> students = getAllOrSpecificStudents("");
    if (students.size() == 0) {
      Display.simpleDisplay("There are no enrolled students found. Please try another action.");
      return;
    }
    String prompt = "All students:";
    List<String> students_info = new ArrayList<>();
    for (int i = 0; i < students.size(); i++) {
      String info = "";
      Student curr = students.get(i);
      info += "Name: " + curr.getLegalName() + ",";
      info += " NetId: " + curr.getNetId();
      students_info.add(info);
    }
    Display.displayToUser(prompt, students_info);
    Display.simpleDisplay("Please choose one student from above");
    int index = readInputWithErrorHandle(students.size());
    String uniqueID = students.get(index).getNetId();
    updateOneAttendance(uniqueID);
  }

  /**
   * Allows the professor to search for a student by name and select one to update
   * attendance.
   *
   * @throws IOException If an I/O error occurs.
   */
  public void searchAndSelect() throws IOException {
    Display.simpleDisplay("Please enter the student's name");
    String name = input.readLine();
    List<Student> students = getAllOrSpecificStudents(name);
    if (students.size() == 0) {
      Display.simpleDisplay("There are no enrolled students named " + name + " found. Please try another action.");
      return;
    }
    String prompt = "The students with the name are following, please choose one";
    List<String> students_info = new ArrayList<>();
    for (int i = 0; i < students.size(); i++) {
      String info = "";
      Student curr = students.get(i);
      info += "Name: " + curr.getLegalName() + ",";
      info += " NetId: " + curr.getNetId();
      students_info.add(info);
    }
    Display.displayToUser(prompt, students_info);
    int index = readInputWithErrorHandle(students.size());
    String uniqueID = students.get(index).getNetId();
    updateOneAttendance(uniqueID);
  }

  /**
   * Retrieves either all enrolled students or students with a specific name for
   * the current course section.
   *
   * @param name The name of the student to search for, or an empty string to
   *             retrieve all students.
   * @return A list of Student objects matching the search criteria.
   * @throws IOException If an I/O error occurs.
   */
  private List<Student> getAllOrSpecificStudents(String name) throws IOException {
    String flag;
    if (name.equals("")) {
      flag = "get_student_for_section";
    } else {
      flag = "get_student_by_name_section";
    }
    JSONObject json = createDefaultJson(flag);
    json.put("courseSectionId", course_section_id);
    List<Student> students;
    socketOut.println(json.toString());
    String response = socketIn.readLine();
    try {
      students = getStudentListFromResponse(response);
    } catch (JsonProcessingException e) {
      students = new ArrayList<>();
    }
    return students;
  }

  /**
   * Exports the attendance record for the current course section in the selected
   * format (PDF or JSON).
   *
   * @throws JsonProcessingException If an error occurs during JSON processing.
   * @throws IOException             If an I/O error occurs.
   */
  @Override
  protected void exportAttendance() throws JsonProcessingException, IOException {
    String prompt = "What format do you want to export?";
    List<String> options = new ArrayList<>();
    options.add("PDF");
    options.add("JSON");
    Display.displayToUser(prompt, options);
    int index = readInputWithErrorHandle(options.size());
    String flag = "section_report";
    JSONObject json = createDefaultJson(flag);
    json.put("courseSectionId", course_section_id);
    socketOut.println(json.toString());
    String response = socketIn.readLine();
    try {
      if (index == 0) {
        outGenerator = new PdfOutputGenerator();
      } else if (index == 1) {
        outGenerator = new JsonOutputGenerator();
      }
      outGenerator.exportAttendanceForProfessor(response);
      Display.simpleDisplay("Successfully exported.");
    } catch (Exception e) {
      Display.simpleDisplay("Error while getting report from server.");
    }
  }

}
