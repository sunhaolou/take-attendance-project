package edu.duke.ece651.project.team5.shared.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
public class CourseTest {
    @Test
    public void testSetCourseId() {
        Course course = new Course();
        course.setCourseId("C1");
        assertEquals(course.getCourseId(),"C1");
    }

    @Test
    public void testSetCourseName() {
        Course course = new Course("ECE651", "SE");
        course.setCourseName("Math");
        assertEquals(course.getCourseName(),"Math");
    }

    @Test
    public void testConstructorAndGetters() {
        String courseId = "CS101";
        String courseName = "Introduction to Computer Science";
        List<Section> sections = new ArrayList<>();

        Course course = new Course(courseId, courseName, sections);

        assertEquals(courseId, course.getCourseId());
        assertEquals(courseName, course.getCourseName());
        assertEquals(sections, course.getSections());
    }

    @Test
    public void testSetters() {
        String courseId = "CS101";
        String courseName = "Introduction to Computer Science";
        List<Section> sections = new ArrayList<>();

        Course course = new Course();

        course.setCourseId(courseId);
        course.setCourseName(courseName);
        course.setSections(sections);

        assertEquals(courseId, course.getCourseId());
        assertEquals(courseName, course.getCourseName());
        assertEquals(sections, course.getSections());
    }

    @Test
    public void testAddSection() {
        String courseId = "CS101";
        String courseName = "Introduction to Computer Science";
        Section section = new Section("section123", "CS101", "CS101-001");

        Course course = new Course(courseId, courseName);

        course.addSection(section);

        assertTrue(course.getSections().contains(section));
    }
}
