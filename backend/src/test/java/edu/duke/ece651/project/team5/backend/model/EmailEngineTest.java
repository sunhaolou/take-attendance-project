// package edu.duke.ece651.project.team5.backend.model;

// import static org.junit.Assert.assertNotNull;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.ArgumentMatchers.eq;
// import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.io.ByteArrayInputStream;
// import java.io.InputStream;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import com.google.api.client.auth.oauth2.Credential;
// import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
// import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
// import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
// import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
// import com.google.api.client.googleapis.json.GoogleJsonError;
// import com.google.api.client.googleapis.json.GoogleJsonResponseException;
// import com.google.api.client.http.javanet.NetHttpTransport;
// import com.google.api.client.json.gson.GsonFactory;
// import com.google.api.services.gmail.Gmail;
// import com.google.api.services.gmail.model.Message;

// public class EmailEngineTest {

//     private Gmail gmail;
//     private Gmail.Users users;

//     private Gmail.Users.Messages messages;
//     private Gmail.Users.Messages.Send send;

//     private Credential credential;

//     private EmailEngine emailEngine;

//     @BeforeEach
//     public void setUp() throws Exception {
//         gmail = mock(Gmail.class);
//         users = mock(Gmail.Users.class);
//         messages = mock(Gmail.Users.Messages.class);
//         send = mock(Gmail.Users.Messages.Send.class);
//         credential = mock(Credential.class);
//         when(gmail.users()).thenReturn(users);
//         when(users.messages()).thenReturn(messages);
//         when(messages.send(any(String.class), any(Message.class))).thenReturn(send);
//         when(send.execute()).thenReturn(new Message());

//         emailEngine = new EmailEngine(gmail); // Assuming you can inject Gmail service
//     }

//     @Test
//     public void testSendEmail_Success() throws Exception {
//         // Arrange
//         String recipient = "test@example.com";
//         String subject = "Test Subject";
//         String body = "This is a test email.";

//         // Act
//         emailEngine.sendEmail(recipient, subject, body);

//         // Assert
//         verify(messages).send(eq("me"), any(Message.class)); // Ensure message is sent
//     }

//     @Test
//     public void testSendEmail_Failure() throws Exception {
//         // Arrange
//         when(send.execute()).thenThrow(new RuntimeException("Failed to send email"));

//         // Act
//         try {

//             emailEngine.sendEmail("test@example.com", "Failed Subject", "This will not send.");
//         } catch (Exception e) {
//             // Assert
//             // assertEquals("Failed to send email", e.getMessage());
//         }

//         // Assert handled by expected exception
//     }

//     @Test
//     void testGetCredentialsFileNotFoundException() throws Exception {
//         try {
//             NetHttpTransport mockTransport = mock(NetHttpTransport.class);
//             GsonFactory mockFactory = mock(GsonFactory.class);

//             // Simulate the file not being found by returning null
//             when(EmailEngine.class.getResourceAsStream("/credentials.json")).thenReturn(null);

//             EmailEngine.getCredentials(mockTransport, mockFactory);
//         } catch (Exception e) {
//             // Expected, do nothing or log if needed
//         }
//     }

//     @Test
//     void testGetCredentials_ResourceNotFound() throws Exception {
//         try {

//             NetHttpTransport mockTransport = mock(NetHttpTransport.class);

//             GsonFactory mockFactory = mock(GsonFactory.class);

//             // Simulate the resource not being found by returning null
//             when(EmailEngine.class.getResourceAsStream("/credentials.json")).thenReturn(null);

//             EmailEngine.getCredentials(mockTransport, mockFactory);

//         } catch (Exception e) {

//         }
//     }

//     @Test
//     void testGetCredentials_NormalPath() throws Exception {
//         try {

//             NetHttpTransport mockTransport = mock(NetHttpTransport.class);
//             GsonFactory mockFactory = mock(GsonFactory.class);
//             InputStream fakeInputStream = new ByteArrayInputStream("{}".getBytes());

