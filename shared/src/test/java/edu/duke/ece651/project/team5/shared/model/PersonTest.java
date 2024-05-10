// package edu.duke.ece651.project.team5.shared.model;

// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.Test;

// import static org.junit.Assert.assertEquals;

// import org.junit.Before;

// public class PersonTest {

//   private Person person;

//   @Before
//   public void setup() {
//     person = new Person("netId", "password", "legalName", "email", "phone");
//   }

//   @Test
//   public void testGetters() {
//     assertEquals("netId", person.getNetId());
//     assertEquals("password", person.getPassword());
//     assertEquals("legalName", person.getLegalName());
//     assertEquals("email", person.getEmail());
//     assertEquals("phone", person.getPhone());
//   }

//   @Test
//   public void testSetters() {
//     person.setNetId("newNetId");
//     person.setPassword("newPassword");
//     person.setLegalName("newLegalName");
//     person.setEmail("newEmail");
//     person.setPhone("newPhone");

//     assertEquals("newNetId", person.getNetId());
//     assertEquals("newPassword", person.getPassword());
//     assertEquals("newLegalName", person.getLegalName());
//     assertEquals("newEmail", person.getEmail());
//     assertEquals("newPhone", person.getPhone());
//   }

//   private static class PersonImpl extends Person {
//     public PersonImpl(String netId, String password, String legalName, String email, String phone) {
//       super(netId, password, legalName, email, phone);
//     }
//   }
// }
