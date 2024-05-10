package edu.duke.ece651.project.team5.frontend;

import org.bouncycastle.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.frontend.model.SectionReport;
import edu.duke.ece651.project.team5.frontend.model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProfessorUserTest {

  @Test
  public void testConstructor() {
    BufferedReader mockInput = mock(BufferedReader.class);
    Socket mockSocket = mock(Socket.class);
    BufferedReader mockSocketIn = mock(BufferedReader.class);
    PrintWriter mockSocketOut = mock(PrintWriter.class);

    ProfessorUser professorUser = new ProfessorUser("username", "password", mockInput, mockSocket, mockSocketIn,
        mockSocketOut);

    assertEquals("username", professorUser.getUserNetID());
    assertEquals("password", professorUser.getPassword());
  }

  @Test
  public void testTakeAction() throws IOException {
    BufferedReader mockInput = mock(BufferedReader.class);
    Socket mockSocket = mock(Socket.class);
    BufferedReader mockSocketIn = mock(BufferedReader.class);
    PrintWriter mockSocketOut = mock(PrintWriter.class);
    when(mockInput.readLine()).thenReturn("6");
    ProfessorUser professorUser = new ProfessorUser("username", "password", mockInput, mockSocket, mockSocketIn,
        mockSocketOut);
    assertThrows(IllegalArgumentException.class, () -> professorUser.takeAction());
  }

  @Test
  public void testBrowseAndSelect() throws IOException {
    BufferedReader mockInput = mock(BufferedReader.class);
    Socket mockSocket = mock(Socket.class);
    BufferedReader mockSocketIn = mock(BufferedReader.class);
    PrintWriter mockSocketOut = mock(PrintWriter.class);
    when(mockInput.readLine()).thenReturn("2");
    ProfessorUser professorUser = new ProfessorUser("username", "password", mockInput, mockSocket, mockSocketIn,
        mockSocketOut);
    ObjectMapper objectMapperMock = Mockito.mock(ObjectMapper.class);
    List<Student> mockStudentList = new ArrayList<>();
    mockStudentList.add(new Student("Alice", "alice123"));
    mockStudentList.add(new Student("Bob", "bob456"));
    List<String> mockStringList = new ArrayList<>();
    mockStringList.add("session1");
    mockStringList.add("session2");
    when(objectMapperMock.readValue(any(String.class), any(TypeReference.class)))
        .thenReturn(mockStudentList)
        .thenReturn(mockStringList);
    professorUser.course_section_id = "123";
    professorUser.setObjectMapper(objectMapperMock);
    when(mockSocketIn.readLine()).thenReturn("");
    professorUser.takeAction();
  }

  @Test
  public void testTakeAttendance() throws IOException {
    BufferedReader inputMock = Mockito.mock(BufferedReader.class);
    BufferedReader socketInMock = Mockito.mock(BufferedReader.class);
    PrintWriter socketOutMock = Mockito.mock(PrintWriter.class);
    Socket socketMock = Mockito.mock(Socket.class);
    ObjectMapper objectMapperMock = Mockito.mock(ObjectMapper.class);
    List<Student> mockStudentList = new ArrayList<>();
    mockStudentList.add(new Student("Alice", "alice123"));
    mockStudentList.add(new Student("Bob", "bob456"));
    List<String> mockStringList = new ArrayList<>();
    mockStringList.add("session1");
    mockStringList.add("session2");
    when(objectMapperMock.readValue(any(String.class), any(TypeReference.class)))
        .thenReturn(mockStringList)
        .thenReturn(mockStudentList);
    ProfessorUser professorUser = new ProfessorUser("username", "password", inputMock, socketMock, socketInMock,
        socketOutMock);
    professorUser.course_section_id = "123";
    professorUser.setObjectMapper(objectMapperMock);
    when(inputMock.readLine()).thenReturn("1");
    when(socketInMock.readLine()).thenReturn("");
    professorUser.takeAction();
  }

  @Test
  public void testSearchAndSelect() throws IOException {
    BufferedReader mockInput = mock(BufferedReader.class);
    Socket mockSocket = mock(Socket.class);
    BufferedReader mockSocketIn = mock(BufferedReader.class);
    PrintWriter mockSocketOut = mock(PrintWriter.class);
    when(mockInput.readLine()).thenReturn("3").thenReturn("1");
    ProfessorUser professorUser = new ProfessorUser("username", "password", mockInput, mockSocket, mockSocketIn,
        mockSocketOut);
    ObjectMapper objectMapperMock = Mockito.mock(ObjectMapper.class);
    List<Student> mockStudentList = new ArrayList<>();
    mockStudentList.add(new Student("Alice", "alice123"));
    mockStudentList.add(new Student("Bob", "bob456"));
    List<String> mockStringList = new ArrayList<>();
    mockStringList.add("session1");
    mockStringList.add("session2");
    when(objectMapperMock.readValue(any(String.class), any(TypeReference.class)))
        .thenReturn(mockStudentList)
        .thenReturn(mockStringList);
    professorUser.course_section_id = "123";
    professorUser.setObjectMapper(objectMapperMock);
    when(mockSocketIn.readLine()).thenReturn("");
    professorUser.takeAction();
  }

  @Test
  public void testExport() throws IOException {
    BufferedReader mockInput = mock(BufferedReader.class);
    Socket mockSocket = mock(Socket.class);
    BufferedReader mockSocketIn = mock(BufferedReader.class);
    PrintWriter mockSocketOut = mock(PrintWriter.class);
    when(mockInput.readLine()).thenReturn("5").thenReturn("1");
    ProfessorUser professorUser = new ProfessorUser("username", "password", mockInput, mockSocket, mockSocketIn,
        mockSocketOut);
    ObjectMapper objectMapperMock = Mockito.mock(ObjectMapper.class);
    List<SectionReport> mockStudentList = new ArrayList<>();
    mockStudentList.add(new SectionReport());
    mockStudentList.add(new SectionReport());
    when(objectMapperMock.readValue(any(String.class), any(TypeReference.class)))
        .thenReturn(mockStudentList);
    professorUser.course_section_id = "123";
    professorUser.setObjectMapper(objectMapperMock);
    when(mockSocketIn.readLine()).thenReturn("");
    professorUser.takeAction();
  }

  @Test
  public void testChooseCourse() throws IOException {
    BufferedReader mockInput = mock(BufferedReader.class);
    Socket mockSocket = mock(Socket.class);
    BufferedReader mockSocketIn = mock(BufferedReader.class);
    PrintWriter mockSocketOut = mock(PrintWriter.class);
    when(mockInput.readLine()).thenReturn("ab").thenReturn("10").thenReturn("4").thenReturn("1");
    ProfessorUser professorUser = new ProfessorUser("username", "password", mockInput, mockSocket, mockSocketIn,
        mockSocketOut);
    professorUser.course_section_id = "123";
    when(mockSocketIn.readLine()).thenReturn("ece651, afa123, cea90");
    professorUser.takeAction();
  }
}
