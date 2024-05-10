package edu.duke.ece651.project.team5.backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import edu.duke.ece651.project.team5.shared.model.Student;

public class StudentUserTest {

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