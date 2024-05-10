package edu.duke.ece651.project.team5.admin;

import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.util.HashMap;
import java.util.Map;
public class GetNewUser {
    public static Map<String, String> display(String title, String message)    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label netIdLabel = new Label("netId:");
        TextField netId = new TextField();
        netId.setId("netId");

        Label pwLabel = new Label("Password:");
        PasswordField pwBox = new PasswordField();
        pwBox.setId("password");

        Label nameLabel = new Label("Legal Name:");
        TextField nameBox = new TextField();
        nameBox.setId("legalName");

        Label emailLabel = new Label("Email:");
        TextField emailBox = new TextField();
        emailBox.setId("email");
        Button confirmButton = new Button("Confirm");
        confirmButton.setId("confirmButton");

        Label phoneLabel = new Label("Phone number:");
        TextField phoneNumber = new TextField();
        phoneNumber.setId("phone");


        Label mess = new Label();
        mess.setText(message);
        grid.add(mess, 1, 1);
        grid.add(netIdLabel, 0, 2);
        grid.add(netId, 1, 2);
        grid.add(pwLabel, 0, 3);
        grid.add(pwBox, 1, 3);
        grid.add(nameLabel, 0, 4);
        grid.add(nameBox, 1, 4);
        grid.add(emailLabel, 0, 5);
        grid.add(emailBox, 1, 5);
        grid.add(phoneLabel, 0, 6);
        grid.add(phoneNumber, 1, 6);
        grid.add(confirmButton, 1, 7);



        confirmButton.setOnAction(e -> window.close());
        confirmButton.getStyleClass().add("confirm-button");

        Scene scene = new Scene(grid, 350, 400);
        scene.getStylesheets().add(GetNewUser.class.getResource("/style.css").toExternalForm());
        window.setScene(scene);
        window.showAndWait();

        Map<String, String> result = new HashMap<>();
        result.put("netId", netId.getText());
        result.put("password", pwBox.getText());
        result.put("legalName", nameBox.getText());
        result.put("email", emailBox.getText());
        result.put("phone", phoneNumber.getText());
        return result;
    }
}
