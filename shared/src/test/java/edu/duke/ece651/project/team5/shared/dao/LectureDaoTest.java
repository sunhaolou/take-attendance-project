package edu.duke.ece651.project.team5.shared.dao;

// import static org.junit.jupiter.api.Assertions.*;

// import java.sql.SQLException;
// import java.sql.Timestamp;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

// import org.junit.jupiter.api.Test;

// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
// import edu.duke.ece651.project.team5.shared.enums.AttendanceStatus;
// import edu.duke.ece651.project.team5.shared.model.AttendanceRecord;
// import edu.duke.ece651.project.team5.shared.model.Lecture;
// import edu.duke.ece651.project.team5.shared.model.Session;

public class LectureDaoTest {
    /*DatabaseInitializer db = new DatabaseInitializer();
    @Test
    public void testAddAndGet() throws SQLException {
        db.dropAllTables();
        db.initialize();
        LectureDao dao = new LectureDao();
        Map<String, AttendanceRecord> attendance = new HashMap<>();
        attendance.put("student1", new AttendanceRecord(1, "lecture123", "student1", AttendanceStatus.PRESENT));
        attendance.put("student2", new AttendanceRecord(2, "lecture123", "student2", AttendanceStatus.ABSENT));
        Session lecture = new Lecture("lecture123", "CS101", Timestamp.valueOf("2024-04-08 16:51:24"), attendance);
        dao.add(lecture);
        Optional<Session> retrievedLecture = dao.get("lecture123");
        assertTrue(retrievedLecture.isPresent());
        assertEquals(lecture.getSessionId(), retrievedLecture.get().getSessionId());
        assertEquals(lecture.getCourseSectionId(), retrievedLecture.get().getCourseSectionId());
        assertEquals(lecture.getTime(), retrievedLecture.get().getTime());
        assertEquals(lecture.getAttendance().size(), retrievedLecture.get().getAttendance().size());
        dao.delete(lecture);
    }

    @Test
    public void testUpdate() throws SQLException {
        db.dropAllTables();
        db.initialize();
        LectureDao dao = new LectureDao();
        Map<String, AttendanceRecord> attendance = new HashMap<>();
        attendance.put("student1", new AttendanceRecord(1, "lecture123", "student1", AttendanceStatus.PRESENT));
        attendance.put("student2", new AttendanceRecord(2, "lecture123", "student2", AttendanceStatus.ABSENT));
        Session lecture = new Lecture("lecture123", "CS101", Timestamp.valueOf("2024-04-08 16:51:24"), attendance);
        dao.add(lecture);
        lecture.setCourseSectionId("CS102");
        dao.update(lecture);
        Optional<Session> updatedLecture = dao.get("lecture123");
        assertTrue(updatedLecture.isPresent());
        assertEquals(lecture.getSessionId(), updatedLecture.get().getSessionId());
        assertEquals(lecture.getCourseSectionId(), updatedLecture.get().getCourseSectionId());
        assertEquals(lecture.getTime(), updatedLecture.get().getTime());
        assertEquals(lecture.getAttendance().size(), updatedLecture.get().getAttendance().size());
        dao.delete(lecture);
    }

    @Test
    public void testDelete() throws SQLException {
        db.dropAllTables();
        db.initialize();
        LectureDao dao = new LectureDao();
        Map<String, AttendanceRecord> attendance = new HashMap<>();
        attendance.put("student1", new AttendanceRecord(1, "lecture123", "student1", AttendanceStatus.PRESENT));
        attendance.put("student2", new AttendanceRecord(2, "lecture123", "student2", AttendanceStatus.ABSENT));
        Session lecture = new Lecture("lecture123", "CS101", Timestamp.valueOf("2024-04-08 16:51:24"), attendance);
        dao.add(lecture);
        dao.delete(lecture);
        Optional<Session> deletedLecture = dao.get("lecture123");
        assertFalse(deletedLecture.isPresent());
    }

    @Test
    public void testGetByCourseSectionId() throws SQLException {
        db.dropAllTables();
        db.initialize();
        LectureDao lectureDao = new LectureDao();
        Session lecture1 = new Lecture();
        lecture1.setSessionId("L1");
        lecture1.setCourseSectionId("C1");
        lecture1.setTime(new Timestamp(System.currentTimeMillis()));
        
//         Session lecture2 = new Lecture();
//         lecture2.setSessionId("L2");
//         lecture2.setCourseSectionId("C1");
//         lecture2.setTime(new Timestamp(System.currentTimeMillis()));

//         // Add lectures to database
//         lectureDao.add(lecture1);
//         lectureDao.add(lecture2);

//         // Retrieve lectures by course section ID
//         List<Session> retrievedLectures = lectureDao.getByCourseSectionId("C1");
//         assertEquals(2, retrievedLectures.size());

        // Clean up
        lectureDao.delete(lecture1);
        lectureDao.delete(lecture2);
    }*/
}
