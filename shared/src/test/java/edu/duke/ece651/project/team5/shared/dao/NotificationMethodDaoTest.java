// package edu.duke.ece651.project.team5.shared.dao;

// import static org.junit.jupiter.api.Assertions.*;
// import java.util.*;

// import org.junit.jupiter.api.Test;
// import java.sql.SQLException;
// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
// import edu.duke.ece651.project.team5.shared.enums.NotificationMethod;

// public class NotificationMethodDaoTest {
//   DatabaseInitializer dbInitializer = new DatabaseInitializer();
//   @Test
//   public void test_notificationMethod() {
//     try{
//         dbInitializer.initialize();
//         NotificationMethodDao dao = new NotificationMethodDao();
//         assertThrows(SQLException.class, () -> dao.add(NotificationMethod.EMAIL));
//         dao.update(NotificationMethod.EMAIL);
//         Optional<NotificationMethod> email = dao.get("1");
//         assertEquals(email.get(), NotificationMethod.EMAIL);
//     }
//     catch (SQLException e){}
//   }

// }
