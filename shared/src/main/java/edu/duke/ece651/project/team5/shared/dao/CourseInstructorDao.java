// package edu.duke.ece651.project.team5.shared.dao;

// import edu.duke.ece651.project.team5.shared.model.*;
// import edu.duke.ece651.project.team5.shared.*;
// import java.sql.*;
// import java.util.*;

// public class CourseInstructorDao {
//     protected Connection connection;

//     public CourseInstructorDao() {
//         try {
//             this.connection = DB.getConnection();
//         } catch (Exception e) {
//             System.err.println("Failed to initialize database connection: " + e.getMessage());
//         }
//     }

//     public List<String> getInstructorsByCourseSection(String courseSectionId) throws SQLException {
//         List<String> result = new ArrayList<>();
//         PreparedStatement pstmt = null;
//         try {
//             String SQL = "SELECT professor_netid FROM course_instructor WHERE course_section_id = ?";
//             pstmt = connection.prepareStatement(SQL);
//             pstmt.setString(1, courseSectionId);
//             ResultSet data = pstmt.executeQuery();
//             while (data.next()) {
//                 String professor_netid = data.getString("professor_netid");
//                 result.add(professor_netid);
//             }
//         } finally {
//             pstmt.close();
//         }
//         return result;
//     }

//     public Set<String> getCourseSectionByProfessorId(String professorNetId) throws SQLException {
//         Set<String> result = new HashSet<>();
//         PreparedStatement pstmt = null;
//         try {
//             String SQL = "SELECT course_section_id FROM course_instructor WHERE professor_netid = ?";
//             pstmt = connection.prepareStatement(SQL);
//             pstmt.setString(1, professorNetId);
//             ResultSet data = pstmt.executeQuery();
//             while (data.next()) {
//                 String course_section_id = data.getString("course_section_id");
//                 result.add(course_section_id);
//             }
//         } finally {
//             pstmt.close();
//         }
//         return result;
//     }

//     public void add(String professorNetId, String courseSectionId) throws SQLException {
//         PreparedStatement pstmt = null;
//         try {
//             String SQL = "INSERT INTO course_instructor (course_section_id, professor_netid) VALUES (?, ?)";
//             pstmt = connection.prepareStatement(SQL);
//             pstmt.setString(1, courseSectionId);
//             pstmt.setString(2, professorNetId);
//             pstmt.executeUpdate();
//         } finally {
//             pstmt.close();
//         }
//     }

//     public void delete(String professorNetId, String courseSectionId) throws SQLException {
//         PreparedStatement pstmt = null;
//         try {
//             String SQL = "DELETE FROM course_instructor WHERE course_section_id = ? AND professor_netid = ?";
//             pstmt = connection.prepareStatement(SQL);
//             pstmt.setString(1, courseSectionId);
//             pstmt.setString(2, professorNetId);
//             pstmt.executeUpdate();
//         } finally {
//             pstmt.close();
//         }
//     }
// }
