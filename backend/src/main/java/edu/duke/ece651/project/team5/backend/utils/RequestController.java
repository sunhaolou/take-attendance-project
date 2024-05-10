// package edu.duke.ece651.project.team5.backend.utils;

// import java.io.PrintWriter;
// import java.sql.SQLException;
// import java.util.List;
// import java.util.Arrays;

// import edu.duke.ece651.project.team5.shared.model.*;
// import java.sql.Timestamp;
// import java.util.Date;

// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import edu.duke.ece651.project.team5.backend.service.*;
// import edu.duke.ece651.project.team5.backend.request.*;
// import edu.duke.ece651.project.team5.shared.dao.*;
// import edu.duke.ece651.project.team5.shared.*;

// /**
//  * The RequestController class handles incoming requests and delegates them to the appropriate methods for processing.
//  * It provides methods for handling login requests, retrieving section and student information, taking and updating attendance,
//  * generating reports, and updating student notifications.
//  */
// public class RequestController {
//     private AuthService authService;
//     private CourseService courseService;
//     private StudentService studentService;
//     private SessionService sessionService;

//     private ReportGenerator reportGenerator;
//     private PrintWriter out;

//     /**
//      * Constructs a RequestController object with the specified AuthService, CourseService, StudentService, SessionService, and PrintWriter.
//      * 
//      * @param authService the AuthService to be used
//      * @param courseService the CourseService to be used
//      * @param studentService the StudentService to be used
//      * @param sessionService the SessionService to be used
//      * @param out the PrintWriter to be used
//      */
//     public RequestController(AuthService authService, CourseService courseService, StudentService studentService,
//             SessionService sessionService, PrintWriter out) {
//         this(authService, courseService, studentService, sessionService,
//                 new ReportGenerator(studentService, courseService), out);
//     }


//     public RequestController(AuthService authService, CourseService courseService, StudentService studentService,
//             SessionService sessionService, ReportGenerator reportGenerator, PrintWriter out) {
//         this.authService = authService;
//         this.courseService = courseService;
//         this.studentService = studentService;
//         this.sessionService = sessionService;

//         this.reportGenerator = reportGenerator;
//         this.out = out;

//     }


//     /**
//      * Handles the incoming request and delegates it to the appropriate method for processing.
//      * 
//      * @param request the request to be processed
//      */
//     public void handleRequest(String request) {

//         try {

//             ObjectMapper mapper = new ObjectMapper();
//             JsonNode rootNode = mapper.readTree(request);
//             String flag = rootNode.get("flag").asText();
//             ObjectMapper objectMapper = new ObjectMapper();
//             switch (flag) {
//                 case "professor_login":
//                     LoginRequest loginProfessorRequest = objectMapper.readValue(request,
//                             LoginRequest.class);
//                     try {
//                         handleLoginRequest(loginProfessorRequest);
//                         out.println("success");
//                     } catch (Exception e) {
//                         out.println(e.getMessage());
//                     }

//                     break;
//                 case "student_login":
//                     LoginRequest loginStudentRequest = objectMapper.readValue(request,
//                             LoginRequest.class);
//                     try {

//                         handleLoginRequest(loginStudentRequest);
//                         out.println("success");
//                     } catch (Exception e) {
//                         out.println(e.getMessage());
//                     }

//                     break;

//                 case "section_all_id":
//                     SectionAllRequest sectionAllIdRequest = objectMapper.readValue(request, SectionAllRequest.class);
//                     try {
//                         List<String> sectionIds = handleSectionIdsAllRequest(sectionAllIdRequest);
//                         out.println(objectMapper.writeValueAsString(sectionIds));
//                     } catch (Exception e) {
//                         out.println(e.getMessage());
//                     }
//                     break;

