package com.example.ssia.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Component
@Slf4j
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        log.info("CustomAuthSuccessHandler.onAuthenticationSuccess");
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Optional<? extends GrantedAuthority> readAuth =
                authorities.stream().filter(a -> a.getAuthority().equals("read")).findFirst();

        if (readAuth.isPresent()) {
            // if 'read' authority exists.
            response.sendRedirect("/home");
        } else {
            log.info("Has no 'read' authority");
            response.sendRedirect("/error");
        }
    }
}
