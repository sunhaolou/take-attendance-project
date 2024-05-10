package edu.duke.ece651.project.team5.admin;

import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class GetInfo{
    public static String display(String title, String message)    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        TextField input = new TextField();

        Button confirmButton = new Button("Confirm");
        confirmButton.getStyleClass().add("confirm-button");
        confirmButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, input, confirmButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(App.class.getResource("/style.css").toExternalForm());
        window.setScene(scene);
        window.showAndWait();

        return input.getText();
    }
}