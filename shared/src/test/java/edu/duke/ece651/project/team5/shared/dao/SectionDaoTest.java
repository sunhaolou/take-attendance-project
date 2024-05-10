package edu.duke.ece651.project.team5.shared.dao;

// import static org.junit.jupiter.api.Assertions.*;

// import java.sql.*;
// import java.util.*;
// import org.junit.jupiter.api.Test;

// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
// import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;
// import edu.duke.ece651.project.team5.shared.model.Course;
// import edu.duke.ece651.project.team5.shared.model.Lecture;
// import edu.duke.ece651.project.team5.shared.model.Section;
// import edu.duke.ece651.project.team5.shared.model.Session;

public class SectionDaoTest {
    /*DatabaseInitializer db = new DatabaseInitializer();
    
    @Test
    public void testAddAndGet() throws SQLException {
        db.dropAllTables();
        db.initialize();  
        SectionDao dao = new SectionDao();
        List<Session> sessions = new ArrayList<>();
        sessions.add(new Lecture("lecture123", "1", Timestamp.valueOf("2024-04-08 16:51:24")));
        Section section = new Section("1", "CS101", "CS101-001", sessions);
        Map<String, EnrollmentStatus> enrollment = new HashMap<>();
        enrollment.put("student123", EnrollmentStatus.ENROLLED);
        section.setEnrollment(enrollment);
        dao.add(section);
        Optional<Section> retrievedSection = dao.get("1");
        assertTrue(retrievedSection.isPresent());
        assertEquals(section.getCourseSectionId(), retrievedSection.get().getCourseSectionId());
        assertEquals(section.getCourseId(), retrievedSection.get().getCourseId());
        assertEquals(section.getSectionId(), retrievedSection.get().getSectionId());
        assertEquals(section.getSessions().size(), retrievedSection.get().getSessions().size());
        assertEquals(section.getEnrollment(), retrievedSection.get().getEnrollment());
        dao.delete(section);
    }
    
    @Test
    public void testUpdate() throws SQLException {
        db.dropAllTables();
        db.initialize();
        SectionDao dao = new SectionDao();
        List<Session> sessions = new ArrayList<>();
        sessions.add(new Lecture("lecture123", "1", Timestamp.valueOf("2024-04-08 16:51:24")));
        Section section = new Section("1", "CS101", "CS101-001", sessions);
        Map<String, EnrollmentStatus> updatedEnrollment = new HashMap<>();
        updatedEnrollment.put("student456", EnrollmentStatus.DROPPED);
        section.setEnrollment(updatedEnrollment);
        dao.add(section);
        section.setCourseId("CS102");
        dao.update(section);
        Optional<Section> updatedSection = dao.get("1");
        assertTrue(updatedSection.isPresent());
        assertEquals(section.getCourseSectionId(), updatedSection.get().getCourseSectionId());
        assertEquals(section.getCourseId(), updatedSection.get().getCourseId());
        assertEquals(section.getSectionId(), updatedSection.get().getSectionId());
        assertEquals(section.getSessions().size(), updatedSection.get().getSessions().size());
        assertEquals(section.getEnrollment(), updatedSection.get().getEnrollment());
        dao.delete(section);
    }
    
    @Test
    public void testDelete() throws SQLException {
        db.dropAllTables();
        db.initialize();
        SectionDao dao = new SectionDao();
        List<Session> sessions = new ArrayList<>();
        sessions.add(new Lecture("lecture123", "1", Timestamp.valueOf("2024-04-08 16:51:24")));
        Section section = new Section("1", "CS101", "CS101-001", sessions);
        dao.add(section);
        dao.delete(section);
        Optional<Section> deletedSection = dao.get("1");
        assertFalse(deletedSection.isPresent());
    }*/
}
