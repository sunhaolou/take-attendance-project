package edu.duke.ece651.project.team5.shared.model;

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

  public void setReports(List<SessionReport> reports) {
    this.reports = reports;
  }

  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (SessionReport report : reports) {
      sb.append(report.toString());
    }
    sb.append("Score: " + score + "\n");
    return sb.toString();

  }

}
