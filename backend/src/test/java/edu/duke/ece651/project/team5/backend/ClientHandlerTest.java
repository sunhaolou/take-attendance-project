// package edu.duke.ece651.project.team5.backend;

// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;

// import java.io.ByteArrayInputStream;
// import java.io.ByteArrayOutputStream;
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.net.Socket;
// import java.io.PrintWriter;
// import java.io.StringReader;
// import java.io.StringWriter;

// import org.junit.jupiter.api.Test;

// import edu.duke.ece651.project.team5.backend.service.AuthService;
// import edu.duke.ece651.project.team5.backend.service.CourseService;
// import edu.duke.ece651.project.team5.backend.service.SessionService;
// import edu.duke.ece651.project.team5.backend.service.StudentService;
// import edu.duke.ece651.project.team5.backend.utils.ReportGenerator;

// public class ClientHandlerTest {

//     @Test
//     public void testConstructorInitialization() throws IOException {
//         Socket socket = mock(Socket.class);
//         when(socket.getInputStream()).thenReturn(new ByteArrayInputStream("Test input".getBytes()));
//         when(socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());

//         AuthService authService = mock(AuthService.class);
//         CourseService courseService = mock(CourseService.class);
//         StudentService studentService = mock(StudentService.class);
//         SessionService sessionService = mock(SessionService.class);
//         ReportGenerator reportGenerator = mock(ReportGenerator.class);

//         ClientHandler handler = new ClientHandler(socket, authService, courseService, studentService, sessionService,
//                 reportGenerator);
//         assertNotNull(handler);
//     }

//     @Test
//     public void testMessageHandling() throws IOException {
//         Socket socket = mock(Socket.class);
//         BufferedReader reader = new BufferedReader(new StringReader("hello\nexit"));
//         PrintWriter writer = new PrintWriter(new StringWriter(), true);

//         when(socket.getInputStream()).thenReturn(new ByteArrayInputStream("hello\nexit".getBytes()));
//         when(socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());

//         AuthService authService = mock(AuthService.class);
//         CourseService courseService = mock(CourseService.class);
//         StudentService studentService = mock(StudentService.class);
//         SessionService sessionService = mock(SessionService.class);
//         ReportGenerator reportGenerator = mock(ReportGenerator.class);

//         ClientHandler handler = new ClientHandler(socket, authService, courseService, studentService, sessionService,
//                 reportGenerator);
//         try {

//             handler.run(); // To run the handler logic
//         } catch (RuntimeException e) {

//         }

//         // Verify interactions, e.g., checking if services are called correctly
//     }

// }
