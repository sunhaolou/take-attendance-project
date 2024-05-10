package edu.duke.ece651.project.team5.shared.enums;

public enum EnrollmentStatus {
    ENROLLED(1, "ENROLLED"),
    DROPPED(2, "DROPPED");
        
    private int id;
    private String status;
        
    EnrollmentStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }
    
    public int getId() { return this.id; }
    
    public String getStatus() { return this.status; }

    public static EnrollmentStatus getById (int id) {
        for (EnrollmentStatus e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }
}