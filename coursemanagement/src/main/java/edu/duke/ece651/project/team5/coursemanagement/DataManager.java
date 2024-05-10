package edu.duke.ece651.project.team5.coursemanagement;

import java.sql.Timestamp;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Callback;
import java.util.Optional;
import org.bouncycastle.eac.EACException;
import org.checkerframework.checker.units.qual.A;
import org.controlsfx.control.CheckComboBox;

import edu.duke.ece651.project.team5.shared.dao.*;
import edu.duke.ece651.project.team5.shared.model.*;
import edu.duke.ece651.project.team5.shared.enums.*;
import edu.duke.ece651.project.team5.admin.*;
import javafx.scene.Scene;

public class DataManager {
    public static void changeInstructor(String courseSectionId, Professor professor, SectionDao sectionDao) {
        try {
            Section section = sectionDao.get(courseSectionId).get();
            section.setProfessors(new ArrayList<>(Collections.singletonList(professor)));
            sectionDao.update(section);
            AlertBox.display("Success", "Successfully changed the instructor.");
        } catch (Exception e) {
            System.out.println("Error in changing instructor: " + e.getMessage());
            AlertBox.display("Error", "Error in changing instructor: " + e.getMessage());
        }
    }

    public static List<Course> getCourses(CourseDao courseDao) {
        try {
            return courseDao.getAll();
        } catch (Exception e) {
            System.out.println("Error in getting sections: " + e.getMessage());
            AlertBox.display("Error", "Error in getting sections: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Professor> getProfessors(ProfessorDao professorDao) {
        try {
            return professorDao.getAll();
        } catch (Exception e) {
            System.out.println("Error in getting professors: " + e.getMessage());
            AlertBox.display("Error", "Error in getting professors: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Student> getStudents(StudentDao studentDao) {
        try {
            return studentDao.getAll();
        } catch (Exception e) {
            System.out.println("Error in getting students: " + e.getMessage());
            AlertBox.display("Error", "Error in getting students: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void createCourseByFile(String filePath, CourseDao courseDao) throws Exception {
        CsvInputProcessor csvInputProcessor = new CsvInputProcessor();
        csvInputProcessor.parseRosterCsvForSession(filePath);
        List<Course> courses = csvInputProcessor.getCourses();
        for (Course course : courses) {
            try {
                courseDao.add(course);
            } catch (Exception e) {
                System.out.println("Error in adding course: " + e.getMessage());
                throw e;
            }
        }

    }

    public static void createCourseManually(String courseId, String courseDiscription, Professor professor)
            throws Exception {
        CourseDao courseDao = new CourseDao();
        Course newCourse = new Course();
        SectionDao sectionDao = new SectionDao();
        try {
            newCourse.setCourseId(courseId);
            newCourse.setCourseName(courseDiscription);
            courseDao.add(newCourse);
            System.out.println("Course created successfully. (before section)");
            newCourse.getSections().add(SectionCreator.addSectionScene(courseId, professor));
            for (Section section : newCourse.getSections()) {
                sectionDao.update(section);
            }
            courseDao.update(newCourse);
            System.out.println("Course created successfully. (after section)");
            AlertBox.display("Success", "Successfully added the course to the database.");

        } catch (Exception e) {
            System.out.println("Error in adding course: " + e.getMessage());
            throw e;
        }
    }

    public static void deleteSection(String courseSectionId, SectionDao sectionDao) {
        try {
            Section sectionBeDeleted = sectionDao.get(courseSectionId).get();
            sectionDao.delete(sectionBeDeleted);
            AlertBox.display("Success", "Successfully deleted the section from the database.");
        } catch (Exception e) {
            System.out.println("Error in deleting course: " + e.getMessage());
            AlertBox.display("Error", "Error in deleting course: " + e.getMessage());
        }
    }

    public static void deleteCourse(String courseSectionId, CourseDao courseDao) {
        try {
            String[] ids = courseSectionId.split("_", 2);
            String courseId = ids[0];
            Course courseBeDeleted = courseDao.get(courseId).get();
            courseDao.delete(courseBeDeleted);
            AlertBox.display("Success", "Successfully deleted the course from the database.");
        } catch (Exception e) {
            System.out.println("Error in deleting course: " + e.getMessage());
            AlertBox.display("Error", "Error in deleting course: " + e.getMessage());
        }
    }
    public static Boolean checkSuperUserExists(String userName, String password, SuperUserDao superUserDao) {
        Optional<Admin> admin = superUserDao.get(userName);
        if (admin.isPresent()) {
            return admin.get().getPassword().equals(password);
        }
        return false;
    }
}

