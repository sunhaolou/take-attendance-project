package edu.duke.ece651.project.team5.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import edu.duke.ece651.project.team5.backend.repository.ProfessorDao;
import edu.duke.ece651.project.team5.backend.repository.*;
import edu.duke.ece651.project.team5.shared.model.*;
import edu.duke.ece651.project.team5.shared.enums.*;
import edu.duke.ece651.project.team5.backend.model.Pair;

/**
 * The CourseService class provides methods to interact with course-related data
 * and operations.
 */
@Service
public class CourseService {

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private StudentDao studentDao;

    // @Autowired
    // private StudentService studentService;

    /**
     * Get all sections of a course.
     * 
     * @param course the course to get sections from
     * @return a list of sections of the course
     */
    public List<String> getSectionIds() {
        User professorUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Professor> professor = professorDao.findByNetId(professorUser.getUsername());
        if (!professor.isPresent()) {
            throw new IllegalArgumentException("professor does not exist");
        }
        // System.out.println(professor.get().getCourseSectionIds());
        return new ArrayList<String>(professor.get().getCourseSectionIds());
        // return new ArrayList<String>(Arrays.asList("ECE651_1", "ECE651_2",
        // "ECE651_3"));
    }

    public Map<String, List<String>> getProfNameAndSectionIds() {
        User professorUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Professor> professor = professorDao.findByNetId(professorUser.getUsername());
        if (!professor.isPresent()) {
            throw new IllegalArgumentException("professor does not exist");
        }
        HashMap<String, List<String>> result = new HashMap<>();
        System.out.println(professor.get().getLegalName());
        System.out.println(professor.get().getCourseSectionIds());
        result.put(professor.get().getLegalName(), new ArrayList<String>(professor.get().getCourseSectionIds()));
        return result;
        // return new ArrayList<String>(Arrays.asList("ECE651_1", "ECE651_2",
        // "ECE651_3"));
    }

    // /**
    // * Get all sections of a course.
    // *
    // * @param course the course to get sections from
    // * @return a list of sections of the course
    // */
    // public Section getSectionById(String sectionId) throws SQLException {

    // Optional<Section> section = sectionDao.findByCourseSectionId(sectionId);
    // if (!section.isPresent()) {
    // throw new IllegalArgumentException("section does not exist");
    // }
    // return section.get();

    // }

    /**
     * Get all sections of a course.
     *
     * @param course the course to get sections from
     * @return a list of sections of the course
     */
    public List<Student> getEnrolledStudentsFromSection(String sectionId) {
        Optional<Section> section = sectionDao.findByCourseSectionId(sectionId);
        User professorUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Professor> professor = professorDao.findByNetId(professorUser.getUsername());
        if (section.isEmpty() || professor.isEmpty()) {
            throw new IllegalArgumentException("section or professor does not exist");
        }
        List<Student> students = new ArrayList<>();
        for (Map.Entry<String, EnrollmentStatus> entry : section.get().getEnrollment().entrySet()) {
            if (entry.getValue() == EnrollmentStatus.ENROLLED) {

                String studentId = entry.getKey();
                Student student = studentDao.findByNetId(studentId).get();
                students.add(student);

            }
        }
        return students;
    }

    public List<Student> getEnrolledStudentsFromSection(String sectionId, String legalName) {

        List<Student> students = getEnrolledStudentsFromSection(sectionId);
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getLegalName().toLowerCase().contains(legalName.toLowerCase())) {
                result.add(student);
            }

        }
        return result;
    }

    public Map<String, List<String>> getStuNameAndSectionIds() {
        User studentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Student> student = studentDao.findByNetId(studentUser.getUsername());
        if (!student.isPresent()) {
            throw new IllegalArgumentException("student does not exist");
        }
        HashMap<String, List<String>> result = new HashMap<>();
        System.out.println(student.get().getLegalName());
        System.out.println(student.get().getEnrolledCourseSections());
        result.put(student.get().getLegalName(), new ArrayList<String>(student.get().getNotificationPreferences().keySet()));
        return result;
    }

}
