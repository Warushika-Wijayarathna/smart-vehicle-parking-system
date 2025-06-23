package com.primespark.userservice.config;

import com.primespark.userservice.service.impl.UserServiceImpl;
import com.primespark.userservice.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // Get the Authorization header from the request
        String authorization = httpServletRequest.getHeader("Authorization");
        String token = null;
        String email = null;

        // Check if the Authorization header is present and starts with "Bearer "
        if (null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            email = jwtUtil.getUsernameFromToken(token);
            Claims claims = jwtUtil.getUserRoleCodeFromToken(token);
            httpServletRequest.setAttribute("email", email);
            httpServletRequest.setAttribute("role", claims.get("role"));

            System.out.println("email: " + email);
            System.out.println("role: " + claims.get("role"));
        }

        // If email is extracted and no authentication is set in the context
        if (null != email && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(email);

            // Validate the token against the user details
            if (jwtUtil.validateToken(token, userDetails)) {
                String role = (String) httpServletRequest.getAttribute("role");

                List<GrantedAuthority> authorities = Collections.singletonList(
                        new SimpleGrantedAuthority(role)
                );

                System.out.println("Authorities: " + authorities);

                // Create an authentication token and set it in the security context
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, authorities
                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        // Continue the filter chain
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
