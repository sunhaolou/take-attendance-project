// package edu.duke.ece651.project.team5.backend;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import java.io.*;
// import java.net.*;
// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import java.io.*;
// import java.net.*;

// class MyServerTest {

// @Test
// void testServerShutdownOnExitCommand() throws Exception {
// // Start server on a separate thread
// MyServer server = new MyServer();
// Thread serverThread = new Thread(() -> {
// try {
// server.start(8080); // Assuming start doesn't block indefinitely after 'exit'
// received
// } catch (Exception e) {
// // Expecting an interruption or socket closure
// System.out.println("Server is shutting down: " + e.getMessage());
// }
// });
// serverThread.start();

// // Allow time for the server to start
// Thread.sleep(500); // Time to allow server to bind and listen

// // Simulate client and send 'exit' command
// try (Socket clientSocket = new Socket("localhost", 8080);
// PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
// BufferedReader in = new BufferedReader(new
// InputStreamReader(clientSocket.getInputStream()))) {
// out.println("exit");

// // Optionally, read any response if expected
// } catch (IOException e) {
// fail("Failed to connect or communicate with the server: " + e.getMessage());
// }

// // Wait to ensure server processes the command
// Thread.sleep(500); // Adjust based on server response time

// // Check if server has stopped (indicated by the server thread being
// terminated)
// serverThread.join(1000); // Wait for the server thread to end
// // assertFalse(serverThread.isAlive(), "Server did not shut down as
// expected.");
// }
// }
