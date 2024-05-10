// package edu.duke.ece651.project.team5.shared.dao;

// import static org.junit.jupiter.api.Assertions.*;

// import java.sql.SQLException;
// import java.util.*;
// import org.junit.jupiter.api.Test;

// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
// import edu.duke.ece651.project.team5.shared.enums.*;
// import edu.duke.ece651.project.team5.shared.model.*;

// public class AttendanceRecordDaoTest {
//     DatabaseInitializer dbInitializer = new DatabaseInitializer();

//     @Test
//     public void testAddAndGet() throws SQLException {
//         AttendanceRecordDao dao = new AttendanceRecordDao();
//         AttendanceRecord recordToAdd = new AttendanceRecord(100000, "steventest", "st146", AttendanceStatus.PRESENT);
        
//         dao.add(recordToAdd);
        
//         AttendanceRecord retrievedRecord = dao.get("100000").orElse(null);
        
//         assertNotNull(retrievedRecord);
//         assertEquals(recordToAdd.getAttendanceRecordId(), retrievedRecord.getAttendanceRecordId());
//         assertEquals(recordToAdd.getLectureId(), retrievedRecord.getLectureId());
//         assertEquals(recordToAdd.getStudentNetId(), retrievedRecord.getStudentNetId());
//         assertEquals(recordToAdd.getAttendanceStatus(), retrievedRecord.getAttendanceStatus());
        
//         dao.delete(recordToAdd);
//     }
    
//     @Test
//     public void testUpdate() throws SQLException {
//         dbInitializer.initialize();
//         dbInitializer.truncateAttendanceRecord();
//         AttendanceRecordDao dao = new AttendanceRecordDao();
//         AttendanceRecord recordToUpdate = new AttendanceRecord(1, "lecture123", "student123", AttendanceStatus.TARDY);
        
//         dao.add(recordToUpdate);
        
//         recordToUpdate.setAttendanceStatus(AttendanceStatus.PRESENT);
//         dao.update(recordToUpdate);
        
//         AttendanceRecord updatedRecord = dao.get("1").orElse(null);
        
//         assertNotNull(updatedRecord);
//         assertEquals(recordToUpdate.getAttendanceStatus(), updatedRecord.getAttendanceStatus());
        
//         dao.delete(updatedRecord);
//     }
    
//     @Test
//     public void testGetRecordByLectureId() throws SQLException {
//         AttendanceRecordDao dao = new AttendanceRecordDao();
//         AttendanceRecord record1 = new AttendanceRecord(1, "lecture123", "student123", AttendanceStatus.PRESENT);
//         AttendanceRecord record2 = new AttendanceRecord(2, "lecture123", "student456", AttendanceStatus.ABSENT);
        
//         dao.add(record1);
//         dao.add(record2);
        
//         Map<String, AttendanceRecord> records = dao.getRecordByLectureId("lecture123");
        
//         assertNotNull(records);
//         assertEquals(2, records.size());
//         assertTrue(records.containsKey("student123"));
//         assertTrue(records.containsKey("student456"));
        
//         dao.delete(record1);
//         dao.delete(record2);
//     }
    
//     @Test
//     public void testGetAttendanceRecordByStudentNetId() throws SQLException {
//         dbInitializer.initialize();
//         dbInitializer.truncateAttendanceRecord();
//         AttendanceRecordDao dao = new AttendanceRecordDao();
//         AttendanceRecord record1 = new AttendanceRecord(1, "lecture123", "student123", AttendanceStatus.PRESENT);
//         AttendanceRecord record2 = new AttendanceRecord(2, "lecture456", "student123", AttendanceStatus.PRESENT);
        
//         dao.add(record1);
//         dao.add(record2);
        
//         Map<String, AttendanceRecord> records = dao.getAttendanceRecordByStudentNetId("student123");
        
//         assertNotNull(records);
//         assertEquals(2, records.size());
//         assertTrue(records.containsKey("lecture123"));
//         assertTrue(records.containsKey("lecture456"));
        
//         dao.delete(record1);
//         dao.delete(record2);
//     }
// }
