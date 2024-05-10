// package edu.duke.ece651.project.team5.shared.dao;
// import static org.junit.jupiter.api.Assertions.*;
// import java.sql.SQLException;
// import org.junit.jupiter.api.Test;
// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;

// public class SuperUserDaoTest {
//     DatabaseInitializer dbInitializer = new DatabaseInitializer();
//     @Test
//     public void test_superuserdao(){
//         try{
//             dbInitializer.initialize();
//             SuperUserDao dao = new SuperUserDao();
//             assertThrows(SQLException.class, () -> dao.add("admin", "admin"));
//             assertTrue(dao.checkSuperUserExist("admin", "admin"));
//             assertFalse(dao.checkSuperUserExist("ghost", "ghost"));
//         }
//         catch (SQLException e){

//         }
//     }
// }