//                 case "get_student_for_section":
//                     GetStudentForSectionRequest getEnrolledStudentForSectionRequest = objectMapper.readValue(request,
//                             GetStudentForSectionRequest.class);
//                     try {
//                         List<Student> enrolledStudents = handleGetEnrolledstudentForSectionRequest(
//                                 getEnrolledStudentForSectionRequest);
//                         out.println(objectMapper.writeValueAsString(enrolledStudents));
//                     } catch (Exception e) {
//                         out.println(e.getMessage());
//                     }
//                     break;

//                 case "get_student_by_name_section":
//                     GetStudentBySectionAndLegalNameRequest getStudentBySectionAndLegalNameRequest = objectMapper
//                             .readValue(request,
//                                     GetStudentBySectionAndLegalNameRequest.class);
//                     try {
//                         List<Student> studentsByCourseAndName = handleGetStudentBySectionAndLegalNameRequest(
//                                 getStudentBySectionAndLegalNameRequest);
//                         out.println(objectMapper.writeValueAsString(studentsByCourseAndName));
//                     } catch (Exception e) {
//                         out.println(e.getMessage());
//                     }
//                     break;

//                 case "session_all_id":
//                     GetSessionRequest getSessionIdRequest = objectMapper.readValue(request, GetSessionRequest.class);
//                     try {
//                         List<String> sessionIds = handleGetSessionIdsRequest(getSessionIdRequest);
//                         out.println(objectMapper.writeValueAsString(sessionIds));
//                     } catch (Exception e) {
//                         out.println(e.getMessage());
//                     }
//                     break;

//                 case "session_attendance":
//                     TakeAndUpdateAttendanceRequest takeAttendanceRequest = objectMapper.readValue(request,
//                             TakeAndUpdateAttendanceRequest.class);
//                     try {
//                         handleTakeAndUpdateAttendanceRequest(takeAttendanceRequest);
//                         out.println("attendance taken");
//                     } catch (Exception e) {
//                         out.println(e.getMessage());
//                     }
//                     break;

//                 case "session_attendance_update":
//                     TakeAndUpdateAttendanceRequest updateAttendanceRequest = objectMapper.readValue(request,
//                             TakeAndUpdateAttendanceRequest.class);
//                     try {
//                         handleTakeAndUpdateAttendanceRequest(updateAttendanceRequest);
//                         out.println("attendance updated");
//                     } catch (Exception e) {
//                         out.println(e.getMessage());
//                     }
//                     break;
//                 case "section_report":
//                     GetSectionReportRequest getSectionReportRequest = objectMapper.readValue(request,
//                             GetSectionReportRequest.class);
//                     try {
//                         SectionReport report = handleSectionReportRequest(getSectionReportRequest);
//                         out.println(objectMapper.writeValueAsString(report));
//                     } catch (Exception e) {
//                         out.println(e.getMessage());
//                     }
//                     break;

//                 case "student_report":
//                     GetStudentReportRequest getStudentReportRequest = objectMapper.readValue(request,
//                             GetStudentReportRequest.class);

//                     try {
//                         StudentReport report = handleStudentReportRequest(getStudentReportRequest);
//                         out.println(objectMapper.writeValueAsString(report));
//                     } catch (Exception e) {
//                         out.println(e.getMessage());
//                     }
//                     break;
//                 case "student_update_notification":
//                     UpdateStudentNotificationRequest updateStudentNotificationRequest = objectMapper.readValue(request,
//                             UpdateStudentNotificationRequest.class);
//                     try {
//                         handleStudentUpdateNotificationRequest(updateStudentNotificationRequest);
//                         out.println("success");
//                     } catch (Exception e) {
//                         out.println(e.getMessage());
//                     }
//                     break;

//                 default:
//                     out.println("unknown request");
//                     throw new IllegalArgumentException("unknown request");

//             }

//         } catch (Exception e) {

//             // e.printStackTrace();
//         }

//     }

