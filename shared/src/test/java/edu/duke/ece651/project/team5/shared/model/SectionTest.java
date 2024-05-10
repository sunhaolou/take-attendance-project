package edu.duke.ece651.project.team5.shared.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SectionTest {
  @Test
  public void test_constructors_and_addProfessor() {
    Section section = new Section("ECE651_1");
    assertEquals("ECE651_1", section.getCourseSectionId());
    Professor afsaneh = new Professor("ar123", "Afsaneh", "ar13@duke.edu", "1234567890", "password");
    section.addProfessor(afsaneh);
    assertEquals(1, section.getProfessors().size());
    section.addProfessor(afsaneh);
    assertEquals(1, section.getProfessors().size());


    Session session1 = new Lecture("ECE651_1_001");
    Session session2 = new Lecture("ECE651_1_002");
    List<Session> sessions = new ArrayList<>();
    sessions.add(session1);
    sessions.add(session2);
    Section section2 = new Section("ECE651_1", "ECE651", "1", sessions);
    assertEquals("ECE651_1", section2.getCourseSectionId());
    assertEquals("ECE651", section2.getCourseId());
    assertEquals("1", section2.getSectionId());
    assertEquals(2, section2.getSessions().size());
  }

  @Test
  void test_addSession_enrollStudent(){
    Section section = new Section("ECE651_1");
    Session session1 = new Lecture("ECE651_1_001");
    Session session2 = new Lecture("ECE651_1_002");
    section.addSession(session1);
    section.addSession(session2);
    assertEquals(2, section.getSessions().size());
    Student student = new Student("hg161");
    section.enrollStudent(student);
    section.enrollStudent("test123");
    section.dropStudent("test123");
  }

}
