package edu.duke.ece651.project.team5.shared.dao;

import java.sql.*;
import java.util.*;

import edu.duke.ece651.project.team5.shared.model.Course;
import edu.duke.ece651.project.team5.shared.model.Section;

public class CourseDao extends BaseDao<Course> {
    public CourseDao() {
        super(Course.class);
    }
}
