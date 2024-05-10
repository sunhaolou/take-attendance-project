package edu.duke.ece651.project.team5.frontend;

import java.util.Map;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import edu.duke.ece651.project.team5.frontend.model.SectionReport;
import edu.duke.ece651.project.team5.frontend.model.SessionReport;
import edu.duke.ece651.project.team5.frontend.model.StudentReport;

import java.io.FileNotFoundException;

/**
 * The PdfOutputGenerator class provides methods to export attendance data to PDF format.
 */
public class PdfOutputGenerator implements OutputGenerator {

  /**
   * Parses the response string to retrieve attendance data.
   *
   * @param response The response containing attendance data in JSON format.
   * @return A SectionReport object containing the parsed attendance data.
   * @throws JsonProcessingException If there is an error processing the JSON.
   */
  private SectionReport getReportListFromResponse(String response) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    SectionReport reports = objectMapper.readValue(response, new TypeReference<SectionReport>() {});
    return reports;
  }

  /**
   * Exports attendance data for a professor to a PDF file.
   *
   * @param response The attendance data received as a JSON string.
   * @throws JsonProcessingException If there is an error processing the JSON.
   */
  @Override
  public void exportAttendanceForProfessor(String response) throws JsonProcessingException {
    Map<String, String> attendance = getReportListFromResponse(response).getAttendance();
    try {
      PdfWriter writer = new PdfWriter("attendance_report.pdf");
      PdfDocument pdfDocument = new PdfDocument(writer);
      Document document = new Document(pdfDocument);
      for (Map.Entry<String, String> entry : attendance.entrySet()) {
        document.add(new Paragraph("netId: " + entry.getKey() + ", score: " + entry.getValue()));
      }
      document.close();

      System.out.println("PDF created successfully.");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Exports attendance data for a student to a PDF file.
   *
   * @param studentReport The attendance report for a student.
   */
  @Override
  public void exportAttendanceForStudents(StudentReport studentReport) {
    try {
      List<SessionReport> record = studentReport.getReports();
      PdfWriter writer = new PdfWriter("attendance_report.pdf");
      PdfDocument pdfDocument = new PdfDocument(writer);
      Document document = new Document(pdfDocument);
      for (SessionReport r : record) {
        document.add(new Paragraph("time: " + r.getTime() + ", status: " + r.getStatus()));
      }
      document.add(new Paragraph("score: " + studentReport.getScore()));
      document.close();

      System.out.println("PDF created successfully.");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
