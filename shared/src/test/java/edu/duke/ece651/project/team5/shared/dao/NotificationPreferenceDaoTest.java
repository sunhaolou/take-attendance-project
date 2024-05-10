// package edu.duke.ece651.project.team5.shared.dao;

// import static org.junit.jupiter.api.Assertions.*;
// import java.util.*;

// import org.junit.jupiter.api.Test;
// import java.sql.SQLException;
// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
// import edu.duke.ece651.project.team5.shared.enums.NotificationMethod;
// import edu.duke.ece651.project.team5.shared.model.NotificationPreference;

// public class NotificationPreferenceDaoTest {
//   DatabaseInitializer dbInitializer = new DatabaseInitializer();
//   @Test
//   public void testNotificationPreferenceDao() {
//     try{
//       dbInitializer.initialize();
//       NotificationPreferenceDao dao = new NotificationPreferenceDao();
//       assertNotNull(dao.get("1"));
//     }
//     catch (SQLException e){}

//   }

// }
