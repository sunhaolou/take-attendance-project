package edu.duke.ece651.project.team5.backend.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.duke.ece651.project.team5.backend.model.EmailEngine;
import edu.duke.ece651.project.team5.backend.model.Pair;
import edu.duke.ece651.project.team5.backend.repository.SectionDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.backend.service.CourseService;

import edu.duke.ece651.project.team5.shared.model.*;
import edu.duke.ece651.project.team5.shared.enums.AttendanceStatus;

/**
 * The ReportGenerator class provides methods to generate reports for students
 * and sections.
 */
@Component

public class ReportGenerator {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SectionDao sectionDao;
    @Autowired
    private StudentDao studentDao;

    private List<Student> students;

    @PostConstruct
    public void init() {
        // This method will be called after all the dependencies are injected.
        // Safe to use injected beans here.
        this.students = studentDao.findAll();
        // You can perform any additional initialization here.
    }

    /**
     * Generates a report for each student in the list of students.
     * 
     */
    @Scheduled(cron = "0 0 12 * * Sun")
    public void generateReport() {

        for (Student student : students) {
            try {
                generateOneReport(student);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Generates a report for each student in the list of students.
     * 
     * @param student the student to generate report for
     * @throws Exception if there is an error generating the report
     * @return the report generated
     */
    public SectionReport generateSectionReport(String sectionId) {

        List<Student> students = courseService.getEnrolledStudentsFromSection(sectionId);
        Optional<Section> optionalSection = sectionDao.findByCourseSectionId(sectionId);
        if (!optionalSection.isPresent()) {
            throw new IllegalArgumentException("Section does not exist");
        }
        Section section = optionalSection.get();
        SectionReport report = new SectionReport();
        for (Student student : students) {
            try {
                Pair<Integer, Integer> score = generateStudentScoreForSection(student, section);
                String finalScore;
                if (score.getValue() == 0) {
                    finalScore = "0";
                } else {

                    finalScore = String.valueOf(score.getKey() * 100 / score.getValue());
                }

                report.addRecord(student.getNetId(), finalScore);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return report;
    }

    /**
     * Generates a report for a student.
     * 
     * @param student the student to generate report for
     * @throws Exception if there is an error generating the report
     * @return the report generated
     */
    public StudentReport generateStudentReport(Student student) {
        List<SessionReport> reports = generateStudentReportContent(student);
        Integer score = generateStudentScore(student);
        return new StudentReport(reports, String.valueOf(score));
    }

    // public List<SessionReport> generateStudentDetailedContent(Student student)
    // throws Exception {
    // List<Section> sections = studentService.getEnrolledSections(student);

    // List<SessionReport> reports = new ArrayList<>();
    // for (Section section : sections) {
    // List<SessionReport> sectionReports =
    // generateStudentDetailedContentForSection(student, section);
    // reports.addAll(sectionReports);
    // }

    // return reports;
    // }

    /**
     * Generates a report for a student.
     * 
     * @param student the student to generate report for
     * @throws Exception if there is an error generating the report
     * @return the report generated
     */
    public List<SessionReport> generateStudentReportContent(Student student) {
        List<Section> sections = getEnrolledSections(student);
        List<SessionReport> reports = new ArrayList<>();
        for (Section section : sections) {
            List<SessionReport> sectionReports = generateStudentDetailedLectureReportForSection(student, section);
            reports.addAll(sectionReports);
        }

        return reports;
    }

    /**
     * Generates a report for a student.
     * 
     * @param student the student to generate report for
     * @throws Exception if there is an error generating the report
     * @return the report generated
     */
    public List<SessionReport> generateStudentDetailedContentForSection(Student student, Section section)
            throws Exception {
        List<Session> sessions = new ArrayList<>();
        sessions.addAll(section.getSessions());

        List<SessionReport> reports = new ArrayList<>();
        for (Session session : sessions) {
            try {

                AttendanceStatus status = session.getAttendanceOfStudent(student);
                SessionReport report = new SessionReport(session.getCourseSectionId(), status.getStatus(),
                        student.getNetId(), session.getTime().toString());
                reports.add(report);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return reports;
    }

    /**
     * Generates a report for a student.
     * 
     * @param student the student to generate report for
     * @throws Exception if there is an error generating the report
     * @return the report generated
     */
    public List<SessionReport> generateStudentDetailedLectureReportForSection(Student student, Section section) {
        List<Session> sessions = new ArrayList<>();
        sessions.addAll(section.getSessions());

        List<SessionReport> reports = new ArrayList<>();
        for (Session session : sessions) {
            try {
                AttendanceStatus status = session.getAttendanceOfStudent(student);
                SessionReport report = new SessionReport(student.getNetId(), session.getTime().toString(),
                        status.getStatus(), session.getCourseSectionId());
                reports.add(report);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return reports;
    }

    /**
     * Generates a report for a student.
     * 
     * @param student the student to generate report for
     * @throws Exception if there is an error generating the report
     * @return the report generated
     */
    public Pair<Integer, Integer> generateStudentScoreForSection(Student student, Section section) {

        List<Session> sessions = new ArrayList<>();
        if (section.getSessions() == null || section.getSessions().isEmpty() || section.getSessions().size() == 0) {
            return new Pair<Integer, Integer>(0, 0);
        }
        sessions.addAll(section.getSessions());
        Integer total = 0;
        Integer attended = 0;
        for (Session session : sessions) {
            try {

                AttendanceStatus status = session.getAttendanceOfStudent(student);
                if (status == AttendanceStatus.PRESENT) {
                    attended += 100;
                    total += 100;

                } else if (status == AttendanceStatus.TARDY) {
                    attended += 80;
                    total += 100;
                } else if (status == AttendanceStatus.ABSENT) {
                    total += 100;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        if (total == 0) {
            return new Pair<Integer, Integer>(0, 0);
        }
        return new Pair<Integer, Integer>(attended, total);
    }

    /**
     * Generates a report for a student.
     * 
     * @param student the student to generate report for
     * @throws Exception if there is an error generating the report
     * @return the report generated
     */
    public Integer generateStudentScore(Student student) {
        List<Section> sections = getEnrolledSections(student);
        Integer total = 0;
        Integer attended = 0;
        for (Section section : sections) {
            Pair<Integer, Integer> score = generateStudentScoreForSection(student, section);
            attended += score.getKey();
            total += score.getValue();
        }
        if (total == 0) {
            return 0;
        }
        return attended * 100 / total;
    }

    /**
     * Generates a report for a student.
     * 
     * @param student the student to generate report for
     * @throws Exception if there is an error generating the report
     * @return the report generated
     */
    public String generateContent(Student student, StudentReport StudentReport) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Dear " + student.getLegalName() + ",\n\n");
        sb.append("This is your report.\n\n");
        sb.append(StudentReport.toString());
        sb.append("Best regards,\n");
        sb.append("ECE651 group 5");
        return sb.toString();
    }

    /**
     * Generates a report for a student.
     * 
     * @param student the student to generate report for
     * @throws Exception if there is an error generating the report
     * @return the report generated
     */
    public String generateContentForUpdate(Student student) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Dear " + student.getLegalName() + ",\n\n");
        sb.append("Your attendance has been changed.\n\n");
        sb.append("Best regards,\n");
        sb.append("ECE651 group 5");
        return sb.toString();
    }

    /**
     * Generates a report for a student.
     * 
     * @param student the student to generate report for
     * @throws Exception if there is an error generating the report
     */
    public void generateOneReport(Student student) throws Exception {
        EmailEngine ee = new EmailEngine();
        if (student.getEmail() == null || student.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Student email is empty");
        }
        ee.sendEmail(student.getEmail(), "attendence report",
                generateContent(student, generateStudentReport(student)));
        System.out.println("email sent to " + student.getNetId());
    }

    /**
     * Generates a report for a student.
     * 
     * @param student the student to generate report for
     * @throws Exception if there is an error generating the report
     */
    public void generateOneReportForUpdate(Student student) throws Exception {
        EmailEngine ee = new EmailEngine();
        if (student.getEmail() == null || student.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Student email is empty");
        }
        ee.sendEmail(student.getEmail(), "attendence report",
                generateContentForUpdate(student));
        System.out.println("email sent to " + student.getNetId());
    }

    /**
     * Gets the list of students.
     * 
     * @return the list of students
     */
    public List<Student> getStudents() {
        return students;
    }

    public List<Section> getEnrolledSections(Student student) {
        List<String> courseSectionIds = student.getEnrolledCourseSectionIds();
        List<Section> sections = new ArrayList<>();

        for (String courseSectionId : courseSectionIds) {
            Optional<Section> optionalSection = sectionDao.findByCourseSectionId(courseSectionId);
            if (!optionalSection.isPresent()) {
                continue;
            }
            sections.add(optionalSection.get());
        }
        return sections;
    }
}
