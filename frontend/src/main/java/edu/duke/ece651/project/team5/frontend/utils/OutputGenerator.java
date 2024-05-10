package edu.duke.ece651.project.team5.frontend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.duke.ece651.project.team5.shared.model.*;

public interface OutputGenerator {
    public void exportAttendanceForSection(SectionReport sectionReport) throws JsonProcessingException;

    public void exportAttendanceForStudents(StudentReport studentReport);
}
