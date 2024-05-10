package edu.duke.ece651.project.team5.coursemanagement;

import edu.duke.ece651.project.team5.admin.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Region;
import javafx.scene.control.ListCell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
import edu.duke.ece651.project.team5.shared.dao.*;
import edu.duke.ece651.project.team5.shared.model.*;
import java.util.Set;
import org.checkerframework.checker.units.qual.A;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
import edu.duke.ece651.project.team5.shared.dao.*;

/**
 * The main class of the Course Management System application.
 * This class extends the JavaFX Application class and serves as the entry point
 * for the application.
 * It provides methods for setting up the login scene, dashboard scene, and
 * other UI components.
 * The App class also contains methods for handling user interactions and
 * managing course data.
 */
public class App extends Application {
  protected Stage primaryStage;
  protected Scene loginScene;
  protected Scene dashboardScene;
  protected Label loginMessage;

  /**
   * The Scene class represents a JavaFX scene, which is a container for all the
   * visual elements in a JavaFX application.
   * It provides the structure and layout for the user interface components.
   *
   * This class is used to create a login scene for the application.
   * The login scene contains a grid layout with labels, text fields, and buttons
   * for user authentication.
   *
   * @param setLoginScene() A method that sets up and returns the login scene.
   * @return The login scene with the specified layout and components.
   */
  public Scene createLoginScene() {
    // Login Scene setup
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));

    Label userName = new Label("User Name:");
    TextField userTextField = new TextField();
    userTextField.setId("usernameField");
    Label password = new Label("Password:");
    PasswordField pwBox = new PasswordField();
    pwBox.setId("passwordField");
    Button loginButton = new Button("Sign in");
    loginButton.setId("loginButton");
    loginButton.getStyleClass().add("confirm-button");
    grid.add(userName, 0, 1);
    grid.add(userTextField, 1, 1);
    grid.add(password, 0, 2);
    grid.add(pwBox, 1, 2);
    grid.add(loginButton, 1, 3);
    grid.add(loginMessage, 1, 4);

    loginButton.setOnAction(e -> {
      if (DataManager.checkSuperUserExists(userTextField.getText(), pwBox.getText(), new SuperUserDao())) {
        System.out.println("Login successfully!");
        loginMessage.setText("Login successfully!");
        dashboardScene = createDashboardScene();
        primaryStage.setScene(dashboardScene);
      } else {
        loginMessage.setText("Username or Password is incorrect.");
      }
    });
    Scene scene = new Scene(grid, 500, 500);
    scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    return scene;
  }

  public Scene sectionListScene(Professor professor) throws IllegalArgumentException {
    Set<Section> sections = professor.getCourseSections();


    Set<String> sectionIds = new java.util.HashSet<>();
    if (sections == null) {
      return showSectionsScene(null, professor, null);
    }
    for (Section section : sections) {
      sectionIds.add(section.getCourseSectionId());
    }
    System.out.println("sectionIds: " + sectionIds.size());
    return showSectionsScene(sectionIds, professor, null);
  }

  public Scene sectionListScene(Course course) {
    List<Section> sections = course.getSections();
    Set<String> sectionIds = new java.util.HashSet<>();
    for (Section section : sections) {
      sectionIds.add(section.getCourseSectionId());
    }
    return showSectionsScene(sectionIds, null, course);
  }

  public Scene createDashboardScene() {
    // Create ObservableLists
    ObservableList<Professor> list1 = FXCollections.observableArrayList(DataManager.getProfessors(new ProfessorDao()));
    ObservableList<Course> list2 = FXCollections.observableArrayList(DataManager.getCourses(new CourseDao()));
    // Create ListViews
    ListView<Professor> listView1 = new ListView<>(list1);
    ListView<Course> listView2 = new ListView<>(list2);
    listView1.setCellFactory(new Callback<ListView<Professor>, ListCell<Professor>>() {
      @Override
      public ListCell<Professor> call(ListView<Professor> param) {
        return new ListCell<Professor>() {
          @Override
          protected void updateItem(Professor professor, boolean empty) {
            super.updateItem(professor, empty);
            if (empty || professor == null) {
              setText(null);
            } else {
              setText(professor.getLegalName());
            }
          }
        };
      }
    });
    listView2.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {
      @Override
      public ListCell<Course> call(ListView<Course> param) {
        return new ListCell<Course>() {
          @Override
          protected void updateItem(Course course, boolean empty) {
            super.updateItem(course, empty);
            if (empty || course == null) {
              setText(null);
            } else {
              setText(course.getCourseId() + ": " + course.getCourseName());
            }
          }
        };
      }
    });
    // Setup list views with event handlers
    listView1.setOnMouseClicked(event -> handleMouseClick(event, listView1));
    listView2.setOnMouseClicked(event -> handleMouseClick(event, listView2));
    // Create ScrollPanes for each ListView
    ScrollPane scrollPane1 = new ScrollPane(listView1);
    scrollPane1.setFitToWidth(true);
    ScrollPane scrollPane2 = new ScrollPane(listView2);
    scrollPane2.setFitToWidth(true);
    // Create Labels for each column
    Button label1 = new Button("Faculty list");
    // Create new course button
    Button newCourseButton = new Button("Create a New Course");
    newCourseButton.setOnAction(e -> {
      primaryStage.setScene(createCourseScene(null));
    });

    // VBox for each column
    VBox vbox1 = new VBox(20, label1, scrollPane1); // 5 is the spacing between elements
    vbox1.setAlignment(Pos.CENTER);
    VBox vbox2 = new VBox(20, newCourseButton, scrollPane2);
    vbox2.setAlignment(Pos.CENTER);
    // Layout in HBox for columns
    HBox hboxColumns = new HBox(20, vbox1, vbox2); // 10 is the spacing between columns

    // Create Exit Button
    Button exitButton = new Button("Exit");
    exitButton.setOnAction(e -> primaryStage.close()); // Close the application
    exitButton.getStyleClass().add("confirm-button");
    // Layout in BorderPane
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(hboxColumns);
    // borderPane.setTop(newCourseButton);
    borderPane.setBottom(exitButton);
    // BorderPane.setAlignment(newCourseButton, Pos.CENTER_RIGHT);
    BorderPane.setAlignment(exitButton, Pos.CENTER);

    // Scene setup
    Scene scene = new Scene(borderPane, 500, 500);
    scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    return scene;
  }

  public void handleMouseClick(MouseEvent event, ListView<? extends Object> listView) {
    Object selectedItem = listView.getSelectionModel().getSelectedItem();
    if (selectedItem != null && event.getClickCount() == 2) {
      System.out.println("Selected item: " + selectedItem);
      if (selectedItem instanceof Professor) {
        primaryStage.setScene(sectionListScene((Professor) selectedItem));
      } else if (selectedItem instanceof Course) {
        primaryStage.setScene(sectionListScene((Course) selectedItem));
      }
    }
  }

  public Scene showSectionsScene(Set<String> sections, Professor professor, Course course) {
    Label label = new Label("List of Sections:");
    if (professor != null) {
      label.setText("Courses of Professor " + professor.getLegalName());
    }

    VBox vbox = new VBox(10, label);
    vbox.setAlignment(Pos.CENTER);
    if (sections != null) {


      ObservableList<String> sectionList = FXCollections.observableArrayList(sections);
      ListView<String> listView = new ListView<>(sectionList);
      vbox.getChildren().add(listView);
      listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
        @Override
        public ListCell<String> call(ListView<String> param) {
          return new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
              super.updateItem(item, empty);
              if (empty || item == null) {
                setText(null);
              } else {
                setText(item);
              }
            }
          };
        }
      });
      listView.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && listView.getSelectionModel().getSelectedItem() != null) {
          String section = listView.getSelectionModel().getSelectedItem();
          System.out.println("Selected section: " + section);


          primaryStage.setScene(showOptionsScene(section, professor));


        }
      });
    } else {
      vbox.getChildren().add(new Label("No sections."));
    }

    // Create Return Button
    Button returnButton = new Button("Return to Dashboard");
    returnButton.setId("returnButton");
    returnButton.setOnAction(e -> {
      dashboardScene = createDashboardScene();
      primaryStage.setScene(dashboardScene);
    });
    returnButton.getStyleClass().add("confirm-button");



    if (course != null) {
      // Create section button
      Button createSectionButton = new Button("Create a New Section");
      createSectionButton.setId("createSectionButton");
      createSectionButton.setOnAction(e -> {
        try {
          course.getSections().add(SectionCreator.addSectionScene(course.getCourseId(), professor));
          new CourseDao().update(course);
        } catch (Exception ex) {
          System.out.println("Error in creating section: " + ex.getMessage());
          AlertBox.display("Error", "Error in creating section: " + ex.getMessage());
        }
      });
      if (professor != null) {
        Button createCourseButton = new Button("Create a New Course for " + professor.getLegalName());
        createCourseButton.setId("createCourseButton");
        createCourseButton.setOnAction(e -> {
          primaryStage.setScene(createCourseScene(professor));
        });
        createCourseButton.getStyleClass().add("confirm-button");
      }
      vbox.getChildren().add(createSectionButton);
    }
    vbox.getChildren().add(returnButton);
    Scene scene = new Scene(vbox, 500, 500);
    scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    return scene;
  }

  public Scene showOptionsScene(String courseSectionId, Professor professor) {
    // Create buttons
    Button editEnrollmentButton = new Button("Edit Enrollment");
    editEnrollmentButton.setId("editEnrollmentButton");
    Button changeDescriptionButton = new Button("Change Course Description");
    changeDescriptionButton.setId("changeDescriptionButton");
    Button deleteSectionButton = new Button("Delete Section");
    deleteSectionButton.setId("deleteSectionButton");
    Button deleteCourseButton = new Button("Delete Course");
    deleteCourseButton.setId("deleteCourseButton");
    Button createLecture = new Button("Create Lecture");
    createLecture.setId("createLecture");
    createLecture.setOnAction(e -> {
      try {
        LectureCreator.addLectureScene(courseSectionId);
        // AlertBox.display("Lecture Created", "Lecture has been created.");
      } catch (Exception ex) {
        ex.printStackTrace();
        AlertBox.display("Error Adding Lecture", ex.getMessage());
      }
    });

    Button returnButton = new Button("Return to dashboard");
    returnButton.setId("returnButton");
    returnButton.getStyleClass().add("confirm-button");
    // Create layout for buttons
    VBox layout = new VBox(10);
    layout.setAlignment(Pos.CENTER);
    layout.getChildren().addAll(editEnrollmentButton, changeDescriptionButton);

    if (professor == null) {
      Button changeInstructor = new Button("Change Instructor");
      changeInstructor.setId("changeInstructor");
      ObservableList<Professor> professorList = FXCollections.observableArrayList(DataManager.getProfessors(new ProfessorDao()));
      ComboBox<Professor> professorComboBox = new ComboBox<>(professorList);
      professorComboBox.setCellFactory(new Callback<ListView<Professor>, ListCell<Professor>>() {
        @Override
        public ListCell<Professor> call(ListView<Professor> param) {
          return new ListCell<Professor>() {
            @Override
            protected void updateItem(Professor professor, boolean empty) {
              super.updateItem(professor, empty);
              if (empty || professor == null) {
                setText(null);
              } else {
                setText(professor.getLegalName());
              }
            }
          };
        }
      });
      changeInstructor.setOnAction(e -> {
        DataManager.changeInstructor(courseSectionId, professorComboBox.getValue(), new SectionDao());
      });
      professorComboBox.setPromptText("Select an instructor:");
      HBox hBox = new HBox(10, professorComboBox, changeInstructor);
      hBox.setAlignment(Pos.CENTER);
      layout.getChildren().add(hBox);
    }
    // Set button actions
    editEnrollmentButton.setOnAction(e -> {
      // Add code to handle edit enrollment action
      try {
        StudentEnrollmentFX.editEnrollment(StudentEnrollmentFX.getSectionFromId(courseSectionId, new SectionDao()), new SectionDao(), new StudentDao());
      } catch (Exception ex) {
        System.out.println("Error in edit enrollment: " + ex.getMessage());
        AlertBox.display("Error", "Error in edit enrollment: " + ex.getMessage());
      }

    });

    changeDescriptionButton.setOnAction(e -> {
      // Add code to handle change course description action
      try {
        String[] ids = courseSectionId.split("_", 2);
        CourseDao courseDao = new CourseDao();
        String courseId = ids[0];
        Course courseUpdated = courseDao.get(courseId).get();
        courseUpdated
            .setCourseName(GetInfo.display("Course Description", "Enter course descripton for " + courseSectionId));
        courseDao.update(courseUpdated);
      } catch (Exception ex) {
        System.out.println("Error in changing course description: " + ex.getMessage());
        AlertBox.display("Error", "Error in changing course description: " + ex.getMessage());
      }
    });

    deleteSectionButton.setOnAction(e -> {
      // Add code to handle delete section action
      if (ConfirmBox.display("Confirm Delete", "Are you sure you want to delete this section?")) {
        DataManager.deleteSection(courseSectionId, new SectionDao());
        AlertBox.display("Section Deleted", "Section " + courseSectionId + " has been deleted.");
      }
    });
    deleteSectionButton.getStyleClass().add("warning-button");
    deleteCourseButton.getStyleClass().add("warning-button");
    deleteCourseButton.setOnAction(e -> {
      // Add code to handle delete course action
      if (ConfirmBox.display("Confirm Delete", "Are you sure you want to delete this course?")) {
        DataManager.deleteCourse(courseSectionId, new CourseDao());
        AlertBox.display("Course Deleted", "Course " + courseSectionId + " has been deleted.");
      }
    });
    returnButton.setOnAction(e -> {
      // Add code to return to dashboard
      dashboardScene = createDashboardScene();
      primaryStage.setScene(dashboardScene);
    });
    // Create layout for buttons
    layout.getChildren().addAll(createLecture, deleteSectionButton, deleteCourseButton, returnButton);
    // Create scene with layout
    Scene scene = new Scene(layout, 500, 500);
    scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    return scene;

  }

  public Scene createCourseScene(Professor professor) {
    Label courseIdLabel = new Label("Course ID:");
    TextField courseIdTextField = new TextField();
    Label courseDescriptionLabel = new Label("Course Description:");
    TextField courseDescriptionTextField = new TextField();
    Button createButton = new Button("Create");
    createButton.setId("createButton");
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));

    grid.add(courseIdLabel, 0, 0);
    grid.add(courseIdTextField, 1, 0);
    grid.add(courseDescriptionLabel, 0, 1);
    grid.add(courseDescriptionTextField, 1, 1);

    grid.add(createButton, 1, 3);

    Button createByFile = new Button("Create new course by importing file");
    createByFile.setId("createByFile");
    grid.add(createByFile, 1, 4);
    createByFile.setOnAction(e -> {
      try {
        DataManager.createCourseByFile(FileHandler.getFilePath(primaryStage), new CourseDao());
        AlertBox.display("Course Created", "Course has been created.");
      } catch (Exception ex) {
        ex.printStackTrace();
        AlertBox.display("Error Adding Course", ex.getMessage());
      }
    });
    Button returnButton = new Button("Return to Dashboard");
    returnButton.setId("returnButton");
    returnButton.getStyleClass().add("confirm-button");
    returnButton.setOnAction(e -> {
      dashboardScene = createDashboardScene();
      primaryStage.setScene(dashboardScene);
    });
    grid.add(returnButton, 1, 5);
    createButton.setOnAction(e -> {
      try {
        DataManager.createCourseManually(courseIdTextField.getText(), courseDescriptionTextField.getText(), professor);
        AlertBox.display("Course Created", "Course " + courseIdTextField.getText() + " has been created.");
      } catch (Exception ex) {
        ex.printStackTrace();
        AlertBox.display("Error Adding Course", ex.getMessage());
      }
    });
    Scene scene = new Scene(grid, 500, 500);
    scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    return scene;
  }

  public Stage getPrimaryStage() {
    return primaryStage;
  }

  public Label getLoginMessage() {
    return loginMessage;
  }

  @Override
  public void start(Stage primaryStage1) {
    this.primaryStage = primaryStage1;
    primaryStage.setTitle("Course Management System");

    loginMessage = new Label();
    loginScene = createLoginScene();
    dashboardScene = createDashboardScene();

    primaryStage.setScene(loginScene);
    primaryStage.show();

  }

  public static void main(String[] args) {
    SuperUserDao superUserDao = new SuperUserDao();
    try {
      Admin admin = new Admin("admin", "admin");
      superUserDao.add(admin);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    launch(args);
  }

}
