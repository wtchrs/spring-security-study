package com.example.ssia.main.config;

import com.example.ssia.main.auth.provider.JwtTokenAuthProvider;
import com.example.ssia.main.auth.provider.OtpAuthProvider;
import com.example.ssia.main.auth.provider.PasswordAuthProvider;
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

    private final JwtTokenAuthProvider jwtTokenAuthProvider;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(passwordAuthProvider)
                .authenticationProvider(otpAuthProvider)
                .authenticationProvider(jwtTokenAuthProvider)
                .build();
    }
}
