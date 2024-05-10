package edu.duke.ece651.project.team5.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

import edu.duke.ece651.project.team5.backend.repository.ProfessorDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.shared.model.Person;
import edu.duke.ece651.project.team5.shared.model.Professor;
import edu.duke.ece651.project.team5.shared.model.Student;

@Service
public class MyDatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> student = studentDao.findByNetId(username);
        Optional<Professor> professor = professorDao.findByNetId(username);

        Person person = null;
        if (student.isPresent()) {
            person = student.get();
        } else if (professor.isPresent()) {
            person = professor.get();
        } else {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        // Create and return the UserDetails object required by Spring Security
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (person instanceof Student) {
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        } else if (person instanceof Professor) {
            authorities.add(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new User(person.getNetId(), person.getPassword(), authorities);
    }
}