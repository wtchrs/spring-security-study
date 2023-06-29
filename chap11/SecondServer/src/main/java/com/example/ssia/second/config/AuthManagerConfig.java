package com.example.ssia.second.config;

import com.example.ssia.second.auth.provider.OtpAuthProvider;
import com.example.ssia.second.auth.provider.PasswordAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@RequiredArgsConstructor
public class AuthManagerConfig {

    private final PasswordAuthProvider passwordAuthProvider;

    private final OtpAuthProvider otpAuthProvider;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        return builder
                .authenticationProvider(otpAuthProvider)
                .authenticationProvider(passwordAuthProvider)
                .build();
    }
}
