package edu.duke.ece651.project.team5.shared.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AttendanceStatusTest {
  @Test
  public void testGetById_NotFound() {
    assertNull(AttendanceStatus.getById(4));
  }

  @Test
  public void testGetById() {
    assertEquals(AttendanceStatus.PRESENT, AttendanceStatus.getById(1));
    assertEquals(AttendanceStatus.ABSENT, AttendanceStatus.getById(2));
    assertEquals(AttendanceStatus.TARDY, AttendanceStatus.getById(3));
  }
  
  @Test
  public void testGetId() {
    assertEquals(1, AttendanceStatus.PRESENT.getId());
    assertEquals(2, AttendanceStatus.ABSENT.getId());
    assertEquals(3, AttendanceStatus.TARDY.getId());
  }
  
  @Test
  public void testGetStatus() {
    assertEquals("PRESENT", AttendanceStatus.PRESENT.getStatus());
    assertEquals("ABSENT", AttendanceStatus.ABSENT.getStatus());
    assertEquals("TARDY", AttendanceStatus.TARDY.getStatus());
  }

  @Test
  public void testSetId(){
    AttendanceStatus status = AttendanceStatus.PRESENT;
    status.setID(3);
    assertEquals(3, status.getId());
  }
  
}
