// package edu.duke.ece651.project.team5.shared.dao;

// import static org.junit.jupiter.api.Assertions.*;

// import java.sql.*;
// import java.util.*;

// import org.junit.jupiter.api.Test;

// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
// import edu.duke.ece651.project.team5.shared.model.Professor;
// import edu.duke.ece651.project.team5.shared.utils.PasswordHasher;

// public class ProfessorDaoTest {
//     DatabaseInitializer dbInitializer = new DatabaseInitializer();

//     @Test
//     public void testAddAndGet() throws SQLException {
//         // dbInitializer.dropAllTables();
//         // dbInitializer.initialize();
//         ProfessorDao professorDao = new ProfessorDao();
//         Professor professor1 = new Professor("ar123", "afsaneh", "ar123@duke.edu", "123456", "123456");
//         Professor professor2 = new Professor("hp123", "javier", "hp123@duke.edu", "123456", "123456");
//         try {

//             professorDao.add(professor1);
//             professorDao.add(professor2);
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//         Optional<Professor> result = professorDao.get("ar123");
//         assertTrue(result.isPresent());
//         assertEquals("ar123", result.get().getNetId());
//         assertEquals("afsaneh", result.get().getLegalName());
//         // assertEquals("ar123@duke.edu", result.get().getEmail());
//         assertEquals("123456", result.get().getPhone());
//         // assertEquals(result.get().getPassword(),
//         // PasswordHasher.getHashedPassword("123456", result.get().getSalt()));
//         // assertEquals("123456", result.get().getPassword());
//     }

//     @Test
//     public void testGetAllandUpdate() throws SQLException {
//         // dbInitializer.dropAllTables();
//         // dbInitializer.initialize();
//         ProfessorDao professorDao = new ProfessorDao();
//         Professor professor1 = new Professor(" ", "afsaneh", "ar123@duke.edu", "123456", "123456");
//         Professor professor2 = new Professor("!", "javier", "hp123@duke.edu", "123456", "123456");
//         try {

//             professorDao.add(professor1);
//             professorDao.add(professor2);
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//         List<Professor> result = professorDao.getAll();
//         // assertEquals(2, result.size());
//         result.sort((a, b) -> a.getNetId().compareTo(b.getNetId()));
//         assertEquals(" ", result.get(0).getNetId());
//         assertEquals("afsaneh", result.get(0).getLegalName());
//         assertEquals("!", result.get(1).getNetId());
//         assertEquals("javier", result.get(1).getLegalName());
//         professor1.setEmail("new@duke.edu");
//         professorDao.update(professor1);
//         Optional<Professor> updatedProfessor = professorDao.get(" ");
//         assertTrue(updatedProfessor.isPresent());
//         assertEquals(" ", updatedProfessor.get().getNetId());
//         // assertEquals("new@duke.edu", updatedProfessor.get().getEmail());
//     }

//     @Test
//     public void test_wholeProcess1() throws SQLException {
//         // This method simulates the process of creating professor in admin
//         // Just store basic information, no course section information
//         // dbInitializer.dropAllTables();
//         // dbInitializer.initialize();
//         ProfessorDao professorDao = new ProfessorDao();
//         Professor professor1 = new Professor(" ", "afsaneh", "ar123@duke.edu", "123456", "123456");
//         Professor professor2 = new Professor("!", "javier", "hp123@duke.edu", "123456", "123456");
//         try {
//             professorDao.add(professor1);

//             professorDao.add(professor2);
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }

//         // creation phase is over, you can ignore the following tests
//         // assertEquals(2, professorDao.getAll().size());
//         List<Professor> result = professorDao.getAll();
//         result.sort((a, b) -> a.getNetId().compareTo(b.getNetId()));
//         assertEquals(" ", result.get(0).getNetId());
//         assertEquals("afsaneh", result.get(0).getLegalName());
//         assertEquals("!", result.get(1).getNetId());
//         assertEquals("javier", result.get(1).getLegalName());
//     }
// }
