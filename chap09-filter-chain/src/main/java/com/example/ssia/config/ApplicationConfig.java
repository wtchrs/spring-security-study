package com.example.ssia.config;

import com.example.ssia.filter.AuthenticationLoggingFilter;
import com.example.ssia.filter.RequestValidationFilter;
import com.example.ssia.filter.StaticKeyAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final StaticKeyAuthenticationFilter staticKeyAuthFilter;

    // @Bean
    public SecurityFilterChain headerAuthExample(HttpSecurity http) throws Exception {
        http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);

        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterAt(staticKeyAuthFilter, BasicAuthenticationFilter.class);
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }
}
