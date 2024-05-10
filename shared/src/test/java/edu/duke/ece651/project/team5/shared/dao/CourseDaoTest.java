// package edu.duke.ece651.project.team5.shared.dao;

// import static org.junit.jupiter.api.Assertions.*;

// import java.sql.*;
// import java.util.*;

// import org.junit.jupiter.api.Test;

// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
// import edu.duke.ece651.project.team5.shared.enums.AttendanceStatus;
// import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;
// import edu.duke.ece651.project.team5.shared.model.*;

// public class CourseDaoTest {
//     DatabaseInitializer db = new DatabaseInitializer();

//     @Test
//     void test_wholeProcess3() throws SQLException {
//         // This test simulates the entire process of admin registering professors and
//         // students,
//         // and then course management registering courses.

//         // 1. database initialization
//         //db.dropAllTables();
//         //db.initialize();
//         // 2. professor registration

//         ProfessorDao professorDao = new ProfessorDao();

//         Professor afsaneh = new Professor("ar123", "afsaneh", "ar123@duke.edu", "123456", "123456");
//         Professor javier = new Professor("hp123", "javier", "hp123@duke.edu", "1234567", "123456");
//         try {

//             professorDao.add(afsaneh);
//             professorDao.add(javier);
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//         // 3. student registration
//         StudentDao studentDao = new StudentDao();
//         Student steven = new Student("hg161", "12345", "geng", "steven", "test1@duke.edu", "185");
//         Student hunter = new Student("zs149", "123456", "shen", "hunter", "test2@duke.edu", "186");
//         Student yuyu = new Student("yh384", "1234567", "hsieh", "yuyu", "test3@duke.edu", "187");
//         Student Haolou = new Student("hs392", "12345678", "sun", "haolou", "test4@duke.edu", "188");
//         Student hiep = new Student("hhn3", "123456789", "nguyen", "hiep", "test5@duke.edu", "189");
//         try {

//             studentDao.add(steven);
//             studentDao.add(hunter);
//             studentDao.add(yuyu);
//             studentDao.add(Haolou);
//             studentDao.add(hiep);
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//         // 4. course registration
//         CourseDao courseDao = new CourseDao();

//         List<Professor> ECE156_1_professors = new ArrayList<>(Arrays.asList(afsaneh));
//         List<Professor> ECE156_2_professors = new ArrayList<>(Arrays.asList(javier));
//         Session ECE156_1_001 = new Lecture("001", "ECE156_1", Timestamp.valueOf("2024-04-15 12:00:00"));
//         Session ECE156_1_002 = new Lecture("002", "ECE156_1", Timestamp.valueOf("2024-04-16 12:00:00"));
//         Session ECE156_2_001 = new Lecture("003", "ECE156_2", Timestamp.valueOf("2024-04-15 12:00:00"));
//         Session ECE156_2_002 = new Lecture("004", "ECE156_2", Timestamp.valueOf("2024-04-16 12:00:00"));
//         List<Session> ECE156_1_sessions = new ArrayList<>(Arrays.asList(ECE156_1_001, ECE156_1_002));
//         List<Session> ECE156_2_sessions = new ArrayList<>(Arrays.asList(ECE156_2_001, ECE156_2_002));
//         Map<String, EnrollmentStatus> ECE156_1_enrollment = new HashMap<>();
//         Map<String, EnrollmentStatus> ECE156_2_enrollment = new HashMap<>();
//         ECE156_1_enrollment.put("hg161", EnrollmentStatus.ENROLLED);
//         ECE156_1_enrollment.put("zs149", EnrollmentStatus.ENROLLED);
//         ECE156_2_enrollment.put("yh384", EnrollmentStatus.ENROLLED);
//         ECE156_2_enrollment.put("hs392", EnrollmentStatus.ENROLLED);
//         ECE156_2_enrollment.put("hhn3", EnrollmentStatus.ENROLLED);
//         ECE156_1_001.takeAttendance(hunter, AttendanceStatus.PRESENT);
//         ECE156_2_001.takeAttendance(yuyu, AttendanceStatus.PRESENT);
//         ECE156_2_001.takeAttendance(Haolou, AttendanceStatus.PRESENT);
//         ECE156_2_001.takeAttendance(hiep, AttendanceStatus.PRESENT);
//         ECE156_2_001.takeAttendance(hiep, AttendanceStatus.ABSENT);
//         Section ECE156_1 = new Section("ECE156_1", "ECE156", "1", ECE156_1_professors, ECE156_1_sessions,
//                 ECE156_1_enrollment);
//         Section ECE156_2 = new Section("ECE156_2", "ECE156", "2", ECE156_2_professors, ECE156_2_sessions,
//                 ECE156_2_enrollment);
//         Course ECE156 = new Course("ECE156", "Software Engineering",
//                 new ArrayList<>(Arrays.asList(ECE156_1, ECE156_2)));
//         try {

//             courseDao.add(ECE156);
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }

