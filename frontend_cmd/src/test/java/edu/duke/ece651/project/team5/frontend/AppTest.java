// package edu.duke.ece651.project.team5.frontend;

// import static org.mockito.Mockito.*;

// import java.io.*;
// import java.net.Socket;
// import org.junit.jupiter.api.Test;

// public class AppTest {

//     @Test
//     public void testInitial_ProfessorLogin_Success() throws IOException {
//         // Mock input
//         BufferedReader inputMock = mock(BufferedReader.class);
//         when(inputMock.readLine()).thenReturn("1").thenReturn("username", "password");
//         InputStream inputStreamMock = mock(InputStream.class);
//         OutputStream outputStreamMock = mock(OutputStream.class);
//         // Mock socket
//         Socket socketMock = mock(Socket.class);
//         when(socketMock.getInputStream()).thenReturn(inputStreamMock);
//         when(socketMock.getOutputStream()).thenReturn(outputStreamMock);
//         BufferedReader mockSocketIn = mock(BufferedReader.class);
//         when(mockSocketIn.readLine()).thenReturn("success");

//         // Create App instance
//         App app = new App(inputMock, socketMock);
//     }

//     @Test
//     public void testInitial_StudentLogin_Failure() throws IOException {
//         // Mock input
//         BufferedReader inputMock = mock(BufferedReader.class);
//         when(inputMock.readLine()).thenReturn("username", "password");

//         // Mock socket
//         Socket socketMock = mock(Socket.class);
//         InputStream inputStreamMock = mock(InputStream.class);
//         BufferedReader socketInMock = mock(BufferedReader.class);
//         OutputStream outputStreamMock = mock(OutputStream.class);
//         PrintWriter socketOutMock = new PrintWriter(outputStreamMock, true);
//         when(socketMock.getInputStream()).thenReturn(inputStreamMock);
//         when(socketMock.getOutputStream()).thenReturn(outputStreamMock);

//         // Mock response from server
//         when(socketInMock.readLine()).thenReturn("");

//         // Create App instance
//         App app = new App(inputMock, socketMock);

//         // Verify that IllegalArgumentException is thrown
//         IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
//             app.initial();
//         });
//     }
// }
