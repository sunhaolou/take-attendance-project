package edu.duke.ece651.project.team5.frontend.model;

import java.util.HashMap;
import java.util.Map;

public class SectionReport {
    // netid , score
    Map<String, String> attendance;

    public SectionReport() {
      attendance = new HashMap<>();
    }

    public Map<String, String> getAttendance() {
        return attendance;
    }

    public void addRecord(String netId, String score) {
        attendance.put(netId, score);
    }

    public void setAttendance(Map<String, String> attendance) {
        this.attendance = attendance;
    }

}
