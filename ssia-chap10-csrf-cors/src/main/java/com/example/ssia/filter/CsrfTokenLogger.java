package com.example.ssia.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;

@Slf4j
public class CsrfTokenLogger implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        log.info("CSRF token [{}]", token.getToken());
        chain.doFilter(request, response);
    }
}
