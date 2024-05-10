// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
package edu.duke.ece651.project.team5.admin;

import edu.duke.ece651.project.team5.admin.utils.*;
import edu.duke.ece651.project.team5.shared.dao.*;
import edu.duke.ece651.project.team5.shared.model.*;

import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.*;
import javax.xml.crypto.Data;

import org.checkerframework.checker.units.qual.A;
import org.json.JSONObject;
import java.nio.file.NoSuchFileException;

import java.util.Iterator;
import java.util.List;

/**
 * This class represents a file handler for the admin module.
 * It extends the AdminManualEditor class and provides methods to handle
 * different file types.
 */
public class AdminFileHandler {

    private static InputProcessor inputProcessor;

    // public AdminFileHandler() {
    // this.inputProcessor = new CsvInputProcessor();
    // }

    /**
     * Adds a list of persons to the database using the specified DAO.
     *
     * @param dao the DAO object used to interact with the database
     * @param <T> the type of person to be added
     */
    public static <T extends Person> void add(Dao<T> dao, Stage primaryStage) {
        try {
            List<JSONObject> persons = getInput(primaryStage);
            addPersonsToDatabase(persons, dao);
            AlertBox.display("Success", "Successfully added the file to the database.");
        } catch (Exception e) {
            AlertBox.display("Error", e.getMessage());
        }
    }

    public static <T extends Person> void addPersonToDatabase(T person, Dao<T> dao) throws Exception {
        try {
            dao.add(person);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /**
     * Adds a list of persons to the database.
     *
     * @param persons The list of JSONObjects representing the persons to be added.
     * @param dao     The DAO object used to access the database.
     * @param <T>     The type of person (Student or Professor).
     */
    public static <T extends Person> void addPersonsToDatabase(List<JSONObject> persons, Dao<T> dao) throws Exception {
        Iterator<JSONObject> Iterator = persons.iterator();
        while (Iterator.hasNext()) {
            JSONObject p = Iterator.next();

            if (dao instanceof StudentDao) {
                Student person = new Student(p.getString("netId"), p.getString("password"), p.getString("legalName"),
                        p.getString("preferredName"), p.getString("email"), p.getString("phone"));
                Dao<Student> studentDao = (StudentDao) dao;
                AdminFileHandler.addPersonToDatabase(person, studentDao);
            }
            // else if (dao instanceof ProfessorDao) {
            else {
                Professor person = new Professor(p.getString("netId"), p.getString("legalName"), p.getString("email"),
                        p.getString("phone"), p.getString("password"));
                Dao<Professor> professorDao = (ProfessorDao) dao;
                AdminFileHandler.addPersonToDatabase(person, professorDao);
            }
        }
    }

    /**
     * Prompts the user to enter a file path and returns a list of JSONObjects
     * parsed from the file.
     * If an exception occurs during file processing, the method will display an
     * error message and prompt the user to enter a valid file path again.
     *
     * @return a list of JSONObjects parsed from the file
     */
    public static List<JSONObject> getInput(Stage primaryStage) throws Exception {
        inputProcessor = new CsvInputProcessor();
        try {
            String filePath = FileHandler.getFilePath(primaryStage);
            return inputProcessor.parseRosterCsv(filePath);

        } catch (Exception e) {
            System.out.println("Failed to process the file: " + e.getMessage());
            throw new Exception("Failed to process the file: " + e.getMessage());

        }
    }

}
