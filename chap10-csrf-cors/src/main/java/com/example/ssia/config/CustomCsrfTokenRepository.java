package com.example.ssia.config;

import com.example.ssia.entity.JpaTokenRepository;
import com.example.ssia.entity.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private static final String IDENTIFIER_HEADER_NAME = "X-IDENTIFIER";

    private static final String TOKEN_HEADER_NAME = "X-CSRF-TOKEN";

    private static final String TOKEN_PARAMETER_NAME = "_csrf";

    private final JpaTokenRepository jpaTokenRepository;

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken(TOKEN_HEADER_NAME, TOKEN_PARAMETER_NAME, uuid);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String identifier = request.getHeader(IDENTIFIER_HEADER_NAME);
        Optional<Token> existingToken = jpaTokenRepository.findByIdentifier(token.getToken());

        if (existingToken.isPresent()) {
            Token t = existingToken.get();
            t.setToken(token.getToken());
        } else {
            jpaTokenRepository.save(new Token(identifier, token.getToken()));
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader(IDENTIFIER_HEADER_NAME);
        Optional<Token> existingToken = jpaTokenRepository.findByIdentifier(identifier);

        return existingToken
                .map(token -> new DefaultCsrfToken(TOKEN_HEADER_NAME, TOKEN_PARAMETER_NAME, token.getToken()))
                .orElse(null);
    }
}
