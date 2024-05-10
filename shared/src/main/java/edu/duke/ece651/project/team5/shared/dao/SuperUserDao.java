package edu.duke.ece651.project.team5.shared.dao;

import edu.duke.ece651.project.team5.shared.model.*;
import edu.duke.ece651.project.team5.shared.*;
import java.sql.*;
import java.util.*;

public class SuperUserDao extends BaseDao<Admin> {
    public SuperUserDao() {
        super(Admin.class);
    }
}