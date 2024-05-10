package edu.duke.ece651.project.team5.frontend.model;

import java.util.ArrayList;
import java.util.List;

public class StudentReport {

  private List<SessionReport> reports;

  private String score;

  public StudentReport() {
    this.reports = new ArrayList<>();

  }

  public StudentReport(List<SessionReport> reports, String score) {
    this.reports = reports;
    this.score = score;
  }

  public List<SessionReport> getReports() {
    return reports;
  }

  public String getScore() {
    return score;
  }
}
