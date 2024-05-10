package edu.duke.ece651.project.team5.shared.dao;

// // import static org.junit.jupiter.api.Assertions.*;

// // import java.sql.*;
// // import java.util.*;

// // import org.junit.jupiter.api.Test;

// // import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
// // import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;

public class SectionEnrollmentDaoTest {
    /*SectionEnrollmentDao sectionEnrollmentDao = new SectionEnrollmentDao();
    DatabaseInitializer initializer = new DatabaseInitializer();
    @Test
    public void testAddAndGet() throws SQLException {
        initializer.initialize();
        Map<String, EnrollmentStatus> enrollment = new HashMap<>();
        enrollment.put("student123", EnrollmentStatus.ENROLLED);
        enrollment.put("student456", EnrollmentStatus.DROPPED);

// //         sectionEnrollmentDao.add(enrollment, "CS101-001");

// //         Optional<Map<String, EnrollmentStatus>> retrievedEnrollmentOptional = sectionEnrollmentDao.get("CS101-001");
// //         assertTrue(retrievedEnrollmentOptional.isPresent());
// //         Map<String, EnrollmentStatus> retrievedEnrollment = retrievedEnrollmentOptional.get();
// //         assertEquals(2, retrievedEnrollment.size());
// //         assertEquals(EnrollmentStatus.ENROLLED, retrievedEnrollment.get("student123"));
// //         assertEquals(EnrollmentStatus.DROPPED, retrievedEnrollment.get("student456"));

// //         sectionEnrollmentDao.delete("CS101-001");
// //     }

// //     @Test
// //     public void testUpdate() throws SQLException {
// //         initializer.initialize();
// //         Map<String, EnrollmentStatus> enrollment = new HashMap<>();
// //         enrollment.put("student123", EnrollmentStatus.ENROLLED);

// //         sectionEnrollmentDao.add(enrollment, "CS101-001");

// //         Map<String, EnrollmentStatus> updatedEnrollment = new HashMap<>();
// //         updatedEnrollment.put("student123", EnrollmentStatus.DROPPED);
// //         sectionEnrollmentDao.update(updatedEnrollment, "CS101-001");

// //         Optional<Map<String, EnrollmentStatus>> retrievedEnrollmentOptional = sectionEnrollmentDao.get("CS101-001");
// //         assertTrue(retrievedEnrollmentOptional.isPresent());
// //         Map<String, EnrollmentStatus> retrievedEnrollment = retrievedEnrollmentOptional.get();
// //         assertEquals(1, retrievedEnrollment.size());
// //         assertEquals(EnrollmentStatus.DROPPED, retrievedEnrollment.get("student123"));

// //         sectionEnrollmentDao.delete("CS101-001");
// //     }

// //     @Test
// //     public void testDelete() throws SQLException {
// //         initializer.initialize();
// //         Map<String, EnrollmentStatus> enrollment = new HashMap<>();
// //         enrollment.put("student123", EnrollmentStatus.ENROLLED);

// //         sectionEnrollmentDao.add(enrollment, "CS101-001");

// //         sectionEnrollmentDao.delete("CS101-001");

// //         Optional<Map<String, EnrollmentStatus>> retrievedEnrollmentOptional = sectionEnrollmentDao.get("CS101-001");
// //         assertFalse(retrievedEnrollmentOptional.isPresent());
// //     }

//     @Test
// public void testGetEnrollmentStatusByStudentNetId() throws SQLException {
//     initializer.initialize();
//     // Add some enrollments
//     Map<String, EnrollmentStatus> enrollment1 = new HashMap<>();
//     enrollment1.put("student123", EnrollmentStatus.ENROLLED);
//     enrollment1.put("student456", EnrollmentStatus.DROPPED);
//     sectionEnrollmentDao.add(enrollment1, "CS101-001");
    
//     Map<String, EnrollmentStatus> enrollment2 = new HashMap<>();
//     enrollment2.put("student123", EnrollmentStatus.ENROLLED);
//     sectionEnrollmentDao.add(enrollment2, "CS102-001");
    
//     // Test for existing student with enrollments
//     Optional<Map<String, EnrollmentStatus>> retrievedEnrollmentOptional = sectionEnrollmentDao.getEnrollmentStatusByStudentNetId("student123");
//     assertTrue(retrievedEnrollmentOptional.isPresent());
//     Map<String, EnrollmentStatus> retrievedEnrollment = retrievedEnrollmentOptional.get();
//     assertEquals(2, retrievedEnrollment.size());
//     assertEquals(EnrollmentStatus.ENROLLED, retrievedEnrollment.get("CS101-001"));
//     assertEquals(EnrollmentStatus.ENROLLED, retrievedEnrollment.get("CS102-001"));
    
//     // Test for existing student without enrollments
//     retrievedEnrollmentOptional = sectionEnrollmentDao.getEnrollmentStatusByStudentNetId("student456");
//     assertTrue(retrievedEnrollmentOptional.isPresent());
//     retrievedEnrollment = retrievedEnrollmentOptional.get();
//     assertEquals(1, retrievedEnrollment.size());
//     assertEquals(EnrollmentStatus.DROPPED, retrievedEnrollment.get("CS101-001"));
    
    // Test for non-existing student
    retrievedEnrollmentOptional = sectionEnrollmentDao.getEnrollmentStatusByStudentNetId("student789");
    assertFalse(retrievedEnrollmentOptional.isPresent());
}*/


}
