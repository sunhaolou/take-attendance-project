// package edu.duke.ece651.project.team5.shared.dao;

// import edu.duke.ece651.project.team5.shared.enums.*;
// import java.util.*;
// import java.sql.*;

// public class NotificationMethodDao extends BaseDao<NotificationMethod> {
//     public NotificationMethodDao() {
//         super();
//     }

//     @Override
//     public Optional<NotificationMethod> get(String notification_method_id) throws SQLException {
//         return Optional.of(NotificationMethod.getById(Integer.parseInt(notification_method_id)));
//     }

//     @Override
//     public void add(NotificationMethod method) throws SQLException {
//         PreparedStatement pstmt = null;
//         try {
//             String SQL = "INSERT INTO notification_method (notification_method) VALUES (?)";
//             pstmt = connection.prepareStatement(SQL);
//             pstmt.setString(1, method.name());
//             pstmt.executeUpdate();
//         } finally {
//             pstmt.close();
//         }
//     }

//     @Override
//     public void update(NotificationMethod method) throws SQLException {
//         PreparedStatement pstmt = null;
//         try {
//             String SQL = "UPDATE notification_method SET notification_method = ? WHERE notification_method_id = ?";
//             pstmt = connection.prepareStatement(SQL);
//             pstmt.setString(1, method.name());
//             pstmt.setInt(2, method.getId());
//             pstmt.executeUpdate();
//         } finally {
//             pstmt.close();
//         }
//     }

//     @Override
//     public void delete(NotificationMethod method) throws SQLException {
//         // PreparedStatement pstmt = null;
//         // try {
//         //     String SQL = "DELETE FROM notification_method WHERE notification_method_id = ?";
//         //     pstmt = connection.prepareStatement(SQL);
//         //     pstmt.setInt(1, method.getId());
//         //     pstmt.executeUpdate();
//         // } finally {
//         //     pstmt.close();
//         // }
//     }
// }