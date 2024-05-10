package edu.duke.ece651.project.team5.frontend.utils;

import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import edu.duke.ece651.project.team5.shared.model.SectionReport;
import edu.duke.ece651.project.team5.shared.model.StudentReport;
import edu.duke.ece651.project.team5.shared.model.SessionReport;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@PrepareForTest({ PdfOutputGenerator.class, PdfWriter.class, PdfDocument.class, Document.class })
public class PdfOutputGeneratorTest {

    @Mock
    private PdfWriter pdfWriter;
    @Mock
    private PdfDocument pdfDocument;
    @Mock
    private Document document;

    @InjectMocks
    private PdfOutputGenerator pdfOutputGenerator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExportAttendanceForSection() throws Exception {
        SectionReport sectionReport = new SectionReport();
        sectionReport.setAttendance(new HashMap<String, String>() {
            {
                put("student1", "Present");
                put("student2", "Absent");
            }
        });

        // Replace constructor creation with mocks
        whenNew(PdfWriter.class).withAnyArguments().thenReturn(pdfWriter);
        whenNew(PdfDocument.class).withAnyArguments().thenReturn(pdfDocument);
        whenNew(Document.class).withAnyArguments().thenReturn(document);

        pdfOutputGenerator.exportAttendanceForSection(sectionReport);

        // Verify document interactions
        // verify(document, times(2)).add(any(Paragraph.class)); // Expect two
        // paragraphs
        // verify(document).close();
    }

    @Test
    public void testExportAttendanceForStudents() throws Exception {
        StudentReport studentReport = new StudentReport();
        List<SessionReport> sessions = new ArrayList<>();

        sessions.add(new SessionReport());
        sessions.add(new SessionReport());
        studentReport.setReports(sessions);
        studentReport.setScore("95");

        // Replace constructor creation with mocks
        whenNew(PdfWriter.class).withAnyArguments().thenReturn(pdfWriter);
        whenNew(PdfDocument.class).withAnyArguments().thenReturn(pdfDocument);
        whenNew(Document.class).withAnyArguments().thenReturn(document);

        pdfOutputGenerator.exportAttendanceForStudents(studentReport);

        // Verify document interactions

    }
}
