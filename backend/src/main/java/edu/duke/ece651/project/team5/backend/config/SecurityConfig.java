package edu.duke.ece651.project.team5.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import edu.duke.ece651.project.team5.backend.service.MyDatabaseUserDetailsService;

// https://www.marcobehler.com/guides/spring-security

// SecurityContext context=SecurityContextHolder.getContext();
// Authentication authentication=context.getAuthentication();
// String username=authentication.getName();
// Object principal=authentication.getPrincipal();
// Collection<?extends GrantedAuthority>authorities=authentication.getAuthorities();

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index/**", "/auth/**", "/session/attendance/student").permitAll()
                .antMatchers("/student/**").hasAuthority("ROLE_STUDENT")
                .antMatchers("/professor/**").hasAuthority("ROLE_PROFESSOR")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                // .formLogin()
                // .loginPage("/auth/login")
                // .defaultSuccessUrl("/auth/dashboard", true)
                // .failureUrl("/auth/login?error=true")
                // .permitAll()
                // .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic()
                .and().addFilterBefore(
                        jwtTokenFilter,
                        UsernamePasswordAuthenticationFilter.class);
        ;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyDatabaseUserDetailsService(); // (1)
    }

}