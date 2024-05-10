// package edu.duke.ece651.project.team5.shared.dao;

// import static org.junit.jupiter.api.Assertions.*;
// import java.util.*;

// import org.junit.jupiter.api.Test;
// import java.sql.SQLException;
// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
// import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;

// public class EnrollmentStatusDaoTest {
//   DatabaseInitializer dbInitializer = new DatabaseInitializer();
//   @Test
//   public void test_EnrollmentStatusDao() {
//     try{
//         dbInitializer.initialize();
//         EnrollmentStatusDao dao = new EnrollmentStatusDao();
//         assertThrows(SQLException.class, () -> dao.add(EnrollmentStatus.ENROLLED));
//         dao.update(EnrollmentStatus.ENROLLED);
//         Optional<EnrollmentStatus> enroll = dao.get("1");
//         assertEquals(enroll.get(), EnrollmentStatus.ENROLLED);
//     }
//     catch (SQLException e){}
//   }
// }
