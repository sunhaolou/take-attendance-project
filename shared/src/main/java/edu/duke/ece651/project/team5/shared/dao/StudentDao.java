package edu.duke.ece651.project.team5.shared.dao;

import edu.duke.ece651.project.team5.shared.model.Student;

public class StudentDao extends BaseDao<Student> {
    public StudentDao() {
        super(Student.class);
    }
}
