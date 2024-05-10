package edu.duke.ece651.project.team5.frontend;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.frontend.model.SectionReport;
import edu.duke.ece651.project.team5.frontend.model.Student;
import edu.duke.ece651.project.team5.frontend.model.StudentReport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class StudentUserTest {
  @Test
  void testExportAttendance() throws IOException {
    BufferedReader mockInput = mock(BufferedReader.class);
    Socket mockSocket = mock(Socket.class);
    BufferedReader mockSocketIn = mock(BufferedReader.class);
    PrintWriter mockSocketOut = mock(PrintWriter.class);
    ObjectMapper objectMapperMock = Mockito.mock(ObjectMapper.class);
    when(mockInput.readLine()).thenReturn("1");
    StudentUser studentUser = new StudentUser("username", "password", mockInput, mockSocket, mockSocketIn,
        mockSocketOut);
    StudentReport studentReport = new StudentReport();
    when(objectMapperMock.readValue(any(String.class), any(TypeReference.class)))
        .thenReturn(studentReport);
    studentUser.setObjectMapper(objectMapperMock);
    when(mockSocketIn.readLine()).thenReturn("");
    studentUser.takeAction();
  }

  @Test
  void testChangePreference() throws IOException {
    BufferedReader mockInput = mock(BufferedReader.class);
    Socket mockSocket = mock(Socket.class);
    BufferedReader mockSocketIn = mock(BufferedReader.class);
    PrintWriter mockSocketOut = mock(PrintWriter.class);
    ObjectMapper objectMapperMock = Mockito.mock(ObjectMapper.class);
    when(mockInput.readLine()).thenReturn("2").thenReturn("a").thenReturn("yes");
    StudentUser studentUser = new StudentUser("username", "password", mockInput, mockSocket, mockSocketIn,
        mockSocketOut);
    StudentReport studentReport = new StudentReport();
    when(objectMapperMock.readValue(any(String.class), any(TypeReference.class)))
        .thenReturn(studentReport);
    studentUser.setObjectMapper(objectMapperMock);
    when(mockSocketIn.readLine()).thenReturn("success");
    studentUser.takeAction();
  }
}
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.duke.ece651.project.team5.frontend.model.Student;
import edu.duke.ece651.project.team5.frontend.model.StudentReport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentUserTest {
  @Test
  void testExportAttendance() throws IOException {
    BufferedReader mockInput = mock(BufferedReader.class);
    Socket mockSocket = mock(Socket.class);
    BufferedReader mockSocketIn = mock(BufferedReader.class);
    PrintWriter mockSocketOut = mock(PrintWriter.class);
    ObjectMapper objectMapperMock = Mockito.mock(ObjectMapper.class);
    when(mockInput.readLine()).thenReturn("1");
    StudentUser studentUser = new StudentUser("username", "password", mockInput, mockSocket, mockSocketIn,
        mockSocketOut);
    StudentReport studentReport = new StudentReport();
    when(objectMapperMock.readValue(any(String.class), any(TypeReference.class)))
        .thenReturn(studentReport);
    studentUser.setObjectMapper(objectMapperMock);
    when(mockSocketIn.readLine()).thenReturn("");
    studentUser.takeAction();
  }

  @Test
  void testChangePreference() throws IOException {
    BufferedReader mockInput = mock(BufferedReader.class);
    Socket mockSocket = mock(Socket.class);
    BufferedReader mockSocketIn = mock(BufferedReader.class);
    PrintWriter mockSocketOut = mock(PrintWriter.class);
    ObjectMapper objectMapperMock = Mockito.mock(ObjectMapper.class);
    when(mockInput.readLine()).thenReturn("2").thenReturn("a").thenReturn("yes");
    StudentUser studentUser = new StudentUser("username", "password", mockInput, mockSocket, mockSocketIn,
        mockSocketOut);
    StudentReport studentReport = new StudentReport();
    when(objectMapperMock.readValue(any(String.class), any(TypeReference.class)))
        .thenReturn(studentReport);
    studentUser.setObjectMapper(objectMapperMock);
    when(mockSocketIn.readLine()).thenReturn("success");
    studentUser.takeAction();
  }

  @Test
  void testGetPassword() {
    Student student = new Student();
    student.setPassword("password");
    StudentUser studentUser = new StudentUser(student);
    assertEquals("password", studentUser.getPassword());
  }

  @Test
  void testGetUsername() {
    Student student = new Student();
    student.setNetId("username");
    StudentUser studentUser = new StudentUser(student);
    assertEquals("username", studentUser.getUsername());
  }

  @Test
  void testIsAccountNonExpired() {
    Student student = new Student();
    StudentUser studentUser = new StudentUser(student);
    assertEquals(true, studentUser.isAccountNonExpired());
  }

  @Test
  void testIsAccountNonLocked() {
    Student student = new Student();
    StudentUser studentUser = new StudentUser(student);
    assertEquals(true, studentUser.isAccountNonLocked());
  }

  @Test
  void testIsCredentialsNonExpired() {
    Student student = new Student();
    StudentUser studentUser = new StudentUser(student);
    assertEquals(true, studentUser.isCredentialsNonExpired());
  }

  @Test
  void testIsEnabled() {
    Student student = new Student();
    StudentUser studentUser = new StudentUser(student);
    assertEquals(true, studentUser.isEnabled());
  }

  @Test
  void testGetAuthorities() {
    Student student = new Student();
    StudentUser studentUser = new StudentUser(student);
    Collection<? extends GrantedAuthority> authorities = studentUser.getAuthorities();
    assertEquals(1, authorities.size());
    assertEquals("ROLE_STUDENT", authorities.iterator().next().getAuthority());
  }
}