//         // 5. test
//         // course
//         Course ECE156_retrieved = courseDao.get("ECE156").get();
//         assertEquals(ECE156.getCourseId(), ECE156_retrieved.getCourseId());
//         assertEquals(ECE156.getCourseName(), ECE156_retrieved.getCourseName());
//         assertEquals(ECE156.getSections().size(), ECE156_retrieved.getSections().size());
//         // sections
//         Section ECE156_1_retrieved = ECE156_retrieved.getSections().get(0);
//         Section ECE156_2_retrieved = ECE156_retrieved.getSections().get(1);
//         assertEquals(ECE156_1.getSectionId(), ECE156_1_retrieved.getSectionId());
//         assertEquals(ECE156_1.getCourseId(), ECE156_1_retrieved.getCourseId());
//         assertEquals(ECE156_1.getProfessors().size(), ECE156_1_retrieved.getProfessors().size());
//         assertEquals(ECE156_1.getSessions().size(), ECE156_1_retrieved.getSessions().size());
//         assertEquals(ECE156_1.getEnrollment().size(), ECE156_1_retrieved.getEnrollment().size());
//         assertEquals(ECE156_2.getSectionId(), ECE156_2_retrieved.getSectionId());
//         assertEquals(ECE156_2.getCourseId(), ECE156_2_retrieved.getCourseId());
//         assertEquals(ECE156_2.getProfessors().size(), ECE156_2_retrieved.getProfessors().size());
//         assertEquals(ECE156_2.getSessions().size(), ECE156_2_retrieved.getSessions().size());
//         assertEquals(ECE156_2.getEnrollment().size(), ECE156_2_retrieved.getEnrollment().size());
//         // lecture
//         assertEquals(AttendanceStatus.ABSENT, ECE156_2_001.getAttendanceOfStudent(hiep));
//         // students
//         assertEquals(ECE156_1.getEnrollment().get("hg161"), ECE156_1_retrieved.getEnrollment().get("hg161"));
//         assertEquals(ECE156_1.getEnrollment().get("zs149"), ECE156_1_retrieved.getEnrollment().get("zs149"));
//         assertEquals(ECE156_2.getEnrollment().get("yh384"), ECE156_2_retrieved.getEnrollment().get("yh384"));
//         assertEquals(ECE156_2.getEnrollment().get("hs392"), ECE156_2_retrieved.getEnrollment().get("hs392"));
//         assertEquals(ECE156_2.getEnrollment().get("hhn3"), ECE156_2_retrieved.getEnrollment().get("hhn3"));
//         // professors
//         assertEquals(ECE156_1.getProfessors().get(0).getNetId(), ECE156_1_retrieved.getProfessors().get(0).getNetId());
//         assertEquals(ECE156_2.getProfessors().get(0).getNetId(), ECE156_2_retrieved.getProfessors().get(0).getNetId());
//         // courseDao
//         courseDao.update(ECE156_retrieved);
//         // sectionDao
//         SectionDao sectionDao = new SectionDao();
//         List<Section> sections = sectionDao.getByProfessorId("ar123");
//         assertEquals("1", sections.get(0).getSectionId());
//         Section section_retrieved = sectionDao.get("ECE156_1").get();
//         section_retrieved.setSectionId("ECE156_1");
//         sectionDao.update(section_retrieved);
//         sectionDao.delete(section_retrieved);
//         sectionDao.add(section_retrieved);

//         // attendanceRecordDao
//         AttendanceRecordDao attendanceRecordDao = new AttendanceRecordDao();
//         AttendanceRecord attendanceRecord = new AttendanceRecord(100000, "001", "hg161", AttendanceStatus.PRESENT);
//         try {
//             attendanceRecordDao.add(attendanceRecord);
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//         Optional<AttendanceRecord> attendanceRecord_retrieved = attendanceRecordDao.get("100000");
//         assertEquals(attendanceRecord.getAttendanceRecordId(), attendanceRecord_retrieved.get().getAttendanceRecordId());
//         assertEquals(attendanceRecord.getLectureId(), attendanceRecord_retrieved.get().getLectureId());
//         Map<String, AttendanceRecord> attendanceRecordMap = attendanceRecordDao.getAttendanceRecordByStudentNetId("hg161");
//         attendanceRecord.setAttendanceStatus(AttendanceStatus.ABSENT);
//         try {
//             attendanceRecordDao.update(attendanceRecord);
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//         attendanceRecordDao.update(attendanceRecord);
//         // sectionEnrollmentDao
//         SectionEnrollmentDao sectionEnrollmentDao = new SectionEnrollmentDao();
//         Map<String, EnrollmentStatus> enrollment = new HashMap<>();
//         enrollment.put("hg161", EnrollmentStatus.DROPPED);
//         sectionEnrollmentDao.update(enrollment, "ECE156_1");
//         Map<String, EnrollmentStatus> enrollment_retrieved = sectionEnrollmentDao.get("ECE156_1").get();
//         assertEquals(enrollment.get("hg161"), enrollment_retrieved.get("hg161"));
//         sectionEnrollmentDao.delete("ECE156_1");
//         assertFalse(sectionEnrollmentDao.get("ECE156_1").isPresent());
//         // courseInstructor Dao
//         CourseInstructorDao cid = new CourseInstructorDao();
//         List<String> instructors = cid.getInstructorsByCourseSection("ECE156_2");
//         assertEquals("hp123", instructors.get(0));
//         cid.delete("hp123", "ECE156_2");
//         assertFalse(cid.getInstructorsByCourseSection("ECE156_2").contains("hp123"));
//         // notification preference Dao
//         NotificationPreferenceDao npd = new NotificationPreferenceDao();
//         NotificationPreference preference = npd.get("7").get();
//         assertEquals("hg161", preference.getStudentNetId());
//         Map<String, NotificationPreference> prefmap = npd.getNotificationPreferenceByStudentNetId("hg161");
//         assertEquals(prefmap.get("ECE156_1").getNotificationPreferenceId(), preference.getNotificationPreferenceId());
//         npd.update(preference);
//         // delete course
//         courseDao.delete(ECE156);
//     }

// }
