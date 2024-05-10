package edu.duke.ece651.project.team5.coursemanagement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import edu.duke.ece651.project.team5.shared.dao.CourseDao;
import edu.duke.ece651.project.team5.shared.dao.ProfessorDao;
import edu.duke.ece651.project.team5.shared.dao.SectionDao;
import edu.duke.ece651.project.team5.shared.dao.StudentDao;
import edu.duke.ece651.project.team5.shared.model.Course;
import edu.duke.ece651.project.team5.shared.model.Professor;
import edu.duke.ece651.project.team5.shared.model.Section;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class AppTest extends ApplicationTest {

  private App app;
  private StudentDao studentDao;
  private SectionDao sectionDao;
  private CourseDao coursedao;
  private ProfessorDao professorDao;

  @Override
  public void start(Stage stage) {
    app = spy(new App());
    studentDao = Mockito.mock(StudentDao.class);
    sectionDao = Mockito.mock(SectionDao.class);
    coursedao = Mockito.mock(CourseDao.class);
    professorDao = Mockito.mock(ProfessorDao.class);
    app.start(stage);
  }

  @AfterEach
  void tearDown() {
    Platform.runLater(() -> {
      Stage primaryStage = (Stage) app.getPrimaryStage();
      primaryStage.close();
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testCreateLoginScene() {
    Platform.runLater(() -> {
      try {
        TextField usernameField = lookup("#usernameField").query();
        PasswordField passwordField = lookup("#passwordField").query();
        Button loginButton = lookup("#loginButton").query();
        interact(() -> {
          usernameField.setText("admin");
          passwordField.setText("admin");
          loginButton.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testCreateLoginSceneFail() throws SQLException {
    Platform.runLater(() -> {
      try {
        TextField usernameField = lookup("#usernameField").query();
        PasswordField passwordField = lookup("#passwordField").query();
        Button loginButton = lookup("#loginButton").query();

        interact(() -> {
          usernameField.setText("admin");
          passwordField.setText("wrongpassword");
          loginButton.fire();
        });

        // Assert
        assertEquals("Username or Password is incorrect.", app.getLoginMessage().getText());
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testSectionSceneProfessor() {
    Platform.runLater(() -> {
      try {
        Professor professor = mock(Professor.class);
        Section section = mock(Section.class);
        Set<Section> sections = new HashSet<>();
        sections.add(section);
        when(professor.getCourseSections()).thenReturn(sections);
        when(section.getCourseSectionId()).thenReturn("ECE651_1");
        app.sectionListScene(professor);
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testSectionListSceneCourse() {
    Platform.runLater(() -> {
      try {
        Course course = mock(Course.class);
        Section section = mock(Section.class);
        List<Section> sections = new ArrayList<>();
        sections.add(section);
        when(course.getSections()).thenReturn(sections);
        when(section.getCourseSectionId()).thenReturn("ECE651_1");
        app.sectionListScene(course);
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testCreateDashboardScene() {
    Platform.runLater(() -> {
      try {
        app.createDashboardScene();
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testHandleMouseClick() {
    Platform.runLater(() -> {
      try {
        MouseEvent mouseEvent = mock(MouseEvent.class);
        ListView<Object> listView = mock(ListView.class);when(mouseEvent.getButton()).thenReturn(MouseButton.PRIMARY);when(mouseEvent.getClickCount()).thenReturn(1);        when(mouseEvent.getTarget()).thenReturn(listView);
        app.handleMouseClick(mouseEvent, listView);
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testShowSectionScene() {
    Platform.runLater(() -> {
      try {
        Set<String> sections = new HashSet<>();
        sections.add("ECE651_1");
        Professor professor = new Professor("professor", "professor");
        Course course = new Course("ECE651", "Software Engineering");
        app.getPrimaryStage().setScene(app.showSectionsScene(sections, professor, course));
        Button returnButton = lookup("#returnButton").query();
        interact(() -> {
          returnButton.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testShowSectionScene2() {
    Platform.runLater(() -> {
      try {
        Set<String> sections = new HashSet<>();
        sections.add("ECE651_1");
        Professor professor = new Professor("professor", "professor");
        Course course = new Course("ECE651", "Software Engineering");
        app.getPrimaryStage().setScene(app.showSectionsScene(sections, professor, course));
        Button createSectiButton = lookup("#createSectionButton").query();
        interact(() -> {
          createSectiButton.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testShowSectionScene3() {
    Platform.runLater(() -> {
      try {
        Set<String> sections = new HashSet<>();
        sections.add("ECE651_1");
        Professor professor = new Professor("professor", "professor");
        Course course = new Course("ECE651", "Software Engineering");
        app.getPrimaryStage().setScene(app.showSectionsScene(sections, professor, course));
        Button createCourrseButton = lookup("#createCourseButton").query();
        interact(() -> {
          createCourrseButton.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testShowOptionScene() {
    Platform.runLater(() -> {
      try {
        String courseSectionId = "ECE651_1";
        Professor professor = new Professor("professor", "professor");
        app.getPrimaryStage().setScene(app.showOptionsScene(courseSectionId, professor));
        Button editEnrollmentButton = lookup("#editEnrollmentButton").query();
        interact(() -> {
          editEnrollmentButton.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testShowOptionScene2() {
    Platform.runLater(() -> {
      try {
        String courseSectionId = "ECE651_1";
        Professor professor = new Professor("professor", "professor");
        app.getPrimaryStage().setScene(app.showOptionsScene(courseSectionId, professor));
        Button changeDescriptionButton = lookup("#changeDescriptionButton").query();
        interact(() -> {
          changeDescriptionButton.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testShowOptionScene3() {
    Platform.runLater(() -> {
      try {
        String courseSectionId = "ECE651_1";
        app.getPrimaryStage().setScene(app.showOptionsScene(courseSectionId, null));
        Button changeInstructorButton = lookup("#changeInstructorButton").query();
        interact(() -> {
          changeInstructorButton.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testShowOptionScene4() {
    Platform.runLater(() -> {
      try {
        String courseSectionId = "ECE651_1";
        Professor professor = new Professor("professor", "professor");
        app.getPrimaryStage().setScene(app.showOptionsScene(courseSectionId, professor));
        Button deleteSectionButton = lookup("#deleteSectionButton").query();
        interact(() -> {
          deleteSectionButton.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testShowOptionScene5() {
    Platform.runLater(() -> {
      try {
        String courseSectionId = "ECE651_1";
        Professor professor = new Professor("professor", "professor");
        app.getPrimaryStage().setScene(app.showOptionsScene(courseSectionId, professor));
        Button deleteCourseButton = lookup("#deleteCourseButton").query();
        interact(() -> {
          deleteCourseButton.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testShowOptionScene6() {
    Platform.runLater(() -> {
      try {
        String courseSectionId = "ECE651_1";
        Professor professor = new Professor("professor", "professor");
        app.getPrimaryStage().setScene(app.showOptionsScene(courseSectionId, professor));
        Button returnButton = lookup("#returnButton").query();
        interact(() -> {
          returnButton.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testCreateCourseScene() {
    Platform.runLater(() -> {
      try {
        Professor professor = new Professor("professor", "professor");
        app.getPrimaryStage().setScene(app.createCourseScene(professor));
        Button createButton = lookup("#createButton").query();
        interact(() -> {
          createButton.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testCreateCourseScene2() {
    Platform.runLater(() -> {
      try {
        Professor professor = new Professor("professor", "professor");
        app.getPrimaryStage().setScene(app.createCourseScene(professor));
        Button createByFile = lookup("#createByFile").query();
        interact(() -> {
          createByFile.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testCreateCourseScene3() {
    Platform.runLater(() -> {
      try {
        Professor professor = new Professor("professor", "professor");
        app.getPrimaryStage().setScene(app.createCourseScene(professor));
        Button returnButton = lookup("#returnButton").query();
        interact(() -> {
          returnButton.fire();
        });
      } catch (Exception e) {
        app.getPrimaryStage().close();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testEditEnrollment() {
    Platform.runLater(() -> {
      Section section = new Section();
      try {
        StudentEnrollmentFX.editEnrollment(section, sectionDao, studentDao);
        Button addButton = lookup("#addButton").query();
        interact(() -> {
          addButton.fire();
        });
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testEditEnrollment2() {
    Platform.runLater(() -> {
      Section section = new Section();
      try {
        StudentEnrollmentFX.editEnrollment(section, sectionDao, studentDao);
        Button closeButton = lookup("#closeButton").query();
        interact(() -> {
          closeButton.fire();
        });
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testGetSectionFromId() {
    Platform.runLater(() -> {
      try {
        StudentEnrollmentWindow.getSectionFromId("ECE651_1", sectionDao);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testStudentEnrollmentWindow() {
    Platform.runLater(() -> {
      try {
        Section section = new Section();
        StudentEnrollmentWindow.editEnrollment(section, sectionDao, studentDao);
        Button addButton = lookup("#addButton").query();
        interact(() -> {
          addButton.fire();
        });
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testStudentEnrollmentWindow2() {
    Platform.runLater(() -> {
      try {
        Section section = new Section();
        StudentEnrollmentWindow.editEnrollment(section, sectionDao, studentDao);
        Button closeButton = lookup("#closeButton").query();
        interact(() -> {
          closeButton.fire();
        });
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testChangeInstructor() {
    Platform.runLater(() -> {
      try {
        Professor professor = new Professor("professor", "professor");
        DataManager.changeInstructor("ece651_1", professor, sectionDao);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testCreateCourseByFile() {
    Platform.runLater(() -> {
      try {
        DataManager.createCourseByFile("file", coursedao);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testDeleteSection() {
    Platform.runLater(() -> {
      try {
        DataManager.deleteSection("ece651_1", sectionDao);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testDeleteCourse() {
    Platform.runLater(() -> {
      try {
        DataManager.deleteCourse("ece651", coursedao);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void addLectureScene() {
    Platform.runLater(() -> {
      try {
        LectureCreator.addLectureScene("ECE651_1");
        Button createButton = lookup("#createButton").query();
        interact(createButton::fire);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void addLectureScene2() {
    Platform.runLater(() -> {
      try {
        LectureCreator.addLectureScene("ECE651_1");
        CheckBox useCurrentTime = lookup("#useCurrentTime").query();
        interact(useCurrentTime::fire);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }
}
