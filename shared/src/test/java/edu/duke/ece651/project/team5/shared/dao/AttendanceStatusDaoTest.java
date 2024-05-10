// package edu.duke.ece651.project.team5.shared.dao;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.sql.SQLException;
// import java.util.Optional;

// import org.junit.jupiter.api.Test;

// import edu.duke.ece651.project.team5.shared.enums.AttendanceStatus;

// public class AttendanceStatusDaoTest {
//   AttendanceStatusDao attendanceStatusDao = new AttendanceStatusDao();

//   @Test
//   public void testGet() throws SQLException {
//     Optional<AttendanceStatus> retrievedStatusOptional = attendanceStatusDao.get("1");
//     assertTrue(retrievedStatusOptional.isPresent());
//     AttendanceStatus retrievedStatus = retrievedStatusOptional.get();
//     assertEquals(AttendanceStatus.PRESENT, retrievedStatus);
//   }

//   /*
//   @Test
//   public void testAddAndUpdateAndDelete() throws SQLException {
//     AttendanceStatus newStatus = AttendanceStatus.PRESENT;

//     // Add new status
//     attendanceStatusDao.add(newStatus);

//     // Retrieve added status
//     Optional<AttendanceStatus> retrievedStatusOptional = attendanceStatusDao.get(String.valueOf(newStatus.getId()));
//     assertTrue(retrievedStatusOptional.isPresent());
//     AttendanceStatus retrievedStatus = retrievedStatusOptional.get();
//     assertEquals(newStatus, retrievedStatus);

//     // Update added status
//     AttendanceStatus updatedStatus = AttendanceStatus.ABSENT;
//     updatedStatus.setID(retrievedStatus.getId()); // Ensure ID is set correctly
//     attendanceStatusDao.update(updatedStatus);

//     // Retrieve updated status
//     retrievedStatusOptional = attendanceStatusDao.get(String.valueOf(updatedStatus.getId()));
//     assertTrue(retrievedStatusOptional.isPresent());
//     retrievedStatus = retrievedStatusOptional.get();
//     assertEquals(updatedStatus, retrievedStatus);

//     // Delete added status
//     attendanceStatusDao.delete(updatedStatus);

//     // Ensure status is deleted
//     retrievedStatusOptional = attendanceStatusDao.get(String.valueOf(updatedStatus.getId()));
//     assertFalse(retrievedStatusOptional.isPresent());
//   }*/

// }
