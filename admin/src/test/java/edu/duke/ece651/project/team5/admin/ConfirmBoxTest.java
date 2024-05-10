package edu.duke.ece651.project.team5.admin;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import javafx.application.Platform;

public class ConfirmBoxTest extends ApplicationTest {
  @Test
  void testDisplay() {
    Platform.runLater(() -> {
      String title = "Test Title";
      String message = "Test Message";
      interact(() -> {
        Boolean result = ConfirmBox.display(title, message);
      });
    });
    WaitForAsyncUtils.waitForFxEvents();
  }
}
