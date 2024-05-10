package edu.duke.ece651.project.team5.shared.dao;

import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import edu.duke.ece651.project.team5.shared.model.Professor;

public class ProfessorDao extends BaseDao<Professor> {
    public ProfessorDao() {
        super(Professor.class);
    }

}
