package edu.duke.ece651.project.team5.shared.dao;

import java.sql.*;
import java.util.*;

import edu.duke.ece651.project.team5.shared.model.AttendanceRecord;
import edu.duke.ece651.project.team5.shared.model.Lecture;
import edu.duke.ece651.project.team5.shared.model.Session;

public class LectureDao extends BaseDao<Lecture> {
    public LectureDao() {
        super(Lecture.class);
    }
}