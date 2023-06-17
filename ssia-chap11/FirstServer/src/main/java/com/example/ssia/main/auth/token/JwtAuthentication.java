package com.example.ssia.main.auth.token;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication implements Authentication {

    private final String username;
    private final String token;
    private boolean isAuthenticated;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtAuthentication(String token) {
        this.username = null;
        this.token = token;
        this.isAuthenticated = false;
        this.authorities = null;
    }

    public JwtAuthentication(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.token = null;
        this.isAuthenticated = true;
        this.authorities = authorities;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }
}
