// package edu.duke.ece651.project.team5.backend;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.PrintWriter;
// import java.net.ServerSocket;
// import java.net.Socket;

// import java.lang.Thread;

// import edu.duke.ece651.project.team5.backend.service.*;
// import edu.duke.ece651.project.team5.backend.utils.ReportGenerator;
// import edu.duke.ece651.project.team5.backend.utils.ReportScheduler;
// import edu.duke.ece651.project.team5.shared.model.*;
// import edu.duke.ece651.project.team5.shared.DatabaseInitializer;
// import edu.duke.ece651.project.team5.shared.dao.*;
// import edu.duke.ece651.project.team5.shared.*;
// import edu.duke.ece651.project.team5.shared.utils.*;

// import java.util.concurrent.Executors;
// import java.util.concurrent.ScheduledExecutorService;
// import java.util.concurrent.TimeUnit;

// /**
//  * This class is used to start the server
//  */
// public class MyServer {
//     private ServerSocket serverSocket;
//     private AuthService authService;
//     private CourseService courseService;
//     private StudentService studentService;
//     private SessionService sessionService;
//     private ReportGenerator reportGenerator;

//     /**
//      * Constructor for the server
//      */
//     public MyServer() {
//         SectionDao sectionDao = new SectionDao();
//         StudentDao studentDao = new StudentDao();
//         ProfessorDao professorDao = new ProfessorDao();
//         LectureDao sessionDao = new LectureDao();

//         this.authService = new AuthService(professorDao, studentDao);
//         this.studentService = new StudentService(studentDao, sectionDao);
//         this.courseService = new CourseService(sectionDao, studentService);
//         this.sessionService = new SessionService(sessionDao);
//         this.reportGenerator = new ReportGenerator(studentService, courseService);
        

//     }

//     /**
//      * Start the server
//      * 
//      * @param port the port to start the server on
//      * @throws Exception
//      */
//     public void start(int port) throws Exception {
//         setupPeriodicTasks();
//         serverSocket = new ServerSocket(port);
//         System.out.println("Server started on port " + port);
//         while (true) {
//             System.out.println("Waiting for client request");
//             Socket socket = serverSocket.accept();
//             System.out.println("Client connected");
//             try {

//                 new Thread(new ClientHandler(socket, authService, courseService, studentService, sessionService,
//                         reportGenerator)).start();
//             } catch (RuntimeException e) {
//                 break;
//             }

//         }
//         stop();
//     }

//     /**
//      * Stop the server
//      * 
//      * @throws Exception
//      */
//     public void stop() throws Exception {
//         serverSocket.close();
//         System.out.println("Server stopped.");
//     }

//     /**
//      * Main method to start the server
//      * 
//      * @param args
//      */
//     private void setupPeriodicTasks() {
//         ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//         Runnable task = new Runnable() {
//             public void run() {
//                 System.out.println("Generating report...");
//                 reportGenerator.generateReport();
//             }
//         };
//         long initialDelay = ReportScheduler.calculateInitialDelay();
//         long period = TimeUnit.DAYS.toSeconds(7);

//         scheduler.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
//     }
// }
