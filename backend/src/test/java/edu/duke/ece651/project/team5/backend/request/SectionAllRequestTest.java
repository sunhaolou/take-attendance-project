// package edu.duke.ece651.project.team5.backend.request;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// class SectionAllRequestTest {

//     @Test
//     void testGetUserNetId() {
//         SectionAllRequest request = new SectionAllRequest();
//         String userNetId = "testUser";
//         request.setUserNetId(userNetId);
//         assertEquals(userNetId, request.getUserNetId());
//     }

//     @Test
//     void testGetPassword() {
//         SectionAllRequest request = new SectionAllRequest();
//         String password = "testPassword";
//         request.setPassword(password);
//         assertEquals(password, request.getPassword());
//     }

//     @Test
//     void testGetFlag() {
//         SectionAllRequest request = new SectionAllRequest();
//         String flag = "testFlag";
//         request.setFlag(flag);
//         assertEquals(flag, request.getFlag());
//     }

//     @Test
//     void testDefaultConstructor() {
//         SectionAllRequest request = new SectionAllRequest();
//         assertNull(request.getUserNetId());
//         assertNull(request.getPassword());
//         assertNull(request.getFlag());
//     }
// }