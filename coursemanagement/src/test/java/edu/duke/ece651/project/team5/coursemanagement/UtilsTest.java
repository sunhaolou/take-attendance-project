// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.StringReader;

// public class UtilsTest {
  
//   @Test
//   public void testReadInputWithErrorHandle_ValidInput() throws IOException {
//     // Set up the input reader
//     BufferedReader inputReader = new BufferedReader(new StringReader("3\n"));
//     // Call the method under test
//     int result = Utils.readInputWithErrorHandle(inputReader, 5);
//     // Verify the result
//     assertEquals(3, result);
//   }
  
//   @Test
//   public void testReadInputWithErrorHandle_InvalidInput() throws IOException {
//     // Set up the input reader
//     BufferedReader inputReader = new BufferedReader(new StringReader("abc\n1\n"));
//     // Set up the output stream
//     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//     PrintStream printStream = new PrintStream(outputStream);
//     // Call the method under test
//     int result = Utils.readInputWithErrorHandle(inputReader, 5);
//     // Verify the output
//     String expectedOutput = "The input should be a number between 1 and 5. Please try again:\n";
//     assertEquals(expectedOutput, outputStream.toString());
//     // Verify the result
//     assertEquals(1, result);
//   }
  
//   @Test
//   public void testReadInputWithErrorHandle_OutOfRangeInput() throws IOException {
//     // Set up the input reader
//     BufferedReader inputReader = new BufferedReader(new StringReader("6\n2\n"));
//     // Set up the output stream
//     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//     PrintStream printStream = new PrintStream(outputStream);
//     // Call the method under test
//     int result = Utils.readInputWithErrorHandle(inputReader, 5);
//     // Verify the output
//     String expectedOutput = "The input should be between 1 and 5. Please try again:\n";
//     assertEquals(expectedOutput, outputStream.toString());
//     // Verify the result
//     assertEquals(2, result);
//   }
// }