package com.devkiu.authentication.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public class JwtUsernameAndPasswordAuthentication extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernamePasswordRequest usernamePasswordRequest =
                    new ObjectMapper()
                            .readValue(
                                    request.getInputStream(),
                                    UsernamePasswordRequest.class
                            );
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            usernamePasswordRequest.getUsername(),
                            usernamePasswordRequest.getPassword()
                    );

            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException("Failed: ", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String key = "jadjkafe9948tjasdkjfkaldsjfj9ae0ruasfnjkadfa#%#RSF#%SAFAEfadf3#";
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setExpiration(java.sql.Date.valueOf(LocalDateTime.now().plusWeeks(2).toLocalDate()))
                .setIssuedAt(new Date())
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();

        response.setHeader("Authorization", "Bearer " + token);
    }

}
