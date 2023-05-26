package com.example.ssia.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class WebAuthorizationConfig {

    private final AuthenticationSuccessHandler authSuccessHandler;
    private final AuthenticationFailureHandler authFailureHandler;

    // @Bean
    public SecurityFilterChain httpBasicFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(c -> {
            c.realmName("OTHER");
            c.authenticationEntryPoint(new CustomEntryPoint());
        });

        http.authorizeHttpRequests()
                .anyRequest().authenticated();

        return http.build();
    }

    // @Bean
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .defaultSuccessUrl("/home", true);

        http.authorizeHttpRequests()
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public SecurityFilterChain formLoginFilterChainWithCustomHandler(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and()
                .httpBasic();

        http.authorizeHttpRequests()
                .anyRequest().authenticated();

        return http.build();
    }
}
