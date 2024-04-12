package com.acro.jwrt.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor   //if i declare a private field in class, it will automatically create its constructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {          //once per requested filter, is to enable everytime someone hit url,
                                                                              // it goes to auth first or too
    
    private final JwtService jwtService;           //this class is used to manipulate and get information from jwt token
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

            final String authHeader = request.getHeader("Authorization");        //this req.get...... contains the JWT token
            final String jwt;
            final String userEmail;
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);      //7 cause initial is "Bearer "
            userEmail = jwtService.extractUsername(jwt);

            //basically checking if the user is not already authenticated
            if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
                /*
                user not null
                check for authentication with backend
                 */

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            }





    }
}
