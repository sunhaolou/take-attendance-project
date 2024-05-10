// package edu.duke.ece651.project.team5.shared.dao;

// import edu.duke.ece651.project.team5.shared.enums.*;
// import java.util.*;
// import java.sql.*;

// public class EnrollmentStatusDao extends BaseDao<EnrollmentStatus> {
//     public EnrollmentStatusDao() {
//         super();
//     }

//     @Override
//     public Optional<EnrollmentStatus> get(String enrollment_status_id) throws SQLException {
//         return Optional.of(EnrollmentStatus.getById(Integer.parseInt(enrollment_status_id)));
//     }
    
//     @Override
//     public void add(EnrollmentStatus status) throws SQLException {
//         PreparedStatement pstmt = null;
//         try {
//             String SQL = "INSERT INTO enrollment_status (enrollment_status) VALUES (?)";
//             pstmt = connection.prepareStatement(SQL);
//             pstmt.setString(1, status.name());
//             pstmt.executeUpdate();
//         } finally {
//             pstmt.close();
//         }
//     }

//     @Override
//     public void update(EnrollmentStatus status) throws SQLException {
//         PreparedStatement pstmt = null;
//         try {
//             String SQL = "UPDATE enrollment_status SET enrollment_status = ? WHERE enrollment_status_id = ?";
//             pstmt = connection.prepareStatement(SQL);
//             pstmt.setString(1, status.name());
//             pstmt.setInt(2, status.getId());
//             pstmt.executeUpdate();
//         } finally {
//             pstmt.close();
//         }
//     }

//     @Override
//     public void delete(EnrollmentStatus status) throws SQLException {
//         /*PreparedStatement pstmt = null;
//         try {
//             String SQL = "DELETE FROM enrollment_status WHERE enrollment_status_id = ?";
//             pstmt = connection.prepareStatement(SQL);
//             pstmt.setInt(1, status.getId());
//             pstmt.executeUpdate();
//         } finally {
//             pstmt.close();
//         }*/
//     }
// }