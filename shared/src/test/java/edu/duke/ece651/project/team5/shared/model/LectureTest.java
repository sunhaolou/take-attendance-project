// package edu.duke.ece651.project.team5.shared.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.sql.Timestamp;
// import java.util.HashMap;
// import java.util.Map;

// import org.junit.jupiter.api.Test;

// import edu.duke.ece651.project.team5.shared.enums.AttendanceStatus;

// public class LectureTest {

//     @Test
//     public void testConstructorWithNoArgument() {
//         Lecture lecture = new Lecture();
//         assertEquals("lectureId", lecture.getLectureId());
//         assertEquals("courseSectionId", lecture.getCourseSectionId());
//         // Assuming the lecture is created at the current time
//         long currentTimeMillis = System.currentTimeMillis();
//         Timestamp expectedTime = new Timestamp(currentTimeMillis);
//         Timestamp actualTime = lecture.getTime();
//         // Since the creation time can vary slightly, we'll check if the time difference is less than 1000 milliseconds
//         long timeDifference = Math.abs(actualTime.getTime() - expectedTime.getTime());
//         // Check if the time difference is less than 1000 milliseconds
//         assertEquals(0, timeDifference, 1000);
//         assertEquals(new HashMap<>(), lecture.getAttendance());
//     }

//     @Test
//     public void testConstructorWithSessionId() {
//         String sessionId = "session123";
//         Lecture lecture = new Lecture(sessionId);
//         assertEquals(sessionId, lecture.getLectureId());
//         assertEquals(sessionId, lecture.getCourseSectionId());
//         // Assuming the lecture is created at the current time
//         long currentTimeMillis = System.currentTimeMillis();
//         Timestamp expectedTime = new Timestamp(currentTimeMillis);
//         Timestamp actualTime = lecture.getTime();
//         // Since the creation time can vary slightly, we'll check if the time difference is less than 1000 milliseconds
//         long timeDifference = Math.abs(actualTime.getTime() - expectedTime.getTime());
//         // Check if the time difference is less than 1000 milliseconds
//         assertEquals(0, timeDifference, 1000);
//         assertEquals(new HashMap<>(), lecture.getAttendance());
//     }

//     @Test
//     public void testConstructorWithArguments() {
//         String lectureId = "lecture123";
//         String courseSectionId = "CS101-001";
//         Timestamp time = new Timestamp(1648545320000L); // Example time
//         Map<String, AttendanceRecord> attendance = new HashMap<>();
//         attendance.put("student123", new AttendanceRecord(1, lectureId, "student123", AttendanceStatus.PRESENT));

//         Lecture lecture = new Lecture(lectureId, courseSectionId, time, attendance);
//         assertEquals(lectureId, lecture.getLectureId());
//         assertEquals(courseSectionId, lecture.getCourseSectionId());
//         assertEquals(time, lecture.getTime());
//         assertEquals(attendance, lecture.getAttendance());
//     }

//     @Test
//     public void testGetSetLectureId() {
//         Lecture lecture = new Lecture();
//         String newLectureId = "newLecture123";
//         lecture.setLectureId(newLectureId);
//         assertEquals(newLectureId, lecture.getLectureId());
//     }

// }
