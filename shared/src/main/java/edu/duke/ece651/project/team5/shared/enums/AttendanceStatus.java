package edu.duke.ece651.project.team5.shared.enums;

import java.util.HashMap;
import java.util.Map;

public enum AttendanceStatus {
    PRESENT(1, "PRESENT"),
    ABSENT(2, "ABSENT"),
    TARDY(3, "TARDY");

    private int id;
    private String status;

    private static final Map<Integer, AttendanceStatus> idToStatusMap = new HashMap<>();
    static {
        for (AttendanceStatus status : AttendanceStatus.values()) {
            idToStatusMap.put(status.getId(), status);
        }
    }

    public static AttendanceStatus getById(int id) {
        return idToStatusMap.get(id);
    }

    AttendanceStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }
}