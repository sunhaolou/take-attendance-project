package edu.duke.ece651.project.team5.backend.model;

import java.util.ArrayList;
import java.util.List;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.duke.ece651.project.team5.shared.model.Professor;

public class ProfessorUser extends Professor implements UserDetails {
    private Professor professor;

    public ProfessorUser(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String getPassword() {
        return professor.getPassword();
    }

    @Override
    public String getUsername() {
        return professor.getNetId();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_PROFESSOR"));

        return authorities;
    }
}
