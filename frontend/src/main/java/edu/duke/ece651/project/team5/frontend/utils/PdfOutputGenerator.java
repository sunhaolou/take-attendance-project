package edu.duke.ece651.project.team5.frontend.utils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;

import edu.duke.ece651.project.team5.shared.model.*;

/**
 * The PdfOutputGenerator class provides methods to export attendance data to
 * PDF format.
 */
@Component
public class PdfOutputGenerator implements OutputGenerator {

  

    /**
     * Exports attendance data for a professor to a PDF file.
     *
     * @param response The attendance data received as a JSON string.
     * @throws JsonProcessingException If there is an error processing the JSON.
     */
    @Override
    public void exportAttendanceForSection(SectionReport sectionReport) throws JsonProcessingException {

        try {
            Map<String, String> attendance = sectionReport.getAttendance();
            PdfWriter writer = new PdfWriter("section_attendance_report.pdf");
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
            PdfWriter writer = new PdfWriter("student_attendance_report.pdf");
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
