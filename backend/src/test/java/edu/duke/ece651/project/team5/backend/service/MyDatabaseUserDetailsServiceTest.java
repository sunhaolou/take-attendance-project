package edu.duke.ece651.project.team5.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.duke.ece651.project.team5.backend.repository.ProfessorDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.shared.model.Professor;
import edu.duke.ece651.project.team5.shared.model.Student;

import java.util.Optional;

public class MyDatabaseUserDetailsServiceTest {

    @InjectMocks
    private MyDatabaseUserDetailsService userDetailsService;

    @Mock
    private ProfessorDao professorDao;

    @Mock
    private StudentDao studentDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_Student() {
        String username = "student1";
        Student student = new Student();
        student.setNetId(username);
        student.setPassword("pass");
        when(studentDao.findByNetId(username)).thenReturn(Optional.of(student));
        when(professorDao.findByNetId(username)).thenReturn(Optional.empty());

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT")));
    }

    @Test
    void testLoadUserByUsername_Professor() {
        String username = "professor1";
        Professor professor = new Professor();
        professor.setNetId(username);
        professor.setPassword("pass");
        when(studentDao.findByNetId(username)).thenReturn(Optional.empty());
        when(professorDao.findByNetId(username)).thenReturn(Optional.of(professor));

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PROFESSOR")));
    }

    @Test
    void testLoadUserByUsername_NotFound() {
        String username = "unknownUser";
        when(studentDao.findByNetId(username)).thenReturn(Optional.empty());
        when(professorDao.findByNetId(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
    }
}
