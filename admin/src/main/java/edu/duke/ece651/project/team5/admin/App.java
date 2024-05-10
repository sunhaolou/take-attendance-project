package edu.duke.ece651.project.team5.admin;

import java.util.Arrays;
import java.util.Optional;

// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
import edu.duke.ece651.project.team5.shared.dao.ProfessorDao;
import edu.duke.ece651.project.team5.shared.dao.StudentDao;
import edu.duke.ece651.project.team5.shared.dao.SuperUserDao;
import edu.duke.ece651.project.team5.shared.model.Admin;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.Arrays;

/**
 * The main class that represents the admin application.
 * This class handles the login process and provides options for the admin to
 * manage users.
 */

public class App extends Application {
    private Stage primaryStage;
    private Scene loginScene;
    private Scene dashboardScene;
    private Label loginMessage;
    private SuperUserDao superUserDao;

    public App() {
        superUserDao = new SuperUserDao();
    }

    public App(SuperUserDao superUserDao) {
        this.superUserDao = superUserDao;
    }

    public Boolean checkSuperUserExists(String userName, String password) {
        Optional<Admin> superUser = superUserDao.get(userName);
        if (!superUser.isPresent()) {
            return false;
        }
        return superUser.get().getPassword().equals(password);

    }
    public Scene setLoginScene() {
        // Login Scene setup
        GridPane grid = new GridPane();
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
            if (checkSuperUserExists(userTextField.getText(), pwBox.getText())) {
                // if (userTextField.getText().equals("admin") &&
                // pwBox.getText().equals("admin")) {
                System.out.println("Login successfully!");
                loginMessage.setText("Login successfully!");
                primaryStage.setScene(dashboardScene);
            } else {
                loginMessage.setText("username or password is incorrect.");
            }
        });
        Scene scene = new Scene(grid, 500, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }

    private Scene setUpdateScene(String type) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setAlignment(Pos.BASELINE_CENTER);
        Button password = new Button("Update password");
        password.setId("updatePasswordButton");
        Button email = new Button("Update email");
        email.setId("updateEmailButton");
        Button phone = new Button("Update phone number");
        phone.setId("updatePhoneButton");
        Button back = new Button("Back to main menu");
        back.getStyleClass().add("return-button");
        back.setOnAction(e -> primaryStage.setScene(dashboardScene));
        if (type.equals("Update student")) {
            Button preferredName = new Button("Update preferred name");
            preferredName.setOnAction(e -> AdminManualEditor.updateStudentPreferredName(new StudentDao()));
            password.setOnAction(e -> AdminManualEditor.updatePassword(new StudentDao()));
            email.setOnAction(e -> AdminManualEditor.updateEmail(new StudentDao()));
            phone.setOnAction(e -> AdminManualEditor.updatePhone(new StudentDao()));
            root.getChildren().addAll(password, email, phone, preferredName, back);
        } else {
            password.setOnAction(e -> AdminManualEditor.updatePassword(new ProfessorDao()));
            email.setOnAction(e -> AdminManualEditor.updateEmail(new ProfessorDao()));
            phone.setOnAction(e -> AdminManualEditor.updatePhone(new ProfessorDao()));
            root.getChildren().addAll(password, email, phone, back);
        }

        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }

    private Scene setDashboard() {

        VBox root = new VBox(10);
        root.setPadding(new Insets(20, 20, 20, 20));

        TreeItem<String> rootItem = new TreeItem<>("Dashboard Options");
        rootItem.setExpanded(true);

        TreeItem<String> manualEdit = new TreeItem<>("Manual Edit");
        manualEdit.setExpanded(true);
        addOptionsManual(manualEdit);

        TreeItem<String> editByFiles = new TreeItem<>("Edit by Files");
        editByFiles.setExpanded(true);
        addOptionsFile(editByFiles);

        rootItem.getChildren().addAll(Arrays.asList(manualEdit, editByFiles));

        TreeView<String> tree = new TreeView<>(rootItem);
        tree.setShowRoot(false);

        tree.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> treeView) {
                return new TreeCell<String>() {
                    private final Button button = new Button();

                    {
                        button.setOnAction(e -> handleButtonClick(getItem()));
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            if (getTreeItem().getParent() != null && getTreeItem().getParent().getParent() != null) {
                                button.setText(item);
                                button.setId(item);
                                setGraphic(button);
                                setText(null);
                            } else {
                                setGraphic(null);
                                setText(item);
                            }
                        }
                    }
                };
            }
        });

        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("return-button");
        logoutButton.setOnAction(e -> primaryStage.close());

        root.getChildren().addAll(tree, logoutButton);

        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        return scene;
    }

    private void addOptionsManual(TreeItem<String> parent) {
        parent.getChildren().add(new TreeItem<>("Update student"));
        parent.getChildren().add(new TreeItem<>("Update faculty"));
        parent.getChildren().add(new TreeItem<>("Add new student"));
        parent.getChildren().add(new TreeItem<>("Add new faculty"));
    }

    private void addOptionsFile(TreeItem<String> parent) {
        parent.getChildren().add(new TreeItem<>("Import Students' data file"));
        parent.getChildren().add(new TreeItem<>("Import Faculty's data file"));
    }
    public void handleButtonClick(String option) {
        System.out.println("Button clicked: " + option);
        // ConfirmBox.display("Title", "Are you sure?");
        // System.out.println(FileHandler.getFilePath(primaryStage));
        // You can handle different actions based on the option text here
        switch (option) {
            case "Update student":
            case "Update faculty":
                System.out.println("update" + option);
                primaryStage.setScene(setUpdateScene(option));
                break;
            case "Add new student":
                AdminManualEditor.addStudent("");
                break;
            case "Add new faculty":
                AdminManualEditor.addProfessor("");
                break;
            case "Import Students' data file":
                AdminFileHandler.add(new StudentDao(), primaryStage);
                break;
            case "Import Faculty's data file":
                AdminFileHandler.add(new ProfessorDao(), primaryStage);
                break;
            default:
                System.out.println("Unknown option: " + option);
                break;
        }
    }

    public Label getLoginMessage() {
        return loginMessage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage1) {
        this.primaryStage = primaryStage1;
        primaryStage.setTitle("Admin App Login");

        loginMessage = new Label();
        loginScene = setLoginScene();
        dashboardScene = setDashboard();

        primaryStage.setScene(loginScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        // DatabaseInitializer dbInitializer = new DatabaseInitializer();
        // dbInitializer.initialize();
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