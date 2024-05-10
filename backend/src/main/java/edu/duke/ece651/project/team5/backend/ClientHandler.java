// package edu.duke.ece651.project.team5.backend;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.PrintWriter;
// import java.net.Socket;

// import edu.duke.ece651.project.team5.backend.utils.ReportGenerator;
// import edu.duke.ece651.project.team5.backend.utils.RequestController;
// import edu.duke.ece651.project.team5.backend.service.*;

// /**
//  * This class is used to handle the client request
//  */
// public class ClientHandler implements Runnable {
//     private Socket socket;
//     private BufferedReader in;
//     private PrintWriter out;
//     private AuthService authService;
//     private CourseService courseService;
//     private StudentService studentService;
//     private SessionService sessionService;
//     private ReportGenerator reportGenerator;

//     /**
//      * Constructor for the client handler
//      * 
//      * @param socket          the socket
//      * @param authService     the auth service
//      * @param courseService   the course service
//      * @param studentService  the student service
//      * @param sessionService  the session service
//      * @param reportGenerator the report generator
//      * @throws IOException
//      */
//     public ClientHandler(Socket socket, AuthService authService, CourseService courseService,
//             StudentService studentService, SessionService sessionService, ReportGenerator reportGenerator)
//             throws IOException {
//         this.socket = socket;
//         this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//         this.out = new PrintWriter(socket.getOutputStream(), true);
//         this.authService = authService;
//         this.courseService = courseService;
//         this.studentService = studentService;
//         this.sessionService = sessionService;
//     }

//     /**
//      * Run method for the client handler
//      */
//     @Override
//     public void run() {
//         try {
//             String message;
//             while ((message = in.readLine()) != null) {
//                 System.out.println("Message Received: " + message);
//                 RequestController requestController = new RequestController(authService, courseService,
//                         studentService, sessionService, reportGenerator, out);
//                 requestController.handleRequest(message);

//                 if (message.equalsIgnoreCase("exit")) {
//                     throw new RuntimeException("Client requested server shutdown");
//                 }
//             }
//         } catch (IOException e) {
//             System.out.println("Error handling client# " + Thread.currentThread().getId() + ": " + e.getMessage());
//         } finally {
//             try {
//                 socket.close();
//             } catch (IOException e) {
//                 System.out.println("Couldn't close a socket, what's going on?");
//             }
//             System.out.println("Connection with client# " + Thread.currentThread().getId() + " closed");
//         }
//     }
// }
