package edu.duke.ece651.project.team5.frontend.model;

public enum NotificationMethod {
    EMAIL(1, "EMAIL"),
    NOREMINDER(2, "NOREMINDER");

    private int id;
    private String method;

    NotificationMethod(int id, String method) {
        this.id = id;
        this.method = method;
    }

    public int getId() { return this.id; }

    public String getMethod() { return this.method; }

    public static NotificationMethod getById(int id) {
        for (NotificationMethod e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

}