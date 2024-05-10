package edu.duke.ece651.project.team5.backend.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;

import edu.duke.ece651.project.team5.backend.repository.SectionDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.backend.utils.ReportGenerator;
import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;
import edu.duke.ece651.project.team5.shared.enums.NotificationMethod;
import edu.duke.ece651.project.team5.shared.model.NotificationPreference;
import edu.duke.ece651.project.team5.shared.model.Section;
import edu.duke.ece651.project.team5.shared.model.Student;
import edu.duke.ece651.project.team5.shared.model.StudentReport;

/**
 * The StudentService class provides methods to interact with student data and
 * perform operations related to students.
 */
@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private ReportGenerator reportGenerator;

    /**
     * Get all sections that a student is enrolled in.
     * 
     * @param student the student to get enrolled sections from
     * @return a list of sections that the student is enrolled in
     */
    public List<Section> getEnrolledSections(Student student) {
        List<Section> sections = new ArrayList<>();
        for (Map.Entry<String, EnrollmentStatus> entry : student.getEnrolledCourseSections().entrySet()) {
            if (entry.getValue() == EnrollmentStatus.ENROLLED) {

                sections.add(sectionDao.findByCourseSectionId(entry.getKey()).get());

            }
        }
        return sections;
    }

    /**
     * Get a student by netId.
     * 
     * @param netId the netId of the student to get
     * @return the student with the specified netId
     * @throws SQLException if there is an error accessing the database
     */
    public Student getStudentByNetId(String netId) {

        Optional<Student> student = studentDao.findByNetId(netId);
        if (!student.isPresent()) {
            throw new IllegalArgumentException("Student does not exist");
        }
        return student.get();
        // throw new IllegalArgumentException("Student does not exist");
    }

    /**
     * Get a student by netId.
     * 
     * @param netId the netId of the student to get
     * @return the student with the specified netId
     * @throws SQLException if there is an error accessing the database
     */
    public List<Student> getAllStudents() {
        return studentDao.findAll();

    }

    /**
     * Get all students enrolled in a section.
     * 
     * @param section the section to get enrolled students from
     * @return a list of students enrolled in the section
     */
    public List<Student> searchStudentsByLegalName(String legalName, String sectionId)  {
        Section section = sectionDao.findByCourseSectionId(sectionId).get();
        List<Student> students = new ArrayList<>();
        for (Map.Entry<String, EnrollmentStatus> entry : section.getEnrollment().entrySet()) {
            Student student = getStudentByNetId(entry.getKey());
            if (entry.getValue() == EnrollmentStatus.ENROLLED
                    && student.getLegalName().toLowerCase().contains(legalName.toLowerCase())) {
                students.add(student);

            }
        }
        return students;
    }

    /**
     * Get all students enrolled in a section.
     * 
     * @param section the section to get enrolled students from
     * @return a list of students enrolled in the section
     */
    public void updateStudentNotificationPreference(Student student, String sectionId, String method)
           {
        NotificationPreference notificationPreference = student.getNotificationPreferences().get(sectionId);
        if (method.equals("true")) {
            notificationPreference.setNotificationMethod(NotificationMethod.EMAIL);

        } else if (method.equals("false")) {
            notificationPreference.setNotificationMethod(NotificationMethod.NOREMINDER);
        } else {
            throw new IllegalArgumentException("Invalid notification method");
        }

        studentDao.saveAndFlush(student);
    }

    public String geteNotiPrefBySec(String sectionId) {
        User studentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Student> student = studentDao.findByNetId(studentUser.getUsername());
        if (student.isEmpty()) {
            throw new IllegalArgumentException("Student does not exist");
        }
        Student stu = student.get();
        Map<String, NotificationPreference> notificationPreferences = stu.getNotificationPreferences();
        NotificationPreference notificationPreference = notificationPreferences.get(sectionId);
        if (notificationPreference == null) {
            throw new IllegalArgumentException("Notification preference does not exist");
        }
        return notificationPreference.getNotificationMethod().toString();
    }

    public String updateNotiPrefBySec(String sectionId, String notiPref) {
        System.out.println("want to change to: " + notiPref);
        User studentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Student> student = studentDao.findByNetId(studentUser.getUsername());
        if (student.isEmpty()) {
            throw new IllegalArgumentException("Student does not exist");
        }
        Student stu = student.get();
        Map<String, NotificationPreference> notificationPreferences = stu.getNotificationPreferences();
        NotificationPreference notificationPreference = notificationPreferences.get(sectionId);
        if (notificationPreference == null) {
            throw new IllegalArgumentException("Notification preference does not exist");
        }
        if (notiPref.equals("EMAIL")) {
            notificationPreference.setNotificationMethod(NotificationMethod.EMAIL);
        } else if (notiPref.equals("NOREMINDER")) {
            notificationPreference.setNotificationMethod(NotificationMethod.NOREMINDER);
        } else {
            throw new IllegalArgumentException("Invalid notification method");
        }
        studentDao.saveAndFlush(stu);
        return "Notification Preference Updated";
    }

    public StudentReport generateStudentReport() {
        User studentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Student> student = studentDao.findByNetId(studentUser.getUsername());
        if (student.isEmpty()) {
            throw new IllegalArgumentException("Student does not exist");
        }
        StudentReport report = reportGenerator.generateStudentReport(student.get());
        return report;
    }
}
