package com.example.ssia.auth;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

@Getter
public class PasswordGrantAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    private final String username;

    private final String password;

    private final String clientId;

    public PasswordGrantAuthenticationToken(Authentication clientPrincipal, String username, String password) {
        super(PasswordGrantAuthenticationConverter.PASSWORD_GRANT_TYPE, clientPrincipal, null);
        this.username = username;
        this.password = password;
        this.clientId = clientPrincipal.getName();
    }
}
