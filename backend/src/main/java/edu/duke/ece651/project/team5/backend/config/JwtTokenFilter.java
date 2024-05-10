package edu.duke.ece651.project.team5.backend.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.api.services.gmail.Gmail.Users.History.List;

import edu.duke.ece651.project.team5.backend.service.MyDatabaseUserDetailsService;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtTokenUtil;

    @Autowired
    private MyDatabaseUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {
        // Get authorization header and validate
        final String authorizationHeader;
        if (request.getHeader("Authorization") != null) {
            authorizationHeader = request.getHeader("Authorization");
        } else {
            authorizationHeader = request.getHeader("authorization");
        }

        final String jwt;
        final String username;
        if (authorizationHeader == null) {
            chain.doFilter(request, response);
            return;
        } else {
            jwt = authorizationHeader.split(" ")[1].trim();
            // System.out.println(jwt);
            username = jwtTokenUtil.extractUsername(jwt);
            // System.out.println(username);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // System.out.println(request.getMethod() + request.getRequestURL());
        chain.doFilter(request, response);
    }

}