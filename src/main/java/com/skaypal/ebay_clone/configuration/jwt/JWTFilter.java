package com.skaypal.ebay_clone.configuration.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.skaypal.ebay_clone.domain.auth.service.LoginService;
import com.skaypal.ebay_clone.utils.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private LoginService loginForm;

    private JWTUtil jwtUtil;

    @Autowired
    public JWTFilter(LoginService loginForm,
                     JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        this.loginForm = loginForm;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer")) {
            String jwt = authHeader.substring(7);

            if (jwt == null || jwt.isBlank())
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "JWT token cannot be empty");
            else {
                try {
                    String username = jwtUtil.validateTokenAndRetrieveSubject(jwt);
                    UserDetails userDetails = loginForm.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
                            userDetails.getPassword(),
                            userDetails.getAuthorities());
                    if (SecurityContextHolder.getContext().getAuthentication() == null)
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                } catch (JWTVerificationException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token");

                }
            }
        }
        filterChain.doFilter(request, response);


    }

}
