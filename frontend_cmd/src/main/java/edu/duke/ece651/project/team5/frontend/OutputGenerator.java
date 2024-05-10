package edu.duke.ece651.project.team5.frontend;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.duke.ece651.project.team5.frontend.model.StudentReport;


public interface OutputGenerator {
  public void exportAttendanceForProfessor(String response) throws JsonProcessingException;
  public void exportAttendanceForStudents(StudentReport studentReport);
}
