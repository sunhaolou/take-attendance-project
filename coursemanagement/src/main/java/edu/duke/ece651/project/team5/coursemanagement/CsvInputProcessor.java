package edu.duke.ece651.project.team5.coursemanagement;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.FileReader;
import java.util.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import edu.duke.ece651.project.team5.shared.model.*;
import edu.duke.ece651.project.team5.shared.dao.*;

public class CsvInputProcessor {
  private Map<String, Course> courseMap;
  private Map<String, Section> sectionMap;
  private ProfessorDao professorDao;

  CsvInputProcessor() {
    courseMap = new HashMap<>();
    sectionMap = new HashMap<>();
    professorDao = new ProfessorDao();
  }

  CsvInputProcessor(ProfessorDao professorDao) {

    courseMap = new HashMap<>();
    sectionMap = new HashMap<>();
    this.professorDao = professorDao;
  }
public List<Course> getCourses() {
    return new ArrayList<>(courseMap.values());
  }
  public void createEnrollment(String line) throws SQLException {
    String[] info = line.split(",");
    Boolean hasInstructor = false;
    String courseId = "";
    String courseName = "";
    String sectionId = "";
    String instructorNetId = "";
    String student_netid = "";
    String status = "";
    for (String element : info) {
      if (element.matches("^[A-Z]+[0-9]+$")) {
        courseId = element;
      } else if (element.matches("^[A-Za-z ]+$") && !element.equals("ENROLLED") && !element.equals("DROPPED")) {
        courseName = element;
      } else if (element.matches("^[0-9]+$")) {
        sectionId = element;
      } else if (element.matches("^[A-Za-z0-9]+$") && !hasInstructor && !element.equals("ENROLLED")
          && !element.equals("DROPPED")) {
        instructorNetId = element;
        hasInstructor = true;
      } else if (element.matches("^[A-Za-z0-9]+$") && hasInstructor && !element.equals("ENROLLED")
          && !element.equals("DROPPED")) {
        student_netid = element;
      } else if (element.matches("^[A-Z]+$")) {
        status = element;
      } else {
        throw new IllegalArgumentException("csv file is invalid - contain unrecognized information");
      }
    }
    String courseSectionId = courseId + "_" + sectionId;
    Section section;
    Course course;
    Professor professor = professorDao.get(instructorNetId).get();

    if (courseMap.containsKey(courseId)) {
      course = courseMap.get(courseId);
    } else {
      course = new Course(courseId, courseName);
      courseMap.put(courseId, course);
    }
    if (sectionMap.containsKey(courseSectionId)) {
      section = sectionMap.get(courseSectionId);
    } else {
      section = new Section(courseSectionId, courseId, sectionId);
      sectionMap.put(courseSectionId, section);
      course.addSection(section);
    }
    section.addProfessor(professor);

    if (status.equals("ENROLLED")) {
      section.enrollStudent(student_netid);
    } else if (status.equals("DROPPED")) {
      section.dropStudent(student_netid);
    } else {
      throw new IllegalArgumentException("unknown status in csv file");
    }

  }

  public void createSession(String line) throws SQLException, java.text.ParseException {
    String[] info = line.split(",");
    String courseId = "";
    String sectionId = "";
    String time = "";
    for (String element : info) {
      if (element.matches("^[A-Z]+[0-9]+$")) {
        courseId = element;
      } else if (element.matches("^[0-9]+$")) {
        sectionId = element;
      } else if (element.matches("^[A-Za-z0-9: \\-]+$")) {
        time = element;
      } else {
        throw new IllegalArgumentException("csv file is invalid - contain unrecognized information");
      }
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    Date parsedDate = dateFormat.parse(time);
    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
    // public Lecture(String lectureId, String courseSectionId, Timestamp time) {
    String courseSectionId = courseId + "_" + sectionId;
    String lectureId = courseSectionId + "_" + time;
    Session lecture = new Lecture(lectureId, courseSectionId, timestamp);
    Section section = sectionMap.get(courseSectionId);
    section.addSession(lecture);
  }

  public void parseRosterCsvForSection(String file_name) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(file_name))) {
      String line;

      br.readLine();
      while ((line = br.readLine()) != null) {
        createEnrollment(line);

      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // @Override
  public void parseRosterCsvForSession(String file_name) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(file_name))) {
      String line;

      br.readLine();
      while ((line = br.readLine()) != null) {
        createSession(line);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