//     /**
//      * Handles a login request.
//      * 
//      * @param signupRequest the login request to be processed
//      * @throws SQLException if there is an error accessing the database
//      */
//     private void handleLoginRequest(LoginRequest signupRequest) throws SQLException {
//         String userNetId = signupRequest.getuserNetId();
//         String password = signupRequest.getPassword();
//         String flag = signupRequest.getFlag();
//         if (flag.equals("professor_login")) {
//             authService.loginProfessor(userNetId, password);
//         } else {
//             authService.loginStudent(userNetId, password);
//         }

//     }

//     // /**
//     //  * Handles a request to retrieve all section IDs.
//     //  * 
//     //  * @param sectionAllRequest the request to retrieve all section IDs
//     //  * @return a list of all section IDs
//     //  * @throws SQLException if there is an error accessing the database
//     //  */
//     // public List<String> handleSectionIdsAllRequest(SectionAllRequest sectionAllRequest) throws SQLException {
//     //     String userNetId = sectionAllRequest.getUserNetId();
//     //     String password = sectionAllRequest.getPassword();
//     //     String flag = sectionAllRequest.getFlag();
//     //     Professor professor = authService.loginProfessor(userNetId, password);
//     //     List<String> sectionIds = courseService.getSectionIds(professor);
//     //     // List<String> sectionIds = Arrays.asList("ece651_1", "ece651_2");
//     //     return sectionIds;

//     // }

//     /**
//      * Handles a request to retrieve all students enrolled in a section.
//      * 
//      * @param getStudentForSectionRequest the request to retrieve all students enrolled in a section
//      * @return a list of all students enrolled in the section
//      * @throws SQLException if there is an error accessing the database
//      */
//     // public List<Student> handleGetEnrolledstudentForSectionRequest(
//     //         GetStudentForSectionRequest getStudentForSectionRequest) throws SQLException {
//     //     String userNetId = getStudentForSectionRequest.getUserNetId();
//     //     String password = getStudentForSectionRequest.getPassword();
//     //     String sectionId = getStudentForSectionRequest.getCourseSectionId();
//     //     String flag = getStudentForSectionRequest.getFlag();
//     //     Professor professor = authService.loginProfessor(userNetId, password);
//     //     Section section = courseService.getSectionById(sectionId);
//     //     List<Student> students = courseService.getEnrolledStudentsFromSection(section);
//     //     // List<Student> students = Arrays.asList(new Student("123", "123", "haha",
//     //     // "123", "123"),
//     //     // new Student("312", "ss", "bbb", "123", "123", "123"));

//     //     return students;

//     // }

//     /**
//      * Handles a request to retrieve all students enrolled in a section by legal name.
//      * 
//      * @param getStudentBySectionAndLegalNameRequest the request to retrieve all students enrolled in a section by legal name
//      * @return a list of all students enrolled in the section by legal name
//      * @throws SQLException if there is an error accessing the database
//      */
//     public List<Student> handleGetStudentBySectionAndLegalNameRequest(
//             GetStudentBySectionAndLegalNameRequest getStudentBySectionAndLegalNameRequest) throws SQLException {
//         String userNetId = getStudentBySectionAndLegalNameRequest.getUserNetId();
//         String password = getStudentBySectionAndLegalNameRequest.getPassword();
//         String legalName = getStudentBySectionAndLegalNameRequest.getLegalName();
//         String sectionId = getStudentBySectionAndLegalNameRequest.getCourseSectionId();
//         Professor professor = authService.loginProfessor(userNetId, password);
//         List<Student> students = studentService.searchStudentsByLegalName(legalName, sectionId);
//         // List<Student> students = Arrays.asList(new Student("123", "123", "haha",
//         // "123", "123"),
//         // new Student("312", "ss", "bbb", "123", "123", "123"));
//         return students;
//     }

