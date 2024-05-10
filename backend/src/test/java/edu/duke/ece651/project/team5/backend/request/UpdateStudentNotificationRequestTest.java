// package edu.duke.ece651.project.team5.backend.request;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// public class UpdateStudentNotificationRequestTest {
//     @Test
//     public void testGettersAndSetters() {
//         UpdateStudentNotificationRequest request = new UpdateStudentNotificationRequest();

//         // Set values using setters
//         request.setUserNetId("user123");
//         request.setCourseSectionId("section456");
//         request.setPassword("password");
//         request.setNotificationMethod("true");
//         request.setFlag("flag123");

//         // Verify values using getters
//         assertEquals("user123", request.getUserNetId());
//         assertEquals("section456", request.getCourseSectionId());
//         assertEquals("password", request.getPassword());
//         assertEquals("true", request.getNotificationMethod());
//         assertEquals("flag123", request.getFlag());
//     }

//     @Test
//     public void testDefaultConstructor() {
//         UpdateStudentNotificationRequest request = new UpdateStudentNotificationRequest();

//         // Verify default values
//         assertNull(request.getUserNetId());
//         assertNull(request.getCourseSectionId());
//         assertNull(request.getPassword());
//         assertNull(request.getNotificationMethod());
//         assertNull(request.getFlag());
//     }
// }