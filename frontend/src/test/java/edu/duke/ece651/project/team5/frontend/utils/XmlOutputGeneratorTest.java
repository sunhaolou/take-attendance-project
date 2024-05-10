// package edu.duke.ece651.project.team5.frontend.utils;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.atLeastOnce;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.Arrays;
// import java.util.HashMap;

// import javax.xml.parsers.DocumentBuilder;
// import javax.xml.parsers.DocumentBuilderFactory;
// import javax.xml.transform.Transformer;
// import javax.xml.transform.dom.DOMSource;
// import javax.xml.transform.stream.StreamResult;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockedStatic;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.w3c.dom.Document;
// import org.w3c.dom.Element;
// import edu.duke.ece651.project.team5.shared.model.SectionReport;
// import edu.duke.ece651.project.team5.shared.model.SessionReport;
// import edu.duke.ece651.project.team5.shared.model.StudentReport;

// public class XmlOutputGeneratorTest {

//     @Mock
//     private DocumentBuilder mockDocumentBuilder;
//     @Mock
//     private Document mockDocument;
//     @Mock
//     private Element mockElement;
//     @Mock
//     private Transformer mockTransformer;

//     @InjectMocks
//     private XmlOutputGenerator xmlOutputGenerator;

//     @BeforeEach
//     public void setUp() throws Exception {
//         MockitoAnnotations.openMocks(this);

//         // Mock static methods
//         try (MockedStatic<DocumentBuilderFactory> mocked = Mockito.mockStatic(DocumentBuilderFactory.class)) {
//             DocumentBuilderFactory mockFactory = mock(DocumentBuilderFactory.class);
//             when(mockFactory.newDocumentBuilder()).thenReturn(mockDocumentBuilder);
//             mocked.when(DocumentBuilderFactory::newInstance).thenReturn(mockFactory);
//         }
//     }

//     @Test
//     public void testExportAttendanceForSection() throws Exception {
//         SectionReport sectionReport = new SectionReport();
//         sectionReport.setAttendance(new HashMap<String, String>() {
//             {
//                 put("student1", "Present");
//                 put("student2", "Absent");
//             }
//         });

//         xmlOutputGenerator.exportAttendanceForSection(sectionReport);

//         verify(mockDocument, atLeastOnce()).createElement("SectionAttendance");
//         verify(mockTransformer).transform(any(DOMSource.class), any(StreamResult.class));
//     }

//     @Test
//     public void testExportAttendanceForStudents() throws Exception {
//         StudentReport studentReport = new StudentReport();
//         studentReport.setReports(Arrays.asList(new SessionReport()));
//         studentReport.setScore("95");

//         xmlOutputGenerator.exportAttendanceForStudents(studentReport);

//         verify(mockDocument, atLeastOnce()).createElement("StudentAttendance");
//         verify(mockTransformer).transform(any(DOMSource.class), any(StreamResult.class));
//     }
// }
