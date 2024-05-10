package edu.duke.ece651.project.team5.shared.dao;

import java.sql.*;
import java.util.*;

import edu.duke.ece651.project.team5.shared.enums.NotificationMethod;
import edu.duke.ece651.project.team5.shared.model.NotificationPreference;
import edu.duke.ece651.project.team5.shared.model.Professor;
import edu.duke.ece651.project.team5.shared.model.Section;
import edu.duke.ece651.project.team5.shared.model.Session;

public class SectionDao extends BaseDao<Section> {
    public SectionDao() {
        super(Section.class);
    }

}
