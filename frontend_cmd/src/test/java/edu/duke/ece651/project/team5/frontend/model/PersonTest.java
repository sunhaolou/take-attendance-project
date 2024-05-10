package edu.duke.ece651.project.team5.frontend.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.duke.ece651.project.team5.frontend.model.Person;

public class PersonTest {
  private static class TestPerson extends Person {
    public TestPerson() {
      super();
    }

    public TestPerson(String netId, String password, String legalName, String email, String phone, byte[] salt) {
      super(netId, password, legalName, email, phone, salt);
    }
  }

  @Test
  public void testDefaultConstructor() {
    TestPerson person = new TestPerson();

    assertEquals("", person.getNetId());
    assertEquals("", person.getPassword());
    assertEquals("", person.getLegalName());
    assertEquals("", person.getEmail());
    assertEquals("", person.getPhone());
    assertDoesNotThrow(() -> person.getSalt());
  }

  @Test
  public void testParameterizedConstructor() {
    byte[] salt = new byte[16];
    TestPerson person = new TestPerson("alice123", "password123", "Alice", "alice@example.com", "1234567890", salt);

    assertEquals("alice123", person.getNetId());
    assertEquals("password123", person.getPassword());
    assertEquals("Alice", person.getLegalName());
    assertEquals("alice@example.com", person.getEmail());
    assertEquals("1234567890", person.getPhone());
    assertEquals(salt, person.getSalt());
  }

  @Test
  public void testSetSalt() {
    byte[] salt = new byte[16];
    TestPerson person = new TestPerson();

    person.setSalt(salt);

    assertEquals(salt, person.getSalt());
  }
}