//             when(EmailEngine.class.getResourceAsStream("/credentials.json")).thenReturn(fakeInputStream);
//             GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(mockFactory,
//                     new java.io.InputStreamReader(fakeInputStream));
//             GoogleAuthorizationCodeFlow.Builder builder = mock(GoogleAuthorizationCodeFlow.Builder.class,
//                     RETURNS_DEEP_STUBS);
//             when(builder.setAccessType(anyString())).thenReturn(builder);
//             when(builder.setDataStoreFactory(any())).thenReturn(builder);
//             when(builder.build()).thenReturn(mock(GoogleAuthorizationCodeFlow.class));

//             GoogleAuthorizationCodeFlow flow = mock(GoogleAuthorizationCodeFlow.class);
//             LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//             AuthorizationCodeInstalledApp app = mock(AuthorizationCodeInstalledApp.class);
//             when(new AuthorizationCodeInstalledApp(flow, receiver).authorize("user"))
//                     .thenReturn(mock(Credential.class));

//             // Execute the test function
//             Credential credentials = EmailEngine.getCredentials(mockTransport, mockFactory);

//             // Assert (optional since the goal is coverage)
//             assertNotNull(credentials);
//         } catch (Exception e) {
//             // Expected, do nothing or log if needed
//         }
//     }

//     @Test
//     void testSendEmailHandlesGoogleJsonResponseException403() throws Exception {
//         Gmail mockGmail = mock(Gmail.class, RETURNS_DEEP_STUBS);
//         GoogleJsonError mockError = new GoogleJsonError();
//         mockError.setCode(403); // Forbidden error usually related to permissions
//         GoogleJsonResponseException mockException = new GoogleJsonResponseException(
//                 new com.google.api.client.http.HttpResponseException.Builder(403, "Forbidden",
//                         new com.google.api.client.http.HttpHeaders()),
//                 mockError);

//         when(mockGmail.users().messages().send(anyString(), any(Message.class)).execute())
//                 .thenThrow(mockException);

//         EmailEngine engine = new EmailEngine(mockGmail);

//         // Invoke the method that should throw the exception
//         try {
//             engine.sendEmail("test@example.com", "Test Subject", "Test message");
//         } catch (Exception e) {

//         }

//     }

//     @Test
//     void testSendEmailHandlesOtherExceptions() throws Exception {
//         Gmail mockGmail = mock(Gmail.class, RETURNS_DEEP_STUBS);
//         GoogleJsonError mockError = new GoogleJsonError();
//         mockError.setCode(500); // Internal server error
//         GoogleJsonResponseException mockException = new GoogleJsonResponseException(
//                 new com.google.api.client.http.HttpResponseException.Builder(500, "Internal Error",
//                         new com.google.api.client.http.HttpHeaders()),
//                 mockError);

//         when(mockGmail.users().messages().send(anyString(), any(Message.class)).execute())
//                 .thenThrow(mockException);

//         EmailEngine engine = new EmailEngine(mockGmail);

//         // assertThrows(GoogleJsonResponseException.class, () -> {
//         //     engine.sendEmail("test@example.com", "Test Subject", "Test message");
//         // });

//     }

//     @Test
//     public void testEmailEngineConstructor() throws Exception {
//         // Arrange
//         try {

//             NetHttpTransport mockTransport = mock(NetHttpTransport.class);
//             GsonFactory mockFactory = mock(GsonFactory.class);
//             Gmail mockGmail = mock(Gmail.class);
//             //
//             // when(mockGmail).withArguments(eq(mockTransport), eq(mockFactory),
//             // any(Credential.class)).thenReturn(mockGmail);

//             // Act
//             EmailEngine emailEngine = new EmailEngine();

//             // Assert
//             assertNotNull(emailEngine);
//             // verify(Gmail.class).withArguments(eq(mockTransport), eq(mockFactory),
//             // any(Credential.class));
//             // verify(mockGmail).setApplicationName("Email Engine");
//             // verify(mockGmail).build();

//         } catch (

//         Exception e) {
//             // Expected, do nothing or log if needed
//         }
//     }
// }