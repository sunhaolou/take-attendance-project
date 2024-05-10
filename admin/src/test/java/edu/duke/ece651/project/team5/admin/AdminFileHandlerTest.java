package edu.duke.ece651.project.team5.admin;

import edu.duke.ece651.project.team5.shared.dao.*;
import edu.duke.ece651.project.team5.shared.model.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import javafx.application.Platform;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import edu.duke.ece651.project.team5.admin.utils.CsvInputProcessor;

@ExtendWith(ApplicationExtension.class)
public class AdminFileHandlerTest {
  @Test
  public void testAddStudentsToDatabase() throws Exception {
    List<JSONObject> students = new ArrayList<>();
    JSONObject student1 = new JSONObject();
    student1.put("netId", "student1");
    student1.put("password", "password1");
    student1.put("legalName", "John Doe");
    student1.put("preferredName", "");
    student1.put("email", "john.doe@example.com");
    student1.put("phone", "1234567890");
    students.add(student1);

    JSONObject student2 = new JSONObject();
    student2.put("netId", "student2");
    student2.put("password", "password2");
    student2.put("legalName", "Jane Smith");
    student2.put("preferredName", "");
    student2.put("email", "jane.smith@example.com");
    student2.put("phone", "9876543210");
    students.add(student2);
    Dao<Student> studentDaoMock = Mockito.mock(StudentDao.class);
    AdminFileHandler.addPersonsToDatabase(students, studentDaoMock);
    verify(studentDaoMock, times(2)).add(any(Student.class));

  }

  @Test
  public void testAddZeroStudentsToDatabase() throws Exception {
    Dao<Student> studentDaoMock = Mockito.mock(StudentDao.class);
    List<JSONObject> students2 = new ArrayList<>();
    AdminFileHandler.addPersonsToDatabase(students2, studentDaoMock);
    verify(studentDaoMock, times(0)).add(any(Student.class));
  }
}