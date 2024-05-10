// package edu.duke.ece651.project.team5.backend;

// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.fail;

// import java.io.IOException;
// import java.net.Socket;

// import org.junit.jupiter.api.Test;

// public class AppTest {
//     @Test
//     public void testMainServerStart() {
//         // Start the server in a separate thread to avoid blocking the test
//         Thread serverThread = new Thread(() -> App.main(new String[] {}));
//         serverThread.start();

//         // Allow some time for the server to start
//         try {
//             Thread.sleep(1000); // wait for the server to initialize
//         } catch (InterruptedException e) {
//             Thread.currentThread().interrupt();
//         }

//         // Check if the server is up by trying to connect to it
//         try (Socket clientSocket = new Socket("localhost", 8080)) {
//             assertTrue(clientSocket.isConnected(), "Server should be running and accept connections.");
//         } catch (IOException e) {
//             fail("Should have connected to the server but failed: " + e.getMessage());
//         } finally {
//             serverThread.interrupt(); // Attempt to stop the server
//         }
//     }
// }
