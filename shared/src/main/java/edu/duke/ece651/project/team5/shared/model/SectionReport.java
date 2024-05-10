package edu.duke.ece651.project.team5.shared.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectionReport {
    // netid , score
    private Map<String, String> attendance;

    public SectionReport() {
        this(new HashMap<>());
    }

    public SectionReport(Map<String, String> attendance) {
        this.attendance = attendance;
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
