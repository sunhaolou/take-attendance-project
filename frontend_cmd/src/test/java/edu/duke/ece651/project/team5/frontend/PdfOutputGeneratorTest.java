package edu.duke.ece651.project.team5.frontend;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import edu.duke.ece651.project.team5.frontend.model.SessionReport;
import edu.duke.ece651.project.team5.frontend.model.StudentReport;

public class PdfOutputGeneratorTest {

  @Test
  public void testExportAttendanceForProfessor() {
    String response = "{\"attendance\":{\"netId1\":\"present\",\"netId2\":\"absent\"}}";
    PdfOutputGenerator generator = new PdfOutputGenerator();
    try {
      generator.exportAttendanceForProfessor(response);
    } catch (Exception e) {
      e.printStackTrace();
    }
    java.io.File file = new java.io.File("attendance_report.pdf");
    file.delete();
  }

  @Test
  public void testExportAttendanceForStudents() {
    List<SessionReport> sessionReports = new ArrayList<>();
    sessionReports.add(new SessionReport());
    sessionReports.add(new SessionReport());
    StudentReport studentReport = new StudentReport(sessionReports, "A");
    PdfOutputGenerator generator = new PdfOutputGenerator();
    try {
      generator.exportAttendanceForStudents(studentReport);
    } catch (Exception e) {
      e.printStackTrace();
    }
    java.io.File file = new java.io.File("attendance_report.pdf");
    file.delete();
  }
}
