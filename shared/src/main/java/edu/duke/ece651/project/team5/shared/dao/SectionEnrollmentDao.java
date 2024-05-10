// package edu.duke.ece651.project.team5.shared.dao;

// import java.sql.*;
// import java.util.*;

// import edu.duke.ece651.project.team5.shared.DB;
// import edu.duke.ece651.project.team5.shared.enums.EnrollmentStatus;

// public class SectionEnrollmentDao {
//     protected Connection connection;

//     public SectionEnrollmentDao() {
//         try {
//             this.connection = DB.getConnection();
//         } catch (Exception e) {
//             System.err.println("Failed to initialize database connection: " + e.getMessage());
//         }
//     }

//     public Optional<Map<String, EnrollmentStatus>> get(String id) throws SQLException {
//         String sql = "SELECT * FROM section_enrollment WHERE course_section_id = ?";
//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             statement.setString(1, id);
//             try (ResultSet resultSet = statement.executeQuery()) {
//                 Map<String, EnrollmentStatus> enrollment = new HashMap<>();
//                 while (resultSet.next()) {
//                     enrollment.put(resultSet.getString("student_netid"),
//                             EnrollmentStatus.getById(resultSet.getInt("enrollment_status_id")));
//                 }
//                 if (enrollment.isEmpty()) {
//                     return Optional.empty();
//                 } else {
//                     return Optional.of(enrollment);
//                 }
//             }
//         }
//     }

//     public Optional<Map<String, EnrollmentStatus>> getEnrollmentStatusByStudentNetId(String netId) throws SQLException {
//         String sql = "SELECT * FROM section_enrollment WHERE student_netid = ?";
//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             statement.setString(1, netId);
//             try (ResultSet resultSet = statement.executeQuery()) {
//                 Map<String, EnrollmentStatus> enrollment = new HashMap<>();
//                 while (resultSet.next()) {
//                     enrollment.put(resultSet.getString("course_section_id"),
//                             EnrollmentStatus.getById(resultSet.getInt("enrollment_status_id")));
//                 }
//                 if (enrollment.isEmpty()) {
//                     return Optional.empty();
//                 } else {
//                     return Optional.of(enrollment);
//                 }
//             }
//         }
//     }

//     public void add(Map<String, EnrollmentStatus> enrollment, String courseSectionId) throws SQLException {
//         String sql = "INSERT INTO section_enrollment (course_section_id, student_netid, enrollment_status_id) VALUES (?, ?, ?)";
//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             for (Map.Entry<String, EnrollmentStatus> entry : enrollment.entrySet()) {
//                 statement.setString(1, courseSectionId);
//                 statement.setString(2, entry.getKey());
//                 statement.setInt(3, entry.getValue().getId());
//                 statement.executeUpdate();
//             }
//         }
//     }

//     public void update(Map<String, EnrollmentStatus> enrollment, String courseSectionId) throws SQLException {

//         String checkSql = "SELECT COUNT(*) FROM section_enrollment WHERE course_section_id = ? AND student_netid = ?";
//         String updateSql = "UPDATE section_enrollment SET enrollment_status_id = ? WHERE course_section_id = ? AND student_netid = ?";
//         String insertSql = "INSERT INTO section_enrollment (enrollment_status_id, course_section_id, student_netid) VALUES (?, ?, ?)";

//         try (PreparedStatement checkStmt = connection.prepareStatement(checkSql);
//                 PreparedStatement updateStmt = connection.prepareStatement(updateSql);
//                 PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
//             for (Map.Entry<String, EnrollmentStatus> entry : enrollment.entrySet()) {
//                 checkStmt.setString(1, courseSectionId);
//                 checkStmt.setString(2, entry.getKey());
//                 ResultSet rs = checkStmt.executeQuery();
//                 rs.next();
//                 int count = rs.getInt(1);

//                 if (count > 0) {
//                     updateStmt.setInt(1, entry.getValue().getId());
//                     updateStmt.setString(2, courseSectionId);
//                     updateStmt.setString(3, entry.getKey());
//                     updateStmt.executeUpdate();
//                 } else {
//                     // Record does not exist, perform insert
//                     insertStmt.setInt(1, entry.getValue().getId());
//                     insertStmt.setString(2, courseSectionId);
//                     insertStmt.setString(3, entry.getKey());
//                     insertStmt.executeUpdate();
//                 }
//             }
//         }

//     }

//     public void delete(String courseSectionId) throws SQLException {
//         String sql = "DELETE FROM section_enrollment WHERE course_section_id = ?";
//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             statement.setString(1, courseSectionId);
//             statement.executeUpdate();
//         }
//     }
//     /*
//      * @Override
//      * public void add(SectionEnrollment e) throws SQLException {
//      * String sql =
//      * "INSERT INTO section_enrollment (course_section_id, student_netid, enrollment_status_id) VALUES (?, ?, ?)"
//      * ;
//      * try (PreparedStatement statement = connection.prepareStatement(sql)) {
//      * statement.setString(1, e.getCourseSectionId());
//      * statement.setString(2, e.getStudentNetId());
//      * statement.setInt(3, e.getEnrollmentStatusId());
//      * statement.executeUpdate();
//      * }
//      * }
//      * 
//      * @Override
//      * public void update(SectionEnrollment t) throws SQLException {
//      * String sql =
//      * "UPDATE section_enrollment SET enrollment_status_id = ? WHERE course_section_id = ? AND student_netid = ?"
//      * ;
//      * try (PreparedStatement statement = connection.prepareStatement(sql)) {
//      * statement.setInt(1, t.getEnrollmentStatusId());
//      * statement.setString(2, t.getCourseSectionId());
//      * statement.setString(3, t.getStudentNetId());
//      * statement.executeUpdate();
//      * }
//      * }
//      * 
//      * @Override
//      * public void delete(SectionEnrollment t) throws SQLException {
//      * String sql =
//      * "DELETE FROM section_enrollment WHERE course_section_id = ? AND student_netid = ?"
//      * ;
//      * try (PreparedStatement statement = connection.prepareStatement(sql)) {
//      * statement.setString(1, t.getCourseSectionId());
//      * statement.setString(2, t.getStudentNetId());
//      * statement.executeUpdate();
//      * }
//      * }
//      * 
//      * @Override
//      * public Optional<SectionEnrollment> get(String id) throws SQLException {
//      * return Optional.empty();
//      * }
//      */

// }