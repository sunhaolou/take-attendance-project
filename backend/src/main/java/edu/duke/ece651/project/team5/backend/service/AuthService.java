package edu.duke.ece651.project.team5.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.duke.ece651.project.team5.backend.config.JwtService;
import edu.duke.ece651.project.team5.backend.model.ProfessorUser;
import edu.duke.ece651.project.team5.backend.model.StudentUser;
import edu.duke.ece651.project.team5.backend.repository.ProfessorDao;
import edu.duke.ece651.project.team5.backend.repository.StudentDao;
import edu.duke.ece651.project.team5.shared.request.*;
import edu.duke.ece651.project.team5.shared.model.*;
import edu.duke.ece651.project.team5.shared.response.*;

/**
 * The class to authenticate user
 * 
 */
/**
 * The AuthService class provides authentication services for professors and
 * students.
 * It allows professors and students to log in by verifying their credentials.
 */
@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ProfessorDao professorDao;

    public LoginResponse authenticate(LoginRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(request.getUserNetId(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();
            String token = jwtService.generateToken(user);
            String role = user.getAuthorities().iterator().next().getAuthority();
            String netId = user.getUsername();
            Optional<Student> student = studentDao.findByNetId(netId);
            Optional<Professor> professor = professorDao.findByNetId(netId);
            String legalName = "";
            if (student.isPresent()) {

                legalName = student.get().getLegalName();

            } else if (professor.isPresent()) {
                legalName = professor.get().getLegalName();
            } else {
                throw new IllegalArgumentException("User not found");
            }
            return new LoginResponse(token, role, legalName);
        } catch (BadCredentialsException e) {
            return new LoginResponse("invalid credentials");
        }

    }

    // public String register(RegisterRequest request) {
    //     String netId = request.getUserNetId();
    //     String legalName = request.getLegalName();
    //     String preferredName = request.getPreferredName();
    //     String password = request.getPassword();
    //     String encodedPassword = passwordEncoder.encode(password);
    //     String flag = request.getFlag();
    //     String email = request.getEmail();
    //     String phone = request.getPhone();

    //     Optional<Student> student = studentDao.findByNetId(netId);
    //     Optional<Professor> professor = professorDao.findByNetId(netId);

    //     if (student.isPresent()) {
    //         throw new IllegalArgumentException("User with netId: " + netId + " already exists");
    //     } else if (professor.isPresent()) {
    //         throw new IllegalArgumentException("User with netId: " + netId + " already exists");
    //     }
    //     if (flag.equals("student")) {
    //         Student newStudent = new Student(netId, encodedPassword, legalName, preferredName, email, phone);
    //         studentDao.save(newStudent);
    //         StudentUser studentUser = new StudentUser(newStudent);
    //         return jwtService.generateToken(studentUser);
    //     } else if (flag.equals("professor")) {
    //         Professor newProfessor = new Professor(netId, legalName, email, phone, encodedPassword);
    //         professorDao.save(newProfessor);
    //         ProfessorUser professorUser = new ProfessorUser(newProfessor);
    //         return jwtService.generateToken(professorUser);

    //     } else {
    //         throw new IllegalArgumentException("Invalid flag");
    //     }

    // }

    public Boolean verifyStudentCredential(String netId, String password) {
        Optional<Student> student = studentDao.findByNetId(netId);
        if (student.isEmpty()) {
            return false;
        }
        Boolean result = passwordEncoder.matches(password, student.get().getPassword());
        return result;
    }
}
