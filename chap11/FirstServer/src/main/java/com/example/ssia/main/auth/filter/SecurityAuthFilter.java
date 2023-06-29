package com.example.ssia.main.auth.filter;

import com.example.ssia.main.auth.token.JwtAuthentication;
import com.example.ssia.main.auth.token.OtpAuthentication;
import com.example.ssia.main.auth.token.UsernamePasswordAuthentication;
import com.example.ssia.main.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SecurityAuthFilter extends OncePerRequestFilter {

    private final AuthenticationManager manager;

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String code = request.getHeader("code");
        String token = request.getHeader("Authorization");

        if (token == null) {
            if (code == null) {
                manager.authenticate(new UsernamePasswordAuthentication(username, password));
            } else {
                manager.authenticate(new OtpAuthentication(username, code));

                String jwtToken = jwtService.createToken(Map.of("username", username));
                response.setHeader("Authorization", jwtToken);
            }
        } else {
            Authentication auth = manager.authenticate(new JwtAuthentication(token));
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        }
    }
}
