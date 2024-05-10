package edu.duke.ece651.project.team5.frontend.utils;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Map;
import edu.duke.ece651.project.team5.shared.model.SectionReport;
import edu.duke.ece651.project.team5.shared.model.StudentReport;
import edu.duke.ece651.project.team5.shared.model.SessionReport;

@Component
public class XmlOutputGenerator implements OutputGenerator {

    /**
     * Exports attendance data for a section to an XML file.
     *
     * @param sectionReport The attendance report for a section.
     */
    @Override
    public void exportAttendanceForSection(SectionReport sectionReport) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Root element
            Element root = document.createElement("SectionAttendance");
            document.appendChild(root);

            // Iterate through attendance data and create XML elements
            for (Map.Entry<String, String> entry : sectionReport.getAttendance().entrySet()) {
                Element attendance = document.createElement("Attendance");
                attendance.setAttribute("netId", entry.getKey());
                attendance.appendChild(document.createTextNode(entry.getValue()));
                root.appendChild(attendance);
            }

            // Write the content into XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("section_attendance_report.xml"));

            transformer.transform(domSource, streamResult);

            System.out.println("XML file created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exports attendance data for a student to an XML file.
     *
     * @param studentReport The attendance report for a student.
     */
    @Override
    public void exportAttendanceForStudents(StudentReport studentReport) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Root element
            Element root = document.createElement("StudentAttendance");
            document.appendChild(root);

            // Student's overall score
            Element score = document.createElement("Score");
            score.appendChild(document.createTextNode(String.valueOf(studentReport.getScore())));
            root.appendChild(score);

            // Attendance records
            for (SessionReport record : studentReport.getReports()) {
                Element session = document.createElement("Session");
                session.setAttribute("time", record.getTime());
                session.appendChild(document.createTextNode(record.getStatus()));
                root.appendChild(session);
            }

            // Write the content into XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("student_attendance_report.xml"));

            transformer.transform(domSource, streamResult);

            System.out.println("XML file created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
