package edu.duke.ece651.project.team5.coursemanagement;

import org.junit.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import edu.duke.ece651.project.team5.shared.dao.Dao;
import edu.duke.ece651.project.team5.shared.dao.SectionDao;
import edu.duke.ece651.project.team5.shared.dao.StudentDao;
import edu.duke.ece651.project.team5.shared.model.Section;
import edu.duke.ece651.project.team5.shared.model.Student;
import javafx.application.Platform;
import javafx.scene.control.Button;

public class StudentEnrollmentFXTest extends ApplicationTest {
  private Dao<Student> studentDao = Mockito.mock(StudentDao.class);
  private Dao<Section> sectionDao = Mockito.mock(SectionDao.class);

  @Test
  public void testEditEnrollment() {
  }
}
