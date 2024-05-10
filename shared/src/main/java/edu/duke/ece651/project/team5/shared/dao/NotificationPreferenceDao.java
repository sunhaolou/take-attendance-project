package edu.duke.ece651.project.team5.shared.dao;

import edu.duke.ece651.project.team5.shared.model.*;
import edu.duke.ece651.project.team5.shared.enums.*;
import java.sql.*;
import java.util.*;

public class NotificationPreferenceDao extends BaseDao<NotificationPreference> {

    public NotificationPreferenceDao() {
        super(NotificationPreference.class);
    }

}
