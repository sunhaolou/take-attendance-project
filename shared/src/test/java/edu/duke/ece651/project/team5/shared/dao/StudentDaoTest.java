// package edu.duke.ece651.project.team5.shared.dao;

// import static org.junit.jupiter.api.Assertions.*;

// import java.sql.SQLException;

// import org.junit.jupiter.api.Test;
// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
// import edu.duke.ece651.project.team5.shared.model.Student;

// public class StudentDaoTest {
//   DatabaseInitializer dbInitializer = new DatabaseInitializer();
//   @Test
//   public void test_addAndGet() throws SQLException {
//     dbInitializer.dropAllTables();
//     dbInitializer.initialize();
//     StudentDao studentDao = new StudentDao();
//     Student student = new Student("netid", "password", "legalname", "preferredname", "email", "phone");
//     studentDao.add(student);
//     Student result = studentDao.get("netid").get();
//     assertEquals("netid", result.getNetId());
//     assertEquals("legalname", result.getLegalName());
//     assertEquals("preferredname", result.getPreferredName());
//     assertEquals("email", result.getEmail());
//     assertEquals("phone", result.getPhone());
//   }

//   @Test
//   public void test_update() throws SQLException {
//     dbInitializer.dropAllTables();
//     dbInitializer.initialize();
//     StudentDao studentDao = new StudentDao();
//     Student student = new Student("netid", "password", "legalname", "preferredname", "email", "phone");
//     studentDao.add(student);
//     Student result = studentDao.get("netid").get();
//     assertEquals("netid", result.getNetId());
//     assertEquals("legalname", result.getLegalName());
//     assertEquals("preferredname", result.getPreferredName());
//     assertEquals("email", result.getEmail());
//     assertEquals("phone", result.getPhone());
//     Student newStudent = new Student("netid", "passw0rd", "Legalname", "Preferredname", "Email", "Phone");
//     studentDao.update(newStudent);
//     Student updatedResult = studentDao.get("netid").get();
//     assertEquals("netid", updatedResult.getNetId());
//     assertEquals("legalname", updatedResult.getLegalName());
//     assertEquals("Preferredname", updatedResult.getPreferredName());
//     assertEquals("Email", updatedResult.getEmail());
//     assertEquals("phone", updatedResult.getPhone());
//   }

//   @Test
//   public void test_delete() throws SQLException {
//     dbInitializer.dropAllTables();
//     dbInitializer.initialize();
//     StudentDao studentDao = new StudentDao();
//     Student student = new Student("netid", "password", "legalname", "preferredname", "email", "phone");
//     studentDao.add(student);
//     Student result = studentDao.get("netid").get();
//     assertEquals("netid", result.getNetId());
//     studentDao.delete(student);
//     assertFalse(studentDao.get("netid").isPresent());
//   }

//   @Test
//   public void test_wholeProcess2() throws SQLException {
//     // This method simulates the process of creating student in admin
//     // Just store basic information
//     dbInitializer.dropAllTables();
//     dbInitializer.initialize();
//     StudentDao studentDao = new StudentDao();
//     Student student1 = new Student("hg161", "123456", "haoyuan", "stven", "test@duke.edu", "185");
//     Student student2 = new Student("xm123", "1234567", "xiaoming", "jack", "test2@duke.edu", "186");
//     studentDao.add(student1);
//     studentDao.add(student2);

//     // creation phase is over, you can ignore the following tests
//     assertEquals(2, studentDao.getAll().size());
//     assertEquals("hg161", studentDao.getAll().get(0).getNetId());
//     assertEquals("haoyuan", studentDao.getAll().get(0).getLegalName());
//     assertEquals("xm123", studentDao.getAll().get(1).getNetId());
//     assertEquals("xiaoming", studentDao.getAll().get(1).getLegalName());
//   }

// }
