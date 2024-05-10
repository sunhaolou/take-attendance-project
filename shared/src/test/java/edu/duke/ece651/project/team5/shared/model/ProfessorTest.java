// package edu.duke.ece651.project.team5.shared.model;

// import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.*;

// import java.util.HashSet;
// import java.util.Set;

// public class ProfessorTest {
//     @Test
//     public void testConstructor(){
//         Professor professor = new Professor("test123", "test test", "test@duke.edu", "01234567890", "password", new byte[0]);
//         assertNotNull(professor);
//         Professor professor1 = new Professor("test123", "test test", "test@duke.edu", "01234567890", "password", new byte[0], new HashSet<String>());
//         assertNotNull(professor1);
//         Professor professor2 = new Professor("test123", "password");
//         assertNotNull(professor2);
//     }
package edu.duke.ece651.project.team5.shared.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

public class ProfessorTest {

    private Professor professor;

    private Section section;

    @BeforeEach
    public void setup() {
        section = new Section();
        Set<Section> courseSections = new HashSet<>();
        courseSections.add(section);

        professor = new Professor("netId", "legalName", "email", "phone", "password");
        professor.setCourseSections(courseSections);
    }

    @Test
    public void testGetters() {
        assertEquals("netId", professor.getNetId());
        assertEquals("legalName", professor.getLegalName());
        assertEquals("email", professor.getEmail());
        assertEquals("phone", professor.getPhone());
        assertEquals("password", professor.getPassword());
        assertEquals(1, professor.getCourseSections().size());
    }

    @Test
    public void testSetters() {
        professor.setNetId("newNetId");
        professor.setLegalName("newLegalName");
        professor.setEmail("newEmail");
        professor.setPhone("newPhone");
        professor.setPassword("newPassword");

        assertEquals("newNetId", professor.getNetId());
        assertEquals("newLegalName", professor.getLegalName());
        assertEquals("newEmail", professor.getEmail());
        assertEquals("newPhone", professor.getPhone());
        assertEquals("newPassword", professor.getPassword());
    }

    @Test
    public void testAddCourseSection() {
        Section newSection = mock(Section.class);
        professor.addCourseSection(newSection);

        assertEquals(2, professor.getCourseSections().size());
    }

    @Test
    public void testGetCourseSectionIds() {
        assertEquals(1, professor.getCourseSectionIds().size());
    }
}

//     @Test
//     public void testGettersAndSetters() {
//         // Create a Professor object
//         Professor professor = new Professor("ar123", "afsaneh", "ar123@duke.edu", "9881234444", "123456789");

//         // Test getters and setters
//         assertEquals("ar123", professor.getNetId());
//         assertEquals("123456789", professor.getPassword());
//         assertEquals("afsaneh", professor.getLegalName());
//         assertEquals("ar123@duke.edu", professor.getEmail());
//         assertEquals("9881234444", professor.getPhone());

//         professor.setNetId("new_professor123");
//         professor.setPassword("new_password");
//         professor.setLegalName("Jane Doe");
//         professor.setEmail("jane@example.com");
//         professor.setPhone("987654321");

//         assertEquals("new_professor123", professor.getNetId());
//         assertEquals("new_password", professor.getPassword());
//         assertEquals("Jane Doe", professor.getLegalName());
//         assertEquals("jane@example.com", professor.getEmail());
//         assertEquals("987654321", professor.getPhone());
//     }

//     @Test
//     public void testCourseSections() {
//         // Create a Professor object
//         Professor professor = new Professor("professor123", "password", "John Doe", "john@example.com", "123456789");

//         // Create a set of course sections
//         Set<String> courseSections = new HashSet<>();
//         courseSections.add("CS101-001");
//         courseSections.add("CS102-001");

//         // Set course sections
//         professor.setcourseSections(courseSections);

//         // Test if course sections are set correctly
//         assertEquals(courseSections, professor.getCourseSections());

//         // Add a new course section
//         professor.addcourseSections("CS103-001");

//         // Test if the new course section is added
//         assertTrue(professor.getCourseSections().contains("CS103-001"));
//     }
// }