//     /**
//      * Handles a request to retrieve all session IDs of a section.
//      * 
//      * @param getSessionRequest the request to retrieve all session IDs of a section
//      * @return a list of all session IDs of the section
//      * @throws SQLException if there is an error accessing the database
//      */
//     public List<String> handleGetSessionIdsRequest(GetSessionRequest getSessionRequest) throws SQLException {
//         String userNetId = getSessionRequest.getUserNetId();
//         String password = getSessionRequest.getPassword();
//         String sectionId = getSessionRequest.getCourseSectionId();
//         Professor professor = authService.loginProfessor(userNetId, password);
//         Section section = courseService.getSectionById(sectionId);
//         List<String> sessionIds = sessionService.getAllSessionIdsOfSection(section);
//         // List<String> sessionIds = Arrays.asList("123", "312");

//         return sessionIds;
//     }

//     /**
//      * Handles a request to take or update attendance.
//      * 
//      * @param takeAndUpdateAttendanceRequest the request to take or update attendance
//      * @throws SQLException if there is an error accessing the database
//      */
//     public void handleTakeAndUpdateAttendanceRequest(TakeAndUpdateAttendanceRequest takeAndUpdateAttendanceRequest)
//             throws SQLException {
//         String userNetId = takeAndUpdateAttendanceRequest.getUserNetId();
//         String password = takeAndUpdateAttendanceRequest.getPassword();
//         String sessionId = takeAndUpdateAttendanceRequest.getSessionId();
//         String flag = takeAndUpdateAttendanceRequest.getFlag();
//         Session session = sessionService.getSessionById(sessionId);
//         Professor professor = authService.loginProfessor(userNetId, password);
//         Student student = studentService.getStudentByNetId(takeAndUpdateAttendanceRequest.getNetId());
//         if (flag.equals("session_attendance")) {
//             sessionService.takeAttendance(session, student,
//                     takeAndUpdateAttendanceRequest.getStatus());
//         } else
//             sessionService.updateAttendance(session, student,
//                     takeAndUpdateAttendanceRequest.getStatus(), reportGenerator);
//     }

//     /**
//      * Handles a request to generate a section report.
//      * 
//      * @param getSectionReportRequest the request to generate a section report
//      * @return the section report
//      * @throws Exception if there is an error accessing the database
//      */
//     public SectionReport handleSectionReportRequest(GetSectionReportRequest getSectionReportRequest) throws Exception {
//         String userNetId = getSectionReportRequest.getUserNetId();
//         String password = getSectionReportRequest.getPassword();
//         String sectionId = getSectionReportRequest.getCourseSectionId();
//         Professor professor = authService.loginProfessor(userNetId, password);
//         Section section = courseService.getSectionById(sectionId);
//         SectionReport report = reportGenerator.generateSectionReport(section);
//         return report;
//     }

//     /**
//      * Handles a request to generate a student report.
//      * 
//      * @param getStudentReportRequest the request to generate a student report
//      * @return the student report
//      * @throws Exception if there is an error accessing the database
//      */
//     public StudentReport handleStudentReportRequest(GetStudentReportRequest getStudentReportRequest) throws Exception {
//         String userNetId = getStudentReportRequest.getUserNetId();
//         String password = getStudentReportRequest.getPassword();

//         Student student = authService.loginStudent(userNetId, password);
//         StudentReport report = reportGenerator.generateStudentReport(student);
//         return report;
//     }

//     /**
//      * Handles a request to update a student's notification preference.
//      * 
//      * @param updateStudentNotificationRequest the request to update a student's notification preference
//      * @throws SQLException if there is an error accessing the database
//      */
//     public void handleStudentUpdateNotificationRequest(
//             UpdateStudentNotificationRequest updateStudentNotificationRequest) throws SQLException {
//         String userNetId = updateStudentNotificationRequest.getUserNetId();
//         String password = updateStudentNotificationRequest.getPassword();
//         String courseSectionId = updateStudentNotificationRequest.getCourseSectionId();
//         String notificationMethod = updateStudentNotificationRequest.getNotificationMethod();
//         Student student = authService.loginStudent(userNetId, password);
//         studentService.updateStudentNotificationPreference(student, courseSectionId,
//                 notificationMethod);

//     }

// }
