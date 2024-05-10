package edu.duke.ece651.project.team5.coursemanagement;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.TextField;

public class LectureCreator {
    public static void addLectureScene(String courseSectionId) throws Exception {

        Stage window = new Stage();
        GridPane root = new GridPane();
        root.setAlignment(javafx.geometry.Pos.CENTER);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Lecture");
        window.setMinWidth(250);
        Scene scene = new Scene(root, 500, 500);

        Button createButton = new Button("Create");
        createButton.setId("createButton");
        Label dateLabel = new Label("Choose the Date:");
        DatePicker datePicker = new DatePicker();
        root.add(dateLabel, 0, 0);
        root.add(datePicker, 1, 0);
        // Combo boxes for time selection
        Label hourLabel = new Label("Choose the hour:");
        Label minuteLabel = new Label("Choose the minute:");
        Label secondLabel = new Label("Choose the second:");
        ComboBox<Integer> hours = new ComboBox<>();
        ComboBox<Integer> minutes = new ComboBox<>();
        ComboBox<Integer> seconds = new ComboBox<>();
        root.add(hourLabel, 0, 1);
        root.add(hours, 1, 1);
        root.add(minuteLabel, 0, 2);
        root.add(minutes, 1, 2);
        root.add(secondLabel, 0, 3);
        root.add(seconds, 1, 3);
        // Populate time combo boxes
        for (int i = 0; i < 24; i++)
            hours.getItems().add(i);
        for (int i = 0; i < 60; i++) {
            minutes.getItems().add(i);
            seconds.getItems().add(i);
        }

        // Check box for selecting current time
        CheckBox useCurrentTime = new CheckBox("Use Current Time");
        useCurrentTime.setId("useCurrentTime");
        root.add(useCurrentTime, 1, 5);
        useCurrentTime.setOnAction(e -> {
            if (useCurrentTime.isSelected()) {
                LocalDate nowDate = LocalDate.now();
                LocalTime nowTime = LocalTime.now();
                datePicker.setValue(nowDate);
                hours.setValue(nowTime.getHour());
                minutes.setValue(nowTime.getMinute());
                seconds.setValue(nowTime.getSecond());
            }
        });
        createButton.getStyleClass().add("return-button");
        root.add(createButton, 1, 7);


        createButton.setOnAction(e -> {
            try {
                Section section = new SectionDao().get(courseSectionId).get();
                Timestamp time = Timestamp.valueOf(
                        datePicker.getValue().atTime(hours.getValue(), minutes.getValue(), seconds.getValue()));
                Lecture lecture = new Lecture(courseSectionId, time);
                // System.out.println(lecture);
                section.addSession(lecture);
                SectionDao sectionDao = new SectionDao();
                sectionDao.update(section);
                // LectureDao lectureDao = new LectureDao();
                // lectureDao.add(lecture);
            } catch (Exception ex) {
                System.out.println("Error creating lecture: " + ex.getMessage());
                AlertBox.display("Error Creating Lecture", ex.getMessage());
                return;
            }
            System.out.println("Lecture created successfully.");
            AlertBox.display("Lecture Created", "Lecture created successfully.");
            window.close();
            return;

        });

        createButton.getStyleClass().add("confirm-button");

        scene.getStylesheets().add(App.class.getResource("/style.css").toExternalForm());
        window.setScene(scene);
        window.showAndWait();
        return;
    }

}
