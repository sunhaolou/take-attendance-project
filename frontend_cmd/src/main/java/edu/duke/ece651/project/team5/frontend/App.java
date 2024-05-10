package edu.duke.ece651.project.team5.frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

/**
 * The main application class responsible for handling user interactions and controlling the flow of the program.
 */
public class App {

  private final BufferedReader input;
  private TextUser user;
  private TextUser defaultUser;
  private final BufferedReader socketIn;
  private final PrintWriter socketOut;
  private Socket socket;

  /**
   * Constructs an instance of the App class.
   *
   * @param input The BufferedReader for reading user input.
   * @param socket The Socket object for communication with the server.
   * @throws IOException If an I/O error occurs while creating the input/output streams.
   */
  public App(BufferedReader input, Socket socket) throws IOException {
    this.input = input;
    this.socket = socket;
    socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    socketOut = new PrintWriter(socket.getOutputStream(), true);
    defaultUser = new TextUser("", "", input, socket, socketIn, socketOut);
  }

  /**
   * Initializes the application by prompting the user to log in and setting up the appropriate user object.
   *
   * @throws IOException If an I/O error occurs while reading from the input or output streams.
   */
  public void initial() throws IOException {
    String prompt = "Which identity are you logging in as?";
    List<String> options = new ArrayList<>();
    options.add("Professor");
    options.add("Student");
    Display.displayToUser(prompt, options);
    int index = defaultUser.readInputWithErrorHandle(options.size());
    String userNetID;
    String password;
    while (true) {
      try {
        Display.simpleDisplay("Please enter your netID");
        userNetID = input.readLine();
        Display.simpleDisplay("Please enter your password");
        password = input.readLine();
        defaultUser.setUserNetID(userNetID);
        defaultUser.setPassword(password);
        String flag;
        if (index == 0) {
          flag = "professor_login";
        } else {
          flag = "student_login";
        }
        JSONObject json = defaultUser.createDefaultJson(flag);
        socketOut.println(json.toString());
        String response = socketIn.readLine();
        if (response.equals("success")) {
          break;
        } else {
          System.out.println(response);
          throw new IllegalArgumentException("Invalid username or password");
        }
      } catch (IllegalArgumentException e) {
        Display.simpleDisplay(e.getMessage());
      }
    }
    if (index == 0) {
      user = new ProfessorUser(userNetID, password, input, socket, socketIn, socketOut);
    } else {
      user = new StudentUser(userNetID, password, input, socket, socketIn, socketOut);
    }
  }

  /**
   * Returns the currently logged-in user.
   *
   * @return The currently logged-in user.
   */
  public TextUser getUser() {
    return user;
  }

  /**
   * Closes the connection and releases any system resources associated with the application.
   *
   * @throws IOException If an I/O error occurs while closing the input or output streams.
   */
  public void connectionClose() throws IOException {
    socketIn.close();
    socketOut.close();
    socket.close();
  }

  /**
   * The main entry point of the application.
   *
   * @param args The command-line arguments.
   * @throws IOException If an I/O error occurs while creating the input/output streams or connecting to the server.
   */
  public static void main(String[] args) throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    PrintStream out = System.out;
    Socket socket = new Socket("localhost", 8080);
    App app = new App(input, socket);
    while (true) {
      try {
        app.initial();
        if (app.getUser().getClass() == ProfessorUser.class) {
          app.getUser().chooseCourse();
        }
        break;
      } catch (IllegalArgumentException e) {
        out.println(e.getMessage());
      }
    }
    while (true) {
      try {
        app.getUser().takeAction();
      } catch (IllegalArgumentException e) {
        out.println(e.getMessage());
        break;
      }
    }
    app.connectionClose();
  }
}
