package edu.duke.ece651.project.team5.admin;
import javafx.scene.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.animation.PauseTransition;
import javafx.application.Application;

import javafx.geometry.*;
public class AlertBox {
    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(100);
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close the window");
        closeButton.getStyleClass().add("confirm-button");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10) ;
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        String css = App.class.getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> window.close() );
        delay.play();
    }
}
