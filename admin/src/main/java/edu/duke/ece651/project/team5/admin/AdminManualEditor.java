package edu.duke.ece651.project.team5.admin;

import edu.duke.ece651.project.team5.admin.utils.CsvInputProcessor;
import edu.duke.ece651.project.team5.admin.utils.InputProcessor;

import edu.duke.ece651.project.team5.shared.dao.*;
import edu.duke.ece651.project.team5.shared.utils.*;
import javafx.scene.control.Alert;
import edu.duke.ece651.project.team5.shared.model.*;
import java.sql.*;
import javax.xml.crypto.Data;

import org.checkerframework.checker.units.qual.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

/**
 * The AdminManualEditor class represents an administrator of the system.
 * It provides functionality to add students and professors, modify student
 * and
 * professor information,
 * and delete students and professors from the database.
 */
public class AdminManualEditor {

  /**
   * Retrieves the new person information from the user.
   *
   * @return a map containing the new person information with the following keys:
   *         - "netId": the netId entered by the user
   *         - "password": the password entered by the user
   *         - "legalName": the legal name entered by the user
   *         - "email": the email entered by the user
   *         - "phone": the phone number entered by the user
   * @throws IllegalArgumentException if any of the entered information has an
   *                                  invalid format
   * @throws IOException              if an I/O error occurs while reading the
   *                                  user input
   */
  public static Map<String, String> getNewPersonInfo(String message) throws IllegalArgumentException {
    Map<String, String> newInfo = GetNewUser.display("Input new user", message);
    InputProcessor inputProcessor = new CsvInputProcessor();

    if (!inputProcessor.checkNetIdFormat(newInfo.get("netId"))) {
      throw new IllegalArgumentException("Invalid netId format.");
    }
    if (!inputProcessor.checkNameFormat(newInfo.get("legalName"))) {
      throw new IllegalArgumentException("Invalid legal name format.");
    }
    if (!inputProcessor.checkEmailFormat(newInfo.get("email"))) {
      throw new IllegalArgumentException("Invalid email format.");
    }
    if (!inputProcessor.checkPhoneFormat(newInfo.get("phone"))) {
      throw new IllegalArgumentException("Invalid phone number format.");
    }
    return newInfo;
  }

  /**
   * Adds a student to the database.
   */
  public static void addStudent(String errorMessage) {
    try {
      Map<String, String> newInfo = getNewPersonInfo(errorMessage + "\nPlease enter student's info.");
      String preHashedPassword = newInfo.get("password");
      String hashedPassword = PasswordHasher.getHashedPassword(preHashedPassword);
      Student student = new Student(newInfo.get("netId"),
          hashedPassword, newInfo.get("legalName"), newInfo.get("email"), newInfo.get("phone"));
      addPersonToDatabase(student, new StudentDao());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      AlertBox.display("Input Error", e.getMessage());
    }
  }

  /**
   * Adds a professor to the database.
   */
  public static void addProfessor(String errorMessage) {

    try {
      Map<String, String> newInfo = getNewPersonInfo(errorMessage + "\nPlease enter professor's info.");
      String preHashedPassword = newInfo.get("password");
      String hashedPassword = PasswordHasher.getHashedPassword(preHashedPassword);
      Professor professor = new Professor(newInfo.get("netId"), newInfo.get("legalName"), newInfo.get("email"),
          newInfo.get("phone"), hashedPassword);
      ProfessorDao professorDao = new ProfessorDao();
      addPersonToDatabase(professor, professorDao);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      AlertBox.display("Input Error", e.getMessage());
    }
  }

    /**
     * Retrieves a person from the specified DAO using the provided netId.
     *
     * @param <T> the type of the person to retrieve
     * @param dao the DAO object used to retrieve the person
     * @return the retrieved person
     * @throws IOException  if an I/O error occurs while reading the netId
     * @throws SQLException if a database error occurs while retrieving the person
     */
    public static <T> T getPerson(Dao<T> dao, String errorMessage) throws Exception {
        String message = "Enter the netId: ";
        try {
            String netId = GetInfo.display("Input netId", errorMessage + message);
            Optional<T> person = dao.get(netId);
            if (person.isPresent()) {
                return person.get();
            } else {
                System.out.println("The NetID not found.");
                throw new IllegalArgumentException("The NetID not found.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

  /**
   * Updates a student's preferred name in the database.
   */
  public static void updateStudentPreferredName(Dao<Student> student_dao) {
    try {
      Student student = getPerson(student_dao, "");
      String preferredName = GetInfo.display("Input preferred name",
          "Enter the preferred name for " + student.getLegalName() + ":");
      student.setPreferredName(preferredName);
      student_dao.update(student);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      AlertBox.display("Error", e.getMessage());
    }
  }

  /**
   * Deletes a student/professor from the database.
   */
  public static <T extends Person> void deletePerson(Dao<T> dao) {

        try {
            T person = getPerson(dao, "");
            if (ConfirmBox.display("Confirm Action", "Are you sure you want to delete "+ person.getLegalName() + "?")) {
                dao.delete(person);
            }
            AlertBox.display("Success", person.getLegalName() + " has been deleted successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlertBox.display("Error", e.getMessage());
        }
    }

  /**
   * Updates the password of a student or professor.
   *
   * @param t the object (student or professor) to update the password for
   */

    public static <T extends Person> void updatePassword(Dao<T> dao) {
        try {
            T person = getPerson(dao, "");
            String password = GetInfo.display("Input password", "Enter the new password for "+ person.getLegalName() +": ");
            person.setPassword(password);
            dao.update(person);
            AlertBox.display("Success", "Password updated successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlertBox.display("Error", e.getMessage());
        }
    }

    /**
     * Updates one's email in the database.
     */
    public static <T extends Person> void updateEmail(Dao<T> dao) {
        try {
            T person = getPerson(dao, "");
            String email = GetInfo.display("Input email", "Enter the new email for "+ person.getLegalName() +": ");
            if (!new CsvInputProcessor().checkEmailFormat(email)) {
                throw new IllegalArgumentException("Invalid email format.");

            }
            person.setEmail(email);
            dao.update(person);
            AlertBox.display("Success", "Email updated successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlertBox.display("Error", e.getMessage());
        }
    }

    /**
     * Updates one's phone in the database.
     */
    public static <T extends Person> void updatePhone(Dao<T> dao) {
        try {
            T person = getPerson(dao, "");
            String phone = GetInfo.display("Input phone", "Enter the new phone for " + person.getLegalName() +": ");
            if (!new CsvInputProcessor().checkEmailFormat(phone)) {
                throw new IllegalArgumentException("Invalid phone number format.");
            }
            person.setEmail(phone);
            dao.update(person);
            AlertBox.display("Success", "Phone number updated successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlertBox.display("Error", e.getMessage());
        }
    }

    /**
     * Adds a student/professor to the database.
     *
     * @param student/professor the student to add
     */
    public static <T extends Person> void addPersonToDatabase(T person, Dao<T> dao) {
        try {
            dao.add(person);
            AlertBox.display("Success", person.getLegalName() +" has been added successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlertBox.display("Error", e.getMessage() + ". NetId might already exist.");
        }
    }

}
