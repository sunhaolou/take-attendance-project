package edu.duke.ece651.project.team5.frontend;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.project.team5.frontend.model.StudentReport;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonOutputGeneratorTest {

    @Test
    public void testExportAttendanceForProfessor() {
        String response = "{\"name\": \"John\", \"attendance\": \"present\"}";
        JsonOutputGenerator generator = new JsonOutputGenerator();
        try {
            generator.exportAttendanceForProfessor(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String content = null;
        try {
            content = Files.readString(Paths.get("attendance_report.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File("attendance_report.json");
        file.delete();
    }

    @Test
    public void testExportAttendanceForStudents() {
        StudentReport studentReport = new StudentReport();
        JsonOutputGenerator generator = new JsonOutputGenerator();
        try {
            generator.exportAttendanceForStudents(studentReport);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String content = null;
        try {
            content = Files.readString(Paths.get("attendance_report.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File("attendance_report.json");
        file.delete();
    }
}
