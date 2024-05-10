package edu.duke.ece651.project.team5.admin;

import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import edu.duke.ece651.project.team5.shared.dao.SuperUserDao;
import edu.duke.ece651.project.team5.shared.model.Admin;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Map;

public class GetNewUserTest extends ApplicationTest {

  @Test
  void testDisplay() {
    Platform.runLater(() -> {
      String title = "Test Title";
      String message = "Test Message";

      interact(() -> {
        Map<String, String> result = GetNewUser.display(title, message);
      });
    });
    WaitForAsyncUtils.waitForFxEvents();
  }
}
