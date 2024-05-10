package edu.duke.ece651.project.team5.backend.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.duke.ece651.project.team5.backend.repository.AttendanceRecordDao;
import edu.duke.ece651.project.team5.backend.repository.CourseDao;
import edu.duke.ece651.project.team5.backend.repository.LectureDao;
import edu.duke.ece651.project.team5.backend.repository.ProfessorDao;
import edu.duke.ece651.project.team5.backend.repository.SectionDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.shared.enums.AttendanceStatus;
import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;
import edu.duke.ece651.project.team5.shared.enums.NotificationMethod;
import edu.duke.ece651.project.team5.shared.model.AttendanceRecord;
import edu.duke.ece651.project.team5.shared.model.Course;
import edu.duke.ece651.project.team5.shared.model.Lecture;
import edu.duke.ece651.project.team5.shared.model.Professor;
import edu.duke.ece651.project.team5.shared.model.Section;
import edu.duke.ece651.project.team5.shared.model.Session;
import edu.duke.ece651.project.team5.shared.model.Student;

// import edu.duke.ece651.project.team5.shared.dao.StudentDao;

@RestController
public class IndexController {
    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private LectureDao lectureDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AttendanceRecordDao attendanceRecordDao;

    @GetMapping("/index/test")

    public String test() {
        String encodedPassword = passwordEncoder.encode("123");

        Professor afsaneh = new Professor("ar123", "afsaneh", "ar123@duke.edu", "123456", encodedPassword);
        afsaneh.setPassword(encodedPassword);
        Professor javier = new Professor("hp123", "javier", "hp123@duke.edu", "1234567", encodedPassword);

        Student steven = new Student("hg161", encodedPassword, "geng", "steven", "test1@duke.edu", "185");
        Student hunter = new Student("zs149", encodedPassword, "shen", "hunter", "test2@duke.edu", "186");
        Student yuyu = new Student("yh384", encodedPassword, "hsieh", "yuyu", "test3@duke.edu", "187");
        Student Haolou = new Student("hs392", encodedPassword, "sun", "haolou", "test4@duke.edu", "188");
        Student hiep = new Student("hhn3", encodedPassword, "nguyen", "hiep", "test5@duke.edu", "189");
        studentDao.save(steven);
        studentDao.save(hunter);
        studentDao.save(yuyu);
        studentDao.save(Haolou);
        studentDao.save(hiep);
        professorDao.save(afsaneh);
        professorDao.save(javier);

        List<Professor> ECE156_1_professors = new ArrayList<>(Arrays.asList(afsaneh));
        List<Professor> ECE156_2_professors = new ArrayList<>(Arrays.asList(javier));
        Lecture ECE156_1_001 = new Lecture("ECE156_1_001", "ECE156_1",
                Timestamp.valueOf("2024-04-15 12:00:00"));
        Lecture ECE156_1_002 = new Lecture("ECE156_1_002", "ECE156_1",
                Timestamp.valueOf("2024-04-16 12:00:00"));
        Lecture ECE156_2_001 = new Lecture("ECE156_2_003", "ECE156_2",
                Timestamp.valueOf("2024-04-15 12:00:00"));
        Lecture ECE156_2_002 = new Lecture("ECE156_2_004", "ECE156_2",
                Timestamp.valueOf("2024-04-16 12:00:00"));

        lectureDao.save(ECE156_1_001);
        lectureDao.save(ECE156_1_002);
        lectureDao.save(ECE156_2_001);
        lectureDao.save(ECE156_2_002);

        List<Session> ECE156_1_sessions = new ArrayList<>(Arrays.asList(ECE156_1_001, ECE156_1_002));
        List<Session> ECE156_2_sessions = new ArrayList<>(Arrays.asList(ECE156_2_001, ECE156_2_002));
        Map<String, EnrollmentStatus> ECE156_1_enrollment = new HashMap<>();
        Map<String, EnrollmentStatus> ECE156_2_enrollment = new HashMap<>();
        ECE156_1_enrollment.put("hg161", EnrollmentStatus.ENROLLED);
        ECE156_1_enrollment.put("zs149", EnrollmentStatus.ENROLLED);
        ECE156_2_enrollment.put("yh384", EnrollmentStatus.ENROLLED);
        ECE156_2_enrollment.put("hs392", EnrollmentStatus.ENROLLED);
        ECE156_2_enrollment.put("hhn3", EnrollmentStatus.ENROLLED);

        ECE156_1_001.takeAttendance(hunter, AttendanceStatus.PRESENT);
        ECE156_1_002.takeAttendance(hunter, AttendanceStatus.TARDY);
        ECE156_2_001.takeAttendance(yuyu, AttendanceStatus.PRESENT);
        ECE156_2_001.takeAttendance(Haolou, AttendanceStatus.PRESENT);

        ECE156_2_001.takeAttendance(hiep, AttendanceStatus.ABSENT);

        AttendanceRecord attendanceRecord1 = new AttendanceRecord("ECE156_1_001", "zs149",
                AttendanceStatus.PRESENT);
        AttendanceRecord attendanceRecord1_1 = new AttendanceRecord("ECE156_1_002", "zs149",
                AttendanceStatus.TARDY);
        AttendanceRecord attendanceRecord2 = new AttendanceRecord("ECE156_2_003", "yh384",
                AttendanceStatus.PRESENT);
        AttendanceRecord attendanceRecord3 = new AttendanceRecord("ECE156_2_003", "hs392",
                AttendanceStatus.PRESENT);
        AttendanceRecord attendanceRecord4 = new AttendanceRecord("ECE156_2_003", "hhn3",
                AttendanceStatus.ABSENT);

        attendanceRecordDao.save(attendanceRecord1);
        attendanceRecordDao.save(attendanceRecord1_1);
        attendanceRecordDao.save(attendanceRecord2);
        attendanceRecordDao.save(attendanceRecord3);
        attendanceRecordDao.save(attendanceRecord4);
        lectureDao.save(ECE156_1_001);
        lectureDao.save(ECE156_1_002);
        lectureDao.save(ECE156_2_001);
        lectureDao.save(ECE156_2_002);
        Section ECE156_1 = new Section("ECE156_1", "ECE156", "1", ECE156_1_professors, ECE156_1_sessions,
                ECE156_1_enrollment);
        Section ECE156_2 = new Section("ECE156_2", "ECE156", "2", ECE156_2_professors, ECE156_2_sessions,
                ECE156_2_enrollment);
        sectionDao.save(ECE156_1);
        sectionDao.save(ECE156_2);
        Course ECE156 = new Course("ECE156", "Software Engineering",
                new ArrayList<>(Arrays.asList(ECE156_1, ECE156_2)));
        afsaneh.setCourseSections(new HashSet<>(Arrays.asList(ECE156_1)));
        javier.setCourseSections(new HashSet<>(Arrays.asList(ECE156_2)));

        steven.createNotificationPreference("ECE156_1", NotificationMethod.EMAIL);
        hunter.createNotificationPreference("ECE156_1", NotificationMethod.EMAIL);
        yuyu.createNotificationPreference("ECE156_2", NotificationMethod.EMAIL);
        Haolou.createNotificationPreference("ECE156_2", NotificationMethod.EMAIL);
        hiep.createNotificationPreference("ECE156_2", NotificationMethod.EMAIL);
        courseDao.save(ECE156);
        studentDao.save(steven);
        studentDao.save(hunter);
        studentDao.save(yuyu);
        studentDao.save(Haolou);
        studentDao.save(hiep);
        professorDao.save(afsaneh);
        professorDao.save(javier);
        return "success";
    }

    @GetMapping("/index/test2")
    public String test2() {
        edu.duke.ece651.project.team5.shared.dao.StudentDao studentDao = new edu.duke.ece651.project.team5.shared.dao.StudentDao();
        Optional<Student> student = studentDao.get("zs149");
        return student.get().getLegalName();

    }
}
