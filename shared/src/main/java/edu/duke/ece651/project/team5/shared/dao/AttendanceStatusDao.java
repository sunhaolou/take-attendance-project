// package edu.duke.ece651.project.team5.shared.dao;

// import edu.duke.ece651.project.team5.shared.enums.*;
// import java.util.*;
// import java.sql.*;

// public class AttendanceStatusDao extends BaseDao<AttendanceStatus> {
//     public AttendanceStatusDao() {
//         super();
//     }

//     @Override
//     public Optional<AttendanceStatus> get(String attendance_status_id) throws SQLException {
//         return Optional.of(AttendanceStatus.getById(Integer.parseInt(attendance_status_id)));
//     }
    
//     @Override
//     public void add(AttendanceStatus status) throws SQLException {
//         PreparedStatement pstmt = null;
//         try {
//             String SQL = "INSERT INTO attendance_status (attendance_status) VALUES (?)";
//             pstmt = connection.prepareStatement(SQL);
//             pstmt.setString(1, status.name());
//             pstmt.executeUpdate();
//         } finally {
//             pstmt.close();
//         }
//     }

//     @Override
//     public void update(AttendanceStatus status) throws SQLException {
//         /*
//         PreparedStatement pstmt = null;
//         try {
//             String SQL = "UPDATE attendance_status SET attendance_status = ? WHERE attendance_status_id = ?";
//             pstmt = connection.prepareStatement(SQL);
//             pstmt.setString(1, status.name());
//             pstmt.setInt(2, status.getId());
//             pstmt.executeUpdate();
//         } finally {
//             pstmt.close();
//         }*/
//     }

//     @Override
//     public void delete(AttendanceStatus status) throws SQLException {
//         /*
//         PreparedStatement pstmt = null;
//         try {
//             String SQL = "DELETE FROM attendance_status WHERE attendance_status_id = ?";
//             pstmt = connection.prepareStatement(SQL);
//             pstmt.setInt(1, status.getId());
//             pstmt.executeUpdate();
//         } finally {
//             pstmt.close();
//         }*/
//     }
// }