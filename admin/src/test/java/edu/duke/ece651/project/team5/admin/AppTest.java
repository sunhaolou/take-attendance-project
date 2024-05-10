package edu.duke.ece651.project.team5.admin;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import edu.duke.ece651.project.team5.shared.dao.SuperUserDao;
import edu.duke.ece651.project.team5.shared.model.Admin;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Optional;

class AppTest extends ApplicationTest {

    private App app;

    @Override
    public void start(Stage stage) {
        SuperUserDao mockSuperUserDao = mock(SuperUserDao.class);
        when(mockSuperUserDao.get("admin")).thenReturn(Optional.of(new Admin("admin", "admin")));
        app = spy(new App(mockSuperUserDao));
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
    public void testSetLoginScene() throws SQLException {
      WaitForAsyncUtils.waitForFxEvents();

      TextField usernameField = lookup("#usernameField").query();
      PasswordField passwordField = lookup("#passwordField").query();
      Button loginButton = lookup("#loginButton").query();

      interact(() -> {
          usernameField.setText("admin");
          passwordField.setText("admin");
          loginButton.fire();
      });

      // Assert
      assertEquals("Login successfully!", app.getLoginMessage().getText());
    }

    @Test
    public void testSetLoginSceneFail() throws SQLException {
      WaitForAsyncUtils.waitForFxEvents();

      TextField usernameField = lookup("#usernameField").query();
      PasswordField passwordField = lookup("#passwordField").query();
      Button loginButton = lookup("#loginButton").query();

      interact(() -> {
          usernameField.setText("admin");
          passwordField.setText("wrongpassword");
          loginButton.fire();
      });

      // Assert
      assertEquals("username or password is incorrect.", app.getLoginMessage().getText());
    }

    @Test
    public void testHandleButtonClick() {
        Platform.runLater(() -> {
            app.handleButtonClick("Unknown");
            app.handleButtonClick("Update student");
            app.handleButtonClick("Update faculty");
            app.handleButtonClick("Add new student");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    public void testHandleButtonClickOther() {
        Platform.runLater(() -> {
          app.handleButtonClick("Add new faculty");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    public void testHandleButtonClickOther2() {
        Platform.runLater(() -> {
          app.handleButtonClick("Import Students' data file");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
    @Test
    public void testHandleButtonClickOther3() {
        Platform.runLater(() -> {
          app.handleButtonClick("Import Faculty's data file");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}