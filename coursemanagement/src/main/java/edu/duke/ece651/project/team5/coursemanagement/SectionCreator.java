package edu.duke.ece651.project.team5.coursemanagement;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Callback;

import org.bouncycastle.eac.EACException;
import org.checkerframework.checker.units.qual.A;
import org.controlsfx.control.CheckComboBox;

import edu.duke.ece651.project.team5.shared.dao.*;
import edu.duke.ece651.project.team5.shared.model.*;
import edu.duke.ece651.project.team5.shared.enums.*;
import edu.duke.ece651.project.team5.admin.*;
import javafx.scene.Scene;

public class SectionCreator {

    public static Section addSectionScene(String courseId, Professor professor) throws Exception {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Section");
        window.setMinWidth(250);

        Section section = new Section();
        section.setCourseId(courseId);
        GridPane grid = new GridPane();

        Label sectionIdLabel = new Label("Section ID:");
        TextField sectionIdTextField = new TextField();
        grid.add(sectionIdLabel, 0, 2);
        grid.add(sectionIdTextField, 1, 2);

        ObservableList<Student> studentList = FXCollections.observableArrayList(DataManager.getStudents(new StudentDao()));
        ListView<Student> studentListView = new ListView<>(studentList);
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

        studentListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Button createButton = new Button("Create Section");

        Label studentLabel = new Label("Select students to enroll:");
        grid.add(studentLabel, 0, 3);
        grid.add(studentListView, 1, 3);
        grid.add(createButton, 1, 5);

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

        professorComboBox.setPromptText("Select an instructor:");
        grid.add(professorComboBox, 1, 4);
        professor = professorComboBox.getValue();

        createButton.setOnAction(e -> {
            List<Student> selectedStudents = studentListView.getSelectionModel().getSelectedItems();
            for (Student student : selectedStudents) {
                section.enrollStudent(student.getNetId());
            }
            section.setSectionId(sectionIdTextField.getText());
            section.setCourseSectionId(courseId + "_" + sectionIdTextField.getText());
            section.setProfessors(new ArrayList<>(Collections.singletonList(professorComboBox.getValue())));

            ProfessorDao professorDao = new ProfessorDao();
            Professor choosenProfessor = professorComboBox.getValue();
            choosenProfessor.addCourseSection(section);

            professorDao.update(choosenProfessor);

            System.out.println("Section " + section.getCourseSectionId() + " created successfully.");
            window.close();

        });

        createButton.getStyleClass().add("confirm-button");
        Button returnButton = new Button("Return");
        returnButton.setOnAction(e -> window.close());
        returnButton.getStyleClass().add("return-button");
        grid.add(returnButton, 2, 5);
        Scene scene = new Scene(grid, 500, 500);
        scene.getStylesheets().add(App.class.getResource("/style.css").toExternalForm());
        window.setScene(scene);
        window.showAndWait();

        return section;

    }
}
