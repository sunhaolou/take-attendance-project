package edu.duke.ece651.project.team5.frontend;

import java.io.PrintStream;
import java.util.*;

/**
 * The Display class provides methods for displaying content to the user.
 */
public class Display {
  // Use System.out as the default output stream
  private static final PrintStream out = System.out;

  /**
   * Displays a simple message to the user.
   *
   * @param content The message to be displayed.
   */
  public static void simpleDisplay(String content) {
    out.println(content);
  }

  /**
   * Displays a prompt followed by a list of options to the user.
   * Each option is prefixed with a number.
   *
   * @param content The prompt or message to be displayed before the options.
   * @param options The list of options to be displayed to the user.
   */
  public static void displayToUser(String content, List<String> options) {
    // Display the prompt or message
    out.println(content);

    // Display each option along with its corresponding number
    for (int i = 0; i < options.size(); i++) {
      out.println(Integer.toString(i + 1) + ". " + options.get(i));
    }
  }
}
