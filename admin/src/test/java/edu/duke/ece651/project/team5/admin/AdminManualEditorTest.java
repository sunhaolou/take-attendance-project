package edu.duke.ece651.project.team5.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import edu.duke.ece651.project.team5.shared.dao.Dao;
import edu.duke.ece651.project.team5.shared.dao.StudentDao;
import edu.duke.ece651.project.team5.shared.model.Person;
import edu.duke.ece651.project.team5.shared.model.Student;
import edu.duke.ece651.project.team5.shared.utils.PasswordHasher;
import javafx.application.Platform;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GetNewUser.class)
public class AdminManualEditorTest extends ApplicationTest {
  @Test
  public void testGetNewPersonInfo() throws InterruptedException {
    Platform.runLater(() -> {
      try {
        Map<String, String> mockInfo = new HashMap<>();
        mockInfo.put("netId", "validNetId");
        mockInfo.put("legalName", "validName");
        mockInfo.put("email", "validEmail@example.com");
        mockInfo.put("phone", "1234567890");

        PowerMockito.mockStatic(GetNewUser.class);
        when(GetNewUser.display(anyString(), anyString())).thenReturn(mockInfo);

        // Act
        Map<String, String> result = AdminManualEditor.getNewPersonInfo("Test message");

        // Assert
        assertEquals(mockInfo, result);
      } catch (Exception e) {
        System.out.println(e);
      }
    });
    WaitForAsyncUtils.waitForFxEvents();
  }

  @Test
  public void testAddStudent() {
    Platform.runLater(() -> {
      Map<String, String> mockInfo = new HashMap<>();
      mockInfo.put("netId", "validNetId");
      mockInfo.put("password", "validPassword");
      mockInfo.put("legalName", "validName");
      mockInfo.put("email", "validEmail@example.com");
      mockInfo.put("phone", "1234567890");


      try (MockedStatic<AdminManualEditor> mockAdminFileHandler = mockStatic(AdminManualEditor.class)){
        mockAdminFileHandler.when(()-> AdminManualEditor.getNewPersonInfo(any(String.class))).thenReturn(mockInfo);
      }

      StudentDao mockStudentDao = mock(StudentDao.class);
      doNothing().when(mockStudentDao).add(any(Student.class));

      // Act
      AdminManualEditor.addStudent("Test message");

      // Assert
      verify(mockStudentDao, times(1)).add(any(Student.class));
    });
    WaitForAsyncUtils.waitForFxEvents();
  }
}