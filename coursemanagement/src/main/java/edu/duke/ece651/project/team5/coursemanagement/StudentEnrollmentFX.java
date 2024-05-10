package edu.duke.ece651.project.team5.coursemanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Callback;
import javafx.geometry.Pos;



import org.checkerframework.checker.units.qual.A;
import org.controlsfx.control.CheckComboBox;

import edu.duke.ece651.project.team5.shared.dao.*;
import edu.duke.ece651.project.team5.shared.model.*;
import edu.duke.ece651.project.team5.shared.enums.*;
import edu.duke.ece651.project.team5.admin.*;
import javafx.scene.Scene;

public class StudentEnrollmentFX {
    private static Set<Student> getStudentsInSection(Section section, Dao<Student> studentDao) throws Exception{
        Map<String, EnrollmentStatus> enrollmentStatusMap = section.getEnrollment();
        Set<Student> students = new HashSet<>();
        for (String studentId : enrollmentStatusMap.keySet()) {
            if (enrollmentStatusMap.get(studentId) == EnrollmentStatus.ENROLLED) {
                try {
                    Student student = studentDao.get(studentId).get();
                    students.add(student);
                } catch (Exception e) {
                    System.out.println("Error in getting student: " + e.getMessage());
                    throw new Exception("Error in getting student: " + e.getMessage());
                }
            }
        }
        return students;
    }

    public static Section getSectionFromId(String sectionId, Dao<Section> sectionDao) throws Exception {
        // Dao<Section> sectionDao = new SectionDao();
        try {
            return sectionDao.get(sectionId).get();
        } catch (Exception e) {
            System.out.println("Error in getting section: " + e.getMessage());
            throw new Exception("Error in getting section: " + e.getMessage());
        }
    }
    public static Stage editEnrollment(Section section, Dao<Section> sectionDao, Dao<Student> studentDao) throws Exception {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Enrollment");
        VBox layout = new VBox();
        layout.setSpacing(10);
        Set<Student> students = StudentEnrollmentFX.getStudentsInSection(section, studentDao);
        Label sectionLabel = new Label("Section: " + section.getCourseSectionId());

        ObservableList<Student> studentsView = FXCollections.observableArrayList(students);
        ListView<Student> studentListView = new ListView<>(studentsView);

        studentListView.setCellFactory(new Callback<ListView<Student>, ListCell<Student>>() {
            @Override
            public ListCell<Student> call(ListView<Student> param) {
                return new ListCell<Student>() {
                    @Override
                    protected void updateItem(Student student, boolean empty) {
                        super.updateItem(student, empty);
                        if (empty || student == null) {
                            setText(null);
                        } else {
                            setText(student.getLegalName());
                        }
                    }
                };
            }
        });
        studentListView.setOnMouseClicked(event -> {
            Student student = studentListView.getSelectionModel().getSelectedItem();
            if (student != null && event.getClickCount() == 2) {
                if (ConfirmBox.display("Remove Student", "Are you sure you want to remove " + student.getLegalName() + " from this section?")) {
                    try {
                        section.dropStudent(student);
                        sectionDao.update(section);
                        if (studentsView.contains(student)) {
                            students.remove(student);
                            studentsView.remove(student);
                        }
                    } catch (Exception e) {
                        System.out.println("Error in removing student: " + e.getMessage());
                        AlertBox.display("Error", "Error in removing student: " + e.getMessage());
                    }
                    // studentsView.remove(student);
                }
            }
        });

        ScrollPane scrollPane = new ScrollPane(studentListView);
        scrollPane.setFitToWidth(true);
        Button addButton = new Button("Add Student to Section");
        addButton.setId("addButton");
        Button closeButton = new Button("Return");
        closeButton.setId("closeButton");
        layout.getChildren().addAll(sectionLabel, scrollPane, addButton, closeButton);
        layout.setAlignment(Pos.CENTER);
        closeButton.setOnAction(e -> {
            window.close();
        });
        closeButton.getStyleClass().add("return-button");  
        addButton.setOnAction(e -> {
            try {
                Student student = StudentEnrollmentFX.getStudentFromInput(studentDao);
                if (student != null && (section.getEnrollment().get(student.getNetId()) != EnrollmentStatus.ENROLLED)) {
                    section.enrollStudent(student);
                    sectionDao.update(section);
                    studentsView.add(student);
                    // if (!studentsView.contains(student)) {
                    //     studentsView.add(student);
                    // }
                }
            } catch (Exception ex) {
                System.out.println("Error in adding student: " + ex.getMessage());
                AlertBox.display("Error", "Error in adding student: " + ex.getMessage());
            }

        });

        Scene scene = new Scene(layout, 500, 500);  
        scene.getStylesheets().add(App.class.getResource("/style.css").toExternalForm());
        
        window.setScene(scene);
        window.showAndWait();

        return window;
    }

    private static Student getStudentFromInput(Dao<Student> studentDao) throws Exception {;
        String netId = GetInfo.display("Add Student to Section", "Enter the student's Net ID:");
        try {
            return studentDao.get(netId).get();
        } catch (Exception e) {
            System.out.println("Error in getting student: " + e.getMessage());
            throw new Exception("Error in getting student: " + e.getMessage());
        }
    }
}
