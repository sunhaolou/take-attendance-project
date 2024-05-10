
package edu.duke.ece651.project.team5.backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import edu.duke.ece651.project.team5.shared.model.Professor;

public class ProfessorUserTest {

    @Test
    public void testGetPassword() {
        Professor professor = new Professor();
        professor.setPassword("password");

        ProfessorUser professorUser = new ProfessorUser(professor);

        assertEquals("password", professorUser.getPassword());
    }

    @Test
    public void testGetUsername() {
        Professor professor = new Professor();
        professor.setNetId("netId");

        ProfessorUser professorUser = new ProfessorUser(professor);

        assertEquals("netId", professorUser.getUsername());
    }

    @Test
    public void testIsAccountNonExpired() {
        Professor professor = new Professor();
        ProfessorUser professorUser = new ProfessorUser(professor);

        assertTrue(professorUser.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        Professor professor = new Professor();
        ProfessorUser professorUser = new ProfessorUser(professor);

        assertTrue(professorUser.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        Professor professor = new Professor();
        ProfessorUser professorUser = new ProfessorUser(professor);

        assertTrue(professorUser.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        Professor professor = new Professor();
        ProfessorUser professorUser = new ProfessorUser(professor);

        assertTrue(professorUser.isEnabled());
    }

    @Test
    public void testGetAuthorities() {
        Professor professor = new Professor();
        ProfessorUser professorUser = new ProfessorUser(professor);

        Collection<? extends GrantedAuthority> authorities = professorUser.getAuthorities();

        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_PROFESSOR")));
    }